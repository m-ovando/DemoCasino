package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

	public static WebDriver getDriver() {
		return getDriver(WebDriverType.CHROME);
	}

	public static WebDriver getDriver(WebDriverType webDriverType) {

		if(WebDriverType.CHROME == webDriverType) {
			
			//TODO: make the driver autoupdate
			System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
			return new ChromeDriver();
		}

		return null;
	}

	public static enum WebDriverType {
		CHROME
	}

}
