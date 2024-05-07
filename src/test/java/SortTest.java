import aua.stf.pom.pages.SortPage;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class SortTest extends BaseTest {

    @Test(priority = 4)
    public void testSortByPriceLowToHigh() {
        SortPage sortPage = new SortPage(driver, webDriverWait);
        sortPage.sortByPriceLowToHigh();
        List<Double> prices = sortPage.getProductPrices();

        for (int i = 0; i < prices.size() - 1; i++) {
            Assert.assertTrue(prices.get(i) <= prices.get(i + 1),
                    "Prices are not sorted from low to high. Found " + prices.get(i) + " before " + prices.get(i + 1));
        }
    }

    @Test(priority = 5)
    public void testSortByPriceHighToLow() {
        SortPage sortPage = new SortPage(driver, webDriverWait);
        sortPage.sortByPriceHighToLow();
        List<Double> prices = sortPage.getProductPrices();

        for (int i = 0; i < prices.size() - 1; i++) {
            Assert.assertTrue(prices.get(i) >= prices.get(i + 1),
                    "Prices are not sorted from high to low. Found " + prices.get(i) + " before " + prices.get(i + 1));
        }
    }
}

