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
 * Servlet implementation class BarcodeServlet
 */
@WebServlet("/BarcodeServlet")
public class BarcodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BarcodeServlet() {
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

		String barcode_id = request.getParameter("barcode_id");
		String sql = "";

System.out.println("成功"+barcode_id);

		SQLManager sqlm = new SQLManager();
		RequestDispatcher dsp = request.getRequestDispatcher("/barcode_finish.jsp"); //送り先

		sql = "UPDATE reservation SET ";
		sql += "state_flg = 1 ";
		sql += "WHERE barcode_id = '"+barcode_id+"';";

		try {
			sqlm.establishConnection();
			sqlm.UpdateSql(sql);//処理を完了するフラグをアップデートする
			sqlm.terminateConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		request.setAttribute("msg","受け取り完了しました。");
		dsp.forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
