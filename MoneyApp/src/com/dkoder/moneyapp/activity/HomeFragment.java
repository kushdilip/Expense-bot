package com.dkoder.moneyapp.activity;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dkoder.moneyapp.R;
import com.dkoder.moneyapp.adapter.FragmentAdapter;

public class HomeFragment extends Fragment {

	View mView;
	ViewPager mViewPager;
	FragmentAdapter mAdapter;
	ActionBar actionBar;
	PagerTabStrip strip;

	public HomeFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.home, container, false);

		// ViewPager setting
		mAdapter = new FragmentAdapter((FragmentActivity) getActivity());
		mViewPager = (ViewPager) mView.findViewById(R.id.pager);
		strip = PagerTabStrip.class.cast(mView.findViewById(R.id.pts_main));

		mViewPager.setAdapter(mAdapter);
		new setAdapterTask().execute();

		// pagertabstrip settings
		strip.setDrawFullUnderline(false);
		strip.setTabIndicatorColorResource(android.R.color.holo_blue_light);
		strip.setBackgroundColor(Color.DKGRAY);
		strip.setNonPrimaryAlpha(0.5f);
		strip.setTextColor(Color.WHITE);
		strip.setTextSpacing(20);
		strip.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);

		return mView;
	}

	private class setAdapterTask extends AsyncTask<Void, Void, Void> {
		protected Void doInBackground(Void... params) {
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (mAdapter != null) {
				mViewPager.setAdapter(mAdapter);
			}
		}
	}
}
