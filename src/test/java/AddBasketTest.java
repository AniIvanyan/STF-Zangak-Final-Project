import aua.stf.pom.locators.BasketBookLocators;
import aua.stf.pom.locators.BookLocators;
import aua.stf.pom.locators.SearchLocators;
import aua.stf.pom.pages.BasketBookPage;
import aua.stf.pom.pages.BookPage;
import aua.stf.pom.pages.SearchPage;
import aua.stf.pom.pages.SortPage;
import base.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddBasketTest extends BaseTest {

    private static final String ERROR_MESSAGE_TITLE = "The book title does not match";
    private static final String ERROR_MESSAGE_AUTHOR = "The author name does not match";
    private static final String ERROR_MESSAGE_PRICE = "The book price does not match";
    private static final String SEARCH_TERM = "Narine Abgaryan";

    private SearchPage searchPage;
    private SortPage sortPage;
    private BookPage bookPage;
    private BasketBookPage basketBookPage;

    @BeforeMethod
    public void setupPages() {
        searchPage = new SearchPage(driver, webDriverWait);
        sortPage = new SortPage(driver, webDriverWait);
        bookPage = new BookPage(driver, webDriverWait);
        basketBookPage = new BasketBookPage(driver, webDriverWait);
    }

    @Test
    public void testAddBookToBasket() {
        // Ensuring the search button is clickable before proceeding
        webDriverWait.until(ExpectedConditions.elementToBeClickable(SearchLocators.SEARCH_BUTTON));
        // Performing a search to find books
        searchPage.performSearch(SEARCH_TERM);
        // Sorting books by price in descending order
        sortPage.sortByPriceHighToLow();
        // Storing expected values for title, author, and price to verify later
        String expectedTitle = bookPage.getBookTitle();
        String expectedAuthor = bookPage.getAuthorName();
        String expectedPrice = bookPage.getBookPrice();

        // Adding the book to the basket
        webDriverWait.until(ExpectedConditions.elementToBeClickable(BookLocators.ADD_TO_BASKET_BUTTON));
        bookPage.clickAddToBasket();
        // Confirming basket operation
        webDriverWait.until(ExpectedConditions.elementToBeClickable(BasketBookLocators.CONFIRM_BOX));
        basketBookPage.clickBasketButton();

        // Ensuring all elements are visible before verifying
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(BasketBookLocators.BASKET_ITEM_TITLE));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(BasketBookLocators.BASKET_ITEM_AUTHOR));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(BasketBookLocators.BASKET_ITEM_PRICE));

        // Retrieving and comparing the actual title, author, and price with the expected values
        String actualTitle = basketBookPage.getItemTitle();
        String actualAuthor = basketBookPage.getItemAuthor();
        String actualPrice = basketBookPage.getItemPrice();

        Assert.assertEquals(actualTitle, expectedTitle, ERROR_MESSAGE_TITLE);
        Assert.assertEquals(actualAuthor, expectedAuthor, ERROR_MESSAGE_AUTHOR);
        Assert.assertEquals(actualPrice, expectedPrice, ERROR_MESSAGE_PRICE);
    }
}
