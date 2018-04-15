package ie.assignment.model;

import java.math.BigDecimal;

public class Account {
	private String accountNumber;
	private String password;
	private BigDecimal amount;
	private BigDecimal overDraft;

	public Account() {
	}

	public Account(String accountNumber,String password, BigDecimal amount, BigDecimal overDraft) {
		this.accountNumber = accountNumber;
		this.password = password;
		this.amount = amount;
		this.overDraft = overDraft;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public BigDecimal getAmount() {
		return amount;
	}
	

	public String getPassword() {
		return password;
	}

	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getOverDraft() {
		return overDraft;
	}

	public void setOverDraft(BigDecimal overDraft) {
		this.overDraft = overDraft;
	}
}
