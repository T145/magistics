package T145.magistics.tiles;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.common.lib.events.EssentiaHandler;

public class TileWarpSentinel extends TileThaumcraft implements IAspectContainer {
	private AspectList buffer = new AspectList();
	private boolean hasTeleported = false;
	private int timer = 100;

	@Override
	public void readCustomNBT(NBTTagCompound tag) {
		hasTeleported = tag.getBoolean("teleported");
		buffer.readFromNBT(tag);
	}

	@Override
	public void writeCustomNBT(NBTTagCompound tag) {
		tag.setBoolean("teleported", hasTeleported);
		buffer.writeToNBT(tag);
	}

	@Override
	public boolean canUpdate() {
		return true;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		if (timer <= 0) {
			timer = 100;
		} else {
			--timer;
		}

		if (hasWorldObj() && !worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {
			double xx = xCoord + 0.5;
			double yy = yCoord + 1;
			double zz = zCoord + 0.5;
			int range = 8;

			EntityPlayer player = worldObj.getClosestPlayer(xx, yy, zz, range);

			if (hasTeleported) {
				List<EntityPlayer> targets = worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(xx, yy, zz, xx + range, yy + range, zz + range));

				if (targets.isEmpty()) {
					hasTeleported = false;
				}

				return;
			} else {
				if (player != null) {
					if (buffer.visSize() > 0) {
						if (timer == 0) {
							takeFromContainer(Aspect.TRAVEL, 1);
							player.setPositionAndUpdate(xx, yy, zz);
							hasTeleported = true;
						} else {
							return;
						}
					} else {
						if (EssentiaHandler.drainEssentia(this, Aspect.TRAVEL, ForgeDirection.UP, range)) {
							addToContainer(Aspect.TRAVEL, 1);
							worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
							markDirty();
							return;
						}
					}
				}
			}
		}
	}

	public boolean addEssentia(Aspect aspect, int amount) {
		return addToContainer(aspect, amount) == 1;
	}

	@Override
	public int addToContainer(Aspect aspect, int amount) {
		if (doesContainerAccept(aspect)) {
			buffer.add(aspect, amount);
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int containerContains(Aspect aspect) {
		return buffer.getAmount(aspect) > 0 ? 1 : 0;
	}

	@Override
	public boolean doesContainerAccept(Aspect aspect) {
		return aspect == Aspect.TRAVEL;
	}

	@Override
	@Deprecated
	public boolean doesContainerContain(AspectList aspects) {
		return false;
	}

	@Override
	public boolean doesContainerContainAmount(Aspect aspect, int amount) {
		return buffer.getAmount(aspect) == amount;
	}

	@Override
	public AspectList getAspects() {
		return buffer;
	}

	@Override
	@Deprecated
	public void setAspects(AspectList aspects) {}

	@Override
	@Deprecated
	public boolean takeFromContainer(AspectList aspects) {
		return false;
	}

	@Override
	public boolean takeFromContainer(Aspect aspect, int amount) {
		return amount == 1 ? buffer.reduce(aspect, amount) : false;
	}
}