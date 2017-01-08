package T145.magistics.blocks;

import T145.magistics.api.enums.EnumConduit;
import T145.magistics.api.objects.IModel;
import T145.magistics.api.objects.ITile;
import T145.magistics.client.render.blocks.RenderConduit;
import T145.magistics.tiles.TileConduit;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockConduit extends MBlock<EnumConduit> implements IModel, ITile {

	public BlockConduit() {
		super("conduit", Material.CIRCUITS, EnumConduit.class);

		GameRegistry.registerTileEntity(TileConduit.class, TileConduit.class.getSimpleName());
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileConduit();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerRenderer() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileConduit.class, new RenderConduit());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerModel() {
		for (EnumConduit type : EnumConduit.values()) {
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), type.ordinal(), new ModelResourceLocation(getRegistryName(), type.getClientName()));
		}
	}
}