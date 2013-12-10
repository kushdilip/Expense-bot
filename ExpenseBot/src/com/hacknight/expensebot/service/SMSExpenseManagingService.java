package com.hacknight.expensebot.service;

import com.hacknight.expensebot.R;
import com.hacknight.expensebot.R.drawable;
import com.hacknight.expensebot.activity.MainActivity;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class SMSExpenseManagingService extends IntentService {
	private final String TAG = "SMSExpenseMangingService";

	public SMSExpenseManagingService() {
		super("SMSExpenseMangingService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.i(TAG, "Hi Dilip, Intent Service is called");
		System.out.println("Hi Dilip, Intent Service is called");

		Bundle bundle = intent.getExtras();
		String smsString = "Empty";
		if (bundle != null) {
			smsString = bundle.getString("sms");
		}

		boolean isValidTransaction = SMSParser.isValidTransaction(smsString);
		if (isValidTransaction) {
			
		

			Intent i = new Intent(this, MainActivity.class);
			PendingIntent pIntent = PendingIntent.getActivity(this, 0, i, 0);

			Notification noti = new Notification.Builder(this)
					.setContentTitle("New Transaction!!!")
					.setContentText("Should I add it to Expense Bot?")
					.addAction(R.drawable.ic_launcher, "Add", pIntent)
					.addAction(R.drawable.ic_launcher, "Cancel", pIntent)
					.setSmallIcon(R.drawable.ic_launcher)
					.build();

			noti.flags |= Notification.FLAG_AUTO_CANCEL;

			NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

			
			notificationManager.notify(1, noti);
		}
	}
}
