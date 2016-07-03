package com.venky.iban;
/**
 * 
 */


import java.math.BigInteger;

import com.venky.iban.exception.InvalidInputException;



/**
 * Validate IBAN number
 */
public class IbanValidator {
		

	private static final BigInteger BD_97 = new BigInteger("97");
	private static final BigInteger BD_98 = new BigInteger("98");
	private String iban;

	/**
	 * Get the IBAN
	 * @return a string with the IBAN
	 */
	public String getIban() {
		return iban;
	}

	/**
	 * Set the IBAN
	 * @param iban the IBAN to set
	 */
	public void setIban(String iban) {
		this.iban = iban;
	}

	/**
	 * Create an IBAN object with the given iban code.
	 * @param iban
	 */
	public IbanValidator(String iban) {
		this.iban = iban;
	}
	
	/**
	 * Completely validate an IBAN
	 * @return <code>true</code> if the IBAN is valid else it will return false
	 * @throws InvalidInputException if iban is <code>null</code>
	 */
	public boolean isValid() {
		if (this.iban==null || this.iban.length()==0)
			throw new InvalidInputException("Iban cannot be null");
		final String code = removeNonAlpha(this.iban);
		final int len = code.length();
		if (len<4) {
			throw new InvalidInputException("Invalid Iban (expected at least 4, got "+len+")");
		}
		final String country = code.substring(0, 2);
		
		final StringBuffer bban = new StringBuffer(code.substring(4));
		if (bban.length()==0) {
			throw new InvalidInputException("BBan is empty");
		}
		bban.append(code.substring(0, 4));
		
		String workString = translateChars(bban);
		int mod = modulo97(workString);
		if (mod!=1) {
			return false;
		}
		
		return true;
	}
	

	/**
	 * Translate letters to numbers, also ignoring non alphanumeric characters
	 * 
	 * @param bban
	 * @return the translated value
	 */
	public String translateChars(final StringBuffer bban) {
		final StringBuffer result = new StringBuffer();
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
	
	/**
	 * 
	 * @param iban
	 * @return the resulting IBAN
	 */
	public String removeNonAlpha(final String iban) {
		final StringBuffer result = new StringBuffer();
		for (int i=0;i<iban.length();i++) {
			char c = iban.charAt(i);
			if (Character.isLetter(c) || Character.isDigit(c) ) {
				result.append((char)c);
			}
		}
		return result.toString();
	}

	private int modulo97(String bban) {
		BigInteger b = new BigInteger(bban);
		b = b.divideAndRemainder(BD_97)[1];
		b = BD_98.min(b);
		b = b.divideAndRemainder(BD_97)[1];
		return b.intValue();
	}

}

