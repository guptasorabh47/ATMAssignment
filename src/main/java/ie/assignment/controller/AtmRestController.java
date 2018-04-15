package ie.assignment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ie.assignment.exception.AccountDetailException;
import ie.assignment.model.Account;
import ie.assignment.service.AccountService;
import ie.assignment.service.AtmService;
import ie.assignment.validator.AccountValidator;

@RestController
@RequestMapping("/{api}")
public class AtmRestController {

	public static final Logger logger = LoggerFactory.getLogger(AtmRestController.class);
	@Autowired
	public AtmService atmService;

	@Autowired
	public AccountService accountService;

	@Autowired
	public AccountValidator validator;

	@GetMapping("/withdraw")
	public ResponseEntity<?> withDraw(@RequestParam(value = "accountNumber") String accountNumber,
			@RequestParam(value = "pin") String password, @RequestParam(value = "amount") int amount)
			throws Exception {
		if (validator.validateAccount(accountNumber, password))
			return new ResponseEntity<String>(atmService.withdraw(amount, accountNumber), HttpStatus.OK);
		else
			throw new AccountDetailException("Invalid Account Details");

	}

	@GetMapping("/checkbalance")
	public ResponseEntity<?> checkBalance(@RequestParam(value = "accountNumber") String accountNumber)
			throws Exception {
		logger.info("Fetching Balacne with Account Number {}", accountNumber);
		Account account = accountService.getAccount(accountNumber);
		String accountDetails;
		if (account != null)
			accountDetails = String.valueOf(account.getAmount());
		else
			throw new AccountDetailException("No such Account Found");
		return new ResponseEntity<String>(accountDetails, HttpStatus.OK);
	}

}
