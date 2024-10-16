package net.sigmastr.arcana.item.custom.scroll;

import net.sigmastr.arcana.item.custom.MagicSpellScrollItem;

public class BuildNetherPortalScrollItem extends MagicSpellScrollItem {

        // Builds a nether portal at the targeted location
        private static final String SPELL = "build_nether_portal";
        private static final String TYPE = "nether";
        private static final int COOLDOWN = 1000;
        private static final int COST = -200;

        public BuildNetherPortalScrollItem(Settings settings) {
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
