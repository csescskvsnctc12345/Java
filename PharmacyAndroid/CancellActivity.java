package local.android.hal_work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class CancellActivity extends Activity {

	// インテントリクエストコード
		private static final int REQUEST_SCAN = 0;

		private static final String ACCESS_URL = "http://172.20.10.2:8080/hal_work2/AndroidCancellServlet";

		private static String resultStr;


		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_cancell);

			//バックボタン用
			ActionBar actionBar = getActionBar();
			actionBar.setDisplayHomeAsUpEnabled(true);
		}

		public void clickEventScan(View v) {
			// バーコードスキャナの呼び出し
			callBarcodeScanner();
		}

		private void callBarcodeScanner() {
			Intent intentScan = new Intent("com.google.zxing.client.android.SCAN");

			try {
				startActivityForResult(intentScan, REQUEST_SCAN);
			}
			catch(ActivityNotFoundException e) {
				// バーコードスキャナが端末にインストールされていなければ、
				// AndroidMarketを起動する。
				Uri uri = Uri.parse("market://details?id=com.google.zxing.client.android");

				Intent intentMarket = new Intent(Intent.ACTION_VIEW,uri);

				try {
					startActivity(intentMarket);
				}
				catch(ActivityNotFoundException ee) {
				}
			}
		}

		// QRコードスキャナーからの返り値を得る。
		@Override
		protected void onActivityResult(int request, int result, Intent intent) {
			switch(request) {
			case REQUEST_SCAN:
				if(result == Activity.RESULT_OK) {
					//読み取り結果がresultStrに入って来る
					resultStr = intent.getStringExtra("SCAN_RESULT");

					Builder builder = new Builder(CancellActivity.this);
					builder.setTitle("予約キャンセル");
					builder.setMessage("予約キャンセルしますか？");
					builder.setNegativeButton("キャンセル", new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO 自動生成されたメソッド・スタブ

						}
					});
					builder.setPositiveButton("予約キャンセルする", new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO 自動生成されたメソッド・スタブ

							RestAccess access = new RestAccess();
							access.execute(ACCESS_URL+"?QRcode="+resultStr);//非同期通信を開始引数としてURLを渡す
						}
					});
					builder.create().show();

				}
				break;
			default:
				break;
			}
		}

		/**
		 * オプションメニューの処理
		 * @param item
		 */
		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			int itemId = item.getItemId();
			switch (itemId) {
			 case android.R.id.home:
		            finish();
		            return true;
			default:
				break;
			}
			return super.onOptionsItemSelected(item);//defaultの値を返す場合false? それ以外ture?
		}

		private class RestAccess extends AsyncTask<String, Void, String>{

			/**
			 * URLを受け取って通信する
			 */
			@Override
			protected String doInBackground(String... params) {
				String urlStr = params[0];

				HttpURLConnection con = null;
				InputStream is = null;
				String result = null;

				try {
					URL url = new URL(urlStr);
					con = (HttpURLConnection) url.openConnection();
					con.setRequestMethod("GET");
					con.connect();
					is = con.getInputStream();

					result = is2String(is);
				} catch (MalformedURLException ex) {
				}
				catch (IOException ex) {
				}
				finally{
					if(con != null){
						con.disconnect();
					}

					try{
						if(is != null){
							is.close();
						}
					}
					catch (IOException ex) {
						Log.e("", "", ex);
					}
				}
				return result;
			}

			/**
			 * doInBackgroundメソッドの実行後にメインスレッドで実行
			 * doInBackgroundメソッドの戻り値をこのメソッドの引数として受け取る
			 * その結果を画面に反映させることができる。
			 */
			@Override
			protected void onPostExecute(String result){
				int flg = 0;
				//JSON配列をキーで受け取る
				try {
					JSONObject rootJSON = new JSONObject(result);
					flg = rootJSON.getInt("msg");
				} catch (JSONException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
				Builder builder = new Builder(CancellActivity.this);
				if(flg == 0){
					builder.setTitle("予約キャンセル");
					builder.setMessage("予約キャンセル完了しました。");
					builder.setPositiveButton("OK", new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO 自動生成されたメソッド・スタブ

							Intent intent = new Intent(CancellActivity.this,AddressPrefecturesActivity.class);
							intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
							startActivity(intent);
						}
					});
					builder.create().show();
				}else if(flg == 1){
					builder.setTitle("予約キャンセル");
					builder.setMessage("予約されていません。");
					builder.setPositiveButton("OK", new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO 自動生成されたメソッド・スタブ
						}
					});
					builder.create().show();
				}

			}
		}

		/**
		 * 文字化け対策
		 * @param is
		 * @return
		 * @throws IOException
		 */
		private String is2String(InputStream is) throws IOException{
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			StringBuffer sb = new StringBuffer();
			char[] b = new char[1024];
			int line;
			while(0 <= (line = reader.read(b))){
				sb.append(b, 0, line);
			}
			return sb.toString();
		}


		/**
		 * JSONArray配列をHashMapに置き換える。
		 * @param jsonArray
		 * @return
		 */
		private ArrayList<HashMap<String, String>> sortJSON(JSONArray jsonArray){
			ArrayList<HashMap<String, String>> arySort = new ArrayList<HashMap<String,String>>();

			for(int i = 0; i < jsonArray.length(); i++){
				HashMap<String, String> map = new HashMap<String, String>();
				try {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					String strJson = jsonObject.toString();
					String[] strJsons = strJson.split("\"", 0);

					//Value側の数値を表すflg
					int flg = 0;
					for(int x = 0; x < strJsons.length; x++){
						//奇数かつ０でないかつValue側の値でない(Key側)時
						if(x%2 != 0 && x != 0 && flg != x){
							map.put(strJsons[x], strJsons[x+2].replace("\\", ""));
							flg = (x + 2);
						}
					}
					arySort.add(map);
				} catch (JSONException e) {
					Log.e("JSON配列ソートERROR→", e.getMessage());
				}
			}
			return arySort;
		}
}
