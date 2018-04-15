package ie.assignment.dao;

import java.math.BigDecimal;

import ie.assignment.exception.AccountDetailException;
import ie.assignment.model.Account;

public interface AccountDao {

	public boolean getValidAccount(String accountNumber, String password) throws AccountDetailException;

	public Account getBalance(String accountNumber) throws Exception;

	public BigDecimal withDrawAmount(String accountNumber, BigDecimal amount) throws Exception;

}
