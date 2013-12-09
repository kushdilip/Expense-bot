package com.hacknight.expensebot.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TableLayout;

import com.hacknight.expensebot.model.Transaction;

public class DBHandler extends SQLiteOpenHelper implements CRUDOperations{

	//Logcat tag
	public static final String LOG = "DBHandler";
	
	//Database version
	public static final int DATABASE_VERSION = 1;

	//Database name
	public static final String DATABASE_NAME = "ExpenseBot.db";

	//Table Names
	public static final String TABLE_TRANSACTION = "transaction";
	public static final String TABLE_CATEGORY = "category";
	public static final String TABLE_ACCOUNT_KIND = "account_kind";
	
	//Common column names
	public static final String KEY_ID = "id";
	public static final String KEY_CREATED_AT = "created_at";	
	
	//TRANSACTION Table - column names
	public static final String KEY_AMOUNT = "amount";
	public static final String KEY_DESCRIPTION = "description";
	public static final String KEY_ACCOUNT_KIND_ID = "account_kind_id";
	public static final String KEY_TRANSACTION_KIND = "transaction_kind";
	public static final String KEY_CATEGORY_ID = "category_id";
	
	//Category Table column name
	public static final String KEY_CATEGORY_NAME = "category_name";
	
	//Category Table column name
	public static final String KEY_ACCOUNT_NAME = "account_name";
		
	
	public static final String COLUMN_ACC_TYPE_CASH = "Cash";
	public static final String COLUMN_ACC_TYPE_ACCOUNT = "Account";

	
	//Table Create Statements
	//Transaction table create statement
	private static final String CREATE_TABLE_TRANSACTION = "CREATE TABLE" + TABLE_TRANSACTION + " ("
			  + KEY_ID + " INTEGER PRIMARY KEY,"
			  + KEY_AMOUNT + "REAL,"
			  + KEY_CREATED_AT + " TEXT,"
			  + KEY_ACCOUNT_KIND_ID +  " INTEGER,"
			  + KEY_TRANSACTION_KIND + " INTEGER(1) );";

	//Category table create statement
	private static final String CREATE_TABLE_CATEGORY = "CREATE TABLE " + TABLE_CATEGORY + " ("
			 + KEY_ID + " INTEGER PRIMARY KEY,"
			 + KEY_CATEGORY_NAME + " TEXT );";
	
	//Account_kind table create statement
		private static final String CREATE_TABLE_ACCOUNT_KIND = "CREATE TABLE " + TABLE_ACCOUNT_KIND + " ("
				 + KEY_ID + " INTEGER PRIMARY KEY,"
				 + KEY_ACCOUNT_NAME + " TEXT );";
		
	
	
	
	
//	private static final String TEXT_TYPE = " TEXT";
//	private static final String NUMBER_TYPE = " NUMBER";
//	private static final String DATE_TYPE = " DATE";
//	private static final String COMMA_SEP = ",";

	private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "
			+ TABLE_TRANSACTION;
	private static final String SQL_DELETE_TYPE = "DROP TABLE IF EXISTS "
			+ TABLE_ACCOUNT_KIND;

	
	public DBHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public void onCreate(SQLiteDatabase db) {

		
		
		// add field for account type
		String CREATE_EXPENSE_ENTRIES = "CREATE TABLE " + TABLE_TRANSACTION + " (" + 
				KEY_ID + " INTEGER PRIMARY KEY,"+ 
				KEY_AMOUNT + " number ,"+ 
				KEY_CREATED_AT + " text, " +
				KEY_TRANSACTION_KIND + " text, "+
				KEY_DESCRIPTION + " text );";

		db.execSQL(CREATE_EXPENSE_ENTRIES);

		String CREATE_ACCOUNTS_ENTRY = "CREATE TABLE " + TABLE_ACCOUNT_KIND + " ("
				+ KEY_TRANSACTION_KIND + " TEXT" + ");";

		db.execSQL(CREATE_ACCOUNTS_ENTRY);

		db.execSQL("INSERT INTO " + TABLE_ACCOUNT_KIND + " VALUES ('"
				+ COLUMN_ACC_TYPE_CASH + "');");
		db.execSQL("INSERT INTO " + TABLE_ACCOUNT_KIND + " VALUES ('"
				+ COLUMN_ACC_TYPE_ACCOUNT + "');");
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		db.execSQL(SQL_DELETE_ENTRIES);
		db.execSQL(SQL_DELETE_TYPE);
		onCreate(db);
	}

	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		onUpgrade(db, oldVersion, newVersion);
	}

	@Override
	public void addTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_AMOUNT, transaction.getAmount());
		values.put(KEY_DESCRIPTION, transaction.getDetails());
		values.put(KEY_CREATED_AT, transaction.getDate());
		values.put(KEY_ACCOUNT_KIND_ID, transaction.getType());
		
		db.insert(TABLE_TRANSACTION, null, values);
		db.close();
	}

	@Override
	public Transaction getTransaction(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> getAllTransactions() {
		// TODO Auto-generated method stub
		List<Transaction> transactionList = new ArrayList<Transaction>();
		
		//select all query
		String selectQuery = "SELECT * FROM " + TABLE_TRANSACTION;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		if (cursor.moveToFirst()) {
			do {
				Transaction transaction = new Transaction();
				transaction.setId(cursor.getInt(0));
				transaction.setAmount(cursor.getInt(1));
				transaction.setDate(cursor.getString(2));
				transaction.setType(cursor.getString(3));
				transaction.setDetails(cursor.getString(4));
				transactionList.add(transaction);
			} while (cursor.moveToNext());
		}
		
		return transactionList;
	}

	@Override
	public int getTransactionCount() {
		// TODO Auto-generated method stub
		String countQuery = "SELECT * FROM " + TABLE_TRANSACTION;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int count = cursor.getCount();
		cursor.close();
		return count;
	}

	@Override
	public int updateTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllTransaction() {
		// TODO Auto-generated method stub
		
	}
	
	
}