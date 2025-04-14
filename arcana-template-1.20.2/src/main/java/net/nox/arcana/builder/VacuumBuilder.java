package net.nox.arcana.builder;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.Blocks;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;

public class VacuumBuilder {
    private final ServerWorld world;
    private final BlockPos startPos;
    private int currentRadius;
    private final int maxRadius;
    private static final int TICKS_PER_LAYER = 2; // Adjust the speed of the draining effect
    private int tickCounter = 0;
    private boolean isBuilding = true;

    public VacuumBuilder(ServerWorld world, BlockPos startPos, int maxRadius) {
        this.world = world;
        this.startPos = startPos;
        this.maxRadius = maxRadius;
        this.currentRadius = maxRadius; // Start from the maximum radius
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
            drainLayer();
        }
    }

    private void drainLayer() {
        if (currentRadius < 0) {
            isBuilding = false; // Stop building when reaching the center
            return;
        }

        // Iterate through all points within the current spherical radius
        for (int x = -currentRadius; x <= currentRadius; x++) {
            for (int y = -currentRadius; y <= currentRadius; y++) {
                for (int z = -currentRadius; z <= currentRadius; z++) {
                    double distance = Math.sqrt(x * x + y * y + z * z);
                    if (distance <= currentRadius && distance > currentRadius - 1) {
                        // Only process the current "layer" in the spherical radius
                        BlockPos pos = startPos.add(x, y, z);

                        // Check for water or lava and replace with air
                        if (world.getFluidState(pos).isIn(FluidTags.WATER) || world.getFluidState(pos).isIn(FluidTags.LAVA)) {
                            world.setBlockState(pos, Blocks.AIR.getDefaultState());
                            playBlockPlaceSound(pos);
                        }
                    }
                }
            }
        }

        currentRadius--; // Move inward to the next smaller spherical radius
    }

    private void playBlockPlaceSound(BlockPos pos) {
        // Play the block place sound when liquid is removed
        world.playSound(null, pos, Blocks.AIR.getSoundGroup(Blocks.BUBBLE_COLUMN.getDefaultState()).getPlaceSound(), SoundCategory.BLOCKS, 1.0F, 1.0F);
    }
}
