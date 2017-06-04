package com.example.bxj.ui.fragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.bxj.R;
import com.example.bxj.R.layout;
import com.example.bxj.R.menu;
import com.example.bxj.model.Good;
import com.example.bxj.model.Tie;
import com.example.bxj.ui.detail.PageFragment1;
import com.example.bxj.ui.detail.PageFragment2;
import com.example.bxj.ui.detail.PageFragment3;
import com.example.jc.adapters.AlltieAdapter;
import com.example.jc.adapters.GoodAdapter;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

//首页
public class SearchFragment extends Fragment {

	private List<String> titleList; // 标题列表数组
	private MyAdapter mAdapter;
	private ViewPager mPager;
	private ArrayList<Fragment> pagerItemList = new ArrayList<Fragment>();
	private PagerTabStrip pagerTitle;// ViewPager的标题
	private String cid;

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.index_search, null);
		mPager = (ViewPager) view.findViewById(R.id.view_pager);
		PageFragment1 page1 = new PageFragment1();
		PageFragment2 page2 = new PageFragment2();
		PageFragment3 page3 = new PageFragment3();

		pagerItemList.add(page1);
		pagerItemList.add(page2);
		pagerItemList.add(page3);
		mAdapter = new MyAdapter(getFragmentManager());
		mPager.setAdapter(mAdapter);
		Bundle bundle = getArguments();
		if (bundle != null) {
			cid = (bundle.getString("cid"));
			Log.i("asda", cid);
		}
		return view;
	}

	public boolean isFirst() {
		if (mPager.getCurrentItem() == 0)
			return true;
		else
			return false;
	}

	public boolean isEnd() {
		if (mPager.getCurrentItem() == pagerItemList.size() - 1)
			return true;
		else
			return false;
	}

	public class MyAdapter extends FragmentPagerAdapter {
		public MyAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public int getCount() {
			return pagerItemList.size();
		}

		@Override
		public Fragment getItem(int position) {

			Fragment fragment = null;
			if (position < pagerItemList.size())
				fragment = pagerItemList.get(position);
			else
				fragment = pagerItemList.get(0);

			return fragment;

		}
	}

	private MyPageChangeListener myPageChangeListener;

	public void setMyPageChangeListener(MyPageChangeListener l) {

		myPageChangeListener = l;

	}

	public interface MyPageChangeListener {
		public void onPageSelected(int position);
	}

	// 为对应的页卡设置标题
	public CharSequence getPageTitle(int position) {
		// TODO Auto-generated method stub
		return titleList.get(position);
	}

	@Override
	public void onDestroy() {

		super.onDestroy();
	}

}
