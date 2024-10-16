package net.sigmastr.arcana.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.sigmastr.arcana.Arcana;
import net.sigmastr.arcana.block.custom.SeeingStoneBlock;

public class ArcanaBlocks {
    public static final Block SEEING_STONE = registerBlock("seeing_stone",
            new SeeingStoneBlock(FabricBlockSettings.copyOf(Blocks.ENCHANTING_TABLE)));

    private static void addItemsToFunctionalTabItemGroup(FabricItemGroupEntries entries) {
        entries.add(SEEING_STONE);
    }
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Arcana.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(Arcana.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        Arcana.LOGGER.info("Registering Mod Blocks for " + Arcana.MOD_ID);

        // add to functional blocks tab
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(ArcanaBlocks::addItemsToFunctionalTabItemGroup);
    }
}
