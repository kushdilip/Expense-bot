package com.dkoder.moneyapp.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dkoder.moneyapp.R;
import com.dkoder.moneyapp.adapter.TransactionListArrayAdapter;
import com.dkoder.moneyapp.db.DBHandler;
import com.dkoder.moneyapp.model.Transaction;

public class ExpenseListFragment extends Fragment {
	private ListView listView;
	private ArrayList<Transaction> items = new ArrayList<Transaction>();
	private TransactionListArrayAdapter mAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.transaction_list, null);
		listView = (ListView) v.findViewById(R.id.transaction_listview);
		return v;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		setUserVisibleHint(true);
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		DBHandler handler = new DBHandler(getActivity());

		List<Transaction> transactions = handler.getAllTransactions();
		items.clear();
		items.addAll(transactions);

		Collections.sort(items);

		handler.closeDB();

		mAdapter = new TransactionListArrayAdapter(getActivity(), this, items);
		listView.setAdapter(mAdapter);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
}