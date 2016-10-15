package T145.magistics.tiles;

import java.util.List;

import T145.magistics.Magistics;
import T145.magistics.api.tiles.TileVisUser;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;

public class TileCrucible extends TileVisUser {
	public float pureVis = 0F;
	public float taintedVis = 0F;
	private float pureBuffer;
	private float taintedBuffer;

	private float maxVis;
	private float conversion;
	private float speed;
	private float wait;
	private boolean updateNextPeriod;

	public int smeltDelay;
	private int soundDelay = 25;

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		pureVis = tag.getFloat("pureVis");
		taintedVis = tag.getFloat("taintedVis");
		setTier();
	}

	private void setTier() {
		switch (getBlockMetadata()) {
		case 0:
			maxVis = 500F;
			conversion = 0.5F;
			speed = 0.25F;
			break;
		case 1:
			maxVis = 600F;
			conversion = 0.6F;
			speed = 0.5F;
			break;
		case 2:
			maxVis = 750F;
			conversion = 0.7F;
			speed = 0.75F;
			break;
		case 3:
			maxVis = 750F;
			conversion = 0.4F;
			speed = 0.75F;
			break;
		}
	}

	@Override
	public void readClientDataFromNBT(NBTTagCompound tag) {
		pureVis = tag.getFloat("pureVis");
		taintedVis = tag.getFloat("taintedVis");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tag.setFloat("pureVis", pureVis);
		tag.setFloat("taintedVis", taintedVis);
		return super.writeToNBT(tag);
	}

	@Override
	public void writeClientDataToNBT(NBTTagCompound tag) {
		tag.setFloat("pureVis", pureVis);
		tag.setFloat("taintedVis", taintedVis);
	}

	private List<EntityItem> getContents() {
		return worldObj.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(getPos().getX(), getPos().getY(), getPos().getZ(), getPos().getX() + 1.0D, getPos().getY() + 1.0D, getPos().getZ() + 1.0D));
	}

	public boolean ejectContents(EntityPlayer player) {
		for (EntityItem item : getContents()) {
			item.noClip = true;
			item.setNoPickupDelay();
			item.motionX = (player.posX - item.posX) * 0.20000000298023224D;
			item.motionY = (player.posY - item.posY) * 0.20000000298023224D;
			item.motionZ = (player.posZ - item.posZ) * 0.20000000298023224D;
			item.moveEntity(item.motionX, item.motionY, item.motionZ);
			item.noClip = false;
			return true;
		}
		return false;
	}

	@Override
	public void update() {
		super.update();
		
		if (this.hasWorldObj()) {
		}
	}
}