package ie.assignment.dao;

import ie.assignment.model.Atm;
import ie.assignment.model.AtmBalance;

public interface AtmDao {
	public void updateAmount(int amount, AtmBalance atmBalance)throws Exception;

	public Atm getTotalAmount()throws Exception;
}
