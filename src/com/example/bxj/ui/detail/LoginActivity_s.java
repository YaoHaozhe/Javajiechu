package com.example.bxj.ui.detail;

import java.io.IOException;

import com.alibaba.fastjson.JSON;
import com.example.bxj.R;
import com.example.bxj.model.Customer;
import com.example.bxj.model.Seller;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity_s extends Activity {
	@ViewInject(R.id.login_phone)
	// ÕËºÅÊäÈë¿ò
	EditText et_phone;
	@ViewInject(R.id.login_password)
	// ÕËºÅÊäÈë¿ò
	EditText et_password;
	@ViewInject(R.id.login)
	// µÇÂ¼°´Å¥
	Button login;
	private String wz = "yaohaozhe.com";
	// ×¢²á
	@ViewInject(R.id.zhuce)
	private TextView zhuce;
	int type = 1;
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				Toast.makeText(LoginActivity_s.this, "³É¹¦",
						Toast.LENGTH_LONG).show();
				break;	
			case 2:
				Toast.makeText(LoginActivity_s.this, "ÕËºÅ»òÃÜÂë´íÎó£¬ÇëÖØÊÔ",
						Toast.LENGTH_LONG).show();
				break;
			}
		}
	};
	private Message msg = new Message();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_s);
		ViewUtils.inject(this);
		et_phone.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				if (et_phone.getText().toString().length() >= 1) {
					login.setEnabled(true);
					Log.e("jhd1", "login-set-true");
				} else {
					login.setEnabled(false);
					Log.e("jhd1", "login-set-false");
				}
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	@OnClick(R.id.zhuce)
	public void onClick(View view) {
		Intent intent = new Intent();
		intent.setClass(LoginActivity_s.this, SignupActivity_s.class);
		startActivity(intent);
	}

	@OnClick(R.id.login)
	public void onClick1(View view) {
		if (type == 1) {
			String url = "http://" + wz + "/loginseller?phone="
					+ et_phone.getText().toString() + "&pass="
					+ et_password.getText().toString();
			OkHttpClient client = new OkHttpClient();
			Request request = new Request.Builder().url(url).get().build();
			client.newCall(request).enqueue(new Callback() {

				public void onResponse(Response arg0) throws IOException {
					String string = arg0.body().string();
					System.out.println(string);
					if (string.equals("null")) {
						msg.what = 2;
						msg.obj = 1;
						handler.sendMessage(msg);
					} else {
						Seller seller = (Seller) JSON.parseObject(string,
								Seller.class);
						if (seller.getSphone().length() > 0
								&& seller.getSpass().length() > 0) {
							System.out.println("name=" + seller.getSphone()
									+ "password:" + seller.getSpass());
							msg.what = 1;
							msg.obj = seller.getSid();
							handler.sendMessage(msg);
						}

					}

				}

				public void onFailure(Request arg0, IOException arg1) {
					// TODO Auto-generated method stub

				}
			});

		}

	}
}
