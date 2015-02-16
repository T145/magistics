package T145.magistics.client.lib.pillars;

import java.util.List;
import java.util.Random;

public class Blobs {
	public float x, y, z, velX, velY, velZ;
	public int strength;
	public static int scale = 2;
	private Random random = new Random();
	private int minX = 1, maxX = 13,  minY = 3, maxY = 13, minZ = 1, maxZ = 13;

	public Blobs(float sizeX, float sizeY, float sizeZ, int power) {
		x = sizeX;
		y = sizeY;
		z = sizeZ;
		strength = power;
		setVelocity();
	}

	public void setVelocity(float velocityX, float velocityY, float velocityZ) {
		velX = velocityX;
		velY = velocityY;
		velZ = velocityZ;
	}

	public void setVelocity() {
		if (Math.abs(strength) > 4) {
			velX = (random.nextInt(20) + 1) / 10;
			velY = (random.nextInt(20) + 1) / 10;
			velZ = (random.nextInt(20) + 1) / 10;
		} else {
			velX = (random.nextInt(50) + 1) / 10;
			velY = (random.nextInt(50) + 1) / 10;
			velZ = (random.nextInt(50) + 1) / 10;
		}

		if (random.nextBoolean())
			velX *= -1F;
		if (random.nextBoolean())
			velY *= -1F;
		if (random.nextBoolean())
			velZ *= -1F;
	}

	public void update() {
		update(1F);
	}

	public Blobs setBounds(int lowX, int lowY, int lowZ, int highX, int highY, int highZ) {
		minX = lowX;
		minY = lowY;
		minZ = lowZ;

		maxX = highX;
		maxY = highY;
		maxZ = highZ;

		return this;
	}

	public void update(float speed) {
		if (x > maxX || x < minX)
			velX *= -1F;
		if (y > maxY || y < minY)
			velY *= -1F;
		if (z > maxZ || z < minZ)
			velZ *= -1F;

		x += speed * velX;
		y += speed * velY;
		z += speed * velZ;
	}

	private static float f(float r, int strength) {
		return strength / r;
	}

	private static float f(float r) {
		return f(r, 3);
	}

	public static float[][][] fieldStrength(List<Blobs> blobs) {
		float result[][][] = new float[16 * scale][16 * scale][16 * scale];

		for (int x = 0; x < 16; x++)
			for (int y = 0; y < 16; y++)
				for (int z = 0; z < 16; z++)
					if (x > 1 && x < 14 && y > 2 && y < 13 && z > 1 && z < 14)
						for (int i = 0; i < blobs.size(); i++) {
							float xDist = blobs.get(i).x - x, yDist = blobs.get(i).y - y, zDist = blobs.get(i).z - z, r = xDist * xDist + yDist * yDist + zDist * zDist;
							result[x][y][z] += f(r, blobs.get(i).strength);
						}

		return result;
	}
}