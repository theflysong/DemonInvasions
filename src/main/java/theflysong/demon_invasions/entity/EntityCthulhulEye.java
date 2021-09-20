package theflysong.demon_invasions.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.monster.IMob;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import theflysong.demon_invasions.entity.goals.CthulhulEyeGoals;

public class EntityCthulhulEye extends FlyingEntity implements IMob{
    private static final DataParameter<Integer> STAGE = EntityDataManager.createKey(EntityCthulhulEye.class, DataSerializers.VARINT);


    protected EntityCthulhulEye(EntityType<? extends EntityCthulhulEye> type, World worldIn) {
        super(type, worldIn);
        this.targetSelector.addGoal(1, new CthulhulEyeGoals.NearestTarget(this, false));
        this.goalSelector.addGoal(1, new CthulhulEyeGoals.AttackEntity(this));
        this.moveController = new FlyingMovementController(this, 90, true);
    }

    @Override
    protected void registerData() {
        this.dataManager.register(STAGE, 0);
        super.registerData();
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        this.dataManager.set(STAGE, compound.getInt("stage"));
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        compound.putInt("stage", this.dataManager.get(STAGE));
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return FlyingEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 100.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 1.25D)
                .createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, 0.0D)
                .createMutableAttribute(Attributes.ARMOR, 1.0D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 1.5D)
                .createMutableAttribute(Attributes.FLYING_SPEED, 1.25D);
    }

    @Override
    public void setRotation(float yaw, float pitch) {
        super.setRotation(yaw, pitch);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
