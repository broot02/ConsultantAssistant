package root.consultantassistant.frontend;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import root.consultantassistant.model.Customer;
import root.consultantassistant.validaton.ValidatingFields;

public class NewCustomerScreen extends BorderPane implements Initializable{

	private Customer customer;
	@FXML	//fx:id = fNameTextField 
	private TextField fNameTextField;
	@FXML	//fx:id = lNameTextField
	private TextField lNameTextField;
	@FXML	//fx:id = addressTextField
	private TextField addressTextField;
	@FXML	//fx:id = stateComboBox
	private ComboBox<String> stateComboBox;
	@FXML	//fx:id = cityTextField
	private TextField cityTextField;
	@FXML	//fx:id = zipTextField
	private TextField zipTextField;
	@FXML	//fx:id = phoneNumberTextField
	private TextField phoneNumberTextField;
	@FXML	//fx:id = emailTextField
	private TextField emailTextField;
	@FXML	//fx:id = mailingListChackBox
	private CheckBox mailingListCheckBox;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		assert fNameTextField != null: "fx:id=\"fNameTextField\" was not injected: check your FXML file 'NewCustomerScreen.fxml'.";
		assert lNameTextField != null: "fx:id=\"lNameTextField\" was not injected: check your FXML file 'NewCsutomerScreen.fxml'.";
		assert addressTextField != null: "fx:id=\"addressTextField\" was not injected: check your FXML file 'NewCustomerScreen.fxml'.";
		assert cityTextField != null: "fx:id=\"cityTextField\" was not injected: check your FXML file 'NewCustomerScreen.fxml'.";
		assert stateComboBox != null: "fx:id=\"stateComboBox\" was not injected: check your FXML file 'NewCustomerScreen.fxml'.";
		assert zipTextField != null: "fx:id=\"zipTextField\" was not injected: check your FXML file 'NewCustomerScreen.fxml'.";
		assert emailTextField != null: "fx:id=\"emailTextField\" was not injected: check your FXML file 'NewCustomerScreen.fxml'.";
		assert phoneNumberTextField != null: "fx:id=\"phoneNumberTextField\" was not injected: check your FXML file 'NewCustomerScreen.fxml'.";
		assert mailingListCheckBox != null: "fx:id=\"mailingListCheckBox\" was not injected: check your FXML file 'NewCustomerScreen.fxml'.";
		ValidatingFields.requiredTextFieldValidatorListener("text-field-accept", "text-field-decline", fNameTextField);
		ValidatingFields.requiredTextFieldValidatorListener("text-field-accept", "text-field-decline", lNameTextField);
		ValidatingFields.requiredTextFieldValidatorListener("text-field-accept", "text-field-decline", addressTextField);
		ValidatingFields.requiredComboBoxValidatorListener("text-field-accept", "text-field-decline", stateComboBox);
		ValidatingFields.requiredTextFieldValidatorListener("text-field-accept", "text-field-decline", cityTextField);
		ValidatingFields.requiredTextFieldValidatorListener("text-field-accept", "text-field-decline", zipTextField);
		ValidatingFields.requiredPhoneNumberValidatorListener("text-field-accept", "text-field-decline", phoneNumberTextField);
		ValidatingFields.requiredEmailValidatorListener("text-field-accept", "text-field-decline", emailTextField);
		
	}
	

	

}
