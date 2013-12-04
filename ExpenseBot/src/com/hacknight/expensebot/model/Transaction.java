package com.hacknight.expensebot.model;

public class Transaction {

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
	
	
	
	
	
}
