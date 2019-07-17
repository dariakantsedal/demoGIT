package eql.tphotel;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestDragDrop {
	WebDriver driver;

	String ie = "ie";
	String ff = "firefox";
	String c = "chrome";

	String url = "http://localhost/TutorialHtml5HotelPhp/";

	@Before
	public void CreerLeWait() throws Exception {

		driver = Outil.ChoisirUnNavigateur(c);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		try {
			BDDOutils.insertData("src\\main\\resources\\dbfichiers\\dataset.xml");

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
		assertEquals("[FAIL] Le title n'est pas bon", driver.getTitle(), "HTML5 Hotel Room Booking (JavaScript/PHP)");

		WebElement resa = driver.findElement(By.xpath("//div[@class='scheduler_default_event_inner']"));
		WebElement lendemain = driver.findElement(By.xpath("//*[@id=\"dp\"]/div[3]/div[3]/div/div[2]/div[31]"));

		Actions a = new Actions(driver);
		a.clickAndHold(resa).moveToElement(lendemain).release(lendemain).build().perform();
		WebElement update_msg = driver.findElement(By.xpath("//@class[.='scheduler_default_message']/..")); //////////
		//////////////////////////EXPLICIT/////////////////////////////////////////
/*		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(update_msg));
		assertTrue("FAIL pas de message update success", update_msg.isDisplayed());
		assertEquals("FAIL pas de message update success", "Update successful", update_msg.getText());
		System.out.println(update_msg.getText());

		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertFalse("FAIL message est toujours là", update_msg.isDisplayed());
	}

}
