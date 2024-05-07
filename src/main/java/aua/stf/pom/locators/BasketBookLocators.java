package aua.stf.pom.locators;

import org.openqa.selenium.By;

public class BasketBookLocators {
    public static final By BASKET_ICON = By.cssSelector("a.btn.cart-btn.header-btn.btn-white-badge");
    public static final By CONFIRM_BOX = By.cssSelector(".added-to-card-product");
    public static final By BASKET_ITEM_TITLE = By.cssSelector(".cart-item .title a");
    public static final By BASKET_ITEM_AUTHOR = By.cssSelector(".cart-item .authors");
    public static final By BASKET_ITEM_PRICE = By.cssSelector(".cart-item .cart-item-price");
    public static final By INCREMENT_BUTTON = By.cssSelector("button.count-btn.btn-up");
    public static final By DECREMENT_BUTTON = By.cssSelector("button.count-btn.btn-down");
    public static final By BASKET_ITEM_QUANTITY = By.cssSelector("input.form-control.count");

}
