package net.sigmastr.arcana.item.custom.scroll;

import net.sigmastr.arcana.item.custom.MagicSpellScrollItem;

public class CastHardeningScrollItem extends MagicSpellScrollItem {

    // Casts a spell that solidifies the water or lava around the targeted location
    private static final String SPELL = "cast_hardening";
    private static final String TYPE = "magic";
    private static final int COOLDOWN = 100;
    private static final int COST = -10;

    public CastHardeningScrollItem(Settings settings) {
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
