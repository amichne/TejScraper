package driver;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.Page;

import java.util.ArrayList;

import static driver.Constants.CHROME_DRIVER_NAME;
import static driver.Constants.CHROME_DRIVER_PATH;

public class Driver {
    private CustomWebDriver webDriver;
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
            webDriver = new CustomWebDriver(driverOptions.getChromeOptions());
        }
        else {
            throw new RuntimeException("Unrecognized DriverName given. DriverName="  + driverName);
        }
    }

    public void focusPage(Page page) {
        webDriver.switchTo().window(page.getHandle());
    }

    public void openPage(Page page){
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
        new WebDriverWait(webDriver, 15, 3).until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(page.getXpath())));
    }

    public boolean closePage(Page pageToClose){
        // Return true if we found the page in our list of pages and closed it
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

    public CustomWebDriver getWebDriver() {
        return webDriver;
    }

    public DriverOptions getDriverOptions() {
        return driverOptions;
    }

    public static void main(String[] args){
        Driver driver = new Driver("webdriver.chrome.driver", "chromedriver");

        driver.getWebDriver().close();
    }
}

