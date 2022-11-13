package co.com.utest.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;



public class DriverRemoteBrowser {

	static String driverPathFirefox = ".\\src\\test\\resources\\drivers\\geckodriver.exe";
	static String driverPathChrome = ".\\src\\test\\resources\\drivers\\chromedriver.exe";
	static String driverPathEdge = ".\\src\\test\\resources\\drivers\\msedgedriver.exe";
	public static WebDriver driver;

	public static DriverRemoteBrowser HisBrowserWeb(String navegador) {
		if (navegador.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", driverPathChrome);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			options.addArguments("--ignore-certificate-errors");
			options.addArguments("--disable-infobars");
			driver = new ChromeDriver(options);
			return new DriverRemoteBrowser();

		}
		else if (navegador.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", driverPathFirefox);
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", true);
			driver = new FirefoxDriver(capabilities);
			return new DriverRemoteBrowser();

		}

		else if (navegador.equalsIgnoreCase("Edge")) {
			System.setProperty("webdriver.edge.driver", driverPathEdge);

			EdgeOptions options = new EdgeOptions();
			//options.addArguments("start-maximized");
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			return new DriverRemoteBrowser();

		}

		else {
			//throw new Exception("navegador incorrecto");
			System.out.println("navegador incorrecto, utiliza:");
			System.out.println("chrome");
			System.out.println("firefox");
			System.out.println("Edge");
			return new DriverRemoteBrowser();
		}
	}


	public static WebDriver on(String url) {
		driver.get(url);
		return driver;
	}
}
