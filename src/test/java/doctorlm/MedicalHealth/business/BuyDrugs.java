package doctorlm.MedicalHealth.business;

import static org.testng.Assert.assertEquals;
import java.util.List;
import org.testng.Reporter;
import doctorlm.MedicalHealth.base.DriverBase;
import doctorlm.MedicalHealth.handle.CategoryHandle;
import doctorlm.MedicalHealth.handle.ChuFangHandle;
import doctorlm.MedicalHealth.handle.GitOrderHandle;
import doctorlm.MedicalHealth.handle.IndexPageHandle;
import doctorlm.MedicalHealth.handle.PayStateHandle;
import doctorlm.MedicalHealth.handle.ProductDetailHandle;
import doctorlm.MedicalHealth.page.ChuFangInfoPage;
import doctorlm.MedicalHealth.page.OrderDetailPage;
import doctorlm.MedicalHealth.util.GetData;
import doctorlm.MedicalHealth.util.MyUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BuyDrugs {
	
	public static String IMG_PATH_001 = System.getProperty("user.dir")+"\\img\\1.jpg";
	public static String IMG_PATH_002 = System.getProperty("user.dir")+"\\img\\2.jpg";
	public static String IMG_PATH_003 = System.getProperty("user.dir")+"\\img\\3.jpg";
	
	private String orderNum;   
	private List<String> drugNames;
	private String totalPrice;
	private String createTime;
	private String onlyDrugPrice;
	private String freePrice;
	private String postPrice;
	
	public DriverBase driver;
	public IndexPageHandle indexHandle;
	public CategoryHandle categoryHandle;
	public ProductDetailHandle proDetailHandle;
	public ChuFangHandle chufangHandle;
	public GitOrderHandle gitOrderHandle;
	public PayStateHandle payStateHandle;
	public OrderDetailPage orderDetailPage;
	public ChuFangInfoPage chuFangInfoPage;
	
	public BuyDrugs(DriverBase driver) {
		this.driver = driver;
		indexHandle = new IndexPageHandle(driver);
		categoryHandle = new CategoryHandle(driver);
		proDetailHandle = new ProductDetailHandle(driver);
		chufangHandle = new ChuFangHandle(driver);
		gitOrderHandle = new GitOrderHandle(driver);
		payStateHandle = new PayStateHandle(driver);
		orderDetailPage = new OrderDetailPage(driver);
		chuFangInfoPage = new ChuFangInfoPage(driver);
	}
	
	public String getOrderNum() {
		return orderNum;
	}

	public List<String> getDrugNames() {
		return drugNames;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public String getCreateTime() {
		return createTime;
	}
	
	/**
	 * @Decription: 从分类 测试分类_1_HOT 中筛选非处方药，随机进1款商品 详情页点击购买
	 */
	public void buyGeneralDrugs() {
		
		indexHandle.clickCategory(8);						//点击指定分类
		
		categoryHandle.clickScreenButton();					//点击筛选
		categoryHandle.clickScreenNoChufang();				//点击非处方
		categoryHandle.clickProductRandom();				//随机点击某商品
		
		proDetailHandle.clickBuyButton();					//点击购买
		
		this.onlyDrugPrice = gitOrderHandle.gitOrderPage.getXiaoJiPrice();
		gitOrderHandle.clickGoPay();						//点击去支付
		payStateHandle.clickPayComplete();					//点击已完成支付
		
	}
	
	/**
	 * @Decription: 从分类 测试分类_1_HOT 中筛选处方药，随机进1款商品 进详情页点击购买
	 */
	public void buyChuFangDrugs() {

		indexHandle.clickCategory(8);						//点击指定分类
		
		categoryHandle.clickScreenButton();					//点击筛选
		categoryHandle.clickScreenChufang();				//点击处方
		categoryHandle.clickProductRandom();				//随机点击某商品
		
		proDetailHandle.clickYuYue();						//点击立即预约
		
		//处方信息填写
		chufangHandle.sendKeysName("AutoTest Username");
		chufangHandle.sendKeysId("500103199003079273");
		chufangHandle.sendKeysDeseaseDescription("疾病描述：肚子痛，非常通");
		chufangHandle.clickDiseaseSelected();		//点击选择疾病
//		chufangHandle.clickOneDesease();			//任意点选一个疾病种类
		chufangHandle.clickOneDesease("高血压");		//点选指定名称的疾病种类，如果没有默认点第一个
		chufangHandle.clickQuestionsPass();  		//点选通过情况的问题
		chufangHandle.pageDown();
//		chufangHandle.clickNoneVoucher(); 			//点击暂无复诊凭证
//		chufangHandle.clickConfirmNoneVoucher();    //点击确认凭证不在身边
		chufangHandle.inputVoucherImg(IMG_PATH_001); //上传图片
		chufangHandle.inputVoucherImg(IMG_PATH_002);
		driver.sleep(2);
		chufangHandle.clickInputWenZhen();			//点击完成并同意问诊
		
		List<String> drugNames_GO = gitOrderHandle.gitOrderPage.getProductNames();
		String totalPrice_GO = gitOrderHandle.gitOrderPage.getTotalPrice();
		gitOrderHandle.clickGoPay();			//点击提交预约
		
		payStateHandle.clickPayComplete();		//点击已完成支付
		
		List<String> drugNames_OD = orderDetailPage.getDrugNames();
		String totalPrice_OD = orderDetailPage.getTotalPrice();
		String orderNum = orderDetailPage.getOrderNum();
		if (totalPrice_GO == totalPrice_OD) {
			if (MyUtil.isEquals(drugNames_GO, drugNames_OD)) {
				Reporter.log("支付成功，订单号为：["+orderNum+"]");
			}
		}
	}
	
	/**
	 * @Decription: 填写处方信息页面Toast提示信息验证（顺序及正确性）
	 */
	public void checkToastInfo() {
		
		indexHandle.clickCategory(8);
		
		categoryHandle.clickScreenButton();
		categoryHandle.clickScreenChufang();
		categoryHandle.clickProductRandom();
		
		proDetailHandle.clickYuYue();
		//
		chufangHandle.pageDown();
		chufangHandle.clickInputWenZhen();
		assertToastInfo(GetData.getData("doctor", "CF_toastName"));
		driver.sleep(3);
		
		chufangHandle.pageUp();
		chufangHandle.sendKeysName("AutoTest Username");
		chufangHandle.pageDown();
		chufangHandle.clickInputWenZhen();
		assertToastInfo(GetData.getData("doctor", "CF_toastId"));
		driver.sleep(3);
		
		chufangHandle.pageUp();
		chufangHandle.sendKeysId("5000");
		chufangHandle.pageDown();
		chufangHandle.clickInputWenZhen();
		assertToastInfo(GetData.getData("doctor", "CF_toastIdWrong"));
		driver.sleep(3);
		
		chufangHandle.pageUp();
		chufangHandle.sendKeysId("500103199003079273");
		chufangHandle.pageDown();
		chufangHandle.clickInputWenZhen();
		assertToastInfo(GetData.getData("doctor", "CF_toastZhenDuan"));
		driver.sleep(3);
		
		chufangHandle.pageUp();
		chufangHandle.clickDiseaseSelected();		//点击选择疾病
		chufangHandle.clickOneDesease();			//任意点选一个疾病种类
		chufangHandle.pageDown();
		chufangHandle.clickInputWenZhen();
		assertToastInfo(GetData.getData("doctor", "CF_toastDeseaseDescription"));
		driver.sleep(3);
		
		chufangHandle.pageUp();
		chufangHandle.sendKeysDeseaseDescription("疾病描述：肚子痛，非常通");
		chufangHandle.pageDown();
		chufangHandle.clickInputWenZhen();
		assertToastInfo(GetData.getData("doctor", "CF_toastVoucher"));
		driver.sleep(3);
		
		chufangHandle.inputVoucherImg(IMG_PATH_003); //上传图片
		chufangHandle.clickInputWenZhen();
		assertToastInfo(GetData.getData("doctor", "CF_toastAllergyHistory"));
		driver.sleep(3);
		
		chufangHandle.clickQuestionsAllYes();
		chufangHandle.clickInputWenZhen();
		assertToastInfo(GetData.getData("doctor", "CF_toastHasAllergys"));
		driver.sleep(3);
		
		chufangHandle.clickQuestionsPass();  		//点选通过情况的问题
		chufangHandle.clickInputWenZhen();
		assertToastInfo(GetData.getData("doctor", "CF_toastIdWrong2"));
		driver.sleep(3);
		
		chufangHandle.pageUp();
		chufangHandle.clearIdText();
		chufangHandle.sendKeysId("511324198908276096");
		chufangHandle.pageDown();
		chufangHandle.clickInputWenZhen();
		assertToastInfo(GetData.getData("doctor", "CF_toastSuccess"));
		driver.sleep(3);
	}
	
	/**
	 * @Description: 断言是否购买成功,判断依据为跳转到订单详情页面
	 */
	public void assertPaySuccess() {	
//		boolean result = payStateHandle.assertPaySuccess();//该判断为已完成支付的按钮出现
		boolean result = false;
		if (driver.getTitle().equals("详情")) {
			result = true;
			Reporter.log("页面跳转详情页面，视为购买成功");
		}
		log.info("============断言中===============");
		assertEquals(result, true, "预期：点击支付后跳转详情页面，视为已支付成功，实际：未跳转页面，用例失败");
		log.info("============断言结束===============");
	} 
	
	/**
	 * @Decription: 断言Toast信息是否与预期一致
	 */
	public void assertToastInfo(String info) {
		boolean result = false;
		String text = chuFangInfoPage.getToastMessage().getText();
		System.out.println("info:"+info);
		System.out.println("text:"+text);
		if (text.equals(info)) {
			result = true;
			Reporter.log("提示信息正确:["+text+"]");
		}
		log.info("============断言中===============");
		assertEquals(result, true, "预期：提示信息与预期一致，实际不一致，用例失败");
		log.info("============断言结束===============");
	}
}
