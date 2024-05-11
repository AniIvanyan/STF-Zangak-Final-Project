import aua.stf.pom.pages.SortPage;
import aua.stf.pom.pages.SearchPage;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;

public class SortTest extends BaseTest {

    private SortPage sortPage;
    private static final String SEARCH_TERM = "Narine Abgaryan";

    @BeforeMethod
    public void setupSortPage() {
        SearchPage searchPage = new SearchPage(driver, webDriverWait);
        searchPage.performSearch(SEARCH_TERM);
        sortPage = new SortPage(driver, webDriverWait);
    }

    @Test
    public void testSortByPriceLowToHigh() {
        // Test to verify that products can be sorted by price in ascending order.
        // Check that each price is less than or equal to the next price in the list, ensuring correct sorting.
        sortPage.sortByPriceLowToHigh();
        List<Double> prices = sortPage.getProductPrices();
        for (int i = 0; i < prices.size() - 1; i++) {
            Assert.assertTrue(prices.get(i) <= prices.get(i + 1),
                    "Prices are not sorted from low to high. Found " + prices.get(i) + " before " + prices.get(i + 1));
        }
    }

    @Test
    public void testSortByPriceHighToLow() {
        // Test to verify that products can be sorted by price in descending order.
        // Validate that each price is greater than or equal to the next price in the list, confirming correct sorting.
        sortPage.sortByPriceHighToLow();
        List<Double> prices = sortPage.getProductPrices();
        for (int i = 0; i < prices.size() - 1; i++) {
            Assert.assertTrue(prices.get(i) >= prices.get(i + 1),
                    "Prices are not sorted from high to low. Found " + prices.get(i) + " before " + prices.get(i + 1));
        }
    }
}
