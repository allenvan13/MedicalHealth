package doctorlm.MedicalHealth.handle;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import doctorlm.MedicalHealth.base.DriverBase;
import doctorlm.MedicalHealth.page.MyAddressPage;

public class MyAddressHandle {

	public DriverBase driver;
	public MyAddressPage myAddressPage;
	
	public MyAddressHandle(DriverBase driver) {
		this.driver = driver;
		myAddressPage = new MyAddressPage(driver);
	}
	
	/**
	 * @功能： 点击指定元素编辑标签
	 */
	void clickEdit(int index) {
		List<WebElement> elements = myAddressPage.getEdit();
		int size = elements.size();
		if (index < size && index >= 0) {
			Reporter.log("点击编辑第["+(index+1)+"]个地址");
			myAddressPage.click(elements.get(index));	
		}else {
			Reporter.log("索引值超出边界");
		}
	}

	/**
	 * @功能： 点击指定元素删除标签
	 */
	void clickDelete(int index) {
		List<WebElement> elements = myAddressPage.getDelete();
		int size = elements.size();
		if (index < size && index >= 0) {
			Reporter.log("点击删除第["+(index+1)+"]个地址");
			myAddressPage.click(elements.get(index));	
		}else {
			Reporter.log("索引值超出边界");
		}
	}
	
	/**
	 * @功能： 点击新增地址
	 */
	void clickAddAddress() {
		myAddressPage.click(myAddressPage.getAddAddress());
	}
	
	/**
	 * @功能： 输入收货人姓名
	 */
	void sendKeysName(String name) {
		myAddressPage.sendKeys(myAddressPage.getInputName(), name);
	}
	
	/**
	 * @功能： 输入收货人手机号码
	 */
	void sendKeysPhone(String phoneNumber) {
		myAddressPage.sendKeys(myAddressPage.getInputPhone(), phoneNumber);
	}
	
	/**
	 * @功能： 点击选择 省市区
	 */
	void clickCheckCity() {
		myAddressPage.click(myAddressPage.getCheckCity());
	}
	
	/**
	 * @功能： 点击完成-选择 省市区后提交
	 */
	void clickCheckCityDone() {
		myAddressPage.click(myAddressPage.getCheckCityDone());
	}
	
	/**
	 * @功能： 输入详细地址
	 */
	void sendKeys(String detailAddress) {
		myAddressPage.sendKeys(myAddressPage.getInputDetailAddress(), detailAddress);
	}
	
	/**
	 * @功能： 点击 设为默认地址
	 */
	void clickDefaultTarget() {
		myAddressPage.click(myAddressPage.getDefaultTarget());
	}
	
	/**
	 * @功能： 点击保存按钮
	 */
	void clickSaveButton() {
		myAddressPage.click(myAddressPage.getSaveButton());
	}
	
	/**
	 * @功能： 断言弹出Toast文本是否等于预期目标
	 */
	boolean assertMessageText(String text) {
		WebElement element = myAddressPage.explicitlyFastWait(3, "doctor", "MAE_toastMessage");
		if (element.getText().contains(text)) {
			return true;
		}else {
			return false;
		}
	}
}
