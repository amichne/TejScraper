package main.java.driver;

public class DriverConstants {
    // Defaults, will implement config parser before conclusion
    public static final String CHROME_DRIVER_NAME = "webdriver.chrome.driver";
    public static final String CHROME_DRIVER_PATH = "chromedriver";
//    public static final String[] CHROME_OPTIONS = {"--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors"};
    public static final String[] CHROME_OPTIONS = {"--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors"};
    public static final String OPEN_WINDOW_CMD = "window.open()";

}
