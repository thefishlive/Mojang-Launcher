package io.github.thefishlive.minecraft.versions;

import io.github.thefishlive.minecraft.ReleaseType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Version {

    private final String id;
    private Date time;
    private Date releaseTime;
    private ReleaseType type;
    private String minecraftArguments;
    private List<Library> libraries;
    private String mainClass;
    private int minimumLauncherVersion;
    private String incompatibilityReason;
    private String assets;
    private List<CompatibilityRule> compatibilityRules;

    public Version(String id, Date time, Date releaseTime, ReleaseType type, String minecraftArguments, List<Library> libraries, String mainClass, int minimumLauncherVersion, String incompatibilityReason, String assets, List<CompatibilityRule> compatibilityRules) {
        this.id = id;
        this.time = time;
        this.releaseTime = releaseTime;
        this.type = type;
        this.minecraftArguments = minecraftArguments;
        this.libraries = libraries;
        this.mainClass = mainClass;
        this.libraries = libraries;
        this.minimumLauncherVersion = minimumLauncherVersion;
        this.incompatibilityReason = incompatibilityReason;
        this.assets = assets;
        this.compatibilityRules = compatibilityRules;
    }

    public String getId() {
        return id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public ReleaseType getType() {
        return type;
    }

    public void setType(ReleaseType type) {
        this.type = type;
    }

    public String getMinecraftArguments() {
        return minecraftArguments;
    }

    public void setMinecraftArguments(String minecraftArguments) {
        this.minecraftArguments = minecraftArguments;
    }

    public List<Library> getLibraries() {
        if (this.libraries == null) {
            return new ArrayList<>();
        }

        return libraries;
    }

    public void setLibraries(List<Library> libraries) {
        if (libraries.isEmpty()) {
            this.libraries = null;
            return;
        }

        this.libraries = libraries;
    }

    public void addLibrary(Library library) {
        if (this.libraries == null) {
            this.libraries = new ArrayList<>();
        }

        this.libraries.add(library);
    }

    public String getMainClass() {
        return mainClass;
    }

    public void setMainClass(String mainClass) {
        this.mainClass = mainClass;
    }

    public int getMinimumLauncherVersion() {
        return minimumLauncherVersion;
    }

    public void setMinimumLauncherVersion(int minimumLauncherVersion) {
        this.minimumLauncherVersion = minimumLauncherVersion;
    }

    public void setMinimumLauncherVersion(int minimumLauncherVersion, String incompatibilityReason) {
        this.minimumLauncherVersion = minimumLauncherVersion;
        this.incompatibilityReason = incompatibilityReason;
    }

    public String getIncompatibilityReason() {
        return incompatibilityReason;
    }

    public void setIncompatibilityReason(String incompatibilityReason) {
        this.incompatibilityReason = incompatibilityReason;
    }

    public String getAssets() {
        return assets;
    }

    public void setAssets(String assets) {
        this.assets = assets;
    }

    public List<CompatibilityRule> getCompatibilityRules() {
        if (this.compatibilityRules == null) {
            return new ArrayList<>();
        }

        return compatibilityRules;
    }

    public void setCompatibilityRules(List<CompatibilityRule> compatibilityRules) {
        if (compatibilityRules.isEmpty()) {
            this.compatibilityRules = null;
            return;
        }

        this.compatibilityRules = compatibilityRules;
    }

    public void addCompatibilityRule(CompatibilityRule compatibilityRule) {
        if (this.compatibilityRules == null) {
            this.compatibilityRules = new ArrayList<>();
        }

        this.compatibilityRules.add(compatibilityRule);
    }
}
