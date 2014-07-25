package io.github.thefishlive.minecraft.profiles;

import java.io.File;
import java.util.*;

import com.google.gson.annotations.SerializedName;
import io.github.thefishlive.minecraft.ReleaseType;
import lombok.NonNull;

/**
 * Game profiles are profiles used to set the settings that minecraft runs
 * with, eg. the game version, default resolution and the launcher's
 * visibility rule.
 *
 * @author James
 * @version 1.0.0
 */
public class GameProfile {
	@NonNull private final String name;
	private String lastVersionId;
	@SerializedName("allowedReleaseTypes") private List<ReleaseType> allowedReleases = new ArrayList<>();
	@SerializedName("launcherVisibilityOnGameClose") private VisibilityRule launcherVisibility;
	private JavaArgs javaArgs;
	private Resolution resolution;
	private File gameDir;
	private File javaDir;
	@SerializedName("useHopperCrashService") private boolean useHopper;

    public GameProfile(@NonNull String name, String lastVersion, List<ReleaseType> allowedReleases,
						VisibilityRule visibility, String javaArgs, Resolution resolution, File gameDir, File javaDir) {
		this(name, lastVersion, allowedReleases, visibility, new JavaArgs(javaArgs), resolution, gameDir, javaDir);
		if (name == null) {
			throw new NullPointerException("name");
		}
	}
	
	public GameProfile(@NonNull String name, String lastVersion, List<ReleaseType> allowedReleases,
						VisibilityRule visibility, JavaArgs javaArgs, Resolution resolution, File gameDir, File javaDir) {
		if (name == null) {
			throw new NullPointerException("name");
		}
		this.name = name;
		this.lastVersionId = lastVersion;
		this.allowedReleases = allowedReleases;
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
    public int hashCode() {
        int prime = 5;
        int hash = 1;
        hash += prime * name.hashCode();
        if (lastVersionId != null) hash += prime * lastVersionId.hashCode();
        if (allowedReleases != null) hash += prime * allowedReleases.hashCode();
        if (launcherVisibility != null) hash += prime * launcherVisibility.hashCode();
        if (javaArgs != null) hash += prime * javaArgs.hashCode();
        if (resolution != null) hash += prime * resolution.hashCode();
        if (gameDir != null) hash += prime * gameDir.hashCode();
        if (javaDir != null) hash += prime * javaDir.hashCode();
        hash += prime * (useHopper ? 1 : 2);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameProfile that = (GameProfile) o;

        if (useHopper != that.useHopper) return false;
        if (allowedReleases != null ? !allowedReleases.equals(that.allowedReleases) : that.allowedReleases != null) return false;
        if (gameDir != null ? !gameDir.equals(that.gameDir) : that.gameDir != null) return false;
        if (javaArgs != null ? !javaArgs.equals(that.javaArgs) : that.javaArgs != null) return false;
        if (javaDir != null ? !javaDir.equals(that.javaDir) : that.javaDir != null) return false;
        if (lastVersionId != null ? !lastVersionId.equals(that.lastVersionId) : that.lastVersionId != null) return false;
        if (launcherVisibility != that.launcherVisibility) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (resolution != null ? !resolution.equals(that.resolution) : that.resolution != null) return false;

        return true;
    }

    @Override
	public java.lang.String toString() {
		return "GameProfile(name=" + this.getName() + ", lastVersionId=" + this.getLastVersionId() + ", allowedReleases=" + this.allowedReleases + ", launcherVisibility=" + this.getLauncherVisibility() + ", javaArgs=" + this.getJavaArgs() + ", resolution=" + this.getResolution() + ", gameDir=" + this.getGameDir() + ", javaDir=" + this.getJavaDir() + ", useHopper=" + this.useHopper + ")";
	}

    public static class Resolution {
        private final int width;
        private final int height;

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

        @Override
        public java.lang.String toString() {
            return "GameProfile.Resolution(width=" + this.getWidth() + ", height=" + this.getHeight() + ")";
        }

        @Override
        public int hashCode() {
            return 31 * width + height;
        }

    }

}