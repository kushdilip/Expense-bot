package com.hacknight.expensebot.service;

public class SMSParser {
	public static final String[] BANKS = {"HDFC", "SBI"};
	public static final String[] TRX_ACCOUNT_TYPE = {"DEBIT/ATM", "CREDIT CARD"};
	public static final String[] TRX_TYPE_TERMS = {"for", "was spent"};
	public static final String[] LOCATION_TERMS = {"at"};
	public static final String[] DATE_TERMS = {"on"};
	public static final String[] AMOUNT_TERMS = {"Rs."};
	
	public static boolean isValidTransaction(String smsString) {
		
		boolean isAccountType, isTrxType, isAmount, isBank;
		isAccountType = isAmount = isBank = isTrxType = false;
		
		for (String bank : BANKS) {
			if (smsString.indexOf(bank) != -1) {
				isBank = true;
				break;
			}
		}
		
		for (String trxAccountType : TRX_ACCOUNT_TYPE) {
			if (smsString.indexOf(trxAccountType) != -1) {
				isAccountType = true;
				break;
			}
		}
		
		for (String trxTypeTerms : TRX_TYPE_TERMS) {
			if (smsString.indexOf(trxTypeTerms) != -1) {
				isTrxType = true;
				break;
			}
		}
		
		for (String amount: AMOUNT_TERMS) {
			if (smsString.indexOf(amount) != -1) {
				isAmount = true;
				break;
			}
		}
		
		return true || (isBank && isAccountType && isTrxType && isAmount);
	}
}
