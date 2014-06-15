package root.consultantassistant.validaton;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import org.apache.commons.validator.routines.EmailValidator;

public class ValidatingFields {

	/**
	 * Adds a {@link ChangeListener} to the textProperty of the
	 * {@link TextField}. When the <code>TextField</code> textProperty changes the
	 * textProperty will be validated or invalidated, the validation is solely
	 * based on it being a required field. Based on the validation of the
	 * <code>TextField</code>, the CSS style class <code>id property</code> of
	 * <code>TextField</code> will be changed to show the state has been
	 * validated/accepted or invalidated/declined, and focus will be given back
	 * to the <code>TextField</code>, until it is validated. There is no need to
	 * call this more than once, as the {@link ChangeListener} will perform the
	 * validation.
	 * 
	 * @param idAccept
	 *            the #id of the CSS style class for the validated state
	 * @param idDecline
	 *            the #id of the CSS style class for the invalidated state
	 * @param textField
	 *            the <code>TextField</code> to be validated
	 * @return <code>true</code> if the textProperty of <code>textField</code>
	 *         is not <code>null</code>/empty string (also sets id to idAccept),
	 *         and <code>false</code> otherwise(also sets id to idDecline).
	 * @see TextField#focusedProperty()
	 * 
	 */
	public static boolean requiredTextFieldValidatorListener(String idAccept,
			String idDecline, TextField textField) {
		if (textField instanceof TextField) {
			textField.textProperty().addListener(
					new ChangeListener<String>() {

						@Override
						public void changed(
								ObservableValue<? extends String> arg0,
								String arg1, String arg2) {
							if ((textField.getText() == null)
									|| (textField.getText().equals(""))) {
								textField.setId(idDecline);
							} else {
								textField.setId(idAccept);
							}
						}
					});
		}
		if (textField.getId().equals(idAccept)) {
			return true;
		}
		return false;
	}

	/**
	 * Adds a {@link ChangeListener} to the textProperty of the
	 * {@link ComboBox}. When the <code>ComboBox</code> textPrope the
	 * textProperty will be validated or invalidated, the validation is solely
	 * based on it being a required field. Based on the validation of the
	 * <code>ComboBox</code>, the CSS style class <code>id property</code> of
	 * <code>ComboBox</code> will be changed to show the state has been
	 * validated/accepted or invalidated/declined, and focus will be given back
	 * to the <code>ComboBox</code>, until it is validated. There is no need to
	 * call this more than once, as the {@link ChangeListener} will perform the
	 * validation.
	 * 
	 * @param idAccept
	 *            the #id of the CSS style class for the validated state
	 * @param idDecline
	 *            the #id of the CSS style class for the invalidated state
	 * @param comboBox
	 *            the <code>ComboBox</code> to be validated
	 * @return <code>true</code> if <code>comboBox</code> is not
	 *         <code>null</code>/empty string (also sets id to idAccept), and
	 *         <code>false</code> otherwise(also sets id to idDecline).
	 * 
	 */

	public static boolean requiredComboBoxValidatorListener(String idAccept,
			String idDecline, ComboBox<?> comboBox) {
		if (comboBox instanceof ComboBox) {
			
			comboBox.editableProperty().addListener(
					new ChangeListener<Boolean>() {

						@Override
						public void changed(
								ObservableValue<? extends Boolean> observable,
								Boolean oldValue, Boolean newValue) {
							if ((comboBox.getValue() == null)
									|| (comboBox.getValue().equals(""))) {
								comboBox.setId(idDecline);
								
							} else {
								comboBox.setId(idAccept);
							}
						}
					});
		}
		if (comboBox.getId().equals(idAccept)) {
			return true;
		}
		return false;
	}

	/**
	 * Adds a {@link ChangeListener} to the focusedProperty of the
	 * {@link TextField}. When the <code>TextField</code> loses/gains focus the
	 * textProperty will be validated or invalidated. Validation is based on it
	 * being a required field, and also a valid email address, according to
	 * <code>EmailValidator</code>. Based on the validation of the
	 * <code>TextField</code>, the CSS style class <code>id property</code> of
	 * <code>TextField</code> will be changed to show the state has been
	 * validated/accepted or invalidated/declined, and focus will be given back
	 * to the <code>TextField</code>, until it is validated. There is no need to
	 * call this more than once, as the {@link ChangeListener} will perform the
	 * validation.
	 * 
	 * @param idAccept
	 *            the #id of the CSS style class for the validated state
	 * @param idDecline
	 *            the #id of the CSS style class for the invalidated state
	 * @param textField
	 *            the <code>TextField</code> to be validated.
	 * @return <code>true</code> if <code>textField</code> is not
	 *         <code>null</code>/empty string and a valid email address (also
	 *         sets id to idAccept), and <code>false</code> otherwise(also sets
	 *         id to idDecline).
	 */
	public static boolean requiredEmailValidatorListener(String idAccept,
			String idDecline, TextField textField) {
		if (textField instanceof TextField) {

			textField.textProperty().addListener(

			new ChangeListener<String>() {

				@Override
				public void changed(ObservableValue<? extends String> arg0,
						String arg1, String arg2) {
					if ((textField.getText() == null)
							|| (textField.getText().equals(""))) {
						textField.setId(idDecline);
						textField.requestFocus();
					} else {
						EmailValidator ev = EmailValidator.getInstance();
						if (ev.isValid(textField.getText())) {
							textField.setId(idAccept);
						} else {
							textField.setId(idDecline);
							textField.requestFocus();
						}
					}
				}
			});
		}
		if (textField.getId().equals(idAccept)) {
			return true;
		}
		return false;
	}

	/**
	 * Adds a {@link ChangeListener} to the focusedProperty of the
	 * {@link TextField}. When the <code>TextField</code> loses/gains focus the
	 * textProperty will be validated or invalidated. Validation is based on it
	 * being a required field, and also a phone number in the required format,
	 * (xxx)xxx-xxxx. Based on the validation of the <code>TextField</code>, the
	 * CSS style class <code>id property</code> of <code>TextField</code> will
	 * be changed to show the state has been validated/accepted or
	 * invalidated/declined, and focus will be given back to the
	 * <code>TextField</code>, until it is validated. There is no need to call
	 * this more than once, as the {@link ChangeListener} will perform the
	 * validation.
	 * 
	 * @param idAccept
	 *            the #id of the CSS style class for the validated state
	 * @param idDecline
	 *            the #id of the CSS style class for the invalidated state
	 * @param textField
	 *            the <code>TextField</code> to be validated.
	 * @return <code>true</code> if <code>textField</code> is not
	 *         <code>null</code>/empty string and in the format
	 *         (xxx)xxx-xxxx(also sets id to idAccept), and <code>false</code>
	 *         otherwise(also sets id to idDecline).
	 */
	public static boolean requiredPhoneNumberValidatorListener(String idAccept,
			String idDecline, TextField textField) {
		if (textField instanceof TextField) {
			textField.textProperty().addListener(
					new ChangeListener<String>() {

						@Override
						public void changed(
								ObservableValue<? extends String> observable,
								String oldValue, String newValue) {
							if ((textField.getText() == null)
									|| (textField.getText().equals(""))) {
								textField.setId(idDecline);
								textField.requestFocus();
							} else {
								Pattern pattern = Pattern
										.compile("[(]\\d{3}[)]\\d{3}-\\d{4}");
								Matcher matcher = pattern.matcher(textField
										.getText());
								if (matcher.matches()) {
									textField.setId(idAccept);
								} else {
									textField.setId(idDecline);
									textField.requestFocus();
								}
							}

						}
					});
		}
		if (textField.getId().equals(idAccept)) {
			return true;
		}
		return false;
	}

	/**
	 * Adds a {@link ChangeListener} to the focusedProperty of the
	 * {@link TextField}. When the <code>TextField</code> loses/gains focus the
	 * textProperty will be validated or invalidated. Validation is based on it
	 * being a required field, and also a valid password based on the
	 * specifications included in the parameters. This method provides the
	 * flexibility to be as strict/flexible as needed based on the parameters
	 * included. Based on the validation of the <code>TextField</code>, the CSS
	 * style class <code>id property</code> of <code>TextField</code> will be
	 * changed to show the state has been validated/accepted or
	 * invalidated/declined, and focus will be given back to the
	 * <code>TextField</code>, until it is validated. There is no need to call
	 * this more than once, as the {@link ChangeListener} will perform the
	 * validation.
	 * 
	 * @param idAccept
	 *            the #id of the CSS style class for the validated state
	 * @param idDecline
	 *            the #id of the CSS style class for the invalidated state
	 * @param passwordField
	 *            the <code>TextField</code> to be validated
	 * @param length
	 *            the required length of the password
	 * @param numOfInteger
	 *            the required number of integers in the password
	 * @param numOfUpper
	 *            the required number of upper case characters in the password
	 * @param numOfSpecial
	 *            the required number of special characters in the password
	 * @return <code>true</code> if <code>textField</code> is not
	 *         <code>null</code>/empty string and the password is validated to
	 *         true according the parameters (also sets id to idAccept), and
	 *         <code>false</code> otherwise(also sets id to idDecline).
	 */
	public static boolean requiredPasswordValidatorListener(String idAccept,
			String idDecline, PasswordField passwordField, int length,
			int numOfInteger, int numOfUpper, int numOfSpecial) {
		if (passwordField instanceof PasswordField) {
			passwordField.textProperty().addListener(
					new ChangeListener<String>() {

						@Override
						public void changed(
								ObservableValue<? extends String> observable,
								String oldValue, String newValue) {
							boolean flag = true;
							if ((passwordField.getText() == null)
									|| (passwordField.getText().equals(""))) {
								passwordField.setId(idDecline);
								passwordField.requestFocus();
								flag = false;
							} else {
								if ((flag == true)
										&& (passwordField.getText().length() >= length)) {
									Pattern pattern = Pattern.compile("[A-Z]");
									Matcher matcher = pattern
											.matcher(passwordField.getText());
									int start = 0;
									for (int i = 0; i <= numOfUpper; i++) {
										if (matcher.find(start)) {
											start = matcher.start();
										} else {
											passwordField.setId(idDecline);
											passwordField.requestFocus();
											flag = false;
											break;
										}
									}
								} else {
									passwordField.setId(idDecline);
									passwordField.requestFocus();
									flag = false;
								}
								if ((flag == true) && (numOfInteger > 0)) {
									int start = 0;
									Pattern pattern = Pattern.compile("[\\d]");
									Matcher matcher = pattern
											.matcher(passwordField.getText());
									for (int i = 0; i <= numOfInteger; i++) {
										if (matcher.find(start)) {
											start = matcher.start();
										} else {
											passwordField.setId(idDecline);
											passwordField.requestFocus();
											flag = false;
											break;
										}
									}
								}
								if ((flag == true) && (numOfUpper > 0)) {
									int start = 0;
									Pattern pattern = Pattern.compile("[A-Z]");
									Matcher matcher = pattern
											.matcher(passwordField.getText());
									for (int i = 0; i <= numOfUpper; i++) {
										if (matcher.find(start)) {
											start = matcher.start();
										} else {
											passwordField.setId(idDecline);
											passwordField.requestFocus();
											flag = false;
											break;
										}
									}
								}
								if ((flag == true) && (numOfSpecial > 0)) {
									int start = 0;
									Pattern pattern = Pattern.compile("[\\W]");
									Matcher matcher = pattern
											.matcher(passwordField.getText());
									for (int i = 0; i <= numOfSpecial; i++) {
										if (matcher.find(start)) {
											start = matcher.start();
										} else {
											passwordField.setId(idDecline);
											passwordField.requestFocus();
											flag = false;
											break;
										}
									}
								}

							}
							if (flag == true) {
								passwordField.setId(idAccept);
							}
						}
					});
		}
		if (passwordField.getId().equals(idAccept)) {
			return true;
		}
		return false;
	}

	/**
	 * Adds a {@link ChangeListener} to the textProperty of the
	 * {@link TextField}. When the <code>TextField</code> textProperty changes the
	 * textProperty will be validated or invalidated. Validation is based on it
	 * being a required field, and also a valid password based on the
	 * password of the passwordField. Based on the validation of the <code>TextField</code>, the CSS
	 * style class <code>id property</code> of <code>TextField</code> will be
	 * changed to show the state has been validated/accepted or
	 * invalidated/declined. There is no need to call
	 * this more than once, as the {@link ChangeListener} will perform the
	 * validation.
	 * 
	 * @param idAccept
	 *            the #id of the CSS style class for the validated state
	 * @param idDecline
	 *            the #id of the CSS style class for the invalidated state
	 * @param passwordField
	 *            the <code>TextField</code> the confirmPasswordField will be
	 *            validated against
	 * @param confirmPasswordField
	 *            the <code>TextField</code> to be validated
	 * @return <code>true</code> if <code>textField</code> is not
	 *         <code>null</code>/empty string and the password is validated to
	 *         the passwordField (also sets id to idAccept), and
	 *         <code>false</code> otherwise(also sets id to idDecline).
	 */
	public static boolean requiredConfirmPasswordValidatorListener(
			String idAccept, String idDecline, PasswordField passwordField,
			PasswordField confirmPasswordField) {
		if ((passwordField instanceof PasswordField)
				&& (confirmPasswordField instanceof PasswordField)) {
			confirmPasswordField.textProperty().addListener(
					new ChangeListener<String>() {

						@Override
						public void changed(
								ObservableValue<? extends String> observable,
								String oldValue, String newValue) {
							if (passwordField.getId().equals(idAccept)) {
								if (passwordField.getText().equals(
										confirmPasswordField.getText())) {
									confirmPasswordField.setId(idAccept);
								} else {
									confirmPasswordField.setId(idDecline);
								}
							} else {
								confirmPasswordField.setId(idDecline);
								passwordField.requestFocus();
							}
						}
					});
		}
		if (confirmPasswordField.getId().equals(idAccept)) {
			return true;
		}
		return false;
	}
}
