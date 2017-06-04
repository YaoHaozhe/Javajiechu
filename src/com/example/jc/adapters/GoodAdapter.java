package com.example.jc.adapters;

import java.util.List;
import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bxj.model.Good;
import com.example.bxj.R;

public class GoodAdapter extends ArrayAdapter<Good> {
	private int Gid;

	public GoodAdapter(Context context, int textViewResourceId,
			List<Good> objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		Gid = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Good good = getItem(position);
		View view;
		ViewHolder viewHolder;
		if (convertView == null) {
			view = LayoutInflater.from(getContext()).inflate(Gid, null);
			viewHolder = new ViewHolder();
			viewHolder.gs_pic = (ImageView) view.findViewById(R.id.gs_pic);
			viewHolder.gs_name = (TextView) view.findViewById(R.id.gs_name);
			viewHolder.gs_sname = (TextView) view.findViewById(R.id.gs_sname);
			viewHolder.juli = (TextView) view.findViewById(R.id.juli);
			//viewHolder.gs_notice = (TextView) view.findViewById(R.id.gs_notice);

			TextView tv2 = (TextView) view.findViewById(R.id.gs_notice);
			viewHolder.gs_notice = Float.valueOf(tv2.getText().toString());
			
			TextView tv = (TextView) view.findViewById(R.id.gs_money);
			viewHolder.gs_money = Float.valueOf(tv.getText().toString());

			TextView tv1 = (TextView) view.findViewById(R.id.gs_yuanjia);
			viewHolder.gs_yuanjia = Float.valueOf(tv1.getText().toString());
			view.setTag(viewHolder);
		} else {
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();
		}

		viewHolder.gs_name.setText(good.getGname());
		viewHolder.gs_pic.setImageResource(good.getGsold());
		viewHolder.gs_sname.setText(good.getSeller().getSname());
		
		viewHolder.gs_notice = good.getGscore();
		viewHolder.gs_money = good.getGtuan();
		viewHolder.juli.setText(good.getSeller().getSadd());
		viewHolder.gs_yuanjia = good.getGmen();

		TextView tv = (TextView) view.findViewById(R.id.gs_money);
		tv.setText(viewHolder.gs_money + "");

		TextView tv1 = (TextView) view.findViewById(R.id.gs_yuanjia);
		tv1.setText(viewHolder.gs_yuanjia + "");
		
		TextView tv2 = (TextView) view.findViewById(R.id.gs_notice);
		tv2.setText(viewHolder.gs_notice + "");
		return view;
	}

	class ViewHolder {
		ImageView gs_pic;
		TextView gs_name;
		TextView gs_sname;
		TextView juli;
		Float gs_notice;
		Float gs_money;
		Float gs_yuanjia;
	}

}
