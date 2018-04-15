package ie.assignment.service;

import java.math.BigDecimal;

import ie.assignment.model.Account;

public interface AccountService {

	public Account getAccount(String accountNumber)throws Exception;

	public BigDecimal withdrawAmount(String accountNumber, BigDecimal amount) throws Exception;

	
}
