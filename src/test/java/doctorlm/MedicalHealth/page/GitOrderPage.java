package doctorlm.MedicalHealth.page;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebElement;

import doctorlm.MedicalHealth.base.DriverBase;
import doctorlm.MedicalHealth.util.GetData;

public class GitOrderPage extends PageBase{

	public DriverBase driver;
	
	public GitOrderPage(DriverBase driver) {
		super(driver);
	}
	
	/*
	 	#提交订单页面元素  GitOrder
		GO_payNotice=className>page_header>顶部提示信息：请在10分钟内付款，否则可能因库存不足无法购买
		GO_address=className>multiple_hidden>地址信息
		GO_nameAndPhone=className>addressBoxB>姓名和电话
		GO_payType=className>payTypeText>支付方式
		GO_xiaoJiPrice=xpath>//*[@id="content"]/div[9]/div[1]/div[2]>商品小计
		GO_freePrice=xpath>//*[@id="content"]/div[9]/div[2]/div[2]>优惠金额
		GO_postPrice=xpath>//*[@id="content"]/div[9]/div[3]/div[2]>运费金额
		GO_yongJinPrice=xpath>//*[@id="content"]/div[9]/div[4]/div[2]>佣金金额
		GO_selectImgNav=className>selectImgNav>我已知晓勾选状态，默认勾选
		GO_acceptContract=className>tips-options-box>我已知晓
		GO_leaveMessage=className>msg>买家留言
		GO_totalPrice=className>totalPriceByBottomNum>左下角合计金额
		GO_goPay=xpath>//*[@id=\"gopay\"]/span>提交预约/去支付 按钮
		GO_payTypeChoice=className>pay-list-r>组元素，0为微信，1为支付宝
		GO_ProductPrices=className>price>组元素 商品价格显示
		GO_ProductNum=className>drug-num>组元素 商品数量显示
		GO_ChuFang=className>chufang>组元素 处方药标签
		GO_ProductName=className>title>组元素 商品名字
	 */
	
	/**
	 * @获取List<String> 每个商品名字
	 */
	public List<String> getProductNames(){
		List<WebElement> elements = getElements(GetData.getBy("doctor", "GO_ProductName"));
		List<String> names = new ArrayList<String>();
		
		for (WebElement element : elements) {
			if (element != null) {
				names.add(element.getText());
			}
		}
		return names;
	}
	
	/**
	 * @获取指定商品的名字String，索引值不正确就默认获取第一个商品的名字
	 */
	public String getProductNames(int index){
		List<WebElement> elements = getElements(GetData.getBy("doctor", "GO_ProductName"));
		if (index < elements.size()) {
			return elements.get(index).getText();
		}else {
			//其他任何参数默认传第一个商品
			System.out.println("传参超出边界，默认返第一个");
			return elements.get(0).getText();
		}
	}

	/**
	 * @获取List<String>：每个商品数量显示
	 */
	public List<String> getProductNums(){
		List<WebElement> elements = getElements(GetData.getBy("doctor", "GO_ProductNum"));
		List<String> ProductNums =  new ArrayList<String>();
		try {
			for (WebElement element : elements) {
				if (element != null) {
					ProductNums.add(element.getText().replaceAll("x",""));
				}
			}
			return ProductNums;
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	/**
	 * @获取List<String>： 每个商品价格显示
	 */
	public List<String> getProductPrices(){
		List<WebElement> elements = getElements(GetData.getBy("doctor", "GO_ProductPrices"));
		List<String> ProductPrices =  new ArrayList<String>();
		try {
			for (WebElement element : elements) {
				if (element != null) {
					ProductPrices.add(element.getText().replaceAll("[ ￥¥月售笔省元]",""));
				}
			}
			return ProductPrices;
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	/**
	 * @获取String： 地址信息
	 */
	public String getAddress() {
		return getElement(GetData.getBy("doctor", "GO_address")).getText();
	}

	/**
	 * @获取String： 姓名和电话
	 */
	public String getNameAndPhone() {
		return getElement(GetData.getBy("doctor", "GO_nameAndPhone")).getText();
	}

	/**
	 * @获取WebElement： 我已知晓勾选状态，默认勾选
	 */
	public WebElement getAcceptContract() {
		return getElement(GetData.getBy("doctor", "GO_acceptContract"));
	}

	/**
	 * @获取String： 商品小计金额
	 */
	public String getXiaoJiPrice() {
		return getElement(GetData.getBy("doctor", "GO_xiaoJiPrice")).getText();
	}

	/**
	 * @获取String： 优惠金额
	 */
	public String getFreePrice() {
		return getElement(GetData.getBy("doctor", "GO_freePrice")).getText();
	}

	/**
	 * @获取String： 运费金额
	 */
	public String getPostPrice() {
		return getElement(GetData.getBy("doctor", "GO_postPrice")).getText();
	}

	/**
	 * @获取String： 佣金金额
	 */
	public String getYongJinPrice() {
		return getElement(GetData.getBy("doctor", "GO_yongJinPrice")).getText();
	}
	
	/**
	 * @获取String： 左下角合计金额
	 */
	public String getTotalPrice() {
		return getElement(GetData.getBy("doctor", "GO_totalPrice")).getText();
	}

	/**
	 * @获取WebElement： 买家留言
	 */
	public WebElement getLeaveMessage() {
		return getElement(GetData.getBy("doctor", "GO_leaveMessage"));
	}

	/**
	 * @获取WebElement： 提交预约 按钮
	 */
	public WebElement getGoPay() {
		return getElement(GetData.getBy("doctor", "GO_goPay"));
	}
	
	/**
	 * @获取List<WebElement> ： 组元素，0为微信，1为支付宝
	 */
	public List<WebElement> getPayTypeChoice(){
		return getElements(GetData.getBy("doctor", "GO_payTypeChoice"));
	}

	/**
	 * @获取WebElement： 支付方式
	 */
	public WebElement getPayType() {
		return getElement(GetData.getBy("doctor", "GO_payType"));
	}

}
