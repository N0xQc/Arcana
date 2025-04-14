package net.nox.arcana;

import net.fabricmc.api.ModInitializer;
import net.nox.arcana.block.ArcanaBlocks;
import net.nox.arcana.entity.ArcanaEntities;
import net.nox.arcana.fluid.ArcanaFluids;
import net.nox.arcana.item.ArcanaItems;
import net.nox.arcana.item.custom.MagicWhipItem;
import net.nox.arcana.util.ArcanaLootTableModifiers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Arcana implements ModInitializer {
	public static final String MOD_ID = "arcana";
    public static final Logger LOGGER = LoggerFactory.getLogger("arcana");

	@Override
	public void onInitialize() {

		LOGGER.info("Arcana is initializing /-----*");
		ArcanaEntities.registerArcanaEntities();
		ArcanaItems.registerArcanaItems();
		ArcanaBlocks.registerArcanaBlocks();
		ArcanaFluids.registerArcanaFluids();
		ArcanaLootTableModifiers.modifyLootTables();
		MagicWhipItem.registerInteractionHandler();
	}
}