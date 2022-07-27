package jqgrid;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
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
 * Servlet implementation class AjaxjQgrid
 */
@WebServlet("/AjaxjQgrid")
public class AjaxjQgrid extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxjQgrid() {
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

		/******************************自動更新用*************************************/
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");

		String store_id = request.getParameter("store_id");
		String sql = "";
		String json = "";

	    PrintWriter out = response.getWriter();//Ajaxの返り値の為

	    ArrayList<String> columnname = new ArrayList<String>();//カラムの名前を格納するオブジェクト
		ArrayList<String> lineName = new ArrayList<String>();//データベースのロー（データ）を格納するオブジェクト
		ArrayList<ArrayList<String>> lineNameAry = new ArrayList<ArrayList<String>>();//データベースのデータを全て格納する場所
		SQLManager aryTbl = new SQLManager();
		DatabaseManager dbm = new DatabaseManager();

		sql = "SELECT reservation.barcode_id,customer.customer_name,reservation.reservation_time,customer.tel,reservation.process_flg ";
		sql += "FROM reservation INNER JOIN (medicine_detail INNER JOIN customer ON medicine_detail.customer_id = customer.customer_id) ";
		sql += "ON reservation.barcode_id = medicine_detail.barcode_id ";
		sql += "WHERE process_flg = 0 AND state_flg = 0 AND pharmacy_id = '"+store_id+"' ";
		sql += "GROUP BY reservation.barcode_id ";
		sql += "ORDER BY reservation.reservation_time ASC;";


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

			if(lineNameAry != null){
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
						//datetimeをSELECTすると何故かミリ秒まで取ってくるのでDateFormatする為
						if("reservation_time".equals(columnname.get(i))){
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
							//String型のdateのデータを一度date型にキャスト
							Date date = sdf.parse(lineName.get(i));

							//date型になったデータをDateFormatでミリ秒を消してJSON配列に入れる
							json += "\""+columnname.get(i)+"\":\""+sdf.format(date)+"\"";

						}else if("process_flg".equals(columnname.get(i))){//処方flgを文字に直す
							json += "\""+columnname.get(i)+"\":\"未完了\"";
						}else{
							json += "\""+columnname.get(i)+"\":\""+lineName.get(i)+"\"";
						}

					}
					json+="}";
				}
				json+="]";
			}

			rs.close();
			st.close();
			dbm.terminateConnection();//データベース切断
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		out.println(JSON.encode(json));

	}

}
