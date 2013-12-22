package com.dkoder.moneyapp.model;

public class Category {

	private int id;
	private String categoryName;
	private boolean isExpense;

	public Category() {
	}
	
	public Category(String categoryName, boolean isExpense) {
		super();
		this.categoryName = categoryName;
		this.isExpense = isExpense;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public boolean isExpense() {
		return isExpense;
	}

	public void setExpense(boolean isExpense) {
		this.isExpense = isExpense;
	}

	@Override
	public String toString() {
		return this.categoryName;
	}

}
