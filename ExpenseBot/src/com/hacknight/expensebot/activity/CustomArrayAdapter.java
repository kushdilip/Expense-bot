package com.hacknight.expensebot.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hacknight.expensebot.R;
import com.hacknight.expensebot.model.Transaction;

public class CustomArrayAdapter extends ArrayAdapter<Transaction> {

	Activity context;
	private ArrayList<Transaction> allModelItemsArray;
	private ExpenseListFragment expenseListfragment;
	private LayoutInflater inflator;


	public CustomArrayAdapter(Activity context,
			ExpenseListFragment expenseListFragment, ArrayList<Transaction> list) {
		super(context, R.layout.list_item, list);
		this.context = context;
		this.expenseListfragment = expenseListFragment;
		this.allModelItemsArray = new ArrayList<Transaction>();
		allModelItemsArray.addAll(list);
		inflator = context.getLayoutInflater();

	}

	/* private view holder class */
	private class ViewHolder {
		ImageView icon;
		TextView amount;
		TextView date;
		int position;
		Transaction model;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = null;
		if (position > allModelItemsArray.size())
			return null;
		final Transaction m = allModelItemsArray.get(position);
		final ViewHolder viewHolder = new ViewHolder();
		ViewHolder Holder = null;
		if (convertView == null) {
			view = inflator.inflate(R.layout.list_item, null);
			view.setTag(viewHolder);
			viewHolder.amount = (TextView) view.findViewById(R.id.amount);
			viewHolder.date = (TextView) view.findViewById(R.id.date);
			viewHolder.icon = (ImageView) view.findViewById(R.id.icon);
			viewHolder.position = position;
			Holder = viewHolder;

		} else {
			view = convertView;
			Holder = ((ViewHolder) view.getTag());
		}
		Holder.amount.setText("Rs. " + String.valueOf(m.getAmount()));
		Holder.date.setText(m.getDate());
		Holder.model = m;
		Holder.position = position;
		if (Holder.model.isIncome()) {
			Holder.icon.setImageResource(R.drawable.increase);
			Holder.amount.setTextColor(Color.GREEN);
		} else {
			Holder.icon.setImageResource(R.drawable.decrease);
			Holder.amount.setTextColor(Color.RED);
		}
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, EditEntryActivity.class);
				
//				allModelItemsArray.get(position).getAmount()
				
				intent.putExtra("amount", allModelItemsArray.get(position).getAmount());
				intent.putExtra("date", allModelItemsArray.get(position).getDate());
				context.startActivity(intent);
			}
		});
		return view;
	}

}