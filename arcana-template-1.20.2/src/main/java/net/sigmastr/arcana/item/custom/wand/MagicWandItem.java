package net.sigmastr.arcana.item.custom.wand;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.sigmastr.arcana.entity.custom.MagicSpellProjectileEntity;
import net.sigmastr.arcana.item.ArcanaItems;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.sigmastr.arcana.item.custom.scroll.*;

public class MagicWandItem extends Item {

    // VARIABLES
    private static String spell = "none";
    private static String type = "none";
    private static int cooldown = 1;
    private static int cost = 0;
    private static boolean cast = false;
    private static String direction = "none";


    // CONSTRUCTOR
    public MagicWandItem(Settings settings) {
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
        // BUILD SPELLS
        if (item == ArcanaItems.BUILD_BRIDGE_SCROLL) {
            spell = BuildBridgeScrollItem.getSPELL();
            type = BuildBridgeScrollItem.getTYPE();
            cooldown = BuildBridgeScrollItem.getCOOLDOWN();
            cost = BuildBridgeScrollItem.getCOST();
        } else if (item == ArcanaItems.BUILD_NETHER_PORTAL_SCROLL) {
            spell = BuildNetherPortalScrollItem.getSPELL();
            type = BuildNetherPortalScrollItem.getTYPE();
            cooldown = BuildNetherPortalScrollItem.getCOOLDOWN();
            cost = BuildNetherPortalScrollItem.getCOST();
        } else if (item == ArcanaItems.BUILD_PILLAR_SCROLL) {
            spell = BuildPillarScrollItem.getSPELL();
            type = BuildPillarScrollItem.getTYPE();
            cooldown = BuildPillarScrollItem.getCOOLDOWN();
            cost = BuildPillarScrollItem.getCOST();
        } else if (item == ArcanaItems.BUILD_STAIRS_SCROLL) {
            spell = BuildStairsScrollItem.getSPELL();
            type = BuildStairsScrollItem.getTYPE();
            cooldown = BuildStairsScrollItem.getCOOLDOWN();
            cost = BuildStairsScrollItem.getCOST();
        } else if (item == ArcanaItems.BUILD_WALL_SCROLL) {
            spell = BuildWallScrollItem.getSPELL();
            type = BuildWallScrollItem.getTYPE();
            cooldown = BuildWallScrollItem.getCOOLDOWN();
            cost = BuildWallScrollItem.getCOST();
        } else if (item == ArcanaItems.BUILD_PLATFORM_SCROLL) {
            spell = BuildPlatformScrollItem.getSPELL();
            type = BuildPlatformScrollItem.getTYPE();
            cooldown = BuildPlatformScrollItem.getCOOLDOWN();
            cost = BuildPlatformScrollItem.getCOST();

        }
        // CAST SPELLS
        else if (item == ArcanaItems.CAST_ANNIHILATION_SCROLL) {
            spell = CastAnnihilationScrollItem.getSPELL();
            type = CastAnnihilationScrollItem.getTYPE();
            cooldown = CastAnnihilationScrollItem.getCOOLDOWN();
            cost = CastAnnihilationScrollItem.getCOST();
        } else if (item == ArcanaItems.CAST_AURA_SCROLL) {
            spell = CastAuraScrollItem.getSPELL();
            type = CastAuraScrollItem.getTYPE();
            cooldown = CastAuraScrollItem.getCOOLDOWN();
            cost = CastAuraScrollItem.getCOST();
        } else if (item == ArcanaItems.CAST_BLOOM_SCROLL) {
            spell = CastBloomScrollItem.getSPELL();
            type = CastBloomScrollItem.getTYPE();
            cooldown = CastBloomScrollItem.getCOOLDOWN();
            cost = CastBloomScrollItem.getCOST();
        } else if (item == ArcanaItems.CAST_CLARITY_SCROLL) {
            spell = CastClarityScrollItem.getSPELL();
            type = CastClarityScrollItem.getTYPE();
            cooldown = CastClarityScrollItem.getCOOLDOWN();
            cost = CastClarityScrollItem.getCOST();
        } else if (item == ArcanaItems.CAST_DESTRUCTION_SCROLL) {
            spell = CastDestructionScrollItem.getSPELL();
            type = CastDestructionScrollItem.getTYPE();
            cooldown = CastDestructionScrollItem.getCOOLDOWN();
            cost = CastDestructionScrollItem.getCOST();
        } else if (item == ArcanaItems.CAST_FLOOD_SCROLL) {
            spell = CastFloodScrollItem.getSPELL();
            type = CastFloodScrollItem.getTYPE();
            cooldown = CastFloodScrollItem.getCOOLDOWN();
            cost = CastFloodScrollItem.getCOST();
        } else if (item == ArcanaItems.CAST_HARDENING_SCROLL) {
            spell = CastHardeningScrollItem.getSPELL();
            type = CastHardeningScrollItem.getTYPE();
            cooldown = CastHardeningScrollItem.getCOOLDOWN();
            cost = CastHardeningScrollItem.getCOST();
        } else if (item == ArcanaItems.CAST_LOOP_SCROLL) {
            spell = CastLoopScrollItem.getSPELL();
            type = CastLoopScrollItem.getTYPE();
            cooldown = CastLoopScrollItem.getCOOLDOWN();
            cost = CastLoopScrollItem.getCOST();
        } else if (item == ArcanaItems.CAST_METEOROLOGY_SCROLL) {
            spell = CastMeteorologyScrollItem.getSPELL();
            type = CastMeteorologyScrollItem.getTYPE();
            cooldown = CastMeteorologyScrollItem.getCOOLDOWN();
            cost = CastMeteorologyScrollItem.getCOST();
        } else if (item == ArcanaItems.CAST_POCKET_DIMENSION_SCROLL) {
            spell = CastPocketDimensionScrollItem.getSPELL();
            type = CastPocketDimensionScrollItem.getTYPE();
            cooldown = CastPocketDimensionScrollItem.getCOOLDOWN();
            cost = CastPocketDimensionScrollItem.getCOST();
        } else if (item == ArcanaItems.CAST_VACUUM_SCROLL) {
            spell = CastVacuumScrollItem.getSPELL();
            type = CastVacuumScrollItem.getTYPE();
            cooldown = CastVacuumScrollItem.getCOOLDOWN();
            cost = CastVacuumScrollItem.getCOST();
        } else if (item == ArcanaItems.CAST_WARP_SCROLL) {
            spell = CastWarpScrollItem.getSPELL();
            type = CastWarpScrollItem.getTYPE();
            cooldown = CastWarpScrollItem.getCOOLDOWN();
            cost = CastWarpScrollItem.getCOST();
        } else if (item == ArcanaItems.CAST_WITHERING_SCROLL) {
            spell = CastWitheringScrollItem.getSPELL();
            type = CastWitheringScrollItem.getTYPE();
            cooldown = CastWitheringScrollItem.getCOOLDOWN();
            cost = CastWitheringScrollItem.getCOST();
        } else if (item == ArcanaItems.CAST_WRATH_SCROLL) {
            spell = CastWrathScrollItem.getSPELL();
            type = CastWrathScrollItem.getTYPE();
            cooldown = CastWrathScrollItem.getCOOLDOWN();
            cost = CastWrathScrollItem.getCOST();
        }

        // INVOKE SPELLS
        else if (item == ArcanaItems.INVOKE_BLAZE_SCROLL) {
            spell = InvokeBlazeScrollItem.getSPELL();
            type = InvokeBlazeScrollItem.getTYPE();
            cooldown = InvokeBlazeScrollItem.getCOOLDOWN();
            cost = InvokeBlazeScrollItem.getCOST();
        } else if (item == ArcanaItems.INVOKE_GHAST_SCROLL) {
            spell = InvokeGhastScrollItem.getSPELL();
            type = InvokeGhastScrollItem.getTYPE();
            cooldown = InvokeGhastScrollItem.getCOOLDOWN();
            cost = InvokeGhastScrollItem.getCOST();
        } else if (item == ArcanaItems.INVOKE_IRON_GOLEM_SCROLL) {
            spell = InvokeIronGolemScrollItem.getSPELL();
            type = InvokeIronGolemScrollItem.getTYPE();
            cooldown = InvokeIronGolemScrollItem.getCOOLDOWN();
            cost = InvokeIronGolemScrollItem.getCOST();
        } else if (item == ArcanaItems.INVOKE_SNOW_GOLEM_SCROLL) {
            spell = InvokeSnowGolemScrollItem.getSPELL();
            type = InvokeSnowGolemScrollItem.getTYPE();
            cooldown = InvokeSnowGolemScrollItem.getCOOLDOWN();
            cost = InvokeSnowGolemScrollItem.getCOST();
        } else if (item == ArcanaItems.INVOKE_UNDEAD_SCROLL) {
            spell = InvokeUndeadScrollItem.getSPELL();
            type = InvokeUndeadScrollItem.getTYPE();
            cooldown = InvokeUndeadScrollItem.getCOOLDOWN();
            cost = InvokeUndeadScrollItem.getCOST();
        } else if (item == ArcanaItems.INVOKE_VILLAGER_SCROLL) {
            spell = InvokeVillagerScrollItem.getSPELL();
            type = InvokeVillagerScrollItem.getTYPE();
            cooldown = InvokeVillagerScrollItem.getCOOLDOWN();
            cost = InvokeVillagerScrollItem.getCOST();
        } else if (item == ArcanaItems.INVOKE_WOLF_SCROLL) {
            spell = InvokeWolfScrollItem.getSPELL();
            type = InvokeWolfScrollItem.getTYPE();
            cooldown = InvokeWolfScrollItem.getCOOLDOWN();
            cost = InvokeWolfScrollItem.getCOST();
        } else {
            spell = "none";
            type = "none";
            cooldown = 1;
            cost = 0;
        }
        // Cooldown and experience cost depending on the spell and the type
        // MAGIC_WAND has a 50% cooldown and experience cost reduction on spells of type "magic"
        if (type.equals("magic")) {
            cooldown = cooldown / 3;
            cost = cost / 3;
        }


        // Check if the player has the MAGIC_WAND in their hand
        if (playerEntity.getStackInHand(hand).getItem() == ArcanaItems.MAGIC_WAND && playerEntity.totalExperience >= -cost && !cast) {
            playerEntity.getItemCooldownManager().set(this, cooldown);
            playerEntity.addExperience(cost);

            if (!world.isClient) {
                // SPAWN MAGIC SPELL PROJECTILE
                if (!spell.equals(CastDestructionScrollItem.getSPELL()) && !spell.equals(CastLoopScrollItem.getSPELL()) && !spell.equals(CastMeteorologyScrollItem.getSPELL())) {
                    world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.BLOCK_AMETHYST_CLUSTER_HIT, SoundCategory.NEUTRAL, 0.5f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));
                    MagicSpellProjectileEntity magicSpellProjectileEntity = new MagicSpellProjectileEntity(playerEntity, world);
                    magicSpellProjectileEntity.setItem(new ItemStack(ArcanaItems.MAGIC_SPELL_PROJECTILE));
                    magicSpellProjectileEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0f, 3f, 3.0f);
                    world.spawnEntity(magicSpellProjectileEntity);
                }
                // AURA
                if (spell.equals(CastAuraScrollItem.getSPELL())) {
                    playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 1));
                    playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 600, 2));
                    world.playSound(null, playerEntity.getBlockPos(), SoundEvents.BLOCK_BEACON_ACTIVATE, playerEntity.getSoundCategory(), 1.0F, 1.0F);
                }
                // DESTRUCTION
                else if (spell.equals(CastDestructionScrollItem.getSPELL())) {
                    world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.NEUTRAL, 0.5f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));
                    FireballEntity fireballEntity = new FireballEntity(world, playerEntity, 0f, 0f, 0f, 2); // 2 is the explosion power of a regular fireball
                    fireballEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0f, 3f, 3.0f);
                    fireballEntity.setOwner(playerEntity);
                    fireballEntity.setPos(playerEntity.getX(), playerEntity.getEyeY(), playerEntity.getZ());
                    world.spawnEntity(fireballEntity);
                }
                // LOOP
                else if (spell.equals(CastLoopScrollItem.getSPELL())) {
                    world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.BLOCK_AMETHYST_CLUSTER_HIT, SoundCategory.NEUTRAL, 0.5f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));
                    ServerWorld serverWorld = (ServerWorld) world;

                    long currentTimeOfDay = serverWorld.getTimeOfDay() % 24000; // Normalize time within the 0–23999 range
                    long newTimeOfDay = (currentTimeOfDay + 12000) % 24000;  // Add 12000 ticks (12 hours), ensuring time wraps around after 24000 ticks

                    // Update the time of day
                    serverWorld.setTimeOfDay(newTimeOfDay);
                }
                // METEOROLOGY
                else if (spell.equals(CastMeteorologyScrollItem.getSPELL())) {
                    world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.BLOCK_AMETHYST_CLUSTER_HIT, SoundCategory.NEUTRAL, 0.5f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));
                    ServerWorld serverWorld = (ServerWorld) world;
                    if (serverWorld.isRaining()) {
                        serverWorld.setWeather(10000, 0, false, false);
                    } else {
                        serverWorld.setWeather(0, 10000, true, true);
                    }
                }
                // POCKET DIMENSION
                else if (spell.equals(CastPocketDimensionScrollItem.getSPELL())) {
                    world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.BLOCK_AMETHYST_CLUSTER_HIT, SoundCategory.NEUTRAL, 0.5f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));
                    ServerWorld serverWorld = (ServerWorld) world;
                    // opens ender chest gui
                    EnderChestInventory enderChestInventory = playerEntity.getEnderChestInventory();
                    playerEntity.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, inventory, playerx) -> GenericContainerScreenHandler.createGeneric9x3(syncId, inventory, enderChestInventory), Text.of("Pocket Dimension")));
                    playerEntity.incrementStat(Stats.OPEN_ENDERCHEST);
                }
            }
            playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
            // Return a successful result
            return TypedActionResult.success(playerEntity.getStackInHand(hand));
        }
        // Return a failed result if the item in hand is not MAGIC_WAND
        return TypedActionResult.fail(playerEntity.getStackInHand(hand));
    }

    public static String getSpell() {
        return spell;
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

    public static boolean getCast() {
        return cast;
    }

    public static void setCast(boolean cast) {
        MagicWandItem.cast = cast;
    }

    public static String getDirection() {
        return direction;
    }
}
