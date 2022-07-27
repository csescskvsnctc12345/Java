package databasebeans;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class SQLManager extends DatabaseManager{

	/**
	 * 引数に指定された更新系SQLをデータベースへと発行し、その結果をBoolean型で返します。
	 * true：成功　false：失敗
	 * @param sql 更新系SQL文
	 * @return booSql 更新の有無
	 * @throws SQLException
	 */
	public boolean UpdateSql(String sql) throws SQLException{
		Statement st =con.createStatement();
		boolean booSql = false;
		int intSql = 0;
		try {
			intSql = st.executeUpdate(sql);
		}catch (SQLException e) {
			intSql = 0;
		}
		if(0 == intSql){
			booSql = false;
		}else{
			booSql = true;
		}
		st.close();
		return booSql;
	}

	/**
	 * ログイン確認(存在チェック)
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public boolean Check(String sql) throws SQLException{
		Statement st =con.createStatement();
		ResultSet rs = st.executeQuery(sql);

		boolean result = false;
		//trueだとログインfalseだとログイン失敗
		if(rs.next()){
			result = true;
		}
		rs.close();
		st.close();
		return result;
	}

	/**
	 * 引数に指定された参照系SQLをデータベースへと発行します。
	 * 結果をArrayList型で返し、参照件数が0件の場合はnullを返します。
	 * @param sql 参照系SQL文
	 * @return aryTbl 取得したレコード
	 * @throws SQLException
	 */
	public ArrayList<ArrayList<String>> SelectSql(String sql) throws SQLException{
		Statement st =con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		ResultSetMetaData rsmd;
		ArrayList<ArrayList<String>> aryTbl = new ArrayList<ArrayList<String>>();
		int intRec = 0;
		int column = 0;
		try {
			rsmd = rs.getMetaData();
			column = rsmd.getColumnCount();
			while(rs.next()){//取得あり
				ArrayList<String> rec = new ArrayList<String>();
				for(int cnt = 1;cnt <= column;cnt++){
					rec.add(rs.getString(cnt));
				}
				aryTbl.add(rec);
				intRec = 1;
			}
			if(0 == intRec){
				aryTbl = null;
			}
			rs.close();
		} catch (SQLException e) {
		}
		rs.close();
		st.close();
		return aryTbl;
	}

	/**
	 * 渡されたSQLのカラムを１つだけ取ってくる
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public String OneSelectSql(String sql) throws SQLException{
		Statement st =con.createStatement();
		//データ取得
		ResultSet rs = st.executeQuery(sql);
		//フィールド名取得
		ResultSetMetaData rsmd= rs.getMetaData();
		String aryTbl = "";
		try {
			while(rs.next()){//取得あり
				aryTbl = rs.getString(rsmd.getColumnName(1));//カラムのデータをStringに格納
			}
			rs.close();
		} catch (SQLException e) {
		}
		rs.close();
		st.close();
		return aryTbl;
	}


	/**
	 * テーブルにある一番大きいIDを取ってくる
	 * @return
	 * @throws SQLException
	 */
	public String MaxId() throws SQLException{
		String id = "";
		String sql = "";

		sql = "SELECT max(id) FROM user";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);

		while(rs.next()){
			id = rs.getString("max(id)");
		}
		rs.close();
		st.close();
		return id;
	}
	/**
	 * 渡されたidの行を１行取ってくる
	 * @param id
	 * @return
	 * @throws SQLException
	 * @throws ParseException
	 */
	public HashMap<String,String> selectUser(String id, String time) throws SQLException, ClassNotFoundException, ParseException{
		HashMap<String,String> map = new HashMap<String,String>();

		int intRec = 0;//nullチェックフラグ
		String sql="";

		//時間をカウントする為
		sql = "SELECT reservation_time, count(reservation_time) AS counttime ";
		sql +="FROM reservation ";
		sql +="WHERE pharmacy_id = '"+id+"' AND reservation_time = '2016-01-17 "+time+":00:00' ";
		sql +="GROUP BY reservation_time;";

		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);

		try {
			if(rs.next()){
				SimpleDateFormat sdf = new SimpleDateFormat("HH");
				Date datetime = sdf.parse(rs.getString("reservation_time"));
//				System.out.println(datetime);
				map.put("reservation_time", sdf.format(datetime));
				map.put("counttime", rs.getString("counttime"));
				intRec = 1;
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		if(0 == intRec){
			map = null;
		}

		rs.close();
		st.close();
		return map;
	}
}
