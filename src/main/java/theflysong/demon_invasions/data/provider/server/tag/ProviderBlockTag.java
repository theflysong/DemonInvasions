package theflysong.demon_invasions.data.provider.server.tag;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ProviderBlockTag extends BlockTagsProvider {
    public ProviderBlockTag(DataGenerator generatorIn, String modid, ExistingFileHelper helper) {
        super(generatorIn, modid, helper);
    }

    @Override
    protected void registerTags() {
//        getOrCreateBuilder(BlockTags.LOGS).add(...)...
    }
}
