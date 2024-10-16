package net.sigmastr.arcana.item.custom.scroll;

import net.sigmastr.arcana.item.custom.MagicSpellScrollItem;

public class CastWrathScrollItem extends MagicSpellScrollItem {

    // Casts a lightning bolt at the target location
    private static final String SPELL = "cast_lightning";
    private static final String TYPE = "magic";
    private static final int COOLDOWN = 100;
    private static final int COST = -20;

    public CastWrathScrollItem(Settings settings) {
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
