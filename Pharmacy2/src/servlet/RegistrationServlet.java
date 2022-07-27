package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import databasebeans.SQLManager;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
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
		String store_id = request.getParameter("store_id");
		String time = request.getParameter("time");
		String sql = "";
		String userSql = "";
		String store_name = "";
		String user_name = "";

		RequestDispatcher dsp = request.getRequestDispatcher("/result.jsp"); //送り先
		SQLManager sqlm = new SQLManager();


		System.out.println("bar"+barcode_id);
		System.out.println("store"+store_id);
		System.out.println("time"+time);

		sql = "SELECT pharmacy_name FROM pharmacy WHERE pharmacy_id = '"+store_id+"';";

		userSql = "SELECT customer.customer_name ";
		userSql += "FROM ";
		userSql += "customer INNER JOIN medicine_detail ON customer.customer_id = medicine_detail.customer_id ";
		userSql += "WHERE barcode_id = '"+barcode_id+"' ";
		userSql += "GROUP BY customer.customer_name;";
System.out.println(userSql);
System.out.println(sql);
		try {
			sqlm.establishConnection();
			store_name = sqlm.OneSelectSql(sql);
System.out.println(store_name);
			user_name = sqlm.OneSelectSql(userSql);
System.out.println(user_name);
			sqlm.terminateConnection();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		//現在の日付を取得する
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(c.getTime());

		//SELECTした名前を送る
		request.setAttribute("store_name",store_name);
		request.setAttribute("user_name",user_name);
		request.setAttribute("time",date+" "+time);
		//IDを送る
		request.setAttribute("barcode_id",barcode_id);
		request.setAttribute("store_id",store_id);
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
