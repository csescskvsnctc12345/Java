package android;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import databasebeans.SQLManager;

/**
 * Servlet implementation class StoreListServlet
 */
@WebServlet("/StoreListServlet")
public class StoreListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoreListServlet() {
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

		System.out.println("android成功");

		ArrayList<ArrayList<String>> AryTbl = new ArrayList<ArrayList<String>>();
		SQLManager sqlm = new SQLManager();
		String sql = "SELECT pharmacy_id, pharmacy_name FROM pharmacy;";
		try {
			sqlm.establishConnection();
			AryTbl = sqlm.SelectSql(sql);
			sqlm.terminateConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		ArrayList<HashMap<String, String>> aryhash = new ArrayList<HashMap<String, String>>();
		for(ArrayList<String> rec:AryTbl){
			HashMap<String, String> hash = new HashMap<String, String>();
			hash.put("store_id", rec.get(0));
			hash.put("store_name", rec.get(1));
			aryhash.add(hash);
		}

		Gson gson = new Gson();

		response.setContentType("application/json; charset=UTF-8");
		response.getOutputStream().write(gson.toJson(aryhash).getBytes("UTF-8"));

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
