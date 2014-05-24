package io.github.thefishlive.minecraft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExtractRules {

    private List<String> exclude;

    public ExtractRules() {
    }

    public ExtractRules(String... exclude) {
        this.exclude = Arrays.asList(exclude);
    }

    public ExtractRules(List<String> exclude) {
        this.exclude = exclude;
    }

    public List<String> getExclude() {
        if (this.exclude == null) {
            return new ArrayList<>();
        }

        return this.exclude;
    }

    public void setExcludes(List<String> exclude) {
        if (exclude.isEmpty()) {
            this.exclude = null;
            return;
        }

        this.exclude = exclude;
    }

    public void addExclude(String exclude) {
        if (this.exclude == null) {
            this.exclude = new ArrayList<>();
        }

        this.exclude.add(exclude);
    }

    public boolean isEmpty() {
        return exclude.isEmpty();
    }
}
