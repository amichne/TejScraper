package main.java.pojos;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import main.java.helpers.JsonHelper;

import static main.java.helpers.JsonHelper.getString;
import static main.java.helpers.JsonHelper.hasField;
import static main.java.pojos.SerializedNameConstants.*;

public class BasePage {
    @SerializedName(URL)
    private String url;

    @SerializedName(HREF_XPATH)
    private String hrefXpath;

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
            return (T) SingleBranchPage.factory(jsonObject);
        }
        if (hasField(jsonObject, COUNT_XPATH)){
            return (T) CountablePage.factory(jsonObject);
        }
        throw new RuntimeException("No valid schema determined for element:\n" + jsonElement.toString());
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
}
