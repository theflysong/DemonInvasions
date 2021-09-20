package theflysong.demon_invasions.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import theflysong.demon_invasions.DemonInvasions;
import theflysong.demon_invasions.capability.AdditiveHealthCapabilityProvider;

@Mod.EventBusSubscriber(modid = DemonInvasions.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class OnAttachCapability {
    @SubscribeEvent
    public static void onAttachCapabilityEvent(AttachCapabilitiesEvent<Entity> event) {
        Entity entity = event.getObject();
        if (entity instanceof PlayerEntity) {
            event.addCapability(new ResourceLocation(DemonInvasions.MODID, "additive_health"), new AdditiveHealthCapabilityProvider());
        }
    }
}
