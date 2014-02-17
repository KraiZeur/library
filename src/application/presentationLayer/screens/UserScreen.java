package application.presentationLayer.screens;

import application.presentationLayer.ScreensController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class UserScreen implements Screen {

	private ScreensController screenController;
	
	public UserScreen(ScreensController screenController) {
		this.screenController = screenController;
	}
	
	@Override
	public Pane getPane() {
		Image iconAddUser = new Image("file:img/customer-icon-add.png");
		Image iconFindUser = new Image("file:img/customer-icon-find.png");
		Image iconRemoveUser = new Image("file:img/customer-icon-remove.png");
		
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
        
        ImageView iconimgRemoveUser = new ImageView(iconRemoveUser);
        Button buttonRemoveUser = new Button("Remove a customer", iconimgRemoveUser);
        buttonRemoveUser.setContentDisplay(ContentDisplay.LEFT);
        buttonRemoveUser.setPrefSize(650, 150);
        buttonRemoveUser.getStyleClass().add("big-text");
        
		
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(buttonAddUser, buttonFindUser, buttonRemoveUser);
        
        return vBox;
	}

}
