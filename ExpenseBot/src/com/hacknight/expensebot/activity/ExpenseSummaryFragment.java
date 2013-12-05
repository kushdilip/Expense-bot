package com.hacknight.expensebot.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hacknight.expensebot.R;
import com.hacknight.expensebot.db.DBHandler;

public class ExpenseSummaryFragment extends Fragment {
	private TextView totalTransactions;
		
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.summarylayout, null);		
        
        totalTransactions = (TextView) v.findViewById(R.id.totalTransactions);
		DBHandler handler = new DBHandler(getActivity());

		int count = handler.getAllTransactions().size();
		
		Toast.makeText(getActivity(), " " + count ,Toast.LENGTH_LONG).show();
        
        
        totalTransactions.setText("" + count);

		
        return v;
        
    }
    
	@Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        setUserVisibleHint(true);
    }

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		
//		totalTransactions.setText("" + count);
	}
    

	  public void setText(String item) {
	    TextView view = (TextView) getView().findViewById(R.id.totalTransactions);
	    view.setText(item);
	  }
    
}