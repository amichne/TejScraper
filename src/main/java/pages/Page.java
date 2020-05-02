package main.java.pages;

import main.java.driver.Driver;
import org.openqa.selenium.JavascriptExecutor;

import java.util.ArrayList;

import static main.java.driver.Constants.OPEN_WINDOW_CMD;
import static main.java.pages.PageUtil.getLink;
import static main.java.pages.PageUtil.waitForElement;

public class Page {
    Driver driver;
    String handle;
    String url;
    // If not null, when loading the page this is the xPath is will block on
    String xpath;

    public Page(Driver driver, String url){
        init(driver, url);
    }

    public Page(Driver driver, String url, String xpath){
        init(driver, url);
        this.xpath = xpath;
        driver.openPage(this);
    }

    private void init(Driver driver, String url){
        this.driver = driver;
        pageFactory(url);
    }

    private void pageFactory(String url){
        // Used as a generic initializer for all Page objects, sets the common fields for all constructor patterns
        // This saves us from writing code twice, which we should NEVER DO in OOP
        this.url = url;
        // Open a new window
        ((JavascriptExecutor) driver.getWebDriver()).executeScript(OPEN_WINDOW_CMD);
        // Get the List (actually a set) of all open window handles (which are guaranteed to be unique)
        ArrayList<String> windows = new ArrayList<>(driver.getWebDriver().getWindowHandles());
        // Setting this instances handle, switching to the new (still blank) tab, and navigating to the URL specified
        handle = windows.get(windows.size() - 1);
        driver.openPage(this);
    }

    public Page openHref(String xpath){
        // TODO HERE
        waitForElement(driver.getWebDriver(), xpath);
        return driver.createPage(getLink(driver.getWebDriver(), xpath));
    }

    public boolean destroy(){
        return driver.closePage(this);
    }

    public String getXpath() {
        return xpath;
    }

    public String getHandle() {
        return handle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
