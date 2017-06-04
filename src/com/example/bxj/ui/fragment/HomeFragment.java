package com.example.bxj.ui.fragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.bxj.AllCategoryActivity;
import com.example.bxj.model.Good;
import com.example.bxj.model.Tie;
import com.example.bxj.myview.WrapContentHeightViewPager;
import com.example.bxj.ui.detail.ActivityHomeList1;
import com.example.bxj.ui.detail.Chuzuwu;
import com.example.bxj.ui.detail.Market;
import com.example.bxj.ui.detail.Tianping;
import com.example.bxj.ui.detail.buy;
import com.example.bxj.ui.detail.guangjieweb;
import com.example.bxj.utils.MyConstant;
import com.example.bxj.R;
import com.example.guojia.activity.ChooseAreaActivity;
import com.example.jc.adapters.GoodAdapter;
import com.example.lunbotu.ADBean;
import com.example.lunbotu.TuTu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import com.example.bxj.ui.detail.*;

//首页
public class HomeFragment extends Fragment implements LocationListener {
	private String wz = "yaohaozhe.com";
	private List<Good> goodlist = new ArrayList<Good>();
	GoodAdapter adapter;

	@ViewInject(R.id.home_top_city)
	private TextView topCity;

//	@ViewInject(R.id.index_home_viewpager)
//	private WrapContentHeightViewPager viewPager;

	private GridView gridView1;
	private GridView gridView2;
	private GridView gridView3;
	Intent intent1;
	// tips
	private boolean displayTips = false;
	//@ViewInject(R.id.index_home_tip)
	//private ImageView tips;
	@ViewInject(R.id.index_home_tips_arrow)
	private ImageView tips_arrow;
	@ViewInject(R.id.index_home_tips_content)
	private LinearLayout tips_content;
	String position;

	private ViewPager ad_viewPage;
	/**
	 * 显示的文字Textview
	 */
	private TextView tv_msg;
	/**
	 * 添加小圆点的线性布局
	 */
	private LinearLayout ll_dian;
	/**
	 * 轮播图对象列表
	 */
	List<ADBean> listADbeans;
	
	/**
	 * 本地图片资源
	 */
	private int[] ids = { R.drawable.one, R.drawable.two, R.drawable.three,
			R.drawable.fore, R.drawable.five };
	/**
	 * 网络资源
	 */

	private TuTu tu;
	private Context mContext;
	private String cid;
	private TextView sousuo;

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		switch (requestCode) {
		case 1:
			if (resultCode == Activity.RESULT_OK) {
				position = data.getStringExtra("county_code");
				topCity.setText(position);
			}
			break;
		default:
		}
	}

	// 接受处理消息
	private Handler handler = new Handler(new Handler.Callback() {

		@Override
		public boolean handleMessage(Message arg0) {

			if (arg0.what == 1) {
				
			}
			return false;
		}
	});

	// 初始化 控件，加载布局
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.index_home, null);
		ViewUtils.inject(this, view); // 注入控件
		// 获取数据并显示

		initGridView();
		// 添加数据
		initGoods();

		adapter = new GoodAdapter(getActivity(), R.layout.good_show_two,
				goodlist);
		ListView listView = (ListView) view.findViewById(R.id.good_list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Good good = goodlist.get(position);
				System.out.println(String.valueOf(goodlist.get(position)
						.getGid()));
				Intent intent = new Intent(getActivity(), buy.class);
				intent.putExtra("gid",
						String.valueOf(goodlist.get(position).getGid()));
				if (cid != null) {
					intent.putExtra("cid", cid);
				}
				startActivity(intent);
				Toast.makeText(getActivity(), good.getGname(),
						Toast.LENGTH_SHORT).show();
			}
		});
		
		Log.e("jhd", "onCreateView");
		// 从LoginActivity传过来的Bundle
		Bundle bundle = getArguments();
		if (bundle != null) {
			cid = (bundle.getString("cid"));
			Log.i("asda", cid);
		}
		Log.i("asda", "test");
		handler.sendEmptyMessage(1);// 发线程 初始化viewpager 解决切换页面导致viewpager中的内容为空
		mContext = getActivity();
		initView(view);
		initAD();

		return view;

	}
	/**
	 * 初始化轮播图
	 */
	private void initAD() {
		listADbeans = new ArrayList<ADBean>();
		for(int i =0;i<5;i++){
			ADBean bean = new ADBean();
			bean.setId(i+"");
			bean.setImgPath(ids[i]);
			//bean.setImgPath(ids[i]);
			listADbeans.add(bean);
		}
		tu = new TuTu(ad_viewPage, ll_dian, mContext, listADbeans);
		tu.startViewPager(4000);//动态设置滑动间隔，并且开启轮播图
	}
	
	/**
	 * 初始化布局
	 */
	private void initView(View view) {
		ad_viewPage = (ViewPager)view.findViewById(R.id.ad_viewPage);
		ll_dian = (LinearLayout)view.findViewById(R.id.ll_dian);	
	}

	@OnClick({ R.id.home_top_city, R.id.index_home_tip ,R.id.sousuo})
	public void onClick(View view) {
		if (view.getId() == R.id.home_top_city) {
			Intent intent = new Intent();
			Context cxt = getActivity();
			intent.setClass(cxt, ChooseAreaActivity.class);
			startActivityForResult(intent, 1);

		} else if (view.getId() == R.id.index_home_tip) {
			Toast.makeText(getActivity(), "+", Toast.LENGTH_SHORT).show();
			if (displayTips) {
				tips_arrow.setVisibility(View.GONE);
				tips_content.setVisibility(View.GONE);
			}
			else {
				tips_arrow.setVisibility(View.VISIBLE);
				tips_content.setVisibility(View.VISIBLE);
			}
			displayTips = !displayTips;

		}
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}

	@Override
	public void onDestroy() {

		super.onDestroy();

		// TODO Auto-generated method stub 暂时不可用
		// stopLocation();
	}

	// gridview 的适配器
	public class GridViewAdapter extends BaseAdapter {

		// 我的数据在utils包下的MyConstant中定义好了
		private LayoutInflater inflater;
		private int page;

		public GridViewAdapter(Context context, int page) {
			super();
			this.inflater = LayoutInflater.from(context);
			this.page = page;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			if (page != -1) {
				return 8;
			} else {
				return MyConstant.navSort.length;
			}
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup arg2) {

			ViewHolder vh = null;
			if (convertView == null) {
				vh = new ViewHolder();
				convertView = inflater.inflate(R.layout.index_home_grid_item,
						null);
				ViewUtils.inject(vh, convertView);
				convertView.setTag(vh);
			} else {
				vh = (ViewHolder) convertView.getTag();
			}

			vh.iv_navsort.setImageResource(MyConstant.navSortImages[position
					+ 8 * page]);
			vh.tv_navsort.setText(MyConstant.navSort[position + 8 * page]);
			if (position == 8 - 1 && page == 2) {
				vh.iv_navsort.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						startActivity(new Intent(getActivity(),
								AllCategoryActivity.class));
					}
				});
			} else {
				vh.iv_navsort.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						startActivity(new Intent(getActivity(),
								ActivityHomeList1.class));
					}
				});
			}

			return convertView;
		}
	}

	// gridview 适配器的holder类
	private class ViewHolder {
		@ViewInject(R.id.index_home_iv_navsort)
		ImageView iv_navsort;
		@ViewInject(R.id.index_home_tv_navsort)
		TextView tv_navsort;
	}

	private void initGridView() {
		gridView1 = (GridView) LayoutInflater.from(getActivity()).inflate(
				R.layout.index_home_gridview, null);
		gridView1.setAdapter(new GridViewAdapter(getActivity(), 0));
		gridView2 = (GridView) LayoutInflater.from(getActivity()).inflate(
				R.layout.index_home_gridview, null);
		gridView2.setAdapter(new GridViewAdapter(getActivity(), 1));
		gridView3 = (GridView) LayoutInflater.from(getActivity()).inflate(
				R.layout.index_home_gridview, null);
		gridView3.setAdapter(new GridViewAdapter(getActivity(), 2));
	}

	private void initGoods() {
		String url = "http://" + wz + "/getgood";
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
				ArrayList<Good> list = JSON.parseObject(string,
						new TypeReference<ArrayList<Good>>() {
						});
				goodlist.addAll(list);
				System.out.println(goodlist.size());
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

	// 自定义viewpager的适配器
	private class MyViewPagerAdapter extends PagerAdapter {

		List<View> list;

		// List<String> titles;
		public MyViewPagerAdapter(List<View> list) {
			// TODO Auto-generated constructor stub

			this.list = list;
			// this.titles=titles;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		// 判断 当前的view 是否是 Object 对象
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			container.addView(list.get(position));
			Log.e("jhd", "添加--" + position);

			return list.get(position);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub

			container.removeView(list.get(position));
		}

		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			// return titles.get(position);
			return "1"; // 暂时没用的
		}
	}

	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.e("jhd", "onStop");
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		sousuo = (TextView)getActivity().findViewById(R.id.sousuo);
		sousuo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "ok",
						Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(getActivity(), sousuo.class);				
				startActivity(intent);
			}
		});
		
		ImageView he = (ImageView)getActivity().findViewById(R.id.he);
		he.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "敬请期待",Toast.LENGTH_SHORT).show();
			}
		});
		
		ImageView chi = (ImageView)getActivity().findViewById(R.id.chi);
		chi.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(), chi.class);				
				startActivity(intent);
			}
		});
		ImageView le = (ImageView)getActivity().findViewById(R.id.le);
		le.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "敬请期待",Toast.LENGTH_SHORT).show();
			}
		});
		ImageView wan = (ImageView)getActivity().findViewById(R.id.wan);
		wan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(), wan.class);				
				startActivity(intent);
			}
		});
	}

}
