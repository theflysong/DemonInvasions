package theflysong.demon_invasions.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class AdditiveHealthData implements IAdditiveHealthData {
    @CapabilityInject(IAdditiveHealthData.class)
    public static Capability<IAdditiveHealthData> ADDITIVE_HEALTH_CAPABILITY;

    private double health_num = 0;

    @Override
    public double getData() {
        return health_num;
    }

    @Override
    public void setData(double health_num) {
        this.health_num = health_num;
    }

    @Override
    public void addData(double health_num) {
        this.health_num += health_num;
    }

    @Override
    public void subData(double health_num) {
        this.health_num -= health_num;
    }

    @Override
    public void resetData() {
        this.health_num = 0;
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT compoundNBT = new CompoundNBT();
        compoundNBT.putDouble("health_num", health_num);
        return compoundNBT;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        this.health_num = nbt.getDouble("health_num");
    }
}
