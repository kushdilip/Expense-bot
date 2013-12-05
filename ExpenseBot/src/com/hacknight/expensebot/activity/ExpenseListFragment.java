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
        View v = inflater.inflate(R.layout.listlayout, null);       
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
//        items.add(new Transaction(150,"12/12/2013","KFC","Card",false));
//        items.add(new Transaction(120,"12/11/2013","KFC","Cash",true));
//        items.add(new Transaction(1590,"12/11/2013","KFC","Cash",false));
//        items.add(new Transaction(1510,"12/1/2013","KFC","Card",false));
//        items.add(new Transaction(12750,"12/11/2013","KFC","Card",true));
//        items.add(new Transaction(150,"12/5/2013","KFC","Cash",false));
//        items.add(new Transaction(120,"12/11/2013","KFC","Cash",false));
//        items.add(new Transaction(1590,"12/3/2013","KFC","Card",false));
//        items.add(new Transaction(152110,"12/11/2013","KFC","Card",true));
//        items.add(new Transaction(1250,"12/6/2013","KFC","Cash",false));
//        items.add(new Transaction(150,"12/5/2013","KFC","Card",false));
//        items.add(new Transaction(12000,"12/11/2013","KFC","Card",true));
//        items.add(new Transaction(1590,"12/11/2013","KFC","Card",true));
//        items.add(new Transaction(1510,"12/11/2013","KFC","Card",true));
//        items.add(new Transaction(1250,"12/11/2013","KFC","Cash",true));
//        items.add(new Transaction(150,"12/2/2013","KFC","Card",false));
//        items.add(new Transaction(120,"12/11/2013","KFC","Card",false));
//        items.add(new Transaction(121590,"12/11/2013","KFC","Card",true));
//        items.add(new Transaction(1510,"12/11/2013","KFC","Card",false));
//        items.add(new Transaction(1250,"12/11/2013","KFC","Card",true));
//        // GlobalList is a class that holds global variables, arrays etc
        // getMenuCategories returns global arraylist which is initialized in GlobalList class
        
        DBHandler handler = new DBHandler(getActivity());

        List<Transaction> transactions = handler.getAllTransactions();
        items.addAll(transactions);
        
        Collections.sort(items);
        
        mAdapter = new TransactionListArrayAdapter(getActivity(), this, items);
        listView.setAdapter(mAdapter);
    }
}