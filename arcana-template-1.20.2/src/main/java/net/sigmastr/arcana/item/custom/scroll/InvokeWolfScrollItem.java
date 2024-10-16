package net.sigmastr.arcana.item.custom.scroll;

import net.sigmastr.arcana.item.custom.MagicSpellScrollItem;

public class InvokeWolfScrollItem extends MagicSpellScrollItem {
    // Invokes a wolf at the targeted location
    private static final String SPELL = "invoke_wolf";
    private static final String TYPE = "magic";
    private static final int COOLDOWN = 150;
    private static final int COST = -40;

    public InvokeWolfScrollItem(Settings settings) {
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
