package application.presentationLayer.screens;

import application.presentationLayer.ScreensController;
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

public class BookScreen implements Screen {

	private ScreensController screenController;
	
	public BookScreen(ScreensController screenController, Stage primaryStage) {
		this.screenController = screenController;
		BookCreationScreen bookCreationScreen = new BookCreationScreen(primaryStage);
		screenController.addScreen("BOOK_CREATION_SCREEN", bookCreationScreen);
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
        buttonFindBook.setContentDisplay(ContentDisplay.LEFT);
        buttonFindBook.setPrefWidth(650);
        buttonFindBook.getStyleClass().add("big-text");
		
        ImageView iconimgRemoveBook = new ImageView(iconRemoveBook);
        Button buttonRemoveBook = new Button("Remove a book", iconimgRemoveBook);
        buttonRemoveBook.setContentDisplay(ContentDisplay.LEFT);
        buttonRemoveBook.setPrefWidth(650);
        buttonRemoveBook.getStyleClass().add("big-text");
        
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(buttonAddBook,buttonFindBook,buttonRemoveBook);
        
        return vBox;
	}

}
