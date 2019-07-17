package eql.tphotel;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;

public class Outil {
	
	
	

	public boolean VerificationIsDisplayed(WebElement element)

	{
		return element.isDisplayed();
	}

	public static WebDriver ChoisirUnNavigateur(String browser) {
		WebDriver driver;

		if (browser == "chrome") {
			System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver.exe");
			driver = new ChromeDriver();

		} else if (browser == "ie")

		{
			System.setProperty("webdriver.ie.driver", "src/main/resources/driver/IEDriverServer.exe");
			driver = new InternetExplorerDriver();

		} else // (browser == "firefox")
		{
			System.setProperty("webdriver.gecko.driver", "src/main/resources/driver/geckodriver.exe");
			driver = new FirefoxDriver();

		} // else
			// System.out.println("browser mal renseigné");

		return driver;

	}

	public static void renseignerChamp(WebElement element, String message) {
		element.clear();
		element.sendKeys(message);
	}
	public static void verificationTextWebElement(String expected, WebElement we)
	
	{
		try {
		assertEquals(expected, we.getText());}
		catch (Error e)
		{
			System.out.println("ERROR verification Text "+expected+", reel est "+we.getText());
			throw e;
		}
		
	}
	
	public void SelectOptionFromMenu (WebElement we, String option)
	{
		Select select = new Select(we);
		select.selectByValue(option);
	}


}
