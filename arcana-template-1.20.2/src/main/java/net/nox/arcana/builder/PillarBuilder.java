package net.nox.arcana.builder;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.nox.arcana.item.ArcanaItems;

public class PillarBuilder {
    private final ServerWorld world;
    private final BlockPos startPos;
    private final String direction;
    private final Block hitBlock;
    private int currentHeight = 0;
    private final int max_height;
    private final Item wand;
    private static final int TICKS_PER_LAYER = 2; // Adjust the speed of the wall rising
    private int tickCounter = 0;
    private boolean isBuilding = true;

    public PillarBuilder(ServerWorld world, BlockPos startPos, String direction, Block hitBlock, int max_height, Item wand) {
        this.world = world;
        this.startPos = startPos;
        this.direction = direction;
        this.hitBlock = hitBlock;
        this.max_height = max_height;
        this.wand = wand;
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
        if (currentHeight >= max_height) {
            isBuilding = false; // Stop building
            return;
        }

        // Check if the hitBlock is already a full block
        Block platformBlock = hitBlock;

        if (!hitBlock.getDefaultState().isFullCube(world, startPos)) {
            // Convert slab or stair to full block equivalent
            platformBlock = NaturalBlocks.getFullBlockEquivalent(hitBlock);
            if (platformBlock == null) {
                isBuilding = false; // Stop building if conversion fails
                return;
            }
        }

        // Define relative positions around the central block position (excluding corners)
        int[] positions;
        if (wand == ArcanaItems.ELDER_WAND) {
            positions = new int[] {-2, -1, 0, 1, 2};
        } else if (wand == ArcanaItems.MAGIC_WAND) {
            positions = new int[] {-1, 0, 1};
        } else
            positions = new int[] {0};

        // Loop through each block height and place the pillar blocks
        for (int y = 0; y < currentHeight; y++) {
            BlockPos blockPos = startPos.up(y);

            // Place the block at the target position
            for (int xOffset : positions) {
                for (int zOffset : positions) {
                    // Exclude corners
                    if (!(Math.abs(xOffset) == 2 && Math.abs(zOffset) == 2)) {
                        if (world.isAir(blockPos.add(xOffset, 0, zOffset))) {
                            world.setBlockState(blockPos.add(xOffset, 0, zOffset), platformBlock.getDefaultState());
                            playBlockPlaceSound(blockPos.add(xOffset, 0, zOffset));
                        }
                    }
                }
            }
        }

        if (world.isAir(startPos)) {
            world.setBlockState(startPos, platformBlock.getDefaultState());
            playBlockPlaceSound(startPos);
        }

        currentHeight++;
    }

    private void playBlockPlaceSound(BlockPos pos) {
        // This method plays the block place sound for the hitBlock at the given position
        world.playSound(null, pos, hitBlock.getSoundGroup(hitBlock.getDefaultState()).getPlaceSound(), SoundCategory.BLOCKS, 1.0F, 1.0F);
        // Add particle effects here if needed
        world.spawnParticles(ParticleTypes.POOF, pos.getX(), pos.getY(), pos.getZ(), 10, 0.5, 0.5, 0.5, 0.1);
    }
}
