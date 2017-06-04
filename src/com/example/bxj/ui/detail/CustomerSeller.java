package com.example.bxj.ui.detail;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.example.bxj.R;

public class CustomerSeller extends Activity {

	private Button Customer;
	private Button Seller;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.customerseller);
		Customer =(Button) findViewById(R.id.customerlogin);
		Customer.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			Intent intent=new Intent();
				intent.setClass(CustomerSeller.this, LoginActivity_c.class);
				startActivity(intent);
						
			}
		});
		Seller =(Button) findViewById(R.id.sellerlogin);
		Seller.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			Intent intent=new Intent();
				intent.setClass(CustomerSeller.this, LoginActivity_s.class);
				startActivity(intent);
						
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
