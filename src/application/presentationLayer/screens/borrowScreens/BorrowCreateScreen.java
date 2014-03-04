package application.presentationLayer.screens.borrowScreens;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import application.businessLayer.controller.BorrowController;
import application.businessLayer.utils.DialogUtil;
import application.presentationLayer.screens.Screen;

/**
 * 
 * @author Thomas
 *
 */
public class BorrowCreateScreen implements Screen{

	@SuppressWarnings("unused")
	private Stage primaryStage;
	private GridPane gridPane;
	private TextField userIdTf;
	private TextField bookIdTf;
	
	public BorrowCreateScreen(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@Override
	public Pane getPane() {

		gridPane = new GridPane();
		FlowPane flowplane = new FlowPane();


		/* Grid pane and flow pane parameters */
		flowplane.getChildren().add(gridPane);
		gridPane.setStyle("-fx-font: bold 18pt \"Times New Roman\";");
		gridPane.setHgap(5); gridPane.setVgap(5);
		gridPane.getStyleClass().add("grid-pane-style");
		gridPane.setStyle("-fx-background-color: linear-gradient(#E8EAEC 0%, #CCD1D7 50%, #E8EAEC 100%); -fx-padding: 35px;-fx-background-radius: 35px;");
		flowplane.getStyleClass().add("background-style");
		gridPane.setAlignment(Pos.CENTER);
		flowplane.setAlignment(Pos.CENTER);

		/* Creation of the different label and textfield inside the formulaire */
		Label bookId = new Label("Book Id :");
		GridPane.setHalignment(bookId, HPos.RIGHT);

		Label userId = new Label("User Id :");
		GridPane.setHalignment(userId, HPos.RIGHT);

		userIdTf = new TextField();
		bookIdTf = new TextField();
		/* End creation of formulaire components */

		/* Adding the components to the grid pane */
		gridPane.add(bookId, 0, 0);
		gridPane.add(bookIdTf, 1, 0);
		gridPane.add(userId, 0, 1);
		gridPane.add(userIdTf, 1, 1);
		/* End adding components to the grid pane */
		
		Button submitButton = new Button("Submit");
		
		gridPane.add(submitButton, 1, 2);
		
		submitButton.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(final ActionEvent e) {
						BorrowController bc = new BorrowController();
						String message = bc.createBorrow(userIdTf.getText(), bookIdTf.getText());
						
						DialogUtil.basicDialog(message);
					}
				});

		return flowplane;
	}
}
