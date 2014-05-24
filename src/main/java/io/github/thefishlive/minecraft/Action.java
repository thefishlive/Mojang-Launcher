package io.github.thefishlive.minecraft;

public enum Action {

    ALLOW,
    DISALLOW;

    public String getId() {
        return name().toLowerCase();
    }

}
