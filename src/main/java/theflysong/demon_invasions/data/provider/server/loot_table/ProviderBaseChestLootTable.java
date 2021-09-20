package theflysong.demon_invasions.data.provider.server.loot_table;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.entity.EntityType;
import net.minecraft.loot.*;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.system.CallbackI;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public abstract class ProviderBaseChestLootTable implements IDataProvider {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    private final Map<ResourceLocation, LootTable.Builder> lootTables = new HashMap<>();
    private DataGenerator generator = null;

    public ProviderBaseChestLootTable(DataGenerator generatorIn) {
        generator = generatorIn;
    }

    protected abstract void registerTables();

    public Map<ResourceLocation, LootTable.Builder> getLootTables() {
        return lootTables;
    }

    @Override
    public void act(DirectoryCache cache) {
        registerTables();

        Map<ResourceLocation, LootTable> tables = new HashMap<>();
        for (Map.Entry<ResourceLocation, LootTable.Builder> entry : lootTables.entrySet()) {
            tables.put(new ResourceLocation(entry.getKey().getNamespace(), "chests/" + entry.getKey().getPath()), entry.getValue().setParameterSet(LootParameterSets.CHEST).build());
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
        return "Chest Loot Tables";
    }

    protected LootTable.Builder tableWithPools(LootPool.Builder... pools) {
        LootTable.Builder builder = LootTable.builder();
        for (LootPool.Builder pool : pools) {
            builder.addLootPool(pool);
        }
        return builder;
    }
}
