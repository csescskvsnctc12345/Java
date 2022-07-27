package android;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import databasebeans.SQLManager;

/**
 * Servlet implementation class TimeListServlet
 */
@WebServlet("/TimeListServlet")
public class TimeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TimeListServlet() {
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

		System.out.println(store_id);

		String sql = "";
		int intTime = 0;//SQL用
		String strTime = "";//SQL用

		SQLManager sqlm = new SQLManager();
		ArrayList<HashMap<String, String>> aryHash = new ArrayList<HashMap<String, String>>();//複数送信用

		for(int i=8; i<22; i++){
			HashMap<String, String> hash = new HashMap<String, String>();
			//時間をカウントする為
			intTime = i;//8時から21時までの時間を検索する為
			strTime = String.valueOf(intTime);//文字列に直す
			for(int cnt = strTime.length();cnt<2;cnt++){
				strTime = "0" + strTime;//二桁の時間にする為
			}
			try {
				sqlm.establishConnection();
				if(null != sqlm.AndoroidTimeList(store_id, strTime)){
					hash = sqlm.AndoroidTimeList(store_id, strTime);
					aryHash.add(hash);
				}else{
					hash.put("reservation_time", strTime+":00");
					hash.put("counttime", "unknown");
					aryHash.add(hash);
				}
				sqlm.terminateConnection();
			} catch (ClassNotFoundException | SQLException | ParseException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

		}

		Gson gson = new Gson();

		response.setContentType("application/json; charset=UTF-8");
		response.getOutputStream().write(gson.toJson(aryHash).getBytes("UTF-8"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
