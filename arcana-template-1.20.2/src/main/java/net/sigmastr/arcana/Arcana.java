package net.sigmastr.arcana;

import net.fabricmc.api.ModInitializer;
import net.sigmastr.arcana.block.ArcanaBlocks;
import net.sigmastr.arcana.entity.ArcanaEntities;
import net.sigmastr.arcana.item.ArcanaItems;
import net.sigmastr.arcana.util.ArcanaLootTableModifiers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Arcana implements ModInitializer {
	public static final String MOD_ID = "arcana";
    public static final Logger LOGGER = LoggerFactory.getLogger("arcana");

	@Override
	public void onInitialize() {

		LOGGER.info("Arcana is initializing /-----*");
		ArcanaEntities.register();
		ArcanaItems.registerModItems();
		ArcanaBlocks.registerModBlocks();
		ArcanaLootTableModifiers.modifyLootTables();
	}
}