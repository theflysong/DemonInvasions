package theflysong.demon_invasions.data.provider.server.loot_table;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.block.CropsBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.BlockStateProperty;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.conditions.MatchTool;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public abstract class ProviderBaseBlockLootTable implements IDataProvider {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    private final Map<Block, LootTable.Builder> lootTables = new HashMap<>();
    private DataGenerator generator = null;

    private static final ILootCondition.IBuilder SILK_TOUCH = MatchTool.builder(ItemPredicate.Builder.create().enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1))));
    private static final ILootCondition.IBuilder NO_SILK_TOUCH = SILK_TOUCH.inverted();
    private static final ILootCondition.IBuilder SHEARS = MatchTool.builder(ItemPredicate.Builder.create().item(Items.SHEARS));
    private static final ILootCondition.IBuilder SILK_TOUCH_OR_SHEARS = SHEARS.alternative(SILK_TOUCH);
    private static final ILootCondition.IBuilder NOT_SILK_TOUCH_OR_SHEARS = SILK_TOUCH_OR_SHEARS.inverted();
    private static final float[] DEFAULT_SAPLING_DROP_RATES = new float[] { 0.05F, 0.0625F, 0.083333336F, 0.1F };

    public ProviderBaseBlockLootTable(DataGenerator generatorIn) {
        generator = generatorIn;
    }

    protected abstract void registerTables();

    public Map<Block, LootTable.Builder> getLootTables() {
        return lootTables;
    }

    @Override
    public void act(DirectoryCache cache) {
        registerTables();

        Map<ResourceLocation, LootTable> tables = new HashMap<>();
        for (Map.Entry<Block, LootTable.Builder> entry : lootTables.entrySet()) {
            tables.put(entry.getKey().getLootTable(), entry.getValue().setParameterSet(LootParameterSets.BLOCK).build());
        }
        writeTables(cache, tables);
    }

    private void writeTables(DirectoryCache cache, Map<ResourceLocation, LootTable> tables) {
        Path outputFolder = generator.getOutputFolder();
        tables.forEach((key, lootTable) -> {
            Path path = getPath(outputFolder, key);
            try {
                IDataProvider.save(GSON, cache, LootTableManager.toJson(lootTable), path);
            } catch (IOException e) {
                LOGGER.error("Couldn't write loot table {}", path, e);
            }
        });
    }

    private static Path getPath(Path pathIn, ResourceLocation id) {
        return pathIn.resolve("data/" + id.getNamespace() + "/loot_tables/" + id.getPath() + ".json");
    }

    @Override
    public String getName() {
        return "Loot Tables";
    }

    protected LootTable.Builder tableNormal(String name, IItemProvider block) {
        LootPool.Builder pool = LootPool.builder()
                .name(name)
                .rolls(ConstantRange.of(1))
                .addEntry(ItemLootEntry.builder(block));
        return LootTable.builder().addLootPool(pool);
    }

    protected LootTable.Builder tableWithPools(LootPool.Builder... pools) {
        LootTable.Builder builder = LootTable.builder();
        for (LootPool.Builder pool : pools) {
            builder.addLootPool(pool);
        }
        return builder;
    }

    protected LootPool.Builder droppingSaplingsAndSticksWithChance(String name, IItemProvider sapling) {
        return LootPool.builder()
                .name(name)
                .rolls(ConstantRange.of(1))
                .acceptCondition(NOT_SILK_TOUCH_OR_SHEARS)
                .addEntry(ItemLootEntry.builder(sapling).weight(2))
                .addEntry(ItemLootEntry.builder(Items.STICK).weight(1))
                .addEntry(ItemLootEntry.builder(Items.AIR).weight(37));
    }

    protected LootPool.Builder droppingSaplingsAndSticksWithHigherChance(String name, IItemProvider sapling) {
        return LootPool.builder()
                .name(name)
                .rolls(ConstantRange.of(1))
                .acceptCondition(NOT_SILK_TOUCH_OR_SHEARS)
                .addEntry(ItemLootEntry.builder(sapling).weight(4))
                .addEntry(ItemLootEntry.builder(Items.STICK).weight(1))
                .addEntry(ItemLootEntry.builder(Items.AIR).weight(35));
    }

    protected LootPool.Builder droppingLeavesWithShearsOrSilkTouch(String name, IItemProvider leaves) {
        return LootPool.builder()
                .name(name)
                .rolls(ConstantRange.of(1))
                .acceptCondition(SILK_TOUCH_OR_SHEARS)
                .addEntry(ItemLootEntry.builder(leaves));
    }

    protected LootPool.Builder droppingSilkwormAndSaplingAndSticksWithChance(String name, IItemProvider silkworm, IItemProvider sapling) {
        return LootPool.builder()
                .name(name)
                .rolls(ConstantRange.of(1))
                .acceptCondition(NOT_SILK_TOUCH_OR_SHEARS)
                .addEntry(ItemLootEntry.builder(silkworm).weight(1))
                .addEntry(ItemLootEntry.builder(sapling).weight(4))
                .addEntry(ItemLootEntry.builder(Items.STICK).weight(1))
                .addEntry(ItemLootEntry.builder(Items.AIR).weight(44));
    }

    protected LootPool.Builder droppingSeeds(String name, IItemProvider seeds) {
        return LootPool.builder()
                .name(name)
                .rolls(ConstantRange.of(1))
                .addEntry(ItemLootEntry.builder(seeds));
    }

    protected LootPool.Builder droppingCropsByAge(String name, Block block, int age, IItemProvider crops) {
        return droppingCropsWithChanceByAge(name, block, age, crops, 1, 4);
    }

    protected LootPool.Builder droppingCropsWithChanceByAge(String name, Block block, int age, IItemProvider crops, int min, int max) {
        return LootPool.builder()
                .name(name)
                .rolls(ConstantRange.of(1))
                .acceptCondition(BlockStateProperty.builder(block).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withIntProp(CropsBlock.AGE, age)))
                .addEntry(ItemLootEntry.builder(crops).acceptFunction(SetCount.builder(RandomValueRange.of(min, max))));
    }

    protected LootPool.Builder droppingSeedsAndFruitsWithChanceByAge(String name, Block block, int age, IItemProvider seeds, IItemProvider fruits) {
        return LootPool.builder()
                .name(name)
                .rolls(ConstantRange.of(1))
                .acceptCondition(BlockStateProperty.builder(block).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withIntProp(CropsBlock.AGE, age)))
                .addEntry(ItemLootEntry.builder(seeds).acceptFunction(SetCount.builder(RandomValueRange.of(0, 3))))
                .addEntry(ItemLootEntry.builder(fruits).acceptFunction(SetCount.builder(RandomValueRange.of(1, 3))));
    }
}
