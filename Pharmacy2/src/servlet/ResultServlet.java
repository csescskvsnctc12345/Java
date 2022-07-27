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
 * Servlet implementation class ResultServlet
 */
@WebServlet("/ResultServlet")
public class ResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResultServlet() {
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


		String store_id = request.getParameter("store_id");
		String barcode_id = request.getParameter("barcode_id");
		String time = request.getParameter("time");
		String sql = "";


		SQLManager sqlm = new SQLManager();
		RequestDispatcher dsp = request.getRequestDispatcher("/success.jsp"); //送り先


		sql = "INSERT INTO reservation(barcode_id,pharmacy_id,reservation_time,process_flg,state_flg) ";
		sql += "VALUES('"+barcode_id+"','"+store_id+"','"+time+"',0,0);";
System.out.println(sql);

		try {
			sqlm.establishConnection();
			sqlm.UpdateSql(sql);
			sqlm.terminateConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

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
