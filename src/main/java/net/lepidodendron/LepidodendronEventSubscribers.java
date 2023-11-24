package net.lepidodendron;

import net.lepidodendron.block.*;
import net.lepidodendron.entity.EntityPrehistoricFloraGuanoBall;
import net.lepidodendron.entity.EntityPrehistoricFloraMeteor;
import net.lepidodendron.entity.base.EntityPrehistoricFloraAgeableBase;
import net.lepidodendron.entity.boats.PrehistoricFloraSubmarine;
import net.lepidodendron.item.*;
import net.lepidodendron.util.EnumBiomeTypePrecambrian;
import net.lepidodendron.util.ModTriggers;
import net.lepidodendron.world.biome.precambrian.BiomePrecambrian;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntitySkeletonHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.village.MerchantTradeOffersEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;
import java.util.Random;

public class LepidodendronEventSubscribers {

  	@SubscribeEvent //Give the Palaeopedia on first join:
	public void playerJoined(EntityJoinWorldEvent event) {
		if (!LepidodendronConfig.giveBook) {
			return;
		}
		if ((event.getEntity() instanceof EntityPlayerMP)) {
			ModTriggers.PALAEOPEDIA_GIVEN.trigger((EntityPlayerMP) event.getEntity());
		}
	}

	@SubscribeEvent //Default to standard attack behaviour
	public void attackTargetSet(LivingSetAttackTargetEvent event) {
		  if (event.getEntity() instanceof EntityPrehistoricFloraAgeableBase) {
			  ((EntityPrehistoricFloraAgeableBase)event.getEntity()).wasWarning = false;
		  }
	}

	@SubscribeEvent //Some instructions for use of rideables
	public void playerMounted(EntityMountEvent event) {
		Entity entity = event.getEntityMounting();
		if (entity instanceof EntityPlayer && event.isMounting() && event.getEntityBeingMounted() != null) {
			EntityPlayer player = (EntityPlayer) entity;
			if (event.getEntityBeingMounted() instanceof PrehistoricFloraSubmarine && event.getEntityMounting().getEntityWorld().isRemote) {
				player.sendMessage(new TextComponentString("Additional Submarine controls: up = " + ClientProxyLepidodendronMod.keyBoatUp.getDisplayName() + "; down = " + ClientProxyLepidodendronMod.keyBoatDown.getDisplayName() + "; strafe left = " + ClientProxyLepidodendronMod.keyBoatStrafeLeft.getDisplayName() + "; strafe right = " + ClientProxyLepidodendronMod.keyBoatStrafeRight.getDisplayName()));
				player.sendMessage(new TextComponentString("Left control panel: read battery; right control panel: add/remove battery"));
			}
		}
	}

	@SubscribeEvent //Bat poo
	public void guano(LivingEvent.LivingUpdateEvent event) {
		if (event.getEntity() instanceof EntityBat) {
			EntityBat bat = (EntityBat) event.getEntity();
			if (bat.world.rand.nextInt(6000) == 0 && (!bat.world.isRemote)
					&& bat.getIsBatHanging() && bat.world.isAirBlock(bat.getPosition().down())) {
				EntityPrehistoricFloraGuanoBall guanoBall = new EntityPrehistoricFloraGuanoBall(bat.world, bat.posX, bat.posY - 0.5, bat.posZ);
				guanoBall.setFromMob(true);
				bat.world.spawnEntity(guanoBall);
			}
		}
	}

	@SubscribeEvent //Spawn Hadean meteors
	public void meteors(WorldTickEvent event) {
		boolean spawnShower = false;
		if (event.world != null && !event.world.isRemote && LepidodendronConfig.doMeteorites) {
			if (event.world.rand.nextInt(6000) == 0) {//Note that lowering this number spawns meteors more frequently, default 6000
				if (!event.world.playerEntities.isEmpty()) {
					EntityPlayer p = (EntityPlayer) event.world.playerEntities.get(event.world.rand.nextInt(event.world.playerEntities.size()));
					BlockPos pos = new BlockPos((p.posX + event.world.rand.nextInt(201) - 100), 300, (p.posZ + event.world.rand.nextInt(201) - 100));
					if (p != null && p.dimension == LepidodendronConfig.dimPrecambrian) {
						Biome biome = event.world.getBiome(pos);
						if (biome instanceof BiomePrecambrian) {
							if (((BiomePrecambrian) biome).getBiomeType() == EnumBiomeTypePrecambrian.Hadean) {
								spawnShower = (event.world.rand.nextInt(50) == 0);
								{
									EntityPrehistoricFloraMeteor meteor = new EntityPrehistoricFloraMeteor(event.world, pos.getX(), pos.getY(), pos.getZ());
									meteor.motionX = event.world.rand.nextDouble() - 0.5;
									meteor.motionZ = event.world.rand.nextDouble() - 0.5;
									event.world.spawnEntity(meteor);
								}
								if (spawnShower) {
									EntityPrehistoricFloraMeteor meteor = new EntityPrehistoricFloraMeteor(event.world, pos.getX(), pos.getY(), pos.getZ());
									meteor.motionX = event.world.rand.nextDouble() - 0.5;
									meteor.motionZ = event.world.rand.nextDouble() - 0.5;
									event.world.spawnEntity(meteor);
									if (event.world.rand.nextInt(3) == 0) {
										EntityPrehistoricFloraMeteor meteor2 = new EntityPrehistoricFloraMeteor(event.world, pos.getX(), pos.getY(), pos.getZ());
										meteor.motionX = event.world.rand.nextDouble() - 0.5;
										meteor.motionZ = event.world.rand.nextDouble() - 0.5;
										event.world.spawnEntity(meteor2);
									}
									if (event.world.rand.nextInt(3) == 0) {
										EntityPrehistoricFloraMeteor meteor3 = new EntityPrehistoricFloraMeteor(event.world, pos.getX(), pos.getY(), pos.getZ());
										meteor.motionX = event.world.rand.nextDouble() - 0.5;
										meteor.motionZ = event.world.rand.nextDouble() - 0.5;
										event.world.spawnEntity(meteor3);
									}
								}
							}
						}
					}
				}
			}
		}
	}

	@SubscribeEvent //Block other mods' normal spawns
	public void onSpawn(LivingSpawnEvent event) {
		if ((event.getWorld().provider.getDimension() != LepidodendronConfig.dimPrecambrian
				&& event.getWorld().provider.getDimension() != LepidodendronConfig.dimCambrian
				&& event.getWorld().provider.getDimension() != LepidodendronConfig.dimOrdovician
				&& event.getWorld().provider.getDimension() != LepidodendronConfig.dimSilurian
				&& event.getWorld().provider.getDimension() != LepidodendronConfig.dimDevonian
				&& event.getWorld().provider.getDimension() != LepidodendronConfig.dimCarboniferous
				&& event.getWorld().provider.getDimension() != LepidodendronConfig.dimPermian
				&& event.getWorld().provider.getDimension() != LepidodendronConfig.dimTriassic
				&& event.getWorld().provider.getDimension() != LepidodendronConfig.dimJurassic
				&& event.getWorld().provider.getDimension() != LepidodendronConfig.dimCretaceousEarly
				&& event.getWorld().provider.getDimension() != LepidodendronConfig.dimPaleogene
				&& event.getWorld().provider.getDimension() != LepidodendronConfig.dimNeogene
				&& event.getWorld().provider.getDimension() != LepidodendronConfig.dimPleistocene)
				|| event.getEntity() == null) {
			return;
		}
		if (EntityList.getKey(event.getEntity()) == null) {
			return;
		}
		String resLocation = EntityList.getKey(event.getEntity()).toString();
		int strPos1 = resLocation.indexOf(":");
		if ((strPos1 > 0)) {
			String modid = resLocation.substring(0, strPos1);
			if (modid.equalsIgnoreCase("fossil") || modid.equalsIgnoreCase("rebornmod")
					|| modid.equalsIgnoreCase("lepidodendron")) {
				return;
			} else {
				if (LepidodendronConfig.blockMobs) {
					event.setResult(Event.Result.DENY);
				}
			}
		}
	}

	@SubscribeEvent //BlockTrap Horses and other mobs
	public void onJoinSpawn(EntityJoinWorldEvent event) {
		if ((event.getWorld().provider.getDimension() != LepidodendronConfig.dimPrecambrian
				&& event.getWorld().provider.getDimension() != LepidodendronConfig.dimCambrian
				&& event.getWorld().provider.getDimension() != LepidodendronConfig.dimOrdovician
				&& event.getWorld().provider.getDimension() != LepidodendronConfig.dimSilurian
				&& event.getWorld().provider.getDimension() != LepidodendronConfig.dimDevonian
				&& event.getWorld().provider.getDimension() != LepidodendronConfig.dimCarboniferous
				&& event.getWorld().provider.getDimension() != LepidodendronConfig.dimPermian
				&& event.getWorld().provider.getDimension() != LepidodendronConfig.dimTriassic
				&& event.getWorld().provider.getDimension() != LepidodendronConfig.dimJurassic
				&& event.getWorld().provider.getDimension() != LepidodendronConfig.dimCretaceousEarly
				&& event.getWorld().provider.getDimension() != LepidodendronConfig.dimPaleogene
				&& event.getWorld().provider.getDimension() != LepidodendronConfig.dimNeogene
				&& event.getWorld().provider.getDimension() != LepidodendronConfig.dimPleistocene)
				|| event.getEntity() == null) {
			return;
		}
		if (LepidodendronConfig.blockSkeletonHorse) {
			if (event.getEntity() instanceof EntitySkeletonHorse) {
				event.setCanceled(true);
				return;
			}
		}
		if (EntityList.getKey(event.getEntity()) == null) {
			return;
		}
		String resLocation = EntityList.getKey(event.getEntity()).toString();
		int strPos1 = resLocation.indexOf(":");
		if (strPos1 > 0) {
			String modid = resLocation.substring(0, strPos1);
			int nautilus = resLocation.indexOf("fossil.nautilus");
			int coelacanth = resLocation.indexOf("fossil.coelacanth");
			int alligator_gar = resLocation.indexOf("fossil.alligator_gar");
			int sturgeon = resLocation.indexOf("fossil.sturgeon");

			if (modid.equalsIgnoreCase("fossil")
					&& (nautilus > 0 || coelacanth > 0
					|| alligator_gar > 0 || sturgeon > 0)
			) {
				if (LepidodendronConfig.blockMobsFAExceptions) {
					event.setCanceled(true);
				} else if ((nautilus > 0 || coelacanth > 0)
						&& (event.getWorld().provider.getDimension() == LepidodendronConfig.dimPrecambrian
						|| event.getWorld().provider.getDimension() == LepidodendronConfig.dimCambrian
						|| event.getWorld().provider.getDimension() == LepidodendronConfig.dimOrdovician
						|| event.getWorld().provider.getDimension() == LepidodendronConfig.dimSilurian
						|| event.getWorld().provider.getDimension() == LepidodendronConfig.dimDevonian
						|| event.getWorld().provider.getDimension() == LepidodendronConfig.dimCarboniferous
						|| event.getWorld().provider.getDimension() == LepidodendronConfig.dimPermian)) {
					event.setCanceled(true);
				} else if ((sturgeon > 0)
						&& (event.getWorld().provider.getDimension() == LepidodendronConfig.dimPrecambrian
						|| event.getWorld().provider.getDimension() == LepidodendronConfig.dimCambrian
						|| event.getWorld().provider.getDimension() == LepidodendronConfig.dimOrdovician
						|| event.getWorld().provider.getDimension() == LepidodendronConfig.dimSilurian
						|| event.getWorld().provider.getDimension() == LepidodendronConfig.dimDevonian
						|| event.getWorld().provider.getDimension() == LepidodendronConfig.dimCarboniferous
						|| event.getWorld().provider.getDimension() == LepidodendronConfig.dimPermian
						|| event.getWorld().provider.getDimension() == LepidodendronConfig.dimTriassic)) {
					event.setCanceled(true);
				} else if ((alligator_gar > 0)
						&& (event.getWorld().provider.getDimension() == LepidodendronConfig.dimPrecambrian
						|| event.getWorld().provider.getDimension() == LepidodendronConfig.dimCambrian
						|| event.getWorld().provider.getDimension() == LepidodendronConfig.dimOrdovician
						|| event.getWorld().provider.getDimension() == LepidodendronConfig.dimSilurian
						|| event.getWorld().provider.getDimension() == LepidodendronConfig.dimDevonian
						|| event.getWorld().provider.getDimension() == LepidodendronConfig.dimCarboniferous
						|| event.getWorld().provider.getDimension() == LepidodendronConfig.dimPermian
						|| event.getWorld().provider.getDimension() == LepidodendronConfig.dimTriassic
						|| event.getWorld().provider.getDimension() == LepidodendronConfig.dimJurassic)) {
					event.setCanceled(true);
				}
			}
		}
	}

	@SubscribeEvent //We want to drop the items (also for the Taxidermy finished items)
	public void clickDisplays(PlayerInteractEvent.LeftClickBlock event) {
		if (event.getWorld().getBlockState(event.getPos()).getBlock() == BlockDisplayCase.block
				&& event.getHand() == EnumHand.MAIN_HAND) {
			TileEntity te = event.getWorld().getTileEntity(event.getPos());
			if (te != null) {
				if (te instanceof BlockDisplayCase.TileEntityDisplayCase) {
					BlockDisplayCase.TileEntityDisplayCase tee = (BlockDisplayCase.TileEntityDisplayCase) te;
					if (tee.hasItem()) {
						if (!(event.getWorld().isRemote)) {
							ItemStack itemstack = tee.getStackInSlot(0);
							Block.spawnAsEntity(event.getWorld(), event.getPos(), itemstack);
							SoundEvent soundevent = SoundEvents.ENTITY_ITEMFRAME_REMOVE_ITEM;
							((WorldServer) event.getEntityPlayer().getEntityWorld()).playSound(null, event.getPos(), soundevent, SoundCategory.BLOCKS, 1.0F, 1.0F);
							event.getEntityPlayer().swingArm(event.getHand());
						}
						tee.setDisplay(ItemStack.EMPTY);
						//return;
						event.setCanceled(true);
					}
				}
			}
		} else if (event.getWorld().getBlockState(event.getPos()).getBlock() == BlockDisplayCaseMagnifying.block
				&& event.getHand() == EnumHand.MAIN_HAND) {
			TileEntity te = event.getWorld().getTileEntity(event.getPos());
			if (te != null) {
				if (te instanceof BlockDisplayCaseMagnifying.TileEntityDisplayCase) {
					BlockDisplayCaseMagnifying.TileEntityDisplayCase tee = (BlockDisplayCaseMagnifying.TileEntityDisplayCase) te;
					if (tee.hasItem()) {
						if (!(event.getWorld().isRemote)) {
							ItemStack itemstack = tee.getStackInSlot(0);
							Block.spawnAsEntity(event.getWorld(), event.getPos(), itemstack);

							SoundEvent soundevent = SoundEvents.ENTITY_ITEMFRAME_REMOVE_ITEM;
							((WorldServer) event.getEntityPlayer().getEntityWorld()).playSound(null, event.getPos(), soundevent, SoundCategory.BLOCKS, 1.0F, 1.0F);
							event.getEntityPlayer().swingArm(event.getHand());
						}
						tee.setDisplay(ItemStack.EMPTY);
						//return;
						event.setCanceled(true);
					}
				}
			}
		} else if (event.getWorld().getBlockState(event.getPos()).getBlock() == BlockDisplayWallMount.block
				&& event.getHand() == EnumHand.MAIN_HAND) {
			TileEntity te = event.getWorld().getTileEntity(event.getPos());
			if (te != null) {
				if (te instanceof BlockDisplayWallMount.TileEntityDisplayWallMount) {
					BlockDisplayWallMount.TileEntityDisplayWallMount tee = (BlockDisplayWallMount.TileEntityDisplayWallMount) te;
					if (tee.hasItem()) {
						if (!(event.getWorld().isRemote)) {
							ItemStack itemstack = tee.getStackInSlot(0);
							Block.spawnAsEntity(event.getWorld(), event.getPos(), itemstack);

							SoundEvent soundevent = SoundEvents.ENTITY_ITEMFRAME_REMOVE_ITEM;
							((WorldServer) event.getEntityPlayer().getEntityWorld()).playSound(null, event.getPos(), soundevent, SoundCategory.BLOCKS, 1.0F, 1.0F);
							event.getEntityPlayer().swingArm(event.getHand());
						}
						tee.setDisplay(ItemStack.EMPTY);
						//return;
						event.getWorld().markBlockRangeForRenderUpdate(event.getPos(), event.getPos());
						event.setCanceled(true);
					}
				}
			}
		}
		else if (event.getWorld().getBlockState(event.getPos()).getBlock() == BlockTaxidermyTable.block
				&& event.getHand() == EnumHand.MAIN_HAND) {
			TileEntity te = event.getWorld().getTileEntity(event.getPos());
			if (te != null) {
				if (te instanceof BlockTaxidermyTable.TileEntityTaxidermyTable) {
					BlockTaxidermyTable.TileEntityTaxidermyTable tee = (BlockTaxidermyTable.TileEntityTaxidermyTable) te;
					if (!tee.getStackInSlot(1).isEmpty()) {
						if (!(event.getWorld().isRemote)) {
							ItemStack itemstack = tee.getStackInSlot(1);
							Block.spawnAsEntity(event.getWorld(), event.getPos(), itemstack);
							//tee.setInventorySlotContents(0, ItemStack.EMPTY);

							SoundEvent soundevent = SoundEvents.ENTITY_ITEMFRAME_REMOVE_ITEM;
							((WorldServer) event.getEntityPlayer().getEntityWorld()).playSound(null, event.getPos(), soundevent, SoundCategory.BLOCKS, 1.0F, 1.0F);
							event.getEntityPlayer().swingArm(event.getHand());
						}
						tee.removeStackFromSlot(1);
						tee.notifyBlockUpdate();
						//return;
						event.setCanceled(true);
					}
				}
			}
		}
	}

	@SubscribeEvent //Vanilla plants drops modifications: prevent bonemealing for propagation or growing ferns or grass
	public void onBonemeal(BonemealEvent event) {
		if (event.getBlock().getBlock() instanceof BlockDoublePlant && LepidodendronConfig.doPropagationVanilla) {
			event.setCanceled(true);
		}
		if (event.getBlock().getBlock() instanceof BlockTallGrass && LepidodendronConfig.doPropagationVanilla) {
			event.setCanceled(true);
		}
		if ((event.getBlock().getBlock() instanceof BlockGrass || event.getBlock().getMaterial() == Material.GRASS) && LepidodendronConfig.doPropagationVanilla) {
			event.setCanceled(true);
		}
	}

	@SubscribeEvent //We want to drop the real items or flowers
	public void onSheared(PlayerInteractEvent.RightClickBlock event) {
		if (event.getWorld().getBlockState(event.getPos()).getBlock() == Blocks.DOUBLE_PLANT
				&& event.getItemStack().getItem() instanceof ItemShears
//				&& LepidodendronConfig.doPropagationVanilla
				&& event.getHand() == EnumHand.MAIN_HAND) {
			BlockDoublePlant.EnumPlantType blockdoubleplant$enumplanttype = (BlockDoublePlant.EnumPlantType) event.getWorld().getBlockState(event.getPos()).getValue(BlockDoublePlant.VARIANT);
			if (blockdoubleplant$enumplanttype == BlockDoublePlant.EnumPlantType.GRASS
					|| blockdoubleplant$enumplanttype == BlockDoublePlant.EnumPlantType.FERN) { //imitate a regular harvest without shears:
				event.getEntityPlayer().swingArm(event.getHand());
				if (!(event.getWorld().isRemote)) {
					event.getWorld().destroyBlock(event.getPos(), false);
					event.getWorld().setBlockToAir(event.getPos().up());
				}
				event.getItemStack().damageItem(1, event.getEntityPlayer());
				event.setCanceled(true);
			} else if (event.getWorld().getBlockState(event.getPos()).getValue(BlockDoublePlant.HALF) == BlockDoublePlant.EnumBlockHalf.UPPER
					&& event.getWorld().getBlockState(event.getPos().down()).getBlock() == Blocks.DOUBLE_PLANT) {
				BlockDoublePlant.EnumPlantType blockdoubleplant$enumplanttypeDown = (BlockDoublePlant.EnumPlantType) event.getWorld().getBlockState(event.getPos().down()).getValue(BlockDoublePlant.VARIANT);
				if (blockdoubleplant$enumplanttypeDown == BlockDoublePlant.EnumPlantType.GRASS
						|| blockdoubleplant$enumplanttypeDown == BlockDoublePlant.EnumPlantType.FERN) { //imitate a regular harvest without shears:
					event.getEntityPlayer().swingArm(event.getHand());
					if (!(event.getWorld().isRemote)) {
						event.getWorld().destroyBlock(event.getPos(), false);
						event.getWorld().setBlockToAir(event.getPos().up());
					}
					event.getItemStack().damageItem(1, event.getEntityPlayer());
					event.setCanceled(true);
				}
			} else if (blockdoubleplant$enumplanttype == BlockDoublePlant.EnumPlantType.ROSE) { //spawn rose flowers:
				event.getEntityPlayer().swingArm(event.getHand());
				if (!(event.getWorld().isRemote) && Math.random() > 0.95) {
					event.getWorld().destroyBlock(event.getPos(), false);
					event.getWorld().setBlockToAir(event.getPos().up());
				}
				Block.spawnAsEntity(event.getWorld(), event.getPos(), new ItemStack(ItemRoseFlower.block, 1));
				event.getItemStack().damageItem(1, event.getEntityPlayer());
				event.setCanceled(true);
			} else if (event.getWorld().getBlockState(event.getPos()).getValue(BlockDoublePlant.HALF) == BlockDoublePlant.EnumBlockHalf.UPPER
					&& event.getWorld().getBlockState(event.getPos().down()).getBlock() == Blocks.DOUBLE_PLANT) {
				BlockDoublePlant.EnumPlantType blockdoubleplant$enumplanttypeDown = (BlockDoublePlant.EnumPlantType) event.getWorld().getBlockState(event.getPos().down()).getValue(BlockDoublePlant.VARIANT);
				if (blockdoubleplant$enumplanttypeDown == BlockDoublePlant.EnumPlantType.ROSE) { //spawn rose flowers:
					event.getEntityPlayer().swingArm(event.getHand());
					if (!(event.getWorld().isRemote) && Math.random() > 0.95) {
						event.getWorld().destroyBlock(event.getPos(), false);
						event.getWorld().setBlockToAir(event.getPos().up());
					}
					Block.spawnAsEntity(event.getWorld(), event.getPos(), new ItemStack(ItemRoseFlower.block, 1));
					event.getItemStack().damageItem(1, event.getEntityPlayer());
					event.setCanceled(true);
				}
			} else if (blockdoubleplant$enumplanttype == BlockDoublePlant.EnumPlantType.PAEONIA) { //spawn Peony flowers:
				event.getEntityPlayer().swingArm(event.getHand());
				if (!(event.getWorld().isRemote) && Math.random() > 0.95) {
					event.getWorld().destroyBlock(event.getPos(), false);
					event.getWorld().setBlockToAir(event.getPos().up());
				}
				Block.spawnAsEntity(event.getWorld(), event.getPos(), new ItemStack(ItemPeonyFlower.block, 1));
				event.getItemStack().damageItem(1, event.getEntityPlayer());
				event.setCanceled(true);
			} else if (event.getWorld().getBlockState(event.getPos()).getValue(BlockDoublePlant.HALF) == BlockDoublePlant.EnumBlockHalf.UPPER
					&& event.getWorld().getBlockState(event.getPos().down()).getBlock() == Blocks.DOUBLE_PLANT) {
				BlockDoublePlant.EnumPlantType blockdoubleplant$enumplanttypeDown = (BlockDoublePlant.EnumPlantType) event.getWorld().getBlockState(event.getPos().down()).getValue(BlockDoublePlant.VARIANT);
				if (blockdoubleplant$enumplanttypeDown == BlockDoublePlant.EnumPlantType.PAEONIA) { //spawn Peony flowers:
					event.getEntityPlayer().swingArm(event.getHand());
					if (!(event.getWorld().isRemote) && Math.random() > 0.95) {
						event.getWorld().destroyBlock(event.getPos(), false);
						event.getWorld().setBlockToAir(event.getPos().up());
					}
					Block.spawnAsEntity(event.getWorld(), event.getPos(), new ItemStack(ItemPeonyFlower.block, 1));
					event.getItemStack().damageItem(1, event.getEntityPlayer());
					event.setCanceled(true);
				}
			} else if (blockdoubleplant$enumplanttype == BlockDoublePlant.EnumPlantType.SYRINGA) { //spawn lilac flowers:
				event.getEntityPlayer().swingArm(event.getHand());
				if (!(event.getWorld().isRemote) && Math.random() > 0.95) {
					event.getWorld().destroyBlock(event.getPos(), false);
					event.getWorld().setBlockToAir(event.getPos().up());
				}
				Block.spawnAsEntity(event.getWorld(), event.getPos(), new ItemStack(ItemLilacFlower.block, 1));
				event.getItemStack().damageItem(1, event.getEntityPlayer());
				event.setCanceled(true);
			} else if (event.getWorld().getBlockState(event.getPos()).getValue(BlockDoublePlant.HALF) == BlockDoublePlant.EnumBlockHalf.UPPER
					&& event.getWorld().getBlockState(event.getPos().down()).getBlock() == Blocks.DOUBLE_PLANT) {
				BlockDoublePlant.EnumPlantType blockdoubleplant$enumplanttypeDown = (BlockDoublePlant.EnumPlantType) event.getWorld().getBlockState(event.getPos().down()).getValue(BlockDoublePlant.VARIANT);
				if (blockdoubleplant$enumplanttypeDown == BlockDoublePlant.EnumPlantType.SYRINGA) { //spawn lilac flowers:
					event.getEntityPlayer().swingArm(event.getHand());
					if (!(event.getWorld().isRemote) && Math.random() > 0.95) {
						event.getWorld().destroyBlock(event.getPos(), false);
						event.getWorld().setBlockToAir(event.getPos().up());
					}
					Block.spawnAsEntity(event.getWorld(), event.getPos(), new ItemStack(ItemLilacFlower.block, 1));
					event.getItemStack().damageItem(1, event.getEntityPlayer());
					event.setCanceled(true);
				}
			}
		} else if (event.getWorld().getBlockState(event.getPos()).getBlock() == Blocks.DOUBLE_PLANT
				&& (!(event.getItemStack().getItem() instanceof ItemShears)) && LepidodendronConfig.doPropagationVanilla
				&& event.getHand() == EnumHand.MAIN_HAND) {
			BlockDoublePlant.EnumPlantType blockdoubleplant$enumplanttype = (BlockDoublePlant.EnumPlantType) event.getWorld().getBlockState(event.getPos()).getValue(BlockDoublePlant.VARIANT);
			if (blockdoubleplant$enumplanttype == BlockDoublePlant.EnumPlantType.GRASS
					|| blockdoubleplant$enumplanttype == BlockDoublePlant.EnumPlantType.FERN) {

			}
		}
	}

	@SubscribeEvent //Let eggs drop their right items:
	public void onBlockPreBreak(BlockEvent.BreakEvent event) {
		if ((!event.getWorld().isRemote)) {
			if (event.getPlayer() != null) {
				if (!event.getPlayer().isCreative() && event.getState().getBlock() == BlockEggs.block) {
					EntityItem entityToSpawn = new EntityItem(event.getWorld(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), BlockEggs.BlockCustom.getEggItemStack(event.getWorld(), event.getPos()));
					entityToSpawn.setPickupDelay(10);
					event.getWorld().spawnEntity(entityToSpawn);
				}
			}
		}
	}

	@SubscribeEvent //Vanilla plants drops modifications: replace saplings with seeds etc.
	public void onBlockHarvest(BlockEvent.HarvestDropsEvent event) {

		if (!LepidodendronConfig.doPropagationVanilla && !LepidodendronConfig.fixApples) {
			return;
		}
		boolean dropSelf = true;
		if (event.getState().getBlock() instanceof BlockDoublePlant && LepidodendronConfig.doPropagationVanilla) {
			BlockDoublePlant.EnumPlantType blockdoubleplant$enumplanttype = (BlockDoublePlant.EnumPlantType) event.getState().getValue(BlockDoublePlant.VARIANT);
			if (blockdoubleplant$enumplanttype == BlockDoublePlant.EnumPlantType.FERN
					|| blockdoubleplant$enumplanttype == BlockDoublePlant.EnumPlantType.GRASS) {
				dropSelf = false; //the large fern and large grass don't drop themselves, but we want to make it so that they do
			}
		}
		if (event.getState().getBlock() instanceof BlockTallGrass && LepidodendronConfig.doPropagationVanilla) {
			BlockTallGrass.EnumType blocktallgrass$enumtype = (BlockTallGrass.EnumType) event.getState().getValue(BlockTallGrass.TYPE);
			if (blocktallgrass$enumtype == BlockTallGrass.EnumType.GRASS) {
				dropSelf = false; //the grass needs shears but we want to drop it without needing them
			}
		}
		int i = 0;
		while (i + 1 <= event.getDrops().size()) {
			Item item = event.getDrops().get(i).getItem();
			Block block = Block.getBlockFromItem(item);

			if (event.getState().getBlock() instanceof BlockDoublePlant && LepidodendronConfig.doPropagationVanilla) {
				BlockDoublePlant.EnumPlantType blockdoubleplant$enumplanttype = (BlockDoublePlant.EnumPlantType) event.getState().getValue(BlockDoublePlant.VARIANT);
				if (blockdoubleplant$enumplanttype == BlockDoublePlant.EnumPlantType.FERN
						&& item == new ItemStack(Blocks.DOUBLE_PLANT, 1, 3).getItem()) {
					dropSelf = true; //a drop already exists so no need to add a new one
				}
				if (blockdoubleplant$enumplanttype == BlockDoublePlant.EnumPlantType.GRASS
						&& item == new ItemStack(Blocks.DOUBLE_PLANT, 1, 2).getItem() && LepidodendronConfig.doPropagationVanilla) {
					dropSelf = true; //a drop already exists so no need to add a new one
				}
			}

			if (event.getState().getBlock() instanceof BlockTallGrass && LepidodendronConfig.doPropagationVanilla) {
				BlockTallGrass.EnumType blocktallgrass$enumtype = (BlockTallGrass.EnumType) event.getState().getValue(BlockTallGrass.TYPE);
				if (blocktallgrass$enumtype == BlockTallGrass.EnumType.GRASS
						&& item == new ItemStack(Blocks.TALLGRASS, 1, 1).getItem()) {
					dropSelf = true; //a drop already exists so no need to add a new one
				}
			}

			if (item == Items.APPLE && LepidodendronConfig.fixApples &&
					(event.getState().getBlock() == Blocks.LEAVES
							|| event.getState().getBlock() == Blocks.LEAVES2)) {
				event.getDrops().remove(i);
			}

			block = Block.getBlockFromItem(item);
			if (block instanceof BlockSapling && LepidodendronConfig.doPropagationVanilla) {
				if (item == (new ItemStack(Blocks.SAPLING, (int) (1), 0).getItem())) { //Oak
					event.getDrops().remove(i);
					event.getDrops().add(i, new ItemStack(ItemOakAcorn.block, 1));
				}
				if (item == (new ItemStack(Blocks.SAPLING, (int) (2), 1).getItem())) { //Spruce
					event.getDrops().remove(i);
					event.getDrops().add(i, new ItemStack(Blocks.AIR, 1));
				}
				if (item == (new ItemStack(Blocks.SAPLING, (int) (2), 2).getItem())) { //Birch
					event.getDrops().remove(i);
					event.getDrops().add(i, new ItemStack(Blocks.AIR, 1));
				}
				if (item == (new ItemStack(Blocks.SAPLING, (int) (2), 3).getItem())) { //Jungle
					event.getDrops().remove(i);
					event.getDrops().add(i, new ItemStack(Blocks.AIR, 1));
				}
				if (item == (new ItemStack(Blocks.SAPLING, (int) (2), 4).getItem())) { //Acacia
					event.getDrops().remove(i);
					event.getDrops().add(i, new ItemStack(Blocks.AIR, 1));
				}
				if (item == (new ItemStack(Blocks.SAPLING, (int) (2), 6).getItem())) { //Dark Oak
					event.getDrops().remove(i);
					event.getDrops().add(i, new ItemStack(ItemOakDarkAcorn.block, 1));
				}
			}
			if (event.getState().getBlock() instanceof BlockDoublePlant && LepidodendronConfig.doPropagationVanilla) {
				BlockDoublePlant.EnumPlantType blockdoubleplant$enumplanttype = (BlockDoublePlant.EnumPlantType) event.getState().getValue(BlockDoublePlant.VARIANT);
				if (blockdoubleplant$enumplanttype == BlockDoublePlant.EnumPlantType.GRASS
						&& item == new ItemStack(Blocks.TALLGRASS, 1, 1).getItem()) {
					event.getDrops().add(i, new ItemStack(Blocks.DOUBLE_PLANT, 1, 2));
					dropSelf = true; //This now drops itself
				}
				if (blockdoubleplant$enumplanttype == BlockDoublePlant.EnumPlantType.FERN
						&& item == new ItemStack(Blocks.TALLGRASS, 1, 2).getItem() && LepidodendronConfig.doPropagationVanilla) {
					event.getDrops().add(i, new ItemStack(Blocks.DOUBLE_PLANT, 1, 3));
					dropSelf = true; //This now drops itself
				}
			}
			i++;
		}

		if (event.getState().getBlock() instanceof BlockDoublePlant && (!dropSelf) && LepidodendronConfig.doPropagationVanilla) { //Spawn the block drop for these ones
			BlockDoublePlant.EnumPlantType blockdoubleplant$enumplanttype = (BlockDoublePlant.EnumPlantType) event.getState().getValue(BlockDoublePlant.VARIANT);
			if (blockdoubleplant$enumplanttype == BlockDoublePlant.EnumPlantType.FERN) {
				event.getDrops().add(i, new ItemStack(Blocks.DOUBLE_PLANT, 1, 3));
			}
			if (blockdoubleplant$enumplanttype == BlockDoublePlant.EnumPlantType.GRASS) {
				event.getDrops().add(i, new ItemStack(Blocks.DOUBLE_PLANT, 1, 2));
			}
		}
		if (event.getState().getBlock() instanceof BlockTallGrass && (!dropSelf) && LepidodendronConfig.doPropagationVanilla) {
			BlockTallGrass.EnumType blocktallgrass$enumtype = (BlockTallGrass.EnumType) event.getState().getValue(BlockTallGrass.TYPE);
			if (blocktallgrass$enumtype == BlockTallGrass.EnumType.GRASS) {
				event.getDrops().add(i, new ItemStack(Blocks.TALLGRASS, 1, 1));
			}
		}
		//if (event.getState().getBlock() == Blocks.VINE) {
		//	event.getDrops().add(i, new ItemStack(ItemGrapes.block,1));
		//}
	}

	@SubscribeEvent //Steam in the Hot Springs:
	public void onPlayerTick(TickEvent.PlayerTickEvent event) {
		Random rand = new Random();
		if (event.phase == TickEvent.Phase.END && event.player.world.provider.getDimension() == LepidodendronConfig.dimDevonian) {
			Entity entity = event.player;
			World world = entity.world;
			BlockPos pos = entity.getPosition();
			int x = (int) entity.posX - 16;
			while (x <= entity.posX + 16) {
				int y = (int) entity.posY - 16;
				while (y <= entity.posY + 16) {
					int z = (int) entity.posZ - 16;
					while (z <= entity.posZ + 16) {
						pos = new BlockPos(x, y, z);
						if (world.getBlockState(pos).getMaterial() == Material.WATER && world.isAirBlock(pos.up())) {
							if (world.getBiome(pos).getRegistryName().toString().equalsIgnoreCase("lepidodendron:devonian_springs") && rand.nextInt(150) == 0) {
								world.spawnParticle(EnumParticleTypes.CLOUD, (double) pos.getX() + Math.random(), (double) pos.getY() + 0.95, (double) pos.getZ() + Math.random(), 0.0D, 0.03D, 0.0D);
								//System.err.println("smokin' at " + pos.getX() + " " + pos.getY() + " " + pos.getZ());
							}
						}
						z += 1;
					}
					y += 1;
				}
				x += 1;
			}
		}

	}

	@SideOnly(Side.CLIENT) //Tooltips for vanilla items etc
	@SubscribeEvent
	public void onEvent(ItemTooltipEvent event) throws NoSuchMethodException {

		if (event.getItemStack().getItem().getRegistryName().toString().equalsIgnoreCase("patchouli:guide_book")) {
			if (event.getItemStack().getTagCompound() != null) {
				if (event.getItemStack().getTagCompound().toString().contains("lepidodendron:paleopedia")) {
					List<String> tt = event.getToolTip();
					tt.add(I18n.translateToLocal("tooltip.palaeopedia.name").trim());
				}
			}
		}

		if (event.getItemStack().getItem() == Item.getItemFromBlock(Blocks.SPONGE)) {
			List<String> tt = event.getToolTip();
			if (!Loader.isModLoaded("pncambrian")) {
				tt.add("NOTE: Used to build the portal to the Cambrian dimension but you do not have that dimension mod installed");
			}
			else {
				tt.add("NOTE: Used to build the portal to the Cambrian dimension");
			}
		}

		if (event.getItemStack().getItem() == Item.getItemFromBlock(Blocks.SAPLING)) {
			List<String> tt = event.getToolTip();
			if (LepidodendronConfig.showTooltips) {
				switch (event.getItemStack().getMetadata()) {
					case 0:
					default: //Oak
						tt.add("Type: Flowering tree");
						tt.add("Periods: [late Cretaceous (?) -] Paleogene - Neogene - Pleistocene - present");
						tt.add("Propagation: acorns drop from leaves");
						break;

					case 1: //Spruce
						tt.add("Type: Coniferous tree");
						tt.add("Periods: late Cretaceous - Paleogene - Neogene - Pleistocene - present");
						tt.add("Propagation: fruit/cone");
						break;

					case 2: //Birch
						tt.add("Type: Flowering tree");
						tt.add("Periods: Paleogene - Neogene - Pleistocene - present");
						tt.add("Propagation: fruits drop from leaves");
						break;

					case 3: //Jungle
						tt.add("Type: Flowering tree");
						tt.add("Periods: Paleogene - Neogene - Pleistocene - present");
						tt.add("Propagation: fruits drop from leaves");
						break;

					case 4: //Acacia
						tt.add("Type: Flowering tree");
						tt.add("Periods: Paleogene - Neogene - Pleistocene - present");
						tt.add("Propagation: fruits drop from leaves");
						break;

					case 5: //Dark Oak
						tt.add("Type: Flowering tree");
						tt.add("Periods: [late Cretaceous (?) -] Paleogene - Neogene - Pleistocene - present");
						tt.add("Propagation: acorns drop from leaves");
						break;
				}
			}
		}

		if (event.getItemStack().getItem() == Items.SPAWN_EGG) {
			//Get Entity period:
			ResourceLocation resourceLocation = ItemMonsterPlacer.getNamedIdFrom(event.getItemStack());
			if (resourceLocation == null) {
				return;
			}
			Class ee = EntityList.getClassFromName(resourceLocation.toString());
			List<String> tt = event.getToolTip();
			try {
				tt.add(I18n.translateToLocal("helper.pf_period.name") + ": " + ee.getMethod("getPeriod", (Class[]) null).invoke(null).toString());
			} catch (Throwable throwable) {
				//Do nothing - it's all good
			}
			try {
				tt.add(I18n.translateToLocal("helper.pf_habitat.name") + ": " + ee.getMethod("getHabitat", (Class[]) null).invoke(null).toString());
			} catch (Throwable throwable) {
				//Do nothing - it's all good
			}

		}

		if (OreDictionary.containsMatch(false, OreDictionary.getOres("logResin"),
				event.getItemStack())) {
			List<String> tt = event.getToolTip();
			try {
				tt.add(I18n.translateToLocal("helper.pf_resinable.name"));
			} catch (Throwable throwable) {
				//Do nothing - it's all good
			}
		}

		if (OreDictionary.containsMatch(false, OreDictionary.getOres("logLatex"),
				event.getItemStack())) {
			List<String> tt = event.getToolTip();
			try {
				tt.add(I18n.translateToLocal("helper.pf_latexable.name"));
			} catch (Throwable throwable) {
				//Do nothing - it's all good
			}
		}

		if (event.getItemStack().getItem().getRegistryName().toString().equalsIgnoreCase("lepidodendron:rubber_boots")) {
			List<String> tt = event.getToolTip();
			try {
				tt.add(I18n.translateToLocal("helper.pf_rubber_boots.name"));
			} catch (Throwable throwable) {
				//Do nothing - it's all good
			}
		}

	}

	@SubscribeEvent //Add portal fossil trades
	public void onEvent(MerchantTradeOffersEvent event) {
		int i = -1;
		if ((!event.getList().isEmpty()) && (!event.getMerchant().getWorld().isRemote)) {
			MerchantRecipeList MerchantRecipeFinal = (MerchantRecipeList) event.getList().clone();
			for (MerchantRecipe recipe: event.getList()) {
				i ++;
				if (recipe.getItemToSell().getItem() == ItemFossilClean.block) {
					if (!recipe.getItemToSell().hasTagCompound()) {
						MerchantRecipeFinal.remove(i);

						ItemStack stackPrototaxites = new ItemStack(ItemFossilClean.block, 1);
						NBTTagCompound plantNBT = new NBTTagCompound();
						plantNBT.setString("id", "lepidodendron:prototaxites");
						NBTTagCompound stackNBT = new NBTTagCompound();
						stackNBT.setTag("PFPlant", plantNBT);
						stackPrototaxites.setTagCompound(stackNBT);
						MerchantRecipe recipePrototaxites = new MerchantRecipe(new ItemStack(Items.EMERALD, 5), stackPrototaxites);
						MerchantRecipeFinal.add(recipePrototaxites);

						ItemStack stackArchaeopteris = new ItemStack(ItemFossilClean.block, 1);
						plantNBT = new NBTTagCompound();
						plantNBT.setString("id", "lepidodendron:archaeopteris_sapling");
						stackNBT = new NBTTagCompound();
						stackNBT.setTag("PFPlant", plantNBT);
						stackArchaeopteris.setTagCompound(stackNBT);
						MerchantRecipe recipeArchaeopteris = new MerchantRecipe(new ItemStack(Items.EMERALD, 5), stackArchaeopteris);
						MerchantRecipeFinal.add(recipeArchaeopteris);

						ItemStack stackCalamites = new ItemStack(ItemFossilClean.block, 1);
						plantNBT = new NBTTagCompound();
						plantNBT.setString("id", "lepidodendron:calamites_sapling");
						stackNBT = new NBTTagCompound();
						stackNBT.setTag("PFPlant", plantNBT);
						stackCalamites.setTagCompound(stackNBT);
						MerchantRecipe recipeCalamites = new MerchantRecipe(new ItemStack(Items.EMERALD, 5), stackCalamites);
						MerchantRecipeFinal.add(recipeCalamites);

						ItemStack stackGlossopteris = new ItemStack(ItemFossilClean.block, 1);
						plantNBT = new NBTTagCompound();
						plantNBT.setString("id", "lepidodendron:glossopterissapling");
						stackNBT = new NBTTagCompound();
						stackNBT.setTag("PFPlant", plantNBT);
						stackGlossopteris.setTagCompound(stackNBT);
						MerchantRecipe recipeGlossopteris = new MerchantRecipe(new ItemStack(Items.EMERALD, 5), stackGlossopteris);
						MerchantRecipeFinal.add(recipeGlossopteris);

						ItemStack stackDicroidium = new ItemStack(ItemFossilClean.block, 1);
						plantNBT = new NBTTagCompound();
						plantNBT.setString("id", "lepidodendron:dicroidium_f_sapling");
						stackNBT = new NBTTagCompound();
						stackNBT.setTag("PFPlant", plantNBT);
						stackDicroidium.setTagCompound(stackNBT);
						MerchantRecipe recipeDicroidium = new MerchantRecipe(new ItemStack(Items.EMERALD, 5), stackDicroidium);
						MerchantRecipeFinal.add(recipeDicroidium);
					}
				}
			}
			event.setList(MerchantRecipeFinal);
		}
	}

	@SubscribeEvent //Make obsidian variants
	public void onEvent(BlockEvent.FluidPlaceBlockEvent event) {
		if (!event.getWorld().isRemote) {
			if (event.getState().getBlock() == Blocks.OBSIDIAN) {
				if (event.getWorld().rand.nextFloat() > 0.98) {
					if (event.getWorld().rand.nextFloat() > 0.5) {
						event.setNewState(BlockObsidianSulphurOre.block.getDefaultState());
					}
					else {
						event.setNewState(BlockObsidianZirconOre.block.getDefaultState());
					}
				}
			}
		}
	}

	@SubscribeEvent //Allow acid to form sourceblocks
	public void onEvent(BlockEvent.CreateFluidSourceEvent event) {
		if ((!event.getWorld().isRemote) && LepidodendronConfig.sulphuricAcidInfinite) {
			try { //Its possible that our fluid isnt registered in a modpack if another mod took precedence
				if (event.getState().getBlock() == BlockAcid.block) {
					event.setResult(Event.Result.ALLOW);
				}
			}
			catch (RuntimeException exception) {
			}
		}
	}

	@SubscribeEvent //Advancement for DNA
	public void onEvent(PlayerEvent.ItemCraftedEvent event) {
		if ((!event.player.getEntityWorld().isRemote) && event.crafting.getItem() == ItemPhialDNA.block) {
			if (event.player instanceof EntityPlayerMP) {
				ModTriggers.DNA_CRAFT.trigger((EntityPlayerMP) event.player);
			}
		}
	}

}
