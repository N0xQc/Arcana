package net.nox.arcana;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.transfer.v1.fluid.CauldronFluidContent;
import net.minecraft.client.render.block.FluidRenderer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.fluid.Fluid;
import net.nox.arcana.entity.ArcanaEntities;
import net.nox.arcana.fluid.ArcanaFluids;

public class ArcanaClient implements ClientModInitializer {
    @Override
    public  void  onInitializeClient() {
        EntityRendererRegistry.register(ArcanaEntities.MAGIC_SPELL_PROJECTILE, FlyingItemEntityRenderer::new);
        FluidRenderHandlerRegistry.INSTANCE.register(ArcanaFluids.BLOOD_FLOWING,
                new SimpleFluidRenderHandler(SimpleFluidRenderHandler.WATER_STILL,
                        SimpleFluidRenderHandler.WATER_FLOWING,
                        SimpleFluidRenderHandler.WATER_OVERLAY, 0x8C0000));
        FluidRenderHandlerRegistry.INSTANCE.register(ArcanaFluids.BLOOD_STILL,
                new SimpleFluidRenderHandler(SimpleFluidRenderHandler.WATER_STILL,
                        SimpleFluidRenderHandler.WATER_FLOWING,
                        SimpleFluidRenderHandler.WATER_OVERLAY, 0x8C0000));
    }
}
