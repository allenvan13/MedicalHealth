package doctorlm.MedicalHealth.handle;

import doctorlm.MedicalHealth.base.DriverBase;
import doctorlm.MedicalHealth.page.BuyCarPage;

public class BuyCarHandle {
	
	public DriverBase driver;
	public BuyCarPage buyCarPage;
	
	public BuyCarHandle(DriverBase driver) {
		this.driver = driver;
	}
	
	/**
	 * @Decription: 点击编辑
	 */
	public void clickEdit() {
		buyCarPage.click(buyCarPage.getEdit());
	}

	/**
	 * @Decription:  点击取消编辑
	 */
	public void clickEditCancel() {
		buyCarPage.click(buyCarPage.getEditCancel());
	}

	/**
	 * @Decription: 点击删除
	 */
	public void clickDelete() {
		buyCarPage.click(buyCarPage.getDelete());
	}

	/**
	 * @Decription: 点击确认删除
	 */
	public void clickDeleteYes() {
		buyCarPage.click(buyCarPage.getDeleteYes());
	}

	/**
	 * @Decription:  点击取消删除
	 */
	public void clickDeleteNo() {
		buyCarPage.click(buyCarPage.getDeleteNo());
	}

	/**
	 * @Decription: 点击底部Banner 医药健康
	 */
	public void clickYiYaoJianKang() {
		buyCarPage.click(buyCarPage.getYiYaoJianKang());
	}

	/**
	 * @Decription: 点击底部Banner 我的
	 */
	public void clickMine() {
		buyCarPage.click(buyCarPage.getMine());
	}

	/**
	 * @Decription: 点选左下角 全选
	 */
	public void clickAllCheck() {
		buyCarPage.click(buyCarPage.getAllCheck());
	}

	/**
	 * @Decription: 点击某个商品checkbox
	 */
	public void click(int index) {
		buyCarPage.click(buyCarPage.getClickCheck().get(index));
	}
	
	/**
	 * @Decription: 点击立即预约
	 */
	public void clickYuYueButton() {
		buyCarPage.click(buyCarPage.getYuYueButton());
	}
	
	/**
	 * @Decription: 点击结算
	 */
	public void clickAccountButton() {
		buyCarPage.click(buyCarPage.getAccountButton());
	}
	
	/**
	 * @Decription: 点击 第几个元素的加号
	 */
	public void clickAdd(int index) {
		buyCarPage.click(buyCarPage.getAdd(index));
	}
	
	/**
	 * @Decription: 点击 第几个元素的减号
	 */
	public void clickCut(int index) {
		buyCarPage.click(buyCarPage.getCut(index));
	}
}
