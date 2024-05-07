import aua.stf.pom.locators.BasketBookLocators;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.BaseTest;
import aua.stf.pom.pages.BasketBookPage;

public class QuantityChangeTest extends BaseTest {
    private BasketBookPage basketBookPage;
    private static final String ERROR_MESSAGE_QUANTITY_INC = "Quantity did not increment correctly";
    private static final String ERROR_MESSAGE_PRICE_INC = "Price did not increase correctly";
    private static final String ERROR_MESSAGE_QUANTITY_DEC = "Quantity did not decrement correctly";
    private static final String ERROR_MESSAGE_PRICE_DEC = "Price did not decrease correctly";

    @BeforeMethod
    public void setUp() {
        basketBookPage = new BasketBookPage(driver, webDriverWait);
    }

    @Test(priority = 7, dependsOnMethods = {"testAddBookToBasket"})
    public void testIncreaseItemQuantity() {
        int initialQuantity = Integer.parseInt(basketBookPage.getItemQuantity());
        String initialPriceText = basketBookPage.getItemPrice();
        int initialPrice = Integer.parseInt(initialPriceText.split("\\s+")[0]);
        int pricePerUnit = initialPrice / initialQuantity;

        basketBookPage.increaseQuantity();
        webDriverWait.until(ExpectedConditions.attributeToBe(
                BasketBookLocators.BASKET_ITEM_QUANTITY, "value", String.valueOf(initialQuantity + 1)));

        String expectedUpdatedPrice = ((initialQuantity + 1) * pricePerUnit) + " դրամ";

        webDriverWait.until(ExpectedConditions.textToBe(
                BasketBookLocators.BASKET_ITEM_PRICE, expectedUpdatedPrice));

        int updatedQuantity = Integer.parseInt(basketBookPage.getItemQuantity());
        String updatedPriceText = basketBookPage.getItemPrice();

        Assert.assertEquals(updatedQuantity, initialQuantity + 1, ERROR_MESSAGE_QUANTITY_INC);
        Assert.assertEquals(updatedPriceText, expectedUpdatedPrice, ERROR_MESSAGE_PRICE_INC);
    }


    @Test(priority = 8, dependsOnMethods = {"testIncreaseItemQuantity"})
    public void testDecreaseItemQuantity() {
        int initialQuantity = Integer.parseInt(basketBookPage.getItemQuantity());
        if (initialQuantity < 2) {
            basketBookPage.increaseQuantity();
            webDriverWait.until(ExpectedConditions.attributeToBe(
                    BasketBookLocators.BASKET_ITEM_QUANTITY, "value", "2"));
        }

        String initialPriceText = basketBookPage.getItemPrice();
        int initialPrice = Integer.parseInt(initialPriceText.split("\\s+")[0]);
        int pricePerUnit = initialPrice / initialQuantity;

        basketBookPage.decreaseQuantity();
        webDriverWait.until(ExpectedConditions.attributeToBe(
                BasketBookLocators.BASKET_ITEM_QUANTITY, "value", String.valueOf(initialQuantity - 1)));

        String expectedUpdatedPrice = (initialPrice - pricePerUnit) + " դրամ";

        webDriverWait.until(ExpectedConditions.textToBe(
                BasketBookLocators.BASKET_ITEM_PRICE, expectedUpdatedPrice));

        int updatedQuantity = Integer.parseInt(basketBookPage.getItemQuantity());
        String updatedPriceText = basketBookPage.getItemPrice();

        Assert.assertEquals(updatedQuantity, initialQuantity - 1, ERROR_MESSAGE_QUANTITY_DEC);
        Assert.assertEquals(updatedPriceText, expectedUpdatedPrice, ERROR_MESSAGE_PRICE_DEC);
    }

}
