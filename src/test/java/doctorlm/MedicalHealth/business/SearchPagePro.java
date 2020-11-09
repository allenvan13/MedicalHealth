package doctorlm.MedicalHealth.business;

import static org.testng.Assert.assertEquals;

import org.testng.Reporter;

import doctorlm.MedicalHealth.base.DriverBase;
import doctorlm.MedicalHealth.handle.IndexPageHandle;
import doctorlm.MedicalHealth.handle.SearchPageHandle;
import doctorlm.MedicalHealth.util.SearchKeys;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class SearchPagePro {

	public SearchPageHandle spHandle;
	public IndexPageHandle ipHandle;
	public DriverBase driver;
	public SearchPagePro(DriverBase driver) {
		this.driver = driver;
		spHandle = new SearchPageHandle(driver);
		ipHandle = new IndexPageHandle(driver);				
	}
	
	/**
	 * @discription: 输入关键字 提交搜索
	 * @param (String searchKeys)搜索关键字
	 */
	public void search(String searchKeys) {
		//点击首页搜索框
		ipHandle.clickSearchInputBox();
		//点击搜索页面搜索框
		spHandle.clickSearchInputBox();
		//清空搜索框文本
		spHandle.clearSearchInputBox();
		//输入搜索关键字
		spHandle.sendSearchKeys(searchKeys);
		driver.sleep(1);
		//点击搜索按钮提交搜索
		spHandle.clickSearchButton();
		driver.sleep(2);
	}
	
	/**
	 * @discription: 输入关键字 提交搜索
	 * @param (SearchKeys searchKeys)搜索关键字 通过SearchKeys对象提供参数
	 */
	public void search(SearchKeys searchKeys) {
		//点击首页搜索框
		ipHandle.clickSearchInputBox();
		//点击搜索页面搜索框
		spHandle.clickSearchInputBox();
		//清空搜索框文本
		spHandle.clearSearchInputBox();
		//输入搜索关键字
		spHandle.sendSearchKeys(searchKeys.getSearchKeys());
		Reporter.log("搜索关键字："+searchKeys.getSearchKeys());
		driver.sleep(1);
		//点击搜索按钮提交搜索
		spHandle.clickSearchButton();
		driver.sleep(2);
	}
	
	/**
	 * @Decription:  完成左模糊搜索，逐字删除点击搜索
	 * @parm: 搜索的关键词
	 */
	public void FuzzySearch(String searchKeys) {
		//得到字符串的长度
		int max = searchKeys.length();
		search(searchKeys);
		assertYesSearchResult();
		do {
			//回退删除1个字
			spHandle.deleteSearchKeys();
			driver.sleep(1);
			//搜索提交
			spHandle.clickSearchButton();
			driver.sleep(1);
			assertYesSearchResult();
			max --;
		} while (max > 1);
		if (max == 1) {
			spHandle.deleteSearchKeys();
			log.info("搜索框文本删除完毕");
			driver.sleep(1);
			//搜索提交
			spHandle.clickSearchButton();
			driver.sleep(1);
		}
		driver.sleep(2);
	}
	
	/**
	 * @Decription: 删除历史搜索词记录
	 */
	public void deleteSearchHistory() {
		//点击首页搜索框
		ipHandle.clickSearchInputBox();
		driver.sleep(1);
		//点击删除
		spHandle.clickDeleteButton();
		driver.sleep(1);
		//确认删除
		spHandle.clickDeleteYes();
	}
	
	/**
	 * @Description: 从搜索页面回到首页页面
	 */
	public void backToIndex() {
		driver.back();
		driver.sleep(2);
	}

	/**
	 * @Description: 断言是否 无搜索结果
	 */
	public void assertNoSearchResult() {	
		boolean result = spHandle.assertHasNoSearchResult();
		log.info("============断言中===============");
		assertEquals(result, false, "预期：无搜索结果，实际：有结果，用例失败");
		log.info("============断言结束===============");
	}
	
	/**
	 * @Description: 断言是否 有搜索结果
	 */
	public void assertYesSearchResult() {
		boolean result = spHandle.assertHasSearchResult();
		log.info("============断言中===============");
		assertEquals(result, true, "预期：有搜索结果，实际：无结果，用例失败");
		log.info("============断言结束===============");
	}
	
	/**
	 * @Description: 断言是否删除搜索关键字记录成功
	 */
	public void assertDeleteSucess() {
		boolean result = spHandle.assertDeleteSucess();
		log.info("============断言中===============");
		assertEquals(result, true, "预期：删除成功，实际：删除失败，用例失败");
		log.info("============断言结束===============");
	}
	
//	/**
//	 * @Description: 断言是否保存了搜索关键字历史
//	 */
//	public void assertHasSaveSearchKeys() {
//		List<String> searchKeys;
//		searchPage.getSearchKeysHistory();
//	}
	
}
