package com.hacknight.expensebot.model;

public class AccountKind {
	int id;
	String accountKindName;
	
	public AccountKind() {
		
	}
	
	public AccountKind(int id, String accountKindName) {
		super();
		this.id = id;
		this.accountKindName = accountKindName;
	}
	public int getId() {
		return id;
	}
	public String getAccountKindName() {
		return accountKindName;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setAccountKindName(String accountKindName) {
		this.accountKindName = accountKindName;
	}
	
	
}
