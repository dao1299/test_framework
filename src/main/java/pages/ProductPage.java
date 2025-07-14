package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
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

    private By continueButton = By.xpath("//button[text()='Continue Shopping']");

    private By viewCart = By.xpath("//a[@href='/view_cart']");

    private By listProductNameCart = By.xpath("//tr[starts-with(@id,'product')]/td[@class='cart_description']//a");

    private List<String> listNameProduct = new ArrayList<>();
    public String getTitleProductPage(){
        return getPageTitle();
    }

    @Step("Enter product name in search input and click search button ")
    public void searchProduct(String keyword){
        sendKeywordToInput(keyword);
        clickButtonSearch();
    }

    @Step("Click to button search")
    public void clickButtonSearch(){
        clickToElement(buttonSearch);
    }

    @Step("Send keyword: {0}")
    public void sendKeywordToInput(String keyword){
        sendKeyToElement(searchInput,keyword);
    }

    @Step("Verify 'SEARCHED PRODUCTS' is visible")
    public boolean verifySearchedProductTitleDisplayed(){
        return findElement(searchedProductTitle).isDisplayed();
    }

    @Step("Verify all the products related to search are visible")
    public boolean verifyProductsAreVisible(){
        int count = 0;
        List<WebElement> listProductSearched = getListProductSearched();
        for (WebElement product : listProductSearched){
            if (!product.findElement(productImages).isDisplayed()) return false;
            if (!product.findElement(productNames).isDisplayed()) return false;
            if (!product.findElement(productPrices).isDisplayed()) return false;
            if (!product.findElement(viewProductLinks).isDisplayed()) return false;
            if (!product.findElement(addToCartButtons).isDisplayed()) return false;
            else{
                scrollToElement(findElement(addToCartButtons));
                product.findElement(addToCartButtons).click();
                listNameProduct.add(product.findElement(productNames).getText());
                if (count==listProductSearched.size()-1) {
                    clickToElement(viewCart);
                    continue;
                }
                clickToElement(continueButton);
                count++;
            }

        }
        return true;
    }



    public List<WebElement> getListProductSearched(){
        return findElements(productWrapper);
    }

    public boolean verifyListProductInCart(){
        // Lay ra danh sach san pham co tren UI => so sanh voi listNameProduct
        List<String> listNameProductBackup = new ArrayList<>(listNameProduct);
        List<WebElement> listProductInCart = findElements(listProductNameCart);
        for (WebElement element: listProductInCart){
            if (listNameProductBackup.remove(element.getText())) System.out.println("Remove: "+element.getText());
        }
        return (listNameProductBackup.isEmpty());
    }
}
