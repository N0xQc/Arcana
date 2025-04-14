package net.nox.arcana.item.custom.wand;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CauldronBlock;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.nox.arcana.builder.*;
import net.nox.arcana.entity.custom.MagicSpellProjectileEntity;
import net.nox.arcana.item.ArcanaItems;
import net.nox.arcana.item.custom.MagicSpellScrollItem;
import net.nox.arcana.item.custom.scroll.*;

public class ElderWandItem extends Item {

    // VARIABLES
    private static String spell = "none";
    private static String type = "none";
    private static int cooldown = 1;
    private static int cost = 0;
    private static boolean cast = false;
    private static String direction = "none";
    private static MagicSpellScrollItem[] getMagicSpellScrollItems() {
        return new MagicSpellScrollItem[] {
                // BUILD SPELLS
                ArcanaItems.BUILD_BRIDGE_SCROLL,
                ArcanaItems.BUILD_NETHER_PORTAL_SCROLL,
                ArcanaItems.BUILD_PILLAR_SCROLL,
                ArcanaItems.BUILD_STAIRS_SCROLL,
                ArcanaItems.BUILD_WALL_SCROLL,
                ArcanaItems.BUILD_PLATFORM_SCROLL,
                // CAST SPELLS
                ArcanaItems.CAST_ANNIHILATION_SCROLL,
                ArcanaItems.CAST_AURA_SCROLL,
                ArcanaItems.CAST_BLOOM_SCROLL,
                ArcanaItems.CAST_CLARITY_SCROLL,
                ArcanaItems.CAST_DESTRUCTION_SCROLL,
                ArcanaItems.CAST_FLOOD_SCROLL,
                ArcanaItems.CAST_HARDENING_SCROLL,
                ArcanaItems.CAST_LOOP_SCROLL,
                ArcanaItems.CAST_METEOROLOGY_SCROLL,
                ArcanaItems.CAST_POCKET_DIMENSION_SCROLL,
                ArcanaItems.CAST_PROPULSION_SCROLL,
                ArcanaItems.CAST_VACUUM_SCROLL,
                ArcanaItems.CAST_WARP_SCROLL,
                ArcanaItems.CAST_WITHERING_SCROLL,
                ArcanaItems.CAST_WRATH_SCROLL,
                // INVOKE SPELLS
                ArcanaItems.INVOKE_BLAZE_SCROLL,
                ArcanaItems.INVOKE_GHAST_SCROLL,
                ArcanaItems.INVOKE_IRON_GOLEM_SCROLL,
                ArcanaItems.INVOKE_SNOW_GOLEM_SCROLL,
                ArcanaItems.INVOKE_UNDEAD_SCROLL,
                ArcanaItems.INVOKE_VILLAGER_SCROLL,
                ArcanaItems.INVOKE_WOLF_SCROLL
        };
    };
    // No projectile spells
    private static MagicSpellScrollItem[] getMagicSpellScrollItemsNoProjectile() {
        return new MagicSpellScrollItem[] {
                // BUILD SPELLS
                ArcanaItems.BUILD_NETHER_PORTAL_SCROLL,
                ArcanaItems.BUILD_PILLAR_SCROLL,
                ArcanaItems.BUILD_STAIRS_SCROLL,
                ArcanaItems.BUILD_WALL_SCROLL,
                ArcanaItems.BUILD_PLATFORM_SCROLL,
                // CAST SPELLS
                ArcanaItems.CAST_AURA_SCROLL,
                ArcanaItems.CAST_CLARITY_SCROLL,
                ArcanaItems.CAST_LOOP_SCROLL,
                ArcanaItems.CAST_METEOROLOGY_SCROLL,
                ArcanaItems.CAST_POCKET_DIMENSION_SCROLL,
                ArcanaItems.CAST_PROPULSION_SCROLL
        };
    }


    // CONSTRUCTOR
    public ElderWandItem(Settings settings) {
        super(settings);
    }


    // METHODS
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {

        // CARDINAL DIRECTION CHECK
        if (playerEntity.getYaw() >= -45 && playerEntity.getYaw() < 45) {
            direction = "south";
        } else if (playerEntity.getYaw() >= 45 && playerEntity.getYaw() < 135) {
            direction = "west";
        } else if (playerEntity.getYaw() >= 135 || playerEntity.getYaw() < -135) {
            direction = "north";
        } else if (playerEntity.getYaw() >= -135 && playerEntity.getYaw() < -45) {
            direction = "east";
        }

        // SPELL CHECK
        Item item = playerEntity.getOffHandStack().getItem();
        for (MagicSpellScrollItem magicSpellScrollItem : getMagicSpellScrollItems()) {
            if (item.equals(magicSpellScrollItem)) {
                spell = magicSpellScrollItem.getSPELL();
                type = magicSpellScrollItem.getTYPE();
                cooldown = magicSpellScrollItem.getCOOLDOWN();
                cost = magicSpellScrollItem.getCOST();

                // Apply 50% reduction for all spells and 125% for "end" spells specifically
                if (type.equals("end")) {
                    cooldown /= 5;
                    cost /= 5;
                } else {
                    cooldown /= 2;
                    cost /= 2;
                }
                break;
            }
        }

        // Check if the player has the MAGIC_WAND in their hand
        if (playerEntity.getStackInHand(hand).getItem() == ArcanaItems.ELDER_WAND && playerEntity.totalExperience >= -cost && !cast) {

            if (!world.isClient) {
                // SPAWN MAGIC SPELL PROJECTILE
                if (isProjectileSpell(spell)) {
                    world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.BLOCK_AMETHYST_CLUSTER_HIT, SoundCategory.NEUTRAL, 0.5f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));
                    MagicSpellProjectileEntity magicSpellProjectileEntity = new MagicSpellProjectileEntity(playerEntity, world);
                    magicSpellProjectileEntity.setItem(new ItemStack(ArcanaItems.MAGIC_SPELL_PROJECTILE));
                    magicSpellProjectileEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0f, 3f, 3.0f);
                    world.spawnEntity(magicSpellProjectileEntity);
                    playerEntity.getItemCooldownManager().set(this, cooldown);
                    playerEntity.addExperience(cost);
                } else {
                    // NO PROJECTILE SPELLS
                    switch (spell) {
                        case "build_nether_portal" -> {
                            // Perform ray tracing to find the block the player is looking at
                            BlockHitResult blockHitResult = (BlockHitResult) playerEntity.raycast(5.0, 1.0f, false);
                            if (blockHitResult.getType() == HitResult.Type.BLOCK) {
                                BlockPos pos = blockHitResult.getBlockPos().up(); // Get the block position
                                NetherPortalBuilder portalBuilder = new NetherPortalBuilder((ServerWorld) world, pos, direction, true);
                                portalBuilder.startBuilding();
                            }
                        }

                        case "build_pillar" -> {
                            // Perform ray tracing to find the block the player is looking at
                            BlockHitResult blockHitResult = (BlockHitResult) playerEntity.raycast(5.0, 1.0f, false);
                            if (blockHitResult.getType() == HitResult.Type.BLOCK) {
                                BlockPos pos = blockHitResult.getBlockPos().up(); // Get the block position
                                ServerWorld serverWorld = (ServerWorld) world;
                                BlockState hitBlockState = serverWorld.getBlockState(blockHitResult.getBlockPos());
                                Block hitBlock = hitBlockState.getBlock();
                                if (NaturalBlocks.isNatural(hitBlock)) {
                                    PillarBuilder pillarBuilder = new PillarBuilder((ServerWorld) world, pos, direction, hitBlock, 20, ArcanaItems.ELDER_WAND);
                                    pillarBuilder.startBuilding();
                                }
                            }
                        }

                        case "build_stairs" -> {
                            // Perform ray tracing to find the block the player is looking at
                            BlockHitResult blockHitResult = (BlockHitResult) playerEntity.raycast(5.0, 1.0f, false);
                            if (blockHitResult.getType() == HitResult.Type.BLOCK) {
                                BlockPos pos = blockHitResult.getBlockPos(); // Get the block position
                                ServerWorld serverWorld = (ServerWorld) world;
                                BlockState hitBlockState = serverWorld.getBlockState(blockHitResult.getBlockPos());
                                Block hitBlock = hitBlockState.getBlock();
                                if (NaturalBlocks.isNatural(hitBlock)) {
                                    StairsBuilder stairsBuilder = new StairsBuilder((ServerWorld) world, pos, direction, hitBlock, 20, ArcanaItems.ELDER_WAND, playerEntity.isSneaking());
                                    stairsBuilder.startBuilding();
                                }
                            }
                        }

                        case "build_wall" -> {
                            // Perform ray tracing to find the block the player is looking at
                            BlockHitResult blockHitResult = (BlockHitResult) playerEntity.raycast(5.0, 1.0f, false);
                            if (blockHitResult.getType() == HitResult.Type.BLOCK) {
                                BlockPos pos = blockHitResult.getBlockPos().up(); // Get the block position
                                ServerWorld serverWorld = (ServerWorld) world;
                                BlockState hitBlockState = serverWorld.getBlockState(blockHitResult.getBlockPos());
                                Block hitBlock = hitBlockState.getBlock();
                                if (NaturalBlocks.isNatural(hitBlock)) {
                                    WallBuilder wallBuilder = new WallBuilder((ServerWorld) world, pos, direction, hitBlock, 10);
                                    wallBuilder.startBuilding();
                                }
                            }
                        }

                        case "build_platform" -> {
                            // Perform ray tracing to find the block the player is looking at
                            BlockHitResult blockHitResult = (BlockHitResult) playerEntity.raycast(5.0, 1.0f, false);
                            if (blockHitResult.getType() == HitResult.Type.BLOCK) {
                                BlockPos pos = blockHitResult.getBlockPos().up(); // Get the block position
                                ServerWorld serverWorld = (ServerWorld) world;
                                BlockState hitBlockState = serverWorld.getBlockState(blockHitResult.getBlockPos());
                                Block hitBlock = hitBlockState.getBlock();
                                if (NaturalBlocks.isNatural(hitBlock)) {
                                    PlatformBuilder platformBuilder = new PlatformBuilder((ServerWorld) world, pos, hitBlock, 20);
                                    platformBuilder.startBuilding();
                                }
                            }
                        }

                        case "cast_aura" -> {
                            ServerWorld serverWorld = (ServerWorld) world;
                            playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 300, 1));
                            playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 300, 2));
                            world.playSound(null, playerEntity.getBlockPos(), SoundEvents.BLOCK_BEACON_ACTIVATE, playerEntity.getSoundCategory(), 1.0F, 1.0F);
                            serverWorld.spawnParticles(ParticleTypes.SONIC_BOOM, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), 1, 0.5, 0.5, 0.5, 0.1);
                        }

                        case "cast_clarity" -> {
                            ServerWorld serverWorld = (ServerWorld) world;
                            playerEntity.clearStatusEffects();
                            serverWorld.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.BLOCK_BEACON_ACTIVATE, SoundCategory.NEUTRAL, 0.5f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));
                            serverWorld.spawnParticles(ParticleTypes.HEART, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), 100, 0.5, 0.5, 0.5, 0.1);
                        }

                        case "cast_destruction" -> {
                            world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.NEUTRAL, 0.5f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));
                            FireballEntity fireballEntity = new FireballEntity(world, playerEntity, 0f, 0f, 0f, 5); // 5 is the explosion power for a greater fireball

                            fireballEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0f, 3f, 3.0f);
                            fireballEntity.setOwner(playerEntity);
                            fireballEntity.setPos(playerEntity.getX(), playerEntity.getEyeY(), playerEntity.getZ());
                            world.spawnEntity(fireballEntity);
                        }

                        case "cast_loop" -> {
                            ServerWorld serverWorld = (ServerWorld) world;
                            world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.BLOCK_AMETHYST_CLUSTER_HIT, SoundCategory.NEUTRAL, 0.5f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));
                            serverWorld.spawnParticles(ParticleTypes.END_ROD, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), 100, 0.5, 0.5, 0.5, 0.1);

                            long currentTimeOfDay = serverWorld.getTimeOfDay() % 24000; // Normalize time within the 0–23999 range

                            long newTimeOfDay = (currentTimeOfDay + 12000) % 24000;  // Add 12000 ticks (12 hours), ensuring time wraps around after 24000 ticks


                            // Update the time of day
                            serverWorld.setTimeOfDay(newTimeOfDay);
                        }

                        case "cast_meteorology" -> {
                            ServerWorld serverWorld = (ServerWorld) world;
                            world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.BLOCK_AMETHYST_CLUSTER_HIT, SoundCategory.NEUTRAL, 0.5f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));
                            serverWorld.spawnParticles(ParticleTypes.END_ROD, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), 100, 0.5, 0.5, 0.5, 0.1);
                            if (serverWorld.isRaining()) {
                                serverWorld.setWeather(10000, 0, false, false);
                            } else {
                                serverWorld.setWeather(0, 10000, true, true);
                            }
                        }

                        case "cast_pocket_dimension" -> {
                            ServerWorld serverWorld = (ServerWorld) world;
                            world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.NEUTRAL, 0.5f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));
                            serverWorld.spawnParticles(ParticleTypes.WITCH, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), 100, 0.5, 0.5, 0.5, 0.1);
                            // opens ender chest gui
                            EnderChestInventory enderChestInventory = playerEntity.getEnderChestInventory();
                            playerEntity.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, inventory, playerx) -> GenericContainerScreenHandler.createGeneric9x3(syncId, inventory, enderChestInventory), Text.of("Pocket Dimension")));
                            playerEntity.incrementStat(Stats.OPEN_ENDERCHEST);
                        }

                        case "cast_propulsion" -> {
                            ServerWorld serverWorld = (ServerWorld) world;

                            // Check if the player is gliding with elytra
                            if (playerEntity.isFallFlying()) {
                                ItemStack itemStack = playerEntity.getStackInHand(hand);
                                if (!world.isClient) {
                                    FireworkRocketEntity fireworkRocketEntity = new FireworkRocketEntity(world, itemStack, playerEntity);
                                    world.spawnEntity(fireworkRocketEntity);
                                }
                            }
                            // Play sound and spawn particles
                            world.playSound(
                                    null,
                                    playerEntity.getBlockPos(),
                                    SoundEvents.ENTITY_FIREWORK_ROCKET_BLAST,
                                    playerEntity.getSoundCategory(),
                                    1.0F,
                                    1.0F
                            );

                            serverWorld.spawnParticles(
                                    ParticleTypes.FIREWORK,
                                    playerEntity.getX(),
                                    playerEntity.getY(),
                                    playerEntity.getZ(),
                                    50, // Number of particles
                                    0.5, 0.5, 0.5, // Spread in x, y, z directions
                                    0.1 // Speed multiplier
                            );
                        }
                    }
                    playerEntity.getItemCooldownManager().set(this, cooldown);
                    playerEntity.addExperience(cost);
                }
            }
            playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
            // Return a successful result
            return TypedActionResult.success(playerEntity.getStackInHand(hand));
        }
        // Return a failed result if the item in hand is not ELDER_WAND
        return TypedActionResult.fail(playerEntity.getStackInHand(hand));
    }

    private boolean isProjectileSpell(String spell) {
        for (MagicSpellScrollItem magicSpellScrollItem : getMagicSpellScrollItemsNoProjectile()) {
            if (spell.equals(magicSpellScrollItem.getSPELL())) {
                return false;
            }
        }
        return true;
    }

    public static String getSpell() {
        return spell;
    }

    public static int getCooldown() {
        return cost;
    }

    public static void startCastCooldown(int time) {
        new Thread(() -> {
            try {
                Thread.sleep(time);
                cast = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    @Override
    public Text getName(ItemStack stack) {
        return Text.literal("Elder Wand").formatted(Formatting.AQUA);
    }

    public static void setCast(boolean cast) {
        ElderWandItem.cast = cast;
    }

    public static String getDirection() {
        return direction;
    }
}
