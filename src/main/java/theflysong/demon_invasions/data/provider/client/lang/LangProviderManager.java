package theflysong.demon_invasions.data.provider.client.lang;

import theflysong.demon_invasions.data.provider.IProviderManager;
import theflysong.demon_invasions.data.provider.ProviderFactory;

import java.util.Arrays;
import java.util.List;

public class LangProviderManager implements IProviderManager {
    private LangProviderManager() {}
    public static LangProviderManager manager = new LangProviderManager();
    @Override
    public List<ProviderFactory> getAllProviders() {
        return Arrays.asList(new ProviderFactory(ProviderLangENUS::new), new ProviderFactory(ProviderLangZHCN::new));
    }
}
