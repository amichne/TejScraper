package main.java;

import main.java.config.Config;
import main.java.driver.Driver;
import main.java.scraper.Explorer;

import static main.java.helpers.GsonHelper.gson;

public class EbayScraper {
    public static void main(String[] args) {
        Config config = Config.load(args[0]);
        System.out.println(gson.toJson(config));
        Driver driver = new Driver(config.getDriverConfig());
        Explorer explorer = config.getExplorer();
        explorer.explore(driver);
    }
}
