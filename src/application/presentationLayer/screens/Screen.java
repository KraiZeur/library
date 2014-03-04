package application.presentationLayer.screens;

import javafx.scene.layout.Pane;

/**
 * The screen interface all the application's screen must implement this interface
 * @author Thomas
 *
 */
public interface Screen {
	/**
	 * Return the current screen pane
	 * @return the screen pane
	 */
	Pane getPane();
}
