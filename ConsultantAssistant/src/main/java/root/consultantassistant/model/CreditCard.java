package root.consultantassistant.model;

import java.io.Serializable;

import org.apache.commons.validator.routines.CreditCardValidator;
import org.joda.time.LocalDate;
/**
 * The <code>CreditCard</code> class is used to simulate payment
 * information for a credit card, which could be used in a POS system.
 * It provides validation based on card number, card security code,
 * and expiration date. Proper use of this class is to call the <code>
 * isValid()</code> method to test for a valid card after
 * it has been set to the desired values, if this is not
 * done an invalid card could be used.
 * @since 1.0
 * @author Brad Root
 * @version 1.0
 */
public class CreditCard implements Serializable {
	/**
	 * <code>CreditCardType</code> is used to limit the options of
	 *  the card type and ensure it is valid credit card type. Validation
	 *  is performed in different ways based on this type.
	 *  It provides the flexibility to add more 
	 *  credit card types.	
	 */
	public enum CreditCardType {
		/**
		 * Represents an AmericanExpress card.
		 */
		AmericanExpress,
		/**
		 * Represents a MasterCard card.
		 */
		MasterCard,
		/**
		 * Represents a Discover card.
		 */
		Discover
	};
	/**
	 * Unique ID for serialization.
	 */
	private static final long serialVersionUID = -3234563581538390264L;
	/**
	 * Represents the credit card number.
	 */
	private String creditCardNumber;
	/**
	 * Represents the card security code. 
	 */
	private String securityCode;
	/**
	 * Represents the type of credit card based off
	 * <code>CreditCardType</code>.
	 */
	private CreditCardType cardType;
	/**
	 * Represents the expiration month of the card.
	 */
	private int expirationMonth;
	/**
	 * Represent the expiration year of the card.
	 */
	private int expirationYear;
	
	/**
	 * Default constructor provided,
	 * as a parameterized constructor was also provided.
	 */
	public CreditCard() {
		creditCardNumber = new String();
	}
	/**
	 * Performs initialization of the entire <code>
	 * CreditCard</code> object. The card is not validated
	 * at this point, an invalid card could be input, and used. 
	 * @param newCreditCardNumber - 
	 * Represents the credit card number.
	 * @param newSecurityCode -
	 * Represents the credit security code.
	 * @param newExpirationMonth -
	 * Represents the expiration month of the card.
	 * @param newExpirationYear -
	 * Represents the expiration year of the card.
	 * @param newCardType - 
	 * Represents the type of credit card.
	 */
	public CreditCard(final String newCreditCardNumber, 
			final String newSecurityCode, final int newExpirationMonth,
			final int newExpirationYear, final CreditCardType newCardType) {
		setCreditCardNumber(newCreditCardNumber);
		setSecurityCode(newSecurityCode);
		setExpirationMonth(newExpirationMonth);
		setExpirationYear(newExpirationYear);
		setCardType(newCardType);
	}
	/**
	 * Returns the credit card number of the
	 * <code>CreditCard</code>.
	 * @return - 
	 * The credit card number of the <code>CreditCard</code>.
	 */
	public final String getCreditCardNumber() {
		return creditCardNumber;
	}
	/**
	 * Sets the credit card number to the new credit card number
	 * passed to the method.
	 * @param newCreditCardNumber - 
	 * The credit card number of the <code>CreditCard</code>. 
	 */
	public final void setCreditCardNumber(final String newCreditCardNumber) {
		creditCardNumber = newCreditCardNumber;
	}
	/**
	 * Returns the <code>CreditCardType</code> of the
	 *  <code>CreditCard</code>.
	 * @return - 
	 * The <code>CreditCardType</code> of the <code>CreditCard</code>.
	 */
	public final CreditCardType getCardType() {
		return cardType;
	}
	/**
	 * Sets the <code>CreditCardType</code> to the newCardType.
	 * @param newCardType - 
	 * <code>CreditCardType</code> to set the cards type to.  
	 */
	public final void setCardType(final CreditCardType newCardType) {
		cardType = newCardType;
	}
	/**
	 * Returns the credit security code of the <code>CreditCard</code>.
	 * @return - 
	 * The credit security code of the <code>CreditCard</code>.
	 */
	public final String getSecurityCode() {
		return securityCode;
	}
	/**
	 * Sets the credit security code of the <code>CreditCard</code>
	 * to the newSecurityCode.
	 * @param newSecurityCode - 
	 * The new credit security code
	 */
	public final void setSecurityCode(final String newSecurityCode) {
		securityCode = newSecurityCode;
	}

	public final int getExpirationMonth() {
		return expirationMonth;
	}

	public final int getExpirationYear() {
		return expirationYear;
	}
	/**
	 * Sets the <code>CreditCard</code> expiration month 
	 * to expirationMonth but only if it passes the validation.
	 * The month must be between 1 and 12.
	 * @param newExpirationMonth -
	 * Represents the expiration month of the card.
	 */
	public final void setExpirationMonth(final int newExpirationMonth) {
		/*
		 * Represents the first month/minimum month. 
		 */
		final int minMonth = 1;
		/*
		 * Represents the last month/maximum month.
		 */
		final int maxMonth = 12;
		if ((newExpirationMonth >= minMonth) && 
				(newExpirationMonth <= maxMonth)) {
			// if valid month
			expirationMonth = newExpirationMonth;
		}
	}
	/**
	 * 
	 * @param expirationYear - 
	 * The year the 
	 */
	public final void setExpirationYear(final int expirationYear) {
		if (expirationYear > 0) {
			this.expirationYear = expirationYear;
		}
	}

	public final boolean isValid() {
		if ((validateExpirationDate()) && (validateCreditCardNumber())
				&& (validateSecurityCode())) {
			return true;
		}
		return false;
	}

	public final boolean validateExpirationDate() {
		LocalDate currentCal = new LocalDate();
		LocalDate expCal = new LocalDate(expirationYear, expirationMonth, 1)
		.dayOfMonth().withMaximumValue();

		if (expCal.isAfter(currentCal)) {
			return true;
		}
		return false;
	}

	public final boolean validateSecurityCode() {
		if (getCardType() == CreditCardType.AmericanExpress) {
			return getSecurityCode().matches("\\d{3}");
		}
		return getSecurityCode().matches("\\d{4}");

	}

	public final boolean validateCreditCardNumber() {
		CreditCardValidator ccv = new CreditCardValidator(
				CreditCardValidator.AMEX + CreditCardValidator.DISCOVER
						+ CreditCardValidator.MASTERCARD);
		if (ccv.isValid(getCreditCardNumber())) {
			return true;
		}
		return false;
	}

}
