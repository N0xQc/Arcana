package net.sigmastr.arcana.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.sigmastr.arcana.Arcana;
import net.sigmastr.arcana.entity.custom.MagicSpellProjectileEntity;

public class ArcanaEntities {
    public static final EntityType<MagicSpellProjectileEntity> MAGIC_SPELL_PROJECTILE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Arcana.MOD_ID, "magic_spell_projectile"),
            FabricEntityTypeBuilder.<MagicSpellProjectileEntity>create(SpawnGroup.MISC, MagicSpellProjectileEntity::new)
                    .dimensions(EntityDimensions.fixed(0.1F, 0.1F)).build());

}
