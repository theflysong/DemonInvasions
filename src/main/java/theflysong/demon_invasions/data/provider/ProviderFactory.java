package theflysong.demon_invasions.data.provider;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.IDataProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ProviderFactory implements IProviderFactory1, IProviderFactory2{
    private IProviderFactory1 factory1 = null;
    private IProviderFactory2 factory2 = null;

    public ProviderFactory(IProviderFactory1 factory1) {
        this.factory1 = factory1;
    }

    public ProviderFactory(IProviderFactory2 factory2) {
        this.factory2 = factory2;
    }

    @Override
    public IDataProvider create(DataGenerator generator, String modid) {
        if (factory1 == null) {
            throw new IllegalArgumentException("It's a binary arguments factory!");
        }
        return factory1.create(generator, modid);
    }

    @Override
    public IDataProvider create(DataGenerator generator, String modid, ExistingFileHelper helper) {
        if (factory2 == null) {
            return factory1.create(generator, modid);
        }
        return factory2.create(generator, modid, helper);
    }
}
