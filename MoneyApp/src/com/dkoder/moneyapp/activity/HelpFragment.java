package com.dkoder.moneyapp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dkoder.moneyapp.R;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

public class HelpFragment extends Fragment {
	// The view to show the ad.
//	private AdView adView;

	public HelpFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.help, container, false);

//		// Create an ad.
//		adView = new AdView(getActivity(), AdSize.BANNER, "a152b5d87dd91d8");
//
//		// Add the AdView to the view hierarchy. The view will have no size
//		// until the ad is loaded.
//		LinearLayout layout = (LinearLayout) v.findViewById(R.id.linearLayout);
//		layout.addView(adView);
//
//		// Create an ad request. Check logcat output for the hashed device ID to
//		// get test ads on a physical device.
//		AdRequest adRequest = new AdRequest();
////		adRequest.addTestDevice(AdRequest.TEST_EMULATOR);
//
//		// Start loading the ad in the background.
//		adView.loadAd(adRequest);

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
	}

	@Override
	public void onDestroy() {
		// Destroy the AdView.
//		if (adView != null) {
//			adView.destroy();
//		}

		super.onDestroy();
	}

}
