package com.dkoder.moneyapp.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dkoder.moneyapp.model.AccountKind;
import com.dkoder.moneyapp.model.Category;
import com.dkoder.moneyapp.model.Transaction;

public class DBHandler extends SQLiteOpenHelper implements CRUDOperations {

	// Logcat tag
	public static final String LOG = "DBHandler";

	// Database version
	public static final int DATABASE_VERSION = 1;

	// Database name
	public static final String DATABASE_NAME = "MoneyApp.db";

	// Table Names
	public static final String TABLE_TRANSACTION = "transaction_data";
	public static final String TABLE_CATEGORY = "category";
	public static final String TABLE_ACCOUNT_KIND = "account_kind";

	// Common column names
	public static final String KEY_ID = "id";
	public static final String KEY_CREATED_AT = "created_at";

	// TRANSACTION Table - column names
	public static final String KEY_AMOUNT = "amount";
	public static final String KEY_DESCRIPTION = "description";
	public static final String KEY_ACCOUNT_KIND_ID = "account_kind_id";
	public static final String KEY_TRANSACTION_KIND = "transaction_kind";
	public static final String KEY_CATEGORY_ID = "category_id";

	// Category Table column name
	public static final String KEY_CATEGORY_NAME = "category_name";
	public static final String KEY_CATEGORY_IS_EXPENSE = "is_expense";

	// AccountKind Table column name
	public static final String KEY_ACCOUNT_NAME = "account_name";

	public static final String COLUMN_ACC_TYPE_CASH = "Cash";
	public static final String COLUMN_ACC_TYPE_ACCOUNT = "Account";

	// Table Create Statements
	// Transaction table create statement
	private static final String CREATE_TABLE_TRANSACTION = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_TRANSACTION
			+ "("
			+ KEY_ID
			+ " INTEGER PRIMARY KEY,"
			+ KEY_AMOUNT
			+ " REAL,"
			+ KEY_DESCRIPTION
			+ " TEXT,"
			+ KEY_CREATED_AT
			+ " TEXT,"
			+ KEY_ACCOUNT_KIND_ID
			+ " INTEGER,"
			+ KEY_CATEGORY_ID + " INTEGER )";

	// Category table create statement
	private static final String CREATE_TABLE_CATEGORY = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_CATEGORY
			+ " ("
			+ KEY_ID
			+ " INTEGER PRIMARY KEY,"
			+ KEY_CATEGORY_NAME
			+ " TEXT,"
			+ KEY_CATEGORY_IS_EXPENSE
			+ " INTEGER)";

	// Account_kind table create statement
	private static final String CREATE_TABLE_ACCOUNT_KIND = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_ACCOUNT_KIND
			+ " ("
			+ KEY_ID
			+ " INTEGER PRIMARY KEY,"
			+ KEY_ACCOUNT_NAME + " TEXT )";

	// Initialize master db
	private static final String INITIALIZE_CATEGORIES = "INSERT INTO "
			+ TABLE_CATEGORY + " (" + KEY_CATEGORY_NAME + ", "
			+ KEY_CATEGORY_IS_EXPENSE + ")" 
			+ " SELECT 'Food', 1 "
			+ " UNION SELECT 'Shopping', 1 " 
			+ " UNION SELECT 'Other', 1;";

	// private static final String INITIALIZE_ACCOUNT_KINDS = "INSERT INTO " +
	// TABLE_ACCOUNT_KIND + " (" + KEY_ACCOUNT_NAME + ") VALUES (" +
	// "'Cash' ), ('Account');";
	private static final String INITIALIZE_ACCOUNT_KINDS = "INSERT INTO " + TABLE_ACCOUNT_KIND
			+ "( " + KEY_ACCOUNT_NAME  + ")"
			+ " SELECT 'Cash'"
			+ " UNION SELECT 'Account';";

	public DBHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public void onCreate(SQLiteDatabase db) {

		// Creating required tables
		db.execSQL(CREATE_TABLE_TRANSACTION);
		db.execSQL(CREATE_TABLE_ACCOUNT_KIND);
		db.execSQL(CREATE_TABLE_CATEGORY);

		// Initialize master db
		db.execSQL(INITIALIZE_ACCOUNT_KINDS);
		db.execSQL(INITIALIZE_CATEGORIES);
		// addCategory(new Category("Food", true));
		// addCategory(new Category("Shopping", true));
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// on upgrade drop older tables
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTION);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNT_KIND);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);

		onCreate(db);
	}

	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		onUpgrade(db, oldVersion, newVersion);
	}

	@Override
	public long addTransaction(Transaction transaction) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_AMOUNT, transaction.getAmount());
		values.put(KEY_DESCRIPTION, transaction.getDetails());
		values.put(KEY_CREATED_AT, transaction.getDate());
		values.put(KEY_ACCOUNT_KIND_ID, transaction.getAccountKindId());
		values.put(KEY_CATEGORY_ID, transaction.getCategoryId());

		long transaction_id = db.insert(TABLE_TRANSACTION, null, values);

		return transaction_id;
	}

	@Override
	public long addCategory(Category category) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_CATEGORY_NAME, category.getCategoryName());
		values.put(KEY_CATEGORY_IS_EXPENSE, category.isExpense() ? 1 : 0);

		long category_id = db.insert(TABLE_CATEGORY, null, values);

		// db.close();

		return category_id;
	}

	@Override
	public Transaction getTransaction(int id) {
		// TODO Auto-generated method stub
		Transaction transaction = new Transaction();
		SQLiteDatabase db = this.getReadableDatabase();

		String selectQuery = "SELECT * FROM " + TABLE_TRANSACTION + " WHERE "
				+ KEY_ID + " = " + id;

		Cursor c = db.rawQuery(selectQuery, null);

		if (c != null)
			c.moveToFirst();

		transaction.setId(c.getInt(c.getColumnIndex(KEY_ID)));
		transaction.setAmount(c.getFloat(c.getColumnIndex(KEY_AMOUNT)));
		transaction.setDate(c.getString(c.getColumnIndex(KEY_CREATED_AT)));
		transaction.setDetails(c.getString(c.getColumnIndex(KEY_DESCRIPTION)));

		c.close();
		return transaction;
	}

	@Override
	public List<Transaction> getAllTransactions() {
		// TODO Auto-generated method stub
		List<Transaction> transactionList = new ArrayList<Transaction>();

		// select all query
		String selectQuery = "SELECT * FROM " + TABLE_TRANSACTION;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				Transaction transaction = new Transaction();

				transaction.setId(cursor.getInt(0));
				transaction.setAmount(cursor.getFloat(1));
				transaction.setDetails(cursor.getString(2));
				transaction.setDate(cursor.getString(3));
				transaction.setAccountKindId(cursor.getInt(4));
				transaction.setCategoryId(cursor.getInt(5));

				transactionList.add(transaction);
			} while (cursor.moveToNext());
		}
		cursor.close();

		for (Transaction transaction : transactionList) {
			transaction.setAccountKindName(getAccountKindNameById(transaction
					.getAccountKindId()));
			transaction.setCategoryName(getCategoryNameById(transaction
					.getCategoryId()));
		}

		return transactionList;
	}

	@Override
	public String getAccountKindNameById(int accountKindId) {
		String accountKindName = "";
		SQLiteDatabase db = this.getReadableDatabase();

		String selectQuery = "SELECT * FROM " + TABLE_ACCOUNT_KIND + " WHERE "
				+ KEY_ID + " = " + accountKindId;

		Cursor c = db.rawQuery(selectQuery, null);

		if (c != null)
			c.moveToFirst();

		accountKindName = c.getString(1);

		c.close();

		return accountKindName;
	}

	@Override
	public String getCategoryNameById(int categoryId) {
		String categoryName = "";
		SQLiteDatabase db = this.getReadableDatabase();

		String selectQuery = "SELECT * FROM " + TABLE_CATEGORY + " WHERE "
				+ KEY_ID + " = " + categoryId;

		Cursor c = db.rawQuery(selectQuery, null);

		if (c != null)
			c.moveToFirst();

		categoryName = c.getString(1);

		c.close();

		return categoryName;
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

	@Override
	public List<Category> getAllCategories() {
		List<Category> categories = new ArrayList<Category>();
		String selectQuery = "SELECT * FROM " + TABLE_CATEGORY;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		if (c.moveToFirst()) {
			do {
				Category cg = new Category();
				cg.setId(c.getInt(0));
				cg.setCategoryName(c.getString(1));

				categories.add(cg);
			} while (c.moveToNext());
		}

		c.close();

		return categories;

	}

	@Override
	public List<AccountKind> getAllAccountKind() {

		List<AccountKind> accountKinds = new ArrayList<AccountKind>();
		String selectQuery = "SELECT * FROM " + TABLE_ACCOUNT_KIND;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		if (c.moveToFirst()) {
			do {
				AccountKind ak = new AccountKind();
				ak.setId(c.getInt(0));
				ak.setAccountKindName(c.getString(1));

				accountKinds.add(ak);
			} while (c.moveToNext());
		}

		c.close();
		return accountKinds;
	}

	// closing database
	public void closeDB() {
		SQLiteDatabase db = this.getReadableDatabase();
		if (db != null && db.isOpen())
			db.close();
	}

}