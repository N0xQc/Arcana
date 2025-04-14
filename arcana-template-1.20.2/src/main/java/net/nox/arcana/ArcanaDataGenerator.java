package net.nox.arcana;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.nox.arcana.datagen.ArcanaLootTableProvider;
import net.nox.arcana.datagen.ArcanaModelProvider;
import net.nox.arcana.datagen.ArcanaRecipeProvider;

public class ArcanaDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ArcanaLootTableProvider::new);
		pack.addProvider(ArcanaModelProvider::new);
		pack.addProvider(ArcanaRecipeProvider::new);
	}
}
