package net.nox.arcana.item.custom.scroll;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import net.nox.arcana.item.custom.MagicSpellScrollItem;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CastWitheringScrollItem extends MagicSpellScrollItem {

    // Casts a spell that kills and consumes the souls of the targeted entity (if it has a soul) and heals the caster
    private static final String SPELL = "cast_withering";
    private static final String TYPE = "elder";
    private static final int COOLDOWN = 1000;
    private static final int COST = -200;

    public CastWitheringScrollItem(Settings settings) {
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
        tooltip.add(Text.translatable("tooltip.arcana.cast_withering_scroll.spell_description").formatted(Formatting.GRAY));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
