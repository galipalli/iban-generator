package com.venky.iban;

public enum CountryCodeEnum {
	GERMANY("DE",16,"89"),AUSTRIA("AT",12,"61"),NETHERLANDS("NL",14,"91");
	private String countryCode;
	private int bbanCount;
	private String checkDigits;
	
	CountryCodeEnum(String countryCode,int bbanCount,String checkDigits){
		this.countryCode=countryCode;
		this.bbanCount=bbanCount;
		this.checkDigits=checkDigits;
	}

	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @return the bbanCount
	 */
	public int getBbanCount() {
		return bbanCount;
	}

	/**
	 * @return the checkDigits
	 */
	public String getCheckDigits() {
		return checkDigits;
	}
	
}
