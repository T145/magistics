/**
 * Copyright (c) 2011-2014, SpaceToad and the BuildCraft Team
 * http://www.mod-buildcraft.com
 *
 * BuildCraft is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://www.mod-buildcraft.com/MMPL-1.0.txt
 */
package hu.hundevelopers.elysium;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import net.minecraft.event.ClickEvent;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.config.Property;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInterModComms;

public class Version implements Runnable
{
	public enum EnumUpdateState
	{
		CURRENT, OUTDATED, CONNECTION_ERROR
	}

	public static final String VERSION = Elysium.VERSION;
	public static EnumUpdateState currentVersion = EnumUpdateState.CURRENT;

	private static final String REMOTE_VERSION_FILE =
			"https://dl.dropboxusercontent.com/s/gc2qm5wk4uiovmj/elysium_version.txt?dl=0";

	private static String recommendedVersion;

	private static Version instance = new Version();

	private static boolean sentIMCOutdatedMessage = false;

	public static String getVersion() {
		return VERSION;
	}

	public static boolean isOutdated() {
		return currentVersion == EnumUpdateState.OUTDATED;
	}

	public static boolean needsUpdateNoticeAndMarkAsSeen() {
		if (!isOutdated() || sentIMCOutdatedMessage) {
			return false;
		}

		Property property = Elysium.config.get("vars", "version.seen", VERSION);
		property.comment = "indicates the last version the user has been informed about and will suppress further notices on it.";
		String seenVersion = property.getString();

		if (recommendedVersion == null || recommendedVersion.equals(seenVersion)) {
			return false;
		}

		property.set(recommendedVersion);
		Elysium.config.save();
		return true;
	}

	public static String getRecommendedVersion() {
		return recommendedVersion;
	}

	public static void versionCheck() {
		try {

			if ("0.0.0".equals(VERSION)) {
				return;
			}

			String location = REMOTE_VERSION_FILE;
			HttpURLConnection conn = null;
			while (location != null && !location.isEmpty()) {
				URL url = new URL(location);

				if (conn != null) {
					conn.disconnect();
				}

				conn = (HttpURLConnection) url.openConnection();
				conn.setRequestProperty("User-Agent",
						"Mozilla/5.0 (Windows; U; Windows NT 6.0; ru; rv:1.9.0.11) Gecko/2009060215 Firefox/3.0.11 (.NET CLR 3.5.30729)");
				conn.connect();
				location = conn.getHeaderField("Location");
			}

			if (conn == null) {
				throw new NullPointerException();
			}

			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String line;
			String mcVersion = Elysium.proxy.getMinecraftVersion();
			while ((line = reader.readLine()) != null) {
				String[] tokens = line.split(":");
				if (mcVersion.matches(tokens[0])) {
					if (Elysium.ID.matches(tokens[1])) {
						recommendedVersion = tokens[2];

						if (VERSION.matches(tokens[2])) {
							System.out.println("Using the latest version [" + getVersion() + "] for Minecraft " + mcVersion);
							currentVersion = EnumUpdateState.CURRENT;
							return;
						}
					}
				}
			}

			if(FMLCommonHandler.instance().getSide().isClient())
			{
				ChatComponentText text = new ChatComponentText("Version [" + recommendedVersion + "] of The Elysium mod is available. Click here!");
				text.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/wip-mods/2093146-wip-the-elysium-greek-themed-dimension-for-heroes"));
				FMLClientHandler.instance().getClientPlayerEntity().addChatMessage(text);


			} else {

				System.out.println("Version [" + recommendedVersion + "] of The Elysium mod is available.");
			}
			
			currentVersion = EnumUpdateState.OUTDATED;
			sendIMCOutdatedMessage();

			conn.disconnect();
			reader.close();
		} catch (Exception e) {
			System.out.println("Unable to read from remote version authority.");
			System.out.println(e.toString());
			currentVersion = EnumUpdateState.CONNECTION_ERROR;
		}
	}

	@Override
	public void run() {

		int count = 0;
		currentVersion = null;

		System.out.println("Beginning version check");

		try {
			while ((count < 3) && ((currentVersion == null) || (currentVersion == EnumUpdateState.CONNECTION_ERROR))) {
				versionCheck();
				count++;

				if (currentVersion == EnumUpdateState.CONNECTION_ERROR) {
					System.out.println("Version check attempt " + count + " failed, trying again in 10 seconds");
					Thread.sleep(10000);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (currentVersion == EnumUpdateState.CONNECTION_ERROR) {
			System.out.println("Version check failed");
		}

	}

	/**
	 * This is an integration with Dynious Version Checker See
	 * http://www.minecraftforum.net/topic/2721902-
	 */
	public static void sendIMCOutdatedMessage() {
		if (Loader.isModLoaded("VersionChecker")) {
			NBTTagCompound compound = new NBTTagCompound();
			compound.setString("modDisplayName", "Elysium mod");
			compound.setString("oldVersion", VERSION);
			compound.setString("newVersion", getRecommendedVersion());

			compound.setString("updateUrl", "http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/wip-mods/2093146-wip-the-elysium-greek-themed-dimension-for-heroes");//TODO: thread url
			compound.setBoolean("isDirectLink", false);

			FMLInterModComms.sendRuntimeMessage(Elysium.ID, "VersionChecker", "addUpdate", compound);
			sentIMCOutdatedMessage = true;
		}
	}

	public static void check() {
		System.out.println("[Elysium] Checking for updates");
		new Thread(instance).start();
	}
}