package com.example.bxj.ui.detail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.bxj.R;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.example.bxj.model.Customer;
import com.example.bxj.model.Good;
import com.example.bxj.ui.MainActivity;

public class buy extends Activity {

	private ImageView g_pic;
	private Button goumai;
	private TextView g_tuan;
	private TextView g_men;
	private TextView g_name;
	private TextView g_sname;
	private TextView g_scombo;
	private TextView g_notice;
	private String gid;
	private String cid;
	private String sid;
	private String oid;
	private String wz = "yaohaozhe.com";

	private int gpic;
	private float gtuan;
	private float gmen;
	private String gname;
	private String gsname;
	private String gscombo;
	private String gnotice;
	private String ophone;
	private int oamount = 1;
	private TextView gname1;

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				Good result = (Good) msg.obj;
				ophone = String.valueOf("111");
				gname = String.valueOf(result.getGname());
				gnotice = String.valueOf(result.getGnotice());
				gscombo = String.valueOf(result.getGcombo());
				gsname = String.valueOf(result.getSeller().getSname());
				sid = String.valueOf(result.getSeller().getSid());
				gtuan = (float) result.getGtuan();
				gmen = (float) result.getGmen();
				gpic = result.getGsold();

				g_name.setText(gname);
				g_sname.setText(gsname);
				g_notice.setText(gnotice);
				g_scombo.setText(gscombo);
				g_tuan.setText(String.valueOf(gtuan) + "元/份");
				g_men.setText(String.valueOf(gmen) + "元/份");
				g_pic.setImageResource(gpic);
				break;
			/*
			 * case 2: success_s.setText("success!"); break; case 3:
			 * succ_u.setText("User login success!"); break;
			 */
			}
		}
	};

	private Message msg = new Message();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_buyactivity);
		Intent intent = getIntent();
		gid = intent.getStringExtra("gid");
		System.out.println(gid);
		int id = Integer.parseInt(gid);
		if (id == 4) {
			g_sname = (TextView) findViewById(R.id.gsname);
			g_sname.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(buy.this, Market.class);
					intent.putExtra("cid", cid);
					startActivity(intent);
				}
			});
		}
		else if(id == 5){
			g_sname = (TextView) findViewById(R.id.gsname);
			g_sname.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(buy.this, Chuzuwu.class);
					intent.putExtra("cid", cid);
					startActivity(intent);
				}
			});
		}
		else{
			g_sname = (TextView) findViewById(R.id.gsname);
			g_sname.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(buy.this, Tianping.class);
					intent.putExtra("cid", cid);
					startActivity(intent);
				}
			});
		}
		cid = intent.getStringExtra("cid");
		if (cid != null) {
			Log.d("235t3w35", cid);
		}
		g_pic = (ImageView) findViewById(R.id.gpic);
		goumai = (Button) findViewById(R.id.goumai);
		g_men = (TextView) findViewById(R.id.gmen);
		g_name = (TextView) findViewById(R.id.gname);
		g_tuan = (TextView) findViewById(R.id.gtuan);
		g_sname = (TextView) findViewById(R.id.gsname);
		g_scombo = (TextView) findViewById(R.id.gscombo);
		g_notice = (TextView) findViewById(R.id.gnotice);
		goumai = (Button) findViewById(R.id.goumai);
		goumai.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				sendinsert();
				Toast.makeText(buy.this, "购买成功", Toast.LENGTH_LONG).show();

				Intent intent = new Intent(buy.this, MainActivity.class);

				intent.putExtra("cid", cid);

				startActivity(intent);
			}
		});
		initDatas();// 初始化数据商品

	}

	// // 增加订单
	private void sendinsert() {
		String url = "http://" + wz + "/insertorders?ophone=" + ophone
				+ "&oamount=" + oamount + "&oprice=" + gmen + "&gid=" + gid
				+ "&cid=" + cid;
		OkHttpClient client = new OkHttpClient();
		Log.e("TAG", "OkHttpClient");
		Request request = new Request.Builder().url(url).get().build();
		Log.e("TAG", "Request");

		client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Response arg0) throws IOException {

			}

			@Override
			public void onFailure(Request arg0, IOException arg1) {
				// TODO Auto-generated method stub

			}
		});

	}

	//

	// // 获取商品数据
	private void initDatas() {
		Integer.parseInt(gid);
		String url = "http://" + wz + "/selectgoodbyid?gid=" + gid;
		OkHttpClient client = new OkHttpClient();
		Log.e("TAG", "OkHttpClient");
		Request request = new Request.Builder().url(url).get().build();
		Log.e("TAG", "Request");

		client.newCall(request).enqueue(new Callback() {

			public void onResponse(Response arg0) throws IOException {
				Log.e("TAG", "onResponse");
				String string = arg0.body().string();
				System.out.println(string);
				Good good = JSON.parseObject(string, Good.class);
				if (good.getGid() > 0) {
					// System.out.println("name="+seller.getName()+"password:"+seller.getPassword());
					msg.what = 1;
					msg.obj = good;
					handler.sendMessage(msg);
				}
			}

			@Override
			public void onFailure(Request arg0, IOException arg1) {
				// TODO Auto-generated method stub

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
}
