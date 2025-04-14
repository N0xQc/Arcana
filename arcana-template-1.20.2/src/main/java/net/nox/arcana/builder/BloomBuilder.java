package net.nox.arcana.builder;

import net.minecraft.block.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

public class BloomBuilder {
    private final ServerWorld world;
    private final BlockPos centerPos;
    private final int radius;


    // Constructor for the GrowthBuilder
    public BloomBuilder(ServerWorld world, BlockPos centerPos, int radius) {
        this.world = world;
        this.centerPos = centerPos;
        this.radius = radius;
    }


    // Apply growth effect to an area
    // Apply growth effect to an area without corners (circular area)
    public void applyGrowth() {
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                // Calculate the distance from the center
                if (Math.sqrt(x * x + z * z) <= radius) {
                    BlockPos pos = centerPos.add(x, 0, z);

                    if (applyBonemealEffect(world, pos)) {
                        if (!world.isClient) {
                            world.syncWorldEvent(WorldEvents.BONE_MEAL_USED, pos, 0);
                        }
                    }
                }
            }
        }
    }


    // Plant and grow a tree at the center position
    public void plantAndGrowTree() {
        BlockPos pos = centerPos;
        BlockState state = world.getBlockState(pos);

        // Check if the target block is valid for sapling placement
        if (state.getBlock() == Blocks.DIRT || state.getBlock() == Blocks.GRASS_BLOCK || state.getBlock() == Blocks.PODZOL || state.getBlock() == Blocks.COARSE_DIRT) {
            // For single sapling placement
            BlockPos saplingPos = pos.up();
            SaplingBlock sapling = getSaplingBasedOnBiome(saplingPos);

            // Check if the sapling can be placed at the target position
            if (sapling.canPlaceAt(sapling.getDefaultState(), world, saplingPos)) {
                world.setBlockState(saplingPos, sapling.getDefaultState(), Block.NOTIFY_ALL);
                if (!world.isClient) {
                    // Force tree growth using the same logic as before
                    forceTreeGrowth(sapling, saplingPos);
                }
            }
        }
    }


    private SaplingBlock getSaplingBasedOnBiome(BlockPos pos) {
        // Get the biome at the position
        RegistryEntry<Biome> biome = world.getBiome(pos);
        SaplingBlock sapling;

        // Select sapling based on the biome type
        // Check for forest biomes
        if (biome.isIn(BiomeTags.IS_FOREST)) {
            if (biome.matchesKey(BiomeKeys.BIRCH_FOREST)) {
                sapling = (SaplingBlock) Blocks.BIRCH_SAPLING;
            } else if (biome.matchesKey(BiomeKeys.DARK_FOREST)) {
                sapling = (SaplingBlock) Blocks.DARK_OAK_SAPLING;
            } else {
                sapling = (SaplingBlock) Blocks.OAK_SAPLING;
            }
        }
        // Check for snowy/cold biomes
        else if (biome.isIn(BiomeTags.IS_TAIGA) || biome.isIn(BiomeTags.IS_MOUNTAIN) || biome.matchesKey(BiomeKeys.SNOWY_BEACH) || biome.matchesKey(BiomeKeys.SNOWY_TAIGA) || biome.matchesKey(BiomeKeys.SNOWY_PLAINS) || biome.matchesKey(BiomeKeys.SNOWY_SLOPES) || biome.matchesKey(BiomeKeys.TAIGA) || biome.matchesKey(BiomeKeys.STONY_PEAKS) || biome.matchesKey(BiomeKeys.ICE_SPIKES)) {
            sapling = (SaplingBlock) Blocks.SPRUCE_SAPLING;
        }
        // Check for jungle biome
        else if (biome.isIn(BiomeTags.IS_JUNGLE)) {
            sapling = (SaplingBlock) Blocks.JUNGLE_SAPLING;
        }
        // Check for savanna biome
        else if (biome.isIn(BiomeTags.IS_SAVANNA)) {
            sapling = (SaplingBlock) Blocks.ACACIA_SAPLING;
        }
        // Check for desert biomes
        else if (biome.matchesKey(BiomeKeys.DESERT) || biome.matchesKey(BiomeKeys.BADLANDS)) {
            sapling = (SaplingBlock) Blocks.DEAD_BUSH;
        }
        // Check for cherry grove biome
        else if (biome.matchesKey(BiomeKeys.CHERRY_GROVE)) {
            sapling = (SaplingBlock) Blocks.CHERRY_SAPLING;
        }
        // Default to oak sapling
        else {
            sapling = (SaplingBlock) Blocks.OAK_SAPLING;
        }
        return sapling;
    }


    // Force the tree to grow by simulating random ticks
    private void forceTreeGrowth(SaplingBlock sapling, BlockPos saplingPos) {
        // Attempt to grow the tree in multiple random ticks
        for (int i = 0; i < 100; i++) {
            BlockState state = world.getBlockState(saplingPos);

            // Check if the block at saplingPos is still a sapling
            if (state.getBlock() instanceof SaplingBlock) {
                sapling.randomTick(state, (ServerWorld) world, saplingPos, world.random);
            } else {
                // The sapling has grown into a tree; stop the loop
                break;
            }
        }
    }


    // Mimic bonemeal behavior on a block
    public static boolean applyBonemealEffect(World world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos);
        Block block = blockState.getBlock();

        // If the targeted block is farmland or dirt, check the block above it
        if (block instanceof FarmlandBlock) {
            pos = pos.up();
            blockState = world.getBlockState(pos);
            block = blockState.getBlock();
        }

        // If the block is fertilizable (like crops, saplings, etc.)
        if (block instanceof Fertilizable fertilizable) {
            boolean canFertilize = fertilizable.isFertilizable(world, pos, blockState, world.isClient);
            if (canFertilize) {
                if (world instanceof ServerWorld) {
                    boolean canGrow = fertilizable.canGrow(world, world.random, pos, blockState);

                    if (canGrow) {
                        fertilizable.grow((ServerWorld) world, world.random, pos, blockState);
                        return true;
                    }
                }
            }
        }

        // Handle bonemeal-like effect on the ground (grass, flowers, etc.)
        return applyBonemealToGround(world, pos);
    }


    // Similar to useOnGround in BoneMealItem: applying bonemeal to the ground (like grass)
    public static boolean applyBonemealToGround(World world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos);

        // Check if the block is water for underwater plants
        if (blockState.isOf(Blocks.WATER) && world.getFluidState(pos).getLevel() == 8) {
            growUnderwaterPlants(world, pos);
            return true;
        }

        // Growing flowers or tall grass on grass/dirt
        if (blockState.isIn(BlockTags.DIRT) || blockState.isOf(Blocks.GRASS_BLOCK)) {
            if (!world.isClient) {
                for (int i = 0; i < 128; ++i) {
                    BlockPos plantPos = pos.add(world.random.nextInt(3) - 1, world.random.nextInt(2) - world.random.nextInt(2), world.random.nextInt(3) - 1);
                    BlockState plantState = Blocks.GRASS.getDefaultState();

                    // Check if the position is valid for the plant to grow
                    if (world.getBlockState(plantPos).isAir() && plantState.canPlaceAt(world, plantPos)) {
                        world.setBlockState(plantPos, plantState, Block.NOTIFY_ALL);
                    }
                }
            }
            return true;
        }
        return false;
    }


    // Helper method for growing underwater plants (like seagrass or coral)
    public static void growUnderwaterPlants(World world, BlockPos pos) {
        Random random = world.getRandom();
        for (int i = 0; i < 128; ++i) {
            BlockPos plantPos = pos.add(random.nextInt(3) - 1, random.nextInt(2) - random.nextInt(2), random.nextInt(3) - 1);
            BlockState plantState = Blocks.SEAGRASS.getDefaultState();

            if (world.getBlockState(plantPos).isOf(Blocks.WATER) && world.getFluidState(plantPos).getLevel() == 8) {
                world.setBlockState(plantPos, plantState, Block.NOTIFY_ALL);
            }
        }
    }
}
