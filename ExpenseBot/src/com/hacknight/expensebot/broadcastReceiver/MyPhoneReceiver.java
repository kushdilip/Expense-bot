package com.hacknight.expensebot.broadcastReceiver;

import com.hacknight.expensebot.service.SMSExpenseManagingService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

public class MyPhoneReceiver extends BroadcastReceiver {
	private static final String TAG = "MyPhoneReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Bundle extras = intent.getExtras();
		if (extras != null) {
			String state = extras.getString(TelephonyManager.EXTRA_STATE);
			Log.w(TAG, state);
			if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
				String phoneNumber = extras
						.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
				Log.w(TAG, phoneNumber);
				System.out.println(phoneNumber);
				// #Todo Code to put in SMSService
				
				
				String smsString = "Thank you for using your HDFC bank DEBIT/ATM"
						+ " card ending 6783 for Rs.299.00 in BANGALORE at JADE CHIENESE "
						+ "on 2013-11-27:22:14:33.";
				
				
				Intent i = new Intent(context, SMSExpenseManagingService.class);
				i.putExtra("sms", smsString);
				context.startService(i);

				

//				Intent service = new Intent(context, SMStickyService.class);
//				service.putExtra("sms", smsString);
//				context.startService(service);

			}
		}
	}
}
