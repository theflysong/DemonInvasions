package theflysong.demon_invasions.data.provider;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.IDataProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public interface IProviderFactory2 {
    IDataProvider create(DataGenerator generator, String modid, ExistingFileHelper helper);
}
