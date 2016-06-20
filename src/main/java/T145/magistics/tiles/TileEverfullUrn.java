package T145.magistics.tiles;

import java.awt.Color;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.BlockFire;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.config.Config;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXBlockBubble;
import thaumcraft.common.tiles.TileAlembic;
import thaumcraft.common.tiles.TileJarFillable;
import vazkii.botania.common.block.mana.BlockAlchemyCatalyst;
import vazkii.botania.common.block.tile.TileAltar;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.network.NetworkRegistry;

public class TileEverfullUrn extends TileEntity {
	private int soundDelay = 33;

	@Override
	public boolean canUpdate() {
		return true;
	}

	public boolean canFillApothecary() {
		return Loader.isModLoaded("Botania");
	}

	public boolean hasAlchemyCatalyst() {
		return canFillApothecary() && worldObj.getBlock(xCoord, yCoord - 1, zCoord) instanceof BlockAlchemyCatalyst;
	}

	public boolean isActive() {
		Block block = worldObj.getBlock(xCoord, yCoord + 1, zCoord);
		return block.getMaterial() == Material.air || block.getMaterial() == Config.airyMaterial;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		if (hasWorldObj() && isActive()) {
			--soundDelay;

			if (soundDelay == 0) {
				worldObj.playSoundEffect(xCoord + 0.5F, yCoord + 0.5F, zCoord + 0.5F, "liquid.water", worldObj.rand.nextFloat() * 0.2F + 0.2F, worldObj.rand.nextFloat() * 1.0F + 0.5F);
				soundDelay = 33;
			}

			int range = 6;

			for (int x = -range; x <= range; ++x) {
				for (int y = -range; y <= range; ++y) {
					for (int z = -range; z <= range; ++z) {
						int xx = xCoord + x;
						int yy = yCoord + y;
						int zz = zCoord + z;
						TileEntity tile = worldObj.getTileEntity(xx, yy, zz);

						if (tile == null) {
							Block block = worldObj.getBlock(xx, yy, zz);

							if (block instanceof BlockFire) {
								bubbleAt(xx, yy, zz);
								worldObj.setBlockToAir(xx, yy, zz);
							} else if (block instanceof BlockFarmland) {
								if (worldObj.getBlockMetadata(xx, yy, zz) < 4) {
									bubbleAt(xx, yy, zz);
									worldObj.setBlockMetadataWithNotify(xx, yy, zz, 7, 2);
								}
							} else if (block instanceof BlockCauldron) {
								if (worldObj.getBlockMetadata(xx, yy, zz) < 3) {
									bubbleAt(xx, yy, zz);
									worldObj.setBlockMetadataWithNotify(xx, yy, zz, 3, 2);
								}
							}
						} else {
							if (tile instanceof IFluidHandler) {
								IFluidHandler tank = (IFluidHandler) tile;
								FluidStack water = new FluidStack(FluidRegistry.WATER, FluidContainerRegistry.BUCKET_VOLUME);

								for (ForgeDirection dir : ForgeDirection.values()) {
									if (tank.fill(dir, water, false) == FluidContainerRegistry.BUCKET_VOLUME) {
										bubbleAt(xx, yy, zz);
										tank.fill(dir, water, true);
										return;
									}
								}
							} else if (tile instanceof IFluidTank) {
								IFluidTank tank = (IFluidTank) tile;
								FluidStack water = new FluidStack(FluidRegistry.WATER, FluidContainerRegistry.BUCKET_VOLUME);

								if (tank.fill(water, false) == FluidContainerRegistry.BUCKET_VOLUME) {
									bubbleAt(xx, yy, zz);
									tank.fill(water, true);
									return;
								}
							} else if (canFillApothecary() && tile instanceof TileAltar) {
								TileAltar apothecary = (TileAltar) tile;

								if (!apothecary.hasWater()) {
									bubbleAt(xx, yy, zz);
									apothecary.setWater(true);
									worldObj.func_147453_f(xx, yy, zz, worldObj.getBlock(xx, yy, zz));
								}
							} else if (hasAlchemyCatalyst()) {
								if (tile instanceof TileJarFillable) {
									TileJarFillable container = (TileJarFillable) tile;

									if (container.doesContainerAccept(Aspect.WATER) && container.amount < container.maxAmount) {
										bubbleAt(xx, yy, zz);
										container.addToContainer(Aspect.WATER, 1);
										return;
									}
								} else if (tile instanceof TileAlembic) {
									TileAlembic alembic = (TileAlembic) tile;

									if (alembic.aspectFilter == Aspect.WATER && alembic.amount < alembic.maxAmount) {
										bubbleAt(xx, yy, zz);
										alembic.addToContainer(Aspect.WATER, 1);
										return;
									}
								}
							}
						}
					}
				}
			}

			EntityPlayer player = worldObj.getClosestPlayer(xCoord, yCoord, zCoord, range);

			if (player != null && player.isBurning()) {
				bubbleAt(player.serverPosX, player.serverPosY, player.serverPosZ);
				player.extinguish();
			}
		}
	}

	public void bubbleAt(int x, int y, int z) {
		PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockBubble(x, y, z, new Color(0.33F, 0.33F, 1F).getRGB()), new NetworkRegistry.TargetPoint(worldObj.provider.dimensionId, x, y, z, 32D));
		worldObj.playSoundEffect(x, y, z, "thaumcraft:bubble", 0.15F, 1F);
	}
}