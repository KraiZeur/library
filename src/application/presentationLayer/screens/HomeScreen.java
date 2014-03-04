package application.presentationLayer.screens;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import application.presentationLayer.ScreensController;

/***
 * 
 * @author Thomas
 *
 */
public class HomeScreen implements Screen{

	private ScreensController screenController;
	
	public HomeScreen(ScreensController screenController) {
		this.screenController = screenController;
	}
	
	@Override
	public Pane getPane() {
		
		GridPane gridPane = new GridPane();
		
		/* Create the buttons with parameters */
		Image imgBook = new Image("file:img/icons/book-icon.png");
		ImageView imgViewBook = new ImageView(imgBook);
		Image imgCust = new Image("file:img/icons/cust-icon.png");
		ImageView imgViewCust = new ImageView(imgCust);
		Image imgBorrow = new Image("file:img/icons/borrow-icon.png");
		ImageView imgViewBorrow = new ImageView(imgBorrow);
		Image imgStat = new Image("file:img/icons/stat-icon.png");
		ImageView imgViewStat = new ImageView(imgStat);
		
		Button bookButton = new Button("", imgViewBook);
		bookButton.setPrefSize(220, 220);
		Button userButton = new Button("", imgViewCust);
		userButton.setPrefSize(220, 220);
		Button borrowButton = new Button("", imgViewBorrow);
		borrowButton.setPrefSize(220, 220);
		Button statButton = new Button("", imgViewStat);
		statButton.setPrefSize(220, 220);
		/* End buttons creation */
		
		/* Button's action on click */
		/* Book button go to the Book Screen */
		bookButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	screenController.setScreen("BOOK_SCREEN");
		    }
		});
		/* User button go to the user screen */
		userButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	screenController.setScreen("USER_SCREEN");
		    }
		});
		/* borrow button go to the borrowing screen */
		borrowButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	screenController.setScreen("BORROW_SCREEN");
		    }
		});
		/* stat button go to the stat screen */
		statButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	screenController.setScreen("STAT_SCREEN");
		    }
		});
		/* End button's action on click */
		
		/* Adding the buttons to the grid Pane */
		gridPane.add(bookButton, 0,0);
		gridPane.add(userButton, 1,0);
		gridPane.add(borrowButton, 0, 1);
		gridPane.add(statButton, 1, 1);
		/* End adding the buttons to the grid Pane */
		
		/* Grid Pane parameters */
		gridPane.setHgap(5);
		gridPane.setVgap(5);
		gridPane.setAlignment(Pos.CENTER);
		gridPane.getStyleClass().add("background-style");
		/* End grid Pane parameters */
		
		return gridPane;
	}
}
