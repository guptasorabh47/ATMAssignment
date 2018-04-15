package ie.assignment.dao;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import ie.assignment.dao.impl.IAccountDao;
import ie.assignment.model.Account;

@RunWith(SpringRunner.class)
public class IAccountDaoTest {
	private AccountDao accountDao = new IAccountDao();

	@Test
	public void getValidAccount() throws Exception {
		String accountNumber = "123456789";
		String password = "1234";
		Assert.assertTrue(accountDao.getValidAccount(accountNumber, password));

	}

	@Test
	public void getInvalidAccount() throws Exception {
		String accountNumber = "12345678";
		String password = "1234";
		Assert.assertFalse(accountDao.getValidAccount(accountNumber, password));

	}

}
