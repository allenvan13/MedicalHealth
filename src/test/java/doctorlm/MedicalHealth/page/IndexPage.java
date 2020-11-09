package doctorlm.MedicalHealth.page;

import java.util.List;

import org.openqa.selenium.WebElement;

import doctorlm.MedicalHealth.base.DriverBase;
import doctorlm.MedicalHealth.util.GetData;

/**
 * @ClassName:IndexPage.java
 * @author： Administrator
 * @date: 2020 下午3:43:35
 * @Description: 医药健康-首页index
 */
public class IndexPage extends PageBase{

	public IndexPage(DriverBase driver) {
		super(driver);
	}

	/**
	 * @获取元素： 搜索框
	 */
	public WebElement getSearchInputBox() {
		return explicitlyWait(3,"doctor","ID_searchInputBox");
//		return getElement(GetData.getBy("doctor","ID_searchBox"));
	}
	
	/**
	 *  @获取元素：瘦身Banner
	 */
	public WebElement getSouShenHuoDong() {
		return getElement(GetData.getBy("doctor","ID_soushen_huodong"));
	}
	
	/**
	 *  @获取元素：企业资质模块
	 */
	public WebElement getZiZhi() {
		return getElement(GetData.getBy("doctor","ID_zizhi"));
	}
	
	/**
	 *  @获取元素：0.1元限时活动Banner
	 */
	public WebElement getXianShiHuoDong() {
		return getElement(GetData.getBy("doctor","ID_xianshi_huodong"));
	}
	
	/**
	 *  @获取元素：高血压Banner
	 */
	public WebElement getGaoXueYa() {
		return getChildrenElement(GetData.getBy("doctor", "ID_father_gaoxueya"), GetData.getBy("doctor", "ID_gaoxueya_huodong"));
	}
	
	/**
	 *  @获取元素：糖尿病Banner
	 */
	public WebElement getTangNiaoBing() {
		return getChildrenElement(GetData.getBy("doctor", "ID_father_gaoxueya"), GetData.getBy("doctor", "ID_tangniaobing_huodong"));
	}
	
	/**
	 *  @获取元素：我的 图标
	 */
	public WebElement getMine() {
		return getElement(GetData.getBy("doctor","ID_Mine"));
	}
	
	/**
	 *  @获取元素：购物车 图标
	 */
	public WebElement getBuyCar() {
		return getElement(GetData.getBy("doctor","ID_BuyCar"));
	}
	
	/**
	 *  @获取元素：点击无效的Div
	 */
	public WebElement getClickDiv() {
		return explicitlyWait(2, "doctor", "ID_clickDiv");
	}
	
	/**
	 *  @获取元素：商品分类Nav
	 */
	public List<WebElement> getMenuNavElements(){
		return getElements(GetData.getBy("doctor", "ID_MenuNavElements"));
//		return explicitlyWaitElements(3, "doctor", "ID_MenuNavElements");
	}
	
	/**
	 *  @获取元素：指定index的分类
	 */
	public WebElement getMenuNavElement(int index){
		return getMenuNavElements().get(index);
	}

	/**
	 *   @获取元素：分类数量
	 */
	public int getCategoryNum() {
		List<WebElement> elements_category = this.getMenuNavElements();
		int category = elements_category.size();
		return category;
	}
	
//	/**
//	 *   @获取元素：单分类中商品数量
//	 */
//	public int getProductNum() {
//	List<WebElement> elements_product = this.getProductElements();
//	int product = elements_product.size();
//	return product;
//	}
//	/**
//	 *  @获取元素：商品list <div>
//	 */
//	public List<WebElement> getProductElements(){
//		return getElements(GetData.getBy("doctor", "ID_productElements"));
//	}
//	
//	/**
//	 *  @获取元素：商品list的商品名字 <div> 用于验证
//	 */
//	public List<WebElement> getProductNameElements(){
//		return getElements(GetData.getBy("doctor", "ID_productNameElements"));
//	}
}
