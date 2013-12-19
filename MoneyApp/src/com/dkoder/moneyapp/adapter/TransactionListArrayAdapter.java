package com.dkoder.moneyapp.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dkoder.moneyapp.R;
import com.dkoder.moneyapp.activity.EditEntryActivity;
import com.dkoder.moneyapp.activity.ExpenseListFragment;
import com.dkoder.moneyapp.model.Transaction;

public class TransactionListArrayAdapter extends ArrayAdapter<Transaction> {

	Activity context;
	private ArrayList<Transaction> allModelItemsArray;
	private LayoutInflater inflator;


	public TransactionListArrayAdapter(Activity context,
			ExpenseListFragment expenseListFragment, ArrayList<Transaction> list) {
		super(context, R.layout.transaction_list_item, list);
		this.context = context;
		this.allModelItemsArray = new ArrayList<Transaction>();
		allModelItemsArray.addAll(list);
		inflator = context.getLayoutInflater();

	}

	/* private view holder class */
	private class ViewHolder {
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
			view = inflator.inflate(R.layout.transaction_list_item, null);
			view.setTag(viewHolder);
			viewHolder.amount = (TextView) view.findViewById(R.id.amount);
			viewHolder.date = (TextView) view.findViewById(R.id.date);
			viewHolder.position = position;
			Holder = viewHolder;

		} else {
			view = convertView;
			Holder = ((ViewHolder) view.getTag());
		}
		Holder.amount.setText("\u20B9" + String.valueOf(m.getAmount()));
		Holder.date.setText(m.getDate());
		Holder.model = m;
		Holder.position = position;
		if (Holder.model.getTransactionKind() == 0) {
			Holder.amount.setTextColor(Color.GREEN);
		} else {
			Holder.amount.setTextColor(Color.RED);
		}
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, EditEntryActivity.class);
				
//				allModelItemsArray.get(position).getAmount()
//				intent.putExtra("amount", allModelItemsArray.get(position).getAmount());
//				intent.putExtra("date", allModelItemsArray.get(position).getDate());
				intent.putExtra("id", allModelItemsArray.get(position).getId());
				context.startActivity(intent);
			}
		});
		return view;
	}

}