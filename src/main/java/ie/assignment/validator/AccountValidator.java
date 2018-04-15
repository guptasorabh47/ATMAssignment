package ie.assignment.validator;

import ie.assignment.exception.AccountDetailException;

public interface AccountValidator {

boolean validateAccount(String accountNumber, String password)throws Exception;
}
