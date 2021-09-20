package theflysong.demon_invasions.entity.goals;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.TargetGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.vector.Vector3d;
import theflysong.demon_invasions.entity.EntityDemonEye;

public class DemonEyeGoals {
    public static class AttackEntity extends Goal {
        public EntityDemonEye entity;
        public enum AttackPhase {
            RISING,
            TRACING,
            ATTACKED,
            LEAVING,
            END
        }
        public AttackPhase phase = AttackPhase.TRACING;
        public int phase_countdown = 0x3ffffff;
        public static final int RISING_CD = 20;
        public static final int TRACING_CD = 200;
        public static final int ATTACKED_CD = 40;
        public static final int LEAVING_CD = 40;
        public double movement_speed;
        public double attack_damage;
        public Vector3d cur_motion = Vector3d.ZERO;

        public AttackEntity(EntityDemonEye entity) {
            this.entity = entity;
            this.movement_speed = entity.getAttribute(Attributes.MOVEMENT_SPEED).getBaseValue();
            this.attack_damage = entity.getAttribute(Attributes.ATTACK_DAMAGE).getBaseValue();
        }

        @Override
        public boolean shouldExecute() {
            LivingEntity target = entity.getAttackTarget();
            if (target == null)
                return false;
            phase = AttackPhase.RISING;
            phase_countdown = RISING_CD;
            cur_motion = new Vector3d(0, 0.15, 0);
            return true;
        }

        @Override
        public void resetTask() {
            this.entity.setAggroed(false);
        }

        @Override
        public void startExecuting() {
            this.entity.setAggroed(true);
        }

        @Override
        public boolean shouldContinueExecuting() {
            return phase != AttackPhase.END && entity.getAttackTarget() != null && entity.getAttackTarget().isAlive();
        }

        @Override
        public void tick() {
            entity.setMotion(cur_motion);
            LivingEntity target = entity.getAttackTarget();
            if (phase == AttackPhase.RISING) {
                phase_countdown --;
                if (phase_countdown <= 0) {
                    phase = AttackPhase.TRACING;
                    phase_countdown = TRACING_CD;
                    entity.setRotation(
                            (float) Math.toDegrees(Math.atan2(
                                    target.getPosX() - entity.getPosX(),
                                    target.getPosZ() - entity.getPosZ())),
                            (float) Math.toDegrees(Math.atan2(
                                    target.getPosY() - entity.getPosY(),
                                    target.getPosZ() - entity.getPosZ())));
                    cur_motion = target.getPositionVec().subtract(entity.getPositionVec()).normalize().mul(0.5, 0.5, 0.5);
                }
            }
            else if (phase == AttackPhase.TRACING) {
                double distance = this.entity.getDistance(target);
                if (distance <= 1.5f) {
                    target.attackEntityFrom(DamageSource.causeMobDamage(this.entity), (float)attack_damage);
                    phase = AttackPhase.ATTACKED;
                    phase_countdown = ATTACKED_CD;
                    cur_motion = entity.getMotion().mul(0.2, 0.2, 0.2);
                }
                if (phase_countdown <= 0) {
                    phase = AttackPhase.ATTACKED;
                    phase_countdown = ATTACKED_CD;
                    cur_motion = entity.getMotion().mul(0.2, 0.2, 0.2);
                }
                if (distance >= 16.0f) {
                    phase = AttackPhase.LEAVING;
                    phase_countdown = LEAVING_CD;
                    cur_motion = new Vector3d(0, 0.15, 0);
                }
            }
            else if (phase == AttackPhase.ATTACKED) {
                phase_countdown --;
                if (phase_countdown <= 0) {
                    phase = AttackPhase.LEAVING;
                    phase_countdown = LEAVING_CD;
                    cur_motion = new Vector3d(0, 0.15, 0);
                }
            }
            else if (phase == AttackPhase.LEAVING) {
                phase_countdown --;
                if (phase_countdown <= 0) {
                    phase = AttackPhase.END;
                    cur_motion = Vector3d.ZERO;
                }
            }
        }
    }

    public static class NearestTarget extends TargetGoal {
        public NearestTarget(MobEntity mobIn, boolean checkSight) {
            super(mobIn, checkSight);
        }

        public NearestTarget(MobEntity mobIn, boolean checkSight, boolean nearbyOnlyIn) {
            super(mobIn, checkSight, nearbyOnlyIn);
        }

        @Override
        public boolean shouldExecute() {
            PlayerEntity target = null;
            int dist = Integer.MAX_VALUE;
            for (PlayerEntity player : this.goalOwner.world.getPlayers()) {
                if (player.getDistance(this.goalOwner) <= dist) {
                    target = player;
                }
            }
            if (target == null)
                return false;
            this.goalOwner.setAttackTarget(target);
            return true;
        }

        @Override
        public boolean shouldContinueExecuting() {
            return this.goalOwner.getAttackTarget() != null;
        }
    }
}
