package aua.stf.pom.pages;

import aua.stf.pom.base.BasePage;
import aua.stf.pom.locators.SearchLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SearchPage extends BasePage {

    public SearchPage(WebDriver driver, WebDriverWait webDriverWait) {
        super(driver, webDriverWait);
    }

    public void enterSearchText(String searchText) {
        WebElement searchField = driver.findElement(SearchLocators.SEARCH_INPUT_FIELD);
        searchField.clear();
        searchField.sendKeys(searchText);
    }

    public void clickSearchButton() {
        driver.findElement(SearchLocators.SEARCH_BUTTON).click();
    }

    public void performSearch(String searchText) {
        enterSearchText(searchText);
        clickSearchButton();
    }

    public String getErrorMessage() {
        try {
            WebElement errorMessage = driver.findElement(SearchLocators.ERROR_MESSAGE);
            return errorMessage.getText();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean areSearchResultsPresent() {
        return !driver.findElements(SearchLocators.SEARCH_RESULTS).isEmpty();
    }
}
