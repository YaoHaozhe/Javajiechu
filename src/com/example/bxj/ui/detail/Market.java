package com.example.bxj.ui.detail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.bxj.R;
import com.example.bxj.model.Good;
import com.example.jc.adapters.GoodAdapter;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Market extends Activity {
	private ImageView web;
	private TextView tt;
	private String wz = "yaohaozhe.com";
	private List<Good> goodlist = new ArrayList<Good>();
	GoodAdapter adapter;
	private String cid;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.market);
		ImageView web = (ImageView) findViewById(R.id.VR1);
		TextView tt = (TextView)findViewById(R.id.tt);
		Intent intent = getIntent();
		cid = intent.getStringExtra("cid");
		if (cid != null) {
			Log.d("tesss", cid+"");
		}
		web.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Market.this,marketweb.class);							
				startActivity(intent);
			}
		});
		tt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Market.this,market1web.class);							
				startActivity(intent);
			}
		});
		initDatas();
		adapter = new GoodAdapter(Market.this, R.layout.good_show_two,
				goodlist);
		ListView listView = (ListView) findViewById(R.id.good_list2);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Good good = goodlist.get(position);
				System.out.println(String.valueOf(goodlist.get(position)
						.getGid()));
				Intent intent = new Intent(Market.this, buy.class);
				intent.putExtra("gid",
						String.valueOf(goodlist.get(position).getGid()));
				if (cid != null) {
					intent.putExtra("cid", cid);
				}
				startActivity(intent);
				Toast.makeText(Market.this, good.getGname(),
						Toast.LENGTH_SHORT).show();
			}
		});

	}

	// 获取商品数据
	private void initDatas() {
		int sid = 2;
		String url = "http://" + wz + "/getgoodbysid?sid=" + sid;
		OkHttpClient client = new OkHttpClient();
		Log.e("TAG", "OkHttpClient");
		Request request = new Request.Builder().url(url).get().build();
		Log.e("TAG", "Request");

		client.newCall(request).enqueue(new Callback() {

			public void onResponse(Response arg0) throws IOException {
				Log.e("TAG", "onResponse");
				String string = arg0.body().string();
				System.out.println(string);
				ArrayList<Good> list = JSON.parseObject(string,
						new TypeReference<ArrayList<Good>>() {
						});
				goodlist.addAll(list);
				System.out.println(goodlist.size());
			}

			@Override
			public void onFailure(Request arg0, IOException arg1) {
				// TODO Auto-generated method stub

			}
		});

	}

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
