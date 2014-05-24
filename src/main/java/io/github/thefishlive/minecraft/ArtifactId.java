package io.github.thefishlive.minecraft;

import lombok.Getter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArtifactId {

    private static final Pattern ARTIFACT_PATTERN = Pattern.compile("([\\w\\.-]+):([\\w\\.-]+):([\\w\\.-]+)");

    private final String name;
    private final String version;
    private final String group;

    public ArtifactId(String id) {
        Matcher matcher = ARTIFACT_PATTERN.matcher(id);

        if (!matcher.matches()) {
            throw new IllegalArgumentException(String.format("Artifact id is in the wrong format, (should be '<group>:<name>:<version>' got '%1$s')", id));
        }

        group = matcher.group(1);
        name = matcher.group(2);
        version = matcher.group(3);
    }

    public String toString() {
        return String.format("%1$s:%2$s:%3$s", group, name, version);
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getGroup() {
        return group;
    }
}
