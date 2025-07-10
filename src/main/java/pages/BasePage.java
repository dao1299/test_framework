package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import base.BaseTest;

import java.time.Duration;
import java.util.List;

public class BasePage {
    private static final int TIMEOUT = BaseTest.TIME_OUT;
    private static final int POLLING = 100;

    WebDriver driver;
    WebDriverWait explicitWait;
    JavascriptExecutor jsExecutor;

    public BasePage() {
        driver = BaseTest.getDriver();
        jsExecutor = (JavascriptExecutor) driver;
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
    }

    protected WebElement findElement(By locator) {
        return explicitWait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected List<WebElement> findElements(By locator) {
        return explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    protected void openPageUrl(String pageUrl) {
        driver.get(pageUrl);
    }

    protected String getPageTitle() {
        return driver.getTitle();
    }

    protected String getPageUrl() {
        return driver.getCurrentUrl();
    }

    protected void clickToElement(By locator) {
        explicitWait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void clickToElement(WebElement element) {
        explicitWait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    protected void clickToElementByJS(WebElement element) {
        jsExecutor.executeScript("arguments[0].click();", element);
    }

    protected void scrollToElement(WebElement element) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void sendKeyToElement(By locator, String textValue) {
        WebElement element = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(textValue);
    }

    protected String getTextElement(WebElement element) {
        return element.getText();
    }

    protected int getElementsSize(By locator) {
        List<WebElement> elements = findElements(locator);
        return elements.size();
    }

    protected String getText(By locator) {
        WebElement element = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element.getText();
    }


    protected String getText(WebElement element) {
        explicitWait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    protected void waitForElementVisible(WebElement element) {
        explicitWait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementInvisible(WebElement element) {
        explicitWait.until(ExpectedConditions.invisibilityOf(element));
    }

    protected boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void sleepTimeInSecond(int second) {
        try {
            Thread.sleep(second * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getAttributeValue(WebElement element, String attributeValue){
        return element.getDomAttribute(attributeValue);
    }

    public void waitForPageLoad() {
        explicitWait.until(webDriver ->
                ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
        );
    }


}
