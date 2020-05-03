package main.java.driver;

import main.java.config.DriverConfig;
import main.java.pages.WebTab;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

import static main.java.driver.DriverConstants.CHROME_DRIVER_NAME;
import static main.java.driver.DriverConstants.CHROME_DRIVER_PATH;

public class Driver {
    private WebDriver webDriver;
    private DriverOptions driverOptions;
    private ArrayList<WebTab> webTabs;

    public Driver(){
        driverOptions = new DriverOptions();
        init(CHROME_DRIVER_NAME, CHROME_DRIVER_PATH);
    }

    public Driver(DriverConfig driverConfig) {
        driverOptions = new DriverOptions();
        init(driverConfig.getName(), driverConfig.getPath());
    }

    private void init(String driverName, String driverPath){
        webTabs = new ArrayList<>();
        System.setProperty(driverName, driverPath);
        if (CHROME_DRIVER_NAME.equalsIgnoreCase(driverName)){
            webDriver = new ChromeDriver(driverOptions.getChromeOptions());
        }
        else {
            throw new RuntimeException("Unrecognized DriverName given. DriverName="  + driverName);
        }
    }

    public WebTab createWebTab(String url){
        WebTab webTab = new WebTab(this, url);
        webTabs.add(webTab);
        focusWebTab(webTab);
        return webTab;
    }

    public void navigateWebTab(WebTab webTab){
        focusWebTab(webTab);
        webDriver.navigate().to(webTab.getUrl());
    }

    public void focusWebTab(WebTab webTab){
        // Focuses the WebTab, and if it has a defined xPath it waits for that element to be loaded
        // TODO: Clean up the webTabs list properly
        webDriver.switchTo().window(webTab.getHandle());
        webTabs.add(webTab);
    }

    public boolean closeWebTab(WebTab webTabToClose){
        // Return true if we found the page in our list of main.java.pages and closed it
        // Return false otherwise
        for (int i = 0; i < webTabs.size(); i++){
            if (webTabs.get(i).getHandle().equals(webTabToClose.getHandle())){
                webDriver.close();
                webTabs.remove(i);
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
}

