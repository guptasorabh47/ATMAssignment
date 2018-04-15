package ie.assignment.dao.impl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import ie.assignment.dao.AccountDao;
import ie.assignment.exception.AccountDetailException;
import ie.assignment.model.Account;

public class IAccountDao implements AccountDao {

	private static final Logger logger = LoggerFactory.getLogger(IAccountDao.class);
	private static final String accountFile = "Account.json";

	@Override
	public boolean getValidAccount(String accountNumber, String password)throws AccountDetailException {
		boolean isAccountExist = false;
		if ((accountNumber != null && !accountNumber.isEmpty()) && (password != null && !password.isEmpty())) {
			for (Account account : readData()) {
				if (account.getAccountNumber().equals(accountNumber) && account.getPassword().equals(password)) {
					logger.debug("Account found!");
					isAccountExist = true;
				}
			}
		} else {
			throw new AccountDetailException("No such Account found!");
		}
		return isAccountExist;
	}

	@Override
	public Account getBalance(String accountNumber) {

		if (accountNumber != null) {
			for (Account account : readData()) {
				if (account.getAccountNumber().equals(accountNumber)) {
					return account;
				}
			}
		}
		return null;
	}

	@Override
	public BigDecimal withDrawAmount(String accountNumber, BigDecimal requestAmount) {
		BigDecimal balance = BigDecimal.ZERO;
		BigDecimal overdraft = BigDecimal.ZERO;
		if (accountNumber != null && requestAmount != BigDecimal.ZERO) {
			for (Account account : readData()) {
				if (accountNumber.equals(account.getAccountNumber())) {
					if (account.getAmount() == BigDecimal.ZERO || requestAmount.compareTo(account.getAmount()) > 0) {
						requestAmount = requestAmount.subtract(account.getAmount());
						overdraft = account.getOverDraft().subtract(requestAmount);

					} else {
						balance = account.getAmount().subtract(requestAmount);
						overdraft = account.getOverDraft();
					}
					account.setAmount(balance);
					account.setOverDraft(overdraft);
					updateData(account);
				}
			}
		}
		return balance;
	}

	private static Account[] readData() {
		logger.debug("Reading Account details");
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(new File(ClassLoader.getSystemClassLoader().getResource(accountFile).getPath()),
					Account[].class);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}

		return null;
	}

	private static void updateData(Account inputAccount) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Account[] accountList = mapper.readValue(
					new File(ClassLoader.getSystemClassLoader().getResource(accountFile).getPath()), Account[].class);
			for (Account account : accountList) {
				if (inputAccount.getAccountNumber().equals(account.getAccountNumber())) {
					account.setAmount(inputAccount.getAmount());
					account.setOverDraft(inputAccount.getOverDraft());
				}
			}
			mapper.writeValue(new File(ClassLoader.getSystemClassLoader().getResource(accountFile).getPath()),
					accountList);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

}
