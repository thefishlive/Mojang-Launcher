package io.github.thefishlive.minecraft.profiles;

import io.github.thefishlive.minecraft.profiles.exception.InvalidProfileFileFormatException;
import io.github.thefishlive.minecraft.profiles.exception.LauncherDataException;
import io.github.thefishlive.minecraft.profiles.exception.ProfileFileNotFoundException;
import io.github.thefishlive.minecraft.profiles.utils.Utils;
import io.github.thefishlive.minecraft.json.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;

/**
 * Represents the minecraft 'launcher_profiles.json' file, this class allows
 * for interfacing with the file and altering the settings like for example
 * adding new Game or Authentication Profiles, as well as setting the current
 * active profile.
 * <p />
 * To load a profiles file use the method {@link ProfilesFile#load(File)} with
 * the file you pass in being the profiles file you want to load if you don't
 * pass in a file it will default to using the default minecraft profiles file
 * {@code %APP_DATA%\.minecraft\launcher_profiles.json}.
 * <p />
 * Please note that changes made in this library will only come into effect
 * on launcher restart.
 * <p />
 *
 * @author James
 * @version 1.0.0
 */
public class ProfilesFile {

    static final String CURRENT_VERSION = "1.4.5";
    static final int CURRENT_FORMAT = 15;

	static Gson GSON = new GsonBuilder()
							.setPrettyPrinting()
							.registerTypeAdapter(UUID.class, new UUIDAdapter())
							.registerTypeAdapter(File.class, new FileAdapter())
							.registerTypeAdapter(JavaArgs.class, new JavaArgsAdapter())
                            .registerTypeAdapter(Date.class, new DateAdapter())
                            .registerTypeAdapterFactory(new EnumTypeAdapterFactory())
							.create();
	
	private static File launcherProfiles = new File(Utils.getMinecraftDirectory(), "launcher_profiles.json");
	
	private Map<String, GameProfile> profiles = new HashMap<>();
	private Map<String, AuthProfile> authenticationDatabase = new HashMap<>();
	private String selectedProfile;
	private String clientToken;
    private UUID selectedUser;
    private LauncherVersionInfo launcherVersion = new LauncherVersionInfo(CURRENT_VERSION, CURRENT_FORMAT);

    private static class LauncherVersionInfo {
        private String name;
        private int format;

        public LauncherVersionInfo(String name, int format) {
            this.name = name;
            this.format = format;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("VersionInfo{");
            sb.append("name='").append(name).append('\'');
            sb.append(", format=").append(format);
            sb.append('}');
            return sb.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            LauncherVersionInfo that = (LauncherVersionInfo) o;

            if (format != that.format) return false;
            if (name != null ? !name.equals(that.name) : that.name != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + format;
            return result;
        }
    }
	
	/**
	 * Add a specified game profile to the profiles file.
	 *
	 * @param profile the game profile to add
	 * @throws LauncherDataException thrown if there is a error writing the 
	 * 				updated profiles file.
	 * @see GameProfile
	 */
	public void addProfile(GameProfile profile) throws LauncherDataException {
		profiles.put(profile.getName(), profile);
	}
	
	/**
	 * Add a specified authentication profile to the profiles file.
	 *
	 * @param profile the authentication profile to add
	 * @throws LauncherDataException thrown if there is a error writing the 
	 * 				updated profiles file.
	 * @see AuthProfile
	 */
	public void addProfile(AuthProfile profile) throws LauncherDataException {
		authenticationDatabase.put(profile.getUuid().toString().replace("-", ""), profile);
	}

    /**
     * Write the current profile file to the default minecraft launcher profiles file.
     *
     * @throws LauncherDataException thrown if there is a error writing the
     * 				updated profiles file.
     */
    public void write() throws LauncherDataException {
        write(launcherProfiles);
    }

	/**
	 * Write the current profile file to disk at a specified location
	 *
     * @param file the file to write the new profiles file to
	 * @throws LauncherDataException thrown if there is a error writing the 
	 * 				updated profiles file.
	 */
	public void write(File file) throws LauncherDataException {
		try (FileWriter writer = new FileWriter(file)) {
			GSON.toJson(this, writer);
			writer.flush();
		} catch (IOException | JsonIOException ex) {
			throw new LauncherDataException(ex); // TODO convert to specific exception
		}
	}
	
	/**
	 * Load the default minecraft launcher profiles file into memory.
	 *
	 * @return a new ProfilesFile object containing the data for the specified
	 * 				file on disk.
	 * @throws LauncherDataException if a error occures whilst loading the data
	 * 				into memory.
	 */
	public static ProfilesFile load() throws LauncherDataException {
		try {
			return load(launcherProfiles);
		} catch (FileNotFoundException ex) {
			throw new ProfileFileNotFoundException();
		}
	}
	
	/**
	 * Load the a specified profiles file into memory.
	 *
	 * @param file the file to load as a profiles file.
	 * @return a new ProfilesFile object containing the data for the specified
	 * 				file on disk.
	 * @throws LauncherDataException if a error occures whilst loading the data
	 * 				into memory.
	 * @throws FileNotFoundException if the specified file does not exist on
	 * 				disk.
	 */
	public static ProfilesFile load(File file) throws FileNotFoundException, LauncherDataException {
		if (!file.exists()) {
			throw new FileNotFoundException();
		}

		try (FileReader reader = new FileReader(file)) {
            ProfilesFile profiles = GSON.fromJson(reader, ProfilesFile.class);

            if (profiles.launcherVersion.format != CURRENT_FORMAT) {
                if (profiles.launcherVersion.format > CURRENT_FORMAT) {
                    throw new LauncherDataException("Library out of date. Expected launcher version " + CURRENT_FORMAT + " but got " + profiles.launcherVersion.format);
                } else if (profiles.launcherVersion.format < CURRENT_FORMAT) {
                    throw new LauncherDataException("Launcher file out of date. Expected launcher version " + CURRENT_FORMAT + " but got " + profiles.launcherVersion.format);
                }
            }

			return profiles;
		} catch (IOException | JsonParseException ex) {
			throw new InvalidProfileFileFormatException(ex);
		}
	}
	
	@java.lang.Override
	public java.lang.String toString() {
		return "ProfilesFile(profiles=" + this.getProfiles() + ", authenticationDatabase=" + this.getAuthenticationDatabase() + ", selectedProfile=" + this.getSelectedProfile() + ", clientToken=" + this.getClientToken() + ", selectedUser=" + this.getSelectedUser() + ", launcherVersion=" + this.launcherVersion + ")";
	}
	
	/**
	 * @see ProfilesFile#load(File)
	 */
	ProfilesFile(String clientToken) {
        this.clientToken = clientToken;
	}
	
	/**
	 * Get a map of the the {@link GameProfile}'s contained in the profiles
	 * file.
	 * 
	 * @return a map of the profile name to {@link GameProfile}
	 */
	public Map<String, GameProfile> getProfiles() {
		return this.profiles;
	}

	/**
	 * Get a map of the the {@link AuthProfile}'s contained in the auth
	 * database.
	 * 
	 * @return a map of the uuid to {@link AuthProfile}
	 */
	public Map<String, AuthProfile> getAuthenticationDatabase() {
		return this.authenticationDatabase;
	}
	
	/**
	 * Gets the currently selected {@link GameProfile}.
	 * 
	 * @return the currently selected {@link GameProfile}
	 */
	public GameProfile getSelectedProfile() {
		return this.profiles.get(this.selectedProfile);
	}

    /**
     * Sets the currently selected {@link GameProfile}.
     *
     * @param profile the new selected {@link GameProfile}
     */
    public void setSelectedProfile(GameProfile profile) {
        this.selectedProfile = profile.getName();
    }

	/**
	 * Gets the yggdrasil client token for this launcher instance.
	 * <p />
	 * This is sent with all authentication requests to the mojang servers
	 * to identify this computer. As such if changed all current access keys
	 * will become invalid.
	 *  
	 * @return
	 */
	public String getClientToken() {
		return this.clientToken;
	}

    /**
     * Gets the currently selected {@link AuthProfile}.
     *
     * @return the currently selected {@link AuthProfile}
     */
    public AuthProfile getSelectedUser() {
        return getAuthenticationDatabase().get(selectedUser);
    }

    /**
     * Sets the currently selected {@link AuthProfile}.
     *
     * @param profile the new selected {@link AuthProfile}
     */
    public void setSelectedUser(AuthProfile profile) {
        this.selectedUser = profile.getUuid();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProfilesFile that = (ProfilesFile) o;

        if (authenticationDatabase != null ? !authenticationDatabase.equals(that.authenticationDatabase) : that.authenticationDatabase != null) return false;
        if (!clientToken.equals(that.clientToken)) return false;
        if (profiles != null ? !profiles.equals(that.profiles) : that.profiles != null) return false;
        if (selectedProfile != null ? !selectedProfile.equals(that.selectedProfile) : that.selectedProfile != null) return false;
        if (selectedUser != null ? !selectedUser.equals(that.selectedUser) : that.selectedUser != null) return false;
        if (launcherVersion != null ? !launcherVersion.equals(that.launcherVersion) : that.launcherVersion != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = profiles != null ? profiles.hashCode() : 0;
        result = 31 * result + (authenticationDatabase != null ? authenticationDatabase.hashCode() : 0);
        result = 31 * result + (selectedProfile != null ? selectedProfile.hashCode() : 0);
        result = 31 * result + clientToken.hashCode();
        result = 31 * result + (selectedUser != null ? selectedUser.hashCode() : 0);
        result = 31 * result + (launcherVersion != null ? launcherVersion.hashCode() : 0);
        return result;
    }
}