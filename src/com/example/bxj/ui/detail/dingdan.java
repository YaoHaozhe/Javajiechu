package com.example.bxj.ui.detail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.bxj.R;
import com.example.jc.adapters.GoodAdapter;
import com.example.jc.adapters.dingdanAdapter;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.example.bxj.model.Good;
import com.example.bxj.model.Orders;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class dingdan extends Activity {
//	private ImageView web;
//	private String wz = "yaohaozhe.com";
//	private List<Orders> goodlist = new ArrayList<Orders>();
	dingdanAdapter adapter;
	private String cid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dingdani);
		
		Intent intent = getIntent();
		cid = intent.getStringExtra("cid");
		if (cid != null) {
			Log.d("tesss", cid+"");
		}
		
		//initDatas();// 初始化数据商品
//		adapter = new dingdanAdapter(dingdan.this, R.layout.dingdani,
//				goodlist);
//		ListView listView = (ListView) findViewById(R.id.good_list3);
//		listView.setAdapter(adapter);

	}

	// 获取商品数据
//	private void initDatas() {
//		int cid = 1;
//		String url = "http://" + wz + "/selecyordersbycid?sid=" + cid;
//		OkHttpClient client = new OkHttpClient();
//		Log.e("TAG", "OkHttpClient");
//		Request request = new Request.Builder().url(url).get().build();
//		Log.e("TAG", "Request");
//
//		client.newCall(request).enqueue(new Callback() {
//
//			public void onResponse(Response arg0) throws IOException {
//				Log.e("TAG", "onResponse");
//				String string = arg0.body().string();
//				System.out.println(string);
//				ArrayList<Orders> list = JSON.parseObject(string,
//						new TypeReference<ArrayList<Orders>>() {
//						});
//				goodlist.addAll(list);
//				System.out.println(goodlist.size());
//			}
//
//			@Override
//			public void onFailure(Request arg0, IOException arg1) {
//				// TODO Auto-generated method stub
//
//			}
//		});
//
//	}

	// @Override
	// protected void onResume() {
	// super.onResume();
	// Intent intent = new Intent();
	// intent.setAction("android.intent.action.VIEW");
	// Uri content_url = Uri
	// .parse("http://720yun.com/t/5c4jO5hfum8?pano_id=2181980");
	// intent.setData(content_url);
	// startActivity(intent);
	// Log.e("TAG", "start onResume~~~");
	// }

	@Override
	protected void onStop() {
		super.onStop();
		this.finish();
		Log.e("TAG", "start onStop~~~");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
