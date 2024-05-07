import aua.stf.pom.locators.BasketBookLocators;
import aua.stf.pom.pages.BasketBookPage;
import aua.stf.pom.pages.BookPage;
import base.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddBasketTest extends BaseTest {

    private static final String ERROR_MESSAGE_TITLE =  "The book title does not match";
    private static final String ERROR_MESSAGE_AUTHOR =  "The author name does not match";
    private static final String ERROR_MESSAGE_PRICE =  "The book price does not match";

    @Test(priority = 6, dependsOnMethods = {"testSortByPriceHighToLow"})
    public void testAddBookToBasket() {
        BookPage bookPage = new BookPage(driver, webDriverWait);
        BasketBookPage basketBookPage = new BasketBookPage(driver, webDriverWait);
        String expectedTitle = bookPage.getBookTitle();
        String expectedAuthor = bookPage.getAuthorName();
        String expectedPrice = bookPage.getBookPrice();

        bookPage.clickAddToBasket();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(BasketBookLocators.CONFIRM_BOX));

        basketBookPage.clickBasketButton();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(BasketBookLocators.BASKET_ITEM_TITLE));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(BasketBookLocators.BASKET_ITEM_AUTHOR));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(BasketBookLocators.BASKET_ITEM_PRICE));

        String actualTitle = basketBookPage.getItemTitle();
        String actualAuthor = basketBookPage.getItemAuthor();
        String actualPrice = basketBookPage.getItemPrice();

        Assert.assertEquals(actualTitle, expectedTitle, ERROR_MESSAGE_TITLE);
        Assert.assertEquals(actualAuthor, expectedAuthor, ERROR_MESSAGE_AUTHOR);
        Assert.assertEquals(actualPrice, expectedPrice, ERROR_MESSAGE_PRICE);
    }
}
