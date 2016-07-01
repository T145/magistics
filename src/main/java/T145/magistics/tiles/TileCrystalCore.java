package T145.magistics.tiles;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.nodes.INode;
import thaumcraft.api.nodes.NodeType;
import thaumcraft.api.wands.IWandable;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.blocks.BlockCosmeticSolid;
import thaumcraft.common.config.Config;
import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;
import thaumcraft.common.tiles.TileJarNode;

public class TileCrystalCore extends TileThaumcraft implements IWandable {
	public float speed = 0F;
	public float rotation = 0F;

	private boolean active = false;
	private boolean draining = false;
	private boolean neighborChange = false;

	private int count = -1;

	public int drainX = 0;
	public int drainY = 0;
	public int drainZ = 0;
	public int drainColor = 0;
	private List<ChunkCoordinates> nodes = null;

	public boolean isActive() {
		return active;
	}

	public boolean isDraining() {
		return draining;
	}

	public boolean hasNodes() {
		return nodes != null && !nodes.isEmpty();
	}

	public boolean isObsidianTotem(int x, int y, int z) {
		Block block = worldObj.getBlock(x, y, z);
		return block instanceof BlockCosmeticSolid && worldObj.getBlockMetadata(x, y, z) == 0;
	}

	private boolean hasAltar() {
		return isObsidianTotem(xCoord - 1, yCoord - 1, zCoord - 1)
				&& isObsidianTotem(xCoord + 1, yCoord - 1, zCoord + 1)
				&& isObsidianTotem(xCoord - 1, yCoord - 1, zCoord + 1)
				&& isObsidianTotem(xCoord + 1, yCoord - 1, zCoord - 1)
				&& isObsidianTotem(xCoord - 1, yCoord - 2, zCoord - 1)
				&& isObsidianTotem(xCoord + 1, yCoord - 2, zCoord + 1)
				&& isObsidianTotem(xCoord - 1, yCoord - 2, zCoord + 1)
				&& isObsidianTotem(xCoord + 1, yCoord - 2, zCoord - 1);
	}

	public boolean canActivate() {
		Block block = worldObj.getBlock(xCoord, yCoord + 1, zCoord);
		return block.getMaterial() == Material.air || block.getMaterial() == Config.airyMaterial && hasWorldObj() && !active && hasAltar() && hasNodes();
	}

	@Override
	public void readCustomNBT(NBTTagCompound tag) {
		active = tag.getBoolean("active");
		speed = tag.getFloat("speed");
	}

	@Override
	public void writeCustomNBT(NBTTagCompound tag) {
		tag.setBoolean("active", active);
		tag.setFloat("speed", speed);
	}

	@Override
	public boolean canUpdate() {
		return true;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		if (hasWorldObj()) {
			if (nodes == null || nodes.isEmpty()) {
				findNodes();
			}

			++count;
			rotation += speed;

			if (active) {
				if (speed < 1F) {
					speed += 0.001F + speed / 100F;
				} else {
					speed = 1F;
				}
			} else if (speed > 0F) {
				speed -= 0.01F;
			} else {
				speed = 0F;
			}

			if (worldObj.isRemote && active && speed > 0.9F) {
				if (count % 2 == 0) {
					switch (count % 8 / 2) {
					case 0:
						Thaumcraft.proxy.blockRunes(worldObj, xCoord - 1, yCoord - 1, zCoord - 1, 0.3F + worldObj.rand.nextFloat() * 0.7F, 0F, 0.3F + worldObj.rand.nextFloat() * 0.7F, 20, 0.03F);
						break;
					case 1:
						Thaumcraft.proxy.blockRunes(worldObj, xCoord + 1, yCoord - 1, zCoord + 1, 0.3F + worldObj.rand.nextFloat() * 0.7F, 0F, 0.3F + worldObj.rand.nextFloat() * 0.7F, 20, 0.03F);
						break;
					case 2:
						Thaumcraft.proxy.blockRunes(worldObj, xCoord - 1, yCoord - 1, zCoord + 1, 0.3F + worldObj.rand.nextFloat() * 0.7F, 0F, 0.3F + worldObj.rand.nextFloat() * 0.7F, 20, 0.03F);
						break;
					case 3:
						Thaumcraft.proxy.blockRunes(worldObj, xCoord + 1, yCoord - 1, zCoord - 1, 0.3F + worldObj.rand.nextFloat() * 0.7F, 0F, 0.3F + worldObj.rand.nextFloat() * 0.7F, 20, 0.03F);
						break;
					}
				}

				if (count % 20 == 0 && draining) {
					Thaumcraft.proxy.beam(worldObj, xCoord + 0.5, yCoord + 0.5 + speed, zCoord + 0.5, drainX + 0.5, drainY + 0.5, drainZ + 0.5, 1, drainColor, true, 1.5F, 20);
				}
			}

			boolean discoverNodes = false;

			if (count % 20 == 0) {
				if (neighborChange && hasNodes()) {
					worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, getBlockType());
					neighborChange = false;
				}

				if (active && speed > 0.9F) {
					if (!(worldObj.getTileEntity(xCoord, yCoord + 1, zCoord) instanceof INode)) {
						ThaumcraftWorldGenerator.createNodeAt(worldObj, xCoord, yCoord + 1, zCoord, NodeType.NORMAL, null, new AspectList());
					}

					INode core = (INode) worldObj.getTileEntity(xCoord, yCoord + 1, zCoord);

					if (hasAltar() && core != null) {
						for (ChunkCoordinates coord : nodes) {
							int x = coord.posX;
							int y = coord.posY;
							int z = coord.posZ;

							TileEntity tile = worldObj.getTileEntity(x, y, z);

							if (tile != null && tile != core) {
								INode node = (INode) tile;

								for (Aspect aspect : node.getAspects().getAspects()) {
									if (node.getAspects().getAmount(aspect) > 0) {
										node.takeFromContainer(aspect, 1);
										core.getAspects().add(aspect, 1);

										if (!worldObj.isRemote && !draining) {
											discoverNodes = true;
										}

										neighborChange = true;
										draining = true;
										drainColor = aspect.getColor();
										drainX = x;
										drainY = y;
										drainZ = z;

										if (!draining) {
											discoverNodes = true;
										}
										return;
									}
								}

								if (node.getAspects().visSize() == 0) {
									draining = false;
									worldObj.setBlockToAir(x, y, z);
									Thaumcraft.proxy.burst(worldObj, x + 0.5D, y + 0.5D, z + 0.5D, 1F);
									worldObj.playSound(x + 0.5D, y + 0.5D, z + 0.5D, "thaumcraft:craftfail", 1F, 1F, false);
								}
							}
						}
					}

					deactivate();
				}
			}

			if (count % 100 == 0 && (discoverNodes || nodes.size() == 0)) {
				findNodes();
			}
		}
	}

	private void deactivate() {
		active = false;
		draining = false;
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

	private void findNodes() {
		nodes = new ArrayList<ChunkCoordinates>();
		int range = 20;

		for (int x = -range; x <= range; ++x) {
			for (int y = -range; y <= range; ++y) {
				for (int z = -range; z <= range; ++z) {
					int xx = xCoord + x;
					int yy = yCoord + y;
					int zz = zCoord + z;
					TileEntity tile = worldObj.getTileEntity(xx, yy, zz);

					if (tile != null && tile instanceof INode && !(tile instanceof TileJarNode) && tile != worldObj.getTileEntity(xCoord, yCoord + 1, zCoord)) {
						nodes.add(new ChunkCoordinates(xx, yy, zz));
					}
				}
			}
		}
	}

	@Override
	public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side, int md) {
		if (canActivate()) {
			active = true;
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			markDirty();
			player.swingItem();
			return 0;
		}
		return -1;
	}

	@Override
	public ItemStack onWandRightClick(World world, ItemStack wandstack, EntityPlayer player) {
		return wandstack;
	}

	@Override
	public void onUsingWandTick(ItemStack wandstack, EntityPlayer player, int count) {}

	@Override
	public void onWandStoppedUsing(ItemStack wandstack, World world, EntityPlayer player, int count) {}
}