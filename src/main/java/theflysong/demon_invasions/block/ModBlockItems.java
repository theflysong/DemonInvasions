package theflysong.demon_invasions.block;

import net.minecraft.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import theflysong.demon_invasions.DemonInvasions;

public class ModBlockItems {
    public static final DeferredRegister<Item> REGISTRY =
            DeferredRegister.create(ForgeRegistries.ITEMS, DemonInvasions.MODID);

    //public static RegistryObject<BlockItem> LIFE_CRYSTAL = REGISTRY.register("life_crystal", () -> new BlockNamedItem(ModBlocks.LIFE_CRYSTAL.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));

    public ModBlockItems(IEventBus bus) {
        REGISTRY.register(bus);
    }
}
