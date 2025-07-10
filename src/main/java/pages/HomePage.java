package pages;

import org.openqa.selenium.By;

public class HomePage extends BasePage{
    private final By productsButton = By.xpath("//a[@href='/products']");

    public void goToProductsPage(){
        clickToElement(productsButton);
    }
}
