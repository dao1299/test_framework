package listeners;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import base.BaseTest;
import io.qameta.allure.Allure;
import utils.ScreenshotUtils;

public class TestListener implements ITestListener {
	
	@Override
	public void onTestFailure(ITestResult result) {
        Object currentClass = result.getInstance();
        WebDriver driver = ((BaseTest) currentClass).getDriver();

        String testName = result.getMethod().getMethodName();
        String screenshotPath = ScreenshotUtils.takeScreenshot(driver, testName);

        // Attach to Allure
        try (InputStream is = new FileInputStream(screenshotPath)) {
            Allure.addAttachment("Failure Screenshot", is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	@Override
	public void onFinish(ITestContext context) {
	    String suiteName = context.getSuite().getName();
	    String testName = context.getName();
	    int passed = context.getPassedTests().size();
	    int failed = context.getFailedTests().size();
	    int skipped = context.getSkippedTests().size();

	    System.out.println("===== Test Summary: " + suiteName + " / " + testName + " =====");
	    System.out.println("✅ PASSED: " + passed);
	    System.out.println("❌ FAILED: " + failed);
	    System.out.println("⚠️ SKIPPED: " + skipped);
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
	    String testName = result.getMethod().getMethodName();
	    BaseTest base = (BaseTest) result.getInstance();
	    WebDriver driver = base.getDriver();

	    // Take screenshot
	    String screenshotPath = ScreenshotUtils.takeScreenshot(driver, testName + "_success");

	    // Attach to Allure
	    try (InputStream is = new FileInputStream(screenshotPath)) {
	        Allure.addAttachment("Success Screenshot", is);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    System.out.println("✅ Test passed: " + testName);
	}

}
