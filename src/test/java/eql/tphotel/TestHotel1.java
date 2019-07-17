package eql.tphotel;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TestHotel1 {
	WebDriver driver;

	String ie = "ie";
	String ff = "firefox";
	String c = "chrome";

	String url = "http://localhost/TutorialHtml5HotelPhp/";

	@Before
	public void CreerLeWait() throws Exception {

		driver = Outil.ChoisirUnNavigateur(c);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		try {
			BDDOutils.deleteAllData("src/main/resources/dbfichiers/suppression.xml");

		} catch (Exception e) {

			System.out.println("FAIL suppression data");
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
		WebElement bouton = driver.findElement(By.xpath("//div[@class='scheduler_default_cell'][1]"));
		Point locator = bouton.getLocation();
		bouton.click();

		driver.switchTo().frame(0);
		Outil.verificationTextWebElement("New Reservation", driver.findElement(By.xpath("//*[@id='f']/h1")));

		WebElement champ_name = driver.findElement(By.xpath("//*[@id='name']"));
		Outil.renseignerChamp(champ_name, "resa 1");
		driver.findElement(By.xpath("//*[@id=\"f\"]/div[9]/input")).click();
		driver.switchTo().defaultContent();

		WebElement resa = driver.findElement(By.xpath("//div[@class='scheduler_default_event_inner']"));
		assertTrue("FAIL cellule contains resa", resa.getText().contains("resa"));

		assertTrue("FAIL location", resa.getLocation().equals(locator));
		
		////////////////////////////////////
		try {
			BDDOutils.compareData("reservations", "src/main/resources/dbfichiers/dataset.xml", "ID");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
