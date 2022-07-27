package local.android.hal_work;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class AddressPrefecturesActivity extends ListActivity  {

	private List<Map<String,String>> _list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_address_prefectures);

		//HashMapをリストに入れる
		_list = createList();

		String[] from = {"prefectures"};
		int[] to = {android.R.id.text1};
		SimpleAdapter adapter = new SimpleAdapter(AddressPrefecturesActivity.this, _list, android.R.layout.simple_expandable_list_item_2, from, to);
		setListAdapter(adapter);//作ったリストをセットする
	}

	/**
	 * アクションバー用生成
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();//MenuInflaterによるXMLの取得
		inflater.inflate(R.menu.address_prefectures, menu);
		//アイコン表示する為
		MenuItem cancell = menu.findItem(R.id.Cancell);
		cancell.setIcon(android.R.drawable.ic_menu_close_clear_cancel);//アイコンをセット
		return true;
	}


	/**
	 * アクションバー選択した時の処理
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.Cancell) {
			Intent intent = new Intent(AddressPrefecturesActivity.this, CancellActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	/**
	 * リスト選択した時
	 */
	@Override
	public void onListItemClick(ListView listView, View view, int position, long id){
		super.onListItemClick(listView, view, position, id);

		Intent intent = new Intent(AddressPrefecturesActivity.this, AddressMunicipalityActivity.class);
		startActivity(intent);

	}


	/**
	 * リスト作成メソッド
	 * @return
	 */
	private List<Map<String, String>> createList(){
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();

		Map<String,String> map = new HashMap<String, String>();
		map.put("prefectures", "北海道");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("prefectures", "青森県");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("prefectures", "岩手県");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("prefectures", "宮城県");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("prefectures", "秋田県");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("prefectures", "山形県");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("prefectures", "福島県");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("prefectures", "茨城県");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("prefectures", "栃木県");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("prefectures", "群馬県");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("prefectures", "埼玉県");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("prefectures", "千葉県");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("prefectures", "東京都");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("prefectures", "神奈川県");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("prefectures", "新潟県");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("prefectures", "富山県");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("prefectures", "石川県");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("prefectures", "福井県");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("prefectures", "山梨県");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("prefectures", "長野県");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("prefectures", "岐阜県");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("prefectures", "静岡県");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("prefectures", "愛知県");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("prefectures", "三重県");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("prefectures", "滋賀県");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("prefectures", "京都府");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("prefectures", "大阪府");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("prefectures", "兵庫県");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("prefectures", "奈良県");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("prefectures", "和歌山県");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("prefectures", "鳥取県");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("prefectures", "島根県");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("prefectures", "岡山県");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("prefectures", "広島県");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("prefectures", "山口県");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("prefectures", "徳島県");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("prefectures", "香川県");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("prefectures", "愛媛県");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("prefectures", "高知県");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("prefectures", "福岡県");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("prefectures", "佐賀県");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("prefectures", "長崎県");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("prefectures", "熊本県");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("prefectures", "大分県");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("prefectures", "宮崎県");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("prefectures", "鹿児島県");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("prefectures", "沖縄県");
		list.add(map);

		return list;
	}
}
