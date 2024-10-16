package net.sigmastr.arcana.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureMap;
import net.minecraft.util.Identifier;
import net.sigmastr.arcana.item.ArcanaItems;

public class ArcanaModelProvider extends FabricModelProvider {
    public ArcanaModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

        // CRAFTING
        // Basic
        itemModelGenerator.register(ArcanaItems.MAGIC_DUST, Models.GENERATED);
        itemModelGenerator.register(ArcanaItems.MAGIC_SPELL_PROJECTILE, Models.GENERATED);
        // Runes
        itemModelGenerator.register(ArcanaItems.EMPTY_BUILDER_RUNE, Models.GENERATED);
        itemModelGenerator.register(ArcanaItems.EMPTY_CASTER_RUNE, Models.GENERATED);
        itemModelGenerator.register(ArcanaItems.EMPTY_INVOKER_RUNE, Models.GENERATED);
        itemModelGenerator.register(ArcanaItems.BUILDER_RUNE, Models.GENERATED);
        itemModelGenerator.register(ArcanaItems.CASTER_RUNE, Models.GENERATED);
        itemModelGenerator.register(ArcanaItems.INVOKER_RUNE, Models.GENERATED);
        // Cores
        itemModelGenerator.register(ArcanaItems.MAGIC_WAND_CORE, Models.GENERATED);
        itemModelGenerator.register(ArcanaItems.NETHER_WAND_CORE, Models.GENERATED);
        itemModelGenerator.register(ArcanaItems.END_WAND_CORE, Models.GENERATED);
        itemModelGenerator.register(ArcanaItems.ELDER_WAND_CORE, Models.GENERATED);
        itemModelGenerator.register(ArcanaItems.EMPTY_RING_OF_POWER, Models.GENERATED);
        // Tools
        itemModelGenerator.register(ArcanaItems.MAGIC_WAND, Models.GENERATED);
        itemModelGenerator.register(ArcanaItems.NETHER_WAND, Models.GENERATED);
        itemModelGenerator.register(ArcanaItems.END_WAND, Models.GENERATED);
        itemModelGenerator.register(ArcanaItems.ELDER_WAND, Models.GENERATED);
        itemModelGenerator.register(ArcanaItems.RING_OF_POWER, Models.GENERATED);

        // SCROLLS
        itemModelGenerator.register(ArcanaItems.MAGIC_SPELL_SCROLL, Models.GENERATED);
        itemModelGenerator.register(ArcanaItems.NETHER_SPELL_SCROLL, Models.GENERATED);
        itemModelGenerator.register(ArcanaItems.END_SPELL_SCROLL, Models.GENERATED);
        itemModelGenerator.register(ArcanaItems.ELDER_SPELL_SCROLL, Models.GENERATED);
        // Build
        itemModelGenerator.register(ArcanaItems.BUILD_BRIDGE_SCROLL, Models.GENERATED);
        itemModelGenerator.register(ArcanaItems.BUILD_NETHER_PORTAL_SCROLL, Models.GENERATED);
        itemModelGenerator.register(ArcanaItems.BUILD_PILLAR_SCROLL, Models.GENERATED);
        itemModelGenerator.register(ArcanaItems.BUILD_PLATFORM_SCROLL, Models.GENERATED);
        itemModelGenerator.register(ArcanaItems.BUILD_STAIRS_SCROLL, Models.GENERATED);
        itemModelGenerator.register(ArcanaItems.BUILD_WALL_SCROLL, Models.GENERATED);

        // Cast
        itemModelGenerator.register(ArcanaItems.CAST_ANNIHILATION_SCROLL, Models.GENERATED);
        itemModelGenerator.register(ArcanaItems.CAST_AURA_SCROLL, Models.GENERATED);
        itemModelGenerator.register(ArcanaItems.CAST_BLOOM_SCROLL, Models.GENERATED);
        itemModelGenerator.register(ArcanaItems.CAST_CLARITY_SCROLL, Models.GENERATED);
        itemModelGenerator.register(ArcanaItems.CAST_DESTRUCTION_SCROLL, Models.GENERATED);
        itemModelGenerator.register(ArcanaItems.CAST_FLOOD_SCROLL, Models.GENERATED);
        itemModelGenerator.register(ArcanaItems.CAST_HARDENING_SCROLL, Models.GENERATED);
        itemModelGenerator.register(ArcanaItems.CAST_LOOP_SCROLL, Models.GENERATED);
        itemModelGenerator.register(ArcanaItems.CAST_METEOROLOGY_SCROLL, Models.GENERATED);
        itemModelGenerator.register(ArcanaItems.CAST_POCKET_DIMENSION_SCROLL, Models.GENERATED);
        itemModelGenerator.register(ArcanaItems.CAST_VACUUM_SCROLL, Models.GENERATED);
        itemModelGenerator.register(ArcanaItems.CAST_WARP_SCROLL, Models.GENERATED);
        itemModelGenerator.register(ArcanaItems.CAST_WITHERING_SCROLL, Models.GENERATED);
        itemModelGenerator.register(ArcanaItems.CAST_WRATH_SCROLL, Models.GENERATED);

        // Invoke
        itemModelGenerator.register(ArcanaItems.INVOKE_BLAZE_SCROLL, Models.GENERATED);
        itemModelGenerator.register(ArcanaItems.INVOKE_GHAST_SCROLL, Models.GENERATED);
        itemModelGenerator.register(ArcanaItems.INVOKE_IRON_GOLEM_SCROLL, Models.GENERATED);
        itemModelGenerator.register(ArcanaItems.INVOKE_SNOW_GOLEM_SCROLL, Models.GENERATED);
        itemModelGenerator.register(ArcanaItems.INVOKE_UNDEAD_SCROLL, Models.GENERATED);
        itemModelGenerator.register(ArcanaItems.INVOKE_VILLAGER_SCROLL, Models.GENERATED);
        itemModelGenerator.register(ArcanaItems.INVOKE_WOLF_SCROLL, Models.GENERATED);
    }
}
