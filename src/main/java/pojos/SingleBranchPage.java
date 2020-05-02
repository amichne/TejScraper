package main.java.pojos;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import static main.java.pojos.SerializedNameConstants.*;

public class SingleBranchPage extends BasePage {
    @SerializedName(CHILD)
    private BasePage child;

    private SingleBranchPage(BasePage basePage){
        super(basePage);
    }

    public static SingleBranchPage factory(JsonObject jsonObject){
        SingleBranchPage singleBranchPage = new SingleBranchPage(BasePage.factory(jsonObject));
        singleBranchPage.child = BasePage.factory(jsonObject.get(CHILD));
        return singleBranchPage;
    }

    public BasePage getChild() {
        return child;
    }

    public void setChild(BasePage child) {
        this.child = child;
    }
}
