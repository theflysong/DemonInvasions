package theflysong.demon_invasions.capability;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import javax.annotation.Nullable;

public class RegisterCapabilities {
    public static void registerCapabilities(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            CapabilityManager.INSTANCE.register(
                    IAdditiveHealthData.class,
                    new Capability.IStorage<IAdditiveHealthData>() {
                        @Nullable
                        @Override
                        public INBT writeNBT(Capability<IAdditiveHealthData> capability, IAdditiveHealthData instance, Direction side) {
                            return null;
                        }

                        @Override
                        public void readNBT(Capability<IAdditiveHealthData> capability, IAdditiveHealthData instance, Direction side, INBT nbt) {

                        }
                    },
                    () -> null
            );
        });
    }
}
