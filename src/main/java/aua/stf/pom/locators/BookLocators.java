package aua.stf.pom.locators;

import org.openqa.selenium.By;

public class BookLocators {
    public static final By ADD_TO_BASKET_BUTTON = By.cssSelector("a[class*='add-to-cart-']");
    public static final By PRODUCT_TITLE = By.cssSelector("h1.mb-3 a");
    public static final By AUTHOR_NAME = By.cssSelector("h2.text-truncate");
    public static final By PRODUCT_PRICE = By.cssSelector(".product-price");
}

