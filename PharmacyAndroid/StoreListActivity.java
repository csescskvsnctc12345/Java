package local.android.hal_work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.TextView;

public class StoreListActivity extends ListActivity {

	private SwipeRefreshLayout mSwipeRefreshLayout;

	//Servletと通信するアドレス
	private static final String ACCESS_URL = "http://172.20.10.2:8080/hal_work2/StoreListServlet";

	/**
	 * 新規登録モードを表す定数フィールド
	 */
	static final int MODE_INSERT = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_store_list);

		//バックボタン用
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		RestAccess access = new RestAccess();
		access.execute(ACCESS_URL);//非同期通信を開始引数としてURLを渡す
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

	/**
	 * 更新用
	 */
	@Override
	protected void onResume() {
		super.onResume();
		RestAccess access = new RestAccess();
		access.execute(ACCESS_URL);

	}

	/**
	 * リストを選択した時の処理
	 */
	@Override
	public void onListItemClick(ListView listView, View view, int position, long id){
		super.onListItemClick(listView, view, position, id);

		HashMap<String, String> hash = (HashMap<String, String>) listView.getItemAtPosition(position);

		String storeId = hash.get("store_id");
		String store_name = hash.get("store_name");

		Intent intent = new Intent(StoreListActivity.this, TimeListActivity.class);
		intent.putExtra("store_id", storeId);
		intent.putExtra("store_name", store_name);
		startActivity(intent);
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
			ArrayList<HashMap<String, String>> aryJson = new ArrayList<HashMap<String,String>>();
			try{

				JSONArray jsonArray = new JSONArray(result);
				aryJson = sortJSON(jsonArray);

				String[] from = {"store_id","store_name"};
				int[] to = { android.R.id.text1,android.R.id.text2};
				SimpleAdapter adapter = new SimpleAdapter(StoreListActivity.this, aryJson, android.R.layout.simple_list_item_2, from, to);
				adapter.setViewBinder(new ViewBinder() {

					@Override
					public boolean setViewValue(View view, Object data,
							String textRepresentation) {
						// TODO 自動生成されたメソッド・スタブ
						switch (view.getId()) {
						case android.R.id.text1:
							view.setVisibility(View.GONE);
							break;
						case android.R.id.text2:
							((TextView)view).setTextSize(30);
							break;
						default:
							break;
						}
						return false;
					}
				});
				setListAdapter(adapter);
			}catch (JSONException e) {
				Log.e("JSON受取ERROR→", e.getMessage());
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

	private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
		@Override
		public void onRefresh() {
			// 3秒待機
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					mSwipeRefreshLayout.setRefreshing(false);
					onResume();
				}
			}, 1000);
		}
	};


}
