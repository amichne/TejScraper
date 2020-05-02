package main.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;
import static main.java.pages.AttributeConstants.LINK_ATTR;

public class PageUtil {

    public static void waitForElement(WebDriver webDriver, String xpath, int timeout, int sleep){
        new WebDriverWait(webDriver, timeout, sleep).until(visibilityOfAllElementsLocatedBy(By.xpath(xpath)));
    }

    public static void waitForElement(WebDriver webDriver, String xpath){
        waitForElement(webDriver, xpath, 15, 3);
    }

    public static String getAttribute(WebDriver webDriver, String xpath, String attribute){
        return webDriver.findElement(By.xpath(xpath)).getAttribute(attribute);
    }

    public static String getLink(WebDriver webDriver, String xpath){
        return getAttribute(webDriver, xpath, LINK_ATTR);
    }


}
