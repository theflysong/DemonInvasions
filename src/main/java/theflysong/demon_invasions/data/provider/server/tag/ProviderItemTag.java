package theflysong.demon_invasions.data.provider.server.tag;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ProviderItemTag extends ItemTagsProvider {
    public ProviderItemTag(DataGenerator generatorIn, String modid, ExistingFileHelper helper, BlockTagsProvider blockTagsProvider) {
        super(generatorIn, blockTagsProvider, modid, helper);
    }

    @Override
    protected void registerTags() {
//        this.copy(BlockTags.LOGS, ItemTags.LOGS);
//        this.copy(BlockTags.PLANKS, ItemTags.PLANKS);
    }
}
