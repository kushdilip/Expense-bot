package com.dkoder.moneyapp.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dkoder.moneyapp.R;
import com.dkoder.moneyapp.adapter.FragmentAdapter;

public class HomeFragment extends Fragment {

	View mView;
	ViewPager mViewPager;
	FragmentAdapter mAdapter;

	public HomeFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// getActivity().getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		mView = inflater.inflate(R.layout.home, container, false);

		mAdapter = new FragmentAdapter((FragmentActivity)getActivity());
		
		mViewPager = (ViewPager) mView.findViewById(R.id.pager);
		
		mViewPager.setAdapter(mAdapter);

		new setAdapterTask().execute();
		
		return mView;
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
}
