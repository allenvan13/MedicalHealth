package doctorlm.MedicalHealth.util;

import java.util.Iterator;
import java.util.List;

public class MyUtil<T> {

	public static <T> boolean isEquals(List<T> list1,List<T> list2){
        if(null != list1 && null != list2){
            if(list1.containsAll(list2) && list2.containsAll(list1)){
                return true;
            }
            return false;
        }else if (list1 == null && null != list2) {
        	System.out.println(list1+"为null，有问题");
        	return false;
		}else if (list2 == null && null != list1) {
        	System.out.println(list2+"为null，有问题");
        	return false;
		}else {
			System.out.println("出BUG了");
        	return false;
		}
    }
	
	
	public static <T> boolean isContains(List<T> list,T t) {
		boolean result = false;
		if (null != list) {
			Iterator<T> it = list.iterator();
			while (it.hasNext()) {
				if (it.next().equals(t)) {
					result = true;
				}
			}
		}
		return result;
	}
}
