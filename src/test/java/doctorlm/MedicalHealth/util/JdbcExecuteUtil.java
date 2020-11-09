/**
 * Copyright (c)  2019 BFD-SoftwareStudio. All rights reserved.
 *
 * Function: 
 * @author: Allen Van
 * @date: 2019年12月28日 下午3:48:54
 */
package doctorlm.MedicalHealth.util;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @ClassName:JdbcExecuteUtil.java
 * @author： Allen Van
 * @date: 2019年12月28日 下午3:48:54
 * @Description: 
 */
public class JdbcExecuteUtil {

    /*
     * 该方法是执行select语句，并将查询到的数据返回到调用者提供的类对象中。
     * 该方法是一个泛型方法，泛型方法的声明方式为：修饰符 <T> 返回值类型 方法名（参数列表）
     * class<T> class 要存储数据的类
     * string properties 存储调用者自己的数据库配置文件
     * String sql 存储调用者的sql语句
     * String... args 存储调用者sql语句占位符相应的参数值
     */
    public static <T> List<T> select2(Class<T> clazz, String properties, String sql, String... args)
            throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {

        //准备一个预编译的sql语句的对象，用来将预编译的sql发送到数据库
        PreparedStatement pStatement = null;
        //准备一个结果集，用来存储查询获得的结果
        ResultSet DataSet = null;
        //调用jdbConnUnit类中getConn的方法来获得一个数据库连接
        Connection connection = JdbcConnUtil.getConn(properties);
        //准备一个List，来存放我们的泛型对象
        List<T> list = new ArrayList<T>();
        
        try {

            // 对sql语句执行预编译，并发送给数据库。可以防止sql注入
            pStatement = connection.prepareStatement(sql);

            // 获取可变参数的个数（对应的是sql语句中占位符的个数）
            int length = args.length;

            /* 利用pStatement.setString方法将参数一次赋值给sql中的占位符
             * select Id,RoleName from roles WHERE RoleName =? orRoleName=?，
             * 相当于给？占位符的位置填充好参数
             */
            for (int i = 0; i < length; i++) {
                pStatement.setString(i + 1, args[i]);
            }

            // 执行预编译好的sql，获得一个结果集
            DataSet = pStatement.executeQuery();
            // 获得结果集对象的 列的描述(可以通过它得到表头、数据集的列数）
            ResultSetMetaData dataTitle = DataSet.getMetaData();
            //获得列数
            int length1 = dataTitle.getColumnCount();

            // 利用结果集的next方法，将游标指向第一行数据，以便后面将数据取出
            while (DataSet.next()) {
                // 得到泛型T的对象
                T t = clazz.newInstance();
                // 获得T所有的属性
                Field[] fields = clazz.getDeclaredFields();
                /*
                 *  下面的for，判断了数据的类型，按照从数据库读出来的类型对应存储到对象中
                 */
                for (int i = 0; i < fields.length; i++) {
                    //得到对应的域，并获得域名
                    Field field = fields[i];
                    String tempFieldName = field.getName();
                    //将每一个域名与列名进行对比，如果有，则赋值
                    for (int j = 0; j < length1; j++) {
                        //得到列名
                        String columnName = dataTitle.getColumnName(j + 1);
                        if (tempFieldName.equals(columnName)) {
                            // 将查询出来的resultSet中数据读取为object类型，再将其转换成和域相同的类型
                            field.set(t, (field.getType().cast(DataSet.getObject(columnName))));
                        }
                    }
                }

                // 下面的for，没有判断数据类型，直接按照String类型存入到成员变量中
                /*
                 * for (int i = 0; i < length1; i++) {
                 * 
                 * String tempTitle=dataTitle.getColumnName(i+1); //根据表头中的列名来获得对应的属性名称 Field
                 * field=clazz.getDeclaredField(tempTitle); //给对象t的属性field赋值 field.set(t,
                 * DataSet.getString(tempTitle));
                 * 
                 * }
                 */
                
                //将每一个t对象加入到list中
                list.add(t);

            }

        } catch (SQLException e) {
            System.out.println("查询语句执行失败！");
            e.printStackTrace();
        }finally{
		//这个关闭必须写在finally里面，如果获取的conn或者statement的时候出现了问题，这没有在finally中的话，就调用不到close 方法了，或者conn为null的时候，还会报空指针异常
		JdbcConnUtil.close(pStatement, DataSet);
		} 
		return list; 
	} 
	/* * 
	该方法是执行查询语句，并将结果保存到map中返回。 
	* 参数： * String properties----数据库配置文件的绝对地址； 
	* String sql-----要执行的sql语句
	* String... args----上述sql语句中如果包含占位符?，就需要传入占位符相应的参数值 
	*/ 
	public static List<Map<String, String>> select(String properties, String sql, String... args) { 
		ResultSet DataSet = null; 
		Connection connection = JdbcConnUtil.getConn(properties); 
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(); 
		PreparedStatement pStatement = null; 
		try { 
			// 对sql语句执行预编译，发送给数据库。防止sql注入 
			pStatement = connection.prepareStatement(sql); 
			// 获取可变参数的个数（对应的是占位符的个数） 
			int length = args.length; 
			// 利用setString方法将参数一次赋值给sql中的占位符 
			// select Id,RoleName from roles WHERE RoleName =? or 
			// RoleName=?，相当于给？占位符的位置填充好参数 
			for (int i = 0; i < length; i++) { 
				pStatement.setString(i + 1, args[i]); } 
				// 执行预编译好的sql，获得一个结果集 
				DataSet = pStatement.executeQuery(); 
				// 获得结果集对象列的描述（包括数量、类型、属性） 
				ResultSetMetaData dataTitle = DataSet.getMetaData(); 
				int length1 = dataTitle.getColumnCount(); 
				// 利用结果集的next方法，将光标移动到第一行数据，之后获得这一行的数据 
				while (DataSet.next()) { 
				//这里的map用来存放一行数据，key为列名，Value为列名对应的值。 
				//必须在循环内部创建，否则最后的list中每个元素都会指向同一个map，
				//而且由于map的key的不可重复性，后面的值会全部覆盖掉前面的值 
				Map<String, String> map = new TreeMap<String, String>(); 
				for (int i = 0; i < length1; i++) { 
					map.put(dataTitle.getColumnName(i + 1), DataSet.getString(dataTitle.getColumnName(i + 1))); 
					// System.out.println(dataTitle.getColumnName(i+1));  
				} 
				list.add(map); 
				}
		} catch (SQLException e) { 
			System.out.println("查询语句执行失败！"); 
			e.printStackTrace(); 
		} 
		JdbcConnUtil.close(pStatement, DataSet); 
		return list; 
	} 
	/* * 
	该方法提供了增删改的语句的执行，返回受影响的行数 
	* String properties----数据库配置文件的绝对地址； 
	* String sql-----要执行的sql语句 
	* String... args----上述sql语句中如果包含占位符?，就需要传入占位符相应的参数值 
	*/ 
	public static int update(String properties, String sql, String... args) { 
		Connection connection = JdbcConnUtil.getConn(properties); 
		PreparedStatement pStatement = null; 
		try { 
			// 预编译sql 
			pStatement = connection.prepareStatement(sql); 
		} catch (SQLException e) { 
			System.out.println("sql预编译失败！"); 
			e.printStackTrace(); 
		} 
		for (int i = 0; i < args.length; i++) { 
			try { 
				// 给预编译之后的sql中的参数（占位符的位置）赋值 
				pStatement.setString(i + 1, args[i]); 
			} catch (SQLException e) { 
				System.out.println("更新sql占位符赋值失败"); 
				e.printStackTrace(); 
			} 
		} 
		int byUpdate = 0; 
		try { 
			// 执行增删改的操作 
			byUpdate = pStatement.executeUpdate(); 
		} catch (SQLException e) { 
			System.out.println("执行增删改异常"); 
			e.printStackTrace(); 
		} 
		if (byUpdate > 0) { 
			System.out.println("更新增删改操作成功,更新了" + byUpdate + "条数据"); 
		} else { 
			System.out.println("更新操作失败"); 
		} 
		JdbcConnUtil.close(pStatement); 
		return byUpdate; 
	} 
}
