package T145.magistics.network;

import T145.magistics.api.enums.EnumConduit;
import T145.magistics.api.enums.EnumCrucible;
import T145.magistics.api.enums.EnumInfuser;
import T145.magistics.api.enums.EnumShard;
import T145.magistics.api.enums.EnumWood;
import T145.magistics.api.objects.IVariant;
import T145.magistics.api.objects.ModBlocks;
import T145.magistics.api.objects.ModItems;
import T145.magistics.client.fx.ParticleEngine;
import T145.magistics.client.fx.particles.ParticleGreenFlame;
import T145.magistics.client.fx.particles.ParticleSmallGreenFlame;
import T145.magistics.client.fx.particles.ParticleWisp;
import T145.magistics.client.gui.GuiInfuser;
import T145.magistics.client.lib.ColorHandler;
import T145.magistics.client.lib.events.IconAtlas;
import T145.magistics.client.render.blocks.RenderConduit;
import T145.magistics.client.render.blocks.RenderCrucible;
import T145.magistics.client.render.blocks.RenderInfuser;
import T145.magistics.client.render.blocks.RenderWoodChest;
import T145.magistics.client.render.entities.RenderColoredSlime.RenderColoredSlimeFactory;
import T145.magistics.entities.EntityVisSlime;
import T145.magistics.lib.sounds.SoundHandler;
import T145.magistics.tiles.TileConduit;
import T145.magistics.tiles.TileCrucible;
import T145.magistics.tiles.TileInfuser;
import T145.magistics.tiles.TileInfuserDark;
import T145.magistics.tiles.TileWoodChest;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);

		switch (ID) {
		case 0:
			return new GuiInfuser(player.inventory, (TileInfuser) world.getTileEntity(pos));
		case 1:
			return new GuiChest(player.inventory, (IInventory) world.getTileEntity(pos));
		default:
			return null;
		}
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		SoundHandler.registerSounds();

		super.preInit(event);

		MinecraftForge.EVENT_BUS.register(ParticleEngine.INSTANCE);
		MinecraftForge.EVENT_BUS.register(IconAtlas.INSTANCE);

		for (EnumConduit type : EnumConduit.values()) {
			registerBlockModel(ModBlocks.conduit, type.ordinal(), type);
		}

		for (EnumCrucible type : EnumCrucible.values()) {
			registerBlockModel(ModBlocks.crucible, type.ordinal(), type);
		}

		for (EnumShard type : EnumShard.values()) {
			registerItemModel(ModItems.shard, type.ordinal(), type);
			registerItemModel(ModItems.shardFragment, type.ordinal(), type);
			registerBlockModel(ModBlocks.infusedOre, type.ordinal(), type);
			registerBlockModel(ModBlocks.infusedOreEnd, type.ordinal(), type);
			registerBlockModel(ModBlocks.infusedOreNether, type.ordinal(), type);
		}

		for (EnumInfuser type : EnumInfuser.values()) {
			registerBlockModel(ModBlocks.infuser, type.ordinal(), type);
			registerBlockModel(ModBlocks.infuser, type.ordinal(), "inventory," + type.getClientName());
		}

		for (EnumWood type : EnumWood.values()) {
			registerBlockModel(ModBlocks.leaves, type.ordinal(), type);
			registerBlockModel(ModBlocks.leaves, type.ordinal(), "check_decay=false,decayable=false," + type.getClientName());
			registerBlockModel(ModBlocks.leaves, type.ordinal(), "check_decay=true,decayable=false," + type.getClientName());
			registerBlockModel(ModBlocks.leaves, type.ordinal(), "check_decay=false,decayable=true," + type.getClientName());
			registerBlockModel(ModBlocks.leaves, type.ordinal(), "check_decay=true,decayable=true," + type.getClientName());

			for (BlockLog.EnumAxis axis : BlockLog.EnumAxis.values()) {
				registerBlockModel(ModBlocks.logs, type.ordinal(), "axis=" + axis.getName() + "," + type.getClientName());
			}

			registerBlockModel(ModBlocks.planks, type.ordinal(), type);

			for (int stage = 0; stage < 2; ++stage) {
				registerBlockModel(ModBlocks.saplings, type.ordinal(), "stage=" + stage + "," + type.getClientName());
			}

			registerBlockModel(ModBlocks.saplings, type.ordinal(), type);
			registerBlockModel(ModBlocks.woodChest, type.ordinal(), type);
		}

		ClientRegistry.bindTileEntitySpecialRenderer(TileConduit.class, new RenderConduit());
		ClientRegistry.bindTileEntitySpecialRenderer(TileCrucible.class, new RenderCrucible());
		ClientRegistry.bindTileEntitySpecialRenderer(TileInfuser.class, new RenderInfuser());
		ClientRegistry.bindTileEntitySpecialRenderer(TileInfuserDark.class, new RenderInfuser());
		ClientRegistry.bindTileEntitySpecialRenderer(TileWoodChest.class, new RenderWoodChest());

		RenderingRegistry.registerEntityRenderingHandler(EntityVisSlime.class, new RenderColoredSlimeFactory(0xFFFF00FF));
	}

	private void registerBlockModel(Block block, int meta, String variant) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, new ModelResourceLocation(block.getRegistryName(), variant));
	}

	private void registerBlockModel(Block block, int meta, IVariant variant) {
		registerBlockModel(block, meta, variant.getClientName());
	}

	private void registerItemModel(Item item, int meta, String variant) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), variant));
	}

	private void registerItemModel(Item item, int meta, IVariant variant) {
		registerItemModel(item, meta, variant.getClientName());
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);

		ColorHandler.registerColorHandlers();
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}

	@Override
	public World getClientWorld() {
		return FMLClientHandler.instance().getClient().theWorld;
	}

	@Override
	public void greenFlameFX(World world, float x, float y, float z) {
		ParticleGreenFlame flame = new ParticleGreenFlame(world, x, y, z, 0F, 0F, 0F);
		Minecraft.getMinecraft().effectRenderer.addEffect(flame);
	}

	@Override
	public void smallGreenFlameFX(World world, float x, float y, float z) {
		ParticleGreenFlame flame = new ParticleSmallGreenFlame(world, x, y, z, 0F, 0F, 0F);
		Minecraft.getMinecraft().effectRenderer.addEffect(flame);
	}

	@Override
	public void customWispFX(World world, double x, double y, double z, double destX, double destY, double destZ, float gravity, int type) {
		ParticleWisp fx = new ParticleWisp(world, x, y, z, destX, destY, destZ, gravity, type);
		ParticleEngine.INSTANCE.addEffect(world, fx);
	}

	@Override
	public void wispFX(World world, double x, double y, double z, float f, float g, float h, float i) {
		ParticleWisp fx = new ParticleWisp(world, x, y, z, f, g, h, i);
		fx.setGravity(0.02F);

		ParticleEngine.INSTANCE.addEffect(world, fx);
	}

	@Override
	public void wispFX2(World world, double x, double y, double z, float size, int type, boolean shrink, boolean clip, float gravity) {
		ParticleWisp fx = new ParticleWisp(world, x, y, z, size, type);
		fx.setGravity(gravity);
		fx.shrink = shrink;
		fx.setNoClip(clip);

		ParticleEngine.INSTANCE.addEffect(world, fx);
	}

	@Override
	public void wispFX3(World world, double x, double y, double z, double destX, double destY, double destZ, float size, int type, boolean shrink, float gravity) {
		ParticleWisp fx = new ParticleWisp(world, x, y, z, destX, destY, destZ, size, type);

		fx.setGravity(gravity);
		fx.shrink = shrink;

		ParticleEngine.INSTANCE.addEffect(world, fx);
	}

	@Override
	public void wispFX4(World world, double x, double y, double z, Entity target, int type, boolean shrink, float gravity) {
		ParticleWisp fx = new ParticleWisp(world, x, y, z, target, type);
		fx.setGravity(gravity);
		fx.shrink = shrink;

		ParticleEngine.INSTANCE.addEffect(world, fx);
	}
}