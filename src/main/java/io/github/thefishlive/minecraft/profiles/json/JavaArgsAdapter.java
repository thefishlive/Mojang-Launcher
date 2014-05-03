package io.github.thefishlive.minecraft.profiles.json;

import io.github.thefishlive.minecraft.profiles.JavaArgs;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class JavaArgsAdapter implements JsonDeserializer<JavaArgs>, JsonSerializer<JavaArgs> {

	public JsonElement serialize(JavaArgs src, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive(src.toCommandForm());
	}

	public JavaArgs deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		return new JavaArgs(json.getAsString());
	}

}
