package tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pages.LoginPage;
import utils.TestDataReader;

@Listeners({io.qameta.allure.testng.AllureTestNg.class})
public class LoginTest extends BaseTest {
  
  @Epic("Login Feature")
  @Feature("Valid Login")
  @Story("User logs in with valid credentials")
  @Severity(SeverityLevel.CRITICAL)
  @Test(description = "Login with valid username and password")
  public void loginSuccessfully() {
	  String username = TestDataReader.get("username");
	  String password = TestDataReader.get("password");
	  LoginPage loginPage = new LoginPage(getDriver());
	  loginPage.login(username, password);
  }
  
  @Epic("Login Feature")
  @Feature("Valid Login")
  @Story("User logs in with valid credentials")
  @Severity(SeverityLevel.CRITICAL)
  @Test(description = "Login to Agile project with valid username and password")
  public void loginAgileProject() {
	  String agileUsername = TestDataReader.get("agileUsername");
	  String agilePassword = TestDataReader.get("agilePassword");
	  LoginPage loginPage = new LoginPage(getDriver());
	  loginPage.navigateToAgileProject();
	  loginPage.login(agileUsername, agilePassword);
  }
}
