package stepDefinitions;

import driver.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import repository.EnvVariableRepositoryParser;
import pageObjects.UserRegistrationPage;
import com.github.javafaker.Faker;
import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class UserRegistrationSteps {
	
	public WebDriver driver;
	public UserRegistrationPage userRegistrationPage;
	private static final EnvVariableRepositoryParser ENV_VARIABLE = EnvVariableRepositoryParser.getInstance();

	
	@Given("User launch Chrome browser")
	public void user_launch_chrome_browser() {
		driver = DriverFactory.getDriver();
		driver.manage().window().maximize();
		userRegistrationPage = new UserRegistrationPage(driver);
	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {
		String urlValue = ENV_VARIABLE.getValue(url);
		driver.get(urlValue);
	}

	//Scenario 01: Successful User registration
	@When("User clicks Registration button")
	public void user_clicks_registration_button() {
		userRegistrationPage.clickRegistrationButton();
	}

	@Then("Page redirects to {string}")
	public void page_redirects_to(String newURL) {
		String newURLValue = ENV_VARIABLE.getValue(newURL);
		assertThat(driver.getCurrentUrl(), is(newURLValue));
	}

	@When("User activates checkbox for terms and conditions")
	public void user_activates_checkbox_for_terms_and_conditions() {
		userRegistrationPage.clickCheckboxTermsConditions();
	}

	@When("User adds valid password {string}")
	public void user_adds_valid_password(String validPass) {
		//String objectWithTimeStamp = objectName + "_" + Instant.now().toEpochMilli();
		String validPassValue = ENV_VARIABLE.getValue(validPass);
		userRegistrationPage.addNewPasswordValue(validPassValue);
		//userRegistrationMap.put(passwordValue);
	}

	@When("User repeats password {string}")
	public void user_repeats_password(String validPass) {
		String validPassValue = ENV_VARIABLE.getValue(validPass);
		userRegistrationPage.addRepeatPasswordValue(validPassValue);
	}

	@When("User adds valid phone number")
	public void user_adds_valid_phone_number() {
		Faker faker = new Faker(new Locale("en-US"));

		String validPhoneValue = faker.phoneNumber().cellPhone();
		userRegistrationPage.addPhoneValue(validPhoneValue);
		userRegistrationPage.setCountry();

		//Todo: Captcha control + Faker failure
	}

	@When("User click on the Registration button at the end of the form")
	public void user_click_on_the_registration_button_at_the_end_of_the_form() {
		//userRegistrationPage.wait(5000);
		userRegistrationPage.clickFormRegistrationButton();
		
		//Todo: hook to avoid captcha
		//if (userRegistrationPage.getCatpcha().isDisplayed() == true){
		//	new WebDriverWait(driver, 10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@src, 'recaptcha')]")));
		//	new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.recaptcha-checkbox-checkmark"))).click();
		//} else {
		//	userRegistrationPage.wait(5000);
		//}
	}

	@Then("The user should be logged and redirected to the main page")
	public void the_user_should_be_logged_and_redirected_to_the_main_page() {
		assertThat(userRegistrationPage.getNotificationSuccess().isDisplayed(), is(true));
	}
	
	//Scenario 2: Unsuccessful User registration with invalid password

	@When("User adds invalid password {string}")
	public void user_adds_invalid_password(String invalidPass) {
		String invalidPassValue = ENV_VARIABLE.getValue(invalidPass);
		userRegistrationPage.addNewPasswordValue(invalidPassValue);
	}

	@Then("The user should not be register and an error for invalid pass should appear")
	public void the_user_should_not_be_register_invalid_pass() {
		assertThat(userRegistrationPage.getErrorInvalidPassword().isDisplayed(), is(true));
	}

	//Scenario 3: Unsuccessful User registration with invalid phone
	
	@When("User adds invalid phone number {string}")
	public void user_adds_invalid_phone_number(String invalidPhone) {
		String invalidPhoneValue = ENV_VARIABLE.getValue(invalidPhone);
		
		userRegistrationPage.addPhoneValue(invalidPhoneValue);
		userRegistrationPage.setCountry();
	}

	@Then("The user should not be register and an error for invalid phone should appear")
	public void the_user_should_not_be_register_invalid_phone() {
		assertThat(userRegistrationPage.getErrorInvalidPhone().isDisplayed(), is(true));
	}

	//Scenario 4: Unsuccessful User registration with inactive checkbox for term and conditions

	@Then("The user should not be register and an error for not accepting term and conditions")
	public void the_user_should_not_be_register_unacepted_terms_conditions() {
		assertThat(userRegistrationPage.getErrorUncheckedTermAndConditions().isDisplayed(), is(true));
	}

	//Close browser for each scenario

	@After("@UserRegistration")
	public void closeBrowser(){
		driver.close();
	}
}
