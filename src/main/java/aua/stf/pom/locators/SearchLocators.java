package aua.stf.pom.locators;

import org.openqa.selenium.By;

public class SearchLocators {
    public static final By SEARCH_INPUT_FIELD = By.name("q");
    public static final By SEARCH_BUTTON = By.cssSelector("button.btn.btn-search");
    public static final By ERROR_MESSAGE = By.cssSelector("div.font-size-28.font-weight-medium.color-green.mb-5");
    public static final By SEARCH_RESULTS = By.cssSelector("div.col-6.col-md-4.col-lg-3.col-xl-20.mb-5.list-item");
}
