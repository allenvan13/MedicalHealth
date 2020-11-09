package doctorlm.MedicalHealth.page;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebElement;

import doctorlm.MedicalHealth.base.DriverBase;
import doctorlm.MedicalHealth.util.GetData;

public class MyOrderPage extends PageBase{
	
	public DriverBase driver;
	
	public MyOrderPage(DriverBase driver) {
		super(driver);
	}
	
	/**
	 * @Decription: 获取订单编号
	 */
	public List<String> getOrderNums() {
		List<WebElement> elements = getElements(GetData.getBy("doctor", "MO_orderNums"));
		List<String> orderNum =  new ArrayList<String>();
		try {
			for (WebElement element : elements) {
				if (element != null) {
					orderNum.add(element.getText().replaceAll("[ ￥¥No.月售笔省元]",""));
				}
			}
			return orderNum;
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	/**
	 * @Decription: 获取下单时间
	 */
	public List<String> getCreatTimes() {
		List<WebElement> elements = getElements(GetData.getBy("doctor", "MO_createTime"));
		List<String> orderNum =  new ArrayList<String>();
		try {
			for (WebElement element : elements) {
				if (element != null) {
					orderNum.add(element.getText().replaceAll("下单时间","").substring(1));
				}
			}
			return orderNum;
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	/**
	 * @Decription: 获取每个订单合计金额
	 */
	public List<Float> getTotalPrices() {
		List<WebElement> elements = getElements(GetData.getBy("doctor", "MO_orderTotalPrice"));
		List<Float> totalPrice =  new ArrayList<Float>();
		try {
			for (WebElement element : elements) {
				if (element != null) {
					String price = element.getText().replaceAll("[ ￥¥No.月售笔省元]","");
					totalPrice.add(Float.valueOf(price));
				}
			}
			return totalPrice;
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
}
