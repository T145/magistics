package T145.magistics.world.features;

import java.util.List;
import java.util.Random;

import net.minecraft.util.EnumFacing;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.StructureVillagePieces.PieceWeight;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
import net.minecraft.world.gen.structure.StructureVillagePieces.Village;
import net.minecraftforge.fml.common.registry.VillagerRegistry.IVillageCreationHandler;

public class VillageCreationHandler implements IVillageCreationHandler {

	private final PieceWeight houseRarity = new PieceWeight(getComponentClass(), 1, 10);

	public VillageCreationHandler() {
		MapGenStructureIO.registerStructureComponent(getComponentClass(), "MagisticsHouse");
	}

	@Override
	public PieceWeight getVillagePieceWeight(Random random, int i) {
		return houseRarity;
	}

	@Override
	public Class<? extends StructureVillagePieces.Village> getComponentClass() {
		return VillagePieceDungeon.class;
	}

	@Override
	public Village buildComponent(PieceWeight villagePiece, Start startPiece, List<StructureComponent> pieces, Random random, int p1, int p2, int p3, EnumFacing facing, int p5) { 
		return VillagePieceDungeon.createPiece(startPiece, pieces, random, p1, p2, p3, facing, p5);
	}
}