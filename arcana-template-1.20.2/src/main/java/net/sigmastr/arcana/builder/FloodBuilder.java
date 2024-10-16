package net.sigmastr.arcana.builder;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;

public class FloodBuilder {
    private final ServerWorld world;
    private final BlockPos startPos;
    private int currentRadius = 0;
    private final int maxRadius;
    private static final int TICKS_PER_LAYER = 2; // Adjust the speed of the platform expansion
    private int tickCounter = 0;
    private boolean isBuilding = true;

    public FloodBuilder(ServerWorld world, BlockPos startPos, int maxRadius) {
        this.world = world;
        this.startPos = startPos;
        this.maxRadius = maxRadius;
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
        if (currentRadius > maxRadius) {
            isBuilding = false; // Stop building
            return;
        }

        // Define relative positions around the central block position
        int[] positions = new int[currentRadius * 2 + 1];
        for (int i = -currentRadius; i <= currentRadius; i++) {
            positions[i + currentRadius] = i;
        }

        for (int x : positions) {
            for (int z : positions) {
                // Exclude corners
                if (Math.abs(x) == currentRadius && Math.abs(z) == currentRadius) {
                    continue; // Skip the corners
                }

                BlockPos pos = startPos.add(x, 0, z);
                if (world.isAir(pos)) {
                    world.setBlockState(pos, Blocks.WATER.getDefaultState());
                    playBlockPlaceSound(pos);
                }
            }
        }

        currentRadius++;
    }

    private void playBlockPlaceSound(BlockPos pos) {
        // This method plays the block place sound for the hitBlock at the given position
        world.playSound(null, pos, Blocks.WATER.getSoundGroup(Blocks.WATER.getDefaultState()).getPlaceSound(), SoundCategory.BLOCKS, 1.0F, 1.0F);
    }
}
