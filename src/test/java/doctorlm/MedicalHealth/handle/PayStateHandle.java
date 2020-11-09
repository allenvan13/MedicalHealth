package doctorlm.MedicalHealth.handle;

import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import doctorlm.MedicalHealth.base.DriverBase;
import doctorlm.MedicalHealth.page.PayStatePage;

public class PayStateHandle {

	public DriverBase driver;
	public PayStatePage payStatePage;
	
	public PayStateHandle(DriverBase driver) {
		this.driver = driver;
		payStatePage = new PayStatePage(driver);
	}
	
	/**
	 * @Decription: 点击 已完成支付
	 */
	public void clickPayComplete() {
		payStatePage.click(payStatePage.getPayComplete());
		Reporter.log("点击 [已完成支付]");
		driver.sleep(2);
	}
	
	/**
	 * @Decription: 判断是否完成支付
	 */
	public boolean assertPaySuccess() {
		WebElement element = payStatePage.getPayComplete();
		if (element.getText().equals("已完成支付")) {
			return true;
		}else {
			return false;
		}
	}
}
