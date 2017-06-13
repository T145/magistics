package T145.magistics.blocks.cosmetic;

import java.util.Random;

import T145.magistics.client.lib.Render;
import T145.magistics.tiles.cosmetic.TileFloatingCandle;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockFloatingCandle extends BlockCandle {

	public BlockFloatingCandle() {
		super("floating_candle");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileFloatingCandle();
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		return true;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(Render.W6, 0D, Render.W6, Render.W10, 1D, Render.W10);
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.INVISIBLE;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		double offsetX = x + 0.5F;
		double offsetY = y + 0.7F + 0.25F;
		double offsetZ = z + 0.5F;
		int ticks = Minecraft.getMinecraft().player.ticksExisted;
		int offset = x + y + z;
		float move = 0.2F * MathHelper.sin(((offset * 10) + ticks) / 30.0F);

		world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, offsetX, offsetY + move, offsetZ, 0D, 0D, 0D, new int[0]);
		world.spawnParticle(EnumParticleTypes.FLAME, offsetX, offsetY + move, offsetZ, 0D, 0D, 0D, new int[0]);
	}
}