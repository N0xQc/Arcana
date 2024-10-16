package net.sigmastr.arcana.item.custom.scroll;

import net.sigmastr.arcana.item.custom.MagicSpellScrollItem;

public class InvokeSnowGolemScrollItem extends MagicSpellScrollItem {

    // Invokes a snow golem at the targeted location
    private static final String SPELL = "invoke_snow_golem";
    private static final String TYPE = "magic";
    private static final int COOLDOWN = 150;
    private static final int COST = -30;

    public InvokeSnowGolemScrollItem(Settings settings) {
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
