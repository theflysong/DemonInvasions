package theflysong.demon_invasions.data.provider;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.List;

public interface IProviderManager {
    List<ProviderFactory> getAllProviders();

    default void registerProviders(DataGenerator generator, String modid, ExistingFileHelper helper) {
        for (ProviderFactory provider : getAllProviders()) {
            generator.addProvider(provider.create(generator, modid, helper));
        }
    }

    default void registerProviders(DataGenerator generator, String modid) {
        for (ProviderFactory provider : getAllProviders()) {
            generator.addProvider(provider.create(generator, modid));
        }
    }
}
