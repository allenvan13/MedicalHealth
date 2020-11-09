/**
 * Copyright (c)  2020 BFD-SoftwareStudio. All rights reserved.
 *
 * Function: 
 * @author: Allen Van
 * @date: 2020年1月1日 下午9:08:05
 */
package doctorlm.MedicalHealth.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

/**
 * @ClassName:IteratorSearchKeys.java
 * @author： Allen Van
 * @date: 2020年1月1日 下午9:08:05
 * @Description: 为测试case提供Iterator参数
 */
public class IteratorTest {
	
	@DataProvider(name = "iterator_searchKeys_LeftFuzzy")
	public static Iterator<Object[]> iteratorData_LeftFuzzy(){
		List<Object> item = new ArrayList<Object>();
		
		SearchKeys searchKeys001 = new SearchKeys();
		searchKeys001.setSearchKeys("盐酸二甲双胍片");
		item.add(searchKeys001);
		
		SearchKeys searchKeys002 = new SearchKeys();
		searchKeys002.setSearchKeys("酸二甲双胍片");
		item.add(searchKeys002);
		
		SearchKeys searchKeys003 = new SearchKeys();
		searchKeys003.setSearchKeys("二甲双胍片");
		item.add(searchKeys003);

		SearchKeys searchKeys004 = new SearchKeys();
		searchKeys004.setSearchKeys("甲双胍片");
		item.add(searchKeys004);
		
		SearchKeys searchKeys005 = new SearchKeys();
		searchKeys005.setSearchKeys("双胍片");
		item.add(searchKeys005);
		
		SearchKeys searchKeys006 = new SearchKeys();
		searchKeys006.setSearchKeys("胍片");
		item.add(searchKeys006);

		SearchKeys searchKeys007 = new SearchKeys();
		searchKeys007.setSearchKeys("片");
		item.add(searchKeys007);
		
		List<Object[]> searchKeysList = new ArrayList<Object[]>();
		
		for(Object x : item) {
			searchKeysList.add(new Object[] {x});
		}
		
		return searchKeysList.iterator();
	}
	
	@DataProvider(name = "iterator_searchKeys_RightFuzzy")
	public static Iterator<Object[]> iteratorData_RightFuzzy(){
		List<Object> item = new ArrayList<Object>();
		
		
		SearchKeys searchKeys001 = new SearchKeys();
		searchKeys001.setSearchKeys("盐酸二甲双胍片");
		item.add(searchKeys001);
		
		SearchKeys searchKeys002 = new SearchKeys();
		searchKeys002.setSearchKeys("盐酸二甲双胍");
		item.add(searchKeys002);
		
		SearchKeys searchKeys003 = new SearchKeys();
		searchKeys003.setSearchKeys("盐酸二甲双");
		item.add(searchKeys003);

		SearchKeys searchKeys004 = new SearchKeys();
		searchKeys004.setSearchKeys("盐酸二甲");
		item.add(searchKeys004);
		
		SearchKeys searchKeys005 = new SearchKeys();
		searchKeys005.setSearchKeys("盐酸二");
		item.add(searchKeys005);
		
		SearchKeys searchKeys006 = new SearchKeys();
		searchKeys006.setSearchKeys("盐酸");
		item.add(searchKeys006);

		SearchKeys searchKeys007 = new SearchKeys();
		searchKeys007.setSearchKeys("盐");
		item.add(searchKeys007);
		
		List<Object[]> searchKeysList = new ArrayList<Object[]>();
		
		for(Object x : item) {
			searchKeysList.add(new Object[] {x});
		}
		
		return searchKeysList.iterator();
	}
}
