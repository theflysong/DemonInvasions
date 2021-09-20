package theflysong.demon_invasions.data.provider.client.lang;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public abstract class ProviderLangBase extends LanguageProvider {
    private final String modid;

    public ProviderLangBase(DataGenerator gen, String modid, String locale) {
        super(gen, modid, locale);
        this.modid = modid;
    }

    protected void addAdvamcement(String advancement, String title, String description) {
        add("advancement." + modid + "." + advancement + ".title", title);
        add("advancement." + modid + "." + advancement + ".description", description);
    }
}
