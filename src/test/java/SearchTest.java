import aua.stf.pom.pages.SearchPage;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {
    private SearchPage searchPage;
    private static final String ERROR_MESSAGE =  "Ոչինչ չի գտնվել, փորձիր նորից";
    private static final String EMPTY_MESSAGE =  "URL should not change when search field is empty";

    @BeforeMethod
    public void methodSetup() {
        searchPage = new SearchPage(driver, webDriverWait);
    }

    @Test(priority = 1)
    public void testEmptySearchField() {
        String oldURL = driver.getCurrentUrl();
        searchPage.enterSearchText("");
        searchPage.clickSearchButton();
        String newURL = driver.getCurrentUrl();
        Assert.assertEquals(newURL, oldURL, EMPTY_MESSAGE);
    }

    @Test(priority = 2)
    public void testInvalidInput() {
        searchPage.performSearch("invalid_input");
        Assert.assertNotNull(searchPage.getErrorMessage(), ERROR_MESSAGE);
    }

    @Test(priority = 3)
    public void testValidInput() {
        searchPage.performSearch("Narine Abgaryan");
        Assert.assertTrue(searchPage.areSearchResultsPresent());
    }
}
