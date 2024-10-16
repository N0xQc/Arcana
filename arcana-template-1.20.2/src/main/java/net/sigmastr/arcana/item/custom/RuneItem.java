package net.sigmastr.arcana.item.custom;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RuneItem extends Item {
    public RuneItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}
