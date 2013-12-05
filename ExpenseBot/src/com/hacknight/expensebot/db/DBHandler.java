package com.hacknight.expensebot.db;

import java.util.ArrayList;
import java.util.List;

import com.hacknight.expensebot.model.Transaction;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper implements CRUDOperations{

	public static final String TABLE_TRANSACTION = "transactions";
	public static final String COLUMN_EXPENSE_ID = "id";
	public static final String COLUMN_EXPENSE_AMOUNT = "amount";
	public static final String COLUMN_EXPENSE_DESC = "description";
	public static final String COLUMN_EXPENSE_DATE = "expDate";
	public static final String COLUMN_EXPENSE_ACCTYPE = "accType";

	public static final String TABLE_TYPE_NAME = "accountType";
	public static final String COLUMN_ACC_TYPE = "accType";
	public static final String COLUMN_ACC_TYPE_CASH = "Cash";
	public static final String COLUMN_ACC_TYPE_ACCOUNT = "Account";

	private static final String TEXT_TYPE = " TEXT";
	private static final String NUMBER_TYPE = " NUMBER";
	private static final String DATE_TYPE = " DATE";
	private static final String COMMA_SEP = ",";

	private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "
			+ TABLE_TRANSACTION;
	private static final String SQL_DELETE_TYPE = "DROP TABLE IF EXISTS "
			+ TABLE_TYPE_NAME;

	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "ExpenseBot.db";

	public DBHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public void onCreate(SQLiteDatabase db) {

		// add field for account type
		String CREATE_EXPENSE_ENTRIES = "CREATE TABLE " + TABLE_TRANSACTION + " (" + 
				COLUMN_EXPENSE_ID + " INTEGER PRIMARY KEY,"+ 
				COLUMN_EXPENSE_AMOUNT + " number ,"+ 
				COLUMN_EXPENSE_DATE + " text, " +
				COLUMN_ACC_TYPE + " text, "+
				COLUMN_EXPENSE_DESC + " text );";

		db.execSQL(CREATE_EXPENSE_ENTRIES);

		String CREATE_ACCOUNTS_ENTRY = "CREATE TABLE " + TABLE_TYPE_NAME + " ("
				+ COLUMN_ACC_TYPE + TEXT_TYPE + ");";

		db.execSQL(CREATE_ACCOUNTS_ENTRY);

		db.execSQL("INSERT INTO " + TABLE_TYPE_NAME + " VALUES ('"
				+ COLUMN_ACC_TYPE_CASH + "');");
		db.execSQL("INSERT INTO " + TABLE_TYPE_NAME + " VALUES ('"
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
		values.put(COLUMN_EXPENSE_AMOUNT, transaction.getAmount());
		values.put(COLUMN_EXPENSE_DESC, transaction.getDetails());
		values.put(COLUMN_EXPENSE_DATE, transaction.getDate());
		values.put(COLUMN_EXPENSE_ACCTYPE, transaction.getType());
		
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