package main.java.pojos;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import static main.java.pojos.SerializedNameConstants.CHILD;

public class LinkedPage extends BasePage {
    @SerializedName(CHILD)
    private BasePage child;

    private LinkedPage(BasePage basePage){
        super(basePage);
    }

    public static LinkedPage factory(JsonObject jsonObject){
        LinkedPage linkedPage = new LinkedPage(BasePage.factory(jsonObject));
        linkedPage.child = BasePage.factory(jsonObject.get(CHILD));
        return linkedPage;
    }

    public BasePage getChild() {
        return child;
    }

    public void setChild(BasePage child) {
        this.child = child;
    }
}
