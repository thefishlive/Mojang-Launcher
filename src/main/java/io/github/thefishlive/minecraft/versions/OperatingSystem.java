package io.github.thefishlive.minecraft.versions;

public enum OperatingSystem {

    LINUX("linux"),
    WINDOWS("windows"),
    MAC("osx"),
    SOLARIS("solaris", false),
    UNKNOWN("unknown", false),
    ;

    private static OperatingSystem current = null;

    private String id;
    private boolean supported;

    private OperatingSystem(String id, boolean supported) {
        this.id = id;
        this.supported = supported;
    }

    private OperatingSystem(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public boolean isSupported() {
        return supported;
    }

    public static OperatingSystem getCurrentPlatform() {
        if (current == null) {
            String osName = System.getProperty("os.name").toLowerCase();

            if (osName.contains("win")) {
                current = WINDOWS;
            } else if (osName.contains("mac")) {
                current = MAC;
            } else if (osName.contains("nix") || osName.contains("nux") || osName.contains("aix")) {
                current = LINUX;
            } else if (osName.contains("sunos")) {
                current = SOLARIS;
            } else {
                current = UNKNOWN;
            }
        }

        return current;
    }
}
