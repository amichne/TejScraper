package main.java.pages;

import main.java.driver.Driver;
import org.openqa.selenium.JavascriptExecutor;

import java.util.ArrayList;

import static main.java.driver.DriverConstants.OPEN_WINDOW_CMD;

public class WebTab {
    protected Driver driver;
    protected String handle;
    protected String url;
    protected boolean alive;
    // If not null, when loading the page this is the xPath is will block on

    public WebTab(Driver driver, String url){
        init(driver, url);
    }

    private void init(Driver driver, String url){
        this.driver = driver;
        webTabFactory(url);
    }

    private void webTabFactory(String url){
        // Used as a generic initializer for all Page objects, sets the common fields for all constructor patterns
        // This saves us from writing code twice, which we should NEVER DO in OOP
        this.url = url;
        // Open a new window
        ((JavascriptExecutor) driver.getWebDriver()).executeScript(OPEN_WINDOW_CMD);
        // Get the List (actually a set) of all open window handles (which are guaranteed to be unique)
        ArrayList<String> windows = new ArrayList<>(driver.getWebDriver().getWindowHandles());
        // Setting this instances handle, switching to the new (still blank) tab, and navigating to the URL specified
        handle = windows.get(windows.size() - 1);
        driver.focusWebTab(this);
        alive = true;
    }

    public WebTab openUrl(String url, boolean inPlace){
        if (inPlace){
            return openUrl(url);
        }
        else {
            return driver.createWebTab(url);
        }
    }

    public WebTab openUrl(String url){
        this.url = url;
        driver.navigateWebTab(this);
        return this;
    }

    public boolean kill(){
        alive = false;
        return driver.closeWebTab(this);
    }

    public boolean isAlive() {
        return alive;
    }

    public Driver getDriver() {
        return driver;
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
