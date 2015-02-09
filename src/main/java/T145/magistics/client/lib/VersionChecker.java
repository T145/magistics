package T145.magistics.client.lib;

import java.awt.Desktop;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

import javax.swing.JOptionPane;

import T145.magistics.common.Magistics;

public class VersionChecker
{
	public static class Version
	{
		public int[] digits;

		public Version(String text)
		{
			String[] split = text.split("\\.");
			this.digits = new int[split.length];
			for(int i = 0; i < split.length; i++)
				this.digits[i] = Integer.parseInt(split[i]);
		}

		public boolean greater(Version v)
		{
			for(int i = 0; i < this.digits.length && i < v.digits.length; i++)
				if(this.digits[i] > v.digits[i])
					return true;
				else if(this.digits[i] < v.digits[i])
					return false;
			return this.digits.length > v.digits.length;
		}

		public boolean less(Version v)
		{
			for(int i = 0; i < this.digits.length && i < v.digits.length; i++)
				if(this.digits[i] < v.digits[i])
					return true;
				else if(this.digits[i] > v.digits[i])
					return false;
			return this.digits.length < v.digits.length;
		}

		@Override
		public boolean equals(Object o)
		{
			if(!(o instanceof Version))
				return false;

			Version v = (Version) o;
			if(v.digits.length != this.digits.length)
				return false;
			for(int i = 0; i < this.digits.length; i++)
				if(this.digits[i] != v.digits[i])
					return false;
			return true;
		}

		@Override
		public String toString()
		{
			String s = ""+this.digits[0];
			for(int i = 1; i < this.digits.length; i++)
				s += "."+this.digits[i];
			return s;
		}

		public boolean lessMajor(Version v) {
			for(int i = 0; i < 2 && i < v.digits.length; i++)
				if(this.digits[i] < v.digits[i])
					return true;
				else if(this.digits[i] > v.digits[i])
					return false;
			return this.digits.length < v.digits.length;
		}
	}

	/*public static class MultiVersion
	{
		public Version[] versions;

		public MultiVersion(String text)
		{
			String[] split = text.replace(" ", "").split(",");
			this.versions = new Version[split.length];
			for(int i = 0; i < split.length; i++)
				this.versions[i] = new Version(split[i]);
		}

		@Override
		public String toString()
		{
			String s = ""+this.versions[0];
			for(int i = 1; i < this.versions.length; i++)
				s += ", "+this.versions[i];
			return s;
		}
	}*/

	public static void check()
	{
		try
		{
			URL url = new URL("https://dl.dropboxusercontent.com/s/pfrwfrj5m03jsrz/version.txt");
			url.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			boolean update = false;
			String pline = "", line = br.readLine();

			while(line != null)
			{

				if(Magistics.proxy.checkUpdates.getBoolean(true))
				{
					if(line.split(" ")[0].equals(Magistics.proxy.getMinecraftVersion()) && new Version(Magistics.version).less(new Version(line.split(" ")[1])))
					{
						update = true;
					}
				} else {
					//TODO: add check for major updates e.g for 1.5
					if(line.split(" ")[0].equals(Magistics.proxy.getMinecraftVersion()) && new Version(Magistics.version).lessMajor(new Version(line.split(" ")[1])))
					{
						update = true;
					}
				}

				pline = line;
				line = br.readLine();
			}
			br.close();

			if(update)
			{
				Object[] buttons = {"Yes", "No", "Notify me only for Major changes!"};

				int n = JOptionPane .showOptionDialog(
						new Frame(),
						"New update available for the Crafting Pillars mod! Do you want to check it out?",//Change for Elysium - ismod loaded
						"Update is available!",
						JOptionPane.DEFAULT_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, buttons,
						buttons[0]);

				if(n == 0)
				{
					System.out.println("[UPDATE] "+pline);
					Desktop.getDesktop().browse(new URI(pline));
					System.exit(0);
				}

				if(n == 2)
				{
					Magistics.proxy.checkUpdates.set(false);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
