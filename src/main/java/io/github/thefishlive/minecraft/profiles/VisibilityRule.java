package io.github.thefishlive.minecraft.profiles;

import java.util.HashMap;
import java.util.Map;

public enum VisibilityRule {
	KEEP_LAUNCHER("keep the launcher open"),
	HIDE_LAUNCHER("hide launcher and re-open when game closes"),
	CLOSE_LAUNCHER("close launcher when game starts");
	
	private static final Map<String, VisibilityRule> RULES = new HashMap<>();
	private final String id;
	
	static {
		for (VisibilityRule rule : values()) {
			RULES.put(rule.getId(), rule);
		}
	}
	
	public String toString() {
		return id;
	}
	
	public static VisibilityRule fromId(String id) {
		return RULES.get(id);
	}
	
	private VisibilityRule(final String id) {
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}
}