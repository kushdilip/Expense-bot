package com.dkoder.moneyapp.db;

import java.util.List;

import com.dkoder.moneyapp.model.AccountKind;
import com.dkoder.moneyapp.model.Category;
import com.dkoder.moneyapp.model.Transaction;

public interface CRUDOperations {

	//Trasaction CRUD
	public long addTransaction(Transaction transaction);
	
	public Transaction getTransaction(int id);
	
	public List<Transaction> getAllTransactions();
	
	public int getTransactionCount();
	
	public int updateTransaction(Transaction transaction);
	
	public void deleteTransaction(Transaction transaction);
	
	public void deleteAllTransaction();
	
	//AccountKind CRUD
	public String getCategoryNameById(int id);
	
	public List<Category> getAllCategories();
	
	
	//Category CRUD
	public String getAccountKindNameById(int id);
	
	public List<AccountKind> getAllAccountKind();
	
	public long addCategory(Category category);
}
