package net.nox.arcana.fluid;

import net.minecraft.fluid.FlowableFluid;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.nox.arcana.Arcana;

public class ArcanaFluids {
    public static final FlowableFluid BLOOD_FLOWING = register("blood_flowing", new BloodFluid.Flowing());
    public static final FlowableFluid BLOOD_STILL = register("blood_still", new BloodFluid.Still());

    private static FlowableFluid register(String name, FlowableFluid fluid) {
        return Registry.register(Registries.FLUID, new Identifier(Arcana.MOD_ID, name), fluid);
    }

    public static void registerArcanaFluids() {
        Arcana.LOGGER.info("Registering Mod Fluids for " + Arcana.MOD_ID);
    }
}
