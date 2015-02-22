package hu.hundevelopers.elysium.event;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTranslated;
import hu.hundevelopers.elysium.Configs;
import hu.hundevelopers.elysium.api.Staff;
import hu.hundevelopers.elysium.client.gui.ElysiumGuiEmulator;
import hu.hundevelopers.elysium.client.gui.ElysiumGuiMainMenu;
import hu.hundevelopers.elysium.item.ElysiumStaffItem;
import hu.hundevelopers.elysium.world.ElysiumWorldProvider;
import hu.hundevelopers.elysium.world.biome.ElysiumBiomeGenCorruption;
import hu.hundevelopers.elysium.world.biome.ElysiumBiomeGenDesert;
import hu.hundevelopers.elysium.world.biome.ElysiumBiomeGenForest;
import hu.hundevelopers.elysium.world.biome.ElysiumBiomeGenPlain;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.sound.PlaySoundEvent17;
import T145.magistics.client.lib.pillars.ExternalRenderer;
import T145.magistics.common.config.ConfigObjects;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ElysiumClientHandler
{
	@SubscribeEvent
	public void guiOpen(GuiOpenEvent event) {
		if (event.gui instanceof GuiMainMenu && ConfigObjects.isMenuEnabled) {
			Minecraft.getMinecraft().displayGuiScreen(new ElysiumGuiEmulator(new ElysiumGuiMainMenu(null)));
			event.setCanceled(true);
		}
	}
	
    @SubscribeEvent
    public void onWorldTick(WorldTickEvent event)
    {
    	if(event.world.provider instanceof ElysiumWorldProvider)
    	{
			event.world.setThunderStrength(1F);
			event.world.setRainStrength(0.5F);
    	}
    }
    
	@SideOnly(Side.CLIENT)
	private ExternalRenderer itemRenderer;
	
	public ElysiumClientHandler()
	{
		if(FMLCommonHandler.instance().getSide() == Side.CLIENT)
			itemRenderer = new ExternalRenderer(false, false);
	}
	private static long lastPlayed = System.currentTimeMillis() - 180000;

	@SubscribeEvent
	public void onSoundPlayed(PlaySoundEvent17 event)
    {
//		if(event.category == SoundCategory.ANIMALS || event.category == SoundCategory.BLOCKS || event.category == SoundCategory.MOBS || event.category == SoundCategory.WEATHER) return;
//		System.out.println("Playing " + event.category + " sound: " + event.name + " class name: " + event.result.getClass().getName() + " " + event.sound.getClass().getName());
		
		World world = FMLClientHandler.instance().getClient().theWorld; 
		if(world != null && world.provider instanceof ElysiumWorldProvider)
		{
			if(System.currentTimeMillis() - lastPlayed >= 18000)
			{
				EntityPlayer player = FMLClientHandler.instance().getClientPlayerEntity(); 
				BiomeGenBase biomeGenBase = world.getBiomeGenForCoords((int) player.posX, (int) player.posZ);
				
				if(event.category == SoundCategory.AMBIENT || event.category == SoundCategory.MUSIC)
				{
					if(player.posY <= Configs.labyrinthTop)
					{
						event.result = PositionedSoundRecord.func_147673_a(new ResourceLocation("elysium:elysiumLabyrinth"));
						lastPlayed = System.currentTimeMillis();

					} else if(biomeGenBase instanceof ElysiumBiomeGenForest)
					{
						event.result = PositionedSoundRecord.func_147673_a(new ResourceLocation("elysium:elysiumForest"));
						lastPlayed = System.currentTimeMillis();

					} else if(biomeGenBase instanceof ElysiumBiomeGenPlain)
					{
						if(world.rand.nextInt(3) == 0 )
							event.result = PositionedSoundRecord.func_147673_a(new ResourceLocation("elysium:elysium"));
						else
							event.result = PositionedSoundRecord.func_147673_a(new ResourceLocation("elysium:elysiumMenu"));
						lastPlayed = System.currentTimeMillis();

					} else if(biomeGenBase instanceof ElysiumBiomeGenCorruption)
					{
						event.result = PositionedSoundRecord.func_147673_a(new ResourceLocation("elysium:elysiumShadow"));
						lastPlayed = System.currentTimeMillis();

					} else if(!(biomeGenBase instanceof ElysiumBiomeGenDesert))
					{
						if(world.rand.nextInt(3) == 0 )
							event.result = PositionedSoundRecord.func_147673_a(new ResourceLocation("elysium:elysiumShadow"));
						else
							event.result = PositionedSoundRecord.func_147673_a(new ResourceLocation("elysium:elysiumLabyrinth"));
						lastPlayed = System.currentTimeMillis();

					} else
					{
						event.result = null;
						lastPlayed = System.currentTimeMillis() - 180000;

					}
				}
			} else
			{
				event.result = null;
				//DO NOT RESET TIME HERE!
			}
		}
		else if(world == null && ConfigObjects.isMenuEnabled)
		{
			ISound menuSong = PositionedSoundRecord.func_147673_a(new ResourceLocation("elysium:elysiumMenu"));
			if(event.category == SoundCategory.MUSIC)
			{
				if(System.currentTimeMillis() - lastPlayed > 180000)
				{
					event.result = menuSong;
					lastPlayed = System.currentTimeMillis();
				} else {
					event.result = null;
				}
			}
		}
    }
	
	@SubscribeEvent
	public void onEntityRender(RenderLivingEvent.Post event)
	{
		EntityLivingBase entity = event.entity;
		
		ItemStack item = entity.getEquipmentInSlot(0);
				
		if(item != null && item.getItem() instanceof ElysiumStaffItem && item.getItemDamage() == 0)
		{
			Block block = Staff.getBlockHolding(item); 
			if(block != null)
			{
				glPushMatrix();
					glTranslated(0, -0.3D, 0);
					glRotatef(entity.rotationYaw, 0, -1, 0);
					glRotatef(entity.rotationPitch, 1, 0, 0);
					
					float f = 1.5F;
					glScalef(f, f, f);
//						glRotatef(90, 0, -1, 0);
								
					EntityItem entityitem = new EntityItem(entity.worldObj);
					entityitem.setEntityItemStack(new ItemStack(block));
					entityitem.hoverStart = 0F;
					itemRenderer.render(entityitem, 0, 0, 0.5F, false);

				glPopMatrix();
			
			}
		}
	}
}
