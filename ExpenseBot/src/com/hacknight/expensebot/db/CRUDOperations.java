package com.hacknight.expensebot.db;

import java.util.List;

import com.hacknight.expensebot.model.AccountKind;
import com.hacknight.expensebot.model.Category;
import com.hacknight.expensebot.model.Transaction;

public interface CRUDOperations {

	//Trasaction CRUD
	public void addTransaction(Transaction transaction);
	
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
	
	
}
