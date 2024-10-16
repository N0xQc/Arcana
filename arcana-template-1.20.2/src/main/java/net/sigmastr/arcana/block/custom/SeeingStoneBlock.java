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
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.sigmastr.arcana.item.ArcanaItems;
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
            if (player.isHolding(ArcanaItems.EMPTY_BUILDER_RUNE) && player.totalExperience >= 5) {
                player.addExperience(-5);
                player.getInventory().removeOne(new ItemStack(ArcanaItems.EMPTY_BUILDER_RUNE));
                this.spawnBreakParticles(world, player, pos, state);
                ItemStack drop = new ItemStack(ArcanaItems.BUILDER_RUNE, 1);
                Block.dropStack(world, pos, drop);
            }
            if (player.isHolding(ArcanaItems.EMPTY_CASTER_RUNE) && player.totalExperience >= 5) {
                player.addExperience(-5);
                player.getInventory().removeOne(new ItemStack(ArcanaItems.EMPTY_CASTER_RUNE));
                this.spawnBreakParticles(world, player, pos, state);
                ItemStack drop = new ItemStack(ArcanaItems.CASTER_RUNE, 1);
                Block.dropStack(world, pos, drop);
            }
            if (player.isHolding(ArcanaItems.EMPTY_INVOKER_RUNE) && player.totalExperience >= 5) {
                player.addExperience(-5);
                player.getInventory().removeOne(new ItemStack(ArcanaItems.EMPTY_INVOKER_RUNE));
                this.spawnBreakParticles(world, player, pos, state);
                ItemStack drop = new ItemStack(ArcanaItems.INVOKER_RUNE, 1);
                Block.dropStack(world, pos, drop);
            }
            if ((player.isHolding(ArcanaItems.EMPTY_RING_OF_POWER) && player.getOffHandStack().isOf(Items.TOTEM_OF_UNDYING) ||
                    (player.isHolding(Items.TOTEM_OF_UNDYING) && player.getOffHandStack().isOf(ArcanaItems.EMPTY_RING_OF_POWER))) && player.totalExperience >= 100) {

                // Remove 100 experience
                player.addExperience(-100);

                // Remove the empty ring of power from the player's inventory
                player.getInventory().removeOne(new ItemStack(ArcanaItems.EMPTY_RING_OF_POWER));

                // Spawn break particles
                this.spawnBreakParticles(world, player, pos, state);

                // Kill the player to trigger the totem of undying effect
                player.damage(world.getDamageSources().generic(), Float.MAX_VALUE);

                // Drop the "Ring of Power"
                ItemStack drop = new ItemStack(ArcanaItems.RING_OF_POWER, 1);
                Block.dropStack(world, pos, drop);
            }

            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.NEUTRAL, 0.5f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));
        }
        return ActionResult.SUCCESS;
    }
}
