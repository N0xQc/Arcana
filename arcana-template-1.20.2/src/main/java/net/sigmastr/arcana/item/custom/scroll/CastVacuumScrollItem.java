package net.sigmastr.arcana.item.custom.scroll;

import net.sigmastr.arcana.item.custom.MagicSpellScrollItem;

public class CastVacuumScrollItem extends MagicSpellScrollItem {

    // Casts a vacuum spell that sucks in all the water and lava around the caster
    private static final String SPELL = "cast_vaccum";
    private static final String TYPE = "end";
    private static final int COOLDOWN = 100;
    private static final int COST = -20;

    public CastVacuumScrollItem(Settings settings) {
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
