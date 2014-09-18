package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import root.screenframework.ScreenController;

public class Main extends Application {
	public static void main(final String args[]) {
		launch(args);
	}

	@Override
	public final void start(final Stage stage) throws Exception {
		ScreenController mainController = new ScreenController();
		mainController.loadScreen("LoginScreen", "/fxml/LoginScreen.fxml");
		mainController.loadScreen("RegistrationScreen",
				"/fxml/RegistrationScreen.fxml");
		mainController.loadScreen("OrderScreen", "/fxml/OrderScreen.fxml");
		mainController.setScreen("OrderScreen");

		StackPane root = new StackPane();
		root.setMinSize(0, 0);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(mainController);
		Scene scene = new Scene(root);
		stage.setResizable(true);
		stage.setScene(scene);
		stage.show();
	}

}
