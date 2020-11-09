package doctorlm.MedicalHealth.test;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import doctorlm.MedicalHealth.base.DriverBase;
import doctorlm.MedicalHealth.business.SearchPagePro;
import doctorlm.MedicalHealth.util.IteratorTest;
import doctorlm.MedicalHealth.util.RetryListener;
import doctorlm.MedicalHealth.util.SearchKeys;
import doctorlm.MedicalHealth.util.TestngListener;

@Listeners({TestngListener.class,RetryListener.class})
public class SearchTest extends CaseBase{

	public DriverBase driver;
	public SearchPagePro searchpPagePro;
	
	@BeforeMethod
	public void beforeMethod() {
		driver = super.driver;
		searchpPagePro = new SearchPagePro(driver);
	}
	
	@AfterMethod
	public void afterMethod() {
		searchpPagePro.backToIndex();
	}
	
	@Test(priority = 1,description = "首页-搜索-搜索不存在的商品，是否没有结果，搜索[我就是个药 ]")
	@Parameters({"searchKeys001"})
	public void testNoResultSearch(String searchKeys001) {
		searchpPagePro.search(searchKeys001);
		searchpPagePro.assertNoSearchResult();	
	}
	
	@Test(priority = 2,description = "首页-搜索-搜索存在的商品简称，是否存在结果，搜索[感冒] ")
	@Parameters({"searchKeys002"})
	public void testFuzzySearch(String searchKeys002) {
		searchpPagePro.search(searchKeys002);
		searchpPagePro.assertYesSearchResult();
	}
	
	@Test(priority = 3,description = "首页-搜索-搜索存在的商品简称，是否存在结果，搜索[缬沙坦胶囊（千金湘江）]")
	@Parameters({"searchKeys003"})
	public void testAllNameSearch(String searchKeys003) {
		searchpPagePro.search(searchKeys003);
		searchpPagePro.assertYesSearchResult();
	}
	
	@Test(priority = 4,description = 
			"首页-搜索-测试不回退页面是否能再次提交搜索，并结果显示正常-搜索[盐酸二甲双胍片]每次删除1个字继续提交")
	@Parameters({"searchKeys004"})
	public void testSearchAgain(String searchKeys004) {
		searchpPagePro.FuzzySearch(searchKeys004);
	}
	
	@Test(priority = 5,dataProvider = "iterator_searchKeys_LeftFuzzy",
			dataProviderClass = IteratorTest.class,
			description = "首页-搜索-左模糊搜索测试:搜索[盐酸二甲双胍片]提交，每次从左往右删除一个字符提交搜索，是否均存在结果")
	public void testLeftFuzzySearch(SearchKeys searchKeys) {
		searchpPagePro.search(searchKeys);
		searchpPagePro.assertYesSearchResult();
	}
	
	@Test(priority = 6,dataProvider = "iterator_searchKeys_RightFuzzy",
			dataProviderClass = IteratorTest.class,
			description = "首页-搜索-右模糊搜索测试:搜索[盐酸二甲双胍片]提交，每次从右往左删除一个字符提交搜索，是否均存在结果")
	public void testRightFuzzySearch(SearchKeys searchKeys) {
		searchpPagePro.search(searchKeys);
		searchpPagePro.assertYesSearchResult();
	}
	
//	@Test(priority = 7)
	@Test(priority = 7,description = "是否能删除搜索历史记录",
			dependsOnMethods = {"testSearchAgain","testLeftFuzzySearch","testRightFuzzySearch"},alwaysRun = true)
	public void deleteSearchHistory() {
		searchpPagePro.deleteSearchHistory();
		searchpPagePro.assertDeleteSucess();
		driver.sleep(5);
	}
	
	@Parameters({"searchKeys001","searchKeys002","searchKeys003","searchKeys004"})
	public List<String> getParameters(String searchKeys001, String searchKeys002, String searchKeys003, String searchKeys004){
		List<String> searchKeys = new ArrayList<String>();
		searchKeys.add(searchKeys001);
		searchKeys.add(searchKeys002);
		searchKeys.add(searchKeys003);
		searchKeys.add(searchKeys004);
		searchKeys.add("盐酸二甲双胍");
		searchKeys.add("盐酸二甲双");
		searchKeys.add("盐酸二甲");
		searchKeys.add("盐酸二");
		searchKeys.add("盐酸");
		searchKeys.add("盐");
		return searchKeys;
	}
	
}
