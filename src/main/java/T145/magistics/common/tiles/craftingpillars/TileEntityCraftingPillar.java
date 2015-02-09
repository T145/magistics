package T145.magistics.common.tiles.craftingpillars;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import T145.magistics.common.Magistics;
import T145.magistics.common.config.ConfigObjects;
import T145.magistics.common.containers.ContainerCraftingPillar;

public class TileEntityCraftingPillar extends BaseTileEntity implements IInventory, ISidedInventory
{
	public ContainerCraftingPillar container = new ContainerCraftingPillar();
	private ItemStack[] inventory = new ItemStack[this.getSizeInventory() + 2];//11th - thaumcraft wand
	public EntityPlayer closestPlayer = null;

	public float rot = 0F;
	public boolean showNum = false;
	//	public AspectList aspectsForRecipe;

	@Override
	public void updateEntity()
	{
		if(this.worldObj.isRemote)
		{
			this.rot += 0.1F;
			if(this.rot >= 360F)
				this.rot -= 360F;
		}

		super.updateEntity();
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		// System.out.println("read: "+this.worldObj.isRemote);

		super.readFromNBT(nbt);

		this.inventory = new ItemStack[this.getSizeInventory() + 2];
		NBTTagList nbtlist = nbt.getTagList("Items", 10);

		for(int i = 0; i < nbtlist.tagCount(); i++)
		{
			NBTTagCompound nbtslot = (NBTTagCompound) nbtlist.getCompoundTagAt(i);
			int j = nbtslot.getByte("Slot") & 255;

			if((j >= 0) && (j < this.getSizeInventory() + 2))
				this.inventory[j] = ItemStack.loadItemStackFromNBT(nbtslot);
		}
		this.showNum = nbt.getBoolean("showNum");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		// System.out.println("write: "+this.worldObj.isRemote);

		super.writeToNBT(nbt);

		NBTTagList nbtlist = new NBTTagList();

		for(int i = 0; i < this.getSizeInventory() + 2; i++)
		{
			if(this.inventory[i] != null)
			{
				NBTTagCompound nbtslot = new NBTTagCompound();
				nbtslot.setByte("Slot", (byte) i);
				this.inventory[i].writeToNBT(nbtslot);
				nbtlist.appendTag(nbtslot);
			}
		}

		nbt.setTag("Items", nbtlist);
		nbt.setBoolean("showNum", this.showNum);
	}

	@Override
	public void onInventoryChanged()
	{
		super.onInventoryChanged();

		if(!this.worldObj.isRemote)
		{
			this.rotateCraftingGrid();

			this.inventory[this.getSizeInventory()] = CraftingManager.getInstance().findMatchingRecipe(this.container.craftMatrix, this.worldObj);

			if(this.worldObj.loadedEntityList != null)
			{
				float closest = Float.MAX_VALUE;
				for(int i = 0; i < this.worldObj.loadedEntityList.size(); i++)
				{
					if(this.worldObj.loadedEntityList.get(i) instanceof EntityPlayer)
					{
						EntityPlayer currentPlayer = (EntityPlayer)this.worldObj.loadedEntityList.get(i);
						if(currentPlayer.isEntityAlive())
						{
							float distance = (float)currentPlayer.getDistanceSq(this.xCoord, this.yCoord, this.zCoord);
							if(distance < closest)
							{
								closest = distance;
								this.closestPlayer = currentPlayer;
							}
						}
					}
				}

				//				if(this.closestPlayer != null && this.getStackInSlot(10) != null)
				//				{
				//					ItemStack result = MagisticsAPIHelper.findMatchingArcaneRecipe(this.container.craftMatrix, this.closestPlayer);
				//					if(result != null)
				//					{
				//						FMLLog.warning("Result " + result.getDisplayName());
				//
				//						this.inventory[this.getSizeInventory()] = result;
				//						aspectsForRecipe = MagisticsAPIHelper.findMatchingArcaneRecipeAspects(this.container.craftMatrix, this.closestPlayer);
				//						
				//						for(int i = 0; i < aspectsForRecipe.size(); i++)
				//						{
				//							FMLLog.warning(aspectsForRecipe.getAspects()[i].getName() + ": " + aspectsForRecipe.getAmount(aspectsForRecipe.getAspects()[i]));
				//						}
				//						
				//					}
				//					else {
				//						FMLLog.warning("no Result");
				//
				////						aspects = null;
				//					}
				//				}

			}
			Magistics.proxy.sendToPlayers(this.getDescriptionPacket(), this.worldObj, this.xCoord, this.yCoord, this.zCoord, 64);
		}
	}

	public void rotateCraftingGrid()
	{
		int meta = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);

		/*
		 * if(!this.worldObj.isRemote) System.out.println(meta);
		 */

		for(int i = 0; i < 3; i++)
		{
			for(int k = 0; k < 3; k++)
			{
				if(meta == 0)
				{
					this.container.craftMatrix.setInventorySlotContents(8 - k * 3 - i, this.getStackInSlot(i * 3 + k));
				}
				else if(meta == 1)
				{
					this.container.craftMatrix.setInventorySlotContents(i * 3 + k, this.getStackInSlot(i * 3 + k));
				}
				else if(meta == 2)
				{
					this.container.craftMatrix.setInventorySlotContents(k * 3 + i, this.getStackInSlot(i * 3 + k));
				}
				else
				{
					this.container.craftMatrix.setInventorySlotContents(8 - i * 3 - k, this.getStackInSlot(i * 3 + k));
				}
			}
		}
	}

	public void craftItem(EntityPlayer player)
	{
		if(!this.worldObj.isRemote)
		{

			EntityItem itemCrafted = new EntityItem(this.worldObj, this.xCoord + 0.5D, this.yCoord + 1.5D, this.zCoord + 0.5D, this.inventory[this.getSizeInventory()]);
			itemCrafted.motionX = random.nextDouble() / 4 - 0.125D;
			itemCrafted.motionZ = random.nextDouble() / 4 - 0.125D;
			itemCrafted.motionY = random.nextDouble() / 4;
			this.worldObj.spawnEntityInWorld(itemCrafted);


			EntityItem itemEntity = new EntityItem(this.worldObj, player.posX, player.posY, player.posZ);
			itemEntity.setEntityItemStack(this.inventory[this.getSizeInventory()]);
			this.worldObj.spawnEntityInWorld(itemEntity);
			this.onCrafting(player, this.inventory[this.getSizeInventory()]);

			//			if(Magistics.modThaumcraft)
			//			{
			//				if (this.inventory[this.getSizeInventory()] != null)
			//				{
			//					FMLLog.warning(this.inventory[this.getSizeInventory()].getDisplayName());
			//
			//					AspectList aspects = MagisticsAPIHelper.findMatchingArcaneRecipeAspects(this.container.craftMatrix, player);
			//					if(aspects != null)
			//					{
			//						if ((aspects.size() > 0) && (this.getStackInSlot(10) != null)) {
			//							ItemStack wand = this.getStackInSlot(10);
			//							ThaumcraftApiHelper.consumeVisFromWandCrafting(wand, player, aspects, true);
			//							
			//							for(int i = 0; i < aspects.size(); i++)
			//							{
			//								FMLLog.warning(aspects.getAspects()[i].getName() + ": " + aspects.getAmount(aspects.getAspects()[i]));
			//							}
			//						}
			//					} else {
			//						FMLLog.warning("Aspect list is null");
			//					}
			//				}
			//			}

			for(int i = 0; i < this.getSizeInventory(); i++)
			{
				ItemStack itemstack1 = this.getStackInSlot(i);

				if(itemstack1 != null)
				{
					this.decrStackSize(i, 1);

					if(itemstack1.getItem().hasContainerItem())
					{
						ItemStack itemstack2 = itemstack1.getItem().getContainerItem(itemstack1);

						if(itemstack2.isItemStackDamageable() && itemstack2.getItemDamage() > itemstack2.getMaxDamage())
						{
							MinecraftForge.EVENT_BUS.post(new PlayerDestroyItemEvent(player, itemstack2));
							itemstack2 = null;
						}

						if(itemstack2 != null && (!itemstack1.getItem().doesContainerItemLeaveCraftingGrid(itemstack1) || !player.inventory.addItemStackToInventory(itemstack2)))
						{
							if(this.getStackInSlot(i) == null)
							{
								this.setInventorySlotContents(i, itemstack2);
							}
							else
							{
								itemEntity = new EntityItem(this.worldObj, player.posX, player.posY, player.posZ);
								itemEntity.setEntityItemStack(itemstack2);
								this.worldObj.spawnEntityInWorld(itemEntity);
							}
						}
					}
				}
			}
		}
		else
		{
			//			for(int i = 0; i < 8; i++)
			//			{
			//				CustomParticle particle = new CustomParticle(this.worldObj, this.xCoord - 0.25D + random.nextDouble() * 1.5D, this.yCoord + random.nextDouble() * 1.5D, this.zCoord - 0.25D + random.nextDouble() * 1.5D, 0D, 0D, 0D);
			//				particle.setRBGColorF(1F, 1F, 1F);
			//				particle.multipleParticleScaleBy(1F);
			//				particle.setParticleTextureIndex(82);// 83 villager
			//				// particle.setParticleTextureIndex(-1);
			//				// particle.setTextureFile("/mods/elysium/textures/misc/particles/fost.png");
			//				FMLClientHandler.instance().getClient().effectRenderer.addEffect(particle);
			//				this.worldObj.playSoundAtEntity(FMLClientHandler.instance().getClient().thePlayer, "random.levelup", 0.75F, 1.0F);
			//			}
		}
	}

	public void onCrafting(EntityPlayer player, ItemStack stack)
	{
		stack.onCrafting(player.worldObj, player, stack.stackSize);

		if (stack.getItem() == Item.getItemFromBlock(Blocks.crafting_table))
		{
			player.addStat(AchievementList.buildWorkBench, 1);
		}

		if (stack.getItem() instanceof ItemPickaxe)
		{
			player.addStat(AchievementList.buildPickaxe, 1);
		}

		if (stack.getItem() == Item.getItemFromBlock(Blocks.furnace))
		{
			player.addStat(AchievementList.buildFurnace, 1);
		}

		if (stack.getItem() instanceof ItemHoe)
		{
			player.addStat(AchievementList.buildHoe, 1);
		}

		if (stack.getItem() == Items.bread)
		{
			player.addStat(AchievementList.makeBread, 1);
		}

		if (stack.getItem() == Items.cake)
		{
			player.addStat(AchievementList.bakeCake, 1);
		}

		if (stack.getItem() instanceof ItemPickaxe && ((ItemPickaxe)stack.getItem()).func_150913_i() != Item.ToolMaterial.WOOD)
		{
			player.addStat(AchievementList.buildBetterPickaxe, 1);
		}

		if (stack.getItem() instanceof ItemSword)
		{
			player.addStat(AchievementList.buildSword, 1);
		}

		if (stack.getItem() == Item.getItemFromBlock(Blocks.enchanting_table))
		{
			player.addStat(AchievementList.enchantments, 1);
		}

		if (stack.getItem() == Item.getItemFromBlock(Blocks.bookshelf))
		{
			player.addStat(AchievementList.bookcase, 1);
		}

		if (stack.getItem() == Item.getItemFromBlock(ConfigObjects.blockCraftingPillar))
		{
			player.addStat(ConfigObjects.achievementRecursion, 1);
			System.out.println("achivement recursion");
		}
	}

	public void dropItemFromSlot(int slot, EntityPlayer player)
	{
		if(!this.worldObj.isRemote && this.getStackInSlot(slot) != null)
		{
			/*
			EntityItem droppedItem = new EntityItem(this.worldObj, this.xCoord + 0.5D, this.yCoord + 1.5D, this.zCoord + 0.5D);
			droppedItem.setEntityItemStack(this.decrStackSize(slot, 1));
			droppedItem.motionX = random.nextDouble() / 4 - 0.125D;
			droppedItem.motionZ = random.nextDouble() / 4 - 0.125D;
			droppedItem.motionY = random.nextDouble() / 4;
			this.worldObj.spawnEntityInWorld(droppedItem);
			 */

			//player.dropPlayerItem(this.decrStackSize(slot, 1));

			EntityItem itemEntity = new EntityItem(this.worldObj, player.posX, player.posY, player.posZ);
			itemEntity.setEntityItemStack(this.decrStackSize(slot, 1));
			this.worldObj.spawnEntityInWorld(itemEntity);
			this.onInventoryChanged();
		}
	}

	@Override
	public int getSizeInventory()
	{
		return 9;
	}

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return this.inventory[slot];
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack)
	{
		this.inventory[slot] = stack;

		if(stack != null && stack.stackSize > this.getInventoryStackLimit())
		{
			stack.stackSize = this.getInventoryStackLimit();
		}

		this.onInventoryChanged();
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount)
	{
		ItemStack stack = null;

		if(this.inventory[slot] != null)
		{
			if(this.inventory[slot].stackSize <= amount)
			{
				stack = this.inventory[slot];
				this.inventory[slot] = null;
				this.onInventoryChanged();
			}
			else
			{
				stack = this.inventory[slot].splitStack(amount);

				if(this.inventory[slot].stackSize == 0)
				{
					this.inventory[slot] = null;
				}

				this.onInventoryChanged();
			}
		}

		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot)
	{
		ItemStack stack = this.getStackInSlot(slot);
		if(stack != null)
		{
			this.setInventorySlotContents(slot, null);
		}

		return stack;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public void openInventory()
	{
	}

	@Override
	public void closeInventory()
	{
	}

	@Override
	public String getInventoryName()
	{
		return "Crafting Pillar";
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return false;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return super.isUseableByPlayer(player);
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemstack)
	{
		return slot == 10 && itemstack.getItem() == ConfigObjects.itemWandThaumcraft;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side)
	{
		return new int[] {10};
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack itemstack, int side)
	{
		return this.isItemValidForSlot(slot, itemstack);
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack itemstack, int side)
	{
		return this.isItemValidForSlot(slot, itemstack);
	}
}
