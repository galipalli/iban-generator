# IBan Generator 
The utility provides functionality to generate valid IBAN's for different countries.IBan number is syntactically valid according to the
ISO standard, but not necessarily existing bank accounts. At the beginning, Germany, Austria and the Netherlands are suported.

This is Maven project and creates Simple Jar file that can be used in any other project. Junit Jar is included for testing the code.

**IBan Generator Quick example For Austria**

`final IbanGenerator generator = new IbanGenerator();`

`String iban = generator.generateIban(CountryCodeEnum.AUSTRIA);`

**For Netherlands**

`final IbanGenerator generator = new IbanGenerator();`

`String iban = generator.generateIban(CountryCodeEnum.NETHERLANDS);`

**For Germany**

`final IbanGenerator generator = new IbanGenerator();`

`String iban = generator.generateIban(CountryCodeEnum.GERMANY);`
