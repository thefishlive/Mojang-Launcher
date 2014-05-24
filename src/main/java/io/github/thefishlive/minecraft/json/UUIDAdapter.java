package io.github.thefishlive.minecraft.json;

import java.lang.reflect.Type;
import java.util.UUID;
import java.util.regex.Pattern;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class UUIDAdapter implements JsonSerializer<UUID>, JsonDeserializer<UUID> {

	private static final Pattern UUID_REPLACE = Pattern.compile("-", Pattern.LITERAL);
	private static final Pattern UUID_PARSE = Pattern.compile("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})");
	private static final Pattern UUID_PARSE_SEPARATED = Pattern.compile("(\\w{8})-(\\w{4})-(\\w{4})-(\\w{4})-(\\w{12})");
	
	public JsonElement serialize(UUID src, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive(format(src));
	}

	public UUID deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		if (UUID_PARSE_SEPARATED.matcher(json.getAsString()).matches()) {
			return UUID.fromString(json.getAsString());
		}
		
		return parseUuid(json.getAsString());
	}
	
	public static String format(UUID src) {
		return UUID_REPLACE.matcher(src.toString()).replaceAll("");
	}
	
	public static UUID parseUuid(String src) {
		return UUID.fromString(UUID_PARSE.matcher(src).replaceFirst("$1-$2-$3-$4-$5"));
	}

}
