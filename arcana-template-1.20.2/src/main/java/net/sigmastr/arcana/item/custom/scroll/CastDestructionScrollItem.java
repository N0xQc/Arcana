package net.sigmastr.arcana.item.custom.scroll;

import net.sigmastr.arcana.item.custom.MagicSpellScrollItem;

public class CastDestructionScrollItem extends MagicSpellScrollItem {

    // Casts a spell that shoots a fireball in the direction the player is facing
    private static final String SPELL = "cast_fireball";
    private static final String TYPE = "nether";
    private static final int COOLDOWN = 100;
    private static final int COST = -20;

    public CastDestructionScrollItem(Settings settings) {
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
