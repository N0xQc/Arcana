package net.nox.arcana.item.custom;

import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillageGossipType;
import net.minecraft.village.VillagerGossips;

public class MagicWhipItem extends Item {

    private static final int REPUTATION_VALUE = 1000;

    public MagicWhipItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    @Override
    public Text getName(ItemStack stack) {
        return Text.literal("Magic Whip").formatted(Formatting.AQUA);
    }

    public static void registerInteractionHandler() {
        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (!world.isClient && hand == Hand.MAIN_HAND) {
                if (entity instanceof VillagerEntity villager && player.getMainHandStack().getItem() instanceof MagicWhipItem && player.totalExperience >= 50) {

                    // Play sound and damage the villager slightly
                    villager.damage(world.getDamageSources().generic(), 0.5f);
                    world.playSound(null, villager.getBlockPos(), SoundEvents.BLOCK_CHAIN_BREAK, SoundCategory.NEUTRAL, 1.0f, 1.0f);

                    // Max out reputation
                    VillagerGossips gossips = villager.getGossip();
                    gossips.startGossip(player.getUuid(), VillageGossipType.MAJOR_POSITIVE, REPUTATION_VALUE);

                    // Make sure the trade is not maxed out due to overuse
                    for (TradeOffer tradeOffer : villager.getOffers()) {
                        if (tradeOffer.getUses() > 0) {
                            tradeOffer.resetUses();
                        }
                    }

                    // Removes experience from the player
                    player.addExperience(-50);
                    player.getItemCooldownManager().set(player.getMainHandStack().getItem(), 50);

                    return ActionResult.SUCCESS; // Avoids opening the trade screen
                }
            }
            return ActionResult.PASS;
        });
    }
}
