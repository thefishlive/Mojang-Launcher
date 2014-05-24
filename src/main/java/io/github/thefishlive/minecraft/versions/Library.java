package io.github.thefishlive.minecraft.versions;

import io.github.thefishlive.minecraft.ArtifactId;
import io.github.thefishlive.minecraft.ExtractRules;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {

    private final ArtifactId name;
    private List<CompatibilityRule> rules;
    private Map<OperatingSystem, String> natives;
    private ExtractRules extract;
    private URL url;

    public Library(ArtifactId name, List<CompatibilityRule> rules, Map<OperatingSystem, String> natives, ExtractRules extract, URL url) {
        this.name = name;
        this.rules = rules;
        this.natives = natives;
        this.extract = extract;
        this.url = url;
    }

    public ArtifactId getName() {
        return name;
    }

    public List<CompatibilityRule> getRules() {
        if (rules == null) {
            return new ArrayList<>();
        }

        return rules;
    }

    public void setRules(List<CompatibilityRule> rules) {
        if (rules.isEmpty()) {
            this.rules = null;
            return;
        }

        this.rules = rules;
    }

    public Map<OperatingSystem, String> getNatives() {
        if (natives == null) {
            return new HashMap<>();
        }

        return natives;
    }

    public void setNatives(Map<OperatingSystem, String> natives) {
        if (natives.isEmpty()) {
            this.natives = null;
            return;
        }

        this.natives = natives;
    }

    public ExtractRules getExtract() {
        if (extract == null) {
            return new ExtractRules();
        }

        return extract;
    }

    public void setExtract(ExtractRules extract) {
        if (extract.isEmpty()) {
            this.extract = null;
            return;
        }

        this.extract = extract;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }
}
