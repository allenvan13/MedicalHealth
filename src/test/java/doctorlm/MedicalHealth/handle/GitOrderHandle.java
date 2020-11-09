package doctorlm.MedicalHealth.handle;

import org.testng.Reporter;

import doctorlm.MedicalHealth.base.DriverBase;
import doctorlm.MedicalHealth.page.GitOrderPage;

public class GitOrderHandle {

	public DriverBase driver;
	public GitOrderPage gitOrderPage;
	
	public GitOrderHandle(DriverBase driver) {
		this.driver = driver;
		gitOrderPage = new GitOrderPage(driver);
	}
	
	/**
	 * @功能： 点击地址信息 跳转我的地址页面
	 */
	public void clickChangeAddress() {
		gitOrderPage.click(gitOrderPage.getAddress());
	}
	
	/**
	 * @功能： 点击支付方式
	 */
	public void clickPayType() {
		gitOrderPage.click(gitOrderPage.getPayType());
	}
	
	/**
	 * @功能： 点击我已知晓 相关条约
	 */
	public void clickAcceptContract() {
		gitOrderPage.click(gitOrderPage.getAcceptContract());
	}

	/**
	 * @功能： 点击 去支付/提交预约
	 */
	public void clickGoPay() {
		gitOrderPage.click(gitOrderPage.getGoPay());
		Reporter.log("点击去支付/提交预约");
	}

	/**
	 * @功能： 点击微信支付
	 */
	public void clickWechatPay() {
		gitOrderPage.click(gitOrderPage.getWechatPay());
	}

	/**
	 * @功能： 点击支付宝支付
	 */
	public void clickAliPay() {
		gitOrderPage.click(gitOrderPage.getAliPay());
	}

	/**
	 * @功能： 输入留言信息-买家留言
	 */
	public void sendKeysLeaveMessage(String message) {
		gitOrderPage.sendKeys(gitOrderPage.getLeaveMessage(), message);
	}
}
