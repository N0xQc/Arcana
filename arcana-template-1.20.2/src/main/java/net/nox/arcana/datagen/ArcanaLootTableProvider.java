package net.nox.arcana.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.VillagerEntity;
import net.nox.arcana.block.ArcanaBlocks;

public class ArcanaLootTableProvider extends FabricBlockLootTableProvider {
    public ArcanaLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        // Arcana Originals
        addDrop(ArcanaBlocks.SEEING_STONE);
    }
}
