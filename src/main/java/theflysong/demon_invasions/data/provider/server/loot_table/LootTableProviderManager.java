package theflysong.demon_invasions.data.provider.server.loot_table;

import theflysong.demon_invasions.data.provider.IProviderManager;
import theflysong.demon_invasions.data.provider.ProviderFactory;
import theflysong.demon_invasions.data.provider.server.recipe.ProviderRecipe;

import java.util.Arrays;
import java.util.List;

public class LootTableProviderManager implements IProviderManager {
    private LootTableProviderManager() {}
    public static LootTableProviderManager manager = new LootTableProviderManager();
    @Override
    public List<ProviderFactory> getAllProviders() {
        return Arrays.asList(
                new ProviderFactory(ProviderBlockLootTable::new),
                new ProviderFactory(ProviderEntityLootTable::new),
                new ProviderFactory(ProviderChestLootTable::new));
    }
}
