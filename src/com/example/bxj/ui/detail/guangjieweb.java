package com.example.bxj.ui.detail;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import com.example.bxj.R;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class guangjieweb extends Activity {
	private WebView web;

	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chuzuwuweb);
		web = (WebView) findViewById(R.id.web_view_chuzuwu);
		  //设置WebView属性，能够执行Javascript脚本
		web.getSettings().setJavaScriptEnabled(true);
		web.getSettings().setDefaultTextEncodingName("utf-8");
		    //即asserts文件夹下有一个a.html
		web.loadUrl("http://ikesnowy.com/street/");   
	}
//	@Override  
//    protected void onResume() {  
//        super.onResume(); 
//        Intent intent = new Intent();        
//        intent.setAction("android.intent.action.VIEW");    
//        Uri content_url = Uri.parse("http://720yun.com/t/5c4jO5hfum8?pano_id=2181980");   
//        intent.setData(content_url);  
//        startActivity(intent);
//        Log.e("TAG", "start onResume~~~");  
//    }  

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
		// Handle action br item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
