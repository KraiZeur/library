package application.main;


import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuBuilder;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuItemBuilder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import application.presentationLayer.ScreensController;
import application.presentationLayer.screens.BookScreen;
import application.presentationLayer.screens.UserScreen;


public class Main extends Application {

	private VBox vBox;
	private boolean isResized = false;
	private VBox arrow;
	private HBox lateral;
	private BorderPane root = new BorderPane();
	private GridPane grid = new GridPane();
	private StackPane rootStack = new StackPane();
	private ScreensController screenController = new ScreensController();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			primaryStage.getIcons().add(new Image("file:img/icon.png"));
			primaryStage.setTitle("Easy SIGB");
			
			UserScreen userScreen = new UserScreen(screenController);
			BookScreen bookScreen = new BookScreen(screenController, primaryStage);
			screenController.addScreen("USER_SCREEN", userScreen);
			screenController.addScreen("BOOK_SCREEN", bookScreen);
			
			Scene scene = new Scene(root,1024,625);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

			primaryStage.setMinHeight(625);
			primaryStage.setMinWidth(1024);

			HBox lateralBar = createLateralBar();
			MenuBar menuBar = createTopMenu();
			
			rootStack.getStyleClass().add("root-stack-style");
			
			grid.getStyleClass().add("root-style");
			
			root.setTop(menuBar);
			root.setCenter(screenController);
			root.setLeft(lateralBar);

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public MenuBar createTopMenu() {
		
		final MenuBar menuBar = new MenuBar();

		// Options->Submenu 2 submenu
		MenuItem menu121 = MenuItemBuilder.create().text("Item 1").build();
		MenuItem menu122 = MenuItemBuilder.create().text("Item 2").build();
		Menu menu12 = MenuBuilder.create().text("Submenu 2").items(menu121, menu122).build();

		// Options->Change Text
		final String change[] = {"Change Text", "Change Back"};
		final MenuItem menu13 = MenuItemBuilder.create().text(change[0]).accelerator(KeyCombination.keyCombination("Shortcut+C")).build();
		menu13.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent t) {
				menu13.setText((menu13.getText().equals(change[0])) ? change[1] : change[0]);
			}
		});  
		Menu menu1 = MenuBuilder.create().text("Options").items(menu12, menu13).build();
		menuBar.getMenus().addAll(menu1);
		return menuBar;
	}

	public HBox createLateralBar() {
		Button button = new Button("Gestion Ouvrage");
		button.setPrefWidth(150);
		button.setPrefHeight(35);

		Button but = ButtonBuilder.create()
				.text("button")
				.prefWidth(150)
				.styleClass("button-style-test")
				.build();
		
		Rectangle[] rect = new Rectangle[10];
		
		for (int i = 0; i < 10; i++) {
			rect[i] = new Rectangle(150, 1);
			rect[i].getStyleClass().add("sep-menu-style");
			rect[i].setFill(Color.GRAY);
		}
		
		Button button2 = new Button("Statistics");
		button2.setPrefWidth(150);
		button2.setPrefHeight(35);

		button.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	screenController.setScreen("BOOK_SCREEN");
		    }
		});
		
		Button button3 = new Button("Gestion Emprunt");
		button3.setPrefWidth(150);
		button3.setPrefHeight(35);
		
		button3.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	FlowPane fp = new FlowPane();
		    	fp.getStyleClass().add("view2-style");
		    	rootStack.getChildren().add(gestionBook());
		    }
		});

		Button button4 = new Button("Gestion Client");
		button4.setPrefWidth(150);
		button4.setPrefHeight(35);

		button4.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	screenController.setScreen("USER_SCREEN");
		    }
		});
		
		button4.getStyleClass().add("button-style-test");
		button3.getStyleClass().add("button-style-test");
		button.getStyleClass().add("button-style-test");
		button2.getStyleClass().add("button-style-test");

		lateral = new HBox();

		arrow = new VBox();
		arrow.setPrefWidth(15);
		arrow.getStyleClass().add("lateral-arrow");

		vBox = new VBox();
		vBox.getChildren().addAll(
				button,
				rect[1],
				button4,
				rect[2],
				button3,
				rect[3],
				button2,
				rect[4],
				but
				);

		lateral.getChildren().addAll(vBox,arrow);

		vBox.setPrefWidth(150);
		vBox.getStyleClass().add("vbox-layout");
		

		arrow.setOnMouseClicked(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent me){

				Timeline timeline = new Timeline(); 

				if(isResized) {

					timeline.setAutoReverse(true);

					timeline.getKeyFrames().addAll
					(new KeyFrame(Duration.ZERO,
							new KeyValue(lateral.translateXProperty(), -150)),
							new KeyFrame(new Duration(250),
									new KeyValue(lateral.translateXProperty(), 0)));
					timeline.playFromStart();

					isResized = false;

				} else {
					timeline.getKeyFrames().addAll
					(new KeyFrame(Duration.ZERO,
							new KeyValue(lateral.translateXProperty(), 0)),
							new KeyFrame(new Duration(250),
									new KeyValue(lateral.translateXProperty(), -150)));

					timeline.playFromStart();


					isResized = true;
				}  
			}
		});
		
		return lateral;

	}
	
	public VBox gestionBook() {
		Image iconAddUser = new Image("file:img/customer-icon-add.png");
		
        ImageView iconimgAddUser = new ImageView(iconAddUser);
        Button buttonAddUser = new Button("Create a book", iconimgAddUser);
        buttonAddUser.setContentDisplay(ContentDisplay.LEFT);
        buttonAddUser.setPrefSize(650, 150);
        buttonAddUser.getStyleClass().add("big-text");
        
		
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(buttonAddUser);
        
        return vBox;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
