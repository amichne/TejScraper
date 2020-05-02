package driver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static driver.Constants.CHROME_WEBDRIVER;

public class Driver {
    private String driverPath;
    private WebDriver webDriver;
    private DriverOptions driverOptions;
    private String baseWindow;
    private String auxWindow;

    public Driver(){
        driverOptions = new DriverOptions();
    }

    public void loadDriver(String driverName, String driverPath){
        System.setProperty(driverName, driverPath);
        if (CHROME_WEBDRIVER.equalsIgnoreCase(driverName)){
            webDriver = new ChromeDriver(driverOptions.getChromeOptions());
        }
        else {
            throw new RuntimeException("Unrecognized DriverName given. DriverName="  + driverName);
        }
    }

    public void loadPage(String url){
        webDriver.get(url);
    }

    public void openLink(String xpath, int tab){
        // TODO: Configurable

        webDriver.switchTo().window(new ArrayList<>(webDriver.getWindowHandles()).get(tab));
        new WebDriverWait(webDriver, 15, 3).until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpath)));
        String linkedUrl = webDriver.findElement(By.xpath(xpath)).getAttribute("href");
        System.out.println(linkedUrl);
        ((JavascriptExecutor) webDriver).executeScript("window.open()");

        ArrayList<String> strings = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(strings.get(strings.size() - 1));
        webDriver.navigate().to(linkedUrl);
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public static void main(String[] args){
        Driver driver = new Driver();
        driver.loadDriver("webdriver.chrome.driver", "chromedriver");
        driver.loadPage("https://www.ebay.com/b/Rolex-Wristwatches/31387/bn_2989578");
        driver.openLink("//*[@id=\"s0-25-9-0-1[0]-0-0-xCarousel-x-carousel-items\"]/ul/li[1]/a", 0);
        driver.openLink("//*[@id=\"s0-25-9-0-1[0]-0-0-xCarousel-x-carousel-items\"]/ul/li[1]/a", 1);
        driver.getWebDriver().close();
    }
}

