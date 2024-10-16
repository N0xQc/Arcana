package net.sigmastr.arcana.item.custom;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.random.Random;

import java.util.List;

public class MagicSpellScrollItem extends Item {
    private static String SPELL;
    private static String TYPE;
    private static int COOLDOWN;
    private static int COST;

    public MagicSpellScrollItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }


    public static String getSPELL() {
        return SPELL;
    }

    public static String getTYPE() {
        return TYPE;
    }


    public static int getCOOLDOWN() {
        return COOLDOWN;
    }

    public static int getCOST() {
        return COST;
    }
}
