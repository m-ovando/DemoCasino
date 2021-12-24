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

public class DepositPage {
	private static final RepositoryParser repositoryParserDeposit = new RepositoryParser("deposit.page.repository.properties");
	private static final RepositoryParser repositoryParserLogin = new RepositoryParser("login.page.repository.properties");

	private WebDriver webDriver;

	public DepositPage(WebDriver driver) {
		webDriver = driver;
		PageFactory.initElements(driver, this);
	}

	public void openUserProfile() {
		String value = repositoryParserLogin.getValue("login.userprofile.avatar.xpath");
		By buttonAddFolder = By.xpath(value);

		new FluentWait<WebDriver>(webDriver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(2))
				.ignoring(NoSuchElementException.class)
				.until(ExpectedConditions.elementToBeClickable(buttonAddFolder))
				.click();
	}

	public WebElement getProfileMenu() {
		String value = repositoryParserDeposit.getValue("deposit.userprofile.menu.xpath");
		By profileMenu = By.xpath(value);

		return new FluentWait<WebDriver>(webDriver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(2))
				.ignoring(NoSuchElementException.class)
				.until(ExpectedConditions.elementToBeClickable(profileMenu));
	}

	public void openDepositMenu() {
		//Expand profile menu
		WebElement profileMenu = getProfileMenu();
		profileMenu.click();
		
		//Click Deposit option
		String value = repositoryParserDeposit.getValue("deposit.userprofile.menu.deposit.xpath");
		WebElement depositMenu = webDriver.findElement(By.xpath(value));
		depositMenu.click();
	}

	public void clickManualDepositButton() {
		String value = repositoryParserDeposit.getValue("deposit.userprofile.deposit.manualdeposit.button.xpath");
		By manualDepositButton = By.xpath(value);

		new FluentWait<WebDriver>(webDriver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(2))
				.ignoring(NoSuchElementException.class)
				.until(ExpectedConditions.elementToBeClickable(manualDepositButton))
				.click();
	}

	public WebElement getAmountInput() {
		String value = repositoryParserDeposit.getValue("deposit.userprofile.deposit.manualdeposit.amount.xpath");
		By inputAmount = By.xpath(value);

		return new FluentWait<WebDriver>(webDriver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(2))
				.ignoring(NoSuchElementException.class)
				.until(ExpectedConditions.elementToBeClickable(inputAmount));
	}
	
	public void setAmount(String amount) {
		WebElement amountInput = getAmountInput();
		amountInput.clear();
		amountInput.sendKeys(amount);
	}
	
	public void clickDepositButton() {
		String value = repositoryParserDeposit.getValue("deposit.userprofile.deposit.manualdeposit.deposit.button.xpath");
		By depositButton = By.xpath(value);

		new FluentWait<WebDriver>(webDriver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(2))
				.ignoring(NoSuchElementException.class)
				.until(ExpectedConditions.elementToBeClickable(depositButton))
				.click();
	}

	public WebElement getSuccessfulMessageDeposit() {
		String value = repositoryParserDeposit.getValue("deposit.userprofile.deposit.manualdeposit.success.xpath");
		By successDeposit = By.xpath(value);

		return new FluentWait<WebDriver>(webDriver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(2))
				.ignoring(NoSuchElementException.class)
				.until(ExpectedConditions.elementToBeClickable(successDeposit));
	}
	public WebElement getUnsuccessfulMessageDeposit() {
		String value = repositoryParserDeposit.getValue("deposit.userprofile.deposit.manualdeposit.error.xpath");
		By errorDeposit = By.xpath(value);

		return new FluentWait<WebDriver>(webDriver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(2))
				.ignoring(NoSuchElementException.class)
				.until(ExpectedConditions.elementToBeClickable(errorDeposit));
	}
}
