package com.dkoder.moneyapp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dkoder.moneyapp.R;
import com.dkoder.moneyapp.db.DBHandler;

public class ExpenseSummaryFragment extends Fragment {
	private TextView totalTransactions;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.transaction_summary, null);
		totalTransactions = (TextView) v.findViewById(R.id.totalTransactions);

		return v;

	}

	
	@Override
	public void onResume() {
		super.onResume();
		DBHandler handler = new DBHandler(getActivity());

		int count = handler.getAllTransactions().size();

		Toast.makeText(getActivity(), " " + count, Toast.LENGTH_LONG).show();

		totalTransactions.setText("Total number of Expenses: " + count);

	}


	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		setUserVisibleHint(true);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		// totalTransactions.setText("" + count);
	}

	public void setText(String item) {
		TextView view = (TextView) getView().findViewById(
				R.id.totalTransactions);
		view.setText(item);
	}

}