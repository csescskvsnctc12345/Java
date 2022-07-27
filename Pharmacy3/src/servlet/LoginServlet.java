package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import databasebeans.SQLManager;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");

		String id = request.getParameter("StoreId");
		String password = request.getParameter("Password");
		String sql = "";
		String sql_name = "";
		String store_id = "";
		String store_name = "";

		String msg = "ログイン失敗";//エラー用メッセージ

		RequestDispatcher dsp = request.getRequestDispatcher("/login.jsp"); //送り先
		RequestDispatcher resultdsp = request.getRequestDispatcher("/index.jsp"); //送り先
		SQLManager sqlm = new SQLManager();
		HttpSession session = request.getSession(true);

		sql = "SELECT pharmacy_id FROM pharmacy ";
		sql += "WHERE pharmacy_id = '"+id+"' AND password = '"+password+"';";

		sql_name = "SELECT pharmacy_name FROM pharmacy ";
		sql_name += "WHERE pharmacy_id = '"+id+"' AND password = '"+password+"';";

		try {
			sqlm.establishConnection();
			boolean booSql = sqlm.Check(sql);//引数：SQL文戻り値boolean型SQL成功か判定

			//ログイン成功
			if(booSql){
				store_id = sqlm.OneSelectSql(sql);
				store_name = sqlm.OneSelectSql(sql_name);
				sqlm.terminateConnection();
				session.setAttribute("store_id", store_id);
				session.setAttribute("store_name", store_name);
				resultdsp.forward(request, response);
				return;
			}else{
				sqlm.terminateConnection();
				request.setAttribute("massage",msg);
				dsp.forward(request, response);
				return;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
