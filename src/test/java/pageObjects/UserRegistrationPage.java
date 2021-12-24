package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.JavascriptExecutor;
import repository.RepositoryParser;

import java.time.Duration;

public class UserRegistrationPage {
	
	private static final RepositoryParser repositoryParser = new RepositoryParser("userregistration.page.repository.properties");

	private WebDriver webDriver;

	public UserRegistrationPage(WebDriver driver) {
		webDriver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickRegistrationButton() {
		String value = repositoryParser.getValue("userregistration.header.registrationbutton.xpath");
		WebElement buttonRegistration = webDriver.findElement(By.xpath(value));
		buttonRegistration.click();
	}

	public void clickCheckboxTermsConditions() {
		String checkbox = repositoryParser.getValue("userregistration.registrationform.checkbox.termsandconditions.cssSelector");
		WebElement checkboxTermConditions = webDriver.findElement(By.cssSelector(checkbox));
		JavascriptExecutor executor = (JavascriptExecutor)webDriver;
		executor.executeScript("arguments[0].click();", checkboxTermConditions);
	}

	public void addNewPasswordValue(String pass) {
		String value = repositoryParser.getValue("userregistration.registrationform.password.xpath");
		WebElement password = webDriver.findElement(By.xpath(value));
		password.clear();
		password.sendKeys(pass);
	}

	public void addRepeatPasswordValue(String pass) {
		String value = repositoryParser.getValue("userregistration.registrationform.repeatPassword.xpath");
		WebElement password = webDriver.findElement(By.xpath(value));
		password.clear();
		password.sendKeys(pass);
	}

	public void setCountry(){
		//Opening combobox with the list of countries
		String value = repositoryParser.getValue("userregistration.registrationform.phone.country.combobox.xpath");
		WebElement countrySelectorElement = webDriver.findElement(By.xpath(value));
		countrySelectorElement.click();
		
		//Select USA
		String usaCountry = repositoryParser.getValue("userregistration.registrationform.phone.country.usa.xpath");
		WebElement optionUSACountry = webDriver.findElement(By.xpath(usaCountry));
		optionUSACountry.click();
	}

	public void addPhoneValue(String phone) {
		String value = repositoryParser.getValue("userregistration.registrationform.phone.number.xpath");
		WebElement phoneNumber = webDriver.findElement(By.xpath(value));
		phoneNumber.clear();
		phoneNumber.sendKeys(phone);
	}

	//Todo: hook to avoid captcha
	//public WebElement getCatpcha() {
	//	String value = repositoryParser.getValue("userregistration.registrationform.captcha.xpath");
	//	By captcha = By.xpath(value);

	//	return new FluentWait<WebDriver>(webDriver)
	//			.withTimeout(Duration.ofSeconds(30))
	//			.pollingEvery(Duration.ofSeconds(2))
	//			.ignoring(NoSuchElementException.class)
	//			.until(ExpectedConditions.elementToBeClickable(captcha));
	//}
	
	public void clickFormRegistrationButton() {
		String value = repositoryParser.getValue("userregistration.registrationform.registrationbutton.xpath");
		WebElement buttonFormRegistration = webDriver.findElement(By.xpath(value));
		buttonFormRegistration.click();
	}

	public WebElement getNotificationSuccess() {
		String value = repositoryParser.getValue("userregistration.mainscreen.notification.success.xpath");
		By notificationSuccess = By.xpath(value);

		return new FluentWait<WebDriver>(webDriver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(2))
				.ignoring(NoSuchElementException.class)
				.until(ExpectedConditions.elementToBeClickable(notificationSuccess));
	}

	public WebElement getErrorInvalidPassword() {
		String value = repositoryParser.getValue("userregistration.registrationform.errormessage.invalid.pass.xpath");
		By notificationSuccess = By.xpath(value);

		return new FluentWait<WebDriver>(webDriver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(2))
				.ignoring(NoSuchElementException.class)
				.until(ExpectedConditions.elementToBeClickable(notificationSuccess));
	}

	public WebElement getErrorInvalidPhone() {
		String value = repositoryParser.getValue("userregistration.registrationform.errormessage.invalid.phone.xpath");
		By notificationSuccess = By.xpath(value);

		return new FluentWait<WebDriver>(webDriver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(2))
				.ignoring(NoSuchElementException.class)
				.until(ExpectedConditions.elementToBeClickable(notificationSuccess));
	}

	public WebElement getErrorUncheckedTermAndConditions() {
		String value = repositoryParser.getValue("userregistration.registrationform.errormessage.uncheckedterms.xpath");
		By notificationSuccess = By.xpath(value);

		return new FluentWait<WebDriver>(webDriver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(2))
				.ignoring(NoSuchElementException.class)
				.until(ExpectedConditions.elementToBeClickable(notificationSuccess));
	}
	
	//Todo: Code used to simile human interaction to avoid captcha
	//public static void wait(int ms){
	//	try{
	//		Thread.sleep(ms);
	//	}
	//	catch(InterruptedException ex){
	//		Thread.currentThread().interrupt();
	//	}
	//}
}
