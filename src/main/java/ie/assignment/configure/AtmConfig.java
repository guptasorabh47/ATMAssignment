package ie.assignment.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ie.assignment.dao.AccountDao;
import ie.assignment.dao.AtmDao;
import ie.assignment.dao.impl.IAccountDao;
import ie.assignment.dao.impl.IAtmDao;
import ie.assignment.service.AccountService;
import ie.assignment.service.AtmService;
import ie.assignment.service.impl.IAccountService;
import ie.assignment.service.impl.IAtmService;
import ie.assignment.validator.AccountValidator;
import ie.assignment.validator.impl.IAccountValidator;
// sample
@Configuration
public class AtmConfig {

	@Bean
	AccountValidator accountValidator(){
		return new IAccountValidator();
	}
	
	@Bean
	AccountService accountService() {
		return new IAccountService();
	}

	@Bean
	AtmService atmService() {
		return new IAtmService();
	}

	@Bean
	AccountDao accountDao() {
		return new IAccountDao();
	}

	@Bean
	AtmDao atmDao() {
		return new IAtmDao();
	}

}
