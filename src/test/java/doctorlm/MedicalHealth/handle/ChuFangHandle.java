/**
 * Copyright (c)  2020 BFD-SoftwareStudio. All rights reserved.
 *
 * Function: 
 * @author: Allen Van
 * @date: 2020年1月2日 下午10:53:14
 */
package doctorlm.MedicalHealth.handle;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import doctorlm.MedicalHealth.base.DriverBase;
import doctorlm.MedicalHealth.page.ChuFangInfoPage;

/**
 * @ClassName:ChuFangHandle.java
 * @author： Allen Van
 * @date: 2020年1月2日 下午10:53:14
 * @Description: 
 */
public class ChuFangHandle {
	
	public DriverBase driver;
	public ChuFangInfoPage chufangPage;
	
	public ChuFangHandle(DriverBase driver) {
		this.driver = driver;
		chufangPage = new ChuFangInfoPage(driver);
	}
	
	/**
	 * @功能： 输入患者姓名
	 */
	public void sendKeysName(String name) {
		chufangPage.sendKeys(chufangPage.getInputName(), name);
		Reporter.log("输入患者相关信息");
	}
	
	/**
	 * @功能： 输入身份证
	 */
	public void sendKeysId(String id) {
		chufangPage.sendKeys(chufangPage.getIdCard(), id);
	}
	
	public void clearIdText() {
		chufangPage.clearText(chufangPage.getIdCard());
	}
	
	/**
	 * @功能： 点击 请选择疾病种类
	 */
	public void clickDiseaseSelected() {
		chufangPage.click(chufangPage.getDiseaseSelected());
	}

	/**
	 * @功能： 点击 选择疾病弹窗-确认按钮
	 */
	public void clickDeseasesYes() {
		chufangPage.click(chufangPage.getDeseasesYes());
	}

	/**
	 * @功能：  点选指定名称的疾病种类，如果没有默认点第一个
	 */
	public void clickOneDesease(String deseaseName) {
		List<WebElement> elements = chufangPage.getDeseases();
		if (chufangPage.assertElementsHasText(elements, deseaseName)) {
			for(WebElement element:elements) {
				if (element.getText().equals(deseaseName)) {
					Reporter.log("点选疾病["+deseaseName+"]");
					element.click();
					clickDeseasesYes();
					driver.sleep(1);
				}
			}
		}else {
			WebElement element = elements.get(0);
			String name = element.getText();
			Reporter.log("没有疾病["+deseaseName+"]，默认点选第一个["+name+"]");
			element.click();
			clickDeseasesYes();
			driver.sleep(1);
		}
	}
	
	/**
	 * @功能：  任意点选一个疾病种类
	 */
	public void clickOneDesease() {
		List<WebElement> elements = chufangPage.getDeseases();
		
		int size = elements.size();
		int index = new Random().nextInt(size);
		String deseaseName = elements.get(index).getText();
		chufangPage.click(elements.get(index));
		Reporter.log("点选疾病["+deseaseName+"]");
		clickDeseasesYes();
		driver.sleep(1);
	}
	
	/**
	 * @功能： 点击[没有您确诊的疾病？点这里]
	 */
	public void clickHasNodeseases() {
		chufangPage.click(chufangPage.getHasNodeseases());
	}

	/**
	 * @功能：  搜索框输入 疾病名称 
	 */
	public void sendKeysDiseaseSearch(String disease) {
		chufangPage.sendKeys(chufangPage.getDiseaseSearch(),disease);
	}
	
	/**
	 * @功能： 输入疾病描述
	 */
	public void sendKeysDeseaseDescription(String deseaseDescription) {
		chufangPage.sendKeys(chufangPage.getDeseaseDescription(), deseaseDescription);
	}
	
	/**
	 * @功能： 点选3问题，通过状态：是否否
	 */
	public void clickQuestionsPass() {
		clickFirstQuestionYes();
		clickSecondQuestionNo();
		clickThirdQuestionNo();
		driver.sleep(1);
	}
	
	/**
	 * @功能： 点选3问题，不通过状态：是是是
	 */
	public void clickQuestionsAllYes() {
		clickFirstQuestionYes();
		clickSecondQuestionYes();
		clickThirdQuestionYes();
		driver.sleep(1);
	}
	
	/**
	 * @功能： 点选第一个问题的[是]
	 */
	public void clickFirstQuestionYes() {
		chufangPage.click(chufangPage.getThreeQuestion_Yes(0));
	}

	/**
	 * @功能： 点选第二个问题的[是]
	 */
	public void clickSecondQuestionYes() {
		chufangPage.click(chufangPage.getThreeQuestion_Yes(1));
	}

	/**
	 * @功能： 点选第三个问题的[是]
	 */
	public void clickThirdQuestionYes() {
		chufangPage.click(chufangPage.getThreeQuestion_Yes(2));
	}

	/**
	 * @功能： 点选第一个问题的[否]
	 */
	public void clickFirstQuestionNo() {
		chufangPage.click(chufangPage.getThreeQuestion_No(0));
	}

	/**
	 * @功能： 点选第二个问题的[否]
	 */
	public void clickSecondQuestionNo() {
		chufangPage.click(chufangPage.getThreeQuestion_No(1));
	}

	/**
	 * @功能： 点选第三个问题的[否]
	 */
	public void clickThirdQuestionNo() {
		chufangPage.click(chufangPage.getThreeQuestion_No(2));
	}
	
	public void pageDown() {
		driver.pageOneDown(driver.driver.findElement(By.xpath("//*[@id=\"content\"]/div[5]/div[1]")));
		driver.sleep(1);
	}
	
	public void pageUp() {
		driver.pageOneUp(driver.driver.findElement(By.xpath("//*[@id=\"content\"]/div[5]/div[1]")));
		driver.sleep(1);
	}
	
	public void clickToastMessage() {
		chufangPage.actionClick(chufangPage.explicitlyFastWait(3, "doctor", "CF_toastMessage"));
	}
	
	/**
	 * @功能： 点击 [暂无复诊凭证]
	 */
	public void clickNoneVoucher() {
		chufangPage.click(chufangPage.getNoneVoucher());
	}
	
	/**
	 * @功能： 点击按钮[我确认遗失或不在身边]
	 */
	public void clickConfirmNoneVoucher() {
		chufangPage.click(chufangPage.getConfirmNoneVoucher());
	}

	/**
	 * @功能： 点击[无凭证]弹窗取消标签
	 */
	public void clickCancelNoneVoucher() {
		chufangPage.click(chufangPage.getCancelNoneVoucher());
	}
	
	/**
	 * @功能： 上传凭证图片
	 */
	public void inputVoucherImg(String filePath) {
		chufangPage.sendKeys(chufangPage.getInputVoucherImg(), filePath);
		Reporter.log("上传凭证图片");
	}
	
	/**
	 * @功能： 点击[立即上传]-点击不在身边后可再次上传凭证
	 */
	public void clickInputVoucherImgAgain() {
		chufangPage.click(chufangPage.getInputVoucherImgAgain());
	}

	/**
	 * @功能： 点击底部提交按钮 [完成并同意问诊]
	 */
	public void clickInputWenZhen() {
		chufangPage.click(chufangPage.getInputWenZhen());
		Reporter.log("点击 [完成并同意问诊]");
//		WebElement element = chufangPage.getInputWenZhen();
//		JavascriptExecutor js = (JavascriptExecutor)driver.driver;
//		js.executeAsyncScript("arguments[0].click();", element);
	}
	
	
}
