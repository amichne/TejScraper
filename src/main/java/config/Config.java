package main.java.config;

import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import main.java.helpers.GsonHelper;
import main.java.pojos.CountablePage;
import main.java.pojos.Explorer;

import java.io.FileNotFoundException;
import java.io.FileReader;

import static main.java.config.ConfigConstants.*;
import static main.java.helpers.GsonHelper.gson;

public class Config {
    @SerializedName(DRIVER)
    protected DriverConfig driverConfig;

    @SerializedName(EXPLORER)
    protected Explorer explorer;

    @SerializedName(COUNTABLE_PAGE)
    protected CountablePage countablePage;

    public static Config load(String filepath){
        GsonHelper.start();
        JsonReader reader;
        try {
            reader = new JsonReader(new FileReader(filepath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("No file found at path: " + filepath);
        }
        return gson.fromJson(reader, Config.class);
    }

    public DriverConfig getDriverConfig() {
        return driverConfig;
    }

    public void setDriverConfig(DriverConfig driverConfig) {
        this.driverConfig = driverConfig;
    }

    public Explorer getExplorer() {
        return explorer;
    }

    public void setExplorer(Explorer explorer) {
        this.explorer = explorer;
    }

    public CountablePage getCountablePage() {
        return countablePage;
    }

    public void setCountablePage(CountablePage countablePage) {
        this.countablePage = countablePage;
    }
}
