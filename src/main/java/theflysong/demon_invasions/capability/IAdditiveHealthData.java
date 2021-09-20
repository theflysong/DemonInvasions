package theflysong.demon_invasions.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public interface IAdditiveHealthData extends INBTSerializable<CompoundNBT> {
    double getData();
    void setData(double data);
    void addData(double data);
    void subData(double data);
    void resetData();
}
