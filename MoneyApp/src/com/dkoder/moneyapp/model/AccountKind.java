package com.dkoder.moneyapp.model;

public class AccountKind {
	private int id;
	private String accountKindName;
	
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

	@Override
	public String toString() {
		return this.accountKindName;
	}
}
