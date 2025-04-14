package net.nox.arcana.item.custom.scroll;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import net.nox.arcana.item.custom.MagicSpellScrollItem;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CastFloodScrollItem extends MagicSpellScrollItem {

    // Creates a storm and flooded area around the source of the spell
    private static final String SPELL = "cast_flood";
    private static final String TYPE = "magic";
    private static final int COOLDOWN = 500;
    private static final int COST = -100;

    public CastFloodScrollItem(Settings settings) {
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
        tooltip.add(Text.translatable("tooltip.arcana.cast_flood_scroll.spell_description").formatted(Formatting.GRAY));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
