package driver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CustomWebDriver extends ChromeDriver {
    private String pageId;

    public CustomWebDriver(ChromeOptions chromeOptions){
        super(chromeOptions);
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }
}
