package com.example.bxj.ui.fragment;

import com.example.bxj.ui.detail.CustomerSeller;
import com.example.bxj.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bxj.ui.detail.*;
//首页
public class MyFragment extends Fragment {
	private String cid;
	@ViewInject(R.id.index_my_list1_touxiang)
	ImageView touxiang;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.index_my, null);
		ViewUtils.inject(this, view); // 注入控件
		Bundle bundle = getArguments();
		if (bundle != null) {
			cid = (bundle.getString("cid"));
			Log.i("asda", cid);
		}
		return view;

	}

	@OnClick({ R.id.index_my_list1_touxiang })
	public void onClick(View v) {
		if (v.getId() == R.id.index_my_list1_touxiang) {
			startActivity(new Intent(getActivity(), CustomerSeller.class));
		}
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
				
		ImageView youhui = (ImageView)getActivity().findViewById(R.id.youhui);
		youhui.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(), youhui.class);				
				startActivity(intent);
			}
		});
		
		TextView tiezi = (TextView)getActivity().findViewById(R.id.tiezi0);
		tiezi.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(), tiezi.class);				
				startActivity(intent);
			}
		});
		ImageView ping = (ImageView)getActivity().findViewById(R.id.ping);
		ping.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "暂无点评",Toast.LENGTH_SHORT).show();			
			}
		});
		ImageView guanzhu = (ImageView)getActivity().findViewById(R.id.guanzhu);
		guanzhu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(), wang.class);				
				startActivity(intent);		
			}
		});
		TextView dingdan = (TextView)getActivity().findViewById(R.id.dingdan);
		dingdan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(), dingdan.class);				
				startActivity(intent);
			}
		});
	}

}
