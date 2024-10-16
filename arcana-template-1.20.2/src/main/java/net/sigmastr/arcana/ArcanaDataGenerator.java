package net.sigmastr.arcana;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.sigmastr.arcana.datagen.ArcanaLootTableProvider;
import net.sigmastr.arcana.datagen.ArcanaModelProvider;
import net.sigmastr.arcana.datagen.ArcanaRecipeProvider;

public class ArcanaDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ArcanaLootTableProvider::new);
		pack.addProvider(ArcanaModelProvider::new);
		pack.addProvider(ArcanaRecipeProvider::new);
	}
}
