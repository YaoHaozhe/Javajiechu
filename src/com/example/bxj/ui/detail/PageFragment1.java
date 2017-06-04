/*
 * Copyright (C) 2012 xiaochengcheng_chong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.bxj.ui.detail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.bxj.R;
import com.example.bxj.model.Tie;
import com.example.jc.adapters.AlltieAdapter;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class PageFragment1 extends Fragment {
	private Handler handler = new Handler(Looper.getMainLooper());
	private List<Tie> tielist = new ArrayList<Tie>();
	private String wz = "yaohaozhe.com";
	AlltieAdapter adapter;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.page1, null);
		initTies();
		adapter = new AlltieAdapter(getActivity(), R.layout.alltie_item,
				tielist);
		ListView listView = (ListView) view.findViewById(R.id.tie_list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Tie tie = tielist.get(position);
				Toast.makeText(getActivity(),"×÷Õß£º"+ tie.getCustomer().getCname(),
						Toast.LENGTH_SHORT).show();
			}
		});
		return view;
	}

	private void initTies() {
		String url = "http://" + wz + "/selecttie";
		OkHttpClient client = new OkHttpClient();
		Log.e("TAG", "OkHttpClient");
		Request request = new Request.Builder().url(url).get().build();
		Log.e("TAG", "Request");
		client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Response arg0) throws IOException {
				Log.e("TAG", "onResponse");
				String string = arg0.body().string();
				System.out.println(string);
				ArrayList<Tie> list = JSON.parseObject(string,
						new TypeReference<ArrayList<Tie>>() {
						});
				tielist.addAll(list);
				System.out.println(tielist.size());
				handler.post(new Runnable() {

					@Override
					public void run() {
						adapter.notifyDataSetChanged();
					}
				});
			}

			@Override
			public void onFailure(Request arg0, IOException arg1) {
				// TODO Auto-generated method stub

			}
		});
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

}
