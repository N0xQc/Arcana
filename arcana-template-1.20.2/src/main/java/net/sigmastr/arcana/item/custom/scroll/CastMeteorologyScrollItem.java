package net.sigmastr.arcana.item.custom.scroll;

import net.sigmastr.arcana.item.custom.MagicSpellScrollItem;

public class CastMeteorologyScrollItem extends MagicSpellScrollItem {

        // Casts the weather control spell altering the weather in the world
        private static final String SPELL = "cast_weather_control";
        private static final String TYPE = "end";
        private static final int COOLDOWN = 1000;
        private static final int COST = -200;

        // CONSTRUCTOR
        public CastMeteorologyScrollItem(Settings settings) {
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
