import aua.stf.pom.locators.SearchLocators;
import aua.stf.pom.pages.SearchPage;
import base.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {
    private SearchPage searchPage;
    private static final String ERROR_MESSAGE = "Ոչինչ չի գտնվել, փորձիր նորից";
    private static final String INVALID_INPUT = "invalid_input";
    private static final String VALID_INPUT = "Narine Abgaryan";
    private static final String EMPTY_MESSAGE = "URL should not change when search field is empty";

    @BeforeMethod
    public void methodSetup() {
        searchPage = new SearchPage(driver, webDriverWait);
    }

    @Test
    public void testEmptySearchField() {
        // Test to ensure the URL remains unchanged when the search field is submitted empty.
        String oldURL = driver.getCurrentUrl();
        searchPage.enterSearchText("");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(SearchLocators.SEARCH_BUTTON));
        searchPage.clickSearchButton();
        String newURL = driver.getCurrentUrl();
        Assert.assertEquals(newURL, oldURL, EMPTY_MESSAGE);
    }

    @Test
    public void testInvalidInput() {
        // Test to verify the handling of invalid inputs in the search field.
        // Expecting an error message to be displayed.
        webDriverWait.until(ExpectedConditions.elementToBeClickable(SearchLocators.SEARCH_BUTTON));
        searchPage.performSearch(INVALID_INPUT);
        Assert.assertNotNull(searchPage.getErrorMessage(), ERROR_MESSAGE);
    }

    @Test
    public void testValidInput() {
        // Test to verify that valid search terms return search results.
        webDriverWait.until(ExpectedConditions.elementToBeClickable(SearchLocators.SEARCH_BUTTON));
        searchPage.performSearch(VALID_INPUT);
        Assert.assertTrue(searchPage.areSearchResultsPresent());
    }
}

