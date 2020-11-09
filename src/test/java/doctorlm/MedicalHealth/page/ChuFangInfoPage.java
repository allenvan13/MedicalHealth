package doctorlm.MedicalHealth.page;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import doctorlm.MedicalHealth.base.DriverBase;
import doctorlm.MedicalHealth.util.GetData;

public class ChuFangInfoPage extends PageBase{
	
	public ChuFangInfoPage(DriverBase driver) {
		super(driver);
	}
	
	/*
	 元素信息
	CF_inputName=id>inputName>患者姓名
	CF_idCard=id>idCard>身份证号
	CF_diseaseSelected=id>diseaseSelected>选择疾病
	CF_deseases=className>disease_item>疾病种类组元素
	CF_hasNodeseases=className>more_disease>没有您确诊的疾病？点这里
	CF_diseaseSearch=id>diseaseSearch>疾病搜索框
	CF_deseasesYes=xpath>//*[@id="windowShadow"]/div/div[4]/div>疾病种类确定按钮
	CF_deseaseDescription=id>description>疾病描述
	CF_threeQuestion_father=className>block_value-content>三个问题父元素，组元素
	CF_threeQuestion_son=className>block_value_item>三个问题子元素，每个问题对应2个子元素，0为是，1为否
	CF_noneVoucher=className>note>暂无复诊凭证
	CF_confirmNoneVoucher=className>bt>我确认遗失或不在身边
	CF_cancelNoneVoucher=className>window_close>提示弹窗右上角X按钮
	CF_inputVoucherImg=id>idUpload>添加凭证图片标签
	CF_numberOfVoucher=className>pic_nums-num>凭证图片已上传张数显示0/3
	CF_inputVoucherImgAgain=xpath>//*[@id="content"]/div[7]/div[2]/span
	CF_inputWenZhen=id>handData>完成并同意问诊
	CF_toastMessage=xpath>//*[@id="showToast"]/div/div[2]>Toast提示信息
	CF_toastName=请填写姓名
	CF_toastId=请填写身份证号
	CF_toastIdWrong=身份证号不正确
	CF_toastZhenDuan=请填写实体机构诊断
	CF_toastDeseaseDescription=请填写疾病描述
	CF_toastVoucher=请上传复诊凭证
	CF_toastAllergyHistory=请选择过敏史
	*/
	
	/** 
	 * @获取元素： 患者姓名
	 */
	public WebElement getInputName() {
		return getElement(GetData.getBy("doctor", "CF_inputName"));
	}

	/** 
	 * @获取元素： 身份证号
	 */
	public WebElement getIdCard() {
		return getElement(GetData.getBy("doctor", "CF_idCard"));
	}

	/** 
	 * @获取元素： 选择疾病
	 */
	public WebElement getDiseaseSelected() {
		return getElement(GetData.getBy("doctor", "CF_diseaseSelected"));
	}

	/** 
	 * @获取元素： 疾病种类组元素
	 */
	public List<WebElement> getDeseases() {
		return explicitlyWaitElements(5, "doctor", "CF_deseases");
	}

	/** 
	 * @获取元素： 没有您确诊的疾病？点这里
	 */
	public WebElement getHasNodeseases() {
		return getElement(GetData.getBy("doctor", "CF_hasNodeseases"));
	}

	/** 
	 * @获取元素： 疾病搜索框
	 */
	public WebElement getDiseaseSearch() {
		return getElement(GetData.getBy("doctor", "CF_diseaseSearch"));
	}

	/** 
	 * @获取元素： 疾病种类确定按钮
	 */
	public WebElement getDeseasesYes() {
		return explicitlyFastWait(3, "doctor", "CF_deseasesYes");
	}

	/** 
	 * @获取元素： 疾病描述
	 */
	public WebElement getDeseaseDescription() {
		return getElement(GetData.getBy("doctor", "CF_deseaseDescription"));
	}

	/** 
	 * @获取元素： 三个问题父元素，组元素
	 */
	public List<WebElement> getThreeQuestion_father() {
		return getElements(GetData.getBy("doctor", "CF_threeQuestion_father"));
	}

	/** 
	 * @获取元素： 三个问题子元素-是，每个问题对应2个子元素，0为是
	 */
	public WebElement getThreeQuestion_Yes(int index) {
		List<WebElement> elements = getThreeQuestion_father();
		int size = elements.size();
		if (index < size && index >= 0) {
			return getChildrenElement(elements.get(index), GetData.getBy("doctor", "CF_threeQuestion_son"), 0);
		}else {
			Reporter.log("索引值超出边界");
			return null;
		}
		
	}
	
	/** 
	 * @获取元素： 三个问题子元素-否，每个问题对应2个子元素,1为否
	 */
	public WebElement getThreeQuestion_No(int index) {
		List<WebElement> elements = getThreeQuestion_father();
		int size = elements.size();
		if (index < size && index >= 0) {
			return getChildrenElement(elements.get(index), GetData.getBy("doctor", "CF_threeQuestion_son"), 1);
		}else {
			Reporter.log("索引值超出边界");
			return null;
		}
	}

	/** 
	 * @获取元素： 暂无复诊凭证
	 */
	public WebElement getNoneVoucher() {
		return getElement(GetData.getBy("doctor", "CF_noneVoucher"));
	}
	
	/** 
	 * @获取元素： 弹窗按钮[我确认遗失或不在身边]
	 */
	public WebElement getConfirmNoneVoucher() {
//		return getElement(GetData.getBy("doctor", "CF_confirmNoneVoucher"));
		return explicitlyFastWait(3, "doctor", "CF_confirmNoneVoucher");
	}
	
	/** 
	 * @获取元素： 提示弹窗右上角X按钮
	 */
	public WebElement getCancelNoneVoucher() {
		return explicitlyWait(3, "doctor", "CF_cancelNoneVoucher");
	}
	
	/** 
	 * @获取元素： 再次上传复诊凭证 链接
	 */
	public WebElement getInputVoucherImgAgain() {
		return explicitlyWait(3, "doctor", "CF_inputVoucherImgAgain");
	}
	
	/** 
	 * @获取元素： 凭证图片已上传张数显示0/3
	 */
	public WebElement getNumberOfVoucher() {
		return getElement(GetData.getBy("doctor", "CF_numberOfVoucher"));
	}
	
	/** 
	 * @获取元素： 添加凭证图片标签
	 */
	public WebElement getInputVoucherImg() {
		return getElement(GetData.getBy("doctor", "CF_inputVoucherImg"));
	}

	/** 
	 * @获取元素： 完成并同意问诊
	 */
	public WebElement getInputWenZhen() {
		return getElement(GetData.getBy("doctor", "CF_inputWenZhen"));
	}
	
	/**
	 * @获取元素：获取 提示toast元素
	 */
	public WebElement getToastMessage() {
		return explicitlyFastWait(5,"doctor","CF_toastMessage");
	}
	
	
}
