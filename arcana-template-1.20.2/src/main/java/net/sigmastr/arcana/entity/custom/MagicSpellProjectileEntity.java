package net.sigmastr.arcana.entity.custom;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.sigmastr.arcana.builder.*;
import net.sigmastr.arcana.entity.ArcanaEntities;
import net.sigmastr.arcana.item.ArcanaItems;
import net.sigmastr.arcana.item.custom.scroll.*;
import net.sigmastr.arcana.item.custom.wand.ElderWandItem;
import net.sigmastr.arcana.item.custom.wand.EndWandItem;
import net.sigmastr.arcana.item.custom.wand.MagicWandItem;
import net.sigmastr.arcana.item.custom.wand.NetherWandItem;

public class MagicSpellProjectileEntity extends ThrownItemEntity {
    private static Item wand;
    private static Item scroll;
    private final Entity player = this.getOwner();
    private final ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;

    public MagicSpellProjectileEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }
    public MagicSpellProjectileEntity(LivingEntity livingEntity, World world) {
        super(ArcanaEntities.MAGIC_SPELL_PROJECTILE, livingEntity, world);
        if (livingEntity.getStackInHand(livingEntity.getActiveHand()).getItem() == ArcanaItems.MAGIC_WAND) {
            wand = ArcanaItems.MAGIC_WAND;
        }
        else if (livingEntity.getStackInHand(livingEntity.getActiveHand()).getItem() == ArcanaItems.NETHER_WAND) {
            wand = ArcanaItems.NETHER_WAND;
        }
        else if (livingEntity.getStackInHand(livingEntity.getActiveHand()).getItem() == ArcanaItems.END_WAND) {
            wand = ArcanaItems.END_WAND;
        }
        else if (livingEntity.getStackInHand(livingEntity.getActiveHand()).getItem() == ArcanaItems.ELDER_WAND) {
            wand = ArcanaItems.ELDER_WAND;
        }
        scroll = livingEntity.getOffHandStack().getItem();
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.getWorld().isClient) {
            Vec3d velocity = this.getVelocity();
            double speed = velocity.length();
            Vec3d direction = new Vec3d(velocity.x / speed, velocity.y / speed, velocity.z / speed);

            this.setVelocity(direction.x * speed, direction.y * speed, direction.z * speed);
        }
    }

    @Override
    protected Item getDefaultItem() {
        return ArcanaItems.MAGIC_SPELL_PROJECTILE;
    }

    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }


    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        this.discard();
        if (!this.getWorld().isClient) {
            ServerWorld serverWorld = (ServerWorld) this.getWorld();
            BlockState hitBlockState = serverWorld.getBlockState(blockHitResult.getBlockPos());
            Block hitBlock = hitBlockState.getBlock();
            for (int i = 0; i < 5; ++i) {
                serverWorld.spawnParticles(ParticleTypes.FIREWORK, (double)getBlockPos().getX() + getWorld().random.nextDouble(), getBlockPos().getY() + getWorld().random.nextDouble(), (double)getBlockPos().getZ() + getWorld().random.nextDouble(), 1, 0.0, 0.0, 0.0, 1.0);
            }

            // [SPELLS]
            // BUILD BRIDGE (magic)
            // Greater version of the spell (builds 5x10 bridge)
            if ((wand == ArcanaItems.MAGIC_WAND || wand == ArcanaItems.ELDER_WAND) && scroll == ArcanaItems.BUILD_BRIDGE_SCROLL) {
                if (NaturalBlocks.isNatural(hitBlock)) {
                    String direction = wand == ArcanaItems.MAGIC_WAND ? MagicWandItem.getDirection() : ElderWandItem.getDirection();
                    int size = wand == ArcanaItems.MAGIC_WAND ? 10 : 20;
                    boolean sneaking = player.isInSneakingPose();
                    int sneakOffset = sneaking ? 2 : 1;
                    BridgeBuilder bridgeBuilder = new BridgeBuilder(serverWorld, getBlockPos().down(sneakOffset), direction, hitBlock, size, wand, sneaking); // Adjust length as needed
                    bridgeBuilder.startBuilding();

                    if (wand == ArcanaItems.MAGIC_WAND) {
                        MagicWandItem.setCast(true);
                        MagicWandItem.startCastCooldown(BuildBridgeScrollItem.getCOOLDOWN() * 2);
                    } else {
                        ElderWandItem.setCast(true);
                        ElderWandItem.startCastCooldown(BuildBridgeScrollItem.getCOOLDOWN() * 2);
                    }
                }
            }
            // Lesser version of the spell (builds 3x10 bridge)
            else if (wand != ArcanaItems.ELDER_WAND && scroll == ArcanaItems.BUILD_BRIDGE_SCROLL) {
                if (NaturalBlocks.isNatural(hitBlock)) {
                    String direction = wand == ArcanaItems.END_WAND ? EndWandItem.getDirection() : NetherWandItem.getDirection();
                    boolean sneaking = player.isInSneakingPose();
                    int sneakOffset = sneaking ? 2 : 1;
                    BridgeBuilder bridgeBuilder = new BridgeBuilder(serverWorld, getBlockPos().down(sneakOffset), direction, hitBlock, 10, wand, sneaking); // Adjust length as needed
                    bridgeBuilder.startBuilding();

                    if (wand == ArcanaItems.END_WAND) {
                        EndWandItem.setCast(true);
                        EndWandItem.startCastCooldown(BuildBridgeScrollItem.getCOOLDOWN() * 2);
                    } else {
                        NetherWandItem.setCast(true);
                        NetherWandItem.startCastCooldown(BuildBridgeScrollItem.getCOOLDOWN() * 2);
                    }
                }
            }


            // BUILD NETHER PORTAL (nether)
            // Greater version of the spell (builds a nether portal and lights it)
            if ((wand == ArcanaItems.NETHER_WAND || wand == ArcanaItems.ELDER_WAND) && scroll == ArcanaItems.BUILD_NETHER_PORTAL_SCROLL) {
                if (hitBlock != Blocks.AIR) {
                    String direction = wand == ArcanaItems.NETHER_WAND ? NetherWandItem.getDirection() : ElderWandItem.getDirection();
                    NetherPortalBuilder portalBuilder = new NetherPortalBuilder(serverWorld, getBlockPos(), direction, true);
                    portalBuilder.startBuilding();

                    if (wand == ArcanaItems.NETHER_WAND) {
                        NetherWandItem.setCast(true);
                        NetherWandItem.startCastCooldown(BuildNetherPortalScrollItem.getCOOLDOWN() * 2);
                    } else {
                        ElderWandItem.setCast(true);
                        ElderWandItem.startCastCooldown(BuildNetherPortalScrollItem.getCOOLDOWN() * 2);
                    }
                }
            }
            // Lesser version of the spell (builds a nether portal)
            else if (wand != ArcanaItems.ELDER_WAND && scroll == ArcanaItems.BUILD_NETHER_PORTAL_SCROLL) {
                if (hitBlock != Blocks.AIR) {
                    String direction = wand == ArcanaItems.MAGIC_WAND ? MagicWandItem.getDirection() : EndWandItem.getDirection();
                    NetherPortalBuilder portalBuilder = new NetherPortalBuilder(serverWorld, getBlockPos(), direction, false);
                    portalBuilder.startBuilding();

                    if (wand == ArcanaItems.MAGIC_WAND) {
                        MagicWandItem.setCast(true);
                        MagicWandItem.startCastCooldown(BuildNetherPortalScrollItem.getCOOLDOWN() * 2);
                    } else {
                        EndWandItem.setCast(true);
                        EndWandItem.startCastCooldown(BuildNetherPortalScrollItem.getCOOLDOWN() * 2);
                    }
                }
            }


            // BUILD PILLAR (magic)
            // Greater version of the spell (builds 5x5x20 pillar)
            if ((wand == ArcanaItems.MAGIC_WAND || wand == ArcanaItems.ELDER_WAND) && scroll == ArcanaItems.BUILD_PILLAR_SCROLL) {
                if (NaturalBlocks.isNatural(hitBlock)) {
                    String direction = wand == ArcanaItems.MAGIC_WAND ? MagicWandItem.getDirection() : ElderWandItem.getDirection();
                    int size = wand == ArcanaItems.MAGIC_WAND ? 10 : 20;
                    PillarBuilder pillarBuilder = new PillarBuilder(serverWorld, getBlockPos().down(1), direction, hitBlock, size, wand);
                    pillarBuilder.startBuilding();

                    if (wand == ArcanaItems.MAGIC_WAND) {
                        MagicWandItem.setCast(true);
                        MagicWandItem.startCastCooldown(BuildPillarScrollItem.getCOOLDOWN() * 2);
                    } else {
                        ElderWandItem.setCast(true);
                        ElderWandItem.startCastCooldown(BuildPillarScrollItem.getCOOLDOWN() * 2);
                    }
                }
            }
            // Lesser version of the spell (builds 3x3x10 pillar)
            else if (wand != ArcanaItems.ELDER_WAND && scroll == ArcanaItems.BUILD_PILLAR_SCROLL) {
                if (NaturalBlocks.isNatural(hitBlock)) {
                    String direction = wand == ArcanaItems.END_WAND ? EndWandItem.getDirection() : NetherWandItem.getDirection();
                    PillarBuilder pillarBuilder = new PillarBuilder(serverWorld, getBlockPos().down(1), direction, hitBlock, 10, wand);
                    pillarBuilder.startBuilding();

                    if (wand == ArcanaItems.END_WAND) {
                        EndWandItem.setCast(true);
                        EndWandItem.startCastCooldown(BuildPillarScrollItem.getCOOLDOWN() * 2);
                    } else if (wand == ArcanaItems.NETHER_WAND) {
                        NetherWandItem.setCast(true);
                        NetherWandItem.startCastCooldown(BuildPillarScrollItem.getCOOLDOWN() * 2);
                    }
                }
            }


            // BUILD PLATFORM (magic)
            // Greater version of the spell (builds 10x10 platform)
            if ((wand == ArcanaItems.MAGIC_WAND || wand == ArcanaItems.ELDER_WAND) && scroll == ArcanaItems.BUILD_PLATFORM_SCROLL) {
                if (NaturalBlocks.isNatural(hitBlock)) {
                    String direction = wand == ArcanaItems.MAGIC_WAND ? MagicWandItem.getDirection() : ElderWandItem.getDirection();
                    int size = wand == ArcanaItems.MAGIC_WAND ? 10 : 20;
                    PlatformBuilder platformBuilder = new PlatformBuilder(serverWorld, getBlockPos(), hitBlock, size);
                    platformBuilder.startBuilding();

                    if (wand == ArcanaItems.MAGIC_WAND) {
                        MagicWandItem.setCast(true);
                        MagicWandItem.startCastCooldown(BuildPlatformScrollItem.getCOOLDOWN() * 2);
                    } else {
                        ElderWandItem.setCast(true);
                        ElderWandItem.startCastCooldown(BuildPlatformScrollItem.getCOOLDOWN() * 2);
                    }
                }
            }
            // Lesser version of the spell (builds 5x5 platform)
            else if (wand != ArcanaItems.ELDER_WAND && scroll == ArcanaItems.BUILD_PLATFORM_SCROLL) {
                if (NaturalBlocks.isNatural(hitBlock)) {
                    String direction = wand == ArcanaItems.END_WAND ? EndWandItem.getDirection() : NetherWandItem.getDirection();
                    PlatformBuilder platformBuilder = new PlatformBuilder(serverWorld, getBlockPos(), hitBlock, 5);
                    platformBuilder.startBuilding();

                    if (wand == ArcanaItems.END_WAND) {
                        EndWandItem.setCast(true);
                        EndWandItem.startCastCooldown(BuildPlatformScrollItem.getCOOLDOWN() * 2);
                    } else if (wand == ArcanaItems.NETHER_WAND) {
                        NetherWandItem.setCast(true);
                        NetherWandItem.startCastCooldown(BuildPlatformScrollItem.getCOOLDOWN() * 2);
                    }
                }
            }


            // BUILD STAIRS (magic)
            // Greater version of the spell (builds 3x10/20 stairs)
            if ((wand == ArcanaItems.MAGIC_WAND || wand == ArcanaItems.ELDER_WAND) && scroll == ArcanaItems.BUILD_STAIRS_SCROLL) {
                if (NaturalBlocks.isNatural(hitBlock)) {
                    String direction = wand == ArcanaItems.MAGIC_WAND ? MagicWandItem.getDirection() : ElderWandItem.getDirection();
                    int size = wand == ArcanaItems.MAGIC_WAND ? 10 : 20;
                    boolean sneaking = player.isInSneakingPose();
                    Block targetBlock = NaturalBlocks.isStairs(hitBlock) ? hitBlock : NaturalBlocks.getStairsWithBlock(hitBlock);
                    StairsBuilder stairsBuilder = new StairsBuilder(serverWorld, getBlockPos().down(2), direction, targetBlock, size, wand, sneaking); // Adjust length as needed
                    stairsBuilder.startBuilding();

                    if (wand == ArcanaItems.MAGIC_WAND) {
                        MagicWandItem.setCast(true);
                        MagicWandItem.startCastCooldown(BuildStairsScrollItem.getCOOLDOWN() * 2);
                    } else {
                        ElderWandItem.setCast(true);
                        ElderWandItem.startCastCooldown(BuildStairsScrollItem.getCOOLDOWN() * 2);
                    }
                }
            }
            // Lesser version of the spell (builds 1x10 stairs)
            else if (wand != ArcanaItems.ELDER_WAND && scroll == ArcanaItems.BUILD_STAIRS_SCROLL) {
                if (NaturalBlocks.isNatural(hitBlock)) {
                    String direction = wand == ArcanaItems.END_WAND ? EndWandItem.getDirection() : NetherWandItem.getDirection();
                    boolean sneaking = player.isInSneakingPose();
                    Block targetBlock = NaturalBlocks.isStairs(hitBlock) ? hitBlock : NaturalBlocks.getStairsWithBlock(hitBlock);
                    StairsBuilder stairsBuilder = new StairsBuilder(serverWorld, getBlockPos().down(2), direction, targetBlock, 10, wand, sneaking); // Adjust length as needed
                    stairsBuilder.startBuilding();

                    if (wand == ArcanaItems.END_WAND) {
                        EndWandItem.setCast(true);
                        EndWandItem.startCastCooldown(BuildStairsScrollItem.getCOOLDOWN() * 2);
                    } else if (wand == ArcanaItems.NETHER_WAND) {
                        NetherWandItem.setCast(true);
                        NetherWandItem.startCastCooldown(BuildStairsScrollItem.getCOOLDOWN() * 2);
                    }
                }
            }


            // BUILD WALL (magic)
            // Greater version of the spell (builds 5x10 wall)
            if ((wand == ArcanaItems.MAGIC_WAND || wand == ArcanaItems.ELDER_WAND) && scroll == ArcanaItems.BUILD_WALL_SCROLL) {
                if (NaturalBlocks.isNatural(hitBlock)) {
                    String direction = wand == ArcanaItems.MAGIC_WAND ? MagicWandItem.getDirection() : ElderWandItem.getDirection();
                    int size = wand == ArcanaItems.MAGIC_WAND ? 5 : 10;
                    WallBuilder wallBuilder = new WallBuilder(serverWorld, getBlockPos(), direction, hitBlock, size);
                    wallBuilder.startBuilding();

                    if (wand == ArcanaItems.MAGIC_WAND) {
                        MagicWandItem.setCast(true);
                        MagicWandItem.startCastCooldown(BuildWallScrollItem.getCOOLDOWN() * 2);
                    } else {
                        ElderWandItem.setCast(true);
                        ElderWandItem.startCastCooldown(BuildWallScrollItem.getCOOLDOWN() * 2);
                    }
                }
            }
            // Lesser version of the spell (builds 3x6 wall)
            else if (wand != ArcanaItems.ELDER_WAND && scroll == ArcanaItems.BUILD_WALL_SCROLL) {
                if (NaturalBlocks.isNatural(hitBlock)) {
                    String direction = wand == ArcanaItems.END_WAND ? EndWandItem.getDirection() : NetherWandItem.getDirection();
                    WallBuilder wallBuilder = new WallBuilder(serverWorld, getBlockPos(), direction, hitBlock, 3);
                    wallBuilder.startBuilding();

                    if (wand == ArcanaItems.END_WAND) {
                        EndWandItem.setCast(true);
                        EndWandItem.startCastCooldown(BuildWallScrollItem.getCOOLDOWN() * 2);
                    } else if (wand == ArcanaItems.NETHER_WAND) {
                        NetherWandItem.setCast(true);
                        NetherWandItem.startCastCooldown(BuildWallScrollItem.getCOOLDOWN() * 2);
                    }
                }
            }
        }
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient) {
            ServerWorld serverWorld = (ServerWorld) this.getWorld();
            BlockState hitBlockState = serverWorld.getBlockState(getBlockPos());
            Block hitBlock = hitBlockState.getBlock();
            for (int i = 0; i < 5; ++i) {
                serverWorld.spawnParticles(ParticleTypes.FIREWORK, (double) getBlockPos().getX() + getWorld().random.nextDouble(), getBlockPos().getY() + getWorld().random.nextDouble(), (double) getBlockPos().getZ() + getWorld().random.nextDouble(), 1, 0.0, 0.0, 0.0, 1.0);
            }

            // [SPELLS]
            // [CAST SPELLS]
            // CAST ANNIHILATION (elder)
            // Greater version of the spell (Instantly kills all entities in a 10 block radius)
            if ((wand == ArcanaItems.ELDER_WAND) && scroll == ArcanaItems.CAST_ANNIHILATION_SCROLL) {
                // This condition checks for a non-air block or an entity hit
                if ((hitResult.getType() == HitResult.Type.BLOCK && serverWorld.getBlockState(((BlockHitResult) hitResult).getBlockPos()).getBlock() != Blocks.AIR) ||
                        hitResult.getType() == HitResult.Type.ENTITY) {
                    for (Entity entity : serverWorld.getEntitiesByClass(Entity.class, getBoundingBox().expand(10.0D), entity -> entity != this)) {
                        if (entity instanceof LivingEntity && entity != player) {
                            entity.kill();
                        }
                    }
                }

                ElderWandItem.setCast(true);
                ElderWandItem.startCastCooldown(CastAnnihilationScrollItem.getCOOLDOWN() * 2);
            }
            // Lesser version of the spell (Instantly kills the target entity)
            else if (wand != ArcanaItems.ELDER_WAND && scroll == ArcanaItems.CAST_ANNIHILATION_SCROLL) {
                if (hitResult instanceof EntityHitResult entityHitResult) {
                    Entity target = entityHitResult.getEntity();
                    if (target instanceof LivingEntity && target != player) {
                        target.kill();
                    }
                }

                if (wand == ArcanaItems.END_WAND) {
                    EndWandItem.setCast(true);
                    EndWandItem.startCastCooldown(CastAnnihilationScrollItem.getCOOLDOWN() * 2);
                } else if (wand == ArcanaItems.NETHER_WAND) {
                    NetherWandItem.setCast(true);
                    NetherWandItem.startCastCooldown(CastAnnihilationScrollItem.getCOOLDOWN() * 2);
                }
            }


            // CAST AURA (magic)
            // Greater version of the spell (Gives the entities near the targeted block have extra hearts and resistance for 30 seconds)
            if ((wand == ArcanaItems.MAGIC_WAND || wand == ArcanaItems.ELDER_WAND) && scroll == ArcanaItems.CAST_AURA_SCROLL) {
                if (hitResult.getType() == HitResult.Type.BLOCK && serverWorld.getBlockState(((BlockHitResult) hitResult).getBlockPos()).getBlock() != Blocks.AIR) {
                    BlockPos hitPos = ((BlockHitResult) hitResult).getBlockPos();
                    int size = wand == ArcanaItems.MAGIC_WAND ? 1 : 2;
                    for (Entity entity : serverWorld.getEntitiesByClass(Entity.class, getBoundingBox().expand(3.0D), entity -> entity != this)) {
                        if (entity instanceof LivingEntity) {
                            ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, size));
                            ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 600, 2* size));
                            serverWorld.playSound(null, hitPos, SoundEvents.BLOCK_BEACON_ACTIVATE, serverPlayer.getSoundCategory(), 1.0F, 1.0F);
                        }
                    }
                }

                if (wand == ArcanaItems.MAGIC_WAND) {
                    MagicWandItem.setCast(true);
                    MagicWandItem.startCastCooldown(CastAuraScrollItem.getCOOLDOWN() * 2);
                } else {
                    ElderWandItem.setCast(true);
                    ElderWandItem.startCastCooldown(CastAuraScrollItem.getCOOLDOWN() * 2);
                }
            }


            // CAST BLOOM (magic)
            // Greater version of the spell (plants a tree or grows a 5x5 area)
            if ((wand == ArcanaItems.MAGIC_WAND || wand == ArcanaItems.ELDER_WAND) && scroll == ArcanaItems.CAST_BLOOM_SCROLL) {
                if ((hitResult.getType() == HitResult.Type.BLOCK && serverWorld.getBlockState(((BlockHitResult) hitResult).getBlockPos()).getBlock() != Blocks.AIR) ||
                        hitResult.getType() != HitResult.Type.ENTITY) {

                    int size = wand == ArcanaItems.MAGIC_WAND ? 2 : 5;
                    BlockPos hitPos = ((BlockHitResult) hitResult).getBlockPos();
                    BloomBuilder bloomBuilder = new BloomBuilder(serverWorld, hitPos, size); // Default radius for 5x5 area

                    if (player.isSneaking()) {
                        // Plant and grow a tree at the target location
                        bloomBuilder.plantAndGrowTree();
                    } else {
                        // Grow the area when sneaking
                        bloomBuilder.applyGrowth();
                    }

                    // Cooldown logic
                    if (wand == ArcanaItems.MAGIC_WAND) {
                        MagicWandItem.setCast(true);
                        MagicWandItem.startCastCooldown(CastBloomScrollItem.getCOOLDOWN() * 2);
                    } else {
                        ElderWandItem.setCast(true);
                        ElderWandItem.startCastCooldown(CastBloomScrollItem.getCOOLDOWN() * 2);
                    }
                }
            }

            // Lesser version of the spell (plants a tree grows a 3x3 area)
            else if (wand != ArcanaItems.ELDER_WAND && scroll == ArcanaItems.CAST_BLOOM_SCROLL) {
                if ((hitResult.getType() == HitResult.Type.BLOCK && serverWorld.getBlockState(((BlockHitResult) hitResult).getBlockPos()).getBlock() != Blocks.AIR) ||
                        hitResult.getType() != HitResult.Type.ENTITY) {

                    BlockPos hitPos = ((BlockHitResult) hitResult).getBlockPos();
                    BloomBuilder bloomBuilder = new BloomBuilder(serverWorld, hitPos, 1);

                    if (player.isSneaking()) {
                        // Plant and grow a tree at the target location
                        bloomBuilder.plantAndGrowTree();
                    } else {
                        // Grow the area when sneaking
                        bloomBuilder.applyGrowth();
                    }

                    if (wand == ArcanaItems.END_WAND) {
                        EndWandItem.setCast(true);
                        EndWandItem.startCastCooldown(CastBloomScrollItem.getCOOLDOWN() * 2);
                    } else if (wand == ArcanaItems.NETHER_WAND) {
                        NetherWandItem.setCast(true);
                        NetherWandItem.startCastCooldown(CastBloomScrollItem.getCOOLDOWN() * 2);
                    }
                }
            }

            // CAST CLARITY (magic)
            // Greater version of the spell (Removes all negative effects from the entities near the targeted block and gives them regeneration for 5 seconds)
            if ((wand == ArcanaItems.MAGIC_WAND || wand == ArcanaItems.ELDER_WAND) && scroll == ArcanaItems.CAST_CLARITY_SCROLL) {
                if (hitResult.getType() == HitResult.Type.BLOCK && serverWorld.getBlockState(((BlockHitResult) hitResult).getBlockPos()).getBlock() != Blocks.AIR) {
                    BlockPos hitPos = ((BlockHitResult) hitResult).getBlockPos();
                    int size = wand == ArcanaItems.MAGIC_WAND ? 1 : 2;
                    for (Entity entity : serverWorld.getEntitiesByClass(Entity.class, getBoundingBox().expand(3.0D), entity -> entity != this)) {
                        if (entity instanceof LivingEntity) {
                            ((LivingEntity) entity).clearStatusEffects();
                            ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, size));
                            serverWorld.playSound(null, hitPos, SoundEvents.BLOCK_BEACON_ACTIVATE, serverPlayer.getSoundCategory(), 1.0F, 1.0F);
                        }
                    }
                }

                if (wand == ArcanaItems.MAGIC_WAND) {
                    MagicWandItem.setCast(true);
                    MagicWandItem.startCastCooldown(CastClarityScrollItem.getCOOLDOWN() * 2);
                } else {
                    ElderWandItem.setCast(true);
                    ElderWandItem.startCastCooldown(CastClarityScrollItem.getCOOLDOWN() * 2);
                }
            }
            // Lesser version of the spell (Removes all negative effects from the target entity)
            else if (wand != ArcanaItems.ELDER_WAND && scroll == ArcanaItems.CAST_CLARITY_SCROLL) {
                if (hitResult instanceof EntityHitResult entityHitResult) {
                    Entity target = entityHitResult.getEntity();
                    if (target instanceof LivingEntity) {
                        ((LivingEntity) target).clearStatusEffects();
                        serverWorld.playSound(null, target.getBlockPos(), SoundEvents.BLOCK_BEACON_ACTIVATE, serverPlayer.getSoundCategory(), 1.0F, 1.0F);
                    }
                }

                if (wand == ArcanaItems.END_WAND) {
                    EndWandItem.setCast(true);
                    EndWandItem.startCastCooldown(CastClarityScrollItem.getCOOLDOWN() * 2);
                } else if (wand == ArcanaItems.NETHER_WAND) {
                    NetherWandItem.setCast(true);
                    NetherWandItem.startCastCooldown(CastClarityScrollItem.getCOOLDOWN() * 2);
                }
            }

            // CAST FLOOD (magic)
            // Greater version of the spell (floods a 10x10 area)
            if ((wand == ArcanaItems.MAGIC_WAND || wand == ArcanaItems.ELDER_WAND) && scroll == ArcanaItems.CAST_FLOOD_SCROLL) {
                if ((hitResult.getType() == HitResult.Type.BLOCK && serverWorld.getBlockState(((BlockHitResult) hitResult).getBlockPos()).getBlock() != Blocks.AIR) ||
                        hitResult.getType() == HitResult.Type.ENTITY) {
                    int size = wand == ArcanaItems.MAGIC_WAND ? 5 : 10;
                    FloodBuilder floodBuilder = new FloodBuilder(serverWorld, getBlockPos(), size);
                    floodBuilder.startBuilding();

                    if (wand == ArcanaItems.MAGIC_WAND) {
                        MagicWandItem.setCast(true);
                        MagicWandItem.startCastCooldown(CastFloodScrollItem.getCOOLDOWN() * 2);
                    } else {
                        ElderWandItem.setCast(true);
                        ElderWandItem.startCastCooldown(CastFloodScrollItem.getCOOLDOWN() * 2);
                    }
                }
            }
            // Lesser version of the spell (floods a 5x5 area)
            else if (wand != ArcanaItems.ELDER_WAND && scroll == ArcanaItems.CAST_FLOOD_SCROLL) {
                if ((hitResult.getType() == HitResult.Type.BLOCK && serverWorld.getBlockState(((BlockHitResult) hitResult).getBlockPos()).getBlock() != Blocks.AIR) ||
                        hitResult.getType() == HitResult.Type.ENTITY) {
                    FloodBuilder floodBuilder = new FloodBuilder(serverWorld, getBlockPos(), 1);
                    floodBuilder.startBuilding();

                    if (wand == ArcanaItems.END_WAND) {
                        EndWandItem.setCast(true);
                        EndWandItem.startCastCooldown(CastFloodScrollItem.getCOOLDOWN() * 2);
                    } else if (wand == ArcanaItems.NETHER_WAND) {
                        NetherWandItem.setCast(true);
                        NetherWandItem.startCastCooldown(CastFloodScrollItem.getCOOLDOWN() * 2);
                    }
                }
            }


            // CAST HARDENING (magic)
            // Greater version of the spell (hardens a 10x10 area (water to ice, lava to obsidian))
            if ((wand == ArcanaItems.MAGIC_WAND || wand == ArcanaItems.ELDER_WAND) && scroll == ArcanaItems.CAST_HARDENING_SCROLL) {
                if ((hitResult.getType() == HitResult.Type.BLOCK && serverWorld.getBlockState(((BlockHitResult) hitResult).getBlockPos()).getBlock() != Blocks.AIR) ||
                        hitResult.getType() != HitResult.Type.ENTITY) {
                    int size = wand == ArcanaItems.MAGIC_WAND ? 5 : 10;
                    BlockPos hitPos = ((BlockHitResult) hitResult).getBlockPos();
                    HardeningBuilder hardeningBuilder = new HardeningBuilder(serverWorld, hitPos, size);
                    hardeningBuilder.startBuilding();

                    if (wand == ArcanaItems.MAGIC_WAND) {
                        MagicWandItem.setCast(true);
                        MagicWandItem.startCastCooldown(CastHardeningScrollItem.getCOOLDOWN() * 2);
                    } else {
                        ElderWandItem.setCast(true);
                        ElderWandItem.startCastCooldown(CastHardeningScrollItem.getCOOLDOWN() * 2);
                    }
                }
            }
            // Lesser version of the spell (hardens a 5x5 area (water to ice, lava to obsidian))
            else if (wand != ArcanaItems.ELDER_WAND && scroll == ArcanaItems.CAST_HARDENING_SCROLL) {
                if ((hitResult.getType() == HitResult.Type.BLOCK && serverWorld.getBlockState(((BlockHitResult) hitResult).getBlockPos()).getBlock() != Blocks.AIR) ||
                        hitResult.getType() != HitResult.Type.ENTITY) {
                    BlockPos hitPos = ((BlockHitResult) hitResult).getBlockPos();
                    HardeningBuilder hardeningBuilder = new HardeningBuilder(serverWorld, hitPos, 3);
                    hardeningBuilder.startBuilding();

                    if (wand == ArcanaItems.END_WAND) {
                        EndWandItem.setCast(true);
                        EndWandItem.startCastCooldown(CastHardeningScrollItem.getCOOLDOWN() * 2);
                    } else if (wand == ArcanaItems.NETHER_WAND) {
                        NetherWandItem.setCast(true);
                        NetherWandItem.startCastCooldown(CastHardeningScrollItem.getCOOLDOWN() * 2);
                    }
                }
            }


            // CAST VACUUM (end)
            // Greater version of the spell (drains water/lava in a 10x10 area)
            if ((wand == ArcanaItems.END_WAND || wand == ArcanaItems.ELDER_WAND) && scroll == ArcanaItems.CAST_VACUUM_SCROLL) {
                if ((hitResult.getType() == HitResult.Type.BLOCK && serverWorld.getBlockState(((BlockHitResult) hitResult).getBlockPos()).getBlock() != Blocks.AIR) ||
                        hitResult.getType() != HitResult.Type.ENTITY) {
                    int size = wand == ArcanaItems.END_WAND ? 5 : 10;
                    BlockPos hitPos = ((BlockHitResult) hitResult).getBlockPos();
                    VacuumBuilder vacuumBuilder = new VacuumBuilder(serverWorld, hitPos, size);
                    vacuumBuilder.startBuilding();

                    if (wand == ArcanaItems.END_WAND) {
                        EndWandItem.setCast(true);
                        EndWandItem.startCastCooldown(CastVacuumScrollItem.getCOOLDOWN() * 2);
                    } else {
                        ElderWandItem.setCast(true);
                        ElderWandItem.startCastCooldown(CastVacuumScrollItem.getCOOLDOWN() * 2);
                    }
                }
            }
            // Lesser version of the spell (drains water/lava in a 5x5 area)
            else if (wand != ArcanaItems.ELDER_WAND && scroll == ArcanaItems.CAST_VACUUM_SCROLL) {
                if ((hitResult.getType() == HitResult.Type.BLOCK && serverWorld.getBlockState(((BlockHitResult) hitResult).getBlockPos()).getBlock() != Blocks.AIR) ||
                        hitResult.getType() != HitResult.Type.ENTITY) {
                    BlockPos hitPos = ((BlockHitResult) hitResult).getBlockPos();
                    VacuumBuilder vacuumBuilder = new VacuumBuilder(serverWorld, hitPos, 3);
                    vacuumBuilder.startBuilding();

                    if (wand == ArcanaItems.MAGIC_WAND) {
                        MagicWandItem.setCast(true);
                        MagicWandItem.startCastCooldown(CastVacuumScrollItem.getCOOLDOWN() * 2);
                    } else if (wand == ArcanaItems.NETHER_WAND) {
                        NetherWandItem.setCast(true);
                        NetherWandItem.startCastCooldown(CastVacuumScrollItem.getCOOLDOWN() * 2);
                    }
                }
            }


            // CAST WARP (end)
            // Only version of the spell (teleports the player to the projectile's location) very long cooldown and high cost for non-end wands
            if ((wand == ArcanaItems.END_WAND || wand == ArcanaItems.ELDER_WAND || wand == ArcanaItems.MAGIC_WAND || wand == ArcanaItems.NETHER_WAND) && scroll == ArcanaItems.CAST_WARP_SCROLL) {
                for (int i = 0; i < 32; ++i) {
                    this.getWorld().addParticle(ParticleTypes.PORTAL, this.getX(), this.getY() + this.random.nextDouble() * 2.0, this.getZ(), this.random.nextGaussian(), 0.0, this.random.nextGaussian());
                }

                //player.sendMessage(Text.of("Teleporting...1"));
                if (player instanceof ServerPlayerEntity serverPlayerEntity) {
                    if (serverPlayerEntity.networkHandler.isConnectionOpen() && serverPlayerEntity.getWorld() == this.getWorld() && !serverPlayerEntity.isSleeping()) {
                        if (player.hasVehicle()) {
                            serverPlayerEntity.requestTeleportAndDismount(this.getX(), this.getY(), this.getZ());
                        } else {
                            player.requestTeleport(this.getX(), this.getY(), this.getZ());
                        }
                        player.onLanding();
                        player.damage(this.getDamageSources().fall(), 5.0f);
                    }
                } else if (player != null) {
                    player.requestTeleport(this.getX(), this.getY(), this.getZ());
                    player.onLanding();
                }
                this.discard();

                if (wand == ArcanaItems.END_WAND){
                    EndWandItem.setCast(true);
                    EndWandItem.startCastCooldown(CastWarpScrollItem.getCOOLDOWN() * 2);
                }
                else if (wand == ArcanaItems.ELDER_WAND){
                    ElderWandItem.setCast(true);
                    ElderWandItem.startCastCooldown(CastWarpScrollItem.getCOOLDOWN() * 2);
                }
                else if (wand == ArcanaItems.MAGIC_WAND){
                    MagicWandItem.setCast(true);
                    MagicWandItem.startCastCooldown(CastWarpScrollItem.getCOOLDOWN() * 2);
                }
                else if (wand == ArcanaItems.NETHER_WAND){
                    NetherWandItem.setCast(true);
                    NetherWandItem.startCastCooldown(CastWarpScrollItem.getCOOLDOWN() * 2);
                }
            }


            // CAST WITHERING (elder)
            // Greater version of the spell (gives the entities near the targeted block wither effect for 10 seconds and heals the player for what they deal)
            if ((wand == ArcanaItems.ELDER_WAND) && scroll == ArcanaItems.CAST_WITHERING_SCROLL) {
                if (hitResult.getType() == HitResult.Type.BLOCK && serverWorld.getBlockState(((BlockHitResult) hitResult).getBlockPos()).getBlock() != Blocks.AIR) {
                    BlockPos hitPos = ((BlockHitResult) hitResult).getBlockPos();
                    for (Entity entity : serverWorld.getEntitiesByClass(Entity.class, getBoundingBox().expand(3.0D), entity -> entity != this)) {
                        if (entity instanceof LivingEntity) {
                            ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 200, 2));
                            serverWorld.playSound(null, hitPos, SoundEvents.BLOCK_BEACON_ACTIVATE, serverPlayer.getSoundCategory(), 1.0F, 1.0F);
                        }
                    }
                    ((LivingEntity) player).addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 2));
                }

                ElderWandItem.setCast(true);
                ElderWandItem.startCastCooldown(CastWitheringScrollItem.getCOOLDOWN() * 2);
            }
            // Lesser version of the spell (gives the target entity wither effect for 5 seconds)
            else if (wand != ArcanaItems.ELDER_WAND && scroll == ArcanaItems.CAST_WITHERING_SCROLL) {
                if (hitResult instanceof EntityHitResult entityHitResult) {
                    Entity target = entityHitResult.getEntity();
                    if (target instanceof LivingEntity) {
                        ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 100, 1));
                        serverWorld.playSound(null, target.getBlockPos(), SoundEvents.BLOCK_BEACON_ACTIVATE, serverPlayer.getSoundCategory(), 1.0F, 1.0F);
                    }
                }

                if (wand == ArcanaItems.MAGIC_WAND) {
                    MagicWandItem.setCast(true);
                    MagicWandItem.startCastCooldown(CastWitheringScrollItem.getCOOLDOWN() * 2);
                } else if (wand == ArcanaItems.END_WAND) {
                    EndWandItem.setCast(true);
                    EndWandItem.startCastCooldown(CastWitheringScrollItem.getCOOLDOWN() * 2);
                } else if (wand == ArcanaItems.NETHER_WAND) {
                    NetherWandItem.setCast(true);
                    NetherWandItem.startCastCooldown(CastWitheringScrollItem.getCOOLDOWN() * 2);
                }
            }


            // CAST WRATH (nether)
            // Greater version of the spell (casts 3-5 lightning bolts)
            if ((wand == ArcanaItems.NETHER_WAND || wand == ArcanaItems.ELDER_WAND) && scroll == ArcanaItems.CAST_WRATH_SCROLL) {
                int size = wand == ArcanaItems.NETHER_WAND ? 3 : 5;
                for (int i = 0; i < size; ++i) {
                    LightningEntity lightningEntity = EntityType.LIGHTNING_BOLT.create(serverWorld);
                    lightningEntity.refreshPositionAndAngles((double) getBlockPos().getX() + getWorld().random.nextDouble(), getBlockPos().getY(), (double) getBlockPos().getZ() + getWorld().random.nextDouble(), 0.0F, 0.0F);
                    serverWorld.spawnEntity(lightningEntity);
                }

                if (wand == ArcanaItems.NETHER_WAND){
                    NetherWandItem.setCast(true);
                    NetherWandItem.startCastCooldown(CastWrathScrollItem.getCOOLDOWN() * 2);
                }
                else if (wand == ArcanaItems.ELDER_WAND){
                    ElderWandItem.setCast(true);
                    ElderWandItem.startCastCooldown(CastWrathScrollItem.getCOOLDOWN() * 2);
                }
            }
            // Lesser version of the spell (casts 1-2 lightning bolt)
            else if (wand != ArcanaItems.ELDER_WAND && scroll == ArcanaItems.CAST_WRATH_SCROLL) {
                int size = wand == ArcanaItems.END_WAND ? 1 : 2;
                for (int i = 0; i < size; ++i) {
                    LightningEntity lightningEntity = EntityType.LIGHTNING_BOLT.create(serverWorld);
                    lightningEntity.refreshPositionAndAngles((double) getBlockPos().getX() + getWorld().random.nextDouble(), getBlockPos().getY(), (double) getBlockPos().getZ() + getWorld().random.nextDouble(), 0.0F, 0.0F);
                    serverWorld.spawnEntity(lightningEntity);
                }

                if (wand == ArcanaItems.END_WAND){
                    EndWandItem.setCast(true);
                    EndWandItem.startCastCooldown(CastWrathScrollItem.getCOOLDOWN() * 2);
                }
                else if (wand == ArcanaItems.MAGIC_WAND){
                    MagicWandItem.setCast(true);
                    MagicWandItem.startCastCooldown(CastWrathScrollItem.getCOOLDOWN() * 2);
                }

            }


            // [INVOKE SPELLS]
            // INVOKE BLAZE (nether)
            // Greater version of the spell (invokes 3 blazes)
            if ((wand == ArcanaItems.NETHER_WAND || wand == ArcanaItems.ELDER_WAND) && scroll == ArcanaItems.INVOKE_BLAZE_SCROLL) {
                int size = wand == ArcanaItems.NETHER_WAND ? 3 : 5;
                for (int i = 0; i < size; ++i) {
                    BlazeEntity blaze = EntityType.BLAZE.create(serverWorld);
                    blaze.refreshPositionAndAngles((double) getBlockPos().getX() + getWorld().random.nextDouble(), getBlockPos().getY(), (double) getBlockPos().getZ() + getWorld().random.nextDouble(), 0.0F, 0.0F);
                    serverWorld.spawnEntity(blaze);
                }

                if (wand == ArcanaItems.NETHER_WAND){
                    NetherWandItem.setCast(true);
                    NetherWandItem.startCastCooldown(InvokeBlazeScrollItem.getCOOLDOWN() * 2);
                }
                else if (wand == ArcanaItems.ELDER_WAND){
                    ElderWandItem.setCast(true);
                    ElderWandItem.startCastCooldown(InvokeBlazeScrollItem.getCOOLDOWN() * 2);
                }
            }
            // Lesser version of the spell (invokes 1 blaze)
            else if (wand != ArcanaItems.ELDER_WAND && scroll == ArcanaItems.INVOKE_BLAZE_SCROLL) {
                BlazeEntity blaze = EntityType.BLAZE.create(serverWorld);
                blaze.refreshPositionAndAngles((double) getBlockPos().getX() + getWorld().random.nextDouble(), getBlockPos().getY(), (double) getBlockPos().getZ() + getWorld().random.nextDouble(), 0.0F, 0.0F);
                serverWorld.spawnEntity(blaze);

                if (wand == ArcanaItems.END_WAND){
                    EndWandItem.setCast(true);
                    EndWandItem.startCastCooldown(InvokeBlazeScrollItem.getCOOLDOWN() * 2);
                }
                else if (wand == ArcanaItems.MAGIC_WAND){
                    MagicWandItem.setCast(true);
                    MagicWandItem.startCastCooldown(InvokeBlazeScrollItem.getCOOLDOWN() * 2);
                }
            }

            // INVOKE GHAST (nether)
            // Greater version of the spell (invokes 2-3 ghasts)
            if ((wand == ArcanaItems.NETHER_WAND || wand == ArcanaItems.ELDER_WAND) && scroll == ArcanaItems.INVOKE_GHAST_SCROLL) {
                int size = wand == ArcanaItems.NETHER_WAND ? 2 : 3;
                for (int i = 0; i < size; ++i) {
                    GhastEntity ghast = EntityType.GHAST.create(serverWorld);
                    ghast.refreshPositionAndAngles((double) getBlockPos().getX() + getWorld().random.nextDouble(), getBlockPos().getY(), (double) getBlockPos().getZ() + getWorld().random.nextDouble(), 0.0F, 0.0F);
                    serverWorld.spawnEntity(ghast);
                }

                if (wand == ArcanaItems.NETHER_WAND){
                    NetherWandItem.setCast(true);
                    NetherWandItem.startCastCooldown(InvokeGhastScrollItem.getCOOLDOWN() * 2);
                }
                else if (wand == ArcanaItems.ELDER_WAND){
                    ElderWandItem.setCast(true);
                    ElderWandItem.startCastCooldown(InvokeGhastScrollItem.getCOOLDOWN() * 2);
                }
            }
            // Lesser version of the spell (invokes 1 ghast)
            else if (wand != ArcanaItems.ELDER_WAND && scroll == ArcanaItems.INVOKE_GHAST_SCROLL) {
                GhastEntity ghast = EntityType.GHAST.create(serverWorld);
                ghast.refreshPositionAndAngles((double) getBlockPos().getX() + getWorld().random.nextDouble(), getBlockPos().getY(), (double) getBlockPos().getZ() + getWorld().random.nextDouble(), 0.0F, 0.0F);
                serverWorld.spawnEntity(ghast);

                if (wand == ArcanaItems.END_WAND){
                    EndWandItem.setCast(true);
                    EndWandItem.startCastCooldown(InvokeGhastScrollItem.getCOOLDOWN() * 2);
                }
                else if (wand == ArcanaItems.MAGIC_WAND){
                    MagicWandItem.setCast(true);
                    MagicWandItem.startCastCooldown(InvokeGhastScrollItem.getCOOLDOWN() * 2);
                }
            }

            // INVOKE IRON GOLEM (magic)
            // Greater version of the spell (invokes 2 iron golems)
            if ((wand == ArcanaItems.MAGIC_WAND || wand == ArcanaItems.ELDER_WAND) && scroll == ArcanaItems.INVOKE_IRON_GOLEM_SCROLL) {
                int size = wand == ArcanaItems.MAGIC_WAND ? 2 : 3;
                for (int i = 0; i < size; ++i) {
                    IronGolemEntity ironGolem = EntityType.IRON_GOLEM.create(serverWorld);
                    ironGolem.refreshPositionAndAngles((double) getBlockPos().getX() + getWorld().random.nextDouble(), getBlockPos().getY(), (double) getBlockPos().getZ() + getWorld().random.nextDouble(), 0.0F, 0.0F);
                    serverWorld.spawnEntity(ironGolem);
                }

                if (wand == ArcanaItems.MAGIC_WAND){
                    MagicWandItem.setCast(true);
                    MagicWandItem.startCastCooldown(InvokeIronGolemScrollItem.getCOOLDOWN() * 2);
                }
                else if (wand == ArcanaItems.ELDER_WAND){
                    ElderWandItem.setCast(true);
                    ElderWandItem.startCastCooldown(InvokeIronGolemScrollItem.getCOOLDOWN() * 2);
                }
            }
            // Lesser version of the spell (invokes 1 iron golem)
            else if (wand != ArcanaItems.ELDER_WAND && scroll == ArcanaItems.INVOKE_IRON_GOLEM_SCROLL) {
                IronGolemEntity ironGolem = EntityType.IRON_GOLEM.create(serverWorld);
                ironGolem.refreshPositionAndAngles((double) getBlockPos().getX() + getWorld().random.nextDouble(), getBlockPos().getY(), (double) getBlockPos().getZ() + getWorld().random.nextDouble(), 0.0F, 0.0F);
                serverWorld.spawnEntity(ironGolem);

                if (wand == ArcanaItems.END_WAND){
                    EndWandItem.setCast(true);
                    EndWandItem.startCastCooldown(InvokeIronGolemScrollItem.getCOOLDOWN() * 2);
                }
                else if (wand == ArcanaItems.NETHER_WAND){
                    NetherWandItem.setCast(true);
                    NetherWandItem.startCastCooldown(InvokeIronGolemScrollItem.getCOOLDOWN() * 2);
                }
            }

            // INVOKE SNOW GOLEM (magic)
            // Greater version of the spell (invokes 5 snow golems)
            if ((wand == ArcanaItems.MAGIC_WAND || wand == ArcanaItems.ELDER_WAND) && scroll == ArcanaItems.INVOKE_SNOW_GOLEM_SCROLL) {
                int size = wand == ArcanaItems.MAGIC_WAND ? 5 : 7;
                for (int i = 0; i < size; ++i) {
                    SnowGolemEntity snowGolem = EntityType.SNOW_GOLEM.create(serverWorld);
                    snowGolem.refreshPositionAndAngles((double) getBlockPos().getX() + getWorld().random.nextDouble(), getBlockPos().getY(), (double) getBlockPos().getZ() + getWorld().random.nextDouble(), 0.0F, 0.0F);
                    serverWorld.spawnEntity(snowGolem);
                }

                if (wand == ArcanaItems.MAGIC_WAND){
                    MagicWandItem.setCast(true);
                    MagicWandItem.startCastCooldown(InvokeSnowGolemScrollItem.getCOOLDOWN() * 2);
                }
                else if (wand == ArcanaItems.ELDER_WAND){
                    ElderWandItem.setCast(true);
                    ElderWandItem.startCastCooldown(InvokeSnowGolemScrollItem.getCOOLDOWN() * 2);
                }
            }
            // Lesser version of the spell (invokes 3 snow golems)
            else if (wand != ArcanaItems.ELDER_WAND && scroll == ArcanaItems.INVOKE_SNOW_GOLEM_SCROLL) {
                for (int i = 0; i < 3; ++i) {
                    SnowGolemEntity snowGolem = EntityType.SNOW_GOLEM.create(serverWorld);
                    snowGolem.refreshPositionAndAngles((double) getBlockPos().getX() + getWorld().random.nextDouble(), getBlockPos().getY(), (double) getBlockPos().getZ() + getWorld().random.nextDouble(), 0.0F, 0.0F);
                    serverWorld.spawnEntity(snowGolem);
                }

                if (wand == ArcanaItems.END_WAND){
                    EndWandItem.setCast(true);
                    EndWandItem.startCastCooldown(InvokeSnowGolemScrollItem.getCOOLDOWN() * 2);
                }
                else if (wand == ArcanaItems.NETHER_WAND){
                    NetherWandItem.setCast(true);
                    NetherWandItem.startCastCooldown(InvokeSnowGolemScrollItem.getCOOLDOWN() * 2);
                }
            }

            // INVOKE UNDEAD (nether)
            // Greater version of the spell (invokes 2 zombie and 1 skeletons)
            if ((wand == ArcanaItems.NETHER_WAND || wand == ArcanaItems.ELDER_WAND) && scroll == ArcanaItems.INVOKE_UNDEAD_SCROLL) {
                int sSize = wand == ArcanaItems.NETHER_WAND ? 1 : 2;
                for (int i = 0; i < sSize; ++i) {
                    SkeletonEntity skeleton = EntityType.SKELETON.create(serverWorld);
                    skeleton.refreshPositionAndAngles((double) getBlockPos().getX() + getWorld().random.nextDouble(), getBlockPos().getY(), (double) getBlockPos().getZ() + getWorld().random.nextDouble(), 0.0F, 0.0F);
                    getWorld().spawnEntity(skeleton);
                }
                int zSize = wand == ArcanaItems.NETHER_WAND ? 2 : 3;
                for (int i = 0; i < zSize; ++i) {
                    ZombieEntity zombie = EntityType.ZOMBIE.create(serverWorld);
                    zombie.refreshPositionAndAngles((double) getBlockPos().getX() + getWorld().random.nextDouble(), getBlockPos().getY(), (double) getBlockPos().getZ() + getWorld().random.nextDouble(), 0.0F, 0.0F);
                    serverWorld.spawnEntity(zombie);
                }

                if (wand == ArcanaItems.NETHER_WAND){
                    NetherWandItem.setCast(true);
                    NetherWandItem.startCastCooldown(InvokeUndeadScrollItem.getCOOLDOWN() * 2);
                }
                else if (wand == ArcanaItems.ELDER_WAND){
                    ElderWandItem.setCast(true);
                    ElderWandItem.startCastCooldown(InvokeUndeadScrollItem.getCOOLDOWN() * 2);
                }
            }
            // Lesser version of the spell (invokes 1 zombie or 1 skeleton)
            else if (wand != ArcanaItems.ELDER_WAND && scroll == ArcanaItems.INVOKE_UNDEAD_SCROLL) {
                if (getWorld().random.nextBoolean()) {
                    ZombieEntity zombie = EntityType.ZOMBIE.create(serverWorld);
                    zombie.refreshPositionAndAngles((double) getBlockPos().getX() + getWorld().random.nextDouble(), getBlockPos().getY(), (double) getBlockPos().getZ() + getWorld().random.nextDouble(), 0.0F, 0.0F);
                    serverWorld.spawnEntity(zombie);
                } else {
                    SkeletonEntity skeleton = EntityType.SKELETON.create(serverWorld);
                    skeleton.refreshPositionAndAngles((double) getBlockPos().getX() + getWorld().random.nextDouble(), getBlockPos().getY(), (double) getBlockPos().getZ() + getWorld().random.nextDouble(), 0.0F, 0.0F);
                    serverWorld.spawnEntity(skeleton);
                }

                if (wand == ArcanaItems.END_WAND){
                    EndWandItem.setCast(true);
                    EndWandItem.startCastCooldown(InvokeUndeadScrollItem.getCOOLDOWN() * 2);
                }
                else if (wand == ArcanaItems.MAGIC_WAND){
                    MagicWandItem.setCast(true);
                    MagicWandItem.startCastCooldown(InvokeUndeadScrollItem.getCOOLDOWN() * 2);
                }
            }


            // INVOKE VILLAGER (elder)
            // Greater version of the spell (invokes 3 villagers)
            if (wand == ArcanaItems.ELDER_WAND && scroll == ArcanaItems.INVOKE_VILLAGER_SCROLL) {
                for (int i = 0; i < 3; ++i) {
                    LivingEntity villager = EntityType.VILLAGER.create(serverWorld);
                    villager.refreshPositionAndAngles((double) getBlockPos().getX() + getWorld().random.nextDouble(), getBlockPos().getY(), (double) getBlockPos().getZ() + getWorld().random.nextDouble(), 0.0F, 0.0F);
                    serverWorld.spawnEntity(villager);
                }
                ElderWandItem.setCast(true);
                ElderWandItem.startCastCooldown(InvokeVillagerScrollItem.getCOOLDOWN() * 2);
            }
            // Lesser version of the spell (invokes 1 villagers)
            else if (wand != ArcanaItems.ELDER_WAND && scroll == ArcanaItems.INVOKE_VILLAGER_SCROLL) {
                LivingEntity villager = EntityType.VILLAGER.create(serverWorld);
                villager.refreshPositionAndAngles((double) getBlockPos().getX() + getWorld().random.nextDouble(), getBlockPos().getY(), (double) getBlockPos().getZ() + getWorld().random.nextDouble(), 0.0F, 0.0F);
                serverWorld.spawnEntity(villager);

                if (wand == ArcanaItems.MAGIC_WAND){
                    MagicWandItem.setCast(true);
                    MagicWandItem.startCastCooldown(InvokeVillagerScrollItem.getCOOLDOWN() * 2);
                }
                else if (wand == ArcanaItems.END_WAND){
                    EndWandItem.setCast(true);
                    EndWandItem.startCastCooldown(InvokeVillagerScrollItem.getCOOLDOWN() * 2);
                }
                else if (wand == ArcanaItems.NETHER_WAND){
                    NetherWandItem.setCast(true);
                    NetherWandItem.startCastCooldown(InvokeVillagerScrollItem.getCOOLDOWN() * 2);
                }
            }


            // INVOKE WOLF (magic)
            // Greater version of the spell (invokes 2-3 wolves)
            if ((wand == ArcanaItems.MAGIC_WAND || wand == ArcanaItems.ELDER_WAND) && scroll == ArcanaItems.INVOKE_WOLF_SCROLL) {
                int size = wand == ArcanaItems.MAGIC_WAND ? 2 : 3;
                for (int i = 0; i < size; ++i) {
                    WolfEntity wolf = EntityType.WOLF.create(serverWorld);
                    wolf.refreshPositionAndAngles((double) getBlockPos().getX() + getWorld().random.nextDouble(), getBlockPos().getY(), (double) getBlockPos().getZ() + getWorld().random.nextDouble(), 0.0F, 0.0F);
                    serverWorld.spawnEntity(wolf);
                }

                if (wand == ArcanaItems.MAGIC_WAND){
                    MagicWandItem.setCast(true);
                    MagicWandItem.startCastCooldown(InvokeWolfScrollItem.getCOOLDOWN() * 2);
                }
                else if (wand == ArcanaItems.ELDER_WAND){
                    ElderWandItem.setCast(true);
                    ElderWandItem.startCastCooldown(InvokeWolfScrollItem.getCOOLDOWN() * 2);
                }
            }
            // Lesser version of the spell (invokes 1 wolf)
            else if (wand != ArcanaItems.ELDER_WAND && scroll == ArcanaItems.INVOKE_WOLF_SCROLL) {
                WolfEntity wolf = EntityType.WOLF.create(serverWorld);
                wolf.refreshPositionAndAngles((double) getBlockPos().getX() + getWorld().random.nextDouble(), getBlockPos().getY(), (double) getBlockPos().getZ() + getWorld().random.nextDouble(), 0.0F, 0.0F);
                serverWorld.spawnEntity(wolf);

                if (wand == ArcanaItems.END_WAND){
                    EndWandItem.setCast(true);
                    EndWandItem.startCastCooldown(InvokeWolfScrollItem.getCOOLDOWN() * 2);
                }
                else if (wand == ArcanaItems.NETHER_WAND){
                    NetherWandItem.setCast(true);
                    NetherWandItem.startCastCooldown(InvokeWolfScrollItem.getCOOLDOWN() * 2);
                }
            }
        }
    }
}
