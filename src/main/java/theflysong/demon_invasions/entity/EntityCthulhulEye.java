package theflysong.demon_invasions.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import net.minecraftforge.fml.network.NetworkHooks;
import theflysong.demon_invasions.entity.goals.CthulhulEyeGoals;

public class EntityCthulhulEye extends FlyingEntity implements IMob {
    public static final DataParameter<Integer> STAGE = EntityDataManager.createKey(EntityCthulhulEye.class, DataSerializers.VARINT);
    public static final DataParameter<Integer> SUB_STAGE = EntityDataManager.createKey(EntityCthulhulEye.class, DataSerializers.VARINT);
    private final ServerBossInfo boss_info = new ServerBossInfo(this.getDisplayName(), BossInfo.Color.PURPLE, BossInfo.Overlay.PROGRESS);


    protected EntityCthulhulEye(EntityType<? extends EntityCthulhulEye> type, World worldIn) {
        super(type, worldIn);
        this.targetSelector.addGoal(1, new CthulhulEyeGoals.NearestTarget(this, false));
        this.goalSelector.addGoal(1, new CthulhulEyeGoals.AttackEntity(this));
        this.goalSelector.addGoal(1, new CthulhulEyeGoals.SummonDemonEyes(this));
        this.moveController = new FlyingMovementController(this, 90, true);
    }

    @Override
    protected void registerData() {
        this.dataManager.register(STAGE, 0);
        this.dataManager.register(SUB_STAGE, 0);
        super.registerData();
    }


    @Override
    public void readAdditional(CompoundNBT compound) {
        this.dataManager.set(STAGE, compound.getInt("stage"));
        this.dataManager.set(SUB_STAGE, compound.getInt("sub_stage"));
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        compound.putInt("stage", this.dataManager.get(STAGE));
        compound.putInt("sub_stage", this.dataManager.get(SUB_STAGE));
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return FlyingEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 200.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 1.25D)
                .createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, 0.0D)
                .createMutableAttribute(Attributes.ARMOR, 6.0D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 4.0D)
                .createMutableAttribute(Attributes.FLYING_SPEED, 1.25D);
    }

    @Override
    public void setRotation(float yaw, float pitch) {
        super.setRotation(yaw, pitch);
    }

    @Override
    public void tick() {
        if (getHealth() <= 100.0F) {
            this.dataManager.set(STAGE, 1);
            this.getAttribute(Attributes.ARMOR).setBaseValue(2.5D);
            this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(7.0D);
        }
        if (getHealth() <= 120 || getHealth() <= 20) {
            this.dataManager.set(SUB_STAGE, 4);
        }
        else if (getHealth() <= 140 || getHealth() <= 40) {
            this.dataManager.set(SUB_STAGE, 3);
        }
        else if (getHealth() <= 160 || getHealth() <= 60) {
            this.dataManager.set(SUB_STAGE, 2);
        }
        else if (getHealth() <= 180 || getHealth() <= 80) {
            this.dataManager.set(SUB_STAGE, 1);
        }
        else if (getHealth() <= 200 || getHealth() <= 100) {
            this.dataManager.set(SUB_STAGE, 0);
        }
        boss_info.setPercent(this.getHealth() / this.getMaxHealth());
        super.tick();
    }

    @Override
    public void addTrackingPlayer(ServerPlayerEntity player) {
        super.addTrackingPlayer(player);
        this.boss_info.addPlayer(player);
    }

    @Override
    public void removeTrackingPlayer(ServerPlayerEntity player) {
        super.removeTrackingPlayer(player);
        this.boss_info.removePlayer(player);
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
