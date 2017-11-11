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
@WebServlet(description = "������--��֤��¼", urlPatterns = { "/Home/LoginServlet" }, initParams = {  
        @WebInitParam(name = "userName", value = "hans", description = "�û�����") })
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
		
		
		String username = request.getParameter("uname");//��request�л�ȡ��Ϊuname�Ĳ�����
//		byte  b[]=username.getBytes("ISO-8859-1"); 
//		username=new String(b);
		
//		JSONArray json = JSONArray.fromObject(username);0
//		JSONObject jsonOne;
//		Map<String,String> map = null;
		
		if(username.equals("") || username == null || username.length()<=0 ){
			response.setContentType("text/html;charset=utf-8");
			PrintWriter pw = response.getWriter();
			errorMsg = "error 101,������û���!!! �û�������Ϊ��";
			pw.println(errorMsg);
			return;
		}
		
		System.out.println("user's name is:" + username +"------- "+resMsg);
		
		try {
			Connection connect = DButil.getConnect();
			Statement statement = (Statement)connect.createStatement();//ͨ��statement�������ݿ�
			
			// ��ѯ���������һ��ResultSet���ϣ�û�в鵽���ʱResultSet�ĳ���Ϊ0 
			String sqlQuery = "select * from " + DButil.TABLE_USER + " where userName='" + username + "'";
			test = statement.executeQuery(sqlQuery);
			if(test.next()){
				result = "login success";
				resMsg = "welcome";
				U3dApplication uu = new U3dApplication();
				
				uu.start();
			}else{
			//��ѯ�������ڵ��˺���Ϣ�����µ��û������������ݿ�
				String sqlInsertPass = "insert into " + DButil.TABLE_USER + "(userName) values('"+ username + "')";
				statement.execute(sqlInsertPass);
				result = "���û���First login,login successfully";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter pw = response.getWriter();//��ȡresponse�������
		pw.println(result);//ͨ���������ҵ���߼��Ľ�����
		pw.println(resMsg);
		pw.flush();//ǿ�н������������ݷ��ͳ�ȥ�����صȵ�����������
		
		response.getWriter().append("\n").append("Served at: ").append(request.getContextPath());
		response.getWriter().append("\n��ʼ������userName = " + getInitParameter("userName"));  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
