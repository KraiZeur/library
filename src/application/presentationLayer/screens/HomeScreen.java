package application.presentationLayer.screens;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import application.presentationLayer.ScreensController;

public class HomeScreen implements Screen{

	private ScreensController screenController;
	
	public HomeScreen(ScreensController screenController) {
		this.screenController = screenController;
	}
	
	@Override
	public Pane getPane() {
		
		GridPane gridPane = new GridPane();
		gridPane.getStyleClass().add("background-style");
		
		Image imgBook = new Image("file:img/icons/book-icon.png");
		ImageView imgViewBook = new ImageView(imgBook);
		
		Image imgCust = new Image("file:img/icons/cust-icon.png");
		ImageView imgViewCust = new ImageView(imgCust);
		
		Button bookButton = new Button("", imgViewBook);
		Button userButton = new Button("", imgViewCust);
		
		gridPane.add(bookButton, 0,0);
		gridPane.add(userButton, 1,0);
		
		gridPane.setAlignment(Pos.CENTER);
		
		return gridPane;
	}

}
