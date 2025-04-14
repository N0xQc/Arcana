package net.nox.arcana.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.nox.arcana.Arcana;
import net.nox.arcana.entity.custom.MagicSpellProjectileEntity;

public class ArcanaEntities {
    public static final EntityType<MagicSpellProjectileEntity> MAGIC_SPELL_PROJECTILE = FabricEntityTypeBuilder
            .<MagicSpellProjectileEntity>create(SpawnGroup.MISC, MagicSpellProjectileEntity::new)
            .dimensions(EntityDimensions.fixed(0.1F, 0.1F))
            .trackRangeBlocks(64)
            .trackedUpdateRate(1)
            .build();

    public static void registerArcanaEntities() {
        Arcana.LOGGER.info("Registering Mod Entities for " + Arcana.MOD_ID);

        Registry.register(Registries.ENTITY_TYPE, new Identifier(Arcana.MOD_ID, "magic_spell_projectile"), MAGIC_SPELL_PROJECTILE);
    }
}

