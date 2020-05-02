package main.java.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import main.java.pages.Page;

import java.util.ArrayList;

import static main.java.driver.Constants.CHROME_DRIVER_NAME;
import static main.java.driver.Constants.CHROME_DRIVER_PATH;
import static main.java.pages.PageUtil.waitForElement;

public class Driver {
    private WebDriver webDriver;
    private DriverOptions driverOptions;
    private ArrayList<Page> pages;

    public Driver(){
        driverOptions = new DriverOptions();
        init(CHROME_DRIVER_NAME, CHROME_DRIVER_PATH);
    }

    public Driver(String driverName, String driverPath) {
        driverOptions = new DriverOptions();
        init(driverName, driverPath);
    }

    private void init(String driverName, String driverPath){
        pages = new ArrayList<>();
        System.setProperty(driverName, driverPath);
        if (CHROME_DRIVER_NAME.equalsIgnoreCase(driverName)){
            webDriver = new ChromeDriver(driverOptions.getChromeOptions());
        }
        else {
            throw new RuntimeException("Unrecognized DriverName given. DriverName="  + driverName);
        }
    }

    public Page createPage(String url){
        Page page = new Page(this, url);
        pages.add(page);
        return page;
    }

    public void focusPage(Page page) {
        webDriver.switchTo().window(page.getHandle());
    }

    public void openPage(Page page){
        focusPage(page);
        if (page.getXpath() != null){
            blockingOpenPage(page);
        }
        else {
            defaultOpenPage(page);
        }
        pages.add(page);
    }

    private void defaultOpenPage(Page page){
        webDriver.navigate().to(page.getUrl());
    }

    private void blockingOpenPage(Page page){
        defaultOpenPage(page);
        waitForElement(webDriver, page.getXpath());
    }

    public boolean closePage(Page pageToClose){
        // Return true if we found the page in our list of main.java.pages and closed it
        // Return false otherwise
        for (int i = 0; i < pages.size(); i++){
            if (pages.get(i).getHandle().equals(pageToClose.getHandle())){
                webDriver.close();
                pages.remove(i);
                return true;
            }
        }
        return false;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public DriverOptions getDriverOptions() {
        return driverOptions;
    }

    public void kill(){
        webDriver.quit();
    }

    public static void main(String[] args){
        Driver driver = new Driver("webdriver.chrome.main.java.driver", "chromedriver");

        driver.getWebDriver().close();
    }
}

