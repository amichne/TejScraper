package main.java.pojos;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import static main.java.helpers.JsonHelper.getString;
import static main.java.helpers.JsonHelper.hasField;
import static main.java.pojos.SerializedNameConstants.*;

public class CountablePage extends BasePage {
    @SerializedName(COUNT_XPATH)
    private String countXpath;

    @SerializedName(COUNT_REGEX)
    private String countRegex;

    @SerializedName(URL_REGEX)
    private String urlRegex;

    private CountablePage(BasePage basePage){
        super(basePage);
    }

    public static CountablePage factory(JsonObject jsonObject){
        CountablePage countablePage = new CountablePage(BasePage.factory(jsonObject));
        if (hasField(jsonObject, COUNT_XPATH)) {
            countablePage.countXpath = getString(jsonObject, COUNT_XPATH);
        }
        if (hasField(jsonObject, COUNT_REGEX)) {
            countablePage.countRegex = getString(jsonObject, COUNT_REGEX);
        }
        else {
            throw new RuntimeException(COUNT_REGEX + " field is required. Received element:\n" +
                    jsonObject.getAsString());
        }
        if (hasField(jsonObject, URL_REGEX)) {
            countablePage.urlRegex = getString(jsonObject, URL_REGEX);
        }
        return countablePage;
    }

    public String getCountXpath() {
        return countXpath;
    }

    public void setCountXpath(String countXpath) {
        this.countXpath = countXpath;
    }

    public String getCountRegex() {
        return countRegex;
    }

    public void setCountRegex(String countRegex) {
        this.countRegex = countRegex;
    }

    public String getUrlRegex() {
        return urlRegex;
    }

    public void setUrlRegex(String urlRegex) {
        this.urlRegex = urlRegex;
    }
}
