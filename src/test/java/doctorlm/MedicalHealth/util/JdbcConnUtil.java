/**
 * Copyright (c)  2019 BFD-SoftwareStudio. All rights reserved.
 *
 * Function: 
 * @author: Allen Van
 * @date: 2019年12月28日 下午3:50:21
 */
package doctorlm.MedicalHealth.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @ClassName:JdbcConnUtil.java
 * @author： Allen Van
 * @date: 2019年12月28日 下午3:50:21
 * @Description: 
 */
public class JdbcConnUtil {
	static Connection conn = null;
    static {
        try {
            //加载驱动，此时会自动向driverManager注册该driver的实例
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("找不到这个类jdbc.driver");
            e.printStackTrace();
        }
    }
    
    
    
    /*
     * 提供一个获取数据库连接的方法
     * String properties参数 是数据库配置文件的绝对地址
     */
     static Connection getConn(String properties) {
        
         
        try {
            //通常将数据库连接信息写在一个配置文件中
            //创建一个Properties对象
            Properties pro = new Properties();
            //创建一个流，怼到我们的配置文件上
            FileInputStream stream = new FileInputStream(properties);
            //将流加载到配置文件中，相当于缓存下来，之后直接get相关数据即可，不用用一次读一次
            pro.load(stream);
            
            //getConnection方法会遍历之前加载进来的多有driver，尝试建立数据库连接，如果成功则返回
            conn = DriverManager.getConnection(pro.getProperty("url"), pro.getProperty("userName"), pro.getProperty("password"));
        
             
        
        } catch (FileNotFoundException e) {
            System.out.println("配置文件未找到！");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("配置文件读取失败！");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("数据库连接创建失败");
            e.printStackTrace();
        }
        
        return conn;
    }
     
     
     
    /*
     * 提供一个关闭之前打开的链接的方法
     * PreparedStatement pStatement----预编译对象
     * ResultSet... set------结果集，对于增删改操作，没有返回结果集，所以这里设计成可变参数列表的形式
     */
    static void close(PreparedStatement pStatement,ResultSet... set) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("数据库连接关闭失败！");
                e.printStackTrace();
            }
        }
        if (pStatement != null) {
            try {
                pStatement.close();
            } catch (SQLException e) {
                System.out.println("预编译声明对象关闭失败！");
                e.printStackTrace();
            }
        }
        
    
        for (int i = 0; i < set.length; i++) {
            if (set[i] != null) {
                try {
                    set[i].close();
                } catch (SQLException e) {
                    System.out.println("结果集关闭失败！");
                    e.printStackTrace();
                }
            }
        }
        
        
    }
    

}