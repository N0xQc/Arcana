package net.nox.arcana.builder;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.block.enums.BlockHalf;
import net.nox.arcana.item.ArcanaItems;

public class StairsBuilder {
    private final ServerWorld world;
    private final BlockPos startPos;
    private final String direction;
    private final Block hitBlock;
    private int currentLength = 0;
    private final int maxLength;
    private final Item wand;
    private static final int TICKS_PER_LAYER = 3; // Adjust the speed of the stairs building
    private int tickCounter = 0;
    private boolean isBuilding = true;
    private final boolean isSneaking;

    public StairsBuilder(ServerWorld world, BlockPos startPos, String direction, Block hitBlock, int maxLength, Item wand, boolean isSneaking) {
        this.world = world;
        this.startPos = startPos;
        this.direction = direction;
        this.hitBlock = hitBlock;
        this.maxLength = maxLength;
        this.wand = wand;
        this.isSneaking = isSneaking;
    }

    public void startBuilding() {
        ServerTickEvents.END_SERVER_TICK.register(this::onServerTick);
    }

    private void onServerTick(MinecraftServer server) {
        if (!isBuilding) {
            return;
        }

        if (tickCounter++ >= TICKS_PER_LAYER) {
            tickCounter = 0;
            buildLayer();
        }
    }

    private void buildLayer() {
        if (currentLength >= maxLength) {
            isBuilding = false; // Stop building
            return;
        }

        // Define relative positions around the central block position (excluding corners)
        int[] positions;
        if (wand == ArcanaItems.ELDER_WAND) {
            positions = new int[] {-2, -1, 0, 1, 2};
        } else if (wand == ArcanaItems.MAGIC_WAND) {
            positions = new int[] {-1, 0, 1};
        } else {
            positions = new int[] {0};
        }

        // Determine the vertical direction of the stairs based on sneaking state
        int verticalOffset = isSneaking ? -1 : 1;
        if (currentLength == 0 && isSneaking) {
            verticalOffset = 0;
        }
        Direction stairFacing = getStairFacing();
        Direction invertedStairFacing = stairFacing.getOpposite();

        BlockPos pos = startPos;
        switch (direction) {
            case "north":
                pos = isSneaking
                        ? pos.south(currentLength + 1).up(currentLength * verticalOffset)
                        : pos.north(currentLength - 1).up(currentLength * verticalOffset);
                for (int x : positions) {
                    placeStairs(pos.east(x), stairFacing, invertedStairFacing);
                }
                break;
            case "south":
                pos = isSneaking
                        ? pos.north(currentLength + 1).up(currentLength * verticalOffset)
                        : pos.south(currentLength - 1).up(currentLength * verticalOffset);
                for (int x : positions) {
                    placeStairs(pos.west(x), stairFacing, invertedStairFacing);
                }
                break;
            case "east":
                pos = isSneaking
                        ? pos.west(currentLength + 1).up(currentLength * verticalOffset + 1)
                        : pos.east(currentLength - 1).up(currentLength * verticalOffset);
                for (int z : positions) {
                    placeStairs(pos.south(z), stairFacing, invertedStairFacing);
                }
                break;
            case "west":
                pos = isSneaking
                        ? pos.east(currentLength + 1).up(currentLength * verticalOffset + 1)
                        : pos.west(currentLength - 1).up(currentLength * verticalOffset);
                for (int z : positions) {
                    placeStairs(pos.north(z), stairFacing, invertedStairFacing);
                }
                break;
        }

        currentLength++;
    }

    private void placeStairs(BlockPos pos, Direction stairFacing, Direction invertedStairFacing) {
        try {
            // Check if the hitBlock is already a full block
            Block platformBlock = hitBlock;

            if (hitBlock.getDefaultState().isFullCube(world, pos) || hitBlock.getDefaultState().contains(Properties.SLAB_TYPE)) {
                // Convert slab or stair to full block equivalent
                platformBlock = NaturalBlocks.getStairsWithBlock(hitBlock);
                if (platformBlock == null) {
                    isBuilding = false; // Stop building if conversion fails
                    return;
                }
            }

            if (world.isAir(pos)) {
                // Place the stair block
                world.setBlockState(pos, platformBlock.getDefaultState()
                        .with(Properties.HORIZONTAL_FACING, isSneaking ? invertedStairFacing : stairFacing) // Adjust facing based on sneaking
                        .with(Properties.BLOCK_HALF, BlockHalf.BOTTOM));
                playBlockPlaceSound(pos);

                // Place the inverted stair block below
                BlockPos invertedPos = pos.down();
                if (world.isAir(invertedPos)) {
                    world.setBlockState(invertedPos, platformBlock.getDefaultState()
                            .with(Properties.HORIZONTAL_FACING, isSneaking ? stairFacing : invertedStairFacing) // Adjust facing based on sneaking
                            .with(Properties.BLOCK_HALF, BlockHalf.TOP));
                    playBlockPlaceSound(invertedPos);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Direction getStairFacing() {
        return switch (direction) {
            case "north" -> isSneaking ? Direction.SOUTH : Direction.NORTH;
            case "south" -> isSneaking ? Direction.NORTH : Direction.SOUTH;
            case "east" -> isSneaking ? Direction.WEST : Direction.EAST;
            case "west" -> isSneaking ? Direction.EAST : Direction.WEST;
            default -> Direction.NORTH; // Default direction if none matches
        };
    }

    private void playBlockPlaceSound(BlockPos pos) {
        Block soundblock = NaturalBlocks.isStairs(hitBlock) ? hitBlock : NaturalBlocks.getStairsWithBlock(hitBlock);
        // This method plays the block place sound for the hitBlock at the given position
        world.playSound(null, pos, soundblock.getSoundGroup(hitBlock.getDefaultState()).getPlaceSound(), SoundCategory.BLOCKS, 1.0F, 1.0F);
        // Add particle effects here if needed
        world.spawnParticles(ParticleTypes.POOF, pos.getX(), pos.getY(), pos.getZ(), 10, 0.5, 0.5, 0.5, 0.1);
    }
}
