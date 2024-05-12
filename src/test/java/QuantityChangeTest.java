import aua.stf.pom.locators.BasketBookLocators;
import aua.stf.pom.locators.BookLocators;
import aua.stf.pom.locators.SearchLocators;
import aua.stf.pom.pages.SortPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.BaseTest;
import aua.stf.pom.pages.BasketBookPage;
import aua.stf.pom.pages.BookPage;
import aua.stf.pom.pages.SearchPage;

public class QuantityChangeTest extends BaseTest {
    private BasketBookPage basketBookPage;
    private static final String ERROR_MESSAGE_QUANTITY_INC = "Quantity did not increment correctly";
    private static final String ERROR_MESSAGE_PRICE_INC = "Price did not increase correctly";
    private static final String ERROR_MESSAGE_QUANTITY_DEC = "Quantity did not decrement correctly";
    private static final String ERROR_MESSAGE_PRICE_DEC = "Price did not decrease correctly";
    private static final String DECREASE_NO_CLICK = "Decrement button should not be clickable";
    private static final String NO_CHANGE_QUANTITY = "Quantity did not changed when it should not have";
    private static final String NO_CHANGE_PRICE = "Price did not changed when it should not have";
    private static final String SEARCH_TERM = "Narine Abgaryan";

    @BeforeMethod
    public void setUp() {
        SearchPage searchPage = new SearchPage(driver, webDriverWait);
        BookPage bookPage = new BookPage(driver, webDriverWait);
        basketBookPage = new BasketBookPage(driver, webDriverWait);
        SortPage sortPage = new SortPage(driver, webDriverWait);

        // Prepare the test by performing actions leading up to the main test
        webDriverWait.until(ExpectedConditions.elementToBeClickable(SearchLocators.SEARCH_BUTTON));
        searchPage.performSearch(SEARCH_TERM);
        sortPage.sortByPriceHighToLow();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(BookLocators.ADD_TO_BASKET_BUTTON));
        bookPage.clickAddToBasket();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(BasketBookLocators.CONFIRM_BOX));
        basketBookPage.clickBasketButton();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(BasketBookLocators.BASKET_ITEM_PRICE));
    }

    @Test
    public void testIncreaseItemQuantity() {
        // Test to check if the item quantity increases correctly and price updates accordingly
        int initialQuantity = Integer.parseInt(basketBookPage.getItemQuantity());
        String initialPriceText = basketBookPage.getItemPrice();
        int initialPrice = Integer.parseInt(initialPriceText.split("\\s+")[0]);
        int pricePerUnit = initialPrice / initialQuantity;

        // Perform action to increase quantity
        basketBookPage.increaseQuantity();
        webDriverWait.until(ExpectedConditions.attributeToBe(
                BasketBookLocators.BASKET_ITEM_QUANTITY, "value", String.valueOf(initialQuantity + 1)));

        // Check if the price updates correctly with the increased quantity
        String expectedUpdatedPrice = ((initialQuantity + 1) * pricePerUnit) + " դրամ";
        webDriverWait.until(ExpectedConditions.textToBe(
                BasketBookLocators.BASKET_ITEM_PRICE, expectedUpdatedPrice));

        // Validate changes
        int updatedQuantity = Integer.parseInt(basketBookPage.getItemQuantity());
        String updatedPriceText = basketBookPage.getItemPrice();

        Assert.assertEquals(updatedQuantity, initialQuantity + 1, ERROR_MESSAGE_QUANTITY_INC);
        Assert.assertEquals(updatedPriceText, expectedUpdatedPrice, ERROR_MESSAGE_PRICE_INC);
    }

    @Test
    public void testDecreaseItemQuantity() {
        // Test to ensure item quantity decreases correctly and price updates accordingly
        int initialQuantity = Integer.parseInt(basketBookPage.getItemQuantity().trim());
        String initialPrice = basketBookPage.getItemPrice().trim();

        // Condition to handle cases where decrementing should not be possible
        if (initialQuantity < 2) {
            boolean isButtonClickable = isElementClickable(BasketBookLocators.DECREMENT_BUTTON);
            Assert.assertFalse(isButtonClickable, DECREASE_NO_CLICK);
            String unchangedPrice = basketBookPage.getItemPrice().trim();
            int unchangedQuantity = Integer.parseInt(basketBookPage.getItemQuantity().trim());
            Assert.assertEquals(unchangedQuantity, initialQuantity, NO_CHANGE_QUANTITY);
            Assert.assertEquals(unchangedPrice, initialPrice, NO_CHANGE_PRICE);
            return;
        }

        // Perform action to decrease quantity
        int initialPriceValue = Integer.parseInt(initialPrice.split("\\s+")[0]);
        int pricePerUnit = initialPriceValue / initialQuantity;

        basketBookPage.decreaseQuantity();
        webDriverWait.until(ExpectedConditions.attributeToBe(
                BasketBookLocators.BASKET_ITEM_QUANTITY, "value", String.valueOf(initialQuantity - 1)));

        // Check if the price updates correctly with the decreased quantity
        String expectedUpdatedPrice = (initialPriceValue - pricePerUnit) + " դրամ";
        webDriverWait.until(ExpectedConditions.textToBe(
                BasketBookLocators.BASKET_ITEM_PRICE, expectedUpdatedPrice));

        // Validate changes
        int updatedQuantity = Integer.parseInt(basketBookPage.getItemQuantity().trim());
        String updatedPriceText = basketBookPage.getItemPrice().trim();

        Assert.assertEquals(updatedQuantity, initialQuantity - 1, ERROR_MESSAGE_QUANTITY_DEC);
        Assert.assertEquals(updatedPriceText, expectedUpdatedPrice, ERROR_MESSAGE_PRICE_DEC);
    }

    private boolean isElementClickable(By locator) {
        // Utility method to check if an element is clickable
        try {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
