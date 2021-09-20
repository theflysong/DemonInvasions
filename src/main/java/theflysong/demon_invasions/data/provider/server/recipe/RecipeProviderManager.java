package theflysong.demon_invasions.data.provider.server.recipe;

import theflysong.demon_invasions.data.provider.IProviderManager;
import theflysong.demon_invasions.data.provider.ProviderFactory;

import java.util.Arrays;
import java.util.List;

public class RecipeProviderManager implements IProviderManager {
    private RecipeProviderManager() {}
    public static RecipeProviderManager manager = new RecipeProviderManager();
    @Override
    public List<ProviderFactory> getAllProviders() {
        return Arrays.asList(new ProviderFactory(ProviderRecipe::new));
    }
}
