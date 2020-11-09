package doctorlm.MedicalHealth.handle;

import doctorlm.MedicalHealth.base.DriverBase;
import doctorlm.MedicalHealth.page.OrderDetailPage;

public class OrderDetailHandle {

	public DriverBase driver;
	public OrderDetailPage orderDetailPage;
	
	public OrderDetailHandle(DriverBase driver) {
		this.driver = driver;
		orderDetailPage = new OrderDetailPage(driver);
	}
	
	/**
	 * @Decription: 点击-查看 （问诊开方记录）
	 */
	public void clickCheckLook() {
		orderDetailPage.click(orderDetailPage.getCheckLook());
	}
	
	/**
	 * @Decription: 点击-提交预约或去支付按钮
	 */
	public void clickGoPay() {
		orderDetailPage.click(orderDetailPage.getGoPay());
	}
	
	/**
	 * @Decription: 断言是否支付成功
	 */
	public boolean assertPaySuccess() {
		boolean result = false;
		
		
		return result;
	}
}
