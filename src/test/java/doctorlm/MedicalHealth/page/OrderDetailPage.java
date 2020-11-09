package doctorlm.MedicalHealth.page;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebElement;

import doctorlm.MedicalHealth.base.DriverBase;
import doctorlm.MedicalHealth.util.GetData;
/**
 * @Description:订单详情页面
 */
public class OrderDetailPage extends PageBase{

	public DriverBase driver;
	
	public OrderDetailPage(DriverBase driver) {
		super(driver);
	}
	
	/*
	 	#订单详情页面OrderDetail
		OD_orderStatus=className>orderStatus>页面顶部 支付状态显示元素
		OD_addressInfo=className>addressInfo-card-bottom>地址信息
		
		OD_productDiv=className>drugListR>组元素-商品div
		OD_drugName=className>drug-name>组元素-商品名字
		OD_drugPrice=className>drug-price-p>组元素-商品价格
		OD_drugNum=className>drug-num>组元素-每个商品的数量
		
		OD_interrogationStatus=className>interrogationStatus>问诊状态提示
		OD_checkLook=cssSelector>img[src=\'/Img/go.png\']>问诊开方-查看按钮
		OD_remarks=className>list-item-beizhu>订单备注
		
		OD_orderInfos=className>list-item>组元素订单信息：0：订单号，1：下单时间，2：商品合计金额，3：优惠金额，4：运费，5：整单佣金，6：合计金额
		OD_payType=className>payname>支付方式
		OD_goPay=xpath>//*[@id="rContainer"]/div[9]/div[2]/div>提交预约按钮 或 去支付按钮
	 */
	
	/**
	 * @获取List<WebElement>： 组元素-商品div
	 */
	public List<WebElement> getProductDivs(){
		return getElements(GetData.getBy("doctor", "OD_productDiv"));
	}
	
	/**
	 * @获取List<String>：订单下所有 商品名字
	 */
	public List<String> getDrugNames() {
		List<WebElement> elements = getElements(GetData.getBy("doctor", "OD_drugName"));
		List<String> DrugNames = new ArrayList<String>();
		try {
			for (WebElement element : elements) {
				if (null != element) {
					DrugNames.add(element.getText());
				}
			}
			return DrugNames;
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	/**
	 * @获取List<String>： 所有商品-每个商品价格
	 */
	public List<String> getDrugPrices() {
		List<WebElement> elements = getElements(GetData.getBy("doctor", "OD_drugPrice"));
		List<String> DrugPrices = new ArrayList<String>();
		try {
			for (WebElement element : elements) {
				if (null != element) {
					DrugPrices.add(element.getText().replaceAll("[ ￥¥月售笔省元]",""));
				}
			}
			return DrugPrices;
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
		
	}
	
	/**
	 * @获取List<String>：  所有商品-每个商品的数量
	 */
	public List<String> getDrugNums() {
		List<WebElement> elements = getElements(GetData.getBy("doctor", "OD_drugNum"));
		List<String> DrugNums =  new ArrayList<String>();
		try {
			for (WebElement element : elements) {
				if (null != element) {
					DrugNums.add(element.getText().replaceAll("×",""));
				}
			}
			return DrugNums;
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	/**
	 * @获取WebElement： 页面顶部 支付状态显示元素
	 */
	public WebElement getOrderStatus() {
		return getElement(GetData.getBy("doctor", "OD_orderStatus"));
	}
	
	/**
	 * @获取String： 地址信息
	 */
	public String getAddressInfo() {
		return getElement(GetData.getBy("doctor", "OD_addressInfo")).getText();
	}
	
	/**
	 * @获取WebElement： 问诊开方-查看按钮
	 */
	public WebElement getCheckLook() {
		return getElement(GetData.getBy("doctor", "OD_checkLook"));
	}
	
	/**
	 * @获取String： 订单备注 信息
	 */
	public String getRemarks() {
		return getElement(GetData.getBy("doctor", "OD_remarks")).getText();
	}
	
	/**
	 * @获取String： 支付方式
	 */
	public String getPayType() {
		return getElement(GetData.getBy("doctor", "OD_payType")).getText();
	}
	
	/**
	 * @获取WebElement： 提交预约按钮 或 去支付按钮
	 */
	public WebElement getGoPay() {
		return getElement(GetData.getBy("doctor", "OD_goPay"));
	}

	/**
	 * @获取List<WebElement>：组元素订单信息：0：订单号，1：下单时间，2：商品合计金额，3：优惠金额，
	 * 4：运费，5：整单佣金，6：合计金额
	 */
	public List<WebElement> getOrderInfos(){
		return getElements(GetData.getBy("doctor", "OD_orderInfos"));
	}
	
	/**
	 * @获取String： 订单号
	 */
	public String getOrderNum() {
		return getOrderInfos().get(0).getText();
	}
	
	/**
	 * @获取String： 下单时间
	 */
	public String getCreateTime() {
		return getOrderInfos().get(1).getText();
	}
	
	/**
	 * @获取String： 药品（只算药品）小计金额
	 */
	public String getOnlyDrugPrice() {
		return filterString(getOrderInfos().get(2).getText());
	}
	
	/**
	 * @获取String： 优惠金额
	 */
	public String getYouHuiPrice() {
		return filterString(getOrderInfos().get(3).getText());
	}
	
	/**
	 * @获取String： 运费
	 */
	public String getPostPrice() {
		return filterString(getOrderInfos().get(4).getText());
	}
	
	/**
	 * @获取String： 整单佣金
	 */
	public String getYongJinPrice() {
		return filterString(getOrderInfos().get(5).getText());
	}
	
	/**
	 * @获取String： 所有合计金额
	 */
	public String getTotalPrice() {
		return filterString(getOrderInfos().get(6).getText());
	}

}
