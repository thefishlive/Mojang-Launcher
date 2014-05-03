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
	
	public boolean equals(Object obj) {
		if (!(obj instanceof AuthProfile)) return false;
		AuthProfile other = (AuthProfile) obj;
		
		return displayName.equals(other.displayName) && userid.equals(other.userid) &&
				accessToken.equals(other.accessToken) && userProperties.equals(other.userProperties)&&
				username.equals(other.username) && uuid.equals(other.uuid);
	}
}