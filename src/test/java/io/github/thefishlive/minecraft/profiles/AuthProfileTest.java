package io.github.thefishlive.minecraft.profiles;

import static org.junit.Assert.*;

import io.github.thefishlive.minecraft.profiles.AuthProfile;
import io.github.thefishlive.minecraft.profiles.ProfilesFile;
import io.github.thefishlive.minecraft.profiles.AuthProfile.Property;

import java.util.UUID;

import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.Test;

public class AuthProfileTest {

	@Test
	public void testSerialization() {
		AuthProfile profile = new AuthProfile(UUID.fromString("af94b13e-a3a3-44db-8126-0e75f7cb4c65"), "thefishlive@hotmail.com");
    	profile.setDisplayName("TheFish97");
    	profile.setUserId("randomuserid");
    	profile.addUserProperty(new Property("twitch_access_token", "randomtwitchaccesstoken"));
    	profile.setAccessToken("randommojangaccesstoken");
    	
		String expected = "{\n  \"displayName\": \"TheFish97\",\n  \"userProperties\": [\n    {\n      \"name\": \"twitch_access_token\",\n      \"value\": \"randomtwitchaccesstoken\"\n    }\n  ],\n  \"accessToken\": \"randommojangaccesstoken\",\n  \"userid\": \"randomuserid\",\n  \"uuid\": \"af94b13ea3a344db81260e75f7cb4c65\",\n  \"username\": \"thefishlive@hotmail.com\"\n}";
    	String json = ProfilesFile.GSON.toJson(profile);
    	
    	System.out.println(StringEscapeUtils.escapeJava(json));
    	System.out.println(StringEscapeUtils.escapeJava(expected));
    	assertEquals(json, expected);
	}

	@Test
	public void testDeserialization() {
		String json = "{\n  \"displayName\": \"TheFish97\",\n  \"userProperties\": [\n    {\n      \"name\": \"twitch_access_token\",\n      \"value\": \"randomtwitchaccesstoken\"\n    }\n  ],\n  \"accessToken\": \"randommojangaccesstoken\",\n  \"userid\": \"randomuserid\",\n  \"uuid\": \"af94b13ea3a344db81260e75f7cb4c65\",\n  \"username\": \"thefishlive@hotmail.com\"\n}";
    	AuthProfile profile = ProfilesFile.GSON.fromJson(json, AuthProfile.class);

		AuthProfile expected = new AuthProfile(UUID.fromString("af94b13e-a3a3-44db-8126-0e75f7cb4c65"), "thefishlive@hotmail.com");
		expected.setDisplayName("TheFish97");
		expected.setUserId("randomuserid");
		expected.addUserProperty(new Property("twitch_access_token", "randomtwitchaccesstoken"));
		expected.setAccessToken("randommojangaccesstoken");
    	
    	System.out.println(profile);
    	System.out.println(expected);
    	assertEquals(profile, expected);
	}
}
