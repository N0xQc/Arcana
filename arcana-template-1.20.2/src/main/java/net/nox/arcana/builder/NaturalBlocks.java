package net.nox.arcana.builder;
import net.minecraft.block.*;

import java.util.Set;
import java.util.HashSet;

public class NaturalBlocks {
    public static final Set<Block> NATURAL_BLOCKS = new HashSet<>();
    static {
        // Basic natural blocks
        NATURAL_BLOCKS.add(Blocks.DIRT);
        NATURAL_BLOCKS.add(Blocks.DIRT_PATH);
        NATURAL_BLOCKS.add(Blocks.GRASS_BLOCK);
        NATURAL_BLOCKS.add(Blocks.PODZOL);
        NATURAL_BLOCKS.add(Blocks.COARSE_DIRT);
        NATURAL_BLOCKS.add(Blocks.MYCELIUM);
        NATURAL_BLOCKS.add(Blocks.MUD);
        NATURAL_BLOCKS.add(Blocks.MUD_BRICKS);
        NATURAL_BLOCKS.add(Blocks.PACKED_MUD);
        NATURAL_BLOCKS.add(Blocks.MUSHROOM_STEM);
        NATURAL_BLOCKS.add(Blocks.BROWN_MUSHROOM_BLOCK);
        NATURAL_BLOCKS.add(Blocks.RED_MUSHROOM_BLOCK);
        NATURAL_BLOCKS.add(Blocks.STONE);
        NATURAL_BLOCKS.add(Blocks.SAND);
        NATURAL_BLOCKS.add(Blocks.RED_SAND);
        NATURAL_BLOCKS.add(Blocks.GRAVEL);
        NATURAL_BLOCKS.add(Blocks.CLAY);
        NATURAL_BLOCKS.add(Blocks.SNOW_BLOCK);
        NATURAL_BLOCKS.add(Blocks.ICE);
        NATURAL_BLOCKS.add(Blocks.PACKED_ICE);
        NATURAL_BLOCKS.add(Blocks.BLUE_ICE);

        // Wood and leaves
        NATURAL_BLOCKS.add(Blocks.OAK_LOG);
        NATURAL_BLOCKS.add(Blocks.BIRCH_LOG);
        NATURAL_BLOCKS.add(Blocks.SPRUCE_LOG);
        NATURAL_BLOCKS.add(Blocks.JUNGLE_LOG);
        NATURAL_BLOCKS.add(Blocks.ACACIA_LOG);
        NATURAL_BLOCKS.add(Blocks.DARK_OAK_LOG);
        NATURAL_BLOCKS.add(Blocks.MANGROVE_LOG);
        NATURAL_BLOCKS.add(Blocks.CHERRY_LOG);
        NATURAL_BLOCKS.add(Blocks.OAK_PLANKS);
        NATURAL_BLOCKS.add(Blocks.BIRCH_PLANKS);
        NATURAL_BLOCKS.add(Blocks.SPRUCE_PLANKS);
        NATURAL_BLOCKS.add(Blocks.JUNGLE_PLANKS);
        NATURAL_BLOCKS.add(Blocks.ACACIA_PLANKS);
        NATURAL_BLOCKS.add(Blocks.DARK_OAK_PLANKS);
        NATURAL_BLOCKS.add(Blocks.MANGROVE_PLANKS);
        NATURAL_BLOCKS.add(Blocks.CHERRY_PLANKS);
        NATURAL_BLOCKS.add(Blocks.STRIPPED_OAK_LOG);
        NATURAL_BLOCKS.add(Blocks.STRIPPED_BIRCH_LOG);
        NATURAL_BLOCKS.add(Blocks.STRIPPED_SPRUCE_LOG);
        NATURAL_BLOCKS.add(Blocks.STRIPPED_JUNGLE_LOG);
        NATURAL_BLOCKS.add(Blocks.STRIPPED_ACACIA_LOG);
        NATURAL_BLOCKS.add(Blocks.STRIPPED_DARK_OAK_LOG);
        NATURAL_BLOCKS.add(Blocks.STRIPPED_MANGROVE_LOG);
        NATURAL_BLOCKS.add(Blocks.STRIPPED_CHERRY_LOG);
        NATURAL_BLOCKS.add(Blocks.OAK_WOOD);
        NATURAL_BLOCKS.add(Blocks.BIRCH_WOOD);
        NATURAL_BLOCKS.add(Blocks.SPRUCE_WOOD);
        NATURAL_BLOCKS.add(Blocks.JUNGLE_WOOD);
        NATURAL_BLOCKS.add(Blocks.ACACIA_WOOD);
        NATURAL_BLOCKS.add(Blocks.DARK_OAK_WOOD);
        NATURAL_BLOCKS.add(Blocks.MANGROVE_WOOD);
        NATURAL_BLOCKS.add(Blocks.CHERRY_WOOD);
        NATURAL_BLOCKS.add(Blocks.OAK_LEAVES);
        NATURAL_BLOCKS.add(Blocks.BIRCH_LEAVES);
        NATURAL_BLOCKS.add(Blocks.SPRUCE_LEAVES);
        NATURAL_BLOCKS.add(Blocks.JUNGLE_LEAVES);
        NATURAL_BLOCKS.add(Blocks.ACACIA_LEAVES);
        NATURAL_BLOCKS.add(Blocks.DARK_OAK_LEAVES);
        NATURAL_BLOCKS.add(Blocks.MANGROVE_LEAVES);
        NATURAL_BLOCKS.add(Blocks.CHERRY_LEAVES);
        NATURAL_BLOCKS.add(Blocks.BAMBOO_BLOCK);
        NATURAL_BLOCKS.add(Blocks.BAMBOO_MOSAIC);

        // Stone variants
        NATURAL_BLOCKS.add(Blocks.COBBLESTONE);
        NATURAL_BLOCKS.add(Blocks.MOSSY_COBBLESTONE);
        NATURAL_BLOCKS.add(Blocks.STONE_BRICKS);
        NATURAL_BLOCKS.add(Blocks.MOSSY_STONE_BRICKS);
        NATURAL_BLOCKS.add(Blocks.CRACKED_STONE_BRICKS);
        NATURAL_BLOCKS.add(Blocks.CHISELED_STONE_BRICKS);
        NATURAL_BLOCKS.add(Blocks.GRANITE);
        NATURAL_BLOCKS.add(Blocks.POLISHED_GRANITE);
        NATURAL_BLOCKS.add(Blocks.DIORITE);
        NATURAL_BLOCKS.add(Blocks.POLISHED_DIORITE);
        NATURAL_BLOCKS.add(Blocks.ANDESITE);
        NATURAL_BLOCKS.add(Blocks.POLISHED_ANDESITE);
        NATURAL_BLOCKS.add(Blocks.TUFF);
        NATURAL_BLOCKS.add(Blocks.CALCITE);
        NATURAL_BLOCKS.add(Blocks.DEEPSLATE);
        NATURAL_BLOCKS.add(Blocks.COBBLED_DEEPSLATE);
        NATURAL_BLOCKS.add(Blocks.POLISHED_DEEPSLATE);
        NATURAL_BLOCKS.add(Blocks.DEEPSLATE_BRICKS);
        NATURAL_BLOCKS.add(Blocks.CRACKED_DEEPSLATE_BRICKS);
        NATURAL_BLOCKS.add(Blocks.CHISELED_DEEPSLATE);
        NATURAL_BLOCKS.add(Blocks.DEEPSLATE_TILES);
        NATURAL_BLOCKS.add(Blocks.CRACKED_DEEPSLATE_TILES);
        NATURAL_BLOCKS.add(Blocks.DRIPSTONE_BLOCK);
        NATURAL_BLOCKS.add(Blocks.MAGMA_BLOCK);
        NATURAL_BLOCKS.add(Blocks.OBSIDIAN);
        NATURAL_BLOCKS.add(Blocks.CRYING_OBSIDIAN);
        NATURAL_BLOCKS.add(Blocks.REINFORCED_DEEPSLATE);
        NATURAL_BLOCKS.add(Blocks.SMOOTH_STONE);

        // Sandstone variants
        NATURAL_BLOCKS.add(Blocks.SANDSTONE);
        NATURAL_BLOCKS.add(Blocks.CHISELED_SANDSTONE);
        NATURAL_BLOCKS.add(Blocks.CUT_SANDSTONE);
        NATURAL_BLOCKS.add(Blocks.SMOOTH_SANDSTONE);
        NATURAL_BLOCKS.add(Blocks.RED_SANDSTONE);
        NATURAL_BLOCKS.add(Blocks.CHISELED_RED_SANDSTONE);
        NATURAL_BLOCKS.add(Blocks.CUT_RED_SANDSTONE);
        NATURAL_BLOCKS.add(Blocks.SMOOTH_RED_SANDSTONE);

        // Terracotta
        NATURAL_BLOCKS.add(Blocks.TERRACOTTA);
        NATURAL_BLOCKS.add(Blocks.WHITE_TERRACOTTA);
        NATURAL_BLOCKS.add(Blocks.ORANGE_TERRACOTTA);
        NATURAL_BLOCKS.add(Blocks.MAGENTA_TERRACOTTA);
        NATURAL_BLOCKS.add(Blocks.LIGHT_BLUE_TERRACOTTA);
        NATURAL_BLOCKS.add(Blocks.YELLOW_TERRACOTTA);
        NATURAL_BLOCKS.add(Blocks.LIME_TERRACOTTA);
        NATURAL_BLOCKS.add(Blocks.PINK_TERRACOTTA);
        NATURAL_BLOCKS.add(Blocks.GRAY_TERRACOTTA);
        NATURAL_BLOCKS.add(Blocks.LIGHT_GRAY_TERRACOTTA);
        NATURAL_BLOCKS.add(Blocks.CYAN_TERRACOTTA);
        NATURAL_BLOCKS.add(Blocks.PURPLE_TERRACOTTA);
        NATURAL_BLOCKS.add(Blocks.BLUE_TERRACOTTA);
        NATURAL_BLOCKS.add(Blocks.BROWN_TERRACOTTA);
        NATURAL_BLOCKS.add(Blocks.GREEN_TERRACOTTA);
        NATURAL_BLOCKS.add(Blocks.RED_TERRACOTTA);
        NATURAL_BLOCKS.add(Blocks.BLACK_TERRACOTTA);

        // Prismarine
        NATURAL_BLOCKS.add(Blocks.PRISMARINE);
        NATURAL_BLOCKS.add(Blocks.PRISMARINE_BRICKS);
        NATURAL_BLOCKS.add(Blocks.DARK_PRISMARINE);

        // Nether
        NATURAL_BLOCKS.add(Blocks.NETHERRACK);
        NATURAL_BLOCKS.add(Blocks.NETHER_BRICKS);
        NATURAL_BLOCKS.add(Blocks.CRACKED_NETHER_BRICKS);
        NATURAL_BLOCKS.add(Blocks.CHISELED_NETHER_BRICKS);
        NATURAL_BLOCKS.add(Blocks.NETHER_BRICK_FENCE);
        NATURAL_BLOCKS.add(Blocks.RED_NETHER_BRICKS);
        NATURAL_BLOCKS.add(Blocks.NETHER_WART_BLOCK);
        NATURAL_BLOCKS.add(Blocks.QUARTZ_BLOCK);
        NATURAL_BLOCKS.add(Blocks.CHISELED_QUARTZ_BLOCK);
        NATURAL_BLOCKS.add(Blocks.QUARTZ_PILLAR);
        NATURAL_BLOCKS.add(Blocks.SMOOTH_QUARTZ);
        NATURAL_BLOCKS.add(Blocks.QUARTZ_BRICKS);
        NATURAL_BLOCKS.add(Blocks.BASALT);
        NATURAL_BLOCKS.add(Blocks.POLISHED_BASALT);
        NATURAL_BLOCKS.add(Blocks.BLACKSTONE);
        NATURAL_BLOCKS.add(Blocks.POLISHED_BLACKSTONE);
        NATURAL_BLOCKS.add(Blocks.POLISHED_BLACKSTONE_BRICKS);
        NATURAL_BLOCKS.add(Blocks.CRIMSON_NYLIUM);
        NATURAL_BLOCKS.add(Blocks.WARPED_NYLIUM);
        NATURAL_BLOCKS.add(Blocks.CRIMSON_HYPHAE);
        NATURAL_BLOCKS.add(Blocks.WARPED_HYPHAE);
        NATURAL_BLOCKS.add(Blocks.CRIMSON_PLANKS);
        NATURAL_BLOCKS.add(Blocks.WARPED_PLANKS);
        NATURAL_BLOCKS.add(Blocks.SOUL_SAND);
        NATURAL_BLOCKS.add(Blocks.SOUL_SOIL);

        // End
        NATURAL_BLOCKS.add(Blocks.END_STONE);
        NATURAL_BLOCKS.add(Blocks.END_STONE_BRICKS);
        NATURAL_BLOCKS.add(Blocks.END_STONE_BRICK_WALL);
        NATURAL_BLOCKS.add(Blocks.END_STONE_BRICK_SLAB);
        NATURAL_BLOCKS.add(Blocks.PURPUR_BLOCK);
        NATURAL_BLOCKS.add(Blocks.PURPUR_PILLAR);

        // Other
        NATURAL_BLOCKS.add(Blocks.BONE_BLOCK);
        NATURAL_BLOCKS.add(Blocks.GLASS);
    }

    public static final Set<Block> STAIRS_BLOCKS = new HashSet<>();

    static {
        STAIRS_BLOCKS.add(Blocks.STONE_STAIRS);
        STAIRS_BLOCKS.add(Blocks.SANDSTONE_STAIRS);
        STAIRS_BLOCKS.add(Blocks.RED_SANDSTONE_STAIRS);
        STAIRS_BLOCKS.add(Blocks.PRISMARINE_STAIRS);
        STAIRS_BLOCKS.add(Blocks.PRISMARINE_BRICK_STAIRS);
        STAIRS_BLOCKS.add(Blocks.DARK_PRISMARINE_STAIRS);
        STAIRS_BLOCKS.add(Blocks.COBBLESTONE_STAIRS);
        STAIRS_BLOCKS.add(Blocks.MOSSY_COBBLESTONE_STAIRS);
        STAIRS_BLOCKS.add(Blocks.STONE_BRICK_STAIRS);
        STAIRS_BLOCKS.add(Blocks.MOSSY_STONE_BRICK_STAIRS);
        STAIRS_BLOCKS.add(Blocks.PURPUR_STAIRS);
        STAIRS_BLOCKS.add(Blocks.POLISHED_GRANITE_STAIRS);
        STAIRS_BLOCKS.add(Blocks.POLISHED_DIORITE_STAIRS);
        STAIRS_BLOCKS.add(Blocks.POLISHED_ANDESITE_STAIRS);
        STAIRS_BLOCKS.add(Blocks.END_STONE_BRICK_STAIRS);
        STAIRS_BLOCKS.add(Blocks.BLACKSTONE_STAIRS);
        STAIRS_BLOCKS.add(Blocks.POLISHED_BLACKSTONE_STAIRS);
        STAIRS_BLOCKS.add(Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS);
        STAIRS_BLOCKS.add(Blocks.CRIMSON_STAIRS);
        STAIRS_BLOCKS.add(Blocks.WARPED_STAIRS);
        STAIRS_BLOCKS.add(Blocks.OAK_STAIRS);
        STAIRS_BLOCKS.add(Blocks.BIRCH_STAIRS);
        STAIRS_BLOCKS.add(Blocks.SPRUCE_STAIRS);
        STAIRS_BLOCKS.add(Blocks.JUNGLE_STAIRS);
        STAIRS_BLOCKS.add(Blocks.ACACIA_STAIRS);
        STAIRS_BLOCKS.add(Blocks.DARK_OAK_STAIRS);
        STAIRS_BLOCKS.add(Blocks.MANGROVE_STAIRS);
        STAIRS_BLOCKS.add(Blocks.CHERRY_STAIRS);
        STAIRS_BLOCKS.add(Blocks.BAMBOO_STAIRS);
        STAIRS_BLOCKS.add(Blocks.BAMBOO_MOSAIC_STAIRS);
        STAIRS_BLOCKS.add(Blocks.MUD_BRICK_STAIRS);
    }

    public static final Set<Block> SLAB_BLOCKS = new HashSet<>();

    static {
        SLAB_BLOCKS.add(Blocks.STONE_SLAB);
        SLAB_BLOCKS.add(Blocks.SMOOTH_STONE_SLAB);
        SLAB_BLOCKS.add(Blocks.SANDSTONE_SLAB);
        SLAB_BLOCKS.add(Blocks.RED_SANDSTONE_SLAB);
        SLAB_BLOCKS.add(Blocks.PRISMARINE_SLAB);
        SLAB_BLOCKS.add(Blocks.PRISMARINE_BRICK_SLAB);
        SLAB_BLOCKS.add(Blocks.DARK_PRISMARINE_SLAB);
        SLAB_BLOCKS.add(Blocks.COBBLESTONE_SLAB);
        SLAB_BLOCKS.add(Blocks.MOSSY_COBBLESTONE_SLAB);
        SLAB_BLOCKS.add(Blocks.STONE_BRICK_SLAB);
        SLAB_BLOCKS.add(Blocks.MOSSY_STONE_BRICK_SLAB);
        SLAB_BLOCKS.add(Blocks.PURPUR_SLAB);
        SLAB_BLOCKS.add(Blocks.POLISHED_GRANITE_SLAB);
        SLAB_BLOCKS.add(Blocks.POLISHED_DIORITE_SLAB);
        SLAB_BLOCKS.add(Blocks.POLISHED_ANDESITE_SLAB);
        SLAB_BLOCKS.add(Blocks.END_STONE_BRICK_SLAB);
        SLAB_BLOCKS.add(Blocks.BLACKSTONE_SLAB);
        SLAB_BLOCKS.add(Blocks.POLISHED_BLACKSTONE_SLAB);
        SLAB_BLOCKS.add(Blocks.POLISHED_BLACKSTONE_BRICK_SLAB);
        SLAB_BLOCKS.add(Blocks.CRIMSON_SLAB);
        SLAB_BLOCKS.add(Blocks.WARPED_SLAB);
        SLAB_BLOCKS.add(Blocks.OAK_SLAB);
        SLAB_BLOCKS.add(Blocks.BIRCH_SLAB);
        SLAB_BLOCKS.add(Blocks.SPRUCE_SLAB);
        SLAB_BLOCKS.add(Blocks.JUNGLE_SLAB);
        SLAB_BLOCKS.add(Blocks.ACACIA_SLAB);
        SLAB_BLOCKS.add(Blocks.DARK_OAK_SLAB);
        SLAB_BLOCKS.add(Blocks.MANGROVE_SLAB);
        SLAB_BLOCKS.add(Blocks.CHERRY_SLAB);
        SLAB_BLOCKS.add(Blocks.BAMBOO_SLAB);
        SLAB_BLOCKS.add(Blocks.BAMBOO_MOSAIC_SLAB);
        SLAB_BLOCKS.add(Blocks.MUD_BRICK_SLAB);
    }

    public static Block getStairsWithBlock(Block hitblock) {
        if (hitblock == Blocks.STONE || hitblock == Blocks.DIRT || hitblock == Blocks.GRASS_BLOCK || hitblock == Blocks.GRAVEL || hitblock == Blocks.CLAY || hitblock == Blocks.DRIPSTONE_BLOCK || hitblock == Blocks.STONE_SLAB) {
            return Blocks.STONE_STAIRS;
        } else if (hitblock == Blocks.SANDSTONE || hitblock == Blocks.CUT_SANDSTONE || hitblock == Blocks.CHISELED_SANDSTONE || hitblock == Blocks.SMOOTH_SANDSTONE || hitblock == Blocks.SAND || hitblock == Blocks.SANDSTONE_SLAB) {
            return Blocks.SANDSTONE_STAIRS;
        } else if (hitblock == Blocks.RED_SANDSTONE || hitblock == Blocks.CUT_RED_SANDSTONE || hitblock == Blocks.CHISELED_RED_SANDSTONE || hitblock == Blocks.SMOOTH_RED_SANDSTONE || hitblock == Blocks.RED_SAND || hitblock == Blocks.RED_SANDSTONE_SLAB) {
            return Blocks.RED_SANDSTONE_STAIRS;
        } else if (hitblock == Blocks.PRISMARINE || hitblock == Blocks.PRISMARINE_SLAB) {
            return Blocks.PRISMARINE_STAIRS;
        } else if (hitblock == Blocks.PRISMARINE_BRICKS || hitblock == Blocks.PRISMARINE_BRICK_SLAB) {
            return Blocks.PRISMARINE_BRICK_STAIRS;
        } else if (hitblock == Blocks.DARK_PRISMARINE || hitblock == Blocks.DARK_PRISMARINE_SLAB) {
            return Blocks.DARK_PRISMARINE_STAIRS;
        } else if (hitblock == Blocks.COBBLESTONE || hitblock == Blocks.COBBLESTONE_SLAB) {
            return Blocks.COBBLESTONE_STAIRS;
        } else if (hitblock == Blocks.MOSSY_COBBLESTONE || hitblock == Blocks.MOSSY_COBBLESTONE_SLAB) {
            return Blocks.MOSSY_COBBLESTONE_STAIRS;
        } else if (hitblock == Blocks.STONE_BRICKS || hitblock == Blocks.STONE_BRICK_SLAB) {
            return Blocks.STONE_BRICK_STAIRS;
        } else if (hitblock == Blocks.MOSSY_STONE_BRICKS || hitblock == Blocks.MOSSY_STONE_BRICK_SLAB) {
            return Blocks.MOSSY_STONE_BRICK_STAIRS;
        } else if (hitblock == Blocks.PURPUR_BLOCK || hitblock == Blocks.PURPUR_PILLAR || hitblock == Blocks.PURPUR_SLAB) {
            return Blocks.PURPUR_STAIRS;
        } else if (hitblock == Blocks.POLISHED_GRANITE || hitblock == Blocks.POLISHED_GRANITE_SLAB) {
            return Blocks.POLISHED_GRANITE_STAIRS;
        } else if (hitblock == Blocks.POLISHED_DIORITE || hitblock == Blocks.POLISHED_DIORITE_SLAB) {
            return Blocks.POLISHED_DIORITE_STAIRS;
        } else if (hitblock == Blocks.POLISHED_ANDESITE || hitblock == Blocks.POLISHED_ANDESITE_SLAB) {
            return Blocks.POLISHED_ANDESITE_STAIRS;
        } else if (hitblock == Blocks.END_STONE_BRICKS || hitblock == Blocks.END_STONE_BRICK_SLAB) {
            return Blocks.END_STONE_BRICK_STAIRS;
        } else if (hitblock == Blocks.BLACKSTONE || hitblock == Blocks.BLACKSTONE_SLAB) {
            return Blocks.BLACKSTONE_STAIRS;
        } else if (hitblock == Blocks.POLISHED_BLACKSTONE || hitblock == Blocks.POLISHED_BLACKSTONE_SLAB) {
            return Blocks.POLISHED_BLACKSTONE_STAIRS;
        } else if (hitblock == Blocks.POLISHED_BLACKSTONE_BRICKS || hitblock == Blocks.POLISHED_BLACKSTONE_BRICK_SLAB) {
            return Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS;
        } else if (hitblock == Blocks.CRIMSON_PLANKS || hitblock == Blocks.CRIMSON_HYPHAE || hitblock == Blocks.CRIMSON_NYLIUM || hitblock == Blocks.CRIMSON_SLAB) {
            return Blocks.CRIMSON_STAIRS;
        } else if (hitblock == Blocks.WARPED_PLANKS || hitblock == Blocks.WARPED_HYPHAE || hitblock == Blocks.WARPED_NYLIUM || hitblock == Blocks.WARPED_SLAB) {
            return Blocks.WARPED_STAIRS;
        } else if (hitblock == Blocks.OAK_PLANKS || hitblock == Blocks.OAK_LOG || hitblock == Blocks.OAK_WOOD || hitblock == Blocks.OAK_LEAVES || hitblock == Blocks.STRIPPED_OAK_LOG || hitblock == Blocks.STRIPPED_OAK_WOOD || hitblock == Blocks.OAK_SLAB) {
            return Blocks.OAK_STAIRS;
        } else if (hitblock == Blocks.BIRCH_PLANKS || hitblock == Blocks.BIRCH_LOG || hitblock == Blocks.BIRCH_WOOD || hitblock == Blocks.BIRCH_LEAVES || hitblock == Blocks.STRIPPED_BIRCH_LOG || hitblock == Blocks.STRIPPED_BIRCH_WOOD || hitblock == Blocks.BIRCH_SLAB) {
            return Blocks.BIRCH_STAIRS;
        } else if (hitblock == Blocks.SPRUCE_PLANKS || hitblock == Blocks.SPRUCE_LOG || hitblock == Blocks.SPRUCE_WOOD || hitblock == Blocks.SPRUCE_LEAVES || hitblock == Blocks.STRIPPED_SPRUCE_LOG || hitblock == Blocks.STRIPPED_SPRUCE_WOOD || hitblock == Blocks.SPRUCE_SLAB) {
            return Blocks.SPRUCE_STAIRS;
        } else if (hitblock == Blocks.JUNGLE_PLANKS || hitblock == Blocks.JUNGLE_LOG || hitblock == Blocks.JUNGLE_WOOD || hitblock == Blocks.JUNGLE_LEAVES || hitblock == Blocks.STRIPPED_JUNGLE_LOG || hitblock == Blocks.STRIPPED_JUNGLE_WOOD || hitblock == Blocks.JUNGLE_SLAB) {
            return Blocks.JUNGLE_STAIRS;
        } else if (hitblock == Blocks.ACACIA_PLANKS || hitblock == Blocks.ACACIA_LOG || hitblock == Blocks.ACACIA_WOOD || hitblock == Blocks.ACACIA_LEAVES || hitblock == Blocks.STRIPPED_ACACIA_LOG || hitblock == Blocks.STRIPPED_ACACIA_WOOD || hitblock == Blocks.ACACIA_SLAB) {
            return Blocks.ACACIA_STAIRS;
        } else if (hitblock == Blocks.DARK_OAK_PLANKS || hitblock == Blocks.DARK_OAK_LOG || hitblock == Blocks.DARK_OAK_WOOD || hitblock == Blocks.DARK_OAK_LEAVES || hitblock == Blocks.STRIPPED_DARK_OAK_LOG || hitblock == Blocks.STRIPPED_DARK_OAK_WOOD || hitblock == Blocks.DARK_OAK_SLAB) {
            return Blocks.DARK_OAK_STAIRS;
        } else if (hitblock == Blocks.MANGROVE_PLANKS || hitblock == Blocks.MANGROVE_LOG || hitblock == Blocks.MANGROVE_WOOD || hitblock == Blocks.MANGROVE_LEAVES || hitblock == Blocks.STRIPPED_MANGROVE_LOG || hitblock == Blocks.STRIPPED_MANGROVE_WOOD || hitblock == Blocks.MANGROVE_SLAB) {
            return Blocks.MANGROVE_STAIRS;
        } else if (hitblock == Blocks.CHERRY_PLANKS || hitblock == Blocks.CHERRY_LOG || hitblock == Blocks.CHERRY_WOOD || hitblock == Blocks.CHERRY_LEAVES || hitblock == Blocks.STRIPPED_CHERRY_LOG || hitblock == Blocks.STRIPPED_CHERRY_WOOD || hitblock == Blocks.CHERRY_SLAB) {
            return Blocks.CHERRY_STAIRS;
        } else {
            return Blocks.AIR;
        }
    }

    public static boolean isNatural(Block block) {
        return (NATURAL_BLOCKS.contains(block) || STAIRS_BLOCKS.contains(block) || SLAB_BLOCKS.contains(block));
    }

    public static boolean isStairs(Block block) {
        return STAIRS_BLOCKS.contains(block);
    }

    public static boolean isSlab(Block block) {
        return SLAB_BLOCKS.contains(block);
    }

    public static Block getFullBlockEquivalent(Block hitblock) {
        if (hitblock == Blocks.STONE_SLAB || hitblock == Blocks.STONE_STAIRS) {
            return Blocks.STONE;
        } else if (hitblock == Blocks.SMOOTH_STONE_SLAB) {
            return Blocks.SMOOTH_STONE;
        } else if (hitblock == Blocks.SANDSTONE_SLAB || hitblock == Blocks.SANDSTONE_STAIRS) {
            return Blocks.SANDSTONE;
        } else if (hitblock == Blocks.RED_SANDSTONE_SLAB || hitblock == Blocks.RED_SANDSTONE_STAIRS) {
            return Blocks.RED_SANDSTONE;
        } else if (hitblock == Blocks.PRISMARINE_SLAB || hitblock == Blocks.PRISMARINE_STAIRS) {
            return Blocks.PRISMARINE;
        } else if (hitblock == Blocks.PRISMARINE_BRICK_SLAB || hitblock == Blocks.PRISMARINE_BRICK_STAIRS) {
            return Blocks.PRISMARINE_BRICKS;
        } else if (hitblock == Blocks.DARK_PRISMARINE_SLAB || hitblock == Blocks.DARK_PRISMARINE_STAIRS) {
            return Blocks.DARK_PRISMARINE;
        } else if (hitblock == Blocks.COBBLESTONE_SLAB || hitblock == Blocks.COBBLESTONE_STAIRS) {
            return Blocks.COBBLESTONE;
        } else if (hitblock == Blocks.MOSSY_COBBLESTONE_SLAB || hitblock == Blocks.MOSSY_COBBLESTONE_STAIRS) {
            return Blocks.MOSSY_COBBLESTONE;
        } else if (hitblock == Blocks.STONE_BRICK_SLAB || hitblock == Blocks.STONE_BRICK_STAIRS) {
            return Blocks.STONE_BRICKS;
        } else if (hitblock == Blocks.MOSSY_STONE_BRICK_SLAB || hitblock == Blocks.MOSSY_STONE_BRICK_STAIRS) {
            return Blocks.MOSSY_STONE_BRICKS;
        } else if (hitblock == Blocks.PURPUR_SLAB || hitblock == Blocks.PURPUR_STAIRS) {
            return Blocks.PURPUR_BLOCK;
        } else if (hitblock == Blocks.POLISHED_GRANITE_SLAB || hitblock == Blocks.POLISHED_GRANITE_STAIRS) {
            return Blocks.POLISHED_GRANITE;
        } else if (hitblock == Blocks.POLISHED_DIORITE_SLAB || hitblock == Blocks.POLISHED_DIORITE_STAIRS) {
            return Blocks.POLISHED_DIORITE;
        } else if (hitblock == Blocks.POLISHED_ANDESITE_SLAB || hitblock == Blocks.POLISHED_ANDESITE_STAIRS) {
            return Blocks.POLISHED_ANDESITE;
        } else if (hitblock == Blocks.END_STONE_BRICK_SLAB || hitblock == Blocks.END_STONE_BRICK_STAIRS) {
            return Blocks.END_STONE_BRICKS;
        } else if (hitblock == Blocks.BLACKSTONE_SLAB || hitblock == Blocks.BLACKSTONE_STAIRS) {
            return Blocks.BLACKSTONE;
        } else if (hitblock == Blocks.POLISHED_BLACKSTONE_SLAB || hitblock == Blocks.POLISHED_BLACKSTONE_STAIRS) {
            return Blocks.POLISHED_BLACKSTONE;
        } else if (hitblock == Blocks.POLISHED_BLACKSTONE_BRICK_SLAB || hitblock == Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS) {
            return Blocks.POLISHED_BLACKSTONE_BRICKS;
        } else if (hitblock == Blocks.CRIMSON_STAIRS || hitblock == Blocks.CRIMSON_SLAB) {
            return Blocks.CRIMSON_PLANKS;
        } else if (hitblock == Blocks.WARPED_STAIRS || hitblock == Blocks.WARPED_SLAB) {
            return Blocks.WARPED_PLANKS;
        } else if (hitblock == Blocks.OAK_STAIRS || hitblock == Blocks.OAK_SLAB) {
            return Blocks.OAK_PLANKS;
        } else if (hitblock == Blocks.BIRCH_STAIRS || hitblock == Blocks.BIRCH_SLAB) {
            return Blocks.BIRCH_PLANKS;
        } else if (hitblock == Blocks.SPRUCE_STAIRS || hitblock == Blocks.SPRUCE_SLAB) {
            return Blocks.SPRUCE_PLANKS;
        } else if (hitblock == Blocks.JUNGLE_STAIRS || hitblock == Blocks.JUNGLE_SLAB) {
            return Blocks.JUNGLE_PLANKS;
        } else if (hitblock == Blocks.ACACIA_STAIRS || hitblock == Blocks.ACACIA_SLAB) {
            return Blocks.ACACIA_PLANKS;
        } else if (hitblock == Blocks.DARK_OAK_STAIRS || hitblock == Blocks.DARK_OAK_SLAB) {
            return Blocks.DARK_OAK_PLANKS;
        } else if (hitblock == Blocks.MANGROVE_STAIRS || hitblock == Blocks.MANGROVE_SLAB) {
            return Blocks.MANGROVE_PLANKS;
        } else if (hitblock == Blocks.CHERRY_STAIRS || hitblock == Blocks.CHERRY_SLAB) {
            return Blocks.CHERRY_PLANKS;
        } else {
            return null; // Return null for unsupported blocks
        }
    }
}

