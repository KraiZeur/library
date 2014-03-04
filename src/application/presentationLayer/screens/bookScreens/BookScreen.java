package application.presentationLayer.screens.bookScreens;

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
 * The screen containing the book creation screen, find screen, remove screen
 * @author Thomas
 *
 */
public class BookScreen implements Screen {

	/**
	 * The screenController of the application
	 */
	private ScreensController screenController;
	
	/**
	 * 
	 * @param screenController
	 * @param primaryStage
	 */
	public BookScreen(ScreensController screenController, Stage primaryStage) {
		this.screenController = screenController;
		BookCreationScreen bookCreationScreen = new BookCreationScreen(primaryStage);
		BookFindScreen bookFindScreen = new BookFindScreen(primaryStage);
		BookRemoveScreen bookRemoveScreen = new BookRemoveScreen(primaryStage);
		screenController.addScreen("BOOK_CREATION_SCREEN", bookCreationScreen);
		screenController.addScreen("BOOK_FIND_SCREEN", bookFindScreen);
		screenController.addScreen("BOOK_REMOVE_SCREEN", bookRemoveScreen);
	}
	
	@Override
	public Pane getPane() {
		Image iconAddBook = new Image("file:img/book-icon-add.png");
		Image iconFindBook = new Image("file:img/book-icon-find.png");
		Image iconRemoveBook = new Image("file:img/book-icon-remove.png");

        ImageView iconimgAddBook = new ImageView(iconAddBook);
        Button buttonAddBook = new Button("Create a book", iconimgAddBook);
        buttonAddBook.setContentDisplay(ContentDisplay.LEFT);
        buttonAddBook.setPrefSize(650, 150);
        buttonAddBook.getStyleClass().add("big-text");
        
        buttonAddBook.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	screenController.setScreen("BOOK_CREATION_SCREEN");
		    }
		});
        
        ImageView iconimgfindBook = new ImageView(iconFindBook);
        Button buttonFindBook = new Button("Find a book", iconimgfindBook);
        buttonFindBook.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	screenController.setScreen("BOOK_FIND_SCREEN");
		    }
		});
        buttonFindBook.setContentDisplay(ContentDisplay.LEFT);
        buttonFindBook.setPrefWidth(650);
        buttonFindBook.getStyleClass().add("big-text");
		
        ImageView iconimgRemoveBook = new ImageView(iconRemoveBook);
        Button buttonRemoveBook = new Button("Remove a book", iconimgRemoveBook);
        buttonRemoveBook.setContentDisplay(ContentDisplay.LEFT);
        buttonRemoveBook.setPrefWidth(650);
        buttonRemoveBook.getStyleClass().add("big-text");
        buttonRemoveBook.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	screenController.setScreen("BOOK_REMOVE_SCREEN");
		    }
		});
        
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(buttonAddBook,buttonFindBook,buttonRemoveBook);
        vBox.getStyleClass().add("background-style");
        
        vBox.setSpacing(5);
        
        return vBox;
	}

}
