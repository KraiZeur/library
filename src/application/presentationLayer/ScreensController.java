package application.presentationLayer;

import java.util.HashMap;

import application.presentationLayer.screens.Screen;
import javafx.scene.layout.StackPane;

/**
 * The class to controll all the screen's application
 * @author Thomas
 *
 */
public class ScreensController extends StackPane {

	/**
	 * A hashmap containing the screen name as a key and the screen as a value
	 */
	HashMap<String, Screen> screenMap = new HashMap<>();

	/**
	 * The default constructor
	 */
	public ScreensController() {
		super();
	}

	/**
	 * Add a screen to the controller
	 * @param name The screen name (must be unique)
	 * @param screen The screen
	 */
	public void addScreen(String name, Screen screen) {
		screenMap.put(name, screen);
	}

	/**
	 * Set a screen according to his name the screen must be added to the controller
	 * @param name the screen's name
	 */
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