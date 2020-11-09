package doctorlm.MedicalHealth.page;

import java.util.List;

import org.openqa.selenium.WebElement;

import doctorlm.MedicalHealth.base.DriverBase;
import doctorlm.MedicalHealth.util.GetData;

public class MyAddressPage extends PageBase{
	
	public MyAddressPage(DriverBase driver) {
		super(driver);
	}
	
	/* 
	   	元素信息
	 
	 	#我的地址 My Address
		MA_name=className>name>收货人姓名-组元素
		MA_address=className>detail>收货地址-组元素
		MA_default=className>default>默认标签-组元素，默认应为为第一个（0）其余显示为none
		MA_edit=className>edit>编辑标签-组元素
		MA_delete=className>delete>删除标签-组元素
		MA_deleteNo=className>r-mask-btn1>删除弹窗-取消按钮
		MA_deleteYes=className>r-mask-btn2>删除弹窗-确认按钮
		MA_addAddress=className>add>新增地址按钮
		MA_backPage=className>r-nav-back>左上角返回图标
		MA_toastDeleteAddress=删除地址成功
		#我的地址子页面-编辑地址My Address Edit
		MAE_inputName=id>inputName>子页面-收货人姓名
		MAE_inputPhone=id>phone>子页面-收货人手机号码
		MAE_checkCity=id>start>子页面-选择省份及城市
		MAE_checkCityDone=className>picker-button>子页面-完成标签-选择城市提交
		MAE_inputDetailAddress=id>area>子页面-详细地址
		MAE_default=id>onDefault>子页面-默认地址图标,2个图标切换
		MAE_noDefault=id>nonDefault>子页面-不是默认地址图标
		MAE_saveButton=className>save_default>子页面-保存按钮
		MAE_toastMessage=xpath>//*[@id="showToast"]/div/div[2]>Toast子页面-提示信息元素
		MAE_toastName=请填写您的姓名
		MAE_toastPhone=请填写您的手机号码
		MAE_toastWrongPhone=手机号码有误
		MAE_toastCity=请选择省份及城市
		MAE_toastDetailA的dress=请填写详细地址
		MAE_toastEditAddress=编辑地址成功
		MAE_toastAddSucess=添加地址成功
	 */
	
	/**
	 * @获取元素： 收货人姓名-组元素
	 */
	public List<WebElement> getName(){
		return getElements(GetData.getBy("doctor", "MA_name"));
	}

	/**
	 * @获取元素： 收货地址-组元素
	 */
	public List<WebElement> getAddress(){
		return getElements(GetData.getBy("doctor", "MA_address"));
	}

	/**
	 * @获取元素： 默认标签-组元素，默认应为为第一个（0）其余显示为none
	 */
	public List<WebElement> getDefault(){
		return getElements(GetData.getBy("doctor", "MA_default"));
	}

	/**
	 * @获取元素： 编辑标签-组元素
	 */
	public List<WebElement> getEdit(){
		return getElements(GetData.getBy("doctor", "MA_edit"));
	}

	/**
	 * @获取元素： 删除标签-组元素
	 */
	public List<WebElement> getDelete(){
		return getElements(GetData.getBy("doctor", "MA_delete"));
	}
	
	/**
	 * @获取元素： 删除弹窗-取消按钮
	 */
	public WebElement getDeleteNo() {
		return getElement(GetData.getBy("doctor", "MA_deleteNo"));
	}

	/**
	 * @获取元素： 删除弹窗-确认按钮
	 */
	public WebElement getDeleteYes() {
		return getElement(GetData.getBy("doctor", "MA_deleteYes"));
	}

	/**
	 * @获取元素： 新增地址按钮
	 */
	public WebElement getAddAddress() {
		return getElement(GetData.getBy("doctor", "MA_addAddress"));
	}

	/**
	 * @获取元素： 左上角返回图标
	 */
	public WebElement getBackPage() {
		return getElement(GetData.getBy("doctor", "MA_backPage"));
	}

	/**
	 * @获取元素： 子页面-收货人姓名
	 */
	public WebElement getInputName() {
		return getElement(GetData.getBy("doctor", "MAE_inputName"));
	}
	
	/**
	 * @获取元素： 子页面-收货人手机号码
	 */
	public WebElement getInputPhone() {
		return getElement(GetData.getBy("doctor", "MAE_inputPhone"));
	}
	
	/**
	 * @获取元素： 子页面-选择省份及城市
	 */
	public WebElement getCheckCity() {
		return getElement(GetData.getBy("doctor", "MAE_checkCity"));
	}
	
	/**
	 * @获取元素： 子页面-完成标签-选择城市提交
	 */
	public WebElement getCheckCityDone() {
		return getElement(GetData.getBy("doctor", "MAE_checkCityDone"));
	}
	
	/**
	 * @获取元素： 子页面-详细地址
	 */
	public WebElement getInputDetailAddress() {
		return getElement(GetData.getBy("doctor", "MAE_inputDetailAddress"));
	}
	
	/**
	 * @获取元素： 子页面-默认地址图标,2个图标切换
	 */
	public WebElement getDefaultTarget() {
		return getElement(GetData.getBy("doctor", "MAE_default"));
	}
	
	/**
	 * @获取元素： 子页面-不是默认地址图标
	 */
	public WebElement getNoDefaultTarget() {
		return getElement(GetData.getBy("doctor", "MAE_noDefault"));
	}
	
	/**
	 * @获取元素： 子页面-保存按钮
	 */
	public WebElement getSaveButton() {
		return getElement(GetData.getBy("doctor", "MAE_saveButton"));
	}
	
	/**
	 * @获取元素： 子页面-提示信息元素
	 */
	public WebElement getToastMessage() {
		return getElement(GetData.getBy("doctor", "MAE_toastMessage"));
	}
}
