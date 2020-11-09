package doctorlm.MedicalHealth.test;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import doctorlm.MedicalHealth.base.DriverBase;
import doctorlm.MedicalHealth.util.GetData;

public class CaseBase {

	public DriverBase driver;
	
	public DriverBase InitDriverBase(String browser) {
		return new DriverBase(browser);
    }
	
	@BeforeTest
	public void before() {
		driver = InitDriverBase("AppChrome");
		String url = GetData.getConfigData("config", "url_test");
		driver.get(url);
		
		driver.implicitlyWait(5);
	}
	
	@AfterTest
	public void after() {
		driver.stopDriver();
	}
	
	
}
