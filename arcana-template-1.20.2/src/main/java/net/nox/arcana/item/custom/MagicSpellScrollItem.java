package net.nox.arcana.item.custom;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public abstract class MagicSpellScrollItem extends Item {

    public MagicSpellScrollItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Check for reference equality
        if (obj == null || getClass() != obj.getClass()) return false; // Check for null or class mismatch
        MagicSpellScrollItem other = (MagicSpellScrollItem) obj;
        return this.getSPELL().equals(other.getSPELL()); // Compare based on unique attribute
    }

    @Override
    public int hashCode() {
        return getSPELL().hashCode(); // Ensure consistency with equals()
    }

    public abstract String getSPELL();

    public abstract String getTYPE();

    public abstract int getCOOLDOWN();

    public abstract int getCOST();
}
