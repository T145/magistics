package T145.magistics.client.renderers.craftingpillars;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTranslatef;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import T145.magistics.common.config.ConfigObjects;
import cpw.mods.fml.client.FMLClientHandler;

public class RingItemRenderer implements IItemRenderer
{
	 @Override
    public boolean handleRenderType (ItemStack item, ItemRenderType type)
    {
		 return true;
    }

    @Override
    public boolean shouldUseRenderHelper (ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return type != ItemRenderType.INVENTORY && type != ItemRenderType.EQUIPPED;
    }
    
	private static final RenderingHelper.ItemRender itemRenderer = new RenderingHelper.ItemRender(false, false);

    @Override
    public void renderItem (ItemRenderType type, ItemStack item, Object... data)
    {			
		glPushMatrix();

    	if(type == ItemRenderType.INVENTORY)
		{
    		itemRenderer.zLevel = 0F;
			itemRenderer.renderItemAndEffectIntoGUI(FMLClientHandler.instance().getClient().fontRenderer, FMLClientHandler.instance().getClient().getTextureManager(), new ItemStack(ConfigObjects.itemRingDummy), 0, 0);
    		itemRenderer.zLevel = 200F;

		}
		else
		{
	    	if(type == ItemRenderType.EQUIPPED_FIRST_PERSON)
			{
	    		glRotatef(90, 0, 1, 0);
	    		glTranslatef(-0.5F, 1, -0.3F);
			} else if (type == ItemRenderType.EQUIPPED){
	    		glTranslatef(0.5F, 0, 0);
			}
	    	
			World world = FMLClientHandler.instance().getWorldClient();
		
			EntityItem entity = new EntityItem(world);
			entity.setEntityItemStack(new ItemStack(ConfigObjects.itemRingDummy));
			entity.hoverStart = 0F;
			if(entity != null)
			{
				if(type == ItemRenderType.INVENTORY)
					itemRenderer.doRender(entity, 0.5F, 0.5F, 0, 0, 0);
				else
				{
					glTranslatef(0, -0.3F, 0);
					glPushMatrix();
						glScalef(1.7F, 1.7F, 3F);
						itemRenderer.doRender(entity, -0.021F, 0.21F, 0, 0, 0);
					glPopMatrix();
	
				}
			}
		}
    	
    	if(type == ItemRenderType.INVENTORY)
		{
			glScalef(1/2F, 1/2F, 1);
		} else {
			glTranslatef(-0.5F, 0, 0);

		}
    	ItemStack gem = null;
    	if(item.stackTagCompound != null)
    	{
			
			NBTTagList nbtlist = item.stackTagCompound.getTagList("Items", 10);
			
			
			for(int i = 0; i < nbtlist.tagCount(); i++)
			{
				NBTTagCompound nbtslot = (NBTTagCompound) nbtlist.getCompoundTagAt(i);
				int j = nbtslot.getByte("Slot") & 255;
		
				if((j >= 0) && (j < 4))
				{
					gem = ItemStack.loadItemStackFromNBT(nbtslot);
		        	if(gem.getItem() != null)
		        	{
		        		if(type == ItemRenderType.INVENTORY)
		        		{
    						itemRenderer.renderItemAndEffectIntoGUI(FMLClientHandler.instance().getClient().fontRenderer, FMLClientHandler.instance().getClient().getTextureManager(), gem, (j%2)*16, (j/2)*16);
		        		}
		        		else
		        		{

		        			World world = FMLClientHandler.instance().getWorldClient();
		        			
		        			EntityItem entity = new EntityItem(world);
		        			entity.setEntityItemStack(gem);
		        			entity.hoverStart = 0F;
		        			itemRenderer.doRender(entity, 0.2F+(j%2)/2F, 0.2F+(j/2)/2F, 0, 0, 0);
		        		}	
		        	}
		
				}
			}
    	}
			
		glPopMatrix();
	}
}