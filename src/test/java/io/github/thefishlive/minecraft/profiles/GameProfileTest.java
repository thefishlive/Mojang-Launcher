package io.github.thefishlive.minecraft.profiles;

import static org.junit.Assert.*;

import io.github.thefishlive.minecraft.profiles.GameProfile;
import io.github.thefishlive.minecraft.profiles.JavaArgs;
import io.github.thefishlive.minecraft.profiles.ProfilesFile;
import io.github.thefishlive.minecraft.profiles.VisibilityRule;

import java.util.UUID;

import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.Test;

public class GameProfileTest {

	@Test
	public void testSerialization() {
    	GameProfile profile = new GameProfile("TheFish97");
    	profile.setLastVersionId("1.7.9");
    	profile.setLauncherVisibility(VisibilityRule.KEEP_LAUNCHER);
    	profile.setJavaArgs(new JavaArgs().setInitialMemory("512M").setMaxMemory("1024M").setPermGen("128M"));
    	
		String expected = "{\n  \"name\": \"TheFish97\",\n  \"lastVersionId\": \"1.7.9\",\n  \"allowedReleaseTypes\": [],\n  \"launcherVisibilityOnGameClose\": \"keep the launcher open\",\n  \"javaArgs\": \"-Xms512M -Xmx1024M -XX:MaxPermSize\\u003d128M\",\n  \"useHopperCrashService\": false\n}";
    	String json = ProfilesFile.GSON.toJson(profile);
    	
    	System.out.println(StringEscapeUtils.escapeJava(json));
    	System.out.println(StringEscapeUtils.escapeJava(expected));
    	assertEquals(json, expected);
	}

	@Test
	public void testDeserialization() {
		String json = "{\"name\":\"TheFish97\",\"lastVersionId\":\"1.7.9\",\n  \"allowedReleaseTypes\": [],\"launcherVisibilityOnGameClose\":\"keep the launcher open\",\"javaArgs\":\"-Xms512M -Xmx1024M -XX:MaxPermSize=128M\"}";
    	GameProfile profile = ProfilesFile.GSON.fromJson(json, GameProfile.class);
    	
    	GameProfile expected = new GameProfile("TheFish97");
    	expected.setLastVersionId("1.7.9");
    	expected.setLauncherVisibility(VisibilityRule.KEEP_LAUNCHER);
    	expected.setJavaArgs(new JavaArgs().setInitialMemory("512M").setMaxMemory("1024M").setPermGen("128M"));
    	
    	System.out.println(profile);
    	System.out.println(expected);
    	assertEquals(profile, expected);
	}
}
