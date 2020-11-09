package doctorlm.MedicalHealth.util;

import java.util.Set;

import org.openqa.selenium.Cookie;

import doctorlm.MedicalHealth.base.DriverBase;



public class CookieUtil {
    public DriverBase driver;
    //要读取cookie.properties配置文件,所以需要Proutil工具类
    public ProUtil proUtil;
    public CookieUtil(DriverBase driverBase) {
        driver = driverBase;
        proUtil =  new ProUtil("src\\main\\java\\resource\\data.properties");
    }

    /**
     * setCookie()
     * 
     * */
    public void setCookie() {
        String value=proUtil.getPro("REMEMBERME");
        /**
	         * 第一个参数:cookie的名称，任意取
	         * 第二个参数:cookie的值
	         * 第三个参数:path:域名地址-代表我们只要是我们所测网站的域名，都表示有效
	         * 第四个代表全部路径下
	         * 第五个参数:日期
         * */
        Cookie cookie = new Cookie("REMEMBERME", value, "gogokao.com", "/",null);
        //把当前的cookie成功添加到Set<Cookie>集合中,在DriverBase类中封装了setCookies方法
        driver.setCookies(cookie);
    }

    /**
     * writeCookie()
     * 
     * */
    public void writeCookie() {
    	Set<Cookie> cookies = driver.getCookies(); 
        for(Cookie cookie:cookies) {
            if(cookie.getName().equals("REMEMBERME")) {
                proUtil.writePro(cookie.getName(),cookie.getValue());
            }
        }
    }
    
    public void writeCookie(Set<Cookie> cookies) {
    	for(Cookie cookie:cookies) {
                proUtil.writePro(cookie.getName(),cookie.getValue());
        }
    }
}
