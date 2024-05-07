package aua.stf.pom.locators;

import org.openqa.selenium.By;

public class SortLocators {
    public static final By SORT_BUTTON = By.cssSelector("a.btn-cs.pl-0[data-toggle='dropdown']");
    public static final By SORT_OPTION_LOW_TO_HIGH = By.cssSelector("a[href*='order_by=priceASC']");
    public static final By SORT_OPTION_HIGH_TO_LOW = By.cssSelector("a[href*='order_by=priceDESC']");
    public static final By PRODUCT_PRICE = By.cssSelector(".product-price");
}

