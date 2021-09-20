package theflysong.demon_invasions;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import theflysong.demon_invasions.block.ModBlockItems;
import theflysong.demon_invasions.block.ModBlocks;
import theflysong.demon_invasions.capability.RegisterCapabilities;
import theflysong.demon_invasions.client.ClientSetuper;
import theflysong.demon_invasions.entity.ModEntities;
import theflysong.demon_invasions.item.ModItems;

@Mod(DemonInvasions.MODID)
public class DemonInvasions {
    public static final String MODID = "demon_invasions";

    public static final String NAME = "Demon Invasions";
    public static final String MC_VERSION = "1.16.5";
    public static final String MOD_VERSION = "1.1.0";
    public static final String VERSION = MC_VERSION + "-" + MOD_VERSION;

    private static DemonInvasions INSTANCE = null;

    private final Logger logger = LogManager.getLogger(DemonInvasions.NAME);

    public DemonInvasions() {
        INSTANCE = this;

        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);
        bus.addListener(this::setupClient);

        new ModItems(bus);
        new ModEntities(bus);
        new ModBlocks(bus);
        new ModBlockItems(bus);
    }

    public static DemonInvasions getInstance() {
        return INSTANCE;
    }

    private void setup(final FMLCommonSetupEvent event) {
        logger.info("Hello Minecraft!");
        RegisterCapabilities.registerCapabilities(event);
    }

    private void setupClient(final FMLClientSetupEvent event) {
        ClientSetuper.onClientSetup();
    }

    public static Logger getLogger() {
        return INSTANCE.logger;
    }
}
