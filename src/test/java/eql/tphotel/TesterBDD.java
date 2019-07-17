package eql.tphotel;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class TesterBDD {
	WebDriver driver;

	String ie = "ie";
	String ff = "firefox";
	String c = "chrome";

	String url = "http://localhost/TutorialHtml5HotelPhp/";

	@Before
	public void CreerLeWait() {

		driver = Outil.ChoisirUnNavigateur(c);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {
			BDDOutils.deleteAllData("/tphotel/src/main/resources/dbfichiers/suppression.xml");
		} catch (Exception e) {

			System.out.println("FAIL suppression data");
		}
		

	}

//	@After
	public void TearDown() throws Exception {
		driver.close();
		try {
			BDDOutils.deleteAllData("/tphotel/src/main/resources/dbfichiers/suppression.xml");
		} catch (Exception e) {

			System.out.println("FAIL suppression data");
			throw e;
		}
	}

	@Test
	public void tester() throws Exception {
		driver.get(url);
		BDDOutils.insertData("src/main/resources/dbfichiers/bookingprochainjour2.xml");
		
		BDDOutils.deleteAllData("src/main/resources/dbfichiers/deletepasall.xml");
		
	}

}
