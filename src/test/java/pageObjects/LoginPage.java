package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import repository.RepositoryParser;

import java.time.Duration;

public class LoginPage {
	private static final RepositoryParser repositoryParser = new RepositoryParser("login.page.repository.properties");

	private WebDriver webDriver;

	public LoginPage(WebDriver driver) {
		webDriver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickLoginButton() {
		String value = repositoryParser.getValue("login.header.signinbutton.xpath");
		WebElement buttonLogin = webDriver.findElement(By.xpath(value));
		buttonLogin.click();
	}

	public void clickLoginWithGmailButton() {
		String value = repositoryParser.getValue("login.socialnetwork.button.gmail.xpath");
		WebElement gmailButton = webDriver.findElement(By.xpath(value));
		gmailButton.click();
	}

	public void setEmail(String email) {
		//Wait for input, and when found set email
		String value = repositoryParser.getValue("login.socialnetwork.gmail.input.mail.findBy");
		FluentWait<WebDriver> fluentWait = new FluentWait<>(webDriver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(2))
				.ignoring(NoSuchElementException.class);
		WebElement emailGmail = fluentWait.until(ExpectedConditions.elementToBeClickable(By.name(value)));
		emailGmail.clear();
		emailGmail.sendKeys(email);
		
		//Click next to go to the password screen
		String nextButton = repositoryParser.getValue("login.socialnetwork.gmail.button.nextMail.xpath");
		WebElement nextScreenButton = webDriver.findElement(By.xpath(nextButton));
		nextScreenButton.click();
		
	}

	public void setPassword(String pwd) {
		//Wait for input, and when found set password
		String value = repositoryParser.getValue("login.socialnetwork.gmail.input.password.findBy");
		FluentWait<WebDriver> fluentWait = new FluentWait<>(webDriver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(2))
				.ignoring(NoSuchElementException.class);
		WebElement password = fluentWait.until(ExpectedConditions.elementToBeClickable(By.name(value)));
		password.clear();
		password.sendKeys(pwd);

		//Click next to login
		String nextButton = repositoryParser.getValue("login.socialnetwork.gmail.button.nextPass.xpath");
		WebElement nextScreenButton = webDriver.findElement(By.xpath(nextButton));
		nextScreenButton.click();
	}

	public WebElement getUsersAvatar() {
		String value = repositoryParser.getValue("login.userprofile.avatar.xpath");
		By userAvatar = By.xpath(value);

		return new FluentWait<WebDriver>(webDriver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(2))
				.ignoring(NoSuchElementException.class)
				.until(ExpectedConditions.elementToBeClickable(userAvatar));
	}
	
	public WebElement getUsersName() {
		String value = repositoryParser.getValue("login.userprofile.name.xpath");
		By userAvatar = By.xpath(value);

		return new FluentWait<WebDriver>(webDriver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(2))
				.ignoring(NoSuchElementException.class)
				.until(ExpectedConditions.elementToBeClickable(userAvatar));
	}
}
