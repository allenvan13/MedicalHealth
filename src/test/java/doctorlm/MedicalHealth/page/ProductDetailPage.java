package doctorlm.MedicalHealth.page;

import org.openqa.selenium.WebElement;

import doctorlm.MedicalHealth.base.DriverBase;
import doctorlm.MedicalHealth.util.GetData;

public class ProductDetailPage extends PageBase{
	
	public ProductDetailPage(DriverBase driver) {
		super(driver);
	}
	
	/**
	 * @获取元素 商品名称
	 */
	public WebElement getDrugName() {
		return explicitlyWait(3, "doctor","DD_drugName");
	}
	
	/**
	 * @获取元素 商品价格
	 */
	public WebElement getDrugPrice() {
		return explicitlyWait(3, "doctor","DD_drugPrice");
	}

	/**
	 * @获取元素 省XX 优惠价格
	 */
	public WebElement getDrugFreePrice() {
		return explicitlyWait(3, "doctor","DD_drugFreePrice");
	}

	/**
	 * @获取元素 月售XX笔 元素
	 */
	public WebElement getDrugSaleNum() {
		return explicitlyWait(3, "doctor","DD_drugSaleNum");
	}
	
	/*
	 * 获取元素 数量+按钮
	 * */
	public WebElement getAddElement() {
		return getElement(GetData.getBy("doctor","DD_add"));
	}

	/*
	 * 获取元素 数量-按钮
	 * */
	public WebElement getCutElement() {
		return getElement(GetData.getBy("doctor","DD_cut"));
	}
	
	/*
	 * 获取 客服图标
	 * */
	public WebElement getKeFu() {
		return getElement(GetData.getBy("doctor","DD_keFu"));
	}
	
	/*
	 * 获取 收藏图标
	 * */
	public WebElement getCollect() {
		return getElement(GetData.getBy("doctor","DD_collect"));
	}
	
	/*
	 * 获取 加购物车图标-非处方药
	 * */
	public WebElement getAddCarNoChufang() {
//		return getElement(GetData.getBy("doctor","DD_addCarNoChufang"));
		return explicitlyWait(3, "doctor","DD_addCarNoChufang");
	}
	
	/*
	 * 获取 加购物车图标-处方药
	 * */
	public WebElement getAddCarChufang() {
//		return getElement(GetData.getBy("doctor","DD_addCarChufang"));
		return explicitlyWait(3, "doctor","DD_addCarChufang");
	}
	
	/*
	 * 获取分享按钮
	 * */
	public WebElement getFenXiang() {
		return getElement(GetData.getBy("doctor","DD_fenXiang"));
	}
	
	/*
	 * 获取购买按钮
	 * */
	public WebElement getBuyButton() {
		return getElement(GetData.getBy("doctor","DD_buy"));
	}
	
	/*
	 * 获取处方药信息提示底部标签
	 * */
	public WebElement getChuFangMessage() {
		return getElement(GetData.getBy("doctor","DD_chuFangMessage"));
	}
	
	/*
	 * 获取处方药属性标签
	 * */
	public WebElement getChuFangTag() {
//		return getElement(GetData.getBy("doctor","DD_chuFangTag"));
		return explicitlyWait(3, "doctor", "DD_chuFangTag");
	}
	
	/*
	 * 获取立即预约按钮
	 * */
	public WebElement getYuYueButton() {
		return getElement(GetData.getBy("doctor","DD_yuYueButton"));
	}
	
	/*
	 * 获取悬浮购物车按钮
	 * */
	public WebElement getGoodCar() {
		return getElement(GetData.getBy("doctor","DD_goodCar"));
	}
	
}
