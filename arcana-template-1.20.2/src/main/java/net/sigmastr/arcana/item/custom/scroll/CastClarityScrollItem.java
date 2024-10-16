package net.sigmastr.arcana.item.custom.scroll;

import net.sigmastr.arcana.item.custom.MagicSpellScrollItem;

public class CastClarityScrollItem extends MagicSpellScrollItem {

    // Casts a spell that purifies the player (heals the player, and removes all negative status effects)
    private static final String SPELL = "cast_purification";
    private static final String TYPE = "magic";
    private static final int COOLDOWN = 50;
    private static final int COST = -10;

    public CastClarityScrollItem(Settings settings) {
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
