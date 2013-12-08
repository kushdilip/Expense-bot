package com.hacknight.expensebot.activity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.hacknight.expensebot.R;
import com.hacknight.expensebot.db.DBHandler;
import com.hacknight.expensebot.model.Transaction;

@SuppressLint("SimpleDateFormat")
public class EditEntryActivity extends Activity {

	EditText edit_amount;
	DatePicker edit_date;
	EditText edit_details;
	RadioGroup edit_type;
	Button ok_button;
	Button cancel_button;
	
	float amount;
	String date;
	String details;
	String type;
	Context context;

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		this.setContentView(R.layout.new_transaction);
		
		context = this;
		final DBHandler handler = new DBHandler(this);
		
		edit_amount = (EditText) findViewById(R.id.edit_amount);
		edit_date = (DatePicker) findViewById(R.id.edit_date);
		edit_details = (EditText) findViewById(R.id.edit_details);
		edit_type = (RadioGroup) findViewById(R.id.edit_type);
		ok_button = (Button) findViewById(R.id.ok_button);
		cancel_button = (Button) findViewById(R.id.cancel_button);

		Calendar c = Calendar.getInstance();
		edit_date.setCalendarViewShown(false);
		ActionBar actionBar = this.getActionBar();

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			actionBar.setTitle("Edit Your Transaction:");

			String amount = "" + extras.getFloat("amount");
			edit_amount.setText(amount);

			String date_string = extras.getString("date");
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date date;
			try {
				date = format.parse(date_string);
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				
				edit_date.updateDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
				
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else {
			actionBar.setTitle("Add New Transaction");
		}
		
		

		ok_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String str_amount = edit_amount.getText().toString();
				amount = Float.valueOf(str_amount);
				date = edit_date.getDayOfMonth() + "/" + edit_date.getMonth()
						+ "/" + edit_date.getYear();
				details = edit_details.getText().toString();
				switch (edit_type.getCheckedRadioButtonId()) {
				case R.id.cash:
					type = "cash";
					break;
				case R.id.account:
					type = "account";
					break;
				}
				
				
				handler.addTransaction(new Transaction(amount, date, " ",type, false));
				
				
				int count = handler.getTransactionCount();
				
				Toast.makeText(context, " " + count ,Toast.LENGTH_LONG).show();
				
				Intent intent = new Intent(context, MainActivity.class);
				context.startActivity(intent);
				
			}

		});

		cancel_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, MainActivity.class);
				context.startActivity(intent);
			}
		});
	}
}