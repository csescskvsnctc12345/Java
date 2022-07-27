package local.android.hal_work;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class AddressMunicipalityActivity extends ListActivity {

	private List<Map<String,String>> _list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_address_municipality);

		_list = createList();

		//バックボタン用
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		String[] from = {"municipality"};
		int[] to = {android.R.id.text1};
		SimpleAdapter adapter = new SimpleAdapter(AddressMunicipalityActivity.this, _list, android.R.layout.simple_expandable_list_item_2, from, to);
		setListAdapter(adapter);
	}



	@Override
	public void onListItemClick(ListView listView, View view, int position, long id){
		super.onListItemClick(listView, view, position, id);

		Map<String, String> item = _list.get(position);
		String municipality = item.get("municipality");

		Intent intent = new Intent(AddressMunicipalityActivity.this, StoreListActivity.class);
		startActivity(intent);

	}


	private List<Map<String, String>> createList(){
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();

		Map<String,String> map = new HashMap<String, String>();
		map.put("municipality", "北区");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("municipality", "西淀川区");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("municipality", "中央区");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("municipality", "東淀川区");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("municipality", "鶴見区");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("municipality", "住之江区");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("municipality", "都島区");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("municipality", "港区");
		list.add(map);

		map = new HashMap<String, String>();
		map.put("municipality", "浪速区");
		list.add(map);

		return list;
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

}
