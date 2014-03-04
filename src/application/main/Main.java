package application.main;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuBuilder;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuItemBuilder;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import application.presentationLayer.ScreensController;
import application.presentationLayer.screens.HomeScreen;
import application.presentationLayer.screens.LoginScreen;
import application.presentationLayer.screens.bookScreens.BookScreen;
import application.presentationLayer.screens.borrowScreens.BorrowScreen;
import application.presentationLayer.screens.statScreens.StatisticScreen;
import application.presentationLayer.screens.userScreens.UserScreen;

/**
 * Main program
 * @author Thomas
 *
 */
public class Main extends Application {

	/**
	 * the vbox of the screen
	 */
	private VBox vBox;
	/**
	 * A boolean to check if the lateral bar is hidden or visible
	 */
	private boolean isResized = false;
	/**
	 * The bar to hide or display the lateral bar
	 */
	private VBox arrow;
	/**
	 * The box containing the lateral bar
	 */
	private HBox lateral;
	/**
	 * lateral bar
	 */
	private HBox lateralBar;
	/**
	 * The root Pane contains, every pane
	 */
	private BorderPane root = new BorderPane();
	/**
	 * The grid pane to display the home menu
	 */
	private GridPane grid = new GridPane();
	/**
	 * The root Pane who has two state login and program
	 */
	private StackPane rootStack = new StackPane();
	/**
	 * The screen controller to manage the all the application's screenss
	 */
	private ScreensController screenController = new ScreensController();

	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.getIcons().add(new Image("file:img/icon.png"));
			primaryStage.setTitle("Easy SIGB");

			UserScreen userScreen = new UserScreen(screenController, primaryStage);
			BookScreen bookScreen = new BookScreen(screenController, primaryStage);
			HomeScreen homeScreen = new HomeScreen(screenController);
			BorrowScreen borrowScreen = new BorrowScreen(screenController, primaryStage); 
			StatisticScreen statScreen = new StatisticScreen(screenController); 
			screenController.addScreen("USER_SCREEN", userScreen);
			screenController.addScreen("BOOK_SCREEN", bookScreen);
			screenController.addScreen("HOME_SCREEN", homeScreen);
			screenController.addScreen("BORROW_SCREEN", borrowScreen);
			screenController.addScreen("STAT_SCREEN", statScreen);

			primaryStage.setMinHeight(700);
			primaryStage.setMinWidth(1115);

			lateralBar = createLateralBar();
			MenuBar menuBar = createTopMenu();

			rootStack.getStyleClass().add("root-stack-style");
			screenController.setScreen("HOME_SCREEN");
			grid.getStyleClass().add("root-style");

			root.setTop(menuBar);
			root.setCenter(screenController);
			root.setLeft(lateralBar);
			
			LoginScreen loginScreen = new LoginScreen();
			
			StackPane stckPane = new StackPane();
			stckPane.getChildren().add(root);
			stckPane.getChildren().add(loginScreen);
			loginScreen.setStckPane(stckPane);
			
			Scene scene = new Scene(stckPane,1070,700);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the top menu bar of the program
	 * @return the menu bar
	 */
	private MenuBar createTopMenu() {

		final MenuBar menuBar = new MenuBar();
		menuBar.getStyleClass().add("top-menu-style");
		final MenuItem menu13 = MenuItemBuilder.create().text("Quit").accelerator(KeyCombination.keyCombination("Shortcut+Q")).build();
		menu13.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent t) {
				System.exit(0);
			}
		});  
		Menu menu1 = MenuBuilder.create().text("File").items(menu13).build();
		menuBar.getMenus().addAll(menu1);
		return menuBar;
	}

	/**
	 * Create the lateral bar of the program
	 * @return the lateral bar
	 */
	private HBox createLateralBar() {
		Button button = new Button("Book");
		button.setPrefWidth(150);
		button.setPrefHeight(35);

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

		button2.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				screenController.setScreen("STAT_SCREEN");
			}
		});

		Button button3 = new Button("Borrow");
		button3.setPrefWidth(150);
		button3.setPrefHeight(35);

		button3.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				screenController.setScreen("BORROW_SCREEN");
			}
		});

		Button button4 = new Button("Customer");
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

		Button homeBtn = new Button("Home");
		homeBtn.getStyleClass().add("button-style-test");

		homeBtn.setPrefWidth(150);
		homeBtn.setPrefHeight(35);

		homeBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				screenController.setScreen("HOME_SCREEN");
			}
		});

		vBox = new VBox();
		vBox.getChildren().addAll(homeBtn,rect[5],button,rect[1],button4,rect[2],button3,rect[3],button2,rect[4]);

		arrow.setMinWidth(15);
		vBox.setMinWidth(0);
		lateral.getChildren().addAll(vBox,arrow);

		vBox.setPrefWidth(150);
		vBox.getStyleClass().add("vbox-layout");

		// The animation when the user click on the lateral bar
		arrow.setOnMouseClicked(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent me){

				Timeline timeline = new Timeline(); 

				if(isResized) {

					timeline.setAutoReverse(true);
					timeline.getKeyFrames().addAll
					(new KeyFrame(Duration.ZERO,
							new KeyValue(lateral.translateXProperty(), 0)),
							new KeyFrame(new Duration(5),
									new KeyValue(lateral.translateXProperty(), 0)));
					timeline.playFromStart();
					isResized = false;

				} else {
					timeline.getKeyFrames().addAll(
							new KeyFrame(Duration.ZERO,
									new KeyValue(lateral.translateXProperty(), 0)),
									new KeyFrame(new Duration(5),
											new KeyValue(lateral.translateXProperty(), 0))
							);

					timeline.playFromStart();
					isResized = true;
				}  

				timeline.setOnFinished(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						if(isResized) {
							System.out.println("here");
							vBox.setVisible(false);
							vBox.setPrefWidth(0);
						} else {
							vBox.setVisible(true);
							vBox.setPrefWidth(150);
						}
					}
				});
			}
		});

		return lateral;

	}

	/**
	 * The main method
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
