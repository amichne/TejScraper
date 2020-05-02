package main.java;

import main.java.config.Config;

import static main.java.helpers.GsonHelper.gson;

public class EbayScraper {
    public static void main(String[] args) {
        Config config = Config.load(args[0]);
        System.out.println(gson.toJson(config));

    }
}
