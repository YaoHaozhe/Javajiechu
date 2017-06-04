package com.example.bxj;

import com.example.bxj.R;
import com.example.bxj.ui.fragment.HomeFragment;
import com.example.bxj.ui.fragment.TuanFragment;

import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;

public class meijie extends FragmentActivity {
   String cid;
   private FragmentManager manager;  
   private FragmentTransaction transaction;  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.meijie);
		Intent intent=getIntent();
		cid = intent.getStringExtra("cid");
		Log.d("jhd", cid);		
		manager = getSupportFragmentManager();  
       
        TuanFragment TuanFragment = new TuanFragment();  
        Bundle bundle = new Bundle();  
        bundle.putString("cid", cid);  
        TuanFragment.setArguments(bundle);  
        //如果transaction  commit（）过  那么我们要重新得到transaction  
        transaction = manager.beginTransaction();  
        transaction.replace(R.id.frame_layout, TuanFragment); 
        transaction.commit();  
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		
		return true;
	}

}
