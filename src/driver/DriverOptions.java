package driver;

import org.openqa.selenium.chrome.ChromeOptions;

import static driver.Constants.CHROME_OPTIONS;

public class DriverOptions {
    private ChromeOptions chromeOptions;

    public DriverOptions(){
        setChromeOptions(CHROME_OPTIONS);
    }

    public void setChromeOptions(String... strings) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(strings);
        this.chromeOptions = options;
    }

    public ChromeOptions getChromeOptions() {
        return chromeOptions;
    }
}
