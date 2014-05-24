package io.github.thefishlive.minecraft.profiles;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Helper class for dealing with java command line arguments. Provides a simple
 * interface for dealing with the basic jvm arguements.
 * 
 * @author James
 * @version 1.0.0
 */
public class JavaArgs {

	private static final char ARG_SPLIT = ' ';
	private static final char KEY_VALUE_SPLIT = '=';
	private static final char ARG_START = '-';
	private static final char QUOTE = '\"';

	private static class JavaArgsConstants {
		public static final String MAX_MEMORY = "Xmx";
		public static final String INITIAL_MEMORY = "Xms";
		public static final String PERM_GEN = "XX:MaxPermSize";
		public static final String SYSTEM_PROPERTY = "D";
	}
	
	private Map<String, String> args;
	private int hashcode;
	
	/**
	 * Constructs a blank instance.
	 */
	public JavaArgs() {
		args = new LinkedHashMap<>();
		recalculateHash();
	}
	
	/**
	 * Constructs a new instance populated by parsing the command line
	 * arguments provided.
	 * 
	 * @param args the command line arguements to parse
	 */
	public JavaArgs(String args) {
		this.args = split(args);
		recalculateHash();
	}
	
	private Map<String, String> split(String split) {
		Map<String, String> parts = new LinkedHashMap<>();
		boolean quoted = false;
		int part = 0;
		StringBuilder[] current = new StringBuilder[] { new StringBuilder(), new StringBuilder() };
		
		for (char character : split.toCharArray()) {
			if (character == QUOTE) {
				quoted = !quoted;
				continue;
			} else if (!quoted) { // Ignore special characters in quotes
				if (character == ARG_START) {
					continue;
				} else if (character == KEY_VALUE_SPLIT) {
					part = 1;
					continue;
				} else if (character == ARG_SPLIT) {
					part = 0;
					parts.put(current[0].toString(), current[1].toString());
					current =  new StringBuilder[] { new StringBuilder(), new StringBuilder() };
					continue;
				}
			}
			current[part].append(character);
		}
		parts.put(current[0].toString(), current[1].toString()); // Put last parts in
		
		return parts;
	}

	/**
	 * Convert this to the external java command form.
	 * 
	 * @return the arguements for a java command
	 */
	public String toCommandForm() {
		StringBuilder builder = new StringBuilder();
		
		for (Map.Entry<String, String> entry : args.entrySet()) {
			builder.append(new StringBuilder().append(ARG_START).append(entry.getKey()).append(entry.getValue().length() != 0 ? KEY_VALUE_SPLIT + formatValue(entry.getValue()) + ARG_SPLIT : ARG_SPLIT));
		}
		
		return builder.toString().trim();
	}

	private String formatValue(String value) {
		return value.contains("" + ARG_SPLIT) ? new StringBuilder(value.length() + 2).append(QUOTE).append(value).append(QUOTE).toString() : value;
	}
	
	/**
	 * Add a arguement to the command.
	 * 
	 * @param arg the main argument.
	 * @param value the value to set this to, if null the value will be ignored
	 * 			when creating the command
	 * @return the current instance
	 */
	public JavaArgs addJavaArg(String arg, String value) {
		this.args.put(arg, value);
		recalculateHash();
		return this;
	}
	
	/**
	 * Sets the {@code -Xmx} value for the command
	 * 
	 * @param memory the memory to use
	 * @return the current instance
	 */
	public JavaArgs setMaxMemory(String memory) {
		return addJavaArg(JavaArgsConstants.MAX_MEMORY + memory, "");
	}

	/**
	 * Sets the {@code -Xms} value for the command.
	 * 
	 * @param memory the memory to use
	 * @return the current instance
	 */
	public JavaArgs setInitialMemory(String memory) {
		return addJavaArg(JavaArgsConstants.INITIAL_MEMORY + memory, "");
	}

	/**
	 * Sets the {@code -XX:PermGenSize} value for the command.
	 * 
	 * @param memory the memory to use
	 * @return the current instance
	 */
	public JavaArgs setPermGen(String memory) {
		return addJavaArg(JavaArgsConstants.PERM_GEN, memory);
	}
	
	/**
	 * Adds a system property to the command, equivalent of {@code -Dproperty=value}.
	 * 
	 * @param property the system property
     * @param value the new value of this property
	 * @return the current instance
	 */
	public JavaArgs addSysProperty(String property, String value) {
		return addJavaArg(JavaArgsConstants.SYSTEM_PROPERTY + property, value);
	}
	
	@Override
	public String toString() {
		return toCommandForm();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof JavaArgs)) return false;
		JavaArgs other = (JavaArgs) obj;
		return other.toCommandForm().equals(toCommandForm());
	}

	@Override
	public int hashCode() {
		return hashcode;
	}
	
	private void recalculateHash() {
		hashcode = this.args.hashCode();
	}

}
