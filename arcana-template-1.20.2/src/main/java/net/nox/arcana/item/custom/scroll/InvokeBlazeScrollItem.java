package net.nox.arcana.item.custom.scroll;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import net.nox.arcana.item.custom.MagicSpellScrollItem;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class InvokeBlazeScrollItem extends MagicSpellScrollItem {

    // Invokes a blaze at the targeted location
    private static final String SPELL = "invoke_blaze";
    private static final String TYPE = "nether";
    private static final int COOLDOWN = 250;
    private static final int COST = -150;

    public InvokeBlazeScrollItem(Settings settings) {
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
        tooltip.add(Text.translatable("tooltip.arcana.invoke_blaze_scroll.spell_description").formatted(Formatting.GRAY));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
