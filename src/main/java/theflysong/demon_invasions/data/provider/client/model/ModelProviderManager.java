package theflysong.demon_invasions.data.provider.client.model;

import theflysong.demon_invasions.data.provider.IProviderManager;
import theflysong.demon_invasions.data.provider.ProviderFactory;

import java.util.Arrays;
import java.util.List;

public class ModelProviderManager implements IProviderManager {
    private ModelProviderManager() {}
    public static ModelProviderManager manager = new ModelProviderManager();
    @Override
    public List<ProviderFactory> getAllProviders() {
        return Arrays.asList(new ProviderFactory(ProviderItemModel::new), new ProviderFactory(ProviderBlockState::new));
    }
}
