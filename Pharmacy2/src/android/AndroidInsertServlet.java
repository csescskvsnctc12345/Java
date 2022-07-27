package android;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import databasebeans.SQLManager;

/**
 * Servlet implementation class AndroidInsertServlet
 */
@WebServlet("/AndroidInsertServlet")
public class AndroidInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AndroidInsertServlet() {
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
		String time = request.getParameter("datetime");
		String QRcode = request.getParameter("QRcode");
		String sql = "";

		//現在の日付を取得する
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(c.getTime());

		SQLManager sqlm = new SQLManager();

		sql = "INSERT INTO reservation(barcode_id,pharmacy_id,reservation_time,process_flg,state_flg) ";
		sql += "VALUES('"+QRcode+"','"+store_id+"','"+date+" "+time+"',0,0);";
System.out.println(sql);

	try {
		sqlm.establishConnection();
		sqlm.UpdateSql(sql);
		sqlm.terminateConnection();
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
