package theflysong.demon_invasions.entity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import theflysong.demon_invasions.DemonInvasions;
import theflysong.demon_invasions.item.ModItems;

@Mod.EventBusSubscriber(modid = DemonInvasions.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {
    public static final EntityRegister REGISTER = new EntityRegister(ModItems.REGISTER);

    public static final RegistryObject<EntityType<EntityDemonEye>> DEMON_EYE = REGISTER.<EntityDemonEye>register("demon_eye")
            .entity(() -> EntityType.Builder.create(EntityDemonEye::new, EntityClassification.MISC).size(1.0F, 1.0F))
            .egg(0xaeaeae, 0xe6e6e6)
            .getTypeObj();

    public static final RegistryObject<EntityType<EntityCthulhulEye>> CTHULHUL_EYE = REGISTER.<EntityCthulhulEye>register("cthulhul_eye")
            .entity(() -> EntityType.Builder.create(EntityCthulhulEye::new, EntityClassification.MISC).size(3.0F, 3.0F))
            .egg(0xaeeaee, 0xe2e2f6)
            .getTypeObj();

    public ModEntities(IEventBus bus) {
        REGISTER.register(bus);
    }

    @SubscribeEvent
    public static void onCreateAttribute(EntityAttributeCreationEvent event) {
        event.put(DEMON_EYE.get(), EntityDemonEye.createAttributes().create());
        event.put(CTHULHUL_EYE.get(), EntityCthulhulEye.createAttributes().create());
    }
}
