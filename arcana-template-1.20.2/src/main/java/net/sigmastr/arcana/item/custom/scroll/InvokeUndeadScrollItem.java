package net.sigmastr.arcana.item.custom.scroll;

import net.sigmastr.arcana.item.custom.MagicSpellScrollItem;

public class InvokeUndeadScrollItem extends MagicSpellScrollItem {

        // Invokes an undead mob at the targeted location
        private static final String SPELL = "invoke_undead";
        private static final String TYPE = "nether";
        private static final int COOLDOWN = 150;
        private static final int COST = -30;

        public InvokeUndeadScrollItem(Settings settings) {
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
