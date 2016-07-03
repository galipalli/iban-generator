package com.venky.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.venky.iban.CountryCodeEnum;
import com.venky.iban.IbanGenerator;
import com.venky.iban.IbanValidator;
import com.venky.iban.exception.InvalidInputException;

public class IbanGeneratorTest {

	
	@Test
	/**
	 * generate unique 100 Iban numbers for Germany.
	 * Tested till 100,000 
	 */
	public void uniqueForAllCountries() {
		final IbanGenerator generator = new IbanGenerator();
		try {
			for(int i=0;i<100;i++){
				String iban = generator.generateIban(CountryCodeEnum.GERMANY);
				assertTrue(new IbanValidator(iban).isValid());	
			}
			
			for(int i=0;i<100;i++){
				String iban = generator.generateIban(CountryCodeEnum.NETHERLANDS);
				assertTrue(new IbanValidator(iban).isValid());	
			}
			
			for(int i=0;i<100;i++){
				String iban = generator.generateIban(CountryCodeEnum.AUSTRIA);
				assertTrue(new IbanValidator(iban).isValid());	
			}
		} catch (InvalidInputException e) {
			fail(e.getMessage());
		}

	}

	
	
	@Test
	public void testForGermany() {
		final IbanGenerator generator = new IbanGenerator();
		try {
			String iban = generator.generateIban(CountryCodeEnum.GERMANY);
			assertTrue(new IbanValidator(iban).isValid());
		} catch (InvalidInputException e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void testForAustria() {
		final IbanGenerator generator = new IbanGenerator();
		try {
			String iban = generator.generateIban(CountryCodeEnum.AUSTRIA);
			assertTrue(new IbanValidator(iban).isValid());
		} catch (InvalidInputException e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void testForNetherlands() {
		final IbanGenerator generator = new IbanGenerator();
		try {
			String iban = generator.generateIban(CountryCodeEnum.NETHERLANDS);
			assertTrue(new IbanValidator(iban).isValid());
		} catch (InvalidInputException e) {
			fail(e.getMessage());
		}
	}
}
