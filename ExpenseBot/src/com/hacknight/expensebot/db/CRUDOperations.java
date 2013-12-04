package com.hacknight.expensebot.db;

import java.util.List;

import com.hacknight.expensebot.model.Transaction;

public interface CRUDOperations {

	public void addTransaction(Transaction transaction);
	
	public Transaction getTransaction(int id);
	
	public List<Transaction> getAllTransactions();
	
	public int getTransactionCount();
	
	public int updateTransaction(Transaction transaction);
	
	public void deleteTransaction(Transaction transaction);
	
	public void deleteAllTransaction();
}
