package stepDefinitions;

import driver.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import pageObjects.LoginPage;
import repository.EnvVariableRepositoryParser;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LoginSteps {

	public WebDriver driver;
	public LoginPage loginPage;
	private static final EnvVariableRepositoryParser ENV_VARIABLE = EnvVariableRepositoryParser.getInstance();

	@Given("User launch Chrome browser for login")
	public void user_launch_chrome_browser() {
		driver = DriverFactory.getDriver();
		driver.manage().window().maximize();
		loginPage = new LoginPage(driver);
	}

	@When("User opens URL {string} in login")
	public void user_opens_url(String url) {
		String urlValue = ENV_VARIABLE.getValue(url);
		driver.get(urlValue);
	}

	//Scenario 01: Successful login
	
	@When("User clicks the Sign in button")
	public void user_clicks_the_sign_in_button() {
		loginPage.clickLoginButton();
	}

	@When("User chooses the Gmail option")
	public void user_chooses_the_gmail_option() {
		loginPage.clickLoginWithGmailButton();
	}

	@When("User enters email as {string} and Password as {string}")
	public void user_enters_email_and_password(String mail, String password) {
		String mailValue = ENV_VARIABLE.getValue(mail);
		String passwordValue = ENV_VARIABLE.getValue(password);

		loginPage.setEmail(mailValue);
		loginPage.setPassword(passwordValue);
	}

	@Then("user should appear logged")
	public void user_should_appear_logged() {
		assertThat(loginPage.getUsersAvatar().isDisplayed(), is(true));
		assertThat(loginPage.getUsersName().isDisplayed(), is(true));
	}


	//Close browser for each scenario

	@After("@Login")
		public void closeBrowser(){
		driver.close();
	}
}
