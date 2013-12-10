package com.hacknight.expensebot.activity;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.hacknight.expensebot.R;
import com.hacknight.expensebot.db.DBHandler;
import com.hacknight.expensebot.model.AccountKind;
import com.hacknight.expensebot.model.Category;
import com.hacknight.expensebot.model.Transaction;

@SuppressLint("SimpleDateFormat")
public class EditEntryActivity extends Activity implements OnItemSelectedListener{

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
		
		categorySpinner.setOnItemSelectedListener(this);
		accountSpinner.setOnItemSelectedListener(this);
		
		String dateString = c.get(Calendar.YEAR)+"/"+ c.get(Calendar.MONTH)+"/"+c.get(Calendar.DATE); 
		datepicker.setText(dateString);
		transaction.setDate(dateString);

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

			String amount = "" + extras.getFloat("amount");
			edit_amount.setText(amount);

			String date_string = extras.getString("date");
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date date;
			try {
				date = format.parse(date_string);
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				
				
				datepicker.setText(cal.get(Calendar.YEAR)+"/"+
						cal.get(Calendar.MONTH)+"/"+cal.get(Calendar.DATE));

			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			actionBar.setTitle("Add New Transaction");
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
				handler.addTransaction(new Transaction(amount, date,
						0));

				int count = handler.getTransactionCount();

				Toast.makeText(context, " " + count, Toast.LENGTH_LONG).show();

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

	@Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
            long id) {
        // On selecting a spinner item
        String label = parent.getItemAtPosition(position).toString();
 
        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "You selected: " + label + ", " + id,
                Toast.LENGTH_LONG).show();
        
        
        
    }
	
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
}