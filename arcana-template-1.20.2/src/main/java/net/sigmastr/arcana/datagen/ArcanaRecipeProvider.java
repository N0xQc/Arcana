package net.sigmastr.arcana.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.sigmastr.arcana.block.ArcanaBlocks;
import net.sigmastr.arcana.item.ArcanaItems;

public class ArcanaRecipeProvider extends FabricRecipeProvider {
    public ArcanaRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        // Shapeless recipes
        // INGREDIENTS
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ArcanaItems.MAGIC_DUST, 2)
                .input(Items.LAPIS_LAZULI, 1)
                .input(Items.GOLD_NUGGET, 1)
                .criterion(hasItem(Items.LAPIS_LAZULI), conditionsFromItem(Items.LAPIS_LAZULI))
                .criterion(hasItem(Items.GOLD_NUGGET), conditionsFromItem(Items.GOLD_NUGGET))
                        .offerTo(exporter, "magic_dust");

        // SPELLS
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ArcanaItems.MAGIC_SPELL_SCROLL, 3)
                .input(Items.STICK, 3)
                .input(Items.PAPER, 3)
                .input(ArcanaItems.MAGIC_DUST, 1)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(Items.PAPER), conditionsFromItem(Items.PAPER))
                .criterion(hasItem(ArcanaItems.MAGIC_DUST), conditionsFromItem(ArcanaItems.MAGIC_DUST))
                        .offerTo(exporter, "magic_spell_scroll");

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ArcanaItems.NETHER_SPELL_SCROLL, 3)
                .input(Items.STICK, 3)
                .input(Items.PAPER, 3)
                .input(Items.BLAZE_POWDER, 1)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(Items.PAPER), conditionsFromItem(Items.PAPER))
                .criterion(hasItem(Items.BLAZE_POWDER), conditionsFromItem(Items.BLAZE_POWDER))
                        .offerTo(exporter, "nether_spell_scroll");

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ArcanaItems.END_SPELL_SCROLL, 3)
                .input(Items.STICK, 3)
                .input(Items.PAPER, 3)
                .input(Items.ENDER_PEARL, 1)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(Items.PAPER), conditionsFromItem(Items.PAPER))
                .criterion(hasItem(Items.ENDER_PEARL), conditionsFromItem(Items.ENDER_PEARL))
                        .offerTo(exporter, "end_spell_scroll");

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ArcanaItems.ELDER_SPELL_SCROLL, 3)
                .input(Items.STICK, 3)
                .input(Items.PAPER, 3)
                .input(Items.ECHO_SHARD, 1)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(Items.PAPER), conditionsFromItem(Items.PAPER))
                .criterion(hasItem(Items.ECHO_SHARD), conditionsFromItem(Items.ECHO_SHARD))
                        .offerTo(exporter, "elder_spell_scroll");

        // Build
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ArcanaItems.BUILD_BRIDGE_SCROLL, 1)
                .input(ArcanaItems.MAGIC_SPELL_SCROLL, 1)
                .input(ArcanaItems.BUILDER_RUNE, 1)
                .input(Items.STONE_SLAB, 2)
                .criterion(hasItem(ArcanaItems.MAGIC_SPELL_SCROLL), conditionsFromItem(ArcanaItems.MAGIC_SPELL_SCROLL))
                .criterion(hasItem(ArcanaItems.BUILDER_RUNE), conditionsFromItem(ArcanaItems.BUILDER_RUNE))
                .criterion(hasItem(Items.STONE_SLAB), conditionsFromItem(Items.STONE_SLAB))
                        .offerTo(exporter, "build_bridge_scroll");

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ArcanaItems.BUILD_NETHER_PORTAL_SCROLL, 1)
                .input(ArcanaItems.NETHER_SPELL_SCROLL, 1)
                .input(ArcanaItems.BUILDER_RUNE, 1)
                .input(Items.OBSIDIAN, 1)
                .criterion(hasItem(ArcanaItems.NETHER_SPELL_SCROLL), conditionsFromItem(ArcanaItems.NETHER_SPELL_SCROLL))
                .criterion(hasItem(ArcanaItems.BUILDER_RUNE), conditionsFromItem(ArcanaItems.BUILDER_RUNE))
                .criterion(hasItem(Items.OBSIDIAN), conditionsFromItem(Items.OBSIDIAN))
                        .offerTo(exporter, "build_nether_portal_scroll");

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ArcanaItems.BUILD_PILLAR_SCROLL, 1)
                .input(ArcanaItems.MAGIC_SPELL_SCROLL, 1)
                .input(ArcanaItems.BUILDER_RUNE, 1)
                .input(Items.STONE, 2)
                .criterion(hasItem(ArcanaItems.MAGIC_SPELL_SCROLL), conditionsFromItem(ArcanaItems.MAGIC_SPELL_SCROLL))
                .criterion(hasItem(ArcanaItems.BUILDER_RUNE), conditionsFromItem(ArcanaItems.BUILDER_RUNE))
                .criterion(hasItem(Items.STONE), conditionsFromItem(Items.STONE))
                        .offerTo(exporter, "build_pillar_scroll");

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ArcanaItems.BUILD_PLATFORM_SCROLL, 1)
                .input(ArcanaItems.MAGIC_SPELL_SCROLL, 1)
                .input(ArcanaItems.BUILDER_RUNE, 1)
                .input(Items.STONE_SLAB, 1)
                .criterion(hasItem(ArcanaItems.MAGIC_SPELL_SCROLL), conditionsFromItem(ArcanaItems.MAGIC_SPELL_SCROLL))
                .criterion(hasItem(ArcanaItems.BUILDER_RUNE), conditionsFromItem(ArcanaItems.BUILDER_RUNE))
                .criterion(hasItem(Items.STONE_SLAB), conditionsFromItem(Items.STONE_SLAB))
                        .offerTo(exporter, "build_platform_scroll");

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ArcanaItems.BUILD_STAIRS_SCROLL, 1)
                .input(ArcanaItems.MAGIC_SPELL_SCROLL, 1)
                .input(ArcanaItems.BUILDER_RUNE, 1)
                .input(Items.STONE_STAIRS, 1)
                .criterion(hasItem(ArcanaItems.MAGIC_SPELL_SCROLL), conditionsFromItem(ArcanaItems.MAGIC_SPELL_SCROLL))
                .criterion(hasItem(ArcanaItems.BUILDER_RUNE), conditionsFromItem(ArcanaItems.BUILDER_RUNE))
                .criterion(hasItem(Items.STONE_STAIRS), conditionsFromItem(Items.STONE_STAIRS))
                        .offerTo(exporter, "build_stairs_scroll");

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ArcanaItems.BUILD_WALL_SCROLL, 1)
                .input(ArcanaItems.MAGIC_SPELL_SCROLL, 1)
                .input(ArcanaItems.BUILDER_RUNE, 1)
                .input(Items.STONE, 1)
                .criterion(hasItem(ArcanaItems.MAGIC_SPELL_SCROLL), conditionsFromItem(ArcanaItems.MAGIC_SPELL_SCROLL))
                .criterion(hasItem(ArcanaItems.BUILDER_RUNE), conditionsFromItem(ArcanaItems.BUILDER_RUNE))
                .criterion(hasItem(Items.COBBLESTONE_WALL), conditionsFromItem(Items.COBBLESTONE_WALL))
                        .offerTo(exporter, "build_wall_scroll");

        // Cast
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ArcanaItems.CAST_ANNIHILATION_SCROLL, 1)
                .input(ArcanaItems.ELDER_SPELL_SCROLL, 1)
                .input(ArcanaItems.CASTER_RUNE, 1)
                .input(Items.WITHER_SKELETON_SKULL, 1)
                .criterion(hasItem(ArcanaItems.ELDER_SPELL_SCROLL), conditionsFromItem(ArcanaItems.ELDER_SPELL_SCROLL))
                .criterion(hasItem(ArcanaItems.CASTER_RUNE), conditionsFromItem(ArcanaItems.CASTER_RUNE))
                .criterion(hasItem(Items.WITHER_SKELETON_SKULL), conditionsFromItem(Items.WITHER_SKELETON_SKULL))
                        .offerTo(exporter, "cast_annihilation_scroll");

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ArcanaItems.CAST_AURA_SCROLL, 1)
                .input(ArcanaItems.MAGIC_SPELL_SCROLL, 1)
                .input(ArcanaItems.CASTER_RUNE, 1)
                .input(Items.ENCHANTED_GOLDEN_APPLE, 1)
                .criterion(hasItem(ArcanaItems.MAGIC_SPELL_SCROLL), conditionsFromItem(ArcanaItems.MAGIC_SPELL_SCROLL))
                .criterion(hasItem(ArcanaItems.CASTER_RUNE), conditionsFromItem(ArcanaItems.CASTER_RUNE))
                .criterion(hasItem(Items.ENCHANTED_GOLDEN_APPLE), conditionsFromItem(Items.ENCHANTED_GOLDEN_APPLE))
                        .offerTo(exporter, "cast_aura_scroll");

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ArcanaItems.CAST_BLOOM_SCROLL, 1)
                .input(ArcanaItems.MAGIC_SPELL_SCROLL, 1)
                .input(ArcanaItems.CASTER_RUNE, 1)
                .input(Items.BONE_MEAL, 1)
                .criterion(hasItem(ArcanaItems.MAGIC_SPELL_SCROLL), conditionsFromItem(ArcanaItems.MAGIC_SPELL_SCROLL))
                .criterion(hasItem(ArcanaItems.CASTER_RUNE), conditionsFromItem(ArcanaItems.CASTER_RUNE))
                .criterion(hasItem(Items.BONE_MEAL), conditionsFromItem(Items.BONE_MEAL))
                        .offerTo(exporter, "cast_bloom_scroll");

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ArcanaItems.CAST_CLARITY_SCROLL, 1)
                .input(ArcanaItems.MAGIC_SPELL_SCROLL, 1)
                .input(ArcanaItems.CASTER_RUNE, 1)
                .input(Items.MILK_BUCKET, 1)
                .criterion(hasItem(ArcanaItems.MAGIC_SPELL_SCROLL), conditionsFromItem(ArcanaItems.MAGIC_SPELL_SCROLL))
                .criterion(hasItem(ArcanaItems.CASTER_RUNE), conditionsFromItem(ArcanaItems.CASTER_RUNE))
                .criterion(hasItem(Items.MILK_BUCKET), conditionsFromItem(Items.MILK_BUCKET))
                        .offerTo(exporter, "cast_clarity_scroll");

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ArcanaItems.CAST_DESTRUCTION_SCROLL, 1)
                .input(ArcanaItems.NETHER_SPELL_SCROLL, 1)
                .input(ArcanaItems.CASTER_RUNE, 1)
                .input(Items.FIRE_CHARGE, 1)
                .criterion(hasItem(ArcanaItems.NETHER_SPELL_SCROLL), conditionsFromItem(ArcanaItems.NETHER_SPELL_SCROLL))
                .criterion(hasItem(ArcanaItems.CASTER_RUNE), conditionsFromItem(ArcanaItems.CASTER_RUNE))
                .criterion(hasItem(Items.FIRE_CHARGE), conditionsFromItem(Items.FIRE_CHARGE))
                        .offerTo(exporter, "cast_destruction_scroll");

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ArcanaItems.CAST_FLOOD_SCROLL, 1)
                .input(ArcanaItems.MAGIC_SPELL_SCROLL, 1)
                .input(ArcanaItems.CASTER_RUNE, 1)
                .input(Items.WATER_BUCKET, 1)
                .criterion(hasItem(ArcanaItems.MAGIC_SPELL_SCROLL), conditionsFromItem(ArcanaItems.MAGIC_SPELL_SCROLL))
                .criterion(hasItem(ArcanaItems.CASTER_RUNE), conditionsFromItem(ArcanaItems.CASTER_RUNE))
                .criterion(hasItem(Items.WATER_BUCKET), conditionsFromItem(Items.WATER_BUCKET))
                        .offerTo(exporter, "cast_flood_scroll");

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ArcanaItems.CAST_HARDENING_SCROLL, 1)
                .input(ArcanaItems.MAGIC_SPELL_SCROLL, 1)
                .input(ArcanaItems.CASTER_RUNE, 1)
                .input(Items.BLUE_ICE, 1)
                .criterion(hasItem(ArcanaItems.MAGIC_SPELL_SCROLL), conditionsFromItem(ArcanaItems.MAGIC_SPELL_SCROLL))
                .criterion(hasItem(ArcanaItems.CASTER_RUNE), conditionsFromItem(ArcanaItems.CASTER_RUNE))
                .criterion(hasItem(Items.BLUE_ICE), conditionsFromItem(Items.BLUE_ICE))
                        .offerTo(exporter, "cast_hardening_scroll");

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ArcanaItems.CAST_LOOP_SCROLL, 1)
                .input(ArcanaItems.END_SPELL_SCROLL, 1)
                .input(ArcanaItems.CASTER_RUNE, 1)
                .input(Items.CLOCK, 1)
                .criterion(hasItem(ArcanaItems.END_SPELL_SCROLL), conditionsFromItem(ArcanaItems.END_SPELL_SCROLL))
                .criterion(hasItem(ArcanaItems.CASTER_RUNE), conditionsFromItem(ArcanaItems.CASTER_RUNE))
                .criterion(hasItem(Items.CLOCK), conditionsFromItem(Items.CLOCK))
                        .offerTo(exporter, "cast_loop_scroll");

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ArcanaItems.CAST_METEOROLOGY_SCROLL, 1)
                .input(ArcanaItems.END_SPELL_SCROLL, 1)
                .input(ArcanaItems.CASTER_RUNE, 1)
                .input(Items.COMPASS, 1)
                .criterion(hasItem(ArcanaItems.END_SPELL_SCROLL), conditionsFromItem(ArcanaItems.END_SPELL_SCROLL))
                .criterion(hasItem(ArcanaItems.CASTER_RUNE), conditionsFromItem(ArcanaItems.CASTER_RUNE))
                .criterion(hasItem(Items.COMPASS), conditionsFromItem(Items.COMPASS))
                        .offerTo(exporter, "cast_meteorology_scroll");

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ArcanaItems.CAST_POCKET_DIMENSION_SCROLL, 1)
                .input(ArcanaItems.END_SPELL_SCROLL, 1)
                .input(ArcanaItems.CASTER_RUNE, 1)
                .input(Items.ENDER_CHEST, 1)
                .criterion(hasItem(ArcanaItems.END_SPELL_SCROLL), conditionsFromItem(ArcanaItems.END_SPELL_SCROLL))
                .criterion(hasItem(ArcanaItems.CASTER_RUNE), conditionsFromItem(ArcanaItems.CASTER_RUNE))
                .criterion(hasItem(Items.ENDER_CHEST), conditionsFromItem(Items.ENDER_CHEST))
                        .offerTo(exporter, "cast_pocket_dimension_scroll");

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ArcanaItems.CAST_VACUUM_SCROLL, 1)
                .input(ArcanaItems.END_SPELL_SCROLL, 1)
                .input(ArcanaItems.CASTER_RUNE, 1)
                .input(Items.MAGMA_BLOCK, 1)
                .criterion(hasItem(ArcanaItems.END_SPELL_SCROLL), conditionsFromItem(ArcanaItems.END_SPELL_SCROLL))
                .criterion(hasItem(ArcanaItems.CASTER_RUNE), conditionsFromItem(ArcanaItems.CASTER_RUNE))
                .criterion(hasItem(Items.MAGMA_BLOCK), conditionsFromItem(Items.MAGMA_BLOCK))
                        .offerTo(exporter, "cast_vacuum_scroll");

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ArcanaItems.CAST_WARP_SCROLL, 1)
                .input(ArcanaItems.END_SPELL_SCROLL, 1)
                .input(ArcanaItems.CASTER_RUNE, 1)
                .input(Items.ENDER_PEARL, 1)
                .criterion(hasItem(ArcanaItems.END_SPELL_SCROLL), conditionsFromItem(ArcanaItems.END_SPELL_SCROLL))
                .criterion(hasItem(ArcanaItems.CASTER_RUNE), conditionsFromItem(ArcanaItems.CASTER_RUNE))
                .criterion(hasItem(Items.ENDER_PEARL), conditionsFromItem(Items.ENDER_PEARL))
                        .offerTo(exporter, "cast_warp_scroll");

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ArcanaItems.CAST_WITHERING_SCROLL, 1)
                .input(ArcanaItems.ELDER_SPELL_SCROLL, 1)
                .input(ArcanaItems.CASTER_RUNE, 1)
                .input(Items.WITHER_ROSE, 1)
                .criterion(hasItem(ArcanaItems.ELDER_SPELL_SCROLL), conditionsFromItem(ArcanaItems.ELDER_SPELL_SCROLL))
                .criterion(hasItem(ArcanaItems.CASTER_RUNE), conditionsFromItem(ArcanaItems.CASTER_RUNE))
                .criterion(hasItem(Items.WITHER_ROSE), conditionsFromItem(Items.WITHER_ROSE))
                        .offerTo(exporter, "cast_withering_scroll");

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ArcanaItems.CAST_WRATH_SCROLL, 1)
                .input(ArcanaItems.ELDER_SPELL_SCROLL, 1)
                .input(ArcanaItems.CASTER_RUNE, 1)
                .input(Items.LIGHTNING_ROD, 1)
                .criterion(hasItem(ArcanaItems.ELDER_SPELL_SCROLL), conditionsFromItem(ArcanaItems.ELDER_SPELL_SCROLL))
                .criterion(hasItem(ArcanaItems.CASTER_RUNE), conditionsFromItem(ArcanaItems.CASTER_RUNE))
                .criterion(hasItem(Items.LIGHTNING_ROD), conditionsFromItem(Items.LIGHTNING_ROD))
                        .offerTo(exporter, "cast_wrath_scroll");

        // Invoke
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ArcanaItems.INVOKE_BLAZE_SCROLL, 1)
                .input(ArcanaItems.NETHER_SPELL_SCROLL, 1)
                .input(ArcanaItems.INVOKER_RUNE, 1)
                .input(Items.BLAZE_POWDER, 1)
                .criterion(hasItem(ArcanaItems.NETHER_SPELL_SCROLL), conditionsFromItem(ArcanaItems.NETHER_SPELL_SCROLL))
                .criterion(hasItem(ArcanaItems.INVOKER_RUNE), conditionsFromItem(ArcanaItems.INVOKER_RUNE))
                .criterion(hasItem(Items.BLAZE_POWDER), conditionsFromItem(Items.BLAZE_POWDER))
                        .offerTo(exporter, "invoke_blaze_scroll");

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ArcanaItems.INVOKE_GHAST_SCROLL, 1)
                .input(ArcanaItems.NETHER_SPELL_SCROLL, 1)
                .input(ArcanaItems.INVOKER_RUNE, 1)
                .input(Items.GHAST_TEAR, 1)
                .criterion(hasItem(ArcanaItems.NETHER_SPELL_SCROLL), conditionsFromItem(ArcanaItems.NETHER_SPELL_SCROLL))
                .criterion(hasItem(ArcanaItems.INVOKER_RUNE), conditionsFromItem(ArcanaItems.INVOKER_RUNE))
                .criterion(hasItem(Items.GHAST_TEAR), conditionsFromItem(Items.GHAST_TEAR))
                        .offerTo(exporter, "invoke_ghast_scroll");

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ArcanaItems.INVOKE_IRON_GOLEM_SCROLL, 1)
                .input(ArcanaItems.MAGIC_SPELL_SCROLL, 1)
                .input(ArcanaItems.INVOKER_RUNE, 1)
                .input(Items.IRON_BLOCK, 1)
                .criterion(hasItem(ArcanaItems.MAGIC_SPELL_SCROLL), conditionsFromItem(ArcanaItems.MAGIC_SPELL_SCROLL))
                .criterion(hasItem(ArcanaItems.INVOKER_RUNE), conditionsFromItem(ArcanaItems.INVOKER_RUNE))
                .criterion(hasItem(Items.IRON_BLOCK), conditionsFromItem(Items.IRON_BLOCK))
                        .offerTo(exporter, "invoke_iron_golem_scroll");

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ArcanaItems.INVOKE_SNOW_GOLEM_SCROLL, 1)
                .input(ArcanaItems.MAGIC_SPELL_SCROLL, 1)
                .input(ArcanaItems.INVOKER_RUNE, 1)
                .input(Items.SNOW_BLOCK, 1)
                .criterion(hasItem(ArcanaItems.MAGIC_SPELL_SCROLL), conditionsFromItem(ArcanaItems.MAGIC_SPELL_SCROLL))
                .criterion(hasItem(ArcanaItems.INVOKER_RUNE), conditionsFromItem(ArcanaItems.INVOKER_RUNE))
                .criterion(hasItem(Items.SNOW_BLOCK), conditionsFromItem(Items.SNOW_BLOCK))
                        .offerTo(exporter, "invoke_snow_golem_scroll");

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ArcanaItems.INVOKE_UNDEAD_SCROLL, 1)
                .input(ArcanaItems.NETHER_SPELL_SCROLL, 1)
                .input(ArcanaItems.INVOKER_RUNE, 1)
                .input(Items.ROTTEN_FLESH, 1)
                .criterion(hasItem(ArcanaItems.NETHER_SPELL_SCROLL), conditionsFromItem(ArcanaItems.NETHER_SPELL_SCROLL))
                .criterion(hasItem(ArcanaItems.INVOKER_RUNE), conditionsFromItem(ArcanaItems.INVOKER_RUNE))
                .criterion(hasItem(Items.ROTTEN_FLESH), conditionsFromItem(Items.ROTTEN_FLESH))
                        .offerTo(exporter, "invoke_undead_scroll");

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ArcanaItems.INVOKE_VILLAGER_SCROLL, 1)
                .input(ArcanaItems.ELDER_SPELL_SCROLL, 1)
                .input(ArcanaItems.INVOKER_RUNE, 1)
                .input(Items.TOTEM_OF_UNDYING, 1)
                .criterion(hasItem(ArcanaItems.ELDER_SPELL_SCROLL), conditionsFromItem(ArcanaItems.ELDER_SPELL_SCROLL))
                .criterion(hasItem(ArcanaItems.INVOKER_RUNE), conditionsFromItem(ArcanaItems.INVOKER_RUNE))
                .criterion(hasItem(Items.TOTEM_OF_UNDYING), conditionsFromItem(Items.TOTEM_OF_UNDYING))
                        .offerTo(exporter, "invoke_villager_scroll");

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ArcanaItems.INVOKE_WOLF_SCROLL, 1)
                .input(ArcanaItems.MAGIC_SPELL_SCROLL, 1)
                .input(ArcanaItems.INVOKER_RUNE, 1)
                .input(Items.BONE, 1)
                .criterion(hasItem(ArcanaItems.MAGIC_SPELL_SCROLL), conditionsFromItem(ArcanaItems.MAGIC_SPELL_SCROLL))
                .criterion(hasItem(ArcanaItems.INVOKER_RUNE), conditionsFromItem(ArcanaItems.INVOKER_RUNE))
                .criterion(hasItem(Items.BONE), conditionsFromItem(Items.BONE))
                        .offerTo(exporter, "invoke_wolf_scroll");

        // Shaped recipes
        // CORES
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ArcanaItems.MAGIC_WAND_CORE, 1)
                .pattern(" A ")
                .pattern("AMA")
                .pattern(" A ")
                .input('A', Items.AMETHYST_SHARD)
                .input('M', ArcanaItems.MAGIC_DUST)
                .criterion(hasItem(Items.AMETHYST_SHARD), conditionsFromItem(Items.AMETHYST_SHARD))
                .criterion(hasItem(ArcanaItems.MAGIC_DUST), conditionsFromItem(ArcanaItems.MAGIC_DUST))
                        .offerTo(exporter, "magic_wand_core");

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ArcanaItems.NETHER_WAND_CORE, 1)
                .pattern(" A ")
                .pattern("ABA")
                .pattern(" A ")
                .input('A', Items.AMETHYST_SHARD)
                .input('B', Items.BLAZE_POWDER)
                .criterion(hasItem(Items.AMETHYST_SHARD), conditionsFromItem(Items.AMETHYST_SHARD))
                .criterion(hasItem(Items.BLAZE_POWDER), conditionsFromItem(Items.BLAZE_POWDER))
                        .offerTo(exporter, "nether_wand_core");

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ArcanaItems.END_WAND_CORE, 1)
                .pattern(" A ")
                .pattern("AEA")
                .pattern(" A ")
                .input('A', Items.AMETHYST_SHARD)
                .input('E', Items.ENDER_EYE)
                .criterion(hasItem(Items.AMETHYST_SHARD), conditionsFromItem(Items.AMETHYST_SHARD))
                .criterion(hasItem(Items.ENDER_EYE), conditionsFromItem(Items.ENDER_EYE))
                        .offerTo(exporter, "end_wand_core");

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ArcanaItems.ELDER_WAND_CORE, 1)
                .pattern(" A ")
                .pattern("ASA")
                .pattern(" A ")
                .input('A', Items.AMETHYST_SHARD)
                .input('S', Items.NETHER_STAR)
                .criterion(hasItem(Items.AMETHYST_SHARD), conditionsFromItem(Items.AMETHYST_SHARD))
                .criterion(hasItem(Items.NETHER_STAR), conditionsFromItem(Items.NETHER_STAR))
                        .offerTo(exporter, "elder_wand_core");

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ArcanaItems.EMPTY_RING_OF_POWER, 1)
                .pattern(" N ")
                .pattern("NEN")
                .pattern(" N ")
                .input('N', Items.NETHERITE_INGOT)
                .input('E', Items.ECHO_SHARD)
                .criterion(hasItem(Items.NETHERITE_INGOT), conditionsFromItem(Items.NETHERITE_INGOT))
                .criterion(hasItem(Items.ECHO_SHARD), conditionsFromItem(Items.ECHO_SHARD))
                        .offerTo(exporter, "empty_ring_of_power");

        // WANDS
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ArcanaItems.MAGIC_WAND, 1)
                .pattern("  S")
                .pattern(" C ")
                .pattern("S  ")
                .input('S', Items.STICK)
                .input('C', ArcanaItems.MAGIC_WAND_CORE)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ArcanaItems.MAGIC_WAND_CORE), conditionsFromItem(ArcanaItems.MAGIC_WAND_CORE))
                        .offerTo(exporter, "magic_wand");

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ArcanaItems.NETHER_WAND, 1)
                .pattern("  S")
                .pattern(" C ")
                .pattern("S  ")
                .input('S', Items.STICK)
                .input('C', ArcanaItems.NETHER_WAND_CORE)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ArcanaItems.NETHER_WAND_CORE), conditionsFromItem(ArcanaItems.NETHER_WAND_CORE))
                        .offerTo(exporter, "nether_wand");

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ArcanaItems.END_WAND, 1)
                .pattern("  S")
                .pattern(" C ")
                .pattern("S  ")
                .input('S', Items.STICK)
                .input('C', ArcanaItems.END_WAND_CORE)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ArcanaItems.END_WAND_CORE), conditionsFromItem(ArcanaItems.END_WAND_CORE))
                        .offerTo(exporter, "end_wand");

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ArcanaItems.ELDER_WAND, 1)
                .pattern("  S")
                .pattern(" C ")
                .pattern("S  ")
                .input('S', Items.STICK)
                .input('C', ArcanaItems.ELDER_WAND_CORE)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ArcanaItems.ELDER_WAND_CORE), conditionsFromItem(ArcanaItems.ELDER_WAND_CORE))
                        .offerTo(exporter, "elder_wand");

        // RUNES
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ArcanaItems.EMPTY_BUILDER_RUNE, 1)
                .pattern("SSS")
                .pattern(" M ")
                .pattern(" S ")
                .input('S', Items.STONE)
                .input('M', ArcanaItems.MAGIC_DUST)
                .criterion(hasItem(Items.STONE), conditionsFromItem(Items.STONE))
                .criterion(hasItem(ArcanaItems.MAGIC_DUST), conditionsFromItem(ArcanaItems.MAGIC_DUST))
                        .offerTo(exporter, "empty_builder_rune");

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ArcanaItems.EMPTY_CASTER_RUNE, 1)
                .pattern("S S")
                .pattern(" M ")
                .pattern("S S")
                .input('S', Items.STONE)
                .input('M', ArcanaItems.MAGIC_DUST)
                .criterion(hasItem(Items.STONE), conditionsFromItem(Items.STONE))
                .criterion(hasItem(ArcanaItems.MAGIC_DUST), conditionsFromItem(ArcanaItems.MAGIC_DUST))
                        .offerTo(exporter, "empty_caster_rune");

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ArcanaItems.EMPTY_INVOKER_RUNE, 1)
                .pattern(" S ")
                .pattern("SMS")
                .pattern(" S ")
                .input('S', Items.STONE)
                .input('M', ArcanaItems.MAGIC_DUST)
                .criterion(hasItem(Items.STONE), conditionsFromItem(Items.STONE))
                .criterion(hasItem(ArcanaItems.MAGIC_DUST), conditionsFromItem(ArcanaItems.MAGIC_DUST))
                        .offerTo(exporter, "empty_invoker_rune");

        // BLOCK
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ArcanaBlocks.SEEING_STONE, 1)
                .pattern("OOO")
                .pattern("OMO")
                .pattern("OOO")
                .input('O', Items.OBSIDIAN)
                .input('M', ArcanaItems.MAGIC_DUST)
                .criterion(hasItem(Items.OBSIDIAN), conditionsFromItem(Items.OBSIDIAN))
                .criterion(hasItem(ArcanaItems.MAGIC_DUST), conditionsFromItem(ArcanaItems.MAGIC_DUST))
                        .offerTo(exporter, "seeing_stone");
    }
}
