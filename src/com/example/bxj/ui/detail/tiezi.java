package com.example.bxj.ui.detail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.bxj.R;
import com.example.bxj.R.string;
import com.example.jc.adapters.GoodAdapter;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.example.bxj.model.Good;

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

public class tiezi extends Activity {
	private String cid;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alltie_item);
		
		Intent intent = getIntent();
		cid = intent.getStringExtra("cid");
		if (cid != null) {
			Log.d("tesss", cid+"");
		}
		
	}


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
