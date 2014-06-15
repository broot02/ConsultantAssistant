package root.consultantassistant.frontend;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

import org.controlsfx.control.PopOver;
import org.controlsfx.control.PopOver.ArrowLocation;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

import root.consultantassistant.model.Consultant;
import root.consultantassistant.model.Customer;
import root.consultantassistant.model.CustomerListXML;
import root.consultantassistant.model.Order;
import root.consultantassistant.model.OrderLine;
import root.consultantassistant.validaton.ValidatingFields;
import root.javafx.components.AutoCompleteComboBoxListener;
import root.screenframework.ControlledScreen;
import root.screenframework.ScreenController;

public class OrderScreen extends BorderPane implements Initializable,
		ControlledScreen {
	/**************************************************************************************
	 * Class variable section
	 **************************************************************************************/
	
	private Consultant consultant;
	ScreenController myController;// Used for the screen framework being used
	private PopOver addItemPopOver;// Used for the add item screen
	private Order order;// Used for the customer's order
	private ObservableList<OrderLine> orderData;// Used for the orderline data
	/*
	 * True if customer is a current
	 * customer(this is decided by
	 * whether or not they are chosen
	 * from the combobox), false
	 * otherwise
	 */
	private boolean currentCustomer = false;
	/*
	 * Holds the current customers, and enables
	 * writing to the XML file for data storage
	 */
	private CustomerListXML customerList = new CustomerListXML("fileTest.xml");

	@FXML
	private Label orderLabelInfo;

	@FXML
	private Label customerLabelInfo;

	@FXML
	// fx:id = customerComboBox
	private ComboBox<String> customerComboBox;

	@FXML
	// fx:id = firstNameTextField
	private TextField firstNameTextField;

	@FXML
	// fx:id = lastNameTextField
	private TextField lastNameTextField;

	@FXML
	// fx:id =addressTextField
	private TextField addressTextField;

	@FXML
	// fx:id = cityTextField
	private TextField cityTextField;

	@FXML
	// fx:id = zipCodeTextField
	private TextField zipCodeTextField;

	@FXML
	// fx:id = stateComboBox
	private ComboBox<String> stateComboBox;

	@FXML
	// fx:id = phoneNumberTextField
	private TextField phoneNumberTextField;

	@FXML
	// fx:id = emailTextField
	private TextField emailTextField;

	@FXML
	// fx:id = "subTotalLabel"
	private Label subTotalLabel;

	@FXML
	// fx:id= "taxLabel"
	private Label taxLabel;

	@FXML
	// fx:id = "totalLabel"
	private Label totalLabel;

	@FXML
	// fx:id = "productTableView"
	private TableView<OrderLine> productTableView;

	@FXML
	// fx:id = "productDescriptionTableColumn"
	private TableColumn<OrderLine, String> productDescriptionTableColumn;

	@FXML
	// fx:id = "quantityTableColumn"
	private TableColumn<OrderLine, Integer> quantityTableColumn;

	@FXML
	// fx:id = "unitPriceTableColumn"
	private TableColumn<OrderLine, Number> unitPriceTableColumn;

	@FXML
	// fx:id = "totalPriceTableColumn"
	private TableColumn<OrderLine, Number> totalPriceTableColumn;

	@FXML
	// fx:id = "addItemButton"
	private ToggleButton addItemButton;

	@FXML
	// fx:id = "removeItemButton"
	private Button removeItemButton;

	@FXML
	// fx:id = "clearListButton"
	private Button clearListButton;

	@FXML
	// fx:id = "submitButton"
	private Button submitButton;

	@FXML
	// fx:id = "cancelButton"
	private Button cancelButton;

	@FXML
	// fx:id = mailingListCheckBox
	private CheckBox mailingListCheckBox;

	@FXML
	// fx:id = "closeMenuItem"
	private MenuItem closeMenuItem;

	@FXML
	// fx:id = "clearOrderMenuItem"
	private MenuItem clearOrderMenuItem;

	@FXML
	// fx:id = clearCustomerMenuItem"
	private MenuItem clearCustomerMenuItem;

	@FXML
	// fx:id = "addItemMenuItem"
	private MenuItem addItemMenuItem;

	@FXML
	// fx:id = "deleteItemMenuItem"
	private MenuItem deleteItemMenuItem;

	@FXML
	// fx:id ="resetCustomerInfoButton"
	private Button resetCustomerInfoButton;

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {
		// Ensuring that all of the nodes from the FXML file are included
		assert customerComboBox != null : "fx:id=\"customerComboBox\" was not injected: check your FXML file 'OrderScreen.fxml'.";
		assert firstNameTextField != null : "fx:id=\"firstNameTextField\" was not injected: check your FXML file 'OrderScreen.fxml'.";
		assert lastNameTextField != null : "fx:id=\"lastNameTextField\" was not injected: check your FXML file 'OrderScreen.fxml'.";
		assert addressTextField != null : "fx:id=\"addressTextField\" was not injected: check your FXML file 'OrderScreen.fxml'.";
		assert cityTextField != null : "fx:id=\"cityTextField\" was not injected: check your FXML file 'OrderScreen.fxml'.";
		assert zipCodeTextField != null : "fx:id=\"zipCodeTextField\" was not injected: check your FXML file 'OrderScreen.fxml'.";
		assert stateComboBox != null : "fx:id=\"stateComboBox\" was not injected: check your FXML file 'OrderScreen.fxml'.";
		assert phoneNumberTextField != null : "fx:id=\"phoneNumberTextField\" was not injected: check your FXML file 'OrderScreen.fxml'.";
		assert emailTextField != null : "fx:id=\"emailTextField\" was not injected: check your FXML file 'OrderScreen.fxml'.";
		assert subTotalLabel != null : "fx:id=\"subTotalLabel\" was not injected: check your FXML file 'OrderScreen.fxml'.";
		assert taxLabel != null : "fx:id=\"taxLabel\" was not injected: check your FXML file 'OrderScreen.fxml'.";
		assert totalLabel != null : "fx:id=\"totalLabel\" was not injected: check your FXML file 'OrderScreen.fxml'.";
		assert productTableView != null : "fx:id=\"productTableView\" was not injected: check your FXML file 'OrderScreen.fxml'.";
		assert addItemButton != null : "fx:id=\"addItemButton\" was not injected: check your FXML file 'OrderScreen.fxml'.";
		assert removeItemButton != null : "fx:id=\"removeItemButton\" was not injected: check your FXML file 'OrderScreen.fxml'.";
		assert clearListButton != null : "fx:id=\"clearListButton\" was not injected: check your FXML file 'OrderScreen.fxml'.";
		assert submitButton != null : "fx:id=\"submitButton\" was not injected: check your FXML file 'OrderScreen.fxml'.";
		assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'OrderScreen.fxml'.";
		assert mailingListCheckBox != null : "fx: id=\"mailingListCheckBox\" was not injected check your FXML file 'OrderScreen.fxml'.";
		assert closeMenuItem != null : "fx: id = \"closeMenuItem\" was not injected check your FXML file 'OrderScreen.fxml'.";
		assert clearOrderMenuItem != null : "fx: id = \"clearOrderMenuItem\" was not injected check your FXML file 'OrderScreen.fxml'.";
		assert clearCustomerMenuItem != null : "fx: id = \"clearCustomerMenuItem\" was not injected check your FXML file 'OrderScreen.fxml'.";
		assert addItemMenuItem != null : "fx:id = \"addItemMenuItem\" was not injected check your FXML file 'OrderScreen.fxml'.";
		assert deleteItemMenuItem != null : "fx:id = \"deleteItemMenuItem\" was not injected check your FXML file 'OrderScreen.fxml'.";
		assert resetCustomerInfoButton != null : "fx:id= \"resetCustomerInfoButton\" was not injected check your FXM file 'OrderScreen.fxml'.";
		// Initialize Model
		addItemPopOver = new PopOver();
		addItemPopOver.setId("group");
		order = new Order();
		orderData = FXCollections.observableArrayList();
		// Initialize FXML Objects
		initProductTableView();
		initMenuItems();
		initCustomerComboBox();
		resetCustomerInfoButton.setVisible(false);

		// Adding validation to components where validation is required
		ValidatingFields.requiredTextFieldValidatorListener(
				"text-field-accept", "text-field-decline", firstNameTextField);
		ValidatingFields.requiredTextFieldValidatorListener(
				"text-field-accept", "text-field-decline", lastNameTextField);
		ValidatingFields.requiredTextFieldValidatorListener(
				"text-field-accept", "text-field-decline", addressTextField);
		ValidatingFields.requiredTextFieldValidatorListener(
				"text-field-accept", "text-field-decline", cityTextField);
		ValidatingFields.requiredTextFieldValidatorListener(
				"text-field-accept", "text-field-decline", zipCodeTextField);
		ValidatingFields
				.requiredPhoneNumberValidatorListener("text-field-accept",
						"text-field-decline", phoneNumberTextField);
		ValidatingFields.requiredEmailValidatorListener("text-field-accept",
				"text-field-decline", emailTextField);
		AutoCompleteComboBoxListener<String> stateComboBoxListener = new AutoCompleteComboBoxListener<>(
				stateComboBox);

		// Add Listener to PopOver based on whether the PopOver is showing
		if (addItemPopOver instanceof PopOver) {
			addItemPopOver.showingProperty().addListener(
					(arg0, arg1, arg2) -> {
						if (addItemPopOver.isShowing()) {
							removeItemButton.setDisable(true);
							clearListButton.setDisable(true);
						} else {
							removeItemButton.setDisable(false);
							clearListButton.setDisable(false);
						}
					});
		}

	}

	@Override
	public void setScreenParent(final ScreenController screenPage) {
		myController = screenPage;

	}

	/**
	 * Displays a <code>PopOver</code> window, containing a screen used to add a
	 * new item to the <code>Order</code>. If the window is already showing
	 * 
	 * @param event
	 * @throws Exception
	 */
	@FXML
	public void handleAddItem(final ActionEvent event) throws Exception {
		Platform.runLater(() -> {
			synchronized (event) {
				// Load root node to be displayed on PopOver
				BorderPane addItemScreen = new BorderPane();
				addItemScreen.setId("group");
				addItemScreen.setPrefSize(688, 390);
				GridPane grid = new GridPane();
				BorderPane.setMargin(addItemScreen, new Insets(4, 4, 4, 4));
				grid.setHgap(5);
				grid.setVgap(40);
				grid.setPrefSize(418, 222);

				Label productLabel = new Label("Product Description: ");
				productLabel.setPrefHeight(15);
				productLabel.setPrefWidth(160);
				grid.add(productLabel, 0, 0);

				Label quantityLabel = new Label("Quantity");
				quantityLabel.setPrefSize(64, 15);
				grid.add(quantityLabel, 0, 1);

				Label priceLabel = new Label("Price: ");
				grid.add(priceLabel, 2, 1);

				TextField productDescription = new TextField();
				productDescription.setId("text-field");
				productDescription.setPrefSize(64, 15);
				grid.add(productDescription, 1, 0);
				ValidatingFields.requiredTextFieldValidatorListener(
						"text-field-accept", "text-field-decline",
						productDescription);

				TextField quantity = new TextField();
				quantity.setId("text-field");
				quantity.setPrefSize(35, 15);
				grid.add(quantity, 1, 1);
				ValidatingFields.requiredTextFieldValidatorListener(
						"text-field-accept", "text-field-decline", quantity);

				TextField price = new TextField();
				price.setId("text-field");
				grid.add(price, 3, 1);
				ValidatingFields.requiredTextFieldValidatorListener(
						"text-field-accept", "text-field-decline", price);

				Button addItem = new Button();
				addItem.setText("Add Item");
				addItem.setId("glass-grey");
				grid.add(addItem, 1, 2);

				Button cancelItem = new Button();
				cancelItem.setId("glass-grey");
				cancelItem.setText("Go Back");
				grid.add(cancelItem, 2, 2);

				addItem.setOnKeyPressed((keyEvent) -> {
					synchronized (this) {

						if (keyEvent.getCode() == KeyCode.ENTER) {
							addItem.fireEvent(new ActionEvent());
						}
						// Ensures the focus is traversed properly
						else if (keyEvent.getCode() == KeyCode.TAB) {
							cancelItem.requestFocus();
						}
					}
				});

				addItem.setOnAction((arg0) -> {

					boolean valid = false;
					taxLabel.setText("Sales Tax: ");
					totalLabel.setText("Total: ");
					if (productDescription.getId().equals("text-field-accept")
							&& quantity.getId().equals("text-field-accept")
							&& price.getId().equals("text-field-accept")) {
						valid = true;
						for (int i = 0; i < orderData.size(); i++) {
							if (orderData.get(i).getDescription()
									.equals(productDescription.getText())) {
								valid = false;
								if (Double.valueOf(price.getText()) == orderData
										.get(i).getUnitPrice()) {
									Action response = Dialogs
											.create()
											// Causing the dialog pop over to
											// misalign the header of the
											// addItem pop over
											.title("Product Already Exists")
											.message(
													"The product: '"
															+ orderData
																	.get(i)
																	.getDescription()
															+ "' already exists in this order. "
															+ "We can simply add the quantity you specified to this product. Is this what you want?")

											.owner(addItemPopOver)
											.masthead(null).showConfirm();

									valid = false;
									synchronized (response) {
										if (response == Dialog.Actions.YES) {
											try {
												OrderLine tempOrder = new OrderLine(
														orderData.get(i)
																.getQuantity()
																+ Integer
																		.valueOf(quantity
																				.getText()),
														orderData
																.get(i)
																.getDescription(),
														orderData.get(i)
																.getUnitPrice());
												orderData.remove(i);
												orderData.add(i, tempOrder);
												updateTotals();
											} catch (NumberFormatException e) {
												Dialogs.create()
														.title("Unexpected Value")
														.message(
																"A numeric value that was expected was not supplied.")
														.owner(addItemPopOver)
														.masthead(null)
														.showError();
											}
										}
									}
								} else {
									Dialogs.create()
											.title("Error: Product Already Exists")
											.message(
													"This Product already exists in the order, we cannot tell what your desired action is.")
											.masthead(
													"This can be resolved by resolving the product name conflict or by ensuring that the name and unit price"
															+ " are the same, and then we can decide where to go from there.")
											.owner(addItemPopOver).showError();

									valid = false;
								}
							}
						}
						if (valid) {
							try {
								orderData.add(new OrderLine(Integer
										.valueOf(quantity.getText()),
										productDescription.getText(), Double
												.valueOf(price.getText())));
								clearOrderMenuItem.setDisable(false);
								updateTotals();
							} catch (NumberFormatException e) {
								Dialogs.create()
										.title("Unexpected Value")
										.message(
												"A numeric value that was expected was not supplied.")
										.owner(addItemPopOver).masthead(null)
										.showError();
							}
						}
					} else {
						// Creates a new dialog box, in the event that
						// all of
						// the required information is not present for
						// the item
						// being added to the order.
						Action response = Dialogs.create()
								.title("More information required")
								.masthead("Error Occurred")
								.message("Missing required data.")
								.owner(addItemPopOver).showError();

						// When the user clicks OK, the focus will be
						// given to
						// the node that doesn't have the required data
						// input.
						try {
							synchronized (this) {

								if (response == Dialog.Actions.OK) {
									GridPane.setConstraints(cancelItem, 2, 2,
											1, 1, HPos.CENTER, VPos.CENTER);
									if (!productDescription.getId().equals(
											"text-field-accept")) {
										productDescription.requestFocus();
										arg0.consume();
									} else if (!quantity.getId().equals(
											"text-field-accept")) {
										quantity.requestFocus();
										arg0.consume();
									} else if (!price.getId().equals(
											"text-field-accept")) {
										price.requestFocus();
										arg0.consume();
									}
								}
							}
						} catch (IllegalArgumentException e) {
						}

					}

				});
				cancelItem.setOnKeyPressed((newEvent) -> {

					if (newEvent.getCode() == KeyCode.ENTER) {
						cancelItem.fireEvent(new ActionEvent());
					}
					// Ensures the focus is traversed properly
						else if (newEvent.getCode() == KeyCode.TAB) {
							productDescription.requestFocus();
						}

					});
				cancelItem.setOnAction((arg0) -> {

					addItemPopOver.hide();
					cancelItem.getParent().getParent().setDisable(false);
				});

				// Set Row and column constraints
				ColumnConstraints column = new ColumnConstraints();
				column.setFillWidth(true);
				column.setHgrow(Priority.ALWAYS);
				column.setMinWidth(10);
				column.setMaxWidth(230);
				column.setPrefWidth(151);
				grid.getColumnConstraints().add(column);
				column.setMaxWidth(419);
				column.setPrefWidth(111);
				grid.getColumnConstraints().add(column);
				column.setMaxWidth(419);
				column.setPrefWidth(240);
				grid.getColumnConstraints().add(column);
				grid.getColumnConstraints().add(column);
				RowConstraints row = new RowConstraints();
				row.setFillHeight(true);
				row.setMaxHeight(30);
				row.setMinHeight(10);
				row.setVgrow(Priority.ALWAYS);
				grid.getRowConstraints().add(row);
				grid.getRowConstraints().add(row);
				grid.getRowConstraints().add(row);
				GridPane.setConstraints(productDescription, 1, 0, 3, 1);
				VBox headingBar = new VBox();
				headingBar.setId("headingBar");
				headingBar.setFillWidth(true);
				Label heading = new Label("Add Item");
				heading.setId("heading");
				headingBar.getChildren().add(heading);
				headingBar.setAlignment(Pos.CENTER);
				BorderPane.setAlignment(headingBar, Pos.CENTER);
				addItemScreen.setTop(headingBar);
				BorderPane.setAlignment(grid, Pos.CENTER);
				addItemScreen.setCenter(grid);
				addItemScreen.getStylesheets().add("css/application.css");

				// Find coordinates for component
				final Scene scene = addItemButton.getScene();
				final Point2D windowCoord = new Point2D(scene.getWindow()
						.getX(), scene.getWindow().getY());
				final Point2D sceneCoord = new Point2D(scene.getX(), scene
						.getY());
				final Point2D nodeCoord = addItemButton.localToScene(0.0, 0.0);
				final double clickX = Math.round(windowCoord.getX()
						+ sceneCoord.getY() + nodeCoord.getX());
				final double clickY = Math.round(windowCoord.getY()
						+ sceneCoord.getY() + nodeCoord.getY());
				addItemPopOver.setContentNode(addItemScreen);
				addItemPopOver.setArrowLocation(ArrowLocation.BOTTOM_LEFT);
				addItemPopOver.setCornerRadius(4);
				addItemPopOver.setDetachedTitle("Add New Item");
				addItemPopOver.show(addItemButton.getParent().getParent(),
						clickX, clickY);

				/*
				 * Adds a listener to the item PopOver, and is used to disable
				 * the parent which restricts focus to the PopOver while it is
				 * being shown.
				 */
				addItemPopOver.showingProperty()
						.addListener(
								(arg0, arg1, arg2) ->

								{
									synchronized (this) {
										addItemButton
												.getParent()
												.getParent()
												.setDisable(
														addItemPopOver
																.isShowing());
									}

								});
				addItemButton.getParent().getParent().setDisable(true);
			}
		});

	}

	@FXML
	public void handleClearList(final ActionEvent event) {
		Platform.runLater(() -> {
			Action response;
			synchronized (event) {
				response = Dialogs.create().title("Remove all items?")
						.masthead("Remove all items from the order?")
						.showConfirm();
			}

			if (response == Dialog.Actions.YES) {

				orderData.clear();
				clearOrderMenuItem.setDisable(true);
				deleteItemMenuItem.setDisable(true);
				removeItemButton.setDisable(true);
				clearListButton.setDisable(true);
				updateTotals();
			}

		});
	}

	@FXML
	public void handleRemoveItem(final ActionEvent event) {

		/*
		 * https://javafx-jira.kenai.com/browse/RT-24367?page=com.atlassian.jira.
		 * plugin.system.issuetabpanels:comment-tabpanel Date:2/28/14
		 * //ObservableList<OrderLine> selectedItems =
		 * productTableView.getSelectionModel().getSelectedItems(); There is a
		 * bug in the JDK which is forcing the the removeAll method to iterate
		 * in a strange way. Workaround is being used, if the workaround is not
		 * used then there will be elements removed from the list unexpectedly.
		 * For example: Index 2 is being removed. After the selected items(Index
		 * 2) are returned as expected, using the remove all method there will
		 * be a strange behavior as all entries below and including Index 2 will
		 * be removed.
		 */
		ObservableList<OrderLine> selectedItems = FXCollections
				.observableArrayList(productTableView.getSelectionModel()
						.getSelectedItems());
		orderData.removeAll(selectedItems);
		productTableView.getSelectionModel().clearSelection();
		if (orderData.isEmpty()) {
			clearOrderMenuItem.setDisable(true);
			deleteItemMenuItem.setDisable(true);
			clearListButton.setDisable(true);
			removeItemButton.setDisable(true);
		}
		updateTotals();
	}

	/**
	 * Updates the sub-total, taxes, and the total. This calculation is based on
	 * the items in the order currently displayed on-screen in the Table view.
	 * 
	 * @param taxRate
	 *            Rate of taxes to be used for the calculation
	 */
	public void updateTotals() {
		double subTotal = 0;
		double salesTax = 0;
		double total = 0;
		for (OrderLine dataLine : orderData) {
			subTotal += dataLine.getTotalPrice();
		}
		total = salesTax + subTotal;
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		if (subTotal == 0) {
			subTotalLabel.setText("SubTotal: ");
			taxLabel.setText("Sales Tax: ");
			totalLabel.setText("Total: ");
			orderLabelInfo.setId("label-decline");
		} else {
			subTotalLabel.setText("SubTotal: " + formatter.format(subTotal));
			taxLabel.setText("Sales Tax: ");
			totalLabel.setText("Total: " + formatter.format(total));
			orderLabelInfo.setId("label-accept");
		}
	}

	// Utility Method used to validate the input of the customer
	public boolean validateCustomerInput() {
		if (firstNameTextField.getId().equals("text-field-accept")
				&& lastNameTextField.getId().equals("text-field-accept")
				&& addressTextField.getId().equals("text-field-accept")
				&& cityTextField.getId().equals("text-field-accept")
				&& zipCodeTextField.getId().equals("text-field-accept")
				// && !(stateComboBox.getSelectionModel().getSelectedItem()
				// .equals(""))
				&& phoneNumberTextField.getId().equals("text-field-accept")
				&& emailTextField.getId().equals("text-field-accept")) {
			customerLabelInfo.setId("label-accept");
			return true;
		}
		return false;
	}

	@FXML
	public void handleSubmit(final ActionEvent event) {
		Platform.runLater(() -> {
			synchronized (this) {

				// Validate customer input, if validated populate customer
				// object, consultant object, and order object with data.
				if (validateCustomerInput()
						&& orderLabelInfo.getId().equals("label-accept")) {
					Customer customer = new Customer();
					customer.setFirstName(firstNameTextField.getText());
					customer.setLastName(lastNameTextField.getText());
					customer.setAddress(addressTextField.getText());
					customer.setEmail(emailTextField.getText());
					customer.setPhoneNumber(phoneNumberTextField.getText());
					customer.setZipCode(Integer.valueOf(zipCodeTextField
							.getText()));
					customer.setState(stateComboBox.getValue());
					customer.setCity(cityTextField.getText());
					customer.setMailingList(mailingListCheckBox.isSelected());
					/*Consultant consultant = new Consultant();
					consultant.setAddress("407 N. Gould St.");
					consultant.setCity("Owosso");
					consultant.setEmail("rootbrad86@gmail.com");
					consultant.setFirstName("Teri");
					consultant.setLastName("root");
					consultant.setUserId("troot");
					consultant.setPassword("password99");
					consultant.setPhoneNumber("(999)999-9999");
					consultant.setState("Michigan");
					consultant.setZip(48867);*/
					NumberFormat formatter = NumberFormat.getCurrencyInstance();
					if (currentCustomer) {
						customerComboBox.getSelectionModel().clearAndSelect(
								customerComboBox.getSelectionModel()
										.getSelectedIndex());
						if (customer.equals(customerList.getCustomerList().get(
								customerComboBox.getSelectionModel()
										.getSelectedIndex()))) {
							order.setConsultant(consultant);
							order.setCustomer(customer);
							order.setOrderLine(orderData);
							taxLabel.setText("Sales Tax: "
									+ formatter.format(order.getSalesTax()));
							totalLabel.setText("Total: "
									+ formatter.format(order.getTotal()));
						} else {
							Action response = Dialogs
									.create()
									.title("Customer's information has changed")
									.message(
											"The customer's information has changed. By choosing Yes, the customer's"
													+ " information will change for future transactions. Is this what you would like to do?")
									.masthead(null).showConfirm();
							if (response == Dialog.Actions.YES) {
								customer.setId(customerList
										.getCustomerList()
										.get(customerComboBox
												.getSelectionModel()
												.getSelectedIndex()).getId());
								customerList.getCustomerList().set(
										customerComboBox.getSelectionModel()
												.getSelectedIndex(), customer);
								customerComboBox
										.getItems()
										.set(customerComboBox
												.getSelectionModel()
												.getSelectedIndex(),
												customerList
														.getCustomerList()
														.get(customerComboBox
																.getSelectionModel()
																.getSelectedIndex())
														.getFirstName()
														+ " "
														+ customerList
																.getCustomerList()
																.get(customerComboBox
																		.getSelectionModel()
																		.getSelectedIndex())
																.getLastName());
								order.setConsultant(consultant);
								order.setCustomer(customer);
								order.setOrderLine(orderData);
								taxLabel.setText("Sales Tax: "
										+ formatter.format(order.getSalesTax()));
								totalLabel.setText("Total: "
										+ formatter.format(order.getTotal()));
								customerList.writeToFile();
							} else if (response == Dialog.Actions.NO) {
								Dialogs.create()
										.title("Resetting customer information")
										.message(
												"The customer information will be reset to"
														+ " his/her saved information.")
										.masthead(null).showWarning();
								firstNameTextField.setText(customerList
										.getCustomerList()
										.get(customerComboBox
												.getSelectionModel()
												.getSelectedIndex())
										.getFirstName());
								lastNameTextField.setText(customerList
										.getCustomerList()
										.get(customerComboBox
												.getSelectionModel()
												.getSelectedIndex())
										.getLastName());
								addressTextField.setText(customerList
										.getCustomerList()
										.get(customerComboBox
												.getSelectionModel()
												.getSelectedIndex())
										.getAddress());
								phoneNumberTextField.setText(customerList
										.getCustomerList()
										.get(customerComboBox
												.getSelectionModel()
												.getSelectedIndex())
										.getPhoneNumber());
								zipCodeTextField
										.setText(((Integer) customerList
												.getCustomerList()
												.get(customerComboBox
														.getSelectionModel()
														.getSelectedIndex())
												.getZipCode()).toString());
								emailTextField
										.setText(customerList
												.getCustomerList()
												.get(customerComboBox
														.getSelectionModel()
														.getSelectedIndex())
												.getEmail());
								cityTextField.setText(customerList
										.getCustomerList()
										.get(customerComboBox
												.getSelectionModel()
												.getSelectedIndex()).getCity());
								stateComboBox
										.setValue(customerList
												.getCustomerList()
												.get(customerComboBox
														.getSelectionModel()
														.getSelectedIndex())
												.getState());
								mailingListCheckBox.setSelected(customerList
										.getCustomerList()
										.get(customerComboBox
												.getSelectionModel()
												.getSelectedIndex())
										.isMailingList());
							}
						}
					} else {
						customerList.addCustomer(customer);
						order.setConsultant(consultant);
						order.setCustomer(customer);
						order.setOrderLine(orderData);
						taxLabel.setText("Sales Tax: "
								+ formatter.format(order.getSalesTax()));
						totalLabel.setText("Total: "
								+ formatter.format(order.getTotal()));
						customerList.writeToFile();
					}
				} else {
					/*
					 * If the customer input is not validated a warning dialog
					 * is displayed, and will not allow the user to proceed with
					 * checkout.
					 */
					Dialogs.create()
							.title("Missing required data.")
							.message(
									"Enter required data to continue with Check-out.")
							.masthead(null).showWarning();

				}
			}

		});

	}

	/**
	 * Utility method used to initialize the menu item listeners, and shortcuts.
	 */
	private void initMenuItems() {
		// Set action event listener, and keyboard shortcut(CTRL + SHIFT + L)
		// for the close menu item
		closeMenuItem.setOnAction((arg0) -> {

			Platform.runLater(() -> {
				Action response = Dialogs.create()
						.masthead("Close the Application?")
						.message("Are you sure you want close the program?")
						.title("Close?").masthead(null).showConfirm();

				if (response == Dialog.Actions.YES) {
					Platform.exit();
				}
			});
		});

		closeMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.L,
				KeyCombination.SHORTCUT_DOWN, KeyCombination.SHIFT_DOWN));

		// Set action event listener, and keyboard shortcut(CTRL + SHIFT + O)
		// for the clear order menu item
		clearOrderMenuItem.setOnAction((event) -> {

			Platform.runLater(() -> {
				synchronized (event) {

					clearListButton.fireEvent(new ActionEvent());
				}
			});

		});
		clearOrderMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.O,
				KeyCombination.SHIFT_DOWN, KeyCombination.CONTROL_DOWN));

		// Set action event listener, and keyboard shortcut(CTRL + SHIFT + C)
		// for
		// the clear customer menu item
		clearCustomerMenuItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(final ActionEvent arg0) {
				Platform.runLater(() -> {
					Action response = Dialogs
							.create()
							.title("Clear Customer Information?")
							.message(
									"Are you sure want to clear the customer information?")
							.masthead(null).showConfirm();

					synchronized (this) {
						if (response == Dialog.Actions.YES) {
							customerComboBox.getSelectionModel()
									.clearSelection();
							firstNameTextField.clear();
							firstNameTextField.setId("text-field");
							lastNameTextField.clear();
							lastNameTextField.setId("text-field");
							addressTextField.clear();
							addressTextField.setId("text-field");
							cityTextField.clear();
							cityTextField.setId("text-field");
							zipCodeTextField.clear();
							zipCodeTextField.setId("text-field");
							phoneNumberTextField.clear();
							phoneNumberTextField.setId("text-field");
							emailTextField.clear();
							emailTextField.setId("text-field");
							stateComboBox.getSelectionModel().clearSelection();

						}
					}
				});

			}

		});
		clearCustomerMenuItem
				.setAccelerator(new KeyCodeCombination(KeyCode.C,
						KeyCodeCombination.SHIFT_DOWN,
						KeyCodeCombination.SHORTCUT_DOWN));
		// Adds action listener
		addItemMenuItem.setOnAction((arg0) -> {

			Platform.runLater(() -> {
				synchronized (this) {

					addItemButton.fireEvent(new ActionEvent());
				}
			});

		});
		addItemMenuItem
				.setAccelerator(new KeyCodeCombination(KeyCode.A,
						KeyCodeCombination.SHORTCUT_DOWN,
						KeyCodeCombination.SHIFT_DOWN));

		deleteItemMenuItem.setOnAction((event) -> {

			Platform.runLater(() -> {
				synchronized (this) {
					removeItemButton.fireEvent(new ActionEvent());
				}
			});

		});
		deleteItemMenuItem
				.setAccelerator(new KeyCodeCombination(KeyCode.D,
						KeyCodeCombination.SHORTCUT_DOWN,
						KeyCodeCombination.SHIFT_DOWN));
	}

	public void initProductTableView() {
		productTableView.setPlaceholder(new Label(
				"There are no items in this order."));
		productDescriptionTableColumn
				.setCellValueFactory(new PropertyValueFactory<OrderLine, String>(
						"description"));
		quantityTableColumn
				.setCellValueFactory(new PropertyValueFactory<OrderLine, Integer>(
						"quantity"));
		unitPriceTableColumn
				.setCellValueFactory(new PropertyValueFactory<OrderLine, Number>(
						"unitPrice"));
		unitPriceTableColumn
				.setCellFactory(param -> new TableCell<OrderLine, Number>() {

					@Override
					protected void updateItem(final Number item, final boolean empty) {
						super.updateItem(item, empty);
						setText(item == null ? "" : NumberFormat
								.getCurrencyInstance().format(item));
					}
				});
		totalPriceTableColumn
				.setCellValueFactory(new PropertyValueFactory<OrderLine, Number>(
						"totalPrice"));
		totalPriceTableColumn
				.setCellFactory(param -> new TableCell<OrderLine, Number>() {

					@Override
					protected void updateItem(final Number item, final boolean empty) {
						super.updateItem(item, empty);
						setText(item == null ? "" : NumberFormat
								.getCurrencyInstance().format(item));
					}
				});
		productTableView.setItems(orderData);

		productTableView.getSelectionModel().selectedItemProperty()
				.addListener((arg0, arg1, arg2) -> {

					if (arg2 == null) {
						deleteItemMenuItem.setDisable(true);
						removeItemButton.setDisable(true);
					} else {
						deleteItemMenuItem.setDisable(false);
						removeItemButton.setDisable(false);
					}

				});
	}

	public void initCustomerComboBox() {
		ArrayList<String> customerName = new ArrayList<>();

		for (int i = 0; i < customerList.getCustomerList().size(); i++) {
			customerName
					.add(customerList.getCustomerList().get(i).getFirstName()
							+ " "
							+ customerList.getCustomerList().get(i)
									.getLastName());
		}

		customerComboBox.getItems().addAll(customerName);
		customerComboBox.valueProperty().addListener(
				(arg0, arg1, arg2) -> {
					if (arg0 != null) {
						firstNameTextField.setText(customerList
								.getCustomerList()
								.get(customerComboBox.getSelectionModel()
										.getSelectedIndex()).getFirstName());
						lastNameTextField.setText(customerList
								.getCustomerList()
								.get(customerComboBox.getSelectionModel()
										.getSelectedIndex()).getLastName());
						addressTextField.setText(customerList
								.getCustomerList()
								.get(customerComboBox.getSelectionModel()
										.getSelectedIndex()).getAddress());
						phoneNumberTextField.setText(customerList
								.getCustomerList()
								.get(customerComboBox.getSelectionModel()
										.getSelectedIndex()).getPhoneNumber());
						zipCodeTextField.setText(((Integer) customerList
								.getCustomerList()
								.get(customerComboBox.getSelectionModel()
										.getSelectedIndex()).getZipCode())
								.toString());
						emailTextField.setText(customerList
								.getCustomerList()
								.get(customerComboBox.getSelectionModel()
										.getSelectedIndex()).getEmail());
						cityTextField.setText(customerList
								.getCustomerList()
								.get(customerComboBox.getSelectionModel()
										.getSelectedIndex()).getCity());
						stateComboBox.setValue(customerList
								.getCustomerList()
								.get(customerComboBox.getSelectionModel()
										.getSelectedIndex()).getState());
						mailingListCheckBox.setSelected(customerList
								.getCustomerList()
								.get(customerComboBox.getSelectionModel()
										.getSelectedIndex()).isMailingList());
						customerComboBox.setDisable(true);
						if (validateCustomerInput()) {
							customerLabelInfo.setId("label-accept");
							resetCustomerInfoButton.setVisible(true);
						}
						currentCustomer = true;
					}
				});
	}

	@FXML
	/**
	 * This method resets the customer info text fields/combo boxes so the value is an empty string, and this resets the
	 * currentCustomer to false.
	 */
	public void resetCustomerInfo() {
		customerComboBox.setValue(null);
		customerComboBox.setDisable(false);
		firstNameTextField.clear();
		firstNameTextField.setId("text-field");
		lastNameTextField.clear();
		lastNameTextField.setId("text-field");
		addressTextField.clear();
		addressTextField.setId("text-field");
		phoneNumberTextField.clear();
		phoneNumberTextField.setId("text-field");
		zipCodeTextField.clear();
		zipCodeTextField.setId("text-field");
		emailTextField.clear();
		emailTextField.setId("text-field");
		cityTextField.clear();
		cityTextField.setId("text-field");
		stateComboBox.getSelectionModel().clearSelection();
		mailingListCheckBox.setSelected(false);
		customerLabelInfo.setId("label-decline");
		resetCustomerInfoButton.setVisible(false);
		currentCustomer = false;
	}

}
