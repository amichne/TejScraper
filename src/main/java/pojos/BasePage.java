package main.java.pojos;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import main.java.scraper.Explorer;

import static main.java.helpers.JsonHelper.getString;
import static main.java.helpers.JsonHelper.hasField;
import static main.java.pojos.SerializedNameConstants.*;

public class BasePage {
    @SerializedName(URL)
    private String url;

    @SerializedName(HREF_XPATH)
    private String hrefXpath;

    @SerializedName(LI_HREF_XPATH)
    private String liHrefPath;

    public BasePage(){}

    public BasePage(BasePage basePage){
        url = basePage.url;
        hrefXpath = basePage.hrefXpath;
    }

    protected static BasePage factory(JsonObject jsonObject){
        BasePage basePage = new BasePage();
        if (hasField(jsonObject, URL)) {
            basePage.url = getString(jsonObject, URL);
        }
        if (hasField(jsonObject, HREF_XPATH)) {
            basePage.hrefXpath = getString(jsonObject, HREF_XPATH);
        }
        return basePage;
    }

    public static <T extends BasePage> T factory(JsonElement jsonElement){
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        if (hasField(jsonObject, CHILD)){
            return (T) LinkedPage.factory(jsonObject);
        }
        if (hasField(jsonObject, COUNT_XPATH)){
            return (T) CountablePage.factory(jsonObject);
        }
        return (T) Explorer.defaultCountable;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHrefXpath() {
        return hrefXpath;
    }

    public void setHrefXpath(String hrefXpath) {
        this.hrefXpath = hrefXpath;
    }

    public String getLiHrefPath() {
        return liHrefPath;
    }

    public void setLiHrefPath(String liHrefPath) {
        this.liHrefPath = liHrefPath;
    }
}
