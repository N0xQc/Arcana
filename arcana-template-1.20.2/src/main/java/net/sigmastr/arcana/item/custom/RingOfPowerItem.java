package net.sigmastr.arcana.item.custom;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class RingOfPowerItem extends Item {
    public RingOfPowerItem(FabricItemSettings fabricItemSettings) {
        super(fabricItemSettings);

        // Register the tick event listener
        ServerTickEvents.START_SERVER_TICK.register(this::onTick);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    @Override
    public Text getName(ItemStack stack) {
        return Text.literal("Ring of Power").formatted(Formatting.GOLD);
    }

    // This method will be called every server tick
    private void onTick(MinecraftServer server) {
        // Iterate over all worlds on the server
        for (ServerWorld world : server.getWorlds()) {
            // Iterate over all players in the world
            for (PlayerEntity player : world.getPlayers()) {
                // Check if the player has the Ring of Power in their inventory or hand
                if (hasRingOfPower(player)) {
                    applyBeaconEffects(player);

                    // Make player invisible if the item is in either hand
                    if (isRingInHand(player)) {
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 200, 0, true, false));
                    }
                }
            }
        }
    }

    private boolean hasRingOfPower(PlayerEntity player) {
        // Check if the player has the Ring of Power in their inventory
        for (ItemStack itemStack : player.getInventory().main) {
            if (itemStack.getItem() instanceof RingOfPowerItem) {
                return true;
            }
        }
        return false;
    }

    private boolean isRingInHand(PlayerEntity player) {
        // Check if the player has the Ring of Power in either hand
        ItemStack mainHand = player.getMainHandStack();
        ItemStack offHand = player.getOffHandStack();

        return mainHand.getItem() instanceof RingOfPowerItem || offHand.getItem() instanceof RingOfPowerItem;
    }

    private void applyBeaconEffects(PlayerEntity player) {
        // Apply all beacon effects (Speed, Haste, Strength, Resistance, Regeneration, etc.)
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 200, 1, true, true));
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 200, 1, true, true));
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200, 1, true, true));
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 200, 1, true, true));
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1, true, true));
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 200, 1, true, true));
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 200, 0, true, true));
    }
}
