package main.java.helpers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import main.java.pojos.BasePage;

import java.util.ArrayList;

public class JsonHelper {

    public static boolean hasField(JsonObject jsonObject, String key){
        return jsonObject.has(key);
    }

    public static JsonArray getArray(JsonObject jsonObject, String key){
        return jsonObject.get(key).getAsJsonArray();
    }

    public static String getString(JsonObject jsonObject, String key){
        return jsonObject.get(key).getAsString();
    }

    public static int getInt(JsonObject jsonObject, String key){
        return jsonObject.get(key).getAsInt();
    }
}
