package hu.hundevelopers.elysium.item;

import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.model.ModelToothArmor;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ElysiumItemArmor extends ItemArmor
{

	public ElysiumItemArmor(ArmorMaterial armorMat, int renderIndex, int armorType)
	{
		super(armorMat, renderIndex, armorType);
		this.setCreativeTab(Elysium.tabElysium);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		return Elysium.ID + ":textures/models/tooth_armor.png";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
	{
		ModelBiped model1 = null;
		ModelBiped model2 = null;
		ModelBiped model = null;
		  
		int type = ((ItemArmor)itemStack.getItem()).armorType;

	    if (model1 == null) {
	      model1 = new ModelToothArmor(0.5F);//0.5
	    }
	    if (model2 == null) {
	      model2 = new ModelToothArmor(0.01F);//0.01
	    }
	    if ((entityLiving instanceof EntityPlayer && type == 0) || !(entityLiving instanceof EntityPlayer) && type == 1)
	      model = model1;
	    else {
	      model = model2;
	    }

	    if (model != null)
	    {
	    	model.bipedHead.showModel = (armorSlot == 0);
	    	model.bipedHeadwear.showModel = (armorSlot == 0);
	    	model.bipedBody.showModel = ((armorSlot == 1) || (armorSlot == 2));
	    	model.bipedRightArm.showModel = (armorSlot == 1);
	    	model.bipedLeftArm.showModel = (armorSlot == 1);
	    	model.bipedLeftLeg.showModel = (armorSlot == 2);
	    	model.bipedRightLeg.showModel = (armorSlot == 2);
	    	model.isSneak = entityLiving.isSneaking();

	    	model.isRiding = entityLiving.isRiding();
			model.isChild = entityLiving.isChild();
			model.aimedBow = false;
	      	model.heldItemRight = (entityLiving.getEquipmentInSlot(0) != null ? 1 : 0);
	      	if ((entityLiving instanceof EntityPlayer))
	      	{
	      		if (((EntityPlayer)entityLiving).getItemInUseDuration() > 0)
	      		{
	      			EnumAction enumaction = ((EntityPlayer)entityLiving).getEquipmentInSlot(0).getItemUseAction();

//	      			if (enumaction == EnumAction.block)
//	      			{
//	      				model.heldItemRight = 3;
//	      			}
//	      			else
      				if (enumaction == EnumAction.bow)
	      			{
	      				model.aimedBow = true;
	      			}
	      		}
	      	}
	    }

	    return model;
	    
	    
//		if (itemStack != null)
//		{
//			if (model != null)
//			{
//				model.bipedHead.showModel = armorSlot == 0;
//				model.bipedHeadwear.showModel = armorSlot == 0;
//				model.bipedBody.showModel = armorSlot == 1 || armorSlot == 2;
//				model.bipedRightArm.showModel = armorSlot == 1;
//				model.bipedLeftArm.showModel = armorSlot == 1;
//				model.bipedRightLeg.showModel = armorSlot == 2 || armorSlot == 3;
//				model.bipedLeftLeg.showModel = armorSlot == 2 || armorSlot == 3;
//				model.isSneak = entityLiving.isSneaking();
//				model.isRiding = entityLiving.isRiding();
//				model.isChild = entityLiving.isChild();
//				model.heldItemRight = entityLiving.getEquipmentInSlot(0) != null ? 1 : 0;
//				if (entityLiving instanceof EntityPlayer) {
//					model.aimedBow = ((EntityPlayer) entityLiving).getItemInUseDuration() > 2;
//				}
//				return model;
//			}
//		}
//		return null;
	}
}