package application.presentationLayer.screens.userScreens;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import application.businessLayer.controller.CustomerController;
import application.businessLayer.utils.DialogUtil;
import application.businessLayer.utils.ImageUtil;
import application.presentationLayer.screens.Screen;

public class UserCreationScreen implements Screen {
	
	@SuppressWarnings("unused")
	private Stage primaryStage;
	private GridPane gridPane;
	private File file;
	private File dest;
	
	
	public UserCreationScreen(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	@Override
	public Pane getPane() {
		gridPane = new GridPane();
		FlowPane flowpane = new FlowPane();
		flowpane.getChildren().add(gridPane);
		
		gridPane.setStyle("-fx-font: bold 18pt \"Times New Roman\";");
		gridPane.setHgap(5); gridPane.setVgap(5);
		gridPane.getStyleClass().add("grid-pane-style");
		gridPane.setStyle("-fx-background-color: linear-gradient(#E8EAEC 0%, #CCD1D7 50%, #E8EAEC 100%); -fx-padding: 35px;-fx-background-radius: 35px;");
		flowpane.getStyleClass().add("background-style");
		gridPane.setAlignment(Pos.CENTER);
		flowpane.setAlignment(Pos.CENTER);
		
		Label firstNameLabel = new Label("FirstName");
		Label lastNameLabel = new Label("LastName");
		Label numberLabel = new Label("Street Number");
		Label streetLabel = new Label("Street");
		Label cityLabel = new Label("City");
		Label zipCodeLabel = new Label("ZipCode");
		
		final TextField firstNameTf = new TextField();
		final TextField lastNameTf = new TextField();
		final TextField numberTf = new TextField();
		final TextField streetTf = new TextField();
		final TextField cityTf = new TextField();
		final TextField zipCodeTf = new TextField();
		firstNameTf.setPromptText("Customer's firstname");
		lastNameTf.setPromptText("Customer's lastname");
		numberTf.setPromptText("Customer's home number");
		streetTf.setPromptText("Customer's street");
		cityTf.setPromptText("Customer's city");
		zipCodeTf.setPromptText("Customer's zipcode");
		
		gridPane.add(firstNameLabel, 0, 0);
		gridPane.add(lastNameLabel, 0, 1);
		gridPane.add(numberLabel, 0, 2);
		gridPane.add(streetLabel, 0, 3);
		gridPane.add(cityLabel, 0, 4);
		gridPane.add(zipCodeLabel, 0, 5);
		
		gridPane.add(firstNameTf, 1, 0);
		gridPane.add(lastNameTf, 1, 1);
		gridPane.add(numberTf, 1, 2);
		gridPane.add(streetTf, 1, 3);
		gridPane.add(cityTf, 1, 4);
		gridPane.add(zipCodeTf, 1, 5);
		
		Button submitButton = new Button("Submit");
		
		gridPane.add(submitButton, 1, 6);
		
		/* Action when the user submit the formulaire */
		submitButton.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(final ActionEvent e) {

						CustomerController customerCtrl = new CustomerController();

						if( dest != null) {
							try {
								ImageUtil.copyFile(file, dest);
							} catch (IOException e1) {
								System.out.println(e1.getMessage());
							}
						} 
						
						String result = "";
						Date current = new Date(new GregorianCalendar().getTime().getTime()); 
						result = customerCtrl.createCustomerWithParameters(firstNameTf.getText(), lastNameTf.getText(), numberTf.getText(), streetTf.getText(), cityTf.getText(),zipCodeTf.getText(), current);

						if(result.equals("Creation succeed")) {
							if(file != null) {
								gridPane.getChildren().remove(13);
							}
							file = null;
							dest = null;
						}

						DialogUtil.basicDialog(result);

					}
				});
		/* End button's action */
		return flowpane;
	}

	}

