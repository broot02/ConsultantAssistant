package root.consultant.assistant.Test.Model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import root.consultantassistant.model.CreditCard;
import root.consultantassistant.model.CreditCard.CreditCardType;

public class CreditCardTest {

	@Test
	public void testGetCreditCardNumber() {
		CreditCard creditCard = new CreditCard();
		creditCard.setCreditCardNumber("378282246310005");
		assertEquals("The credit card number should be 378282246310005",
				"378282246310005",creditCard.getCreditCardNumber());
	}
	
	
	@Test
	public void testSetCreditCardNumber() {
		CreditCard creditCard = new CreditCard();
		creditCard.setCreditCardNumber("378282246310005");
		assertEquals("This card number should set to 378282246310005","378282246310005",
				creditCard.getCreditCardNumber());
		creditCard.setCreditCardNumber("6011111111111117");
		assertEquals("This card number should be to 6011111111111117", "6011111111111117",
				creditCard.getCreditCardNumber());
	}

	@Test
	public void testGetSecurityCode() {
		CreditCard creditCard = new CreditCard();
		creditCard.setSecurityCode("1234");
		assertEquals("This security number should be 1234", "1234", creditCard.getSecurityCode());
	}
	
	@Test
	public void testSetSecurityCode() {
		CreditCard creditCard = new CreditCard();
		creditCard.setSecurityCode("1234");
		assertEquals("This security number should be 1234", "1234", creditCard.getSecurityCode());
		creditCard.setSecurityCode("3421");
		assertEquals("This security number should be 3421", "3421", creditCard.getSecurityCode());
	}
	
	
	@Test
	public void testGetExpirationMonth() {
		CreditCard creditCard = new CreditCard();
		creditCard.setExpirationMonth(2);
		assertEquals("The expiration date should be set to 2", 2,
				creditCard.getExpirationMonth());
		creditCard.setExpirationMonth(-1);
		assertEquals("The expiration date should be set to 2", 2,
				creditCard.getExpirationMonth());
		creditCard.setExpirationMonth(13);
		assertEquals("The expiration date should be set to 2", 2,
				creditCard.getExpirationMonth());
	}
	
	
	@Test
	public void testSetExpirationMonth() {
		CreditCard creditCard = new CreditCard();
		creditCard.setExpirationMonth(2);
		assertEquals("The expiration date should be set to 2", 2,
				creditCard.getExpirationMonth());
		creditCard.setExpirationMonth(-1);
		assertEquals("The expiration date should be set to 2", 2,
				creditCard.getExpirationMonth());
		creditCard.setExpirationMonth(13);
		assertEquals("The expiration date should be set to 2", 2,
				creditCard.getExpirationMonth());
	}
	
	@Test
	public void testGetExpirationYear() {
		CreditCard creditCard = new CreditCard();
		creditCard.setExpirationYear(2);
		assertEquals("The expiration year should be set to 2", 2,
				creditCard.getExpirationYear());
		creditCard.setExpirationYear(-1);
		assertEquals("The expiration year should be set to 2", 2,
				creditCard.getExpirationYear());
		creditCard.setExpirationYear(2015);
		assertEquals("The expiration year should be set to 2015", 2015,
				creditCard.getExpirationYear());
	}
	
	
	@Test
	public void testSetExpirationYear() {
		CreditCard creditCard = new CreditCard();
		creditCard.setExpirationYear(2);
		assertEquals("The expiration year should be set to 2", 2,
				creditCard.getExpirationYear());
		creditCard.setExpirationYear(-1);
		assertEquals("The expiration year should be set to 2", 2,
				creditCard.getExpirationYear());
		creditCard.setExpirationYear(2015);
		assertEquals("The expiration year should be set to 2015", 2015,
				creditCard.getExpirationYear());
	}
	
	
	@Test
	public void testSetCardType(){
		CreditCard creditCard = new CreditCard();
		creditCard.setCardType(CreditCardType.AmericanExpress);
		assertEquals("The credit card type should be set to American Express", 0, creditCard.getCardType().ordinal());
		creditCard.setCardType(CreditCardType.MasterCard);
		assertEquals("The credit card type should be set to Master Card", 1, creditCard.getCardType().ordinal());
		creditCard.setCardType(CreditCardType.Discover);
		assertEquals("The credit card type should be set to Discover", 2, creditCard.getCardType().ordinal());
	}
	
	@Test
	public void testGetCardType(){
		CreditCard creditCard = new CreditCard();
		creditCard.setCardType(CreditCardType.AmericanExpress);
		assertEquals("The credit card type should be set to American Express", CreditCardType.AmericanExpress, creditCard.getCardType());
		creditCard.setCardType(CreditCardType.MasterCard);
		assertEquals("The credit card type should be set to Master Card", CreditCardType.MasterCard, creditCard.getCardType());
		creditCard.setCardType(CreditCardType.Discover);
		assertEquals("The credit card type should be set to Discover", CreditCardType.Discover, creditCard.getCardType());
	}
	
	@Test
	public void testValidateCreditCardNumber(){
		CreditCard creditCard = new CreditCard();
		creditCard.setCreditCardNumber("378282246310005");
		assertEquals("This card number should be valid(amex test)",true,
				creditCard.validateCreditCardNumber());
		creditCard.setCreditCardNumber("6011111111111117");
		assertEquals("This card number should be valid(disover test)", true,
				creditCard.validateCreditCardNumber());
		creditCard.setCreditCardNumber("5555555555554444");
		assertEquals("This card number should be valid(mastercard test)", true,
				creditCard.validateCreditCardNumber());
		creditCard.setCreditCardNumber("4111111111111111");
		assertEquals("This card number shouldn't be valid(visa test)", false,
				creditCard.validateCreditCardNumber());
		creditCard.setCreditCardNumber("3530111333300000");
		assertEquals("This card number shouldn't be valid(jcb test)", false,
				creditCard.validateCreditCardNumber());
		creditCard.setCreditCardNumber("129021974717412123");
		assertEquals("This card number shouldn't be valid and not set(random)", false,
				creditCard.validateCreditCardNumber());
		creditCard.setCreditCardNumber("5555555555554444a");
		assertEquals("This card number shouldn't be valid and not set(characters)", false,
				creditCard.validateCreditCardNumber());
	}
		
	@Test
	public void testValidateSecurityCode(){
		CreditCard creditCard = new CreditCard();
		creditCard.setCardType(CreditCardType.AmericanExpress);
		creditCard.setSecurityCode("123");
		assertEquals("123 should be a valid security code for an American Express card",
				true,creditCard.validateSecurityCode());
		creditCard.setCardType(CreditCardType.MasterCard);
		creditCard.setSecurityCode("1234");
		assertEquals("1234 should be a valid security code for Mastercard",
				true,creditCard.validateSecurityCode());
		creditCard.setCardType(CreditCardType.Discover);
		creditCard.setSecurityCode("1234");
		assertEquals("1234 should be a valid security code for a Discover card",
				true,creditCard.validateSecurityCode());
		creditCard.setCardType(CreditCardType.AmericanExpress);
		creditCard.setSecurityCode("1234");
		assertEquals("1234 shouldn't be a valid security code for an American Express card",
				false,creditCard.validateSecurityCode());
		creditCard.setCardType(CreditCardType.MasterCard);
		creditCard.setSecurityCode("123");
		assertEquals("123 shouldn't be a valid security code for Mastercard",
				false,creditCard.validateSecurityCode());
		creditCard.setCardType(CreditCardType.Discover);
		creditCard.setSecurityCode("123");
		assertEquals("123 shouldn't be a valid security code for Mastercard",
				false,creditCard.validateSecurityCode());
	}
	
	@Test
	public void testValidateExpirationDate(){
		CreditCard creditCard = new CreditCard();
		creditCard.setExpirationMonth(10);
		creditCard.setExpirationYear(2014);
		assertEquals("10/2014 should be a valid expiration date", true, creditCard.validateExpirationDate());
		creditCard.setExpirationMonth(7);
		creditCard.setExpirationYear(2014);
		assertEquals("7/2014 should be a valid expiration date", true, creditCard.validateExpirationDate());
		creditCard.setExpirationMonth(5);
		creditCard.setExpirationYear(2013);
		assertEquals("5/2013 shouldn't be a valid expiration date", false, creditCard.validateExpirationDate());
		creditCard.setExpirationMonth(4);
		creditCard.setExpirationYear(2014);
		assertEquals("4/2014 shouldn't be a valid expiration date", false, creditCard.validateExpirationDate());
	}
	
	@Test
	public void testIsValid() {
		CreditCard creditCard = new CreditCard();
		creditCard.setExpirationMonth(10);
		creditCard.setExpirationYear(2014);creditCard.setCardType(CreditCardType.AmericanExpress);
		creditCard.setSecurityCode("123");
		creditCard.setCreditCardNumber("378282246310005");
		assertEquals("This american express card should be valid",true, creditCard.isValid());
		creditCard.setCreditCardNumber("282246310005");
		assertEquals("This american express card shouldn't be valid",false, creditCard.isValid());		
		
	}

}
