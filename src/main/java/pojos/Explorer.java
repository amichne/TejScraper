package main.java.pojos;

import com.google.gson.annotations.SerializedName;

public class Explorer {
    private SingleBranchPage root;

    public SingleBranchPage getRoot() {
        return root;
    }

    public void setRoot(SingleBranchPage root) {
        this.root = root;
    }
}
