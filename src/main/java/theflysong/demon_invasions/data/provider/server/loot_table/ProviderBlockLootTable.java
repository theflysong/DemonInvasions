package theflysong.demon_invasions.data.provider.server.loot_table;

import net.minecraft.data.DataGenerator;
import theflysong.demon_invasions.block.ModBlocks;
import theflysong.demon_invasions.item.ModItems;

public class ProviderBlockLootTable extends ProviderBaseBlockLootTable {
    public ProviderBlockLootTable(DataGenerator generatorIn, String modId) {
        super(generatorIn);
    }

    @Override
    protected void registerTables() {
        getLootTables().put(ModBlocks.LIFE_CRYSTAL.get(), tableNormal(ModBlocks.LIFE_CRYSTAL.getId().getPath(), ModItems.LIFE_CRYSTAL.get()));
    }
}
