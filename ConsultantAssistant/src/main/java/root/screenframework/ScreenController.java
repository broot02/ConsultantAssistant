package root.screenframework;

import java.util.HashMap;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import org.springframework.stereotype.Component;
@Component
public class ScreenController extends StackPane {

	
	private HashMap<String, Node> screens = new HashMap<>();

	public ScreenController() {
		super();
	}

	public void addScreen(final String name, final Node screen) {
		screens.put(name, screen);
	}

	public Node getScreen(final String name) {
		return screens.get(name);
	}

	public boolean loadScreen(final String name, final String resource) {
		try {
			
			FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource(
					resource));
			Parent loadScreen = (Parent) fxmlloader.load();
			ControlledScreen myScreenController = ((ControlledScreen)fxmlloader
					.getController());
			myScreenController.setScreenParent(this);
			addScreen(name, loadScreen);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean setScreen(final String name) {
		if (screens.get(name) != null) { // Screen loaded
			final DoubleProperty opacity = opacityProperty();

			if (!getChildren().isEmpty()) {
				Timeline fade = new Timeline(new KeyFrame(Duration.ZERO,
						new KeyValue(opacity, 0.0)), new KeyFrame(new Duration(
						400), event -> {
							getChildren().remove(0);
							getChildren().add(0, screens.get(name));
							Timeline fadeIn = new Timeline(new KeyFrame(
									Duration.ZERO, new KeyValue(opacity, 0.0)),
									new KeyFrame(new Duration(400), new KeyValue(
											opacity, 1.0)));
							fadeIn.play();
						}, new KeyValue(opacity, 0.0)));
				fade.play();
			} else {
				setOpacity(0.0);
				getChildren().add(screens.get(name));
				Timeline fadeIn = new Timeline(new KeyFrame(Duration.ZERO,
						new KeyValue(opacity, 0.0)), new KeyFrame(new Duration(
						800), new KeyValue(opacity, 1.0)));
				fadeIn.play();
			}
			return true;
		} else {
			System.out.println("screen hasn't been loaded");
			return false;
		}
	}
	
	public boolean unloadScreen(final String name){
		if(screens.remove(name) == null){
			System.out.println("Screen didn't exist");
			return false;
			
		}else{
			return true;
		}
	}
	
}
