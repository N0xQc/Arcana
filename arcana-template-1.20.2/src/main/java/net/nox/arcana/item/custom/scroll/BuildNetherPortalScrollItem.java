package net.nox.arcana.item.custom.scroll;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import net.nox.arcana.item.custom.MagicSpellScrollItem;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BuildNetherPortalScrollItem extends MagicSpellScrollItem {

        // Builds a nether portal at the targeted location
        private static final String SPELL = "build_nether_portal";
        private static final String TYPE = "nether";
        private static final int COOLDOWN = 750;
        private static final int COST = -200;

        public BuildNetherPortalScrollItem(Settings settings) {
            super(settings);
        }

    @Override
    public String getSPELL() {
        return SPELL;
    }

    @Override
    public String getTYPE() {
        return TYPE;
    }

    @Override
    public int getCOOLDOWN() {
        return COOLDOWN;
    }

    @Override
    public int getCOST() {
        return COST;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.arcana.build_nether_portal_scroll.spell_description").formatted(Formatting.GRAY));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
