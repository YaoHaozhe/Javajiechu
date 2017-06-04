package com.example.bxj.ui.detail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.bxj.R;
import com.example.bxj.R.string;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.example.bxj.model.Customer;
import com.example.bxj.model.Good;
import com.example.bxj.ui.MainActivity;
import com.example.jc.adapters.GoodAdapter;

public class sousuo extends Activity {
	private String wz = "yaohaozhe.com";
	private List<Good> goodlist = new ArrayList<Good>();
	private String cid;
	GoodAdapter adapter;
	private String name;
	private Button queren;
	private EditText sousuo;
	private Handler handler = new Handler(Looper.getMainLooper());
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.sousuo);
		queren = (Button) findViewById(R.id.queren);		
		sousuo = (EditText) findViewById(R.id.editsousuo);
		
		
		
		queren.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				adapter.clear();
				name = sousuo.getText().toString();
				if(name.length() != 0)
				{
					initDatas(name);
				}
				if(name.length() == 0)
				{
					Toast.makeText(sousuo.this,"ËÑË÷¿ò²»ÄÜÎª¿Õ", Toast.LENGTH_SHORT).show();
					initDatas("q");
				}
				
			}
		});
		adapter = new GoodAdapter(sousuo.this, R.layout.good_show_two, goodlist);
		ListView listView = (ListView) findViewById(R.id.good_list01);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Good good = goodlist.get(position);
				System.out.println(String.valueOf(goodlist.get(position)
						.getGid()));
				Intent intent = new Intent(sousuo.this, buy.class);
				intent.putExtra("gid",String.valueOf(goodlist.get(position).getGid()));
				if (cid != null) {
					intent.putExtra("cid", cid);
				}
				startActivity(intent);
				Toast.makeText(sousuo.this, good.getGname(), Toast.LENGTH_SHORT)
						.show();
			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void initDatas(String name2) {
		//name = sousuo.toString().trim();
		
		String url = "http://" + wz + "/selectgoodlikename?name=" + name2;
		OkHttpClient client = new OkHttpClient();
		Log.e("TAG", "110");
		Request request = new Request.Builder().url(url).get().build();
		Log.e("TAG", "q");

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
				handler.post(new Runnable() {

					@Override
					public void run() {
						adapter.notifyDataSetChanged();
					}
				});
			}

			@Override
			public void onFailure(Request arg0, IOException arg1) {
				// TODO Auto-generated method stub

			}
		});
	}
}
