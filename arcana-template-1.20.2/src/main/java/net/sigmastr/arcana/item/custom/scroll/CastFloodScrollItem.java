package net.sigmastr.arcana.item.custom.scroll;

import net.sigmastr.arcana.item.custom.MagicSpellScrollItem;

public class CastFloodScrollItem extends MagicSpellScrollItem {

    // Creates a storm and flooded area around the source of the spell
    private static final String SPELL = "cast_flood";
    private static final String TYPE = "magic";
    private static final int COOLDOWN = 500;
    private static final int COST = -100;

    public CastFloodScrollItem(Settings settings) {
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
