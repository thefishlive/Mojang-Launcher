package io.github.thefishlive.minecraft.profiles.utils;

import java.io.File;
import java.util.Locale;

public class Utils {

	/**
	 * Static utils class, should never have to be constructed.
	 */
	private Utils() {
	}
	
	public static File getMinecraftDirectory() {
		return new File(getAppData(), ".minecraft");
	}
	
	public static File getAppData() {
		String userHomeDir = System.getProperty("user.home", ".");
		String osType = System.getProperty("os.name").toLowerCase(Locale.ENGLISH);
		if (osType.contains("win") && System.getenv("APPDATA") != null) {
			return new File(System.getenv("APPDATA"));
		} else if (osType.contains("mac")) {
			return new File(new File(userHomeDir, "Library"), "Application Support");
		} else {
			return new File(userHomeDir);
		}
	}
	
}