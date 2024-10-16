package net.sigmastr.arcana.builder;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class NetherPortalBuilder {
    private final ServerWorld world;
    private final BlockPos startPos;
    private final String direction;
    private int currentHeight = 0;
    private static final int TICKS_PER_LAYER = 3; // Adjust the speed of the wall rising
    private int tickCounter = 0;
    private boolean isBuilding = true;
    private boolean isLit = false;

    public NetherPortalBuilder(ServerWorld world, BlockPos startPos, String direction, Boolean isLit) {
        this.world = world;
        this.startPos = startPos;
        this.direction = direction;
        this.isLit = isLit;
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
        Block block = Blocks.OBSIDIAN;
        if (currentHeight >= 5) {
            isBuilding = false; // Stop building
            if (isLit) {
                lightPortal(); // Light the portal
            }
            return;
        }

        for (int i = 0; i < 4; ++i) {
            BlockPos pos = startPos.up(currentHeight);
            if (currentHeight > 0 && currentHeight < 4 && i > 0 && i < 3) {
                block = Blocks.AIR;
            } else block = Blocks.OBSIDIAN;
            switch (direction) {
                case "north":
                case "south":
                    pos = pos.west(2 - i);
                    break;
                case "east":
                case "west":
                    pos = pos.north(2 - i);
                    break;
                default:
                    continue;
            }
            world.setBlockState(pos, block.getDefaultState());
            playBlockPlaceSound(pos, block);
        }

        currentHeight++;
    }

    private void lightPortal() {
        for (int y = 1; y <= 3; y++) {
            for (int x = 1; x <= 2; x++) {
                BlockPos pos = startPos.up(y);
                switch (direction) {
                    case "north":
                    case "south":
                        pos = pos.west(2 - x);
                        break;
                    case "east":
                    case "west":
                        pos = pos.north(2 - x);
                        break;
                }
                if (world.getBlockState(pos).isAir()) {
                    world.setBlockState(pos, Blocks.FIRE.getDefaultState());
                    playBlockPlaceSound(pos, Blocks.FIRE);
                }
            }
        }
    }

    private void playBlockPlaceSound(BlockPos pos, Block block) {
        // This method plays the block place sound for the hitBlock at the given position
        world.playSound(null, pos, block.getSoundGroup(block.getDefaultState()).getPlaceSound(), SoundCategory.BLOCKS, 1.0F, 1.0F);
    }
}
