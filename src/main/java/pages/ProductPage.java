package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class ProductPage extends BasePage{

    private By searchInput = By.id("search_product");

    private By buttonSearch = By.id("submit_search");

    private By searchedProductTitle = By.xpath("//*[text()='Searched Products']");

    private By productWrapper = By.xpath("//div[@class='product-image-wrapper']");


    private By productImages = By.xpath(".//div[@class='productinfo text-center']/img");

    private By productPrices = By.xpath(".//div[@class='productinfo text-center']/h2");

    private By productNames = By.xpath(".//div[@class='productinfo text-center']/p");

    private By addToCartButtons = By.xpath(".//a[contains(@class,'add-to-cart')]");

    private By viewProductLinks = By.xpath(".//a[contains(@href, '/product_details/')]");

    public String getTitleProductPage(){
        return getPageTitle();
    }

    @Step("Enter product name in search input and click search button")
    public void searchProduct(String keyword){
        sendKeywordToInput(keyword);
        clickButtonSearch();
    }

    public void clickButtonSearch(){
        clickToElement(buttonSearch);
    }

    public void sendKeywordToInput(String keyword){
        sendKeyToElement(searchInput,keyword);
    }

    @Step("Verify 'SEARCHED PRODUCTS' is visible")
    public boolean verifySearchedProductTitleDisplayed(){
        return findElement(searchedProductTitle).isDisplayed();
    }

    @Step("Verify all the products related to search are visible")
    public boolean verifyProductsAreVisible(){
        List<WebElement> listProductSearched = getListProductSearched();
        for (WebElement product : listProductSearched){
            if (!product.findElement(productImages).isDisplayed()) return false;
            if (!product.findElement(productNames).isDisplayed()) return false;
            if (!product.findElement(productPrices).isDisplayed()) return false;
            if (!product.findElement(addToCartButtons).isDisplayed()) return false;
            if (!product.findElement(viewProductLinks).isDisplayed()) return false;
        }
        return true;
    }



    public List<WebElement> getListProductSearched(){
        return findElements(productWrapper);
    }
}
