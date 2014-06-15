package root.consultantassistant.frontend;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import root.consultantassistant.model.Consultant;
import root.consultantassistant.validaton.ValidatingFields;
import root.screenframework.ControlledScreen;
import root.screenframework.ScreenController;

public class RegistrationScreen extends BorderPane implements Initializable, ControlledScreen {
	ScreenController myController;
	// Holds consultant data and performs validation against input
	private Consultant consultant;
	@FXML
	// fx:id = fNameTextField
	private TextField fNameTextField;
	@FXML
	// fx:id = lNameTextField
	private TextField lNameTextField;
	@FXML
	// fx:id = addressTextField
	private TextField addressTextField;
	@FXML
	// fx:id = cityTextField
	private TextField cityTextField;
	@FXML
	// fx:id = stateComboBox
	private ComboBox<String> stateComboBox;
	@FXML
	// fx:id = zipTextField
	private TextField zipTextField;
	@FXML
	// fx:id = emailTextField
	private TextField emailTextField;
	@FXML
	// fx:id = phoneNumberTextField
	private TextField phoneNumberTextField;
	@FXML
	// fx:id = userIdTextField
	private TextField userIdTextField;
	@FXML
	// fx:id = passwordField
	private PasswordField passwordField;
	@FXML
	// fx:id = confirmPasswordField
	private PasswordField confirmPasswordField;

	// Ensures that the fxml file was injected properly, and performs
	// initialization of the fields if needed.
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		assert fNameTextField != null : "fx:id=\"fNameTextField\" was not injected: check your FXML file 'RegistrationScreen.fxml'.";
		assert lNameTextField != null : "fx:id=\"lNameTextField\" was not injected: check your FXML file 'RegistrationScreen.fxml'.";
		assert addressTextField != null : "fx:id=\"addressTextField\" was not injected: check your FXML file 'RegistrationScreen.fxml'.";
		assert cityTextField != null : "fx:id=\"cityTextField\" was not injected: check your FXML file 'RegistrationScreen.fxml'.";
		assert stateComboBox != null : "fx:id=\"stateComboBox\" was not injected: check your FXML file 'RegistrationScreen.fxml'.";
		assert zipTextField != null : "fx:id=\"zipTextField\" was not injected: check your FXML file 'RegistrationScreen.fxml'.";
		assert emailTextField != null : "fx:id=\"emailTextField\" was not injected: check your FXML file 'RegistrationScreen.fxml'.";
		assert phoneNumberTextField != null : "fx:id=\"phoneNumberTextField\" was not injected: check your FXML file 'RegistrationScreen.fxml'.";
		assert userIdTextField != null : "fx:id=\"userIdTextField\" was not injected: check your FXML file 'RegistrationScreen.fxml'.";
		assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'RegistrationScreen.fxml'.";
		assert confirmPasswordField != null : "fx:id=\"confirmPasswordField\" was not injected: check your FXML file 'RegistrationScreen.fxml'.";

		// Call Static Method to set up validation of fields
		ValidatingFields.requiredTextFieldValidatorListener(
				"text-field-accept", "text-field-decline", fNameTextField);
		ValidatingFields.requiredTextFieldValidatorListener(
				"text-field-accept", "text-field-decline", lNameTextField);
		ValidatingFields.requiredTextFieldValidatorListener(
				"text-field-accept", "text-field-decline", addressTextField);
		ValidatingFields.requiredTextFieldValidatorListener(
				"text-field-accept", "text-field-decline", cityTextField);
		ValidatingFields.requiredComboBoxValidatorListener("combo-box-accept",
				"combo-box-decline", stateComboBox);
		ValidatingFields.requiredTextFieldValidatorListener(
				"text-field-accept", "text-field-decline", zipTextField);
		ValidatingFields.requiredEmailValidatorListener("text-field-accept",
				"text-field-decline", emailTextField);
		ValidatingFields.requiredTextFieldValidatorListener(
				"text-field-accept", "text-field-decline", userIdTextField);
		ValidatingFields
				.requiredPhoneNumberValidatorListener("text-field-accept",
						"text-field-decline", phoneNumberTextField);
		ValidatingFields.requiredPasswordValidatorListener("text-field-accept",
				"text-field-decline", passwordField, 8, 2, 1, 0);
		ValidatingFields.requiredConfirmPasswordValidatorListener(
				"text-field-accept", "text-field-decline", passwordField,
				confirmPasswordField);
	}
	@FXML
	public boolean validate() {
		if ((fNameTextField.getId().equals("text-field-accept"))
				&& (lNameTextField.getId().equals("text-field-accept"))
				&& (addressTextField.getId().equals("text-field-accept"))
				&& (cityTextField.getId().equals("text-field-accept"))
				&& (stateComboBox.getId().equals("combo-box-accept"))
				&& (zipTextField.getId().equals("text-field-accept"))
				&& (emailTextField.getId().equals("text-field-accept"))
				&& (phoneNumberTextField.getId().equals("text-field-accept"))
				&& (userIdTextField.getId().equals("text-field-accept"))
				&& (passwordField.getId().equals("text-field-accept"))
				&& (confirmPasswordField.getId().equals("text-field-accept"))) {
				
			File loginFile = new File(getClass() + "users.txt");
			if (loginFile.isFile()) {
				Path file = Paths.get(getClass().getResource("users.txt").toString());
				try {
					List<String> users = Files.readAllLines(file);
					for(String user: users){
						if(user.equals(userIdTextField.getText())){
							return false;
						}
					}
					users.add(userIdTextField.getText());				
					Files.write(file, users, Charset.defaultCharset());
					return true;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					// Create the empty file with default permissions, etc.
					Path path = Paths.get(getClass() + "users.txt");
					Files.createFile(path);
					try (BufferedWriter writer = Files.newBufferedWriter(path, Charset.defaultCharset())) {
					    writer.write(userIdTextField.getText(), 0, userIdTextField.getText().length());
					} catch (IOException x) {
					    System.err.format("IOException: %s%n", x);
					}
				} catch (FileAlreadyExistsException x) {
					System.err.format("file named %s" + " already exists%n",
							loginFile);
				} catch (IOException x) {
					// Some other sort of failure, such as permissions.
					System.err.format("createFile error: %s%n", x);
				}
			}
			return true;
		} else {
			return false;
		
		}
	}
	@Override
	public void setScreenParent(ScreenController screenPage) {
		myController = screenPage;
		
	}

}
