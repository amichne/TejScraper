package main.java.helpers;

import org.openqa.selenium.WebElement;

public class WebElementHelper {

    public static String GetWebElementXpath(WebElement webElement) throws AssertionError{
        Object o = webElement;
        String text = o.toString();
        /* text is smth like this
        [[FirefoxDriver: firefox on WINDOWS (9170d4a5-1554-4018-adac-f3f6385370c0)] -> xpath: //div[contains(@class,'forum-topic-preview')]//div[contains(@class,'small-human')]]
        */
        text = text.substring( text.indexOf("xpath: ")+7,text.length()-1);
        return text;
    }
}
