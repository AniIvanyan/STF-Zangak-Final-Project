package aua.stf.pom.pages;

import aua.stf.pom.base.BasePage;
import aua.stf.pom.locators.SortLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.stream.Collectors;


public class SortPage extends BasePage {

    public SortPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void openSortDropdown() {
        WebElement sortButton = webDriverWait.until(ExpectedConditions.elementToBeClickable(SortLocators.SORT_BUTTON));
        sortButton.click();
    }

    public void sortByPriceLowToHigh() {
        openSortDropdown();
        WebElement sortByLowToHigh = webDriverWait.until(ExpectedConditions.elementToBeClickable(SortLocators.SORT_OPTION_LOW_TO_HIGH));
        sortByLowToHigh.click();
    }

    public void sortByPriceHighToLow() {
        openSortDropdown();
        WebElement sortByHighToLow = webDriverWait.until(ExpectedConditions.elementToBeClickable(SortLocators.SORT_OPTION_HIGH_TO_LOW));
        sortByHighToLow.click();
    }


    public List<Double> getProductPrices() {
        return webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(SortLocators.PRODUCT_PRICE))
                .stream()
                .map(WebElement::getText)
                .map(priceText -> priceText.replaceAll("[^\\d.]", ""))
                .map(Double::parseDouble)
                .collect(Collectors.toList());
    }
}
