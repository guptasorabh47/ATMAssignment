package ie.assignment.service.impl;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ie.assignment.dao.AtmDao;
import ie.assignment.exception.AccountDetailException;
import ie.assignment.exception.AtmException;
import ie.assignment.model.Account;
import ie.assignment.model.Atm;
import ie.assignment.model.AtmBalance;
import ie.assignment.service.AccountService;
import ie.assignment.service.AtmService;

public class IAtmService implements AtmService {
	private static final Logger logger = LoggerFactory.getLogger(IAtmService.class);

	@Autowired
	private AtmDao atmDao;
	@Autowired
	private AccountService accountService;

	@Override
	public String withdraw(int amount, String accountNumber)throws Exception {
		Account account = accountService.getAccount(accountNumber);
		if (account.getAmount().add(account.getOverDraft()).intValue() < amount) {
			// will throw exception
			return "Insufficient funds!";
		}

		logger.debug("Getting cash from ATM for the amount €{}", amount);
		if (amount % 5 != 0) {
			return "Withdrawals allowed in multiples of 5 only!";
		}
		Atm atm = atmDao.getTotalAmount();
		if (amount > atm.getAmount()) {
			throw new AccountDetailException("No cash available!");
		}

		StringBuffer message = new StringBuffer("Please collect your cash ").append("\n");

		int requestedAmount = amount;
		AtmBalance balance = atm.getBalance();
		Map<Integer, Integer> map = new LinkedHashMap<>();
		map.put(50, balance.getFifty());
		map.put(20, balance.getTwenty());
		map.put(10, balance.getTen());
		map.put(5, balance.getFive());

		int numberOfNotes, notes, count;
		int[] denomination = new int[map.size()];
		boolean flag = false;

		int i = 0;
		for (Integer key : map.keySet()) {
			notes = key;
			count = map.get(key);
			numberOfNotes = amount / notes;
			if (count > 0 && count >= numberOfNotes) {
				amount = amount % notes;
			} else if (count > 0) {
				numberOfNotes = numberOfNotes - count;
				amount = (amount - (numberOfNotes * notes));
				numberOfNotes = count;
			} else {
				numberOfNotes = 0;
			}
			if (flag && notes == 5) {
				numberOfNotes = numberOfNotes + 1;
			}

			denomination[i] = numberOfNotes;
			i++;
		}

		if (amount != 0) {
			throw new AtmException("No cash available!");
		} else {
			i = 0;
			for (Integer key : map.keySet()) {
				buildMessage(message, denomination[i], () -> (new StringBuffer("*").append("€" + key).append(" ")));
				map.put(key, map.get(key) - denomination[i]);
				i++;
			}
			accountService.withdrawAmount(accountNumber, BigDecimal.valueOf(requestedAmount));
			AtmBalance updateBalance = new AtmBalance(map.get(50), map.get(20), map.get(10), map.get(5));

			atmDao.updateAmount(atm.getAmount() - requestedAmount, updateBalance);
		}
		return message.toString();
	}

	private StringBuffer buildMessage(StringBuffer message, int value, Supplier supplier) {
		if (value > 0) {
			return message.append(value).append(supplier.get());
		}
		return message;
	}

}
