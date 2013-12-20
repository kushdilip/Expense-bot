package com.dkoder.moneyapp.model;

import java.util.Comparator;

public class Transaction implements Comparable<Transaction> {

	private int id;
	private float amount;
	private String details;
	private String date;
	private int accountKindId;
	private String accountKindName;
	private int categoryId;
	private String categoryName;

	public Transaction() {
		// TODO Auto-generated constructor stub
	}

	public Transaction(float amount, String date, int accountKindId) {
		this.amount = amount;
		this.date = date;
		this.accountKindId = accountKindId;
		this.categoryId = 1;
	}

	public int getId() {
		return id;
	}

	public float getAmount() {
		return amount;
	}

	public String getDetails() {
		return details;
	}

	public String getDate() {
		return date;
	}

	public String getAccountKindName() {
		return accountKindName;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setAccountKindName(String accountKindName) {
		this.accountKindName = accountKindName;
	}

	public int getAccountKindId() {
		return accountKindId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setAccountKindId(int accountKindId) {
		this.accountKindId = accountKindId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public int compareTo(Transaction another) {
		return another.id - this.id;
	}

	class TransactionAmountComparator implements Comparator<Transaction> {
		@Override
		public int compare(Transaction lhs, Transaction rhs) {
			return (int) (lhs.getAmount() - rhs.getAmount());
		}
	}

}
