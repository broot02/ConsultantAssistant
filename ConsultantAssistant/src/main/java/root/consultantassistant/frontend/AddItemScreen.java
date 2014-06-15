package root.consultantassistant.frontend;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import root.consultantassistant.model.OrderLine;
import root.consultantassistant.validaton.ValidatingFields;
import root.screenframework.ControlledScreen;
import root.screenframework.ScreenController;

public class AddItemScreen extends BorderPane implements Initializable,
		ControlledScreen{

	ScreenController myController;

	private OrderLine orderLine;

	@FXML
	// fx:id = "layoutPane"
	private BorderPane layoutPane;

	@FXML
	// fx:id = "productDescriptionTextField"
	private TextField productDescriptionTextField;

	@FXML
	// fx:id = "quantityTextField"
	private TextField quantityTextField;

	@FXML
	// fx:id = "priceTextField"
	private TextField priceTextField;

	@FXML
	// fx:id = "addItemButton"
	private Button addItemButton;

	@FXML
	// fx:id = "cancelButton"
	private Button cancelButton;

	@Override
	public void setScreenParent(final ScreenController screenPage) {
		myController = screenPage;

	}

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {
		assert layoutPane != null : "fx:id=\"layoutPane\" was not injected: check your FXML file 'AddItemScreen.fxml";
		assert productDescriptionTextField != null : "fx:id=\"productDescriptionTextField\" was not injected: check your FXML file 'AddItemScreen.fxml'.";
		assert quantityTextField != null : "fx:id=\"quantityTextField\" was not injected: check your FXML file 'AddItemScreen.fxml'.";
		assert priceTextField != null : "fx:id=\"priceTextField\" was not injected: check your FXML file 'AddItemScreen.fxml'.";
		assert addItemButton != null : "fx:id=\"addItemButton\" was not injected: check your FXML file 'AddItemScreen.fxml'.";
		assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'AddItemScreen.fxml'.";
		ValidatingFields.requiredTextFieldValidatorListener(
				"text-field-accept", "text-field-decline",
				productDescriptionTextField);
		ValidatingFields.requiredTextFieldValidatorListener(
				"text-field-accept", "text-field-decline", quantityTextField);
		ValidatingFields.requiredTextFieldValidatorListener(
				"text-field-accept", "text-field-decline", priceTextField);

	}

	@FXML
	public void handleAddItemButton(final ActionEvent event) throws IOException {
		productDescriptionTextField.setText("Test");
		if (productDescriptionTextField.getId().equals("text-field-accept")
				&& quantityTextField.getId().equals("text-field-accept")
				&& priceTextField.getId().equals("text-field-accept")) {
			orderLine = new OrderLine(Integer.valueOf(quantityTextField
					.getText()), productDescriptionTextField.getText(),
					Double.valueOf(priceTextField.getText()));
			FXMLLoader fxmlLoader = new FXMLLoader(
					OrderScreen.class.getResource("/OrderScreen.fxml"));
			OrderScreen control = (OrderScreen) fxmlLoader.getController();
			//control.addData(orderLine);
		}
	}

	@FXML
	public void handleCancelButton(final ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(
				OrderScreen.class.getResource("/OrderScreen.fxml"));
		try {
			loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		OrderScreen screen = ((OrderScreen) loader.getController());
		//PopOver popup = screen.getPopup();
		//popup.hide();
	}

	@FXML
	public OrderLine getOrderLine() {
		return orderLine;
	}



}
