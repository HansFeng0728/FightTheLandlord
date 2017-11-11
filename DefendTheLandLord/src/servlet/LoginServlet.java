package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Statement;


import DButil.DButil;
import connect.U3dApplication;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet(description = "服务器--验证登录", urlPatterns = { "/Home/LoginServlet" }, initParams = {  
        @WebInitParam(name = "userName", value = "hans", description = "用户姓名") })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String result = "";
		ResultSet test;
		
		String resMsg = "";
		String errorMsg = "";
		
		
		String username = request.getParameter("uname");//从request中获取名为uname的参数的
//		byte  b[]=username.getBytes("ISO-8859-1"); 
//		username=new String(b);
		
//		JSONArray json = JSONArray.fromObject(username);0
//		JSONObject jsonOne;
//		Map<String,String> map = null;
		
		if(username.equals("") || username == null || username.length()<=0 ){
			response.setContentType("text/html;charset=utf-8");
			PrintWriter pw = response.getWriter();
			errorMsg = "error 101,请输出用户名!!! 用户名不能为空";
			pw.println(errorMsg);
			return;
		}
		
		System.out.println("user's name is:" + username +"------- "+resMsg);
		
		try {
			Connection connect = DButil.getConnect();
			Statement statement = (Statement)connect.createStatement();//通过statement操作数据库
			
			// 查询类操作返回一个ResultSet集合，没有查到结果时ResultSet的长度为0 
			String sqlQuery = "select * from " + DButil.TABLE_USER + " where userName='" + username + "'";
			test = statement.executeQuery(sqlQuery);
			if(test.next()){
				result = "login success";
				resMsg = "welcome";
				U3dApplication uu = new U3dApplication();
				
				uu.start();
			}else{
			//查询不到存在的账号信息，将新的用户名保存在数据库
				String sqlInsertPass = "insert into " + DButil.TABLE_USER + "(userName) values('"+ username + "')";
				statement.execute(sqlInsertPass);
				result = "新用户，First login,login successfully";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter pw = response.getWriter();//获取response的输出流
		pw.println(result);//通过输出流把业务逻辑的结果输出
		pw.println(resMsg);
		pw.flush();//强行将缓冲区的数据发送出去而不必等到缓冲区存满
		
		response.getWriter().append("\n").append("Served at: ").append(request.getContextPath());
		response.getWriter().append("\n初始化参数userName = " + getInitParameter("userName"));  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
