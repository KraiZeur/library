package application.presentationLayer.screens.borrowScreens;

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
import application.presentationLayer.ScreensController;
import application.presentationLayer.screens.Screen;

/**
 * 
 * @author Thomas
 *
 */
public class BorrowScreen implements Screen{

	private ScreensController screenController;
	
	public BorrowScreen(ScreensController screenController, Stage primaryStage) {
		this.screenController = screenController;
		BorrowCreateScreen borrowCreationScreen = new BorrowCreateScreen(primaryStage);
		BorrowRemoveScreen borrowRemoveScreen = new BorrowRemoveScreen(primaryStage);
		BorrowListScreen borrowListScreen = new BorrowListScreen();
		screenController.addScreen("BORROW_CREATION_SCREEN", borrowCreationScreen);
		screenController.addScreen("BORROW_REMOVE_SCREEN", borrowRemoveScreen);
		screenController.addScreen("BORROW_LIST_SCREEN", borrowListScreen);
	}
	
	@Override
	public Pane getPane() {
		Image iconAddBook = new Image("file:img/book-icon-add.png");
		Image iconRemBorrow = new Image("file:img/icons/return-borrow.png");
		Image iconListBook = new Image("file:img/book-icon-find.png");

        ImageView iconimgAddBook = new ImageView(iconAddBook);
        Button buttonAddBook = new Button("Borrow a book", iconimgAddBook);
        buttonAddBook.setContentDisplay(ContentDisplay.LEFT);
        buttonAddBook.setPrefSize(650, 150);
        buttonAddBook.getStyleClass().add("big-text");
        
        buttonAddBook.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	screenController.setScreen("BORROW_CREATION_SCREEN");
		    }
		});
        
        ImageView iconimgRemBorrow = new ImageView(iconRemBorrow);
        Button buttonFindBook = new Button("Return a book", iconimgRemBorrow);
        buttonFindBook.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	screenController.setScreen("BORROW_REMOVE_SCREEN");
		    }
		});
        
        ImageView iconimglistBorrow = new ImageView(iconListBook);
        Button buttonListBook = new Button("List borrow", iconimglistBorrow);
        buttonListBook.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	screenController.setScreen("BORROW_LIST_SCREEN");
		    }
		});
        
        buttonListBook.setContentDisplay(ContentDisplay.LEFT);
        buttonListBook.setPrefWidth(650);
        buttonListBook.getStyleClass().add("big-text");
        buttonFindBook.setContentDisplay(ContentDisplay.LEFT);
        buttonFindBook.setPrefWidth(650);
        buttonFindBook.getStyleClass().add("big-text");

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(buttonAddBook,buttonFindBook,buttonListBook);
        vBox.getStyleClass().add("background-style");
        
        vBox.setSpacing(5);
        
        return vBox;
	}

}
