package theflysong.demon_invasions.entity.goals;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.TargetGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.server.ServerWorld;
import theflysong.demon_invasions.entity.EntityCthulhulEye;
import theflysong.demon_invasions.entity.ModEntities;

public class CthulhulEyeGoals {
    public static class AttackEntity extends Goal {
        public EntityCthulhulEye entity;
        public enum AttackPhase {
            RISING,
            RUSHING,
            TRACED,
            LEAVING,
            END
        }
        public AttackPhase phase = AttackPhase.RUSHING;
        public int phase_countdown = 0x3ffffff;
        public int attack_num = 0;
        public int attack_cd = 30;
        public static final int RISING_CD = 20;
        public static final int RUSHING_CD = 60;
        public static final int TRACED_CD = 40;
        public static final int LEAVING_CD = 40;
        public double movement_speed;
        public Vector3d cur_motion = Vector3d.ZERO;

        public AttackEntity(EntityCthulhulEye entity) {
            this.entity = entity;
            this.movement_speed = entity.getAttribute(Attributes.MOVEMENT_SPEED).getBaseValue();
        }

        @Override
        public boolean shouldExecute() {
            LivingEntity target = entity.getAttackTarget();
            if (target == null)
                return false;
            phase = AttackPhase.RISING;
            phase_countdown = RISING_CD;
            cur_motion = new Vector3d(0, 0.2, 0);
            if (entity.getDataManager().get(EntityCthulhulEye.STAGE) == 0) {
                if (entity.getDataManager().get(EntityCthulhulEye.SUB_STAGE) == 0) {
                    attack_num = 1;
                }
                if (entity.getDataManager().get(EntityCthulhulEye.SUB_STAGE) == 1) {
                    attack_num = 2;
                }
                if (entity.getDataManager().get(EntityCthulhulEye.SUB_STAGE) == 2) {
                    attack_num = 2;
                }
                if (entity.getDataManager().get(EntityCthulhulEye.SUB_STAGE) == 3) {
                    attack_num = 3;
                }
                if (entity.getDataManager().get(EntityCthulhulEye.SUB_STAGE) == 4) {
                    attack_num = 3;
                }
            }
            else {
                if (entity.getDataManager().get(EntityCthulhulEye.SUB_STAGE) == 0) {
                    attack_num = 4;
                }
                if (entity.getDataManager().get(EntityCthulhulEye.SUB_STAGE) == 1) {
                    attack_num = 5;
                }
                if (entity.getDataManager().get(EntityCthulhulEye.SUB_STAGE) == 2) {
                    attack_num = 5;
                }
                if (entity.getDataManager().get(EntityCthulhulEye.SUB_STAGE) == 3) {
                    attack_num = -1; //infinity
                }
                if (entity.getDataManager().get(EntityCthulhulEye.SUB_STAGE) == 4) {
                    attack_num = -1;
                }
            }
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
            attack_cd = Math.max(0, attack_cd - 1);
            double distance_with_target = entity.getDistanceSq(target.getPosX(), target.getPosY(), target.getPosZ());
            if (distance_with_target <= 10 && attack_cd <= 0) {
                entity.attackEntityAsMob(target);
                attack_cd = 30;
                if (phase == AttackPhase.RUSHING) {
                    phase_countdown = 10;
                }
            }
            if (phase == AttackPhase.RISING) {
                phase_countdown --;
                if (phase_countdown <= 0) {
                    phase = AttackPhase.RUSHING;
                    phase_countdown = RUSHING_CD;
                    entity.setRotation(
                            (float) Math.toDegrees(Math.atan2(
                                    target.getPosX() - entity.getPosX(),
                                    target.getPosZ() - entity.getPosZ())),
                            (float) Math.toDegrees(Math.atan2(
                                    target.getPosY() - entity.getPosY(),
                                    target.getPosZ() - entity.getPosZ())));
                    entity.limbSwing = (float) Math.toDegrees(Math.atan2(
                            target.getPosX() - entity.getPosX(),
                            target.getPosY() - entity.getPosY()));
                    double speed = this.entity.getDataManager().get(EntityCthulhulEye.STAGE) == 0 ? 0.8 : 1.2;
                    cur_motion = target.getPositionVec().subtract(entity.getPositionVec()).normalize().mul(speed, speed, speed);
                }
            }
            else if (phase == AttackPhase.RUSHING) {
                phase_countdown --;
                if (phase_countdown <= 0) {
                    phase = AttackPhase.TRACED;
                    phase_countdown = TRACED_CD;
                    double speed = this.entity.getDataManager().get(EntityCthulhulEye.STAGE) == 0 ? 0.4 : 0.6;
                    cur_motion = entity.getMotion().mul(speed, speed, speed);
                }
            }
            else if (phase == AttackPhase.TRACED) {
                phase_countdown --;
                if (phase_countdown <= 0) {
                    if (attack_num > 0 || attack_num == -1) {
                        if (attack_num != -1) {
                            attack_num--;
                        }
                        phase = AttackPhase.RUSHING;
                        entity.setRotation(
                                (float) Math.toDegrees(Math.atan2(
                                        target.getPosX() - entity.getPosX(),
                                        target.getPosZ() - entity.getPosZ())),
                                (float) Math.toDegrees(Math.atan2(
                                        target.getPosY() - entity.getPosY(),
                                        target.getPosZ() - entity.getPosZ())));
                        double speed = this.entity.getDataManager().get(EntityCthulhulEye.STAGE) == 0 ? 0.8 : 1.2;
                        cur_motion = target.getPositionVec().subtract(entity.getPositionVec()).normalize().mul(speed, speed, speed);
                        phase_countdown = RUSHING_CD;
                    }
                    else {
                        phase = AttackPhase.LEAVING;
                        phase_countdown = LEAVING_CD;
                        double speed = this.entity.getDataManager().get(EntityCthulhulEye.STAGE) == 0 ? 0.2 : 0.3;
                        cur_motion = new Vector3d(0, speed, 0);
                    }
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


    public static class SummonDemonEyes extends Goal {
        public EntityCthulhulEye entity;
        boolean should_summon;
        public int countdown = 200;
        public static final int CD = 200;

        public SummonDemonEyes(EntityCthulhulEye entity) {
            this.entity = entity;
        }

        @Override
        public boolean shouldExecute() {
            return true;
        }

        @Override
        public void startExecuting() {
            countdown = CD;
            super.startExecuting();
        }

        @Override
        public void tick() {
            if (entity.getDataManager().get(EntityCthulhulEye.STAGE) == 0) {
                if (entity.getDataManager().get(EntityCthulhulEye.SUB_STAGE) == 0) {
                    should_summon = false;
                }
                if (entity.getDataManager().get(EntityCthulhulEye.SUB_STAGE) == 1) {
                    should_summon = false;
                }
                if (entity.getDataManager().get(EntityCthulhulEye.SUB_STAGE) == 2) {
                    should_summon = true;
                }
                if (entity.getDataManager().get(EntityCthulhulEye.SUB_STAGE) == 3) {
                    should_summon = false;
                }
                if (entity.getDataManager().get(EntityCthulhulEye.SUB_STAGE) == 4) {
                    should_summon = true;
                }
            }
            else {
                if (entity.getDataManager().get(EntityCthulhulEye.SUB_STAGE) == 0) {
                    should_summon = false;
                }
                if (entity.getDataManager().get(EntityCthulhulEye.SUB_STAGE) == 1) {
                    should_summon = false;
                }
                if (entity.getDataManager().get(EntityCthulhulEye.SUB_STAGE) == 2) {
                    should_summon = true;
                }
                if (entity.getDataManager().get(EntityCthulhulEye.SUB_STAGE) == 3) {
                    should_summon = false;
                }
                if (entity.getDataManager().get(EntityCthulhulEye.SUB_STAGE) == 4) {
                    should_summon = true;
                }
            }
            if (! should_summon)
                return;
            countdown --;
            if (countdown <= 0 ){
                countdown = CD;
                ModEntities.DEMON_EYE.get().spawn(
                        (ServerWorld) entity.getEntityWorld(), null, null,
                        entity.getPosition().add(entity.world.rand.nextInt(3) - 1, entity.world.rand.nextInt(3) - 1, entity.world.rand.nextInt(3) - 1),
                        SpawnReason.MOB_SUMMONED, false, false
                );
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
