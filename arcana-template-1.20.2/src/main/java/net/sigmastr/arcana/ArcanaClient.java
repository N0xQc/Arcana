package net.sigmastr.arcana;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.sigmastr.arcana.entity.ArcanaEntities;

import javax.swing.text.html.parser.Entity;

public class ArcanaClient implements ClientModInitializer {
    @Override
    public  void  onInitializeClient() {
        EntityRendererRegistry.register(ArcanaEntities.MAGIC_SPELL_PROJECTILE, FlyingItemEntityRenderer::new);
    }
}
