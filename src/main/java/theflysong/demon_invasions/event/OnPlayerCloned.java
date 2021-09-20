package theflysong.demon_invasions.event;

import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import theflysong.demon_invasions.DemonInvasions;
import theflysong.demon_invasions.capability.AdditiveHealthData;
import theflysong.demon_invasions.capability.IAdditiveHealthData;

@Mod.EventBusSubscriber(modid = DemonInvasions.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class OnPlayerCloned {
    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        {LazyOptional<IAdditiveHealthData> oldCapa = event.getOriginal().getCapability(AdditiveHealthData.ADDITIVE_HEALTH_CAPABILITY);
        LazyOptional<IAdditiveHealthData> newCapa = event.getPlayer().getCapability(AdditiveHealthData.ADDITIVE_HEALTH_CAPABILITY);
        if (oldCapa.isPresent() && newCapa.isPresent()) {
            newCapa.ifPresent((newCap) -> oldCapa.ifPresent((oldCap) -> newCap.deserializeNBT(oldCap.serializeNBT())));
        }
        event.getPlayer().getAttribute(Attributes.MAX_HEALTH).setBaseValue(20.0D + newCapa.orElse(null).getData());
        event.getPlayer().heal((float)newCapa.orElse(null).getData());
        }
    }
}
