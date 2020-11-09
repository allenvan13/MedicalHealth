package doctorlm.MedicalHealth.business;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.Reporter;

import doctorlm.MedicalHealth.base.DriverBase;
import doctorlm.MedicalHealth.handle.IndexPageHandle;
import doctorlm.MedicalHealth.handle.MinePageHandle;
import doctorlm.MedicalHealth.handle.MyOrderHandle;
import doctorlm.MedicalHealth.util.MyUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CheckOrder {

	public DriverBase driver;
	public IndexPageHandle indexHandle;
	public MinePageHandle mineHandle;
	public MyOrderHandle myOrderHandle;
	public BuyDrugs buyDrugs;

	private List<String> orderNum;
	private List<Float> totalPrice;
	
	private String orderNum_buyDrugs;
	private Float totalPrice_buyDrugs;
	
	public CheckOrder(DriverBase driver) {
		this.driver = driver;
		indexHandle = new IndexPageHandle(driver);
		mineHandle = new MinePageHandle(driver);
		myOrderHandle = new MyOrderHandle(driver);
		buyDrugs = new BuyDrugs(driver);
	}
	
	public void checkOrderNumber() {
		indexHandle.clickMine(); 
		driver.sleep(1);
		mineHandle.clickMyOrder();
		driver.sleep(1);
		orderNum = myOrderHandle.myOrderPage.getOrderNums();
		totalPrice = myOrderHandle.myOrderPage.getTotalPrices();
//		List<String> drugName_buyDrugs = buyDrugs.getDrugNames();
		orderNum_buyDrugs = buyDrugs.getOrderNum();
		totalPrice_buyDrugs = buyDrugs.getTotalPrice();
	}
	
	/**
	 * @Decription: 断言是否成功创建并显示订单，依据：我的订单页面中订单、价格与购买后详情页面一致
	 */
	public void assertHasCreateOrder() {
		boolean result = false;
		if (MyUtil.<Float>isContains(totalPrice, totalPrice_buyDrugs) && 
				MyUtil.<String>isContains(orderNum, orderNum_buyDrugs)) {
			 Reporter.log("查看我的订单中含该订单：订单号：["+orderNum_buyDrugs+"]，订单价格：["+totalPrice_buyDrugs+"]元");
			 result = true;
		}
		
		log.info("============断言中===============");
		assertEquals(result, true, "预期：成功创建并显示订单，实际不一致，用例失败");
		log.info("============断言结束===============");
	}
	
	public List<String> getOrderNum() {
		return orderNum;
	}

	public List<Float> getTotalPrice() {
		return totalPrice;
	}

	public String getOrderNum_buyDrugs() {
		return orderNum_buyDrugs;
	}

	public Float getTotalPrice_buyDrugs() {
		return totalPrice_buyDrugs;
	}

}
