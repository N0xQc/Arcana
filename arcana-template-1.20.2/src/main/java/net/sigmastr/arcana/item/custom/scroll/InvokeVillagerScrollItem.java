package net.sigmastr.arcana.item.custom.scroll;

import net.sigmastr.arcana.item.custom.MagicSpellScrollItem;

public class InvokeVillagerScrollItem extends MagicSpellScrollItem {

        // Invokes an undead mob at the targeted location
        private static final String SPELL = "invoke_villager";
        private static final String TYPE = "elder";
        private static final int COOLDOWN = 150;
        private static final int COST = -90;

        public InvokeVillagerScrollItem(Settings settings) {
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
