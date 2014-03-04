package application.presentationLayer.screens;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import dataAccessLayer.daoLayer.DAOAdministrator;

/**
 * The login screen of the application
 * @author Thomas
 *
 */
public class LoginScreen extends BorderPane{

	private StackPane stckPane;
	Button submitBtn = new Button("Login");
	private GridPane gridPane = new GridPane();
	private TextField userName = new TextField();
	private PasswordField  password = new PasswordField ();
	
	public LoginScreen() {
		super();
		gridPane.add(userName, 0, 0);
		userName.setPromptText("Username");
		gridPane.setVgap(15);
		gridPane.add(password, 0, 1);
		password.setPromptText("password");
		userName.setPrefHeight(50);
		password.setPrefHeight(50);
		gridPane.add(submitBtn, 0, 2);
		submitBtn.setPrefSize(450, 100);
		
		gridPane.setAlignment(Pos.CENTER);
		
		this.setCenter(gridPane);
		this.setStyle("-fx-background-color: #090a0c,linear-gradient(#6B7E8F 0%, #495561 20%, #4A5766 100%),linear-gradient(#53626F, #3E4855),radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));");
		
		// When the user try to login
		submitBtn.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(final ActionEvent e) {

						DAOAdministrator daoAdmin = new DAOAdministrator();
						if(daoAdmin.findByLogin(userName.getText(), password.getText()) != null) {
							stckPane.getChildren().remove(1);
						}
						
					}
				});
	}

	/**
	 * 
	 * @return
	 */
	public StackPane getStckPane() {
		return stckPane;
	}

	/**
	 * 
	 * @param stckPane
	 */
	public void setStckPane(StackPane stckPane) {
		this.stckPane = stckPane;
	}
}
