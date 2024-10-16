package net.sigmastr.arcana.util;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;
import net.sigmastr.arcana.item.ArcanaItems;

public class ArcanaLootTableModifiers {

    private static final Identifier ANCIENT_CITY_ID = new Identifier("minecraft", "chests/ancient_city");
    private static final Identifier BASTION_TREASURE = new Identifier("minecraft", "chests/bastion_treasure");
    private static final Identifier BURIED_TREASURE = new Identifier("minecraft", "chests/buried_treasure");
    private static final Identifier END_CITY_TREASURE = new Identifier("minecraft", "chests/end_city_treasure");
    private static final Identifier RUINED_PORTAL = new Identifier("minecraft", "chests/ruined_portal");
    private static final Identifier SHIPWRECK_TREASURE = new Identifier("minecraft", "chests/shipwreck_treasure");
    private static final Identifier SIMPLE_DUNGEON = new Identifier("minecraft", "chests/simple_dungeon");
    private static final Identifier STRONGHOLD_LIBRARY = new Identifier("minecraft", "chests/stronghold_library");
    private static final Identifier WOODLAND_MANSION = new Identifier("minecraft", "chests/village/woodland_mansion");

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, supplier, setter) -> {
            if (ANCIENT_CITY_ID.equals(id)) {
                // Add loot table modifiers here
                LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))
                .conditionally(RandomChanceLootCondition.builder(0.5f));

                poolBuilder.with(ItemEntry.builder(ArcanaItems.MAGIC_WAND_CORE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build());

                poolBuilder.with(ItemEntry.builder(ArcanaItems.BUILDER_RUNE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build());

                poolBuilder.with(ItemEntry.builder(ArcanaItems.CASTER_RUNE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build());

                poolBuilder.with(ItemEntry.builder(ArcanaItems.INVOKER_RUNE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build());

                supplier.pool(poolBuilder.build());
            } else if (BASTION_TREASURE.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))
                .conditionally(RandomChanceLootCondition.builder(0.4f));

                poolBuilder.with(ItemEntry.builder(ArcanaItems.NETHER_WAND_CORE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build());

                poolBuilder.with(ItemEntry.builder(ArcanaItems.CAST_DESTRUCTION_SCROLL))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build());

                poolBuilder.with(ItemEntry.builder(ArcanaItems.CAST_HARDENING_SCROLL))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build());

                poolBuilder.with(ItemEntry.builder(ArcanaItems.INVOKE_BLAZE_SCROLL))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build());

                poolBuilder.with(ItemEntry.builder(ArcanaItems.INVOKE_GHAST_SCROLL))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build());

                supplier.pool(poolBuilder.build());
            } else if (BURIED_TREASURE.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))
                .conditionally(RandomChanceLootCondition.builder(0.25f));

                poolBuilder.with(ItemEntry.builder(ArcanaItems.CAST_CLARITY_SCROLL))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build());

                poolBuilder.with(ItemEntry.builder(ArcanaItems.CAST_FLOOD_SCROLL))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build());

                poolBuilder.with(ItemEntry.builder(ArcanaItems.MAGIC_DUST))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2f, 7f)).build());

                poolBuilder.with(ItemEntry.builder(ArcanaItems.EMPTY_BUILDER_RUNE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 2f)).build());

                poolBuilder.with(ItemEntry.builder(ArcanaItems.EMPTY_CASTER_RUNE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 2f)).build());

                poolBuilder.with(ItemEntry.builder(ArcanaItems.EMPTY_INVOKER_RUNE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 2f)).build());

                supplier.pool(poolBuilder.build());
            } else if (END_CITY_TREASURE.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))
                .conditionally(RandomChanceLootCondition.builder(0.25f));

                poolBuilder.with(ItemEntry.builder(ArcanaItems.END_WAND_CORE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build());

                poolBuilder.with(ItemEntry.builder(ArcanaItems.CAST_LOOP_SCROLL))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build());

                poolBuilder.with(ItemEntry.builder(ArcanaItems.CAST_METEOROLOGY_SCROLL))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build());

                poolBuilder.with(ItemEntry.builder(ArcanaItems.CAST_VACUUM_SCROLL))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build());

                poolBuilder.with(ItemEntry.builder(ArcanaItems.CAST_WARP_SCROLL))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build());

                supplier.pool(poolBuilder.build());
            } else if (RUINED_PORTAL.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))
                .conditionally(RandomChanceLootCondition.builder(0.5f));

                poolBuilder.with(ItemEntry.builder(ArcanaItems.BUILD_NETHER_PORTAL_SCROLL))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 2f)).build());

                poolBuilder.with(ItemEntry.builder(ArcanaItems.CAST_DESTRUCTION_SCROLL))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build());

                poolBuilder.with(ItemEntry.builder(ArcanaItems.CAST_HARDENING_SCROLL))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build());

                supplier.pool(poolBuilder.build());
            } else if (SHIPWRECK_TREASURE.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))
                .conditionally(RandomChanceLootCondition.builder(0.5f));

                poolBuilder.with(ItemEntry.builder(ArcanaItems.CAST_VACUUM_SCROLL))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build());

                poolBuilder.with(ItemEntry.builder(ArcanaItems.BUILDER_RUNE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build());

                poolBuilder.with(ItemEntry.builder(ArcanaItems.CASTER_RUNE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build());

                poolBuilder.with(ItemEntry.builder(ArcanaItems.INVOKER_RUNE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build());

                poolBuilder.with(ItemEntry.builder(ArcanaItems.MAGIC_DUST))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2f, 7f)).build());

                poolBuilder.with(ItemEntry.builder(ArcanaItems.MAGIC_WAND_CORE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build());

                supplier.pool(poolBuilder.build());
            } else if (SIMPLE_DUNGEON.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))
                .conditionally(RandomChanceLootCondition.builder(0.25f));

                poolBuilder.with(ItemEntry.builder(ArcanaItems.CAST_BLOOM_SCROLL))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build());

                poolBuilder.with(ItemEntry.builder(ArcanaItems.CAST_WRATH_SCROLL))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build());

                poolBuilder.with(ItemEntry.builder(ArcanaItems.INVOKE_SNOW_GOLEM_SCROLL))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build());

                poolBuilder.with(ItemEntry.builder(ArcanaItems.INVOKE_UNDEAD_SCROLL))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build());

                poolBuilder.with(ItemEntry.builder(ArcanaItems.MAGIC_DUST))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2f, 7f)).build());

                supplier.pool(poolBuilder.build());
            } else if (STRONGHOLD_LIBRARY.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))
                .conditionally(RandomChanceLootCondition.builder(0.5f));

                poolBuilder.with(ItemEntry.builder(ArcanaItems.CAST_AURA_SCROLL))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build());

                poolBuilder.with(ItemEntry.builder(ArcanaItems.CAST_POCKET_DIMENSION_SCROLL))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build());

                poolBuilder.with(ItemEntry.builder(ArcanaItems.INVOKE_IRON_GOLEM_SCROLL))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build());

                poolBuilder.with(ItemEntry.builder(ArcanaItems.INVOKE_WOLF_SCROLL))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build());

                poolBuilder.with(ItemEntry.builder(ArcanaItems.MAGIC_DUST))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2, 7f)).build());

                poolBuilder.with(ItemEntry.builder(ArcanaItems.END_WAND_CORE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build());

                poolBuilder.with(ItemEntry.builder(ArcanaItems.MAGIC_WAND_CORE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build());

                supplier.pool(poolBuilder.build());
            } else if (WOODLAND_MANSION.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))
                .conditionally(RandomChanceLootCondition.builder(0.4f));

                poolBuilder.with(ItemEntry.builder(ArcanaItems.CAST_AURA_SCROLL))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build());

                poolBuilder.with(ItemEntry.builder(ArcanaItems.CAST_CLARITY_SCROLL))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build());

                poolBuilder.with(ItemEntry.builder(ArcanaItems.INVOKE_VILLAGER_SCROLL))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build());

                poolBuilder.with(ItemEntry.builder(ArcanaItems.MAGIC_DUST))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2f, 7f)).build());

                poolBuilder.with(ItemEntry.builder(ArcanaItems.MAGIC_WAND_CORE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build());

                supplier.pool(poolBuilder.build());
            }
        });
    }
}
