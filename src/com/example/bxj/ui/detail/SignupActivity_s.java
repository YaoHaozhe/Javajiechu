package com.example.bxj.ui.detail;

import java.io.IOException;

import com.alibaba.fastjson.JSON;
import com.example.bxj.R;
import com.example.bxj.model.Customer;
import com.example.bxj.model.Seller;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.app.Activity;

import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity_s extends Activity {
	int type = 1;
	// ’À∫≈ ‰»ÎøÚ
	private EditText new_phone;
	// ◊¢≤·√‹¬Î
	private EditText new_password;
	private EditText new_password2;
	private Button ok;
	private String wz = "yaohaozhe.com";
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				Toast.makeText(SignupActivity_s.this, "≥…π¶", Toast.LENGTH_LONG)
						.show();
				break;
			case 2:
				Toast.makeText(SignupActivity_s.this, "’À∫≈“—¥Ê‘⁄£¨¡ÌÕ‚œÎ∏ˆ’À∫≈∞…",
						Toast.LENGTH_LONG).show();
				break;
			}
		}
	};
	private Message msg = new Message();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_signup);
		ok = (Button) findViewById(R.id.ok);
		new_password = (EditText) findViewById(R.id.new_password);
		new_password2 = (EditText) findViewById(R.id.new_password2);
		new_phone = (EditText) findViewById(R.id.new_phone);
		
		ok.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(SignupActivity_s.this, "µ„ª˜", Toast.LENGTH_LONG).show();
				if (type == 1) {
					if (new_password.getText().toString()
							.equals(new_password2.getText().toString())) {
						String url = "http://" + wz
								+ "/insertseller?phone="
								+ new_phone.getText().toString() + "&pass="
								+ new_password.getText().toString();
						OkHttpClient client = new OkHttpClient();
						Request request = new Request.Builder().url(url).get()
								.build();
						client.newCall(request).enqueue(new Callback() {

							public void onResponse(Response arg0)
									throws IOException {
								String string = arg0.body().string();
								System.out.println(string);
								if (string.equals("null")) {
									msg.what = 2;
									msg.obj = 2;
									handler.sendMessage(msg);
								} else {
									Seller seller = (Seller) JSON
											.parseObject(string, Seller.class);
									if (seller.getSphone().length() > 0
											&& seller.getSpass().length() > 0) {
										System.out.println("phone="
												+ seller.getSphone()
												+ "password:"
												+ seller.getSpass());
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
					} else if (new_password.getText() != new_password2
							.getText()) {
						Toast.makeText(SignupActivity_s.this, "√‹¬Î≤ª“ª÷¬£¨«Î÷ÿ ‘",
								Toast.LENGTH_LONG).show();
					}

				}
			}

		});
		/*
		 * Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
		 */

	}
}
