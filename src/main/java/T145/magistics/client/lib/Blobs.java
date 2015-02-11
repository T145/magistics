package T145.magistics.client.lib;

import java.util.List;
import java.util.Random;

public class Blobs
{
	public float x, y, z, velX, velY, velZ;
	public int strength;

	public static int scale = 2;

	private Random random = new Random();
	
	private int minX = 1;
	private int maxX = 13;
	private int minY = 3;
	private int maxY = 13;
	private int minZ = 1;
	private int maxZ = 13;

	public Blobs(float x, float y, float z, int strength)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.strength = strength;
		this.setVelocity();
	}

	public void setVelocity(float velX, float velY, float velZ)
	{
		this.velX = velX;
		this.velY = velY;
		this.velZ = velZ;
	}

	public void setVelocity()
	{
		if(Math.abs(this.strength) > 4)
		{
			this.velX = (this.random.nextInt(20)+1)/10;
			this.velY = (this.random.nextInt(20)+1)/10;
			this.velZ = (this.random.nextInt(20)+1)/10;
		} else {
			this.velX = (this.random.nextInt(50)+1)/10;
			this.velY = (this.random.nextInt(50)+1)/10;
			this.velZ = (this.random.nextInt(50)+1)/10;
		}

		if(this.random.nextBoolean())
			this.velX *= -1F;
		if(this.random.nextBoolean())
			this.velY *= -1F;
		if(this.random.nextBoolean())
			this.velZ *= -1F;
	}

	public void update()
	{
		this.update(1F);
	}

	public Blobs setBounds(int minX, int maxX, int minY, int maxY, int minZ, int maxZ)
	{
		this.minX = minX;
		this.minY = minY;
		this.minZ = minZ;
		
		this.maxX = maxX;
		this.maxY = maxY;
		this.maxZ = maxZ;
		
		return this;
	}
	
	public void update(float speed)
	{
		if(this.x > maxX || this.x < minX)
			this.velX *= -1F;

		if(this.z > maxZ || this.z < minZ)
			this.velZ *= -1F;

		if(this.y > maxY || this.y < minY)
			this.velY *= -1F;

		this.x += speed * this.velX;
		this.y += speed * this.velY;
		this.z += speed * this.velZ;
	}

	private static float f(float r, int strength)
	{
		return strength/r;
	}

	private static float f(float r)
	{
		return f(r, 3);
	}

	public static float[][][] fieldStrength(List<Blobs> blobs)
	{
		float result[][][] = new float[16*scale][16*scale][16*scale];

		for(int x = 0; x < 16; x++)
		{
			for(int y = 0; y < 16; y++)
			{
				for(int z = 0; z < 16; z++)
				{
					if(x>1 && x<14 && y>2 && y<13 && z>1 && z<14)
					{
						for(int i = 0; i < blobs.size(); i++)
						{
							float xDist = blobs.get(i).x - x;
							float yDist = blobs.get(i).y - y;
							float zDist = blobs.get(i).z - z;
							float r = xDist*xDist + yDist*yDist + zDist*zDist; //distance square
							result[x][y][z] += f(r, blobs.get(i).strength);
						}
					}
				}
			}
		}

		return result;
	}
}