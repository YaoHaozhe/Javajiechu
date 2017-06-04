package com.example.bxj.ui.fragment;

import com.example.bxj.R;
import com.example.bxj.ui.detail.Chuzuwu;
import com.example.bxj.ui.detail.Market;
import com.example.bxj.ui.detail.Tianping;
import com.example.bxj.ui.detail.guangjieweb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

//Ê×Ò³
public class TuanFragment extends Fragment {
	private Button market;
	private Button tianping;
	private Button chuzuwu;
	private String cid;
	private TextView guangjie;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.index_tuan, null);
		Bundle bundle = getArguments();
		if (bundle != null) {
			cid = (bundle.getString("cid"));
			Log.i("asda", cid);
		}
		return view;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Button market = (Button) getActivity().findViewById(R.id.market);
		Button tianping = (Button) getActivity().findViewById(R.id.tianping);
		Button chuzuwu = (Button) getActivity().findViewById(R.id.chuzuwu);
		guangjie = (TextView)getActivity().findViewById(R.id.guangjie);
		guangjie.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(), guangjieweb.class);
				intent.putExtra("cid", cid);
				startActivity(intent);
			}
		});
		market.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(), Market.class);
				intent.putExtra("cid", cid);
				startActivity(intent);
			}
		});
		tianping.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(), Tianping.class);
				intent.putExtra("cid", cid);
				startActivity(intent);
			}
		});
		chuzuwu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(), Chuzuwu.class);
				intent.putExtra("cid", cid);
				startActivity(intent);
			}
		});
	}
}
