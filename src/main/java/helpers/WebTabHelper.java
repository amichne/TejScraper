package main.java.helpers;

import main.java.pages.WebTab;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

import static main.java.pages.AttributeConstants.LINK_ATTR;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;

public class WebTabHelper {

    public static void waitForElement(WebTab webTab, String xpath, int timeout, int sleep){
        new WebDriverWait(webTab.getDriver().getWebDriver(), timeout, sleep).until(visibilityOfAllElementsLocatedBy(By.xpath(xpath)));
    }

    public static void waitForElement(WebTab webTab, String xpath){
        waitForElement(webTab, xpath, 15, 3);
    }

    public static String getAttribute(WebTab webTab, String xpath, String attribute){
        return webTab.getDriver().getWebDriver().findElement(By.xpath(xpath)).getAttribute(attribute);
    }

    public static String getLink(WebTab webTab, String xpath){
        return webTab.getDriver().getWebDriver().findElement(By.xpath(xpath)).getAttribute(LINK_ATTR);
    }

    public static String getAttribute(WebElement webElement, String xpath, String attribute){
        // WebElement means that we CANNOT use absolute Xpath, as we don't have the root of the document
        validateRelativeXpath(xpath);
        return webElement.findElement(By.xpath(xpath)).getAttribute(attribute);
    }

    public static String getLink(WebElement webElement, String xpath){
        // WebElement means that we CANNOT use absolute Xpath, as we don't have the root of the document
        return getAttribute(webElement, xpath, LINK_ATTR);
    }

    public static ArrayList<WebElement> getWebElementsUl(WebTab webTab, String xpath){
        return new ArrayList<>(webTab.getDriver().getWebDriver().findElements(By.xpath(xpath)));
    }

    public static void validateRelativeXpath(String xpath){
        if (xpath == null || !xpath.startsWith("//")) {
            throw new RuntimeException("Received invalid xpath, must be absolute xpath, got: " + xpath);
        }
    }

}
