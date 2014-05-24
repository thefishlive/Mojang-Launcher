package io.github.thefishlive.minecraft.profiles;

import io.github.thefishlive.minecraft.ReleaseType;
import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.UUID;

import static org.junit.Assert.*;

public class ProfilesFileTest {

    private static File profilesFile;
    private static ProfilesFile expected;
    private static String expectedJson;

    @BeforeClass
    public static void setup() {
        URL url = ProfilesFileTest.class.getResource("/launcher_profiles.json");
        profilesFile = new File(url.getFile());

        expected = new ProfilesFile("24bde6dd-cefc-4cff-8c97-44c46e808fc2");

        AuthProfile auth = new AuthProfile(UUID.fromString("75b7fafe-7e24-4353-9e0e-63fd26caab03"), "username@example.com");
        auth.setDisplayName("DisplayName");
        auth.addUserProperty(new AuthProfile.Property("twitch_access_token", "1fc2942a11f5f37414de5192d30c5a14"));
        auth.setAccessToken("abbaaa7cd925256f667b15d9d1c6745d");
        auth.setUserId("8ad76b690b964550908ad6ac1710808a");
        expected.addProfile(auth);

        GameProfile profile = new GameProfile("McBadgerPack-1.7.2-b");
        profile.setGameDir(new File("C:\\Users\\James\\AppData\\Roaming\\.mcbadgerpack-1.7.2-b"));
        profile.setLastVersionId("McBadgerPack-1.7.2-b");
        profile.setJavaArgs(new JavaArgs().setMaxMemory("1G").addSysProperty("fml.ignoreInvalidMinecraftCertificates", "true").addSysProperty("fml.ignorePatchDiscrepancies", "true"));
        profile.setPlayerUUID(auth.getUuid());
        profile.setLauncherVisibility(VisibilityRule.KEEP_LAUNCHER);
        expected.addProfile(profile);

        profile = new GameProfile("Dev");
        profile.setGameDir(new File("C:\\Users\\James\\AppData\\Roaming\\.mcbadgerpack-1.7.2-b"));
        profile.setLastVersionId("14w17a");
        profile.setJavaArgs(new JavaArgs().setMaxMemory("1G").addSysProperty("fml.ignoreInvalidMinecraftCertificates", "true").addSysProperty("fml.ignorePatchDiscrepancies", "true"));
        profile.addReleaseType(ReleaseType.SNAPSHOT);
        profile.addReleaseType(ReleaseType.RELEASE);
        profile.setPlayerUUID(auth.getUuid());
        profile.setLauncherVisibility(VisibilityRule.KEEP_LAUNCHER);
        expected.addProfile(profile);

        expected.setSelectedProfile(profile);
        
        expectedJson = "{\n  \"profiles\": {\n    \"McBadgerPack-1.7.2-b\": {\n      \"name\": \"McBadgerPack-1.7.2-b\",\n      \"lastVersionId\": \"McBadgerPack-1.7.2-b\",\n      \"allowedReleaseTypes\": [],\n      \"playerUUID\": \"75b7fafe7e2443539e0e63fd26caab03\",\n      \"launcherVisibilityOnGameClose\": \"keep the launcher open\",\n      \"javaArgs\": \"-Xmx1G -Dfml.ignoreInvalidMinecraftCertificates\\u003dtrue -Dfml.ignorePatchDiscrepancies\\u003dtrue\",\n      \"gameDir\": \"C:\\\\Users\\\\James\\\\AppData\\\\Roaming\\\\.mcbadgerpack-1.7.2-b\",\n      \"useHopperCrashService\": false\n    },\n    \"Dev\": {\n      \"name\": \"Dev\",\n      \"lastVersionId\": \"14w17a\",\n      \"allowedReleaseTypes\": [\n        \"snapshot\",\n        \"release\"\n      ],\n      \"playerUUID\": \"75b7fafe7e2443539e0e63fd26caab03\",\n      \"launcherVisibilityOnGameClose\": \"keep the launcher open\",\n      \"javaArgs\": \"-Xmx1G -Dfml.ignoreInvalidMinecraftCertificates\\u003dtrue -Dfml.ignorePatchDiscrepancies\\u003dtrue\",\n      \"gameDir\": \"C:\\\\Users\\\\James\\\\AppData\\\\Roaming\\\\.mcbadgerpack-1.7.2-b\",\n      \"useHopperCrashService\": false\n    }\n  },\n  \"authenticationDatabase\": {\n    \"75b7fafe-7e24-4353-9e0e-63fd26caab03\": {\n      \"displayName\": \"DisplayName\",\n      \"userProperties\": [\n        {\n          \"name\": \"twitch_access_token\",\n          \"value\": \"1fc2942a11f5f37414de5192d30c5a14\"\n        }\n      ],\n      \"accessToken\": \"abbaaa7cd925256f667b15d9d1c6745d\",\n      \"userid\": \"8ad76b690b964550908ad6ac1710808a\",\n      \"uuid\": \"75b7fafe7e2443539e0e63fd26caab03\",\n      \"username\": \"username@example.com\"\n    }\n  },\n  \"selectedProfile\": \"Dev\",\n  \"clientToken\": \"24bde6dd-cefc-4cff-8c97-44c46e808fc2\"\n}";
    }

    @Test
    public void testDeserialization() throws FileNotFoundException {
        ProfilesFile profile = ProfilesFile.GSON.fromJson(new FileReader(profilesFile), ProfilesFile.class);

        System.out.println(profile.toString());
        System.out.println(expected.toString());

        assertEquals(profile, expected);
    }

    @Test
    public void testSerialization() {
        String json = ProfilesFile.GSON.toJson(expected);

        System.out.println(StringEscapeUtils.escapeJava(json));
        System.out.println(StringEscapeUtils.escapeJava(expectedJson));

        assertEquals(expectedJson, json);
    }
}
