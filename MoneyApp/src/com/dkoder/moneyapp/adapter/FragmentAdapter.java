package com.dkoder.moneyapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import com.dkoder.moneyapp.activity.ExpenseListFragment;
import com.dkoder.moneyapp.activity.ExpenseSummaryFragment;

public class FragmentAdapter extends FragmentPagerAdapter{

	private static final String[] tabs = { "Summary", "Transactions" };
	
	public FragmentAdapter(FragmentActivity activity) {
		super(activity.getSupportFragmentManager());
	}
	
	public FragmentAdapter(FragmentManager fm){
		super(fm);
	}
	
	

//	@Override
//	public int getIconResId(int index) {
//		// TODO Auto-generated method stub
//		return 0;
//	}

	@Override
	public Fragment getItem(int position) {
		// TODO Auto-generated method stub
		Fragment fragment = new ExpenseSummaryFragment();
		switch (position) {
		case 0:
			fragment = new ExpenseSummaryFragment();
			break;
		case 1:
			fragment = new ExpenseListFragment();
			break;
		}
		return fragment;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		String title = "";
		title = tabs[position];
		return title;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		// TODO Auto-generated method stub
		if (object != null) {
			return ((Fragment)object).getView() == view;
		} else {
			return false;
		}
	}
	
	

}