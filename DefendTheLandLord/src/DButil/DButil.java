package DButil;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DButil {
	//table
	public static final String TABLE_USER = "User";
	public static final String TABLE_USER_INFO = "user_info";
	
	public static Connection getConnect() throws SQLException{
		String url = "jdbc:mysql://localhost:3306/fightlandlords";//数据库的url
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");//java反射  固定写法
			conn = (Connection) DriverManager.getConnection(url,"root","");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			System.out.println("SQLException"+e.getMessage());
			System.out.println("SQLState"+e.getSQLState());
			System.out.println("VendorError"+e.getErrorCode());
		}
		return conn;
	}
	
}
