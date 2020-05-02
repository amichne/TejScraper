package pages;

import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.UUID;

import static driver.Constants.OPEN_WINDOW_CMD;
import static pages.AttributeConstants.LINK_ATTR;

public class Page {
    Driver driver;
    String handle;
    String url;
    // If not null, when loading the page this is the xPath is will block on
    String xpath;

    public Page(Driver driver, String url){
        init(driver);
        pageFactory(url);
    }

    public Page(Driver driver, String url, String xpath){
        // This constructor will load a new page with the URL linked in the passed xPath
        init(driver);
        pageFactory(url);
        // TODO: Configurable
        driver.openPage(this);
        String linkedUrl = driver.getWebDriver().findElement(By.xpath(xpath)).getAttribute(LINK_ATTR);
        ((JavascriptExecutor) driver.getWebDriver()).executeScript(OPEN_WINDOW_CMD);

    }

    private void init(Driver driver){
        this.driver = driver;
    }

    private void pageFactory(String url){
        // Used as a generic initializer for all Page objects, sets the common fields for all constructor patterns
        // This saves us from writing code twice, which we should NEVER DO in OOP
        this.url = url;
        // Open a new window
        ((JavascriptExecutor) driver).executeScript(OPEN_WINDOW_CMD);
        // Get the List (actually a set) of all open window handles (which are guaranteed to be unique)
        ArrayList<String> windows = new ArrayList<>(driver.getWebDriver().getWindowHandles());
        // Setting this instances handle, switching to the new (still blank) tab, and navigating to the URL specified
        handle = windows.get(windows.size() - 1);
        driver.focusPage(this);
    }

    public void destroy(){

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
