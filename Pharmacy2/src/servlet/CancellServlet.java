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
 * Servlet implementation class CancellServlet
 */
@WebServlet("/CancellServlet")
public class CancellServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancellServlet() {
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
		String sqldel = "";

		String msg = "予約されていません";//エラー用メッセージ


		RequestDispatcher dsp = request.getRequestDispatcher("/cancell.jsp"); //送り先
		RequestDispatcher resultdsp = request.getRequestDispatcher("/cancell_success.jsp"); //送り先
		SQLManager sqlm = new SQLManager();

		sql = "SELECT barcode_id FROM reservation WHERE barcode_id = '"+barcode_id+"';";

		sqldel = "DELETE FROM reservation WHERE barcode_id = '"+barcode_id+"';";

		try {
			sqlm.establishConnection();
			boolean booSql = sqlm.Check(sql);//読み込んだバーコードで予約が存在するか


			if(booSql){
				System.out.println("キャンセル");
				sqlm.UpdateSql(sqldel);
				sqlm.terminateConnection();
				resultdsp.forward(request, response);
				return;
			}else{
				System.out.println("失敗");
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
