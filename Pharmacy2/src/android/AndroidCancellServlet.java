package android;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import databasebeans.SQLManager;

/**
 * Servlet implementation class AndroidCancellServlet
 */
@WebServlet("/AndroidCancellServlet")
public class AndroidCancellServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AndroidCancellServlet() {
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

		String QRcode = request.getParameter("QRcode");
		String msg = "";

		String sqldel = "";
		String sql = "";

		SQLManager sqlm = new SQLManager();

		sql = "SELECT barcode_id FROM reservation WHERE barcode_id = '"+QRcode+"';";
		sqldel = "DELETE FROM reservation WHERE barcode_id = '"+QRcode+"';";

		try {
			sqlm.establishConnection();
			boolean booSql = sqlm.Check(sql);//読み込んだバーコードで予約が存在するか


			if(booSql){
				System.out.println("キャンセル");
				sqlm.UpdateSql(sqldel);
				sqlm.terminateConnection();
				msg = "{\"msg\":0}";
				response.setContentType("application/json; charset=UTF-8");
				response.getOutputStream().write(msg.getBytes("UTF-8"));
			}else{
				System.out.println("失敗");
				sqlm.terminateConnection();
				msg = "{\"msg\":1}";
				response.setContentType("application/json; charset=UTF-8");
				response.getOutputStream().write(msg.getBytes("UTF-8"));
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
