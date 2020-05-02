package main.java.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import main.java.pojos.BasePage;

public class GsonHelper {
    public static Gson gson;

    public static Gson start(){
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        builder.registerTypeAdapter(BasePage.class, new BasePageDeserializer());
        GsonHelper.gson = builder.create();
        return GsonHelper.gson;
    }
}
