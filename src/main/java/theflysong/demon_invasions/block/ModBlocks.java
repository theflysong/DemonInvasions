package theflysong.demon_invasions.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import theflysong.demon_invasions.DemonInvasions;

public class ModBlocks {
    public static DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, DemonInvasions.MODID);

    public static RegistryObject<Block> LIFE_CRYSTAL = REGISTER.register("life_crystal", LifeCrystalBlock::new);

    public ModBlocks(IEventBus bus) {
        REGISTER.register(bus);
    }
}
