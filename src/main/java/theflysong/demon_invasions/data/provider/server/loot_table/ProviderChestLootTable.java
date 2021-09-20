package theflysong.demon_invasions.data.provider.server.loot_table;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.Items;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.RandomValueRange;
import net.minecraft.loot.functions.LootingEnchantBonus;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.util.ResourceLocation;
import theflysong.demon_invasions.entity.ModEntities;
import theflysong.demon_invasions.item.ModItems;

public class ProviderChestLootTable extends ProviderBaseChestLootTable {
    private final String modid;

    public ProviderChestLootTable(DataGenerator generatorIn, String modId) {
        super(generatorIn);
        modid = modId;
    }

    protected ResourceLocation modLoc(String path) {
        return new ResourceLocation(modid, path);
    }

    @Override
    protected void registerTables() {
        getLootTables().put(modLoc("heart_cave"), tableWithPools(
                LootPool.builder().name("strange_eye").addEntry(
                        ItemLootEntry.builder(ModItems.STRANGE_EYE.get())
                                        .weight(1)
                        )
                        .addEntry(
                                ItemLootEntry.builder(ModItems.LENS.get())
                                        .weight(2)
                                        .acceptFunction(SetCount.builder(RandomValueRange.of(1, 2)))
                                        .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0, 1)))
                        ),
                LootPool.builder().name("weapons")
                        .addEntry(ItemLootEntry.builder(ModItems.WOODEN_BOW.get()).weight(6))
                        .addEntry(ItemLootEntry.builder(ModItems.IRON_BOW.get()).weight(4))
                        .addEntry(ItemLootEntry.builder(ModItems.GOLDEN_BOW.get()).weight(2))
                        .addEntry(ItemLootEntry.builder(ModItems.DIAMOND_BOW.get()).weight(1))
                        .addEntry(ItemLootEntry.builder(Items.AIR).weight(6)),
                LootPool.builder().name("ores")
                        .rolls(new RandomValueRange(1, 2))
                        .addEntry(
                                ItemLootEntry.builder(Items.IRON_INGOT).weight(6)
                                        .acceptFunction(SetCount.builder(new RandomValueRange(1, 7))))
                        .addEntry(
                                ItemLootEntry.builder(Items.GOLD_INGOT).weight(4)
                                        .acceptFunction(SetCount.builder(new RandomValueRange(1, 4))))
                        .addEntry(
                                ItemLootEntry.builder(Items.DIAMOND).weight(3)
                                        .acceptFunction(SetCount.builder(new RandomValueRange(1, 2))))
        ));
    }
}
