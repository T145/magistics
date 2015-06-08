package T145.magistics.common.tiles;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.client.fx.particles.FXEssentiaTrail;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.tiles.TileCrucible;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.TileAltar;
import T145.magistics.common.Magistics;
import cpw.mods.fml.common.Loader;

public class TileEverfullUrn extends TileEntity implements IFluidTank, IFluidHandler {
	private int countdown = 33;
	private int ticks = 0;
	private int drainTicks = 0;
	private int dX;
	private int dY;
	private int dZ;
	private int excessTicks = 0;
	private int drainType = 0;
	private float distance = 0;
	private int range = 3;
	private int yRange = 2;
	private EntityPlayer burningPlayer;

	@Override
	public FluidStack getFluid() {
		return new FluidStack(FluidRegistry.WATER, 100);
	}

	@Override
	public int getFluidAmount() {
		return 9999;
	}

	@Override
	public int getCapacity() {
		return 9999;
	}

	@Override
	public FluidTankInfo getInfo() {
		return new FluidTankInfo(this);
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		return 0;
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		int drained = maxDrain;

		if (999999 < drained) {
			drained = 999999;
		}

		FluidStack stack = new FluidStack(FluidRegistry.WATER, drained);
		return stack;
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		return fill(resource, doFill);
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		if (!resource.isFluidEqual(new FluidStack(FluidRegistry.WATER, 1)) || !(from == ForgeDirection.UP))
			return null;

		return drain(resource.amount, doDrain);

	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		if (from == ForgeDirection.UP) {
			return drain(maxDrain, doDrain);
		} else {
			FluidStack stack = new FluidStack(FluidRegistry.WATER, 0);
			return stack;
		}
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return false;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return from == ForgeDirection.UP;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[] { getInfo() };
	}

	@Override
	public boolean canUpdate() {
		return true;
	}

	public static Block getAltar() {
		return ModBlocks.altar;
	}

	public static boolean needsWater(TileEntity tile) {
		TileAltar altar = (TileAltar) tile;
		return !altar.hasWater;
	}

	public static void fillWater(TileEntity tile) {
		TileAltar altar = (TileAltar) tile;
		altar.hasWater = true;
	}

	public void spawnWaterOnPlayer(World worldObj, int xCoord, int yCoord, int zCoord, EntityPlayer player) {
		FXEssentiaTrail fx = new FXEssentiaTrail(worldObj, xCoord + 0.5F, yCoord + 1.1F, zCoord + 0.5F, player.posX, player.posY, player.posZ, 5, Aspect.TOOL.getColor(), 1.0F);
		Minecraft.getMinecraft().effectRenderer.addEffect(fx);
	}

	public void spawnRandomWaterFountain(World worldObj, int xCoord, int yCoord, int zCoord) {
		FXEssentiaTrail fx = new FXEssentiaTrail(worldObj, xCoord + 0.5F, yCoord + 1.1F, zCoord + 0.5F, xCoord + 0.5F + ((Math.random()) - 0.5), yCoord + 2.1F, zCoord + 0.5F + ((Math.random()) - 0.5), 5, Aspect.TOOL.getColor(), 1.0F);
		Minecraft.getMinecraft().effectRenderer.addEffect(fx);
	}

	public void spawnWaterAtLocation(World worldObj, double xCoord, double yCoord, double zCoord, double dX, double dY, double dZ) {
		FXEssentiaTrail fx = new FXEssentiaTrail(worldObj, xCoord, yCoord, zCoord, dX, dY, dZ, 5, Aspect.TOOL.getColor(), 1.0F);
		Minecraft.getMinecraft().effectRenderer.addEffect(fx);
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		ticks++;

		if (worldObj.getBlock(xCoord, yCoord + 1, zCoord).getMaterial() == Material.air || worldObj.getBlock(xCoord, yCoord + 1, zCoord).getMaterial() == Config.airyMaterial) {
			--countdown;

			if (countdown == 0) {
				worldObj.playSoundEffect((double) ((float) xCoord + 0.5F), (double) ((float) yCoord + 0.5F), (double) ((float) zCoord + 0.5F), "liquid.water", worldObj.rand.nextFloat() * 0.2F + 0.2F, worldObj.rand.nextFloat() * 1.0F + 0.5F);
				countdown = 33;
			}

			/*if (Minecraft.getMinecraft().gameSettings.fancyGraphics && !Config.lowGfx  || countdown % 3 == 0) {
				FXWisp var1 = new FXWisp(worldObj, (double) ((float) xCoord + 0.5F), (double) (yCoord + 1), (double) ((float) zCoord + 0.5F), (double) ((float) xCoord + 0.5F + worldObj.rand.nextFloat() - worldObj.rand.nextFloat()), (double) ((float) yCoord + 4.5F + worldObj.rand.nextFloat()), (double) ((float) zCoord + 0.5F + worldObj.rand.nextFloat() - worldObj.rand.nextFloat()), 0.15F, 7);
				var1.setGravity(0.2F);
				var1.noClip = false;
				var1.shrink = true;
				Minecraft.getMinecraft().effectRenderer.addEffect(var1);
			}*/

			if (drainTicks > 0 && drainType == 1) {
				if (worldObj.getBlock(dX, dY, dZ) == ConfigBlocks.blockMetalDevice) {
					if (worldObj.getBlockMetadata(dX, dY, dZ) == 0) {
						TileCrucible tile = ((TileCrucible) (worldObj.getTileEntity(dX, dY, dZ)));
						if (tile.tank.getFluidAmount() < tile.tank.getCapacity()) {
							drainTicks = (tile.tank.getCapacity() - tile.tank.getFluidAmount()) / 10;
							if (excessTicks > (20 * distance)) {
								tile.fill(ForgeDirection.SOUTH, new FluidStack(FluidRegistry.WATER, 10), true);
							}
							if (drainTicks % 5 == 0 && worldObj.isRemote && excessTicks < (40 * distance)) {
								spawnWaterAtLocation(worldObj, xCoord + 0.5F, yCoord + 1.1F, zCoord + 0.5F, dX + 0.5F, dY + 1.1F, dZ + 0.5F);
							}
							excessTicks++;
							drainTicks--;
						} else {
							drainTicks = 0;
						}
					} else {
						drainTicks = 0;
					}
				} else {
					drainTicks = 0;
				}
			}

			if (drainTicks > 0 && drainType == 3) {
				if (Loader.isModLoaded("Botania")) {
					if (worldObj.getBlock(dX, dY, dZ) == getAltar()) {
						TileEntity tile = ((worldObj.getTileEntity(dX, dY, dZ)));
						if (needsWater(tile)) {
							if (drainTicks % 5 == 0 && worldObj.isRemote && excessTicks < (40 * distance)) {
								spawnWaterAtLocation(worldObj, xCoord + 0.5F, yCoord + 1.1F, zCoord + 0.5F, dX + 0.5F, dY + 1.1F, dZ + 0.5F);
							}
							excessTicks++;
							drainTicks--;
							if (drainTicks == 0) {
								fillWater(tile);
							}
						} else {
							drainTicks = 0;
						}
					} else {
						drainTicks = 0;
					}
				} else {
					drainTicks = 0;
				}
			}

			if (drainTicks > 0 && drainType == 2) {
				EntityPlayer player = burningPlayer;
				List<EntityPlayer> players = worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(xCoord - range, yCoord - yRange, zCoord - range, xCoord + range, yCoord + yRange, zCoord + range));

				if (players.contains(player) && player.isBurning()) {

					if (drainTicks % 3 == 0 && worldObj.isRemote && excessTicks < (40 * distance)) {
						spawnWaterOnPlayer(worldObj, xCoord, yCoord, zCoord, player);
					}

					excessTicks++;
					drainTicks--;

					if (excessTicks > (15 * distance)) {
						player.extinguish();
						drainTicks = 0;
						worldObj.playSoundAtEntity(player, "liquid.swim", 2.0F, 1.0F);
						for (int x = -1; x < 2; x++) {
							for (int z = -1; z < 2; z++) {
								if (worldObj.getBlock((int) player.posX + x, (int) player.posY, (int) player.posZ + z) == Blocks.fire) {
									worldObj.setBlockToAir((int) player.posX + x, (int) player.posY, (int) player.posZ + z);
								}
							}
						}

					}
				} else {
					drainTicks = 0;
				}
			}

			if (ticks % 2 == 0 && worldObj.isRemote && (drainTicks <= 0 || excessTicks > (40 * distance))) {
				spawnRandomWaterFountain(worldObj, xCoord, yCoord, zCoord);
			}

			if (ticks % 5 == 0) {
				if (drainTicks == 0 || drainType != 2) {
					List<EntityPlayer> players = worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(xCoord - range, yCoord - yRange, zCoord - range, xCoord + range, yCoord + yRange, zCoord + range));
					for (EntityPlayer player : players) {
						if (player.isBurning()) {
							distance = (float) Math.sqrt(Math.pow(xCoord - player.posX, 2) + Math.pow(yCoord - player.posY, 2) + Math.pow(zCoord - player.posZ, 2));
							drainTicks = 100;
							excessTicks = 0;
							drainType = 2;
							burningPlayer = player;
							break;
						}
					}
				}
				if (drainTicks == 0) {
					for (int x = (-1 * range); x < (range + 1); x++) {
						for (int z = (-1 * range); z < (range + 1); z++) {
							for (int y = (-1 * yRange); y < (yRange + 1); y++) {
								
								if (worldObj.getBlock(xCoord + x, yCoord + y, zCoord + z) == ConfigBlocks.blockMetalDevice) {
									if (worldObj.getBlockMetadata(xCoord + x, yCoord + y, zCoord + z) == 0) {
										TileCrucible tile = ((TileCrucible) (worldObj.getTileEntity(xCoord + x, yCoord + y, zCoord + z)));
										if (tile.tank.getFluidAmount() < tile.tank.getCapacity()) {
											distance = (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
											drainTicks = (tile.tank.getCapacity() - tile.tank.getFluidAmount()) / 10;
											excessTicks = 0;
											drainType = 1;
											dX = xCoord + x;
											dY = yCoord + y;
											dZ = zCoord + z;
											break;
										}
									}
								}

								if (Loader.isModLoaded("Botania")) {
									if (worldObj.getBlock(xCoord + x, yCoord + y, zCoord + z) == getAltar()) {
										TileEntity tile = ((worldObj.getTileEntity(xCoord + x, yCoord + y, zCoord + z)));
										if (needsWater(tile)) {
											distance = (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
											drainTicks = 100;
											excessTicks = 0;
											drainType = 3;
											dX = xCoord + x;
											dY = yCoord + y;
											dZ = zCoord + z;
											break;
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
}