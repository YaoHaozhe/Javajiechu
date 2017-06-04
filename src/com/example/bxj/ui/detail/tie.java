//package com.example.bxj.ui.detail;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Calendar;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.text.format.Time;
//import android.util.Log;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.Window;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.TypeReference;
//import com.example.bxj.R;
//import com.squareup.okhttp.Callback;
//import com.squareup.okhttp.OkHttpClient;
//import com.squareup.okhttp.Request;
//import com.squareup.okhttp.Response;
//import com.example.bxj.model.Customer;
//import com.example.bxj.model.Tie;
//import com.example.bxj.ui.MainActivity;
//
//public class tie extends Activity {
//
//	private ImageView g_pic;
//	private Button goumai;
//	private String title;
//	private String tiezuozhe;
//	private String tieneirong;
//	private TextView _title;
//	private TextView _tiezuozhe;
//	private TextView _tieneirong;
//	private String cid;
//	private String tid;
//	private String wz = "yaohaozhe.com";
//
//	private int gpic;
//	private String gscombo;
//	private String gnotice;
//
//	private Handler handler = new Handler() {
//		public void handleMessage(Message msg) {
//			switch (msg.what) {
//			case 1:
//				Tie result = (Tie) msg.obj;
//				
//				title = String.valueOf(result.getTname());
//				tiezuozhe = String.valueOf(result.getCustomer().getCname());
//				tieneirong = String.valueOf(result.getTcontent());
//				
//				
//
//				
//				_title.setText(title);
//				_tiezuozhe.setText(tiezuozhe);
//				_tieneirong.setText(tieneirong);
//
//				break;
//			/*
//			 * case 2: success_s.setText("success!"); break; case 3:
//			 * succ_u.setText("User login success!"); break;
//			 */
//			}
//		}
//	};
//
//	private Message msg = new Message();
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		setContentView(R.layout.tie);
//		Intent intent = getIntent();
//
//		cid = intent.getStringExtra("cid");
//		if (cid != null) {
//			Log.d("235t3w35", cid);
//		}
//		
//		_title = (TextView) findViewById(R.id.title);
//		_tiezuozhe = (TextView) findViewById(R.id.tiezuozhe);
//		_tieneirong = (TextView) findViewById(R.id.tieneirong);
//		
//		initDatas();// 初始化数据商品
//
//	}
//
//
//	// // 获取帖子数据
//	private void initDatas() {
//		Integer.parseInt(tid);
//		String url = "http://" + wz + "/selecttiebyid?tid=" + tid;
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
//				Tie good = JSON.parseObject(string, Tie.class);
//				if (good.getTid() > 0) {
//					// System.out.println("name="+seller.getName()+"password:"+seller.getPassword());
//					msg.what = 1;
//					msg.obj = good;
//					handler.sendMessage(msg);
//				}
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
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}
//}
