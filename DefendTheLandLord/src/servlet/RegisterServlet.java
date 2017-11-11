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
@WebServlet(description = "ѧϰServlet������", urlPatterns = { "/Home/RegisterServlet" }, initParams = {  
        @WebInitParam(name = "userName", value = "hans", description = "�û�����") })
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
			Statement statement = (Statement) connect.createStatement();// Statement�������Ϊ���ݿ����ʵ���������ݿ�����в�����ͨ������ʵ��
			ResultSet result;

			String sqlQuery = "select * from " + DButil.TABLE_USER + " where userName =" + username + ";";

			// ��ѯ���������һ��ResultSet���ϣ�û�в鵽���ʱResultSet�ĳ���Ϊ0
			result = statement.executeQuery(sqlQuery);
			// ���Ȳ�ѯ�ǲ������Ѿ����ڵ��˺�
			if (result.next()) {// �Ѵ���
				resCode = "201";
				resMsg = "���˺��Ѿ����ڣ���ֱ�ӵ�¼��ע�������˺�";
				userId = "";
			} else {
				String sqlInsertPass = "insert into" + DButil.TABLE_USER + "(userName,Password) values('" + username
						+ "','" + password + "')";
				// �������������һ��int���͵�ֵ������ò���Ӱ�쵽������
				int row1 = statement.executeUpdate(sqlInsertPass); // �����ʺ�����
				if (row1 == 1) {
					String sqlQueryId = "select userId from " + DButil.TABLE_USER + "where userName='" + username + "'";
					ResultSet result2 = statement.executeQuery(sqlQueryId);// ��ѯ������¼��userId
					if (result2.next()) {
						userId = result2.getInt("userId") + "";
					}
					String sqlInsertInfo = "insert into " + DButil.TABLE_USER_INFO + "(userId) values('" + userId + "')";
					int row2 = statement.executeUpdate(sqlInsertInfo); // ���û���Ϣ���в����ע���Id
					if (row2 == 1) { // �������ж�����ɹ�����ҵ��Ƕ��϶�Ϊע��ɹ�
						resCode = "100";
						resMsg = "ע��ɹ�";
					}
				} else {
					resCode = "202";
					resMsg = "�û���Ϣ��������";
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

		response.setContentType("text/html;charset=utf-8"); // ������Ӧ���ĵı����ʽ
		PrintWriter pw = response.getWriter(); // ��ȡ response �������
		pw.println(map.toString()); // ͨ���������ҵ���߼��Ľ�����
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
