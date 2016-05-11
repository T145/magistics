package T145.magistics.tiles;

import thaumcraft.common.tiles.TileChestHungry;

public class TileChestHungryTrapped extends TileChestHungry {
	@Override
	public void openInventory() {
		if (numUsingPlayers < 0) {
			numUsingPlayers = 0;
		}
		numUsingPlayers++;
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, getBlockType(), 1, numUsingPlayers);
		worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, getBlockType());
		worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord - 1, zCoord, getBlockType());
	}

	@Override
	public void closeInventory() {
		numUsingPlayers--;
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, getBlockType(), 1, numUsingPlayers);
		worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, getBlockType());
		worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord - 1, zCoord, getBlockType());
	}
}