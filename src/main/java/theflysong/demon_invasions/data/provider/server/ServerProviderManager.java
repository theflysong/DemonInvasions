package theflysong.demon_invasions.data.provider.server;

import theflysong.demon_invasions.data.provider.IProviderManager;
import theflysong.demon_invasions.data.provider.ProviderFactory;
import theflysong.demon_invasions.data.provider.server.advancement.AdvancementProviderManager;
import theflysong.demon_invasions.data.provider.server.loot_table.LootTableProviderManager;
import theflysong.demon_invasions.data.provider.server.recipe.RecipeProviderManager;

import java.util.ArrayList;
import java.util.List;

public class ServerProviderManager implements IProviderManager {
    private ServerProviderManager() {}
    public static ServerProviderManager manager = new ServerProviderManager();
    @Override
    public List<ProviderFactory> getAllProviders() {
        List<ProviderFactory> providers = new ArrayList<>();
        providers.addAll(RecipeProviderManager.manager.getAllProviders());
        providers.addAll(LootTableProviderManager.manager.getAllProviders());
        providers.addAll(AdvancementProviderManager.manager.getAllProviders());
        return providers;
    }
}
