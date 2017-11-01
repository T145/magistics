package T145.magistics.client.lib;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.lwjgl.opengl.ARBShaderObjects;

import T145.magistics.Magistics;
import joptsimple.internal.Strings;
import net.minecraft.client.Minecraft;

public final class Shaders {

	private static final String PATH_PREFIX = "/assets/magistics/shaders/";
	private static final int VERT = 35633;
	private static final int FRAG = 35632;

	public static int endShader = 0;
	public static ShaderCallback shaderCallback;

	public static void init() {
		if (!Shaders.useShaders()) {
			return;
		}

		endShader = Shaders.createProgram("ender.vert", "ender.frag");
		shaderCallback = new ShaderCallback() {
			public void call(int shader) {
				Minecraft mc = Minecraft.getMinecraft();
				int x = ARBShaderObjects.glGetUniformLocationARB(shader, "yaw");
				ARBShaderObjects.glUniform1fARB(x, (float) (mc.player.rotationYaw * 2.0F * Math.PI / 360.0D));
				int z = ARBShaderObjects.glGetUniformLocationARB(shader, "pitch");
				ARBShaderObjects.glUniform1fARB(z, -(float) (mc.player.rotationPitch * 2.0F * Math.PI / 360.0D));
			}
		};
	}

	public static void useShader(int shader, ShaderCallback callback) {
		if (!Shaders.useShaders()) {
			return;
		}

		ARBShaderObjects.glUseProgramObjectARB(shader);

		if (shader != 0) {
			int time = ARBShaderObjects.glGetUniformLocationARB(shader, "time");
			ARBShaderObjects.glUniform1iARB(time, Minecraft.getMinecraft().getRenderViewEntity().ticksExisted);

			if (callback != null) {
				callback.call(shader);
			}
		}
	}

	public static void useShader(int shader) {
		Shaders.useShader(shader, null);
	}

	public static void releaseShader() {
		Shaders.useShader(0);
	}

	public static boolean useShaders() {
		return true;
	}

	private static int createProgram(String vert, String frag) {
		int vertId = 0;
		int fragId = 0;
		int program = 0;

		if (vert != null) {
			vertId = Shaders.createShader(PATH_PREFIX + vert, VERT);
		}

		if (frag != null) {
			fragId = Shaders.createShader(PATH_PREFIX + frag, FRAG);
		}

		if ((program = ARBShaderObjects.glCreateProgramObjectARB()) == 0) {
			return 0;
		}

		if (vert != null) {
			ARBShaderObjects.glAttachObjectARB(program, vertId);
		}

		if (frag != null) {
			ARBShaderObjects.glAttachObjectARB(program, fragId);
		}

		ARBShaderObjects.glLinkProgramARB(program);

		if (ARBShaderObjects.glGetObjectParameteriARB(program, 35714) == 0) {
			return 0;
		}

		ARBShaderObjects.glValidateProgramARB(program);

		if (ARBShaderObjects.glGetObjectParameteriARB(program, 35715) == 0) {
			return 0;
		}

		return program;
	}

	private static int createShader(String filePath, int shaderType) {
		int shader = 0;

		try {
			shader = ARBShaderObjects.glCreateShaderObjectARB(shaderType);

			if (shader == 0) {
				return 0;
			}

			ARBShaderObjects.glShaderSourceARB(shader, Shaders.readFileAsString(filePath));
			ARBShaderObjects.glCompileShaderARB(shader);

			if (ARBShaderObjects.glGetObjectParameteriARB(shader, 35713) == 0) {
				throw new RuntimeException("Error creating shader: " + Shaders.getLogInfo(shader));
			}

			return shader;
		} catch (Exception err) {
			ARBShaderObjects.glDeleteObjectARB(shader);
			Magistics.LOG.catching(err);
			return -1;
		}
	}

	private static String getLogInfo(int obj) {
		return ARBShaderObjects.glGetInfoLogARB(obj, ARBShaderObjects.glGetObjectParameteriARB(obj, 35716));
	}

	private static String readFileAsString(String filePath) throws Exception {
		StringBuilder fileData = new StringBuilder();
		InputStream inStream = Shaders.class.getResourceAsStream(filePath);

		if (inStream == null) {
			return Strings.EMPTY;
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "UTF-8"));
		char[] buf = new char[1024];
		int numRead = 0;

		while ((numRead = reader.read(buf)) != -1) {
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
		}

		reader.close();
		return fileData.toString();
	}
}