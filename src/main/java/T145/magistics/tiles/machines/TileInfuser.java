package T145.magistics.tiles.machines;

import T145.magistics.api.logic.IFacing;
import T145.magistics.api.magic.IQuintessenceManager;
import T145.magistics.containers.ContainerInfuser;
import T145.magistics.tiles.MTileInventory;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IInteractionObject;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileInfuser extends MTileInventory implements IInteractionObject, IFacing, IQuintessenceManager {

	public float cookCost;
	public float cookTime;

	protected boolean active;
	protected boolean crafting;
	protected int angle;
	protected int soundDelay;

	private int boost;
	private int boostDelay = 20;
	private int suction;
	private EnumFacing facing = EnumFacing.SOUTH;

	public boolean isActive() {
		return active;
	}

	public boolean isCrafting() {
		return crafting;
	}

	public boolean isDark() {
		return false;
	}

	public boolean isDormant() {
		return !active && !crafting;
	}

	public float getDiskAngle() {
		return angle;
	}

	public int getBoost() {
		return boost;
	}

	public void setBoost(int boost) {
		this.boost = boost;
	}

	@Override
	public boolean canConnect(EnumFacing facing) {
		return facing != EnumFacing.UP;
	}

	@Override
	public int getSuction() {
		return suction;
	}

	@Override
	public void setSuction(int suction) {
		this.suction = suction;
	}

	@Override
	public EnumFacing getFacing() {
		return facing;
	}

	@Override
	public void setFacing(EnumFacing facing) {
		this.facing = facing;
	}

	@Override
	public void setFacingFromEntity(EntityLivingBase placer) {
		setFacing(EnumFacing.getDirectionFromEntityLiving(pos, placer));
	}

	@Override
	public String getName() {
		return "tile.infuser.light.name";
	}

	@Override
	public boolean hasCustomName() {
		return true;
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentTranslation(getName(), new Object[0]);
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer player) {
		return new ContainerInfuser(playerInventory, this);
	}

	@Override
	public String getGuiID() {
		return "magistics:infuser";
	}

	@Override
	public int getSizeInventory() {
		return 8;
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, boolean simulate) {
		return slot > (isDark() ? 0 : 1);
	}

	@Override
	public boolean canExtractItem(int slot, int amount, boolean simulate) {
		return true;
	}

	@Override
	public void writePacketNBT(NBTTagCompound compound) {
		super.writePacketNBT(compound);

		compound.setInteger("Facing", facing.ordinal());
		compound.setInteger("Suction", suction);
		compound.setFloat("CookCost", cookCost);
		compound.setFloat("CookTime", cookTime);
	}

	@Override
	public void readPacketNBT(NBTTagCompound compound) {
		super.readPacketNBT(compound);

		setFacing(EnumFacing.getFront(compound.getInteger("Facing")));
		suction = compound.getInteger("Suction");
		cookCost = compound.getFloat("CookCost");
		cookTime = compound.getFloat("CookTime");
	}

	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int pixels) {
		return 0;
	}

	@SideOnly(Side.CLIENT)
	public int getBoostScaled() {
		return 0;
	}

	@Override
	public void update() {
		if (isDormant()) {
			setSuction(0);
		}
	}
}