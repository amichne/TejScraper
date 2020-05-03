package main.java.scraper;

import main.java.config.Config;
import main.java.driver.Driver;
import main.java.helpers.GsonHelper;
import main.java.pages.WebTab;
import main.java.pojos.BasePage;
import main.java.pojos.CountablePage;
import main.java.pojos.LinkedPage;
import org.openqa.selenium.WebElement;

import static main.java.helpers.WebTabHelper.*;

public class Explorer {
    protected LinkedPage root;
    public static CountablePage defaultCountable;

    public Explorer(){}

    public Explorer(Config config){
        this.root = config.getExplorer().getRoot();
        defaultCountable = config.getExplorer().getDefaultCountable();
    }

    public Results explore(Driver driver){
        if (root == null || root.getUrl() == null){
            throw new RuntimeException("Root was null or had no url. Received root: "+GsonHelper.start().toJson(root));
        }

        Results results = new Results();
        explore(results, new WebTab(driver, root.getUrl()), root);
        return results;
    }

    private void explore(Results results, WebTab webTab, BasePage page){
        // Recursive method to explore the pages from the root document
        if (page instanceof CountablePage){
            // This is our "base case". The case that causes the recursion to terminate
            explore(results, webTab, (CountablePage) page);
        }

        if (page instanceof LinkedPage){
            // This is out n-1 case, which MUST MAINTAIN continual, linear progression towards reaching the base case
            if (((LinkedPage) page).getChild() != null) {
                explore(results, webTab, (LinkedPage) page);
            }
            else {

            }
        }
    }

    private void explore(Results results, WebTab webTab, LinkedPage page){
        // If we enter this method, we are guaranteed to call explore(results, webTab, basePage) AT LEAST once
        if (page.getLiHrefPath() != null){
            waitForElement(webTab, page.getHrefXpath());
            for (WebElement element : getWebElementsUl(webTab, page.getHrefXpath())){
                explore(results, webTab.openUrl(getLink(element, page.getLiHrefPath()), false), page.getChild());
            }
            return;
        }
        explore(results, webTab.openUrl(getLink(webTab, page.getHrefXpath())), page.getChild());
    }

    private void explore(Results results, WebTab webTab, CountablePage countablePage){
        // Base case
        waitForElement(webTab, countablePage.getCountXpath());

        results.addResult(webTab, countablePage);
    }

    public LinkedPage getRoot() {
        return root;
    }

    public void setRoot(LinkedPage root) {
        this.root = root;
    }

    public CountablePage getDefaultCountable() {
        return defaultCountable;
    }

    public void setDefaultCountable(CountablePage countable) {
        defaultCountable = countable;
    }
}
