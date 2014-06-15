package root.consultantassistant.frontend;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import org.springframework.beans.factory.annotation.Autowired;

import root.consultantassistant.model.Consultant;
import root.screenframework.ControlledScreen;
import root.screenframework.ScreenController;

public class LoginScreen extends BorderPane 
implements Initializable, ControlledScreen{

	private ScreenController myController;
	@FXML	//fx:id ="userIDField"
	private TextField userIDField;
	@FXML	//fx:id ="passwordField"
	private PasswordField passwordField;
	@FXML	//fx:id = "signInButton"
	private Button signInButton;
	@FXML	//fx:id = "forgotButton"
	private Button forgotButton;
	@FXML	//fx:id = "registerButton"
	private Button registerButton;
	
	@Autowired
	private Consultant consultant;


	@Override
	public void initialize(final URL arg0, final ResourceBundle arg1) {
		assert userIDField != null : "fx:id=\"userIDField\" was not injected: check your FXML file 'LoginScreen.fxml'.";
		assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'LoginScreen.fxml'.";
		assert signInButton != null : "fx:id=\"signInButton\" was not injected: check your FXML file 'LoginScreen.fxml'.";
		assert forgotButton != null : "fx:id=\"forgotButton\" was not injected: check your FXML file 'LoginScreen.fxml'.";
		assert registerButton != null : "fx:id=\"registerButton\" was not injected: check your FXML file 'LoginScreen.fxml'.";
	}


	public void setUserIDField(){
		userIDField.setText("Hello");
	}

	@FXML
	public void handleRegisterButton(final ActionEvent event)throws Exception{
		myController.setScreen("RegistrationScreen");
		myController.unloadScreen("LoginScreen");

	}


	@FXML
	public void handleSignInButton(final ActionEvent event)throws Exception{
		if(userIDField.getText().equals("broot02") && passwordField.getText().equals("smokie")){
			myController.setScreen("OrderScreen");
			myController.unloadScreen("LoginScreen");
			consultant.setAddress("407 N. Gould St.");
			consultant.setCity("Owosso");
			consultant.setEmail("rootbrad86@gmail.com");
			consultant.setFirstName("Teri");
			consultant.setLastName("Root");
			consultant.setPassword("smokie");
			consultant.setPhoneNumber("(999)999-9999");
			consultant.setState("Michigan");
			consultant.setUserId("broot02");
			consultant.setZip(48867);
		}
	}
	@Override
	public void setScreenParent(final ScreenController screenPage) {
		myController = screenPage;
	}



}
