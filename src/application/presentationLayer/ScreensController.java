package application.presentationLayer;

import java.util.HashMap;

import application.presentationLayer.screens.Screen;
import javafx.scene.layout.StackPane;

public class ScreensController extends StackPane {

	HashMap<String, Screen> screenMap = new HashMap<>();

	public ScreensController() {
		super();
	}

	public void addScreen(String name, Screen screen) {
		screenMap.put(name, screen);
	}

	public void setScreen(String name) {
		if (!getChildren().isEmpty()) { 
			this.getChildren().remove(0); 
			this.getChildren().add(screenMap.get(name).getPane());
		} else {
			System.out.println("here");
			this.getChildren().add(screenMap.get(name).getPane());
		}
	}
}