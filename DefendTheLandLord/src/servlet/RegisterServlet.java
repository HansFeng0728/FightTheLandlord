package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import DButil.DButil;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(description = "学习Servlet创建的", urlPatterns = { "/Home/RegisterServlet" }, initParams = {  
        @WebInitParam(name = "userName", value = "hans", description = "用户姓名") })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("userName");
		String password = request.getParameter("pwd");

		System.out.println("user's name is:" + username + " user's password is:" + password);

		String resCode = "";
		String resMsg = "";
		String userId = "";

		try {
			Connection connect = DButil.getConnect();
			Statement statement = (Statement) connect.createStatement();// Statement可以理解为数据库操作实例，对数据库的所有操作都通过它来实现
			ResultSet result;

			String sqlQuery = "select * from " + DButil.TABLE_USER + " where userName =" + username + ";";

			// 查询类操作返回一个ResultSet集合，没有查到结果时ResultSet的长度为0
			result = statement.executeQuery(sqlQuery);
			// 首先查询是不是有已经存在的账号
			if (result.next()) {// 已存在
				resCode = "201";
				resMsg = "该账号已经存在，请直接登录或注册其他账号";
				userId = "";
			} else {
				String sqlInsertPass = "insert into" + DButil.TABLE_USER + "(userName,Password) values('" + username
						+ "','" + password + "')";
				// 更新类操作返回一个int类型的值，代表该操作影响到的行数
				int row1 = statement.executeUpdate(sqlInsertPass); // 插入帐号密码
				if (row1 == 1) {
					String sqlQueryId = "select userId from " + DButil.TABLE_USER + "where userName='" + username + "'";
					ResultSet result2 = statement.executeQuery(sqlQueryId);// 查询新增记录的userId
					if (result2.next()) {
						userId = result2.getInt("userId") + "";
					}
					String sqlInsertInfo = "insert into " + DButil.TABLE_USER_INFO + "(userId) values('" + userId + "')";
					int row2 = statement.executeUpdate(sqlInsertInfo); // 在用户信息表中插入刚注册的Id
					if (row2 == 1) { // 两个表中都插入成功，从业务角度认定为注册成功
						resCode = "100";
						resMsg = "注册成功";
					}
				} else {
					resCode = "202";
					resMsg = "用户信息表插入错误";
					userId = "";
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		HashMap<String, String> map = new HashMap<>();
		map.put("resCode", resCode);
		map.put("resMsg", resMsg);
		map.put("userId", userId);

		response.setContentType("text/html;charset=utf-8"); // 设置响应报文的编码格式
		PrintWriter pw = response.getWriter(); // 获取 response 的输出流
		pw.println(map.toString()); // 通过输出流把业务逻辑的结果输出
		pw.flush();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
