package net.sigmastr.arcana.item.custom.scroll;

import net.sigmastr.arcana.item.custom.MagicSpellScrollItem;

public class CastWarpScrollItem extends MagicSpellScrollItem {

    // Casts a spell that teleports the caster to the targeted location
    private static final String SPELL = "cast_teleportation";
    private static final String TYPE = "end";
    private static final int COOLDOWN = 1000;
    private static final int COST = -200;

    public CastWarpScrollItem(Settings settings) {
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
