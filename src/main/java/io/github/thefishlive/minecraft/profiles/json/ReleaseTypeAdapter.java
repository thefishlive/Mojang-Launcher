package io.github.thefishlive.minecraft.profiles.json;

import io.github.thefishlive.minecraft.profiles.GameProfile.ReleaseType;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class ReleaseTypeAdapter implements JsonDeserializer<ReleaseType>, JsonSerializer<ReleaseType> {

	public JsonElement serialize(ReleaseType src, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive(src.getId());
	}

	public ReleaseType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		return ReleaseType.fromId(json.getAsString());
	}

}