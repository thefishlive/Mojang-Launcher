package io.github.thefishlive.minecraft.versions;

import com.google.gson.annotations.SerializedName;
import io.github.thefishlive.minecraft.Action;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CompatibilityRule {

    private Action action = Action.ALLOW;
    @SerializedName("rule") private OSRestriction rule;

    public CompatibilityRule() {
    }

    public Action getAppliedAction() {
        if (this.rule != null && !this.rule.isCurrent()) return null;
        return this.action;
    }
    public Action getAction() {
        return action;
    }

    public OSRestriction getRule() {
        return rule;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CompatibilityRule{");
        sb.append("action=").append(action);
        sb.append(", rule=").append(rule);
        sb.append('}');
        return sb.toString();
    }

    public static final class OSRestriction {
        private OperatingSystem name;
        private String version;
        private transient Pattern complied;

        public OSRestriction(OperatingSystem system, String version) {
            this.name = system;
            this.version = version;
        }

        public OperatingSystem getName() {
            return name;
        }

        public String getVersion() {
            return version;
        }

        public Pattern getCompliedVersion() {
            if (this.complied == null) {
                this.complied = Pattern.compile(version);
            }

            return this.complied;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("CompatibilityRule.OSRestriction{");
            sb.append("version='").append(version).append('\'');
            sb.append(", name=").append(name);
            sb.append('}');
            return sb.toString();
        }


        public boolean isCurrent() {
            if ((this.name != null) && (this.name != OperatingSystem.getCurrentPlatform())) return false;

            if (this.version != null) {
                try {
                    Pattern pattern = getCompliedVersion();
                    Matcher matcher = pattern.matcher(System.getProperty("rule.version"));
                    if (!matcher.matches()) {
                        return false;
                    }
                } catch (Throwable ignored) {
                }
            }
            return true;
        }
    }
}
