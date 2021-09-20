package theflysong.demon_invasions.data;

import net.minecraft.data.BlockTagsProvider;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import theflysong.demon_invasions.DemonInvasions;
import theflysong.demon_invasions.data.provider.client.ClientProviderManager;
import theflysong.demon_invasions.data.provider.server.ServerProviderManager;
import theflysong.demon_invasions.data.provider.server.tag.ProviderBlockTag;
import theflysong.demon_invasions.data.provider.server.tag.ProviderItemTag;

@Mod.EventBusSubscriber(modid = DemonInvasions.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OnDataGen {
    @SubscribeEvent
    public static void dataGen(GatherDataEvent event) {
        if (event.includeClient()) {
            ClientProviderManager.manager.registerProviders(event.getGenerator(), DemonInvasions.MODID, event.getExistingFileHelper());
        }

        if (event.includeServer()) {
            ServerProviderManager.manager.registerProviders(event.getGenerator(), DemonInvasions.MODID, event.getExistingFileHelper());
            BlockTagsProvider blockTagsProvider = new ProviderBlockTag(event.getGenerator(), DemonInvasions.MODID, event.getExistingFileHelper());
            event.getGenerator().addProvider(blockTagsProvider);
            event.getGenerator().addProvider(new ProviderItemTag(event.getGenerator(), DemonInvasions.MODID, event.getExistingFileHelper(), blockTagsProvider));
        }
    }
}
