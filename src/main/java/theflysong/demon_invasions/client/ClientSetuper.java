package theflysong.demon_invasions.client;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import theflysong.demon_invasions.block.ModBlocks;
import theflysong.demon_invasions.client.entity.RendererCthulhulEye;
import theflysong.demon_invasions.client.entity.RendererDemonEye;
import theflysong.demon_invasions.entity.ModEntities;

public class ClientSetuper {
    public static void onClientSetup() {
        bindEntityRenderer();
        changeRenderLayer();
    }

    public static void bindEntityRenderer() {
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.DEMON_EYE.get(), RendererDemonEye::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.CTHULHUL_EYE.get(), RendererCthulhulEye::new);
    }

    public static void changeRenderLayer() {
        RenderTypeLookup.setRenderLayer(ModBlocks.LIFE_CRYSTAL.get(), RenderType.getCutout());
    }
}
