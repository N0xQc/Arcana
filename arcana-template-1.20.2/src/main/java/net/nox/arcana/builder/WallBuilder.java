package net.nox.arcana.builder;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.Block;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;

public class WallBuilder {
    private final ServerWorld world;
    private final BlockPos startPos;
    private final String direction;
    private final Block hitBlock;
    private int currentHeight = 0;
    private final int max_height;
    private static final int TICKS_PER_LAYER = 3; // Adjust the speed of the wall rising
    private int tickCounter = 0;
    private boolean isBuilding = true;

    public WallBuilder(ServerWorld world, BlockPos startPos, String direction, Block hitBlock, int max_height) {
        this.world = world;
        this.startPos = startPos;
        this.direction = direction;
        this.hitBlock = hitBlock;
        this.max_height = max_height;
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

        for (int i = 0; i < ((max_height*2) + 1); ++i) {
            BlockPos pos = startPos.up(currentHeight);
            switch (direction) {
                case "north":
                case "south":
                        pos = pos.east(i - max_height);
                    break;
                case "east":
                case "west":
                        pos = pos.north(i - max_height);
                    break;
                default:
                    continue;
            }
            if (world.isAir(pos)) {
                world.setBlockState(pos, platformBlock.getDefaultState());
                playBlockPlaceSound(pos);
            }
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
