package main.java.config;

import com.google.gson.annotations.SerializedName;

import static main.java.config.ConfigConstants.DRIVER_NAME;
import static main.java.config.ConfigConstants.DRIVER_PATH;

public class DriverConfig {
    @SerializedName(DRIVER_NAME)
    protected String name;

    @SerializedName(DRIVER_PATH)
    protected String path;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
