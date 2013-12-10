package com.hacknight.expensebot.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.hacknight.expensebot.R;
import com.hacknight.expensebot.db.DBHandler;
import com.hacknight.expensebot.model.Transaction;

public class ExpenseListFragment extends Fragment {	
    private ListView listView;
    private ArrayList<Transaction> items = new ArrayList<Transaction>();
    private TransactionListArrayAdapter mAdapter;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.transaction_list_layout, null);       
        listView = (ListView) v.findViewById(R.id.listview);
        return v;       
    }
    
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        setUserVisibleHint(true);
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // GlobalList is a class that holds global variables, arrays etc
        // getMenuCategories returns global arraylist which is initialized in GlobalList class
        
        DBHandler handler = new DBHandler(getActivity());

        List<Transaction> transactions = handler.getAllTransactions();
        items.addAll(transactions);
        
        //Collections.sort(items);
        
        handler.closeDB(); 
        
        mAdapter = new TransactionListArrayAdapter(getActivity(), this, items);
        listView.setAdapter(mAdapter);
    }
}