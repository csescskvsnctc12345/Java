package servlet;

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
import javax.servlet.http.HttpSession;

import databasebeans.SQLManager;

/**
 * Servlet implementation class TimeServlet
 */
@WebServlet("/TimeServlet")
public class TimeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TimeServlet() {
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
		String sql = "";
		int intTime = 0;//SQL用
		String strTime = "";//SQL用

		SQLManager sqlm = new SQLManager();
		ArrayList<HashMap<String, String>> aryHash = new ArrayList<HashMap<String, String>>();//複数送信用

		RequestDispatcher dsp = request.getRequestDispatcher("/time.jsp"); //送り先

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
				if(null != sqlm.selectUser(store_id, strTime)){
					hash = sqlm.selectUser(store_id, strTime);
					aryHash.add(hash);
				}else{
					hash.put("reservation_time", strTime);
					hash.put("counttime", "unknown");
					aryHash.add(hash);
				}
				sqlm.terminateConnection();
			} catch (ClassNotFoundException | SQLException | ParseException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

		}
		HttpSession session = request.getSession(true);
		session.setAttribute("store_id", store_id);
		request.setAttribute("data",aryHash);
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
