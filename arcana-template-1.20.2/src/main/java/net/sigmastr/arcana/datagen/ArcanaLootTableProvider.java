package net.sigmastr.arcana.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.sigmastr.arcana.block.ArcanaBlocks;

public class ArcanaLootTableProvider extends FabricBlockLootTableProvider {
    public ArcanaLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ArcanaBlocks.SEEING_STONE);
    }
}
