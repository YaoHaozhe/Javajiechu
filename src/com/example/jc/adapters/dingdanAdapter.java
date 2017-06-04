package com.example.jc.adapters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import android.content.Context;
import android.provider.ContactsContract.Data;
import android.renderscript.Int4;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bxj.model.Good;
import com.example.bxj.model.Orders;
import com.example.bxj.R;

public class dingdanAdapter extends ArrayAdapter<Orders> {
	private int Gid;

	public dingdanAdapter(Context context, int textViewResourceId,
			List<Orders> objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		Gid = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Orders good = getItem(position);
		View view;
		ViewHolder viewHolder;
		if (convertView == null) {
			view = LayoutInflater.from(getContext()).inflate(Gid, null);
			viewHolder = new ViewHolder();
			viewHolder.g_name = (TextView) view.findViewById(R.id.g_name);
			viewHolder.ophone = (TextView) view.findViewById(R.id.ophone);			
			//viewHolder.gs_notice = (TextView) view.findViewById(R.id.gs_notice);

			TextView tv2 = (TextView) view.findViewById(R.id.g_shuliang);
			viewHolder.g_shuliang = Integer.valueOf(tv2.getText().toString());
			
			TextView tv = (TextView) view.findViewById(R.id.g_money);
			viewHolder.g_money = Float.valueOf(tv.getText().toString());
		
			view.setTag(viewHolder);
		} else {
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();
		}

		viewHolder.g_name.setText(good.getGood().getGname());
		viewHolder.ophone.setText(good.getOphone());
		
		viewHolder.g_shuliang = good.getOamount();
		viewHolder.g_money = good.getOprice();
		
		TextView tv = (TextView) view.findViewById(R.id.gs_money);
		tv.setText(viewHolder.g_money + "");

		
		TextView tv2 = (TextView) view.findViewById(R.id.gs_notice);
		tv2.setText(viewHolder.g_shuliang + "");
		
		return view;
	}

	class ViewHolder {
		TextView g_name;
		TextView ophone;
		int g_shuliang;
		Float g_money;
	}

}
