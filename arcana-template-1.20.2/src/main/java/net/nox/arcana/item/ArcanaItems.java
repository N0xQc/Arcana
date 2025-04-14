package net.nox.arcana.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.nox.arcana.Arcana;
import net.nox.arcana.fluid.ArcanaFluids;
import net.nox.arcana.item.custom.MagicSpellScrollItem;
import net.nox.arcana.item.custom.MagicWhipItem;
import net.nox.arcana.item.custom.RingOfPowerItem;
import net.nox.arcana.item.custom.RuneItem;
import net.nox.arcana.item.custom.scroll.*;
import net.nox.arcana.item.custom.wand.MagicWandItem;
import net.nox.arcana.item.custom.wand.ElderWandItem;
import net.nox.arcana.item.custom.wand.EndWandItem;
import net.nox.arcana.item.custom.wand.NetherWandItem;

public class ArcanaItems {


    // CRAFTING
    public static final Item MAGIC_DUST = registerItem("magic_dust", new Item(new FabricItemSettings()));
    public static final Item BUILDER_RUNE = registerItem("builder_rune", new RuneItem(new FabricItemSettings()));
    public static final Item CASTER_RUNE = registerItem("caster_rune", new RuneItem(new FabricItemSettings()));
    public static final Item INVOKER_RUNE = registerItem("invoker_rune", new RuneItem(new FabricItemSettings()));
    public static final Item EMPTY_BUILDER_RUNE = registerItem("empty_builder_rune", new Item(new FabricItemSettings()));
    public static final Item EMPTY_CASTER_RUNE = registerItem("empty_caster_rune", new Item(new FabricItemSettings()));
    public static final Item EMPTY_INVOKER_RUNE = registerItem("empty_invoker_rune", new Item(new FabricItemSettings()));
    public static final Item MAGIC_WAND_CORE = registerItem("magic_wand_core", new RuneItem(new FabricItemSettings()));
    public static final Item NETHER_WAND_CORE = registerItem("nether_wand_core", new RuneItem(new FabricItemSettings()));
    public static final Item END_WAND_CORE = registerItem("end_wand_core", new RuneItem(new FabricItemSettings()));
    public static final Item ELDER_WAND_CORE = registerItem("elder_wand_core", new RuneItem(new FabricItemSettings()));
    public static final Item SOUL_GEM = registerItem("soul_gem", new Item(new FabricItemSettings()));
    public static final Item NETHERITE_RING = registerItem("netherite_ring", new Item(new FabricItemSettings()));
    public static final Item EMPTY_RING_OF_POWER = registerItem("empty_ring_of_power", new Item(new FabricItemSettings()));
    public static final Item BLOOD_BOTTLE = registerItem("blood_bottle", new BucketItem(ArcanaFluids.BLOOD_STILL, new FabricItemSettings().maxCount(1)));

    // TOOLS
    public static final Item MAGIC_WAND = registerItem("magic_wand", new MagicWandItem(new FabricItemSettings().maxCount(1)));
    public static final Item NETHER_WAND = registerItem("nether_wand", new NetherWandItem(new FabricItemSettings().maxCount(1)));
    public static final Item END_WAND = registerItem("end_wand", new EndWandItem(new FabricItemSettings().maxCount(1)));
    public static final Item ELDER_WAND = registerItem("elder_wand", new ElderWandItem(new FabricItemSettings().maxCount(1)));
    public static final Item MAGIC_SPELL_PROJECTILE = registerItem("magic_spell_projectile", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item RING_OF_POWER = registerItem("ring_of_power", new RingOfPowerItem(new FabricItemSettings().maxCount(1)));
    public static final Item MAGIC_WHIP = registerItem("magic_whip", new MagicWhipItem(new FabricItemSettings().maxCount(1)));

    // SPELL SCROLLS
    public static final Item MAGIC_SPELL_SCROLL = registerItem("magic_spell_scroll", new Item(new FabricItemSettings()));
    public static final Item NETHER_SPELL_SCROLL = registerItem("nether_spell_scroll", new Item(new FabricItemSettings()));
    public static final Item END_SPELL_SCROLL = registerItem("end_spell_scroll", new Item(new FabricItemSettings()));
    public static final Item ELDER_SPELL_SCROLL = registerItem("elder_spell_scroll", new Item(new FabricItemSettings()));
    public static final MagicSpellScrollItem BUILD_BRIDGE_SCROLL = (MagicSpellScrollItem) registerItem("build_bridge_scroll", new BuildBridgeScrollItem(new FabricItemSettings().maxCount(1)));
    public static final MagicSpellScrollItem BUILD_NETHER_PORTAL_SCROLL = (MagicSpellScrollItem) registerItem("build_nether_portal_scroll", new BuildNetherPortalScrollItem(new FabricItemSettings().maxCount(1)));
    public static final MagicSpellScrollItem BUILD_PILLAR_SCROLL = (MagicSpellScrollItem) registerItem("build_pillar_scroll", new BuildPillarScrollItem(new FabricItemSettings().maxCount(1)));
    public static final MagicSpellScrollItem BUILD_PLATFORM_SCROLL = (MagicSpellScrollItem) registerItem("build_platform_scroll", new BuildPlatformScrollItem(new FabricItemSettings().maxCount(1)));
    public static final MagicSpellScrollItem BUILD_STAIRS_SCROLL = (MagicSpellScrollItem) registerItem("build_stairs_scroll", new BuildStairsScrollItem(new FabricItemSettings().maxCount(1)));
    public static final MagicSpellScrollItem BUILD_WALL_SCROLL = (MagicSpellScrollItem) registerItem("build_wall_scroll", new BuildWallScrollItem(new FabricItemSettings().maxCount(1)));
    public static final MagicSpellScrollItem CAST_ANNIHILATION_SCROLL = (MagicSpellScrollItem) registerItem("cast_annihilation_scroll", new CastAnnihilationScrollItem(new FabricItemSettings().maxCount(1)));
    public static final MagicSpellScrollItem CAST_AURA_SCROLL = (MagicSpellScrollItem) registerItem("cast_aura_scroll", new CastAuraScrollItem(new FabricItemSettings().maxCount(1)));
    public static final MagicSpellScrollItem CAST_BLOOM_SCROLL = (MagicSpellScrollItem) registerItem("cast_bloom_scroll", new CastBloomScrollItem(new FabricItemSettings().maxCount(1)));
    public static final MagicSpellScrollItem CAST_CLARITY_SCROLL = (MagicSpellScrollItem) registerItem("cast_clarity_scroll", new CastClarityScrollItem(new FabricItemSettings().maxCount(1)));
    public static final MagicSpellScrollItem CAST_DESTRUCTION_SCROLL = (MagicSpellScrollItem) registerItem("cast_destruction_scroll", new CastDestructionScrollItem(new FabricItemSettings().maxCount(1)));
    public static final MagicSpellScrollItem CAST_FLOOD_SCROLL = (MagicSpellScrollItem) registerItem("cast_flood_scroll", new CastFloodScrollItem(new FabricItemSettings().maxCount(1)));
    public static final MagicSpellScrollItem CAST_HARDENING_SCROLL = (MagicSpellScrollItem) registerItem("cast_hardening_scroll", new CastHardeningScrollItem(new FabricItemSettings().maxCount(1)));
    public static final MagicSpellScrollItem CAST_LOOP_SCROLL = (MagicSpellScrollItem) registerItem("cast_loop_scroll", new CastLoopScrollItem(new FabricItemSettings().maxCount(1)));
    public static final MagicSpellScrollItem CAST_METEOROLOGY_SCROLL = (MagicSpellScrollItem) registerItem("cast_meteorology_scroll", new CastMeteorologyScrollItem(new FabricItemSettings().maxCount(1)));
    public static final MagicSpellScrollItem CAST_POCKET_DIMENSION_SCROLL = (MagicSpellScrollItem) registerItem("cast_pocket_dimension_scroll", new CastPocketDimensionScrollItem(new FabricItemSettings().maxCount(1)));
    public static final MagicSpellScrollItem CAST_PROPULSION_SCROLL = (MagicSpellScrollItem) registerItem("cast_propulsion_scroll", new CastPropulsionScrollItem(new FabricItemSettings().maxCount(1)));
    public static final MagicSpellScrollItem CAST_VACUUM_SCROLL = (MagicSpellScrollItem) registerItem("cast_vacuum_scroll", new CastVacuumScrollItem(new FabricItemSettings().maxCount(1)));
    public static final MagicSpellScrollItem CAST_WARP_SCROLL = (MagicSpellScrollItem) registerItem("cast_warp_scroll", new CastWarpScrollItem(new FabricItemSettings().maxCount(1)));
    public static final MagicSpellScrollItem CAST_WITHERING_SCROLL = (MagicSpellScrollItem) registerItem("cast_withering_scroll", new CastWitheringScrollItem(new FabricItemSettings().maxCount(1)));
    public static final MagicSpellScrollItem CAST_WRATH_SCROLL = (MagicSpellScrollItem) registerItem("cast_wrath_scroll", new CastWrathScrollItem(new FabricItemSettings().maxCount(1)));
    public static final MagicSpellScrollItem INVOKE_BLAZE_SCROLL = (MagicSpellScrollItem) registerItem("invoke_blaze_scroll", new InvokeBlazeScrollItem(new FabricItemSettings().maxCount(1)));
    public static final MagicSpellScrollItem INVOKE_GHAST_SCROLL = (MagicSpellScrollItem) registerItem("invoke_ghast_scroll", new InvokeGhastScrollItem(new FabricItemSettings().maxCount(1)));
    public static final MagicSpellScrollItem INVOKE_IRON_GOLEM_SCROLL = (MagicSpellScrollItem) registerItem("invoke_iron_golem_scroll", new InvokeIronGolemScrollItem(new FabricItemSettings().maxCount(1)));
    public static final MagicSpellScrollItem INVOKE_SNOW_GOLEM_SCROLL = (MagicSpellScrollItem) registerItem("invoke_snow_golem_scroll", new InvokeSnowGolemScrollItem(new FabricItemSettings().maxCount(1)));
    public static final MagicSpellScrollItem INVOKE_UNDEAD_SCROLL = (MagicSpellScrollItem) registerItem("invoke_undead_scroll", new InvokeUndeadScrollItem(new FabricItemSettings().maxCount(1)));
    public static final MagicSpellScrollItem INVOKE_VILLAGER_SCROLL = (MagicSpellScrollItem) registerItem("invoke_villager_scroll", new InvokeVillagerScrollItem(new FabricItemSettings().maxCount(1)));
    public static final MagicSpellScrollItem INVOKE_WOLF_SCROLL = (MagicSpellScrollItem) registerItem("invoke_wolf_scroll", new InvokeWolfScrollItem(new FabricItemSettings().maxCount(1)));

    private static void addItemsToIngredientsTabItemGroup(FabricItemGroupEntries entries) {
        // CRAFTING
        entries.add(MAGIC_DUST);
        entries.add(BUILDER_RUNE);
        entries.add(CASTER_RUNE);
        entries.add(INVOKER_RUNE);
        entries.add(EMPTY_BUILDER_RUNE);
        entries.add(EMPTY_CASTER_RUNE);
        entries.add(EMPTY_INVOKER_RUNE);
        entries.add(MAGIC_WAND_CORE);
        entries.add(NETHER_WAND_CORE);
        entries.add(END_WAND_CORE);
        entries.add(ELDER_WAND_CORE);
        entries.add(SOUL_GEM);
        entries.add(NETHERITE_RING);
        entries.add(EMPTY_RING_OF_POWER);
        entries.add(BLOOD_BOTTLE);

        // SPELL SCROLLS
        entries.add(MAGIC_SPELL_SCROLL);
        entries.add(NETHER_SPELL_SCROLL);
        entries.add(END_SPELL_SCROLL);
        entries.add(ELDER_SPELL_SCROLL);
        // MAGIC
        entries.add(BUILD_BRIDGE_SCROLL);
        entries.add(BUILD_PILLAR_SCROLL);
        entries.add(BUILD_PLATFORM_SCROLL);
        entries.add(BUILD_STAIRS_SCROLL);
        entries.add(BUILD_WALL_SCROLL);
        entries.add(CAST_AURA_SCROLL);
        entries.add(CAST_BLOOM_SCROLL);
        entries.add(CAST_CLARITY_SCROLL);
        entries.add(CAST_HARDENING_SCROLL);
        entries.add(CAST_FLOOD_SCROLL);
        entries.add(CAST_WRATH_SCROLL);
        entries.add(INVOKE_IRON_GOLEM_SCROLL);
        entries.add(INVOKE_SNOW_GOLEM_SCROLL);
        entries.add(INVOKE_WOLF_SCROLL);
        // NETHER
        entries.add(BUILD_NETHER_PORTAL_SCROLL);
        entries.add(CAST_DESTRUCTION_SCROLL);
        entries.add(INVOKE_BLAZE_SCROLL);
        entries.add(INVOKE_GHAST_SCROLL);
        entries.add(INVOKE_UNDEAD_SCROLL);
        // END
        entries.add(CAST_LOOP_SCROLL);
        entries.add(CAST_METEOROLOGY_SCROLL);
        entries.add(CAST_POCKET_DIMENSION_SCROLL);
        entries.add(CAST_PROPULSION_SCROLL);
        entries.add(CAST_VACUUM_SCROLL);
        entries.add(CAST_WARP_SCROLL);
        // ELDER
        entries.add(CAST_ANNIHILATION_SCROLL);
        entries.add(CAST_WITHERING_SCROLL);
        entries.add(INVOKE_VILLAGER_SCROLL);
    }
    private static void addItemsToToolsTabItemGroup(FabricItemGroupEntries entries) {
        entries.add(MAGIC_WAND);
        entries.add(NETHER_WAND);
        entries.add(END_WAND);
        entries.add(ELDER_WAND);
        entries.add(RING_OF_POWER);
        entries.add(MAGIC_WHIP);
    }
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Arcana.MOD_ID, name), item);
    }


    public static void registerArcanaItems() {
        Arcana.LOGGER.info("Registering Mod Items for " + Arcana.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ArcanaItems::addItemsToIngredientsTabItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(ArcanaItems::addItemsToToolsTabItemGroup);
    }
}
