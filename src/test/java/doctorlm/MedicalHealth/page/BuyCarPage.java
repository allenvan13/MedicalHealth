package doctorlm.MedicalHealth.page;
/**
 * @ClassName:BuyCarPage.java
 * @author： Administrator
 * @date: 2020 下午3:02:35
 * @Description: 购物车页面
 */

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import doctorlm.MedicalHealth.base.DriverBase;
import doctorlm.MedicalHealth.util.GetData;

public class BuyCarPage extends PageBase{
	
	public BuyCarPage(DriverBase driver) {
		super(driver);
	}
	
	/**
	 * @获取元素  左下角全选标签
	 */
	public WebElement getAllCheck() {
		return getElement(GetData.getBy("doctor","C_allCheck"));
	}
	
	/**
	 * @获取元素  每个商品左边点选checkbox 按钮，注意最后一个为全选
	 */
	public List<WebElement> getClickCheck(){
		return getElements(GetData.getBy("doctor", "C_clickChecks"));
	}
	
	/**
	 * @获取元素  右下角结算按钮
	 */
	public WebElement getAccountButton() {
		return getElement(GetData.getBy("doctor","C_accountButton"));
	}
	
	/**
	 * @获取元素  右下角立即预约按钮
	 */
	public WebElement getYuYueButton() {
		return getElement(GetData.getBy("doctor","C_yuYueButton"));
	}
	
	/**
	 * @获取元素  底部总计金额span标签
	 */
	public WebElement getTotalPrice() {
		return getElement(GetData.getBy("doctor","C_totalPrice"));
	}
	
	/**
	 * @获取元素  底部显示的优惠总金额
	 */
	public WebElement getFreePrice() {
		return getElement(GetData.getBy("doctor","C_freePrice"));
	}
	
	/**
	 * @获取元素  底部Banner医药健康
	 */
	public WebElement getYiYaoJianKang() {
		return getElement(GetData.getBy("doctor","C_yiYaoJianKang"));
	}
	
	/**
	 * @获取元素  底部Banner我的
	 */
	public WebElement getMine() {
		return getElement(GetData.getBy("doctor","C_Mine"));
	}
	
	/**
	 * @获取元素  底部处方药提示信息： 您选购的药品中包含处方药，需咨询医生开方
	 */
	public WebElement getChuFangMessage() {
		return getElement(GetData.getBy("doctor","C_chuFangMessage"));
	}
	
	/**
	 * @获取元素  顶部包邮提示信息1:不满88？与包邮商品一起下单，免运费！ 提示信息2:包邮,（满88或与包邮商品一同购买）
	 */
	public WebElement getFreePostMessage() {
		return getElement(GetData.getBy("doctor","C_freePostMessage"));
	}
	
	/**
	 * @获取元素  购物车无商品(空空如也)的页面提示
	 */
	public WebElement getEmptyMessage() {
		return getElement(GetData.getBy("doctor","C_emptyMessage"));
	}
	
	/**
	 * @获取元素  右上角[编辑]标签
	 */
	public WebElement getEdit() {
		return getElement(GetData.getBy("doctor","C_edit"));
	}
	
	/**
	 * @获取元素  右上角[取消]编辑标签
	 */
	public WebElement getEditCancel() {
		return getElement(GetData.getBy("doctor","C_editCancel"));
	}
	
	/**
	 * @获取元素  右上角[删除]编辑标签
	 */
	public WebElement getDelete() {
		return getElement(GetData.getBy("doctor","C_delete"));
	}
	
	/**
	 * @获取元素  确认删除按钮
	 */
	public WebElement getDeleteYes() {
		return getElement(GetData.getBy("doctor","C_deleteYes"));
	}
	
	/**
	 * @获取元素  取消删除按钮
	 */
	public WebElement getDeleteNo() {
		return getElement(GetData.getBy("doctor","C_deleteNo"));
	}
	
	/**
	 * @获取元素  右上角[去凑单]标签 
	 */
	public WebElement getCouDan() {
		return getElement(GetData.getBy("doctor","C_couDan"));
	}
	
	/**
	 * @获取元素  购物车中商品div-父级元素，用于定位子元素（价格、数量、名字、处方药标签等）
	 */
	public List<WebElement> getAllProduct(){
		return getElements(GetData.getBy("doctor","C_productCounts"));
	}
	
	/**
	 * @获取元素  购物车中商品名字
	 */
	public List<WebElement> getProductNameDiv(){
		return getElements(GetData.getBy("doctor","C_productNames"));
	}
	
	public List<String> getProductName(){
		List<WebElement> elements = getProductNameDiv();
		List<String> names = new ArrayList<String>();
		for (WebElement element : elements) {
			names.add(element.getText());
		}
		return names;
	}
	
	/**
	 * @Decription: 获取每个商品价格元素的父元素，用于定位子元素 每个商品价格
	 */
	public List<WebElement> getPriceFatherElements(){
		return getElements(GetData.getBy("doctor","C_productPrices_father"));
	}
	
	/**
	 * @Decription: 通过商品div获取具体某个商品的价格
	 */
	public WebElement getProductPrice(int index) {
		return getChildrenElement(getPriceFatherElements(), GetData.getBy("doctor", "C_productPrice_son"), index);
	}
	
	/**
	 * @Decription: 获取加减号的父级元素
	 */
	public List<WebElement> getAddCutFatherElements(){
		return getElements(GetData.getBy("doctor","C_clickAddAndCut_father"));
	}
	
	/**
	 * @Decription: 通过加减号的父级元素获取具体某个（index）商品的加元素
	 * @param int index 第几个元素的加号
	 */
	public WebElement getAdd(int index) {
		return getChildrenElement(getPriceFatherElements().get(index), GetData.getBy("doctor", "C_clickaddAndCut_son"), 1);
	}
	
	/**
	 * @Decription: 通过加减号的父级元素获取具体某个（index）商品的减元素
	 * @param int index 第几个元素的减号
	 */
	public WebElement getCut(int index) {
		return getChildrenElement(getPriceFatherElements().get(index), GetData.getBy("doctor", "C_clickaddAndCut_son"), 0);
	}
}
