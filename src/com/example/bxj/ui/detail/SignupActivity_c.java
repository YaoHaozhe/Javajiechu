package com.example.bxj.ui.detail;

import java.io.IOException;

import com.alibaba.fastjson.JSON;
import com.example.bxj.R;
import com.example.bxj.model.Customer;
import com.example.bxj.ui.MainActivity;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.app.Activity;
import android.content.Intent;

import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity_c extends Activity {
	int type = 1;
	// ’À∫≈ ‰»ÎøÚ
	private EditText new_phone;
	// ◊¢≤·√‹¬Î
	private EditText new_password;
	private EditText new_password2;
	private EditText et_cname;
	private Button ok;
	private String wz = "yaohaozhe.com";
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				Toast.makeText(SignupActivity_c.this, "≥…π¶", Toast.LENGTH_LONG)
						.show();
				int result = (Integer) msg.obj;
				Intent intent = new Intent(SignupActivity_c.this,
						MainActivity.class);

				intent.putExtra("cid", String.valueOf(result));

				startActivity(intent);
				break;
			case 2:
				Toast.makeText(SignupActivity_c.this, "’À∫≈“—¥Ê‘⁄£¨¡ÌÕ‚œÎ∏ˆ’À∫≈∞…",
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
		et_cname = (EditText) findViewById(R.id.new_name);

		ok.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {			
				if (type == 1) {
					if (new_password.getText().toString()
							.equals(new_password2.getText().toString())) {
						String url = "http://" + wz + "/insertcustomer?phone="
								+ new_phone.getText().toString() + "&pass="
								+ new_password.getText().toString() + "&name="
								+ et_cname.getText().toString();
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
									Customer customer = (Customer) JSON
											.parseObject(string, Customer.class);
									if (customer.getCphone().length() > 0
											&& customer.getCpass().length() > 0) {
										System.out.println("phone="
												+ customer.getCphone()
												+ "password:"
												+ customer.getCpass());
										msg.what = 1;
										msg.obj = customer.getCid();
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
						Toast.makeText(SignupActivity_c.this, "√‹¬Î≤ª“ª÷¬£¨«Î÷ÿ ‘",
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
