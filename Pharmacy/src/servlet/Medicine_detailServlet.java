package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import databasebeans.SQLManager;

import utility.CreateCode;

/**
 * Servlet implementation class Medicine_detailServlet
 */
@WebServlet("/Medicine_detailServlet")
public class Medicine_detailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Medicine_detailServlet() {
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

		String customer_id = request.getParameter("customer_id");
		String[] medicine = request.getParameterValues("medicine_id");
		String[] quantity = request.getParameterValues("quantity");
		String[] message = request.getParameterValues("message");
		String result_message = "";//エラーメッセージ
		String sql = "";
		String insertSql = "";
		String customer_name = "";
		int flg = 0;

		SQLManager sqlm = new SQLManager();

		ArrayList<HashMap<String, String>> aryHash = new ArrayList<HashMap<String, String>>();//複数送信用
		ArrayList<ArrayList<String>> AryTbl = new ArrayList<ArrayList<String>>();
		String[] medicine_name = new String[medicine.length];

		RequestDispatcher dsp = request.getRequestDispatcher("/medicine_detail.jsp"); //送り先
		RequestDispatcher resultdsp = request.getRequestDispatcher("/print.jsp"); //送り先

		CreateCode cc = new CreateCode();//バーコード生成

		if(customer_id.equals("")){
			result_message = "空白があります。";
			request.setAttribute("massage",result_message);
			dsp.forward(request, response);
			return;
		}

		if(medicine.length == 0){
			result_message = "空白があります。";
			request.setAttribute("massage",result_message);
			dsp.forward(request, response);
			return;
		}

		if(quantity.length == 0){
			result_message = "空白があります。";
			request.setAttribute("massage",result_message);
			dsp.forward(request, response);
			return;
		}


		//入力された薬があるかチェック
		for(int i=0; i<medicine.length; i++){
			sql = "SELECT * FROM medicine WHERE medicine_id = '"+medicine[i]+"';";
			try {
				sqlm.establishConnection();
				boolean booSql = sqlm.Check(sql);//引数：SQL文戻り値boolean型SQL成功か判定
				sqlm.terminateConnection();

				if(!booSql){
					result_message = "存在しないお薬です。";
					request.setAttribute("massage",result_message);
					dsp.forward(request, response);
					return;
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}


		//IDを生成用
		int _id = 1;
		String strId = "";
		String strBarcode = "";

		try {
			strBarcode = idcreate(_id);//バーコードのIDを生成(１回だけ)
			cc.createBarcode(strBarcode);//バーコード生成
			cc.createQR(strBarcode);//QRコード生成

			//データベースに入力された情報を格納していく
			for(int i=0; i<medicine.length; i++){
				strId = idcreate(_id);//薬詳細テーブル用にIDを生成(薬の数だけ生成する)

				//薬の数だけデータベースに格納する
				sqlm.establishConnection();
				insertSql = "INSERT INTO medicine_detail(medicine_detail_id,customer_id,barcode_id,medicine_id,quantity,message) ";
				insertSql += "VALUES('"+strId+"','"+customer_id+"','"+strBarcode+"','"+medicine[i]+"','"+quantity[i]+"','"+message[i]+"');";
				sqlm.UpdateSql(insertSql);
				sqlm.terminateConnection();
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		//薬のIDと一致してる薬の名前を取ってくる
		for(int i=0; i<medicine.length; i++){
			sql = "SELECT medicine_name FROM medicine WHERE medicine_id = '"+medicine[i]+"';";
			try {
				sqlm.establishConnection();
				medicine_name[i] = sqlm.OneSelectSql(sql);
				sqlm.terminateConnection();
System.out.println(medicine_name[i]);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}


		//印刷する為にHashMapに格納してArrayListに入れる
		for(int i=0; i<medicine.length; i++){
			HashMap<String, String> hash = new HashMap<String, String>();
			hash.put("medicine", medicine_name[i]);
			hash.put("quantity", quantity[i]);
			hash.put("message", message[i]);

			aryHash.add(hash);
			flg = 1;
		}

		//送られてきた情報を印刷の画面に送る
		if(flg == 1){
			//患者の名前を取得
			sql = "SELECT customer_name FROM customer WHERE customer_id = '"+customer_id+"';";
			try {
				sqlm.establishConnection();
				customer_name = sqlm.OneSelectSql(sql);
				sqlm.terminateConnection();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

			request.setAttribute("customer_name",customer_name);
			request.setAttribute("barcode",strBarcode);
			request.setAttribute("data",aryHash);
			resultdsp.forward(request, response);
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	//ID生成メソッド
	private String idcreate(int id) throws ClassNotFoundException, SQLException{
		int _id = id;
		String strId = "";//戻り値用
		ArrayList<ArrayList<String>> AryTbl = new ArrayList<ArrayList<String>>();
		SQLManager sqlm = new SQLManager();
		String sql_id = "SELECT medicine_detail_id FROM medicine_detail ORDER BY medicine_detail_id DESC LIMIT 0,1;";
		//IDを生成
		sqlm.establishConnection();
		AryTbl = sqlm.SelectSql(sql_id);
		sqlm.terminateConnection();
		if(null != AryTbl){
			_id = Integer.parseInt(AryTbl.get(0).get(0));
			_id = _id + 1;
		}
		strId = String.valueOf(_id);
		for(int cnt = strId.length();cnt<14;cnt++){
			strId = "0" + strId;
		}

		return strId;
	}

}
