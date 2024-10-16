package net.sigmastr.arcana.item.custom.scroll;

import net.sigmastr.arcana.item.custom.MagicSpellScrollItem;

public class CastPocketDimensionScrollItem extends MagicSpellScrollItem {
    // Opens the ender chest GUI
    private static final String SPELL = "cast_pocket_dimension";
    private static final String TYPE = "end";
    private static final int COOLDOWN = 150;
    private static final int COST = -30;

    public CastPocketDimensionScrollItem(Settings settings) {
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
