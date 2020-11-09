package doctorlm.MedicalHealth.handle;

import doctorlm.MedicalHealth.base.DriverBase;
import doctorlm.MedicalHealth.page.MyOrderPage;

public class MyOrderHandle {
	
	public DriverBase driver;
	public MyOrderPage myOrderPage;
	
	public MyOrderHandle(DriverBase driver) {
		this.driver = driver;
		myOrderPage = new MyOrderPage(driver);
	}
	
	
}
