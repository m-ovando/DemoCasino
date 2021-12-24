package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

	public static WebDriver getDriver() {
		return getDriver(WebDriverType.CHROME);
	}

	public static WebDriver getDriver(WebDriverType webDriverType) {

		if(WebDriverType.CHROME == webDriverType) {

			ChromeOptions options = new ChromeOptions();
			options.addArguments("disable-infobars");
			options.addArguments("--disable-extensions");
			WebDriverManager.chromedriver().setup();
			return new ChromeDriver(options);
		}

		return null;
	}

	public static enum WebDriverType {
		CHROME
	}

}
