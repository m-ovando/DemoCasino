package stepDefinitions;

import driver.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import pageObjects.LoginPage;
import pageObjects.DepositPage;
import repository.EnvVariableRepositoryParser;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DepositSteps {

	public WebDriver driver;
	public LoginPage loginPage;
	public DepositPage depositPage;
	private static final EnvVariableRepositoryParser ENV_VARIABLE = EnvVariableRepositoryParser.getInstance();

	@Given("User launch Chrome browser for deposit")
	public void user_launch_chrome_browser_for_deposit() {
		driver = DriverFactory.getDriver();
		driver.manage().window().maximize();
		loginPage = new LoginPage(driver);
		depositPage = new DepositPage(driver);
	}

	@Given("User logged on url {string} with user {string} and password {string}")
	public void user_logged_on_url_with_user_and_password(String url, String mail, String password) {
		String urlValue = ENV_VARIABLE.getValue(url);
		String mailValue = ENV_VARIABLE.getValue(mail);
		String passwordValue = ENV_VARIABLE.getValue(password);
				
		driver.get(urlValue);
		
		loginPage.clickLoginButton();
		loginPage.clickLoginWithGmailButton();
		loginPage.setEmail(mailValue);
		loginPage.setPassword(passwordValue);
	}

	//Scenario 01: Successful Deposit

	@When("User opens the user profile")
	public void user_opens_the_user_profile() {
		depositPage.openUserProfile();
		assertThat(depositPage.getProfileMenu().isDisplayed(), is(true));
	}

	@When("User opens the Deposit option on the profile menu")
	public void user_opens_the_deposit_option_on_the_profile_menu() {
		depositPage.openDepositMenu();
	}

	@When("User clicks on the Manual Deposit button")
	public void user_clicks_on_the_manual_deposit_button() {
		depositPage.clickManualDepositButton();
	}

	@When("User adds the amount {string} on the Amount field")
	public void user_adds_the_amount_on_the_amount_field(String amount) {
		depositPage.setAmount(amount);
	}

	@When("User clicks on the Deposit button")
	public void user_clicks_on_the_deposit_button() {
		depositPage.clickDepositButton();
	}

	@Then("A successful message should appear indicating that the amount was correct")
	public void a_successful_message_should_appear_indicating_that_the_amount_was_correct() {
		assertThat(depositPage.getSuccessfulMessageDeposit().isDisplayed(), is(true));
	}
	
	//Scenario 02: Unsuccessful Deposit
	
	@Then("An error should appear indicating that the amount can't be blank")
	public void an_error_should_appear_indicating_that_the_amount_can_t_be_blank() {
		assertThat(depositPage.getUnsuccessfulMessageDeposit().isDisplayed(), is(true));
	}

	//Close browser for each scenario

	@After("@Deposit")
	public void closeBrowser(){
		driver.close();
	}
}
