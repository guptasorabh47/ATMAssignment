package ie.assignment.service.impl;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ie.assignment.dao.AccountDao;
import ie.assignment.model.Account;
import ie.assignment.service.AccountService;

public class IAccountService implements AccountService {
	private static final Logger logger = LoggerFactory.getLogger(IAccountService.class);

	@Autowired
	private AccountDao accountDao;

	@Override
	public Account getAccount(String accountNumber)throws Exception {
		logger.debug("Retrieve the balance from an account with account number {}", accountNumber);
		return accountDao.getBalance(accountNumber);
	}

	@Override
	public BigDecimal withdrawAmount(String accountNumber, BigDecimal amount)throws Exception {
		 logger.debug("Withdraw amount of €{} from account with account number {}", amount, accountNumber);
	        return accountDao.withDrawAmount(accountNumber, amount);
	}

	
}
