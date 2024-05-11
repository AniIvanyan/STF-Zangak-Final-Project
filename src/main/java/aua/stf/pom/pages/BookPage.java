package aua.stf.pom.pages;

import aua.stf.pom.base.BasePage;
import aua.stf.pom.locators.BookLocators;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookPage extends BasePage {

    public BookPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public String getBookTitle() {
        return driver.findElement(BookLocators.PRODUCT_TITLE).getText();
    }

    public String getAuthorName() {
        return driver.findElement(BookLocators.AUTHOR_NAME).getText();
    }

    public String getBookPrice() {
        return driver.findElement(BookLocators.PRODUCT_PRICE).getText();
    }

    public void clickAddToBasket() {
        try {
            WebElement addToBasketButton = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(BookLocators.ADD_TO_BASKET_BUTTON));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", addToBasketButton);

            webDriverWait.until(ExpectedConditions.elementToBeClickable(addToBasketButton));

            addToBasketButton.click();
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
        } catch (Exception e) {
            System.out.println("Failed to click on the button: " + e.getMessage());
            throw e;
        }
    }
}
