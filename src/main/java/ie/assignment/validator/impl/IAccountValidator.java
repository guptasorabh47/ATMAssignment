package ie.assignment.validator.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ie.assignment.dao.AccountDao;
import ie.assignment.exception.AccountDetailException;
import ie.assignment.validator.AccountValidator;

public class IAccountValidator implements AccountValidator {
	private static final Logger logger = LoggerFactory.getLogger(IAccountValidator.class);
	@Autowired
	private AccountDao accountDao;

	@Override
	public boolean validateAccount(String accountNumber, String password)throws AccountDetailException {
		boolean isValidAccount = false;

		if (accountDao.getValidAccount(accountNumber, password)) {
			logger.debug("Account validated successfully");
			isValidAccount = true;
		}

		return isValidAccount;

	}

}
