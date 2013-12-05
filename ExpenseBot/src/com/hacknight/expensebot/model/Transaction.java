package com.hacknight.expensebot.model;

import java.util.Comparator;

public class Transaction implements Comparable<Transaction>{

	private int id;
	private float amount;
	private String date;
	private String details;
	private String type;
	private boolean income;
	
	public Transaction(float amount, String date, String details, String type, boolean income) {
		this.amount = amount;
		this.date = date;
		this.details = details;
		this.type = type;
		this.income = income;
	}
	
	public Transaction() {
		// TODO Auto-generated constructor stub
	}

	public float getAmount()
	{
		return this.amount;
	}

	public String getDate()
	{
		return this.date;
	}

	public String getDetails()
	{
		return this.details;
	}
	
	public String getType()
	{
		return this.type;
	}

	public boolean isIncome()
	{
		return this.income;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setIncome(boolean income) {
		this.income = income;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int compareTo(Transaction another) {
		// TODO Auto-generated method stub
		return another.id - this.id;
	}
	
	class TransactionAmountComparator implements Comparator<Transaction> {

		@Override
		public int compare(Transaction lhs, Transaction rhs) {
			// TODO Auto-generated method stub
			return (int)(lhs.getAmount() - rhs.getAmount()) ;
		}
	}
	
	
	
	
}
