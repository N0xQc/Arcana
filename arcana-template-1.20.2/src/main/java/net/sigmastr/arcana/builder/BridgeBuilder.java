package net.sigmastr.arcana.builder;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.sigmastr.arcana.item.ArcanaItems;

public class BridgeBuilder {
    private final ServerWorld world;
    private final BlockPos startPos;
    private final String direction;
    private final Block hitBlock;
    private int currentLength = 0;
    private final int maxLength;
    private final Item wand;
    private static final int TICKS_PER_LAYER = 3; // Adjust the speed of the bridge building
    private int tickCounter = 0;
    private boolean isBuilding = true;
    private final boolean isSneaking;

    public BridgeBuilder(ServerWorld world, BlockPos startPos, String direction, Block hitBlock, int maxLength, Item wand, boolean isSneaking) {
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
        if (wand == ArcanaItems.ELDER_WAND){
            positions = new int[] {-2, -1, 0, 1, 2};
        } else if (wand == ArcanaItems.MAGIC_WAND) {
            positions = new int[] {-1, 0, 1};
        } else {
            positions = new int[] {0};
        }

        BlockPos pos = startPos;
        switch (direction) {
            case "north":
                pos = isSneaking ? pos.south(currentLength) : pos.north(currentLength);
                for (int x : positions) {
                    BlockPos blockPos = pos.east(x);
                    if (world.isAir(blockPos)) {
                        world.setBlockState(blockPos, hitBlock.getDefaultState());
                        playBlockPlaceSound(blockPos);
                    }
                }
                break;
            case "south":
                pos = isSneaking ? pos.north(currentLength) : pos.south(currentLength);
                for (int x : positions) {
                    BlockPos blockPos = pos.west(x);
                    if (world.isAir(blockPos)) {
                        world.setBlockState(blockPos, hitBlock.getDefaultState());
                        playBlockPlaceSound(blockPos);
                    }
                }
                break;
            case "east":
                pos = isSneaking ? pos.west(currentLength) : pos.east(currentLength);
                for (int z : positions) {
                    BlockPos blockPos = pos.south(z);
                    if (world.isAir(blockPos)) {
                        world.setBlockState(blockPos, hitBlock.getDefaultState());
                        playBlockPlaceSound(blockPos);
                    }
                }
                break;
            case "west":
                pos = isSneaking ? pos.east(currentLength) : pos.west(currentLength);
                for (int z : positions) {
                    BlockPos blockPos = pos.north(z);
                    if (world.isAir(blockPos)) {
                        world.setBlockState(blockPos, hitBlock.getDefaultState());
                        playBlockPlaceSound(blockPos);
                    }
                }
                break;
            default:
                return;
        }

        currentLength++;
    }

    private void playBlockPlaceSound(BlockPos pos) {
        // This method plays the block place sound for the hitBlock at the given position
        world.playSound(null, pos, hitBlock.getSoundGroup(hitBlock.getDefaultState()).getPlaceSound(), SoundCategory.BLOCKS, 1.0F, 1.0F);
    }
}
