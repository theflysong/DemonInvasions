package theflysong.demon_invasions.data.provider.server.advancement;

import theflysong.demon_invasions.data.provider.IProviderManager;
import theflysong.demon_invasions.data.provider.ProviderFactory;
import theflysong.demon_invasions.data.provider.server.recipe.ProviderRecipe;

import java.util.Arrays;
import java.util.List;

public class AdvancementProviderManager implements IProviderManager {
    private AdvancementProviderManager() {}
    public static AdvancementProviderManager manager = new AdvancementProviderManager();
    @Override
    public List<ProviderFactory> getAllProviders() {
        return Arrays.asList(new ProviderFactory(ProviderAdvancement::new));
    }
}
