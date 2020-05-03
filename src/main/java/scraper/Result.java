package main.java.scraper;

import main.java.pages.WebTab;

import java.sql.Timestamp;

public class Result {
    protected WebTab webTab;
    protected int count;
    protected Timestamp timestamp;

    public Result(WebTab webTab, int count){
        this.webTab = webTab;
        this.count = count;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public WebTab getWebTab() {
        return webTab;
    }

    public void setWebTab(WebTab webTab) {
        this.webTab = webTab;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
