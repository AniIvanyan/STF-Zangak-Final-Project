package aua.stf.pom.pages;

import aua.stf.pom.base.BasePage;
import aua.stf.pom.locators.BasketBookLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasketBookPage extends BasePage {

    public BasketBookPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void clickBasketButton() {
        WebElement basketButton = webDriverWait.until(ExpectedConditions.elementToBeClickable(BasketBookLocators.BASKET_ICON));
        basketButton.click();
    }

    public String getItemTitle() {
        WebElement titleElement = driver.findElement(BasketBookLocators.BASKET_ITEM_TITLE);
        return titleElement.getText();
    }

    public String getItemAuthor() {
        WebElement authorElement = driver.findElement(BasketBookLocators.BASKET_ITEM_AUTHOR);
        return authorElement.getText();
    }

    public String getItemPrice() {
        WebElement priceElement = driver.findElement(BasketBookLocators.BASKET_ITEM_PRICE);
        return priceElement.getText();
    }

    public String getItemQuantity() {
        WebElement quantityInput = driver.findElement(BasketBookLocators.BASKET_ITEM_QUANTITY);
        return quantityInput.getAttribute("value");
    }
    public void increaseQuantity() {
        WebElement incrementButton = webDriverWait.until(ExpectedConditions.elementToBeClickable(BasketBookLocators.INCREMENT_BUTTON));
        incrementButton.click();
    }

    public void decreaseQuantity() {
        WebElement decrementButton = webDriverWait.until(ExpectedConditions.elementToBeClickable(BasketBookLocators.DECREMENT_BUTTON));
        decrementButton.click();
    }

}
