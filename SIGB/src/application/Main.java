package application;
	
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

// ajout nouveau commentaire 2
// autre ajout de commentaire 
// autre ajout
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
			root.setCenter(new Slider());
			System.out.println();
			
			System.out.println("test");
			System.out.println("home sweet home");
			System.out.println("work");
			System.out.println("lollll");
			System.out.println("newwwwwwwwwwwww");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
