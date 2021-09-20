package theflysong.demon_invasions.data.provider.client;

import theflysong.demon_invasions.data.provider.IProviderManager;
import theflysong.demon_invasions.data.provider.ProviderFactory;
import theflysong.demon_invasions.data.provider.client.lang.LangProviderManager;
import theflysong.demon_invasions.data.provider.client.model.ModelProviderManager;

import java.util.ArrayList;
import java.util.List;

public class ClientProviderManager implements IProviderManager {
    private ClientProviderManager() {}
    public static ClientProviderManager manager = new ClientProviderManager();
    @Override
    public List<ProviderFactory> getAllProviders() {
        List<ProviderFactory> providers = new ArrayList<>();
        providers.addAll(LangProviderManager.manager.getAllProviders());
        providers.addAll(ModelProviderManager.manager.getAllProviders());
        return providers;
    }
}
