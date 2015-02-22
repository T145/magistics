package hu.hundevelopers.elysium.render;

import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTranslatef;
import hu.hundevelopers.elysium.api.Staff;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import T145.magistics.client.lib.pillars.ExternalRenderer;
import cpw.mods.fml.client.FMLClientHandler;

public class StaffRenderer implements IItemRenderer
{

	//fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer central_gem;
    ModelRenderer gem2;
    ModelRenderer gem;

    private ResourceLocation TEXTURE_STAFF[] = {
    		new ResourceLocation("elysium:textures/models/staff1.png"),
    		new ResourceLocation("elysium:textures/models/staff2.png"),
    		new ResourceLocation("elysium:textures/models/staff3.png"),
    		new ResourceLocation("elysium:textures/models/staff4.png")
		};

	public static ModelBase model = new ModelBase()
	{

	};
	
	private ExternalRenderer itemRenderer;
	
	public StaffRenderer()
	{
		itemRenderer = new ExternalRenderer(false, false);

		model.textureWidth = 64;
		model.textureHeight = 32;
    
		Shape1 = new ModelRenderer(model, 0, 0);
		Shape1.addBox(-0.5F, -3F, -0.5F, 1, 18, 1);
		Shape1.setRotationPoint(0F, 0F, 0F);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(model, 4, 0);
		Shape2.addBox(-1F, -8F, -1F, 2, 5, 2);
		Shape2.setRotationPoint(0F, 0F, 0F);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(model, 12, 0);
		Shape3.addBox(-1.5F, -9F, -5F, 3, 3, 3);
		Shape3.setRotationPoint(0F, 0F, 0F);
		Shape3.setTextureSize(64, 32);
		Shape3.mirror = true;
		setRotation(Shape3, -0.5235988F, 0F, 0F);
		Shape4 = new ModelRenderer(model, 24, 0);
		Shape4.addBox(-2F, -10F, -8F, 4, 3, 4);
		Shape4.setRotationPoint(0F, 0F, 0F);
		Shape4.setTextureSize(64, 32);
		Shape4.mirror = true;
		setRotation(Shape4, -0.7853982F, 0F, 0F);
		Shape5 = new ModelRenderer(model, 40, 0);
		Shape5.addBox(-1.5F, -14F, 3F, 3, 5, 3);
		Shape5.setRotationPoint(0F, 0F, 0F);
		Shape5.setTextureSize(64, 32);
		Shape5.mirror = true;
		setRotation(Shape5, 0.2443461F, 0F, 0F);
		Shape6 = new ModelRenderer(model, 52, 0);
		Shape6.addBox(-1F, -11F, 10F, 2, 3, 2);
		Shape6.setRotationPoint(0F, 0F, 0F);
		Shape6.setTextureSize(64, 32);
		Shape6.mirror = true;
		setRotation(Shape6, 0.8726646F, 0F, 0F);
		Shape7 = new ModelRenderer(model, 4, 7);
		Shape7.addBox(-0.5F, -15F, 1F, 1, 1, 4);
		Shape7.setRotationPoint(0F, 0F, 0F);
		Shape7.setTextureSize(64, 32);
		Shape7.mirror = true;
		setRotation(Shape7, 0.3665191F, 0F, 0F);
		central_gem = new ModelRenderer(model, 4, 16);
		central_gem.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		central_gem.setRotationPoint(0F, -12F, -2F);
		central_gem.setTextureSize(64, 32);
		central_gem.mirror = true;
		setRotation(central_gem, 0F, 0.7853982F, 0F);
		gem2 = new ModelRenderer(model, 4, 12);
		gem2.addBox(-1F, -1F, -1F, 2, 2, 2);
		gem2.setRotationPoint(0F, -12F, -2F);
		gem2.setTextureSize(64, 32);
		gem2.mirror = true;
		setRotation(gem2, 0F, 0.7853982F, 0F);
		gem = new ModelRenderer(model, 4, 12);
		gem.addBox(-1F, -1F, -1F, 2, 2, 2);
		gem.setRotationPoint(0F, -12F, -2F);
		gem.setTextureSize(64, 32);
		gem.mirror = true;
		setRotation(gem, 0F, 0F, 0.7853982F);
	}
  
	public void render(float f5)
	{
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape4.render(f5);
	    Shape5.render(f5);
	    Shape6.render(f5);
	    Shape7.render(f5);
	   
	    central_gem.render(f5);
	    gem2.render(f5);
	    gem.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		return true;
	}
	
	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
	{
		return true;
	}
	
	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		float time = 0.02F;
	    central_gem.rotateAngleY += time;
	    gem.rotateAngleZ += time;
	    gem2.rotateAngleZ -= time;
            
		glPushMatrix();
//		glTranslated(0.5D, 1.5D, 0.5D);
		glRotatef(180F, 1F, 0F, 0F);
		glEnable(GL11.GL_BLEND);
		glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	
		Minecraft.getMinecraft().renderEngine.bindTexture(this.TEXTURE_STAFF[item.getItemDamage()]);

		if (type == ItemRenderType.INVENTORY)
        {
//            GL11.glDisable(GL11.GL_LIGHTING);

            	render(0.0625F);

//            GL11.glEnable(GL11.GL_LIGHTING);
        }
        else
        {
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);

            float scale;
    	    switch (type)
            {
	            case EQUIPPED_FIRST_PERSON:
	                glTranslatef(0F, -1.2F, -0.5F);
	        		glRotatef(200F, 0F, 1F, 0F);
//	        		scale = 0.8F;
//	            	glScalef(scale, scale, scale);
	                

                break;
	            case EQUIPPED:
	            	scale = 2.1F;
	            	glScalef(scale, scale, scale);
	            	glTranslatef(0.35F, -0.2F, -0.35F);
//	            	
	        		glRotatef(100, 0F, 1F, 0F);

	            	
	                break;
	            case ENTITY:
	                glTranslatef(0, -6 / 16f, 0);
	                break;
	            default:
	            	break;
            }
    		render(0.0625F);
        }
		GL11.glDisable(GL11.GL_BLEND);

		glPopMatrix();
		
		if(type == ItemRenderType.EQUIPPED_FIRST_PERSON)
		{
			if(item.getItemDamage() == 0)
			{
				Block block = Staff.getBlockHolding(item); 
				if(block != null)
				{
					glPushMatrix();
//	        		glRotatef(10F, 0F, 0F, 1F);
						
						float f = 1.5F;
						glScalef(f, f, f);
						glRotatef(-45, 0, 1, 0);
						
						EntityItem entityitem = new EntityItem(FMLClientHandler.instance().getWorldClient());
						entityitem.setEntityItemStack(new ItemStack(block));
						entityitem.hoverStart = 0F;
						itemRenderer.render(entityitem, -0.453F, 1.0F + (float)Math.sin(System.currentTimeMillis()/200D)/200F, 0.5F, false);
					glPopMatrix();
				}
				
			}
		}
	}
}
