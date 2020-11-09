package doctorlm.MedicalHealth.page;

import org.openqa.selenium.WebElement;

import doctorlm.MedicalHealth.base.DriverBase;

public class PayStatePage extends PageBase{

	public DriverBase driver;
	
	public PayStatePage(DriverBase driver) {
		super(driver);
	}
	
	/**
	 * @Decription: 获取元素  弹窗确认 已完成支付按钮
	 */
	public WebElement getPayComplete() {
		return explicitlyWait(20, "doctor", "PS_payComplete");
	}
}
