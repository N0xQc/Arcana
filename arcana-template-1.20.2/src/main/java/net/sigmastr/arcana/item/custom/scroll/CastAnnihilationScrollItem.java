package net.sigmastr.arcana.item.custom.scroll;

import net.sigmastr.arcana.item.custom.MagicSpellScrollItem;

public class CastAnnihilationScrollItem extends MagicSpellScrollItem {

        // Casts a spell that annihilates the targeted location
        private static final String SPELL = "cast_annihilation";
        private static final String TYPE = "elder";
        private static final int COOLDOWN = 750;
        private static final int COST = -200;

        public CastAnnihilationScrollItem(Settings settings) {
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
