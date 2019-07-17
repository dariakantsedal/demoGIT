package eql.tphotel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class TestMoseHover {

	WebDriver driver;

	String ie = "ie";
	String ff = "firefox";
	String c = "chrome";

	String url = "http://localhost/TutorialHtml5HotelPhp/";

	@Before
	public void CreerLeWait() throws Exception {

		driver = Outil.ChoisirUnNavigateur(c);
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

		try {
			BDDOutils.insertData("src\\main\\resources\\dbfichiers\\bookingprochainjour.xml");

		} catch (Exception e) {
			System.out.println("Insertion n'as pas marché");
			throw e;
		}

	}

	@After
	public void TearDown() throws Exception {
		driver.close();
		try {
			BDDOutils.deleteAllData("src/main/resources/dbfichiers/suppression.xml");

		} catch (Exception e) {

			System.out.println("FAIL suppression data 2");
			throw e;
		}
	}

	@Test
	public void tester() {
		driver.get(url);
		WebElement resa = driver.findElement(By.xpath("//div[@class='scheduler_default_event_inner']"));
		Actions a = new Actions(driver);
		a.moveToElement(resa).build().perform();
		a.moveToElement(resa)
				.moveToElement(driver.findElement(By.xpath("//div[@class='scheduler_default_event_delete']"))).click()
				.build().perform();

		WebElement update_msg = driver.findElement(By.xpath("//@class[.='scheduler_default_message']/.."));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue("FAIL pas de message update success", update_msg.isDisplayed());
		assertEquals("FAIL pas de message update success", "Deleted.", update_msg.getText());
		
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertFalse("FAIL message est toujours là", update_msg.isDisplayed());
		// a.moveToElement(resa).moveToElement(driver.findElement(By.xpath("//div[@class='scheduler_default_event_delete']"))).click().build().perform();;

	}

}
