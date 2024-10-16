package net.sigmastr.arcana.item.custom.scroll;

import net.sigmastr.arcana.item.custom.MagicSpellScrollItem;

public class CastLoopScrollItem extends MagicSpellScrollItem {

        // Casts a spell that alternates the time of day between day and night
        private static String SPELL = "cast_time_control";
        private static String TYPE = "end";
        private static int COOLDOWN = 500;
        private static int COST = -100;

        // CONSTRUCTOR
        public CastLoopScrollItem(Settings settings) {
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
