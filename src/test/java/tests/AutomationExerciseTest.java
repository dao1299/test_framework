package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductPage;
import utils.TestDataReader;

public class AutomationExerciseTest extends BaseTest {
    @Test
    public void TC_1(){
        String expectedHeaderTextStep3 = "All Products".toUpperCase();
        HomePage homePage = new HomePage();
        homePage.goToProductsPage();
        ProductPage productPage = new ProductPage();
        Assert.assertTrue(productPage.getTitleProductPage().toUpperCase().contains(expectedHeaderTextStep3));

        productPage.searchProduct(TestDataReader.get("keywordSearchTestCase01"));
        Assert.assertTrue(productPage.verifySearchedProductTitleDisplayed());

        Assert.assertTrue(productPage.verifyProductsAreVisible());
    }
}
