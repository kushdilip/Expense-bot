package com.dkoder.moneyapp.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;

import com.dkoder.moneyapp.R;
import com.dkoder.moneyapp.adapter.FragmentAdapter;

public class HomeFragment extends Fragment implements ActionBar.TabListener,
		OnPageChangeListener {

	View mView;
	ViewPager mViewPager;
	FragmentAdapter mAdapter;
	ActionBar actionBar;
	String[] tabs = { "Summary", "Transactions" };

	public HomeFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.home, container, false);

		// ViewPager setting
		mAdapter = new FragmentAdapter((FragmentActivity) getActivity());
		mViewPager = (ViewPager) mView.findViewById(R.id.pager);
		mViewPager.setAdapter(mAdapter);
		new setAdapterTask().execute();

		return mView;
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		getActivity().getActionBar().removeAllTabs();
		getActivity().getActionBar().setNavigationMode(
				ActionBar.NAVIGATION_MODE_STANDARD);

	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		// Action bar settings
		actionBar = getActivity().getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.removeAllTabs();
		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this));
		}
		mViewPager.setOnPageChangeListener(this);
	}

	private class setAdapterTask extends AsyncTask<Void, Void, Void> {
		protected Void doInBackground(Void... params) {
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			mViewPager.setAdapter(mAdapter);
		}
	}

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int position) {
		// on changing the page
		// make respected tab selected
		actionBar.setSelectedNavigationItem(position);
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
	}
}
