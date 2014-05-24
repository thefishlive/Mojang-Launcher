package io.github.thefishlive.minecraft.profiles;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AuthProfile {

	public static class Property {
		
		private final String name;
		private final String value;
		
		public Property(String name, String value) {
			this.name = name;
			this.value = value;
		}
		
		@Override
		public java.lang.String toString() {
			return "AuthProfile.Property(name=" + this.getName() + ", value=" + this.getValue() + ")";
		}
		
		public String getName() {
			return this.name;
		}
		
		public String getValue() {
			return this.value;
		}
		
		public boolean equals(Object obj) {
			if (!(obj instanceof Property)) return false;
			Property prop = (Property) obj;
			return prop.name.equals(name) && prop.value.equals(value);
		}
	}

	private String displayName;
	private List<Property> userProperties = new ArrayList<>();
	private String accessToken;
	private String userid;
	private final UUID uuid;
	private final String username;
	
	public AuthProfile(UUID uuid, String username) {
		this.uuid = uuid;
		this.username = username;
	}

	@Override
	public java.lang.String toString() {
		return "AuthProfile(username=" + this.getUsername() + ", uuid=" + this.getUuid() + ", userProperties=" + this.getUserProperties() + ", accessToken=" + this.getAccessToken() + ", userid=" + this.getUserid() + ", displayName=" + this.getDisplayName() + ")";
	}

	public final UUID getUuid() {
		return this.uuid;
	}

	public final String getUsername() {
		return this.username;
	}
	
	public List<Property> getUserProperties() {
		return this.userProperties;
	}
	
	public void setUserProperties(List<Property> properties) {
		this.userProperties = properties;
	}
	
	public void addUserProperty(Property property) {
		this.userProperties.add(property);
	}
	
	public String getAccessToken() {
		return this.accessToken;
	}
	
	public void setAccessToken(String token) {
		this.accessToken = token;
	}
	
	public String getUserid() {
		return this.userid;
	}
	
	public void setUserId(String token) {
		this.userid = token;
	}
	
	public String getDisplayName() {
		return this.displayName;
	}
	
	public void setDisplayName(String name) {
		this.displayName = name;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthProfile that = (AuthProfile) o;

        if (accessToken != null ? !accessToken.equals(that.accessToken) : that.accessToken != null) return false;
        if (displayName != null ? !displayName.equals(that.displayName) : that.displayName != null) return false;
        if (userProperties != null ? !userProperties.equals(that.userProperties) : that.userProperties != null) return false;
        if (userid != null ? !userid.equals(that.userid) : that.userid != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (uuid != null ? !uuid.equals(that.uuid) : that.uuid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = displayName != null ? displayName.hashCode() : 0;
        result = 31 * result + (userProperties != null ? userProperties.hashCode() : 0);
        result = 31 * result + (accessToken != null ? accessToken.hashCode() : 0);
        result = 31 * result + (userid != null ? userid.hashCode() : 0);
        result = 31 * result + (uuid != null ? uuid.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }
}