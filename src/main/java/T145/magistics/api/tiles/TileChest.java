package T145.magistics.api.tiles;

public abstract class TileChest extends TileMagisticsInventory {
	public int numUsingPlayers;
	public float prevLidAngle;
	public float lidAngle;

	public TileChest(boolean canFace, boolean canOwn) {
		super(36, 27, canFace, canOwn);
	}

	@Override
	public abstract String getInventoryName();

	@Override
	public void updateEntity() {
		prevLidAngle = lidAngle;

		if (numUsingPlayers > 0 && lidAngle == 0F) {
			worldObj.playSoundEffect(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D, "random.chestopen", 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
		}

		if (numUsingPlayers == 0 && lidAngle > 0F || numUsingPlayers > 0 && lidAngle < 1F) {
			float oldAngle = lidAngle;

			if (numUsingPlayers > 0) {
				lidAngle += 0.1F;
			} else {
				lidAngle -= 0.1F;
			}

			if (lidAngle > 1F) {
				lidAngle = 1F;
			}

			if (lidAngle < 0.5F && oldAngle >= 0.5F) {
				worldObj.playSoundEffect(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D, "random.chestclosed", 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
			}

			if (lidAngle < 0F) {
				lidAngle = 0F;
			}
		}
	}

	@Override
	public boolean receiveClientEvent(int id, int data) {
		switch (id) {
		case 0:
			numUsingPlayers = data;
			return true;
		default:
			return tileEntityInvalid;
		}
	}

	@Override
	public void openInventory() {
		numUsingPlayers += 1;
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, getBlockType(), 0, numUsingPlayers);
	}

	@Override
	public void closeInventory() {
		numUsingPlayers -= 1;
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, getBlockType(), 0, numUsingPlayers);
	}
}