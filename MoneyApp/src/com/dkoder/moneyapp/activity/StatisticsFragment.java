package com.dkoder.moneyapp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dkoder.moneyapp.R;
import com.inmobi.monetization.IMBanner;

public class StatisticsFragment extends Fragment {
	public StatisticsFragment() {	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.statistics, container, false);
		
		IMBanner banner = (IMBanner) v.findViewById(R.id.banner);
		banner.loadBanner();
		return v;
		
	}

	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		setUserVisibleHint(true);
	}

}
