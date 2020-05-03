package main.java.helpers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import main.java.pojos.BasePage;

import java.lang.reflect.Type;

public class BasePageDeserializer implements JsonDeserializer<BasePage> {
    @Override
    public BasePage deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return BasePage.factory(jsonElement);
    }
}
