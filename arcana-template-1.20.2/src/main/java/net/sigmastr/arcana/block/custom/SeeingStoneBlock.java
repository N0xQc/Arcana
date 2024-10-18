package net.sigmastr.arcana.block.custom;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.sigmastr.arcana.item.ArcanaItems;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SeeingStoneBlock extends Block{
    private static final VoxelShape SHAPE = Block.createCuboidShape(0, 0, 0, 16, 20, 16);

    public SeeingStoneBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            // Check for EMPTY_BUILDER_RUNE
            if (player.isHolding(ArcanaItems.EMPTY_BUILDER_RUNE) && player.totalExperience >= 5) {
                player.addExperience(-5);
                removeItemFromHand(player, ArcanaItems.EMPTY_BUILDER_RUNE);
                this.spawnBreakParticles(world, player, pos, state);
                dropChargedItem(world, pos, ArcanaItems.BUILDER_RUNE);
            }

            // Check for EMPTY_CASTER_RUNE
            if (player.isHolding(ArcanaItems.EMPTY_CASTER_RUNE) && player.totalExperience >= 5) {
                player.addExperience(-5);
                removeItemFromHand(player, ArcanaItems.EMPTY_CASTER_RUNE);
                this.spawnBreakParticles(world, player, pos, state);
                dropChargedItem(world, pos, ArcanaItems.CASTER_RUNE);
            }

            // Check for EMPTY_INVOKER_RUNE
            if (player.isHolding(ArcanaItems.EMPTY_INVOKER_RUNE) && player.totalExperience >= 5) {
                player.addExperience(-5);
                removeItemFromHand(player, ArcanaItems.EMPTY_INVOKER_RUNE);
                this.spawnBreakParticles(world, player, pos, state);
                dropChargedItem(world, pos, ArcanaItems.INVOKER_RUNE);
            }

            // Special case for EMPTY_RING_OF_POWER and TOTEM_OF_UNDYING
            if ((player.isHolding(ArcanaItems.EMPTY_RING_OF_POWER) && player.getOffHandStack().isOf(Items.TOTEM_OF_UNDYING) ||
                    player.isHolding(Items.TOTEM_OF_UNDYING) && player.getOffHandStack().isOf(ArcanaItems.EMPTY_RING_OF_POWER)) && player.totalExperience >= 100) {
                player.addExperience(-100);
                removeItemFromHand(player, ArcanaItems.EMPTY_RING_OF_POWER);
                this.spawnBreakParticles(world, player, pos, state);
                player.damage(world.getDamageSources().generic(), Float.MAX_VALUE);
                dropChargedItem(world, pos, ArcanaItems.RING_OF_POWER);
            }

            Item[] wands = {ArcanaItems.MAGIC_WAND, ArcanaItems.NETHER_WAND, ArcanaItems.END_WAND, ArcanaItems.ELDER_WAND};
            for (Item wand : wands) {
                if (player.isHolding(wand)) {
                    if (player.getOffHandStack().isOf(wand)) {
                        String wandName = getString(player, wands);
                        if (!player.getOffHandStack().hasCustomName()) {
                            player.getOffHandStack().setCustomName(Text.literal(wandName).formatted(Formatting.AQUA));
                        } else {
                            Text customName = player.getOffHandStack().getName();
                            player.getOffHandStack().setCustomName(Text.literal(customName.getString()).formatted(Formatting.AQUA));
                        }
                    } else if (player.getMainHandStack().isOf(wand)) {
                        String wandName = getString(player, wands);
                        if (!player.getMainHandStack().hasCustomName()) {
                            player.getMainHandStack().setCustomName(Text.literal(wandName).formatted(Formatting.AQUA));
                        } else {
                            Text customName = player.getMainHandStack().getName();
                            player.getMainHandStack().setCustomName(Text.literal(customName.getString()).formatted(Formatting.AQUA));
                        }
                    }
                }
            }

            if (player.isHolding(ArcanaItems.RING_OF_POWER)) {
                if (player.getOffHandStack().isOf(ArcanaItems.RING_OF_POWER)) {
                    if (!player.getOffHandStack().hasCustomName()) {
                        player.getOffHandStack().setCustomName(Text.literal("Ring of Power").formatted(Formatting.GOLD));
                    } else {
                        Text customName = player.getOffHandStack().getName();
                        player.getOffHandStack().setCustomName(Text.literal(customName.getString()).formatted(Formatting.GOLD));
                    }
                } else if (player.getMainHandStack().isOf(ArcanaItems.RING_OF_POWER)) {
                    if (!player.getMainHandStack().hasCustomName()) {
                        player.getMainHandStack().setCustomName(Text.literal("Ring of Power").formatted(Formatting.GOLD));
                    } else {
                        Text customName = player.getMainHandStack().getName();
                        player.getMainHandStack().setCustomName(Text.literal(customName.getString()).formatted(Formatting.GOLD));
                    }
                }
            }

            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.NEUTRAL, 0.5f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));
        }
        return ActionResult.SUCCESS;
    }

    @NotNull
    private static String getString(PlayerEntity player, Item[] wands) {
        String wandName;
        if (player.getOffHandStack().isOf(wands[0])){
            wandName = "Magic Wand";
        } else if (player.getOffHandStack().isOf(wands[1])){
            wandName = "Nether Wand";
        } else if (player.getOffHandStack().isOf(wands[2])){
            wandName = "End Wand";
        } else {
            wandName = "Elder Wand";
        }
        return wandName;
    }

    private void removeItemFromHand(PlayerEntity player, Item item) {
        // Remove the item from either hand if it's there
        if (player.getMainHandStack().isOf(item)) {
            player.getMainHandStack().decrement(1);  // Decrement count or remove the item entirely
        } else if (player.getOffHandStack().isOf(item)) {
            player.getOffHandStack().decrement(1);
        }
    }

    private void dropChargedItem(World world, BlockPos pos, Item chargedItem) {
        ItemStack drop = new ItemStack(chargedItem, 1);
        Block.dropStack(world, pos, drop);
    }

}
