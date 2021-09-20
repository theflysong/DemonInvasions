package theflysong.demon_invasions.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.monster.IMob;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import theflysong.demon_invasions.entity.goals.DemonEyeGoals;

public class EntityDemonEye extends FlyingEntity implements IMob {
    public EntityDemonEye(EntityType<? extends FlyingEntity> type, World worldIn) {
        super(type, worldIn);
        this.targetSelector.addGoal(1, new DemonEyeGoals.NearestTarget(this, false));
        this.goalSelector.addGoal(1, new DemonEyeGoals.AttackEntity(this));
        this.moveController = new FlyingMovementController(this, 90, true);
    }

    @Override
    protected void registerData() {
        super.registerData();
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return FlyingEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 20.0D)
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
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}
