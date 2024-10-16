package net.sigmastr.arcana.item.custom.scroll;

import net.sigmastr.arcana.item.custom.MagicSpellScrollItem;

public class BuildPlatformScrollItem extends MagicSpellScrollItem {

        // Builds a well at the targeted location
        private static final String SPELL = "build_platform";
        private static final String TYPE = "magic";
        private static final int COOLDOWN = 100;
        private static final int COST = -10;

        // CONSTRUCTOR
        public BuildPlatformScrollItem(Settings settings) {
            super(settings);
        }

        // METHODS
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
