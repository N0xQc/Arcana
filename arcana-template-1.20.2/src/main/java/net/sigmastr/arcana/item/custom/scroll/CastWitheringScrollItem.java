package net.sigmastr.arcana.item.custom.scroll;

import net.sigmastr.arcana.item.custom.MagicSpellScrollItem;

public class CastWitheringScrollItem extends MagicSpellScrollItem {

    // Casts a spell that kills and consumes the souls of the targeted entity (if it has a soul) and heals the caster
    private static final String SPELL = "cast_soul_consumption";
    private static final String TYPE = "elder";
    private static final int COOLDOWN = 1000;
    private static final int COST = 0;

    public CastWitheringScrollItem(Settings settings) {
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
