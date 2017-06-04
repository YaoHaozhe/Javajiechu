package com.example.jc.adapters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bxj.model.Tie;
import com.example.bxj.R;

public class AlltieAdapter extends ArrayAdapter<Tie> {
	private int Tid;

	public AlltieAdapter(Context context, int textViewResourceId,
			List<Tie> objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		Tid = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Tie tie = getItem(position);
		View view;
		ViewHolder viewHolder;
		if (convertView == null) {
			view = LayoutInflater.from(getContext()).inflate(Tid, null);
			viewHolder = new ViewHolder();
			viewHolder.tiename = (TextView) view.findViewById(R.id.tiename);
			viewHolder.tiezuozhe = (TextView) view.findViewById(R.id.tiezuozhe);
			TextView tv = (TextView) view.findViewById(R.id.tiezuozhe);
			SimpleDateFormat sFormat = new SimpleDateFormat(tv.getText().toString());
			try {
				viewHolder.ttime = sFormat.parse("yyyy-MM");
			} catch (ParseException parseException) {
				//
			}
			view.setTag(viewHolder);
		} else {
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();

		}
		
		viewHolder.tiename.setText(tie.getTname());
		viewHolder.tiezuozhe.setText(tie.getCustomer().getCname());
		viewHolder.ttime = tie.getTtime();
		
		TextView tv = (TextView) view.findViewById(R.id.ttime);
		SimpleDateFormat sFormat = new SimpleDateFormat();
		tv.setText(sFormat.format(viewHolder.ttime));
		
		return view;
	}

	class ViewHolder {
		TextView tiename;
		TextView tiezuozhe;
		Date ttime;
	}

}
