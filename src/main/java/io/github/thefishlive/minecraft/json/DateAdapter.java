package io.github.thefishlive.minecraft.json;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateAdapter implements JsonSerializer<Date>, JsonDeserializer<Date> {
    private final DateFormat enUsFormat = DateFormat.getDateTimeInstance(2, 2, Locale.US);
    private final DateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return deserializeToDate(json.getAsString());
    }

    @Override
    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(serializeToString(src));
    }

    public Date deserializeToDate(String string) {
        synchronized (this.enUsFormat) {
            try {
                return this.enUsFormat.parse(string);
            }
            catch (ParseException ex) {
                try {
                    return this.iso8601Format.parse(string);
                }
                catch (ParseException e1) {
                    try {
                        String cleaned = string.replace("Z", "+00:00");
                        cleaned = cleaned.substring(0, 22) + cleaned.substring(23);
                        return this.iso8601Format.parse(cleaned);
                    } catch (Exception e) {
                        throw new JsonSyntaxException("Invalid date: " + string, e); }
                }
            }
        }
    }
    public String serializeToString(Date date) {
        synchronized (this.enUsFormat) {
            String result = this.iso8601Format.format(date);
            return result.substring(0, 22) + ":" + result.substring(22);
        }
    }

}
