package application.presentationLayer.screens.userScreens;

import application.presentationLayer.ScreensController;
import application.presentationLayer.screens.Screen;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 
 * @author Thomas
 *
 */
public class UserScreen implements Screen {

	private ScreensController screenController;
	private UserCreationScreen userCreationScreen;
	private UserFindScreen userFindScreen;
	
	public UserScreen(ScreensController screenController, Stage primaryStage) {
		this.screenController = screenController;
		userCreationScreen = new UserCreationScreen(primaryStage);
		userFindScreen = new UserFindScreen(primaryStage);
		screenController.addScreen("USER_CREATION_SCREEN", userCreationScreen);
		screenController.addScreen("USER_FIND_SCREEN", userFindScreen);
	}
	
	@Override
	public Pane getPane() {
		Image iconAddUser = new Image("file:img/customer-icon-add.png");
		Image iconFindUser = new Image("file:img/customer-icon-find.png");
		
        ImageView iconimgAddUser = new ImageView(iconAddUser);
        Button buttonAddUser = new Button("Create a customer", iconimgAddUser);
        buttonAddUser.setContentDisplay(ContentDisplay.LEFT);
        buttonAddUser.setPrefSize(650, 150);
        buttonAddUser.getStyleClass().add("big-text");
        
        ImageView iconimgFindUser = new ImageView(iconFindUser);
        Button buttonFindUser = new Button("Find a customer", iconimgFindUser);
        buttonFindUser.setContentDisplay(ContentDisplay.LEFT);
        buttonFindUser.setPrefSize(650, 150);
        buttonFindUser.getStyleClass().add("big-text");
       
        
        buttonAddUser.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	screenController.setScreen("USER_CREATION_SCREEN");
		    }
		});
        
        buttonFindUser.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	screenController.setScreen("USER_FIND_SCREEN");
		    }
		});

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(buttonAddUser, buttonFindUser);
        
        vBox.getStyleClass().add("background-style");
        
        vBox.setSpacing(5);
        
        return vBox;
	}

}
