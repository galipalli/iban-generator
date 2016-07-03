package com.venky.iban;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class IbanGenerator {
	private static Random rnd = new Random();
	private static final Set<String> UNIQUE_IBAN_NUMBERS= new HashSet<String>();
	private static final BigInteger BD_97 = new BigInteger("97");
	private static final BigInteger BD_98 = new BigInteger("98");
	
	/**
	 * Generate Iban number for Germany,Austria and Netherlands
	 * @param countryCodeEnum 
	 * @return Iban number
	 */
	public synchronized String generateIban(final CountryCodeEnum countryCodeEnum) {
		final StringBuilder iban = new StringBuilder(countryCodeEnum.getCountryCode());
		iban.append(countryCodeEnum.getCheckDigits());
		
		String number=translateChars(iban);
		String bban=generateBban(countryCodeEnum);
		StringBuilder generatedNumber=new StringBuilder(bban).append(number);
		boolean modulo=checkModulo(generatedNumber);
		while(!modulo ){
			bban=generateBban(countryCodeEnum);
			generatedNumber=new StringBuilder(bban).append(number);
			modulo=checkModulo(generatedNumber);
			if(modulo && UNIQUE_IBAN_NUMBERS.contains(iban.toString()+bban)){
				modulo=false;
			}
		}
		iban.append(bban);
		return iban.toString();
	}

	private boolean checkModulo(StringBuilder generatedNumber) {
		BigInteger b = new BigInteger(generatedNumber.toString());
		b = b.divideAndRemainder(BD_97)[1];
		b = BD_98.min(b);
		b = b.divideAndRemainder(BD_97)[1];
		return (b.intValue()==1);
	}



	/**
	 * Translate letters to numbers, also ignoring non alphanumeric characters
	 * 
	 * @param bban
	 * @return the translated value
	 */
	private String translateChars(final StringBuilder bban) {
		final StringBuilder result = new StringBuilder();
		for (int i=0;i<bban.length();i++) {
			char c = bban.charAt(i);
			if (Character.isLetter(c)) {
				result.append(Character.getNumericValue(c));
			} else {
				result.append((char)c);
			}
		}
		return result.toString();
	}
	
	private String generateBban(CountryCodeEnum countryCodeEnum) {
		StringBuilder sb = new StringBuilder(countryCodeEnum.getBbanCount());
		for (int i = 0; i < countryCodeEnum.getBbanCount(); i++)
			sb.append((char) ('0' + rnd.nextInt(10)));
		return sb.toString();
	}
}
