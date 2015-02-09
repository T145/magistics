package T145.magistics.common.items.craftingpillars;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import T145.magistics.api.items.baubles.Baubles;
import T145.magistics.api.items.baubles.IMod;
import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemRingBase extends Item implements IBauble
{
	public ItemRingBase()
	{
		this.setMaxStackSize(1);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
	{
		if(!world.isRemote)
		{
			IInventory baubles = BaublesApi.getBaubles(player);
			for(int i = 0; i < baubles.getSizeInventory(); i++)
			{
				if(baubles.getStackInSlot(i) == null && baubles.isItemValidForSlot(i, item))
				{
					baubles.setInventorySlotContents(i, item.copy());
					if(!player.capabilities.isCreativeMode){
						player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
					}
					onEquipped(item, player);
					break;
				}
			}
		}

		return item;
	}
	
	@Override
	public void onCreated(ItemStack itemStack, World world, EntityPlayer player)
	{
	    itemStack.stackTagCompound = new NBTTagCompound();
	}
	//TODO http://www.seventhsanctum.com/generate.php?Genname=magicitem

	/**
     * allows items to add custom lines of information to the mouseover description
     */
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean flag)
    {
        super.addInformation(item, player, list, flag);

        if (item.stackTagCompound != null)
        {

        	ItemStack gem = null;
    		NBTTagList nbtlist = item.stackTagCompound.getTagList("Items", 10);
    		for(int i = 0; i < nbtlist.tagCount(); i++)
    		{
    			NBTTagCompound nbtslot = (NBTTagCompound) nbtlist.getCompoundTagAt(i);
    			int j = nbtslot.getByte("Slot") & 255;

    			if((j >= 0) && (j < 4))
    			{
    				gem = ItemStack.loadItemStackFromNBT(nbtslot);
		        	if(gem != null)
		        		list.add(Baubles.getModifier(gem).getColor(gem) + gem.getDisplayName());

    			}
    		}
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
    	this.itemIcon = iconRegister.registerIcon("mythril:mythril_ring");
    }
    
	@Override
	public BaubleType getBaubleType(ItemStack itemstack)
	{
		return BaubleType.RING;
	}

	@Override
	public void onWornTick(ItemStack ring, EntityLivingBase player)
	{
        if (ring.stackTagCompound != null)
        {
        	ItemStack gem = null;
    		NBTTagList nbtlist = ring.stackTagCompound.getTagList("Items", 10);
    		for(int i = 0; i < nbtlist.tagCount(); i++)
    		{
    			NBTTagCompound nbtslot = (NBTTagCompound) nbtlist.getCompoundTagAt(i);
    			int j = nbtslot.getByte("Slot") & 255;

    			if((j >= 0) && (j < 4))
    			{
    				gem = ItemStack.loadItemStackFromNBT(nbtslot);
		        	if(gem != null)
		        	{
		        		IMod modif = Baubles.getModifier(gem);
		        		if(modif != null)
		        			Baubles.getModifier(gem).onWornTick(ring, gem, player);
		        	}
    			}
    		}
        }
	}

	@Override
	public void onEquipped(ItemStack ring, EntityLivingBase player)
	{
		if (ring.stackTagCompound != null)
        {
        	ItemStack gem = null;
    		NBTTagList nbtlist = ring.stackTagCompound.getTagList("Items", 10);
    		for(int i = 0; i < nbtlist.tagCount(); i++)
    		{
    			NBTTagCompound nbtslot = (NBTTagCompound) nbtlist.getCompoundTagAt(i);
    			int j = nbtslot.getByte("Slot") & 255;

    			if((j >= 0) && (j < 4))
    			{
    				gem = ItemStack.loadItemStackFromNBT(nbtslot);
		        	if(gem != null)
		        		Baubles.getModifier(gem).onEquipped(ring, player);

    			}
    		}
        }
	}

	@Override
	public void onUnequipped(ItemStack ring, EntityLivingBase player)
	{
		if (ring.stackTagCompound != null)
        {
        	ItemStack gem = null;
    		NBTTagList nbtlist = ring.stackTagCompound.getTagList("Items", 10);
    		for(int i = 0; i < nbtlist.tagCount(); i++)
    		{
    			NBTTagCompound nbtslot = (NBTTagCompound) nbtlist.getCompoundTagAt(i);
    			int j = nbtslot.getByte("Slot") & 255;

    			if((j >= 0) && (j < 4))
    			{
    				gem = ItemStack.loadItemStackFromNBT(nbtslot);
		        	if(gem != null)
		        		Baubles.getModifier(gem).onUnequipped(ring, player);

    			}
    		}
        }
	}

	@Override
	public boolean canEquip(ItemStack ring, EntityLivingBase player)
	{
		if (ring.stackTagCompound != null)
        {
        	ItemStack gem = null;
    		NBTTagList nbtlist = ring.stackTagCompound.getTagList("Items", 10);
    		for(int i = 0; i < nbtlist.tagCount(); i++)
    		{
    			NBTTagCompound nbtslot = (NBTTagCompound) nbtlist.getCompoundTagAt(i);
    			int j = nbtslot.getByte("Slot") & 255;

    			if((j >= 0) && (j < 4))
    			{
    				gem = ItemStack.loadItemStackFromNBT(nbtslot);
		        	if(gem != null)
		        		if(!Baubles.getModifier(gem).canEquip(ring, player)) return false;

    			}
    		}
        }
		return true;
	}

	@Override
	public boolean canUnequip(ItemStack ring, EntityLivingBase player)
	{
		if (ring.stackTagCompound != null)
        {
        	ItemStack gem = null;
    		NBTTagList nbtlist = ring.stackTagCompound.getTagList("Items", 10);
    		for(int i = 0; i < nbtlist.tagCount(); i++)
    		{
    			NBTTagCompound nbtslot = (NBTTagCompound) nbtlist.getCompoundTagAt(i);
    			int j = nbtslot.getByte("Slot") & 255;

    			if((j >= 0) && (j < 4))
    			{
    				gem = ItemStack.loadItemStackFromNBT(nbtslot);
		        	if(gem != null)
		        		if(!Baubles.getModifier(gem).canUnequip(ring, player)) return false;

    			}
    		}
        }
		return true;
	}
}
