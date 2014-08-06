package T145.magistics.common.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.api.wands.IWandable;
import thaumcraft.common.tiles.TileThaumcraft;

public class TileInfuser extends TileThaumcraft implements IAspectContainer, IWandable, IEssentiaTransport {
	public ForgeDirection orientation = ForgeDirection.getOrientation(2);

	@Override
	public void readCustomNBT(NBTTagCompound nbt) {
		orientation = ForgeDirection.getOrientation(nbt.getInteger("orientation"));
	}

	@Override
	public void writeCustomNBT(NBTTagCompound nbt) {
		nbt.setInteger("orientation", orientation.ordinal());
	}

	@Override
	public int addEssentia(Aspect arg0, int arg1, ForgeDirection arg2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean canInputFrom(ForgeDirection arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canOutputTo(ForgeDirection arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getEssentiaAmount(ForgeDirection arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Aspect getEssentiaType(ForgeDirection arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMinimumSuction() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSuctionAmount(ForgeDirection arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Aspect getSuctionType(ForgeDirection arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isConnectable(ForgeDirection arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean renderExtendedTube() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setSuction(Aspect arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int takeEssentia(Aspect arg0, int arg1, ForgeDirection arg2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void onUsingWandTick(ItemStack arg0, EntityPlayer arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ItemStack onWandRightClick(World arg0, ItemStack arg1,
			EntityPlayer arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onWandRightClick(World arg0, ItemStack arg1, EntityPlayer arg2,
			int arg3, int arg4, int arg5, int arg6, int arg7) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void onWandStoppedUsing(ItemStack arg0, World arg1,
			EntityPlayer arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int addToContainer(Aspect arg0, int arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int containerContains(Aspect arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean doesContainerAccept(Aspect arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doesContainerContain(AspectList arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doesContainerContainAmount(Aspect arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AspectList getAspects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAspects(AspectList arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean takeFromContainer(AspectList arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean takeFromContainer(Aspect arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}
}