package doctorlm.MedicalHealth.business;

import doctorlm.MedicalHealth.base.DriverBase;
import doctorlm.MedicalHealth.handle.CategoryHandle;
import doctorlm.MedicalHealth.handle.IndexPageHandle;

public class IndexPro {

	public CategoryHandle categoryHandle;
	public IndexPageHandle ipHandle;
	public DriverBase driver;
	
	public IndexPro(DriverBase driver) {
		this.driver = driver;
		categoryHandle = new CategoryHandle(driver);
		ipHandle = new IndexPageHandle(driver);				
	}
	
	//该方法弃用  @function: 遍历所有商品
    public void clickCategory() {
//    	
//    	int productAll = 0;  //商品总数
//        int checkRight = 0;  //核对正确的商品数
//        int checkWrong = 0;  //核对错误的商品数
//        boolean sucess = false;  //最终遍历是否成功的boolean
//        
//    	//此方法获取元素较多，注意区分元素定义名字，多个与单个 区分 elements 与element
//    	List<WebElement> elements_category = ipHandle.indexPage.getMenuNavElements(); //获取所有商品分类
//    	int category_max = elements_category.size();
//    	
//    	for (int i = 0; i < category_max; i++) {
//    		WebElement element_category = elements_category.get(i);   //顺序获取每个商品分类
//    		String categoryName = element_category.getText();
//    		Reporter.log("点击分类："+categoryName);
//    		element_category.click();
//    		driver.sleep(1);
//    		
//    		indexPage.pageDownByJs(1, GetData.getBy("doctor", "CA_searchResultAssertDiv"), GetData.getData("doctor", "CA_searchResultAssertText"));
//    		
//    		List<WebElement> elements_product = indexPage.getProductElements();		//获取列表所有商品
//    		List<WebElement> elements_productName = indexPage.getProductNameElements();	//获取列表所有商品名称元素
//    		List<WebElement> elements_productPrice = indexPage.getProductNameElements();	//获取列表所有商品名称元素
//    		
//    		
//    		for (int j = 0; j < elements_product.size(); j++) {
//				String productName_home = elements_productName.get(j).getText();
//				log.info("点击商品 [{}] ",productName_home);
//				elements_product.get(j).click();
//				driver.sleep(1);
//				
//				String productName_Detail = productPage.getDrugName().getText();
//				String pageName = driver.getTitle();
//				assertEquals(productName_Detail, productName_home,"商品详情页面信息正确{}");
//				if (productName_home.equals(productName_Detail) && pageName.equals("商品详情")) {
//					log.info("成功跳转[{}]页面,页面商品为[{}]",pageName,productName_Detail);
//				}else {
//					log.error("跳转页面失败，或者商品名称不正确");
//				}
//				Reporter.log("点击-< ，返回上页");
//				driver.back();
//				driver.sleep(1);
//				
//				elements_product = indexPage.getProductElements();		//需要重新获取
//	    		elements_productName = indexPage.getProductNameElements();	//需要重新获取
//			}
//    		
//			elements_category = indexPage.getMenuNavElements();  	//需要重新获取
//		}
//		Reporter.log("成功完成遍历[所有]商品");
    }
    
}
