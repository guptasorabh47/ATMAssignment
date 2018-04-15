package ie.assignment.dao.impl;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import ie.assignment.dao.AtmDao;
import ie.assignment.model.Atm;
import ie.assignment.model.AtmBalance;

public class IAtmDao implements AtmDao {

	private static final Logger logger = LoggerFactory.getLogger(IAtmDao.class);
	private static final String AtmFile = "ATM.json";
	
	@Override
	public void updateAmount(int amount, AtmBalance atmBalance) throws Exception {
		logger.debug("Updating balance in ATM after withdrawal");
		Atm atm = new Atm(atmBalance, amount);
		writeData(atm);
	}

	@Override
	public Atm getTotalAmount()throws Exception {
		logger.debug("Retrieving amount from ATM");
		return readData();
	}

	private static void writeData(Atm atm) {
		logger.debug("Writing data to json for ATM");
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(new File(ClassLoader.getSystemClassLoader().getResource(AtmFile).getPath()), atm);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	private static Atm readData() {
		logger.debug("Reading data from ATM json");
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(new File(ClassLoader.getSystemClassLoader().getResource(AtmFile).getPath()),
					Atm.class);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

}
