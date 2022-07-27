package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.arnx.jsonic.JSON;

import databasebeans.DatabaseManager;
import databasebeans.SQLManager;


/**
 * Servlet implementation class DetailsServlet
 */
@WebServlet("/DetailsServlet")
public class DetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		/******************薬の詳細を表示する為にAjaxでここに飛ぶ********************/
		response.setContentType("application/json; charset=utf-8");
	    response.setHeader("Access-Control-Allow-Origin", "http://localhost"); //ここは個人のサーバ環境によって異なる．

	    String barcode_id = request.getParameter("barcode_id");
	    String sql = "";
		String json = "";

	    PrintWriter out = response.getWriter();//Ajaxの返り値の為

	    ArrayList<String> columnname = new ArrayList<String>();//カラムの名前を格納するオブジェクト
		ArrayList<String> lineName = new ArrayList<String>();//データベースのロー（データ）を格納するオブジェクト
		ArrayList<ArrayList<String>> lineNameAry = new ArrayList<ArrayList<String>>();//データベースのデータを全て格納する場所
		SQLManager aryTbl = new SQLManager();
		DatabaseManager dbm = new DatabaseManager();


	    sql = "SELECT medicine.medicine_name,medicine_detail.quantity,medicine_detail.message ";
	    sql += "FROM medicine_detail INNER JOIN medicine ON medicine.medicine_id = medicine_detail.medicine_id ";
		sql += "WHERE barcode_id = '"+barcode_id+"';";
		System.out.println(sql);

		try {
			aryTbl.establishConnection();
			lineNameAry = aryTbl.SelectSql(sql);//SELECT文の行を全てlineNameAryに格納
			aryTbl.terminateConnection();//データベース切断

			dbm.establishConnection();
			Statement st = dbm.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			ResultSetMetaData rsmd  =rs.getMetaData();

			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				columnname.add(rsmd.getColumnName(i));//テーブルにあるカラムを全てgetする
			}

			//JSONのデータ型を作るループ文
			json+="[";
			for(int j=0;j<lineNameAry.size();j++){//テーブルの行の数だけループする
				lineName = lineNameAry.get(j);//テーブルのｊ番目の行をlineNameに格納する
				if(j!=0){
					json+=",";
				}
				json+="{";
				for(int i=0;i<columnname.size();i++){//テーブルのカラム(列)の数だけループする
					if(i!=0){
						json+=",";
					}
					json += "\""+columnname.get(i)+"\":\""+lineName.get(i)+"\"";
				}
				json+="}";
			}
			json+="]";

			rs.close();
			st.close();
			dbm.terminateConnection();//データベース切断
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		out.println(JSON.encode(json));

	}

}
