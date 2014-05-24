package io.github.thefishlive.minecraft;

import java.util.HashMap;
import java.util.Map;

public enum ReleaseType {
    SNAPSHOT("snapshot"),
    RELEASE("release"),
    BETA("old_beta"),
    ALPHA("old_alpha");

    private static final Map<String, ReleaseType> TYPES = new HashMap<>();
    private final String id;
    static {
        for (ReleaseType type : values()) {
            TYPES.put(type.getId(), type);
        }
    }

    public static ReleaseType fromId(String id) {
        return TYPES.get(id);
    }

    private ReleaseType(final String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

}
