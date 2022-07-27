package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import databasebeans.SQLManager;


/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerServlet() {
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

		String name = request.getParameter("customer_name");
		String address_1 = request.getParameter("address_1");
		String address_2 = request.getParameter("address_2");
		String tel = request.getParameter("tel");
		String sql = "";
		String result_message = "";

		SQLManager sqlm = new SQLManager();
		RequestDispatcher dsp = request.getRequestDispatcher("/index.jsp"); //送り先

		if(name.equals("")){
			result_message = "空白があります。";
			request.setAttribute("massage",result_message);
			dsp.forward(request, response);
			return;
		}

		if(address_1.equals("")){
			result_message = "空白があります。";
			request.setAttribute("massage",result_message);
			dsp.forward(request, response);
			return;
		}

		if(address_2.equals("")){
			result_message = "空白があります。";
			request.setAttribute("massage",result_message);
			dsp.forward(request, response);
			return;
		}

		if(tel.equals("")){
			result_message = "空白があります。";
			request.setAttribute("massage",result_message);
			dsp.forward(request, response);
			return;
		}

		sql = "INSERT INTO customer(customer_name, address_1, address_2, tel) ";
		sql += "VALUES('"+name+"','"+address_1+"','"+address_2+"','"+tel+"');";

		try {
			sqlm.establishConnection();
			boolean booSql = sqlm.UpdateSql(sql);//引数：SQL文戻り値boolean型SQL成功か判定
			sqlm.terminateConnection();
			if(booSql){
				result_message = "登録完了しました。";
				request.setAttribute("massage",result_message);
				dsp.forward(request, response);
				return;
			}else{
				result_message = "登録に失敗しました。";
				request.setAttribute("massage",result_message);
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
