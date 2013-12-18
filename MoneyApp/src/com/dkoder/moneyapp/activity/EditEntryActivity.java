package com.dkoder.moneyapp.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.dkoder.moneyapp.R;
import com.dkoder.moneyapp.db.DBHandler;
import com.dkoder.moneyapp.model.AccountKind;
import com.dkoder.moneyapp.model.Category;
import com.dkoder.moneyapp.model.Transaction;

@SuppressLint("SimpleDateFormat")
public class EditEntryActivity extends Activity {

	EditText edit_amount;
	EditText edit_details;
	
	Transaction transaction;
	
	Button ok_button;
	Button cancel_button;
	Button datepicker;
	Spinner categorySpinner;
	Spinner accountSpinner;
	final Calendar c = Calendar.getInstance();
	
	
	float amount;
	String date;
	String details;
	Context context;

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		this.setContentView(R.layout.new_transaction);
		
		context = this;
		final DBHandler handler = new DBHandler(this);
		
		transaction = new Transaction();
		
		edit_amount = (EditText) findViewById(R.id.edit_amount);
		edit_details = (EditText) findViewById(R.id.edit_details);
		ok_button = (Button) findViewById(R.id.ok_button);
		cancel_button = (Button) findViewById(R.id.cancel_button);
		datepicker = (Button) findViewById(R.id.datepicker);
		categorySpinner = (Spinner) findViewById(R.id.categories);
		accountSpinner = (Spinner) findViewById(R.id.accounts);
		
		categorySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
		    public void onItemSelected(AdapterView<?> parent, View view, int position,
		            long id) {
		        // On selecting a spinner item
		        String label = parent.getItemAtPosition(position).toString();
		 
		        // Showing selected spinner item
//		        Toast.makeText(parent.getContext(), "You selected: " + label + ", " + id,
//		                Toast.LENGTH_LONG).show();
		        
		        transaction.setCategoryName(label);
		    }

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}

		});
		
		accountSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
		    public void onItemSelected(AdapterView<?> parent, View view, int position,
		            long id) {
		        // On selecting a spinner item
		        String label = parent.getItemAtPosition(position).toString();
		 
		        // Showing selected spinner item
//		        Toast.makeText(parent.getContext(), "You selected: " + label + ", " + id,
//		                Toast.LENGTH_LONG).show();
		        transaction.setAccountKindName(label);
		    }

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}

		});
		

		loadCategoriesSpinner();
		loadAccountsSpinner();
		
		final DatePickerDialog.OnDateSetListener datePicker = new DatePickerDialog.OnDateSetListener() {

		    @Override
		    public void onDateSet(DatePicker view, int year, int monthOfYear,
		            int dayOfMonth) {
		        // TODO Auto-generated method stub
		        c.set(Calendar.YEAR, year);
		        c.set(Calendar.MONTH, monthOfYear);
		        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		        updateLabel();
		    }

		};

		datepicker.setOnClickListener(new OnClickListener() {

		        @Override
		        public void onClick(View v) {
		            // TODO Auto-generated method stub
		            new DatePickerDialog(EditEntryActivity.this, datePicker, c
		                    .get(Calendar.YEAR), c.get(Calendar.MONTH),
		                    c.get(Calendar.DAY_OF_MONTH)).show();
		        }
		    });

		
		ActionBar actionBar = this.getActionBar();

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			actionBar.setTitle("Edit Your Transaction:");
			
			int id = extras.getInt("id");
			transaction = handler.getTransaction(extras.getInt("id"));
			
//			String amount = "" + extras.getFloat("amount");
			String amount = "" + transaction.getAmount();
			edit_amount.setText(amount);
			
			edit_details.setText(transaction.getDetails());

//			String date_string = extras.getString("date");
			String date_string = transaction.getDate();
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date date;
			try {
				date = format.parse(date_string);
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				
				
				datepicker.setText(c.get(Calendar.DATE) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR)); 

			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			actionBar.setTitle("Add New Transaction");
			String dateString = c.get(Calendar.DATE) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR); 
			datepicker.setText(dateString);
			transaction.setDate(dateString);
		}

		ok_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String str_amount = edit_amount.getText().toString();
				amount = Float.valueOf(str_amount);        
				transaction.setAmount(Float.valueOf(str_amount));
				
				
				details = edit_details.getText().toString();
				transaction.setDetails(edit_details.getText().toString());
				
				// handler.addTransaction(new Transaction(amount, date,
				// " ",type, false));
				handler.addTransaction(transaction);

				int count = handler.getTransactionCount();

//				Toast.makeText(context, " " + count, Toast.LENGTH_LONG).show();

				Intent intent = new Intent(context, MoneyAppActivity.class);
				context.startActivity(intent);

			}

		});

		cancel_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, MoneyAppActivity.class);
				context.startActivity(intent);
			}
		});
	}

	private void updateLabel() {

		String myFormat = "dd/MM/yy"; // In which you need put here
		SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
		date = sdf.format(c.getTime());
		transaction.setDate(date);
		datepicker.setText(date);
	}
	
	private void loadCategoriesSpinner(){
		DBHandler dbHandler = new DBHandler(getApplicationContext());
		List<Category> categories = dbHandler.getAllCategories();
		List<String> categoriesList = new ArrayList<String>();
		for (Category category : categories) {
			categoriesList.add(category.getCategoryName());
		}
		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, categoriesList);
		
		categorySpinner.setAdapter(dataAdapter);
	}
	
	private void loadAccountsSpinner(){
		DBHandler dbHandler = new DBHandler(getApplicationContext());
		List<AccountKind> accountKinds = dbHandler.getAllAccountKind();
		List<String> accountKindList = new ArrayList<String>();
		for (AccountKind accountKind : accountKinds) {
			accountKindList.add(accountKind.getAccountKindName());
		}
		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, accountKindList);
		
		accountSpinner.setAdapter(dataAdapter);	
	}

}