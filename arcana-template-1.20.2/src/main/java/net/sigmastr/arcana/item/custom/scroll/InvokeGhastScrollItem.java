package net.sigmastr.arcana.item.custom.scroll;

import net.sigmastr.arcana.item.custom.MagicSpellScrollItem;

public class InvokeGhastScrollItem extends MagicSpellScrollItem {

    // Invokes a ghast at the targeted location
    private static final String SPELL = "invoke_ghast";
    private static final String TYPE = "nether";
    private static final int COOLDOWN = 500;
    private static final int COST = -100;

    public InvokeGhastScrollItem(Settings settings) {
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
