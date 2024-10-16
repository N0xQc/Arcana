package net.sigmastr.arcana.item.custom.scroll;

import net.sigmastr.arcana.item.custom.MagicSpellScrollItem;

public class InvokeBlazeScrollItem extends MagicSpellScrollItem {

    // Invokes a blaze at the targeted location
    private static final String SPELL = "invoke_blaze";
    private static final String TYPE = "nether";
    private static final int COOLDOWN = 250;
    private static final int COST = -50;

    public InvokeBlazeScrollItem(Settings settings) {
        super(settings);
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
