package theflysong.demon_invasions.data.provider.server.loot_table;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.Items;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootFunction;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.RandomValueRange;
import net.minecraft.loot.conditions.RandomChanceWithLooting;
import net.minecraft.loot.functions.ApplyBonus;
import net.minecraft.loot.functions.LootingEnchantBonus;
import net.minecraft.loot.functions.SetCount;
import theflysong.demon_invasions.entity.ModEntities;
import theflysong.demon_invasions.item.ModItems;

public class ProviderEntityLootTable extends ProviderBaseEntityLootTable {
    public ProviderEntityLootTable(DataGenerator generatorIn, String modId) {
        super(generatorIn);
    }

    @Override
    protected void registerTables() {
        getLootTables().put(ModEntities.DEMON_EYE.get(), tableWithPools(
                LootPool.builder()
                        .name("lens")
                        .addEntry(
                                ItemLootEntry.builder(ModItems.LENS.get())
                                        .weight(1)
                                        .acceptFunction(SetCount.builder(RandomValueRange.of(1, 2)))
                                        .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0, 1)))
                        )
                        .addEntry(
                                ItemLootEntry.builder(Items.AIR)
                                        .weight(2)
                        )
        ));

        getLootTables().put(ModEntities.CTHULHUL_EYE.get(), tableWithPools(
                LootPool.builder().name("blood_lens").addEntry(
                                ItemLootEntry.builder(ModItems.BLOOD_LENS.get()).weight(1)
                                        .acceptFunction(SetCount.builder(RandomValueRange.of(1, 2)))
                                        .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0, 1)))),
                LootPool.builder().name("weapons")
                        .addEntry(ItemLootEntry.builder(ModItems.SEA_BOW.get()).weight(5))
                        .addEntry(ItemLootEntry.builder(Items.AIR).weight(35))
                )
        );
    }
}
