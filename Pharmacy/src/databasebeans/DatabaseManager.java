package databasebeans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Database接続を管理するクラス
 * @author
 */
public class DatabaseManager {

	protected static String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
	protected static String DATABASE_TYPE = "jdbc:mysql:";
	protected static String DATABASE_PATH = "//localhost/";
	protected static String DATABASE_NAME = "hal_work?characterEncoding=utf8";
	protected static final String DATABSE_USER = "root";
	protected static final String DATABASE_PASS = "";

	public Connection con;

	/**
	 * コンストラクタ
	 */
	public DatabaseManager() {

	}

	/**
	 * データベース接続
	 * @throws SQLException コネクションが取得できない場合
	 * @throws ClassNotFoundException ドライバが見つからない場合
	 */
	public void establishConnection() throws SQLException,ClassNotFoundException {
		Class.forName(DATABASE_DRIVER);
		this.con = DriverManager.getConnection(DATABASE_TYPE + DATABASE_PATH + DATABASE_NAME, DATABSE_USER,DATABASE_PASS);
	}

	/**
	 * データベース切断
	 * @throws SQLException コネクションが存在しないか、破棄できない場合
	 */
	public void terminateConnection() throws SQLException {
		this.con.close();
	}

	/**
	 * トランザクションを開始します。
	 * @throws SQLException
	 */
	public void beginTransaction() throws SQLException {
		this.con.setAutoCommit(false);
	}

	/**
	 * データベースをロールバックし、トランザクションを終了します。
	 * @throws SQLException
	 */
	public void rollback() throws SQLException {
		this.con.rollback();
		this.con.setAutoCommit(true);
	}

	/**
	 * トランザクションを終了します。
	 * @throws SQLException
	 */
	public void endTransaction() throws SQLException {
		this.con.commit();
		this.con.setAutoCommit(true);
	}

	/**
	 * 管理が複数にまたがるトランザクション処理を実現する為に、<br/>
	 * コネクションを他オブジェクトから引き継ぎます。
	 * @param dbm	引継ぎ元のオブジェクト
	 */
	public void inheritConnection(DatabaseManager dbm) {
		this.con = dbm.con;
	}


	/**
	 * データベース上にてchar型で管理されているIDのために、受け取ったIDを＋１して帰す。(AUTO_INCREMENTをchar型でもこなすためのもの)
	 * @param id 現在データベースに登録されている最大のID値
	 * @return 引数のIDを+1したもの。
	 * @throws NumberFormatException 受け取ったIDが数値変換に失敗した場合
	 */
	public String idPlus(String id) throws NumberFormatException {
		//受け取ったIDの桁数を求める（データベース上にて何桁なのかを求める）。
		Integer intIdLength = id.length();
		//受け取ったIDをInteger型にして+1する。
		Integer intId = Integer.parseInt(id)+1;
		//+1したIDをString型に戻す。
		String strId =  String.valueOf(intId);
		//受け取ったIDの桁数Integer変換して+1した数値との桁数の差を求める（0が何個必要か求める）。
		Integer zeroLength = intIdLength - strId.length();
		for(int i=0 ; zeroLength>i ; i++){
			strId = "0"+strId;
		}
		return strId;
	}
}
