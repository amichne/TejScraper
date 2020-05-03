package main.java.scraper;

import main.java.helpers.GsonHelper;
import main.java.pages.WebTab;
import main.java.pojos.CountablePage;

import java.util.ArrayList;

public class Results {
    private ArrayList<Result> results;

    public Results(){

    }

    public void addResult(WebTab webTab, CountablePage countablePage){
        System.out.println(GsonHelper.start().toJson(countablePage));
    }
}
