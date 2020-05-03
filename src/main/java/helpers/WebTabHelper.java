package main.java.helpers;

import main.java.pages.WebTab;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

import static main.java.pages.AttributeConstants.*;
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
        validateXpathListItems(xpath);
        WebElement webElement = webTab.getDriver().getWebDriver().findElement(By.xpath(xpath));
        return new ArrayList<>(webElement.findElements(By.tagName("li")));
    }

    private static void validateXpathListItems(String xpath) {
        if (!xpath.endsWith(LI_ATTR)){
            throw new RuntimeException("Iterable xpaths must end with \"li\", received xpath: " + xpath);
        }
    }


//    public static String getXpath(WebElement webElement){
//        [ChromeDriver: chrome on MAC (2369a811d66351c21669bd409a54e812)] -> xpath: //*[@id="s0-25-9-0-1[0]-0-0-xCarousel-x-carousel-items"]/ul
//        return webElement.toString().split("xpath: ")[0];
//    }

    public static void validateRelativeXpath(String xpath){
        if (xpath == null || !xpath.startsWith("//")) {
            throw new RuntimeException("Received invalid xpath, must be relative xpath, got: " + xpath);
        }
    }

}
