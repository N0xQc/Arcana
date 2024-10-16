package net.sigmastr.arcana.item.custom.scroll;

import net.sigmastr.arcana.item.custom.MagicSpellScrollItem;

public class BuildWallScrollItem extends MagicSpellScrollItem {

    // Casts a spell that builds a wall in the direction the player is facing at the targeted location
    private static final String SPELL = "build_wall";
    private static final String TYPE = "magic";
    private static final int COOLDOWN = 100;
    private static final int COST = -20;

    public BuildWallScrollItem(Settings settings) {
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
