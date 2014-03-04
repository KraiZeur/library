package application.businessLayer.utils;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Class about dialog
 * @author Thomas
 *
 */
public class DialogUtil {

	/**
	 * The dialog created by this class
	 */
	private static Stage dialog;
	
	/**
	 * Display a new dialog with the String parameters inside
	 * @param string The message to display
	 */
	public static void basicDialog(String string) {
		dialog = new Stage();
		dialog.initStyle(StageStyle.UTILITY);
		GridPane group = new GridPane();
		group.add(new Label(string +"       "), 0, 0);
		Scene scene = new Scene(group);
		dialog.setScene(scene);
		Button button = new Button("Close");
		group.add(new Label(), 0, 1);
		group.add(button, 0, 2);
		dialog.sizeToScene();
		dialog.setHeight(100);
		dialog.show();
		
		// Close the dialog on click
		button.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(final ActionEvent e) {
						dialog.close();
					}
				});
	}
}
