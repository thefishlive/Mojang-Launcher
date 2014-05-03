package io.github.thefishlive.minecraft.profiles;

import java.io.File;
import java.util.*;

import com.google.gson.annotations.SerializedName;
import lombok.NonNull;

/**
 * Game profiles are profiles used to set the settings that minecraft runs
 * with, eg. the game version, default resolution and the launcher's
 * visibility rule, along with what authentication profile is used to identify
 * the user.
 *
 * @author James
 * @version 1.0.0
 */
public class GameProfile {
	@NonNull private final String name;
	private String lastVersionId;
	@SerializedName("allowedReleaseTypes") private List<ReleaseType> allowedReleases = new ArrayList<>();
	private UUID playerUUID;
	@SerializedName("launcherVisibilityOnGameClose") private VisibilityRule launcherVisibility;
	private JavaArgs javaArgs;
	private Resolution resolution;
	private File gameDir;
	private File javaDir;
	@SerializedName("useHopperCrashService") private boolean useHopper;
	
	@Override
	public int hashCode() {
		int prime = 5;
		int hash = 1;
		hash += prime * name.hashCode();
		if (lastVersionId != null) hash += prime * lastVersionId.hashCode();
		if (allowedReleases != null) hash += prime * allowedReleases.hashCode();
		if (playerUUID != null) hash += prime * playerUUID.hashCode();
		if (launcherVisibility != null) hash += prime * launcherVisibility.hashCode();
		if (javaArgs != null) hash += prime * javaArgs.hashCode();
		if (resolution != null) hash += prime * resolution.hashCode();
		if (gameDir != null) hash += prime * gameDir.hashCode();
		if (javaDir != null) hash += prime * javaDir.hashCode();
		hash += prime * (useHopper ? 1 : 2);
		return hash;
	}
	
	public boolean equals(Object obj) {
		if (!(obj instanceof GameProfile)) return false;
		return hashCode() == obj.hashCode();
	}
	
	public static class Resolution {
		private final int width;
		private final int height;
		
		@Override
		public java.lang.String toString() {
			return "GameProfile.Resolution(width=" + this.getWidth() + ", height=" + this.getHeight() + ")";
		}
		
		@Override
		public int hashCode() {
			return 31 * width + height;
		}
		
		public Resolution(final int width, final int height) {
			this.width = width;
			this.height = height;
		}
		
		public int getWidth() {
			return this.width;
		}
		
		public int getHeight() {
			return this.height;
		}
	}
	
	public static enum ReleaseType {
		SNAPSHOT("snapshot"),
		RELEASE("release"),
		BETA("old_beta"),
		ALPHA("old_alpha");
		private static final Map<String, ReleaseType> TYPES = new HashMap<>();
		private final String id;
		static {
			for (ReleaseType type : values()) {
				TYPES.put(type.getId(), type);
			}
		}
		
		public static ReleaseType fromId(String id) {
			return TYPES.get(id);
		}
		
		private ReleaseType(final String id) {
			this.id = id;
		}
		
		public String getId() {
			return this.id;
		}
	}
	
	public GameProfile(@NonNull String name, String lastVersion, List<ReleaseType> allowedReleases, UUID user, 
						VisibilityRule visibility, String javaArgs, Resolution resolution, File gameDir, File javaDir) {
		this(name, lastVersion, allowedReleases, user, visibility, new JavaArgs(javaArgs), resolution, gameDir, javaDir);
		if (name == null) {
			throw new NullPointerException("name");
		}
	}
	
	public GameProfile(@NonNull String name, String lastVersion, List<ReleaseType> allowedReleases, UUID user, 
						VisibilityRule visibility, JavaArgs javaArgs, Resolution resolution, File gameDir, File javaDir) {
		if (name == null) {
			throw new NullPointerException("name");
		}
		this.name = name;
		this.lastVersionId = lastVersion;
		this.allowedReleases = allowedReleases;
		this.playerUUID = user;
		this.launcherVisibility = visibility;
		this.javaArgs = javaArgs;
		this.resolution = resolution;
		this.gameDir = gameDir;
		this.javaDir = javaDir;
	}
	
	public GameProfile(@NonNull final String name) {
		if (name == null) {
			throw new NullPointerException("name");
		}
		this.name = name;
	}
	
	@NonNull
	public String getName() {
		return this.name;
	}
	
	public String getLastVersionId() {
		return this.lastVersionId;
	}
	
	public void setLastVersionId(final String lastVersionId) {
		this.lastVersionId = lastVersionId;
	}
	
	public List<ReleaseType> getAllowedReleaseTypes() {
		return this.allowedReleases;
	}
	
	public void setAllowedReleaseTypes(final List<ReleaseType> allowedReleaseTypes) {
		this.allowedReleases = allowedReleaseTypes;
	}
	
	public void addReleaseType(ReleaseType type) {
		this.allowedReleases.add(type);
	}
	
	public UUID getPlayerUUID() {
		return this.playerUUID;
	}
	
	public void setPlayerUUID(final UUID playerUUID) {
		this.playerUUID = playerUUID;
	}
	
	public VisibilityRule getLauncherVisibility() {
		return this.launcherVisibility;
	}
	
	public void setLauncherVisibility(final VisibilityRule launcherVisibility) {
		this.launcherVisibility = launcherVisibility;
	}
	
	public JavaArgs getJavaArgs() {
		return this.javaArgs;
	}
	
	public void setJavaArgs(final String javaArgs) {
		this.javaArgs = new JavaArgs(javaArgs);
	}
	
	public void setJavaArgs(final JavaArgs javaArgs) {
		this.javaArgs = javaArgs;
	}
	
	public Resolution getResolution() {
		return this.resolution;
	}
	
	public void setResolution(final Resolution resolution) {
		this.resolution = resolution;
	}
	
	public File getGameDir() {
		return this.gameDir;
	}
	
	public void setGameDir(final File gameDir) {
		this.gameDir = gameDir;
	}
	
	public File getJavaDir() {
		return this.javaDir;
	}
	
	public void setJavaDir(final File javaDir) {
		this.javaDir = javaDir;
	}
	
	@Override
	public java.lang.String toString() {
		return "GameProfile(name=" + this.getName() + ", lastVersionId=" + this.getLastVersionId() + ", allowedReleases=" + this.allowedReleases + ", playerUUID=" + this.getPlayerUUID() + ", launcherVisibility=" + this.getLauncherVisibility() + ", javaArgs=" + this.getJavaArgs() + ", resolution=" + this.getResolution() + ", gameDir=" + this.getGameDir() + ", javaDir=" + this.getJavaDir() + ", useHopper=" + this.useHopper + ")";
	}
}