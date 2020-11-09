package doctorlm.MedicalHealth.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtil {
	
    private static String DRIVER;
    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;
    
    static {  	
        try {
        	//从jdbc.properties文件中获取数据初始化链接的值
            Properties pro=new Properties();
            InputStream is=JDBCUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
			pro.load(is);
			DRIVER=pro.getProperty("driver");
	        URL=pro.getProperty("url");
	        USERNAME=pro.getProperty("user");
	        PASSWORD=pro.getProperty("password");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    /**1.1 获得数据库连接
                *  获取jdbc连接的方法getConnection （通过JDBCUtil.getConnection（）来获取一个JDBC的连接）
     * IP 为数据库所在的远程服务器的ip地址
     * PORT 为数据库访问的端口
     * DATABASE  要连接的数据库名称
     * */
    // public static Connection getConnection(String IP,String DATABASE){
    public static Connection getConnection(String SqlType,String IP,int PORT,String DATABASE,String username,String password){
        try {
        		//MySql默认端口3306
        		if (SqlType.equalsIgnoreCase("MySql")) {
        			//1加载驱动
                    Class.forName("com.mysql.jdbc.Driver");
                    //2.获取连接
                    URL = "jdbc:mysql://"+ IP + ":" + PORT +"/"+ DATABASE;
                    return DriverManager.getConnection(URL, username, password);
				}
        		
        		//SQL server 默认端口1433
        		if (SqlType.equalsIgnoreCase("SQLserver")) {

                    Class.forName("com.mircosoft.jdbc.sqlserver.SQLServerDriver");
                    URL = "jdbc:mircosoft:sqlserver:"+ IP + ":" + PORT +";databasename="+ DATABASE;
                    return DriverManager.getConnection(URL, username, password);
				}
        		
        		//Oracle默认端口1521
        		if (SqlType.equalsIgnoreCase("Oracle")) {
        			Class.forName("oracle.jdbc.driver.OracleDriver");
                    URL = "jdbc:oracle:thin:"+ IP + ":" + PORT +":"+ DATABASE;
                    return DriverManager.getConnection(URL, username, password);
				}
                
            } catch (Exception e) {//捕捉所有的异常
                e.printStackTrace();
            }
            return null;
    }
    
    /**
     * 1.2 获得数据库连接，方法重写
     * @param IP 
     * @param MySQL_DATABASE 
     * @param PORT 
     * */
    public static Connection getConnection(String IP, String MySQL_DATABASE, String PORT){
        try {
                //1加载驱动
                Class.forName("com.mysql.jdbc.Driver");
                //2.获取连接
                URL = "jdbc:mysql://"+ IP + ":" + PORT +"/"+ MySQL_DATABASE;
                return DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (Exception e) {//捕捉所有的异常
                e.printStackTrace();
            }
            return null;
    }
    
    /**2.释放资源
     * 关闭，释放资源的方法close （若不存在使用下列资源，传递参数为null即可，通过JDBCUtil.close()关闭资源）
     * rs 为结果集，通过JDBC查到的结果集，使用后需关闭释放资源
     * ps st 为开启的sql语句
     * conn 为jdbc的连接
     * @throws SQLException 
     * */
    public static void closeAll(Connection conn, Statement st,PreparedStatement ps,ResultSet rs) throws SQLException{
    	//栈式关闭（最先连接，最后关闭连接）
    	//关闭ResultSet
            if(rs != null) rs.close();
    	//关闭PreparedStatement
            if(ps != null) ps.close();
        //关闭Statement
            if(st != null) st.close();
        //关闭Connection
            if(conn != null) conn.close();
    }
    
    public static void main(String[] args) {
		
	}
}
