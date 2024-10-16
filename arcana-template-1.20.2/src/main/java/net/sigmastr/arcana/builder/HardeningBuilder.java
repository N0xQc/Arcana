package net.sigmastr.arcana.builder;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.Blocks;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;

public class HardeningBuilder {
    private final ServerWorld world;
    private final BlockPos startPos;
    private int currentRadius = 0;
    private final int maxRadius;
    private static final int TICKS_PER_LAYER = 2; // Adjust the speed of the hardening effect
    private int tickCounter = 0;
    private boolean isBuilding = true;

    public HardeningBuilder(ServerWorld world, BlockPos startPos, int maxRadius) {
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
            hardenLayer();
        }
    }

    private void hardenLayer() {
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

                // Find the topmost fluid block in the column
                BlockPos topPos = findTopFluidBlock(pos);

                if (topPos != null) {
                    // Harden water to blue ice
                    if (world.getFluidState(topPos).isIn(FluidTags.WATER)) {
                        world.setBlockState(topPos, Blocks.BLUE_ICE.getDefaultState());
                        playBlockPlaceSound(topPos);
                    }
                    // Harden lava to obsidian
                    else if (world.getFluidState(topPos).isIn(FluidTags.LAVA)) {
                        world.setBlockState(topPos, Blocks.OBSIDIAN.getDefaultState());
                        playBlockPlaceSound(topPos);
                    }
                }
            }
        }

        currentRadius++;
    }

    private BlockPos findTopFluidBlock(BlockPos basePos) {
        // Scan upwards from the base position until we find the topmost water or lava block
        BlockPos currentPos = basePos;
        while (world.getFluidState(currentPos.up()).isIn(FluidTags.WATER) || world.getFluidState(currentPos.up()).isIn(FluidTags.LAVA)) {
            currentPos = currentPos.up();
        }

        // Check if the current position is water or lava
        if (world.getFluidState(currentPos).isIn(FluidTags.WATER) || world.getFluidState(currentPos).isIn(FluidTags.LAVA)) {
            return currentPos;
        }

        return null;
    }

    private void playBlockPlaceSound(BlockPos pos) {
        // Play the block place sound when liquid is hardened
        world.playSound(null, pos, Blocks.OBSIDIAN.getSoundGroup(Blocks.OBSIDIAN.getDefaultState()).getPlaceSound(), SoundCategory.BLOCKS, 1.0F, 1.0F);
    }
}
