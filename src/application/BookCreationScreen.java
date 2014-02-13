package application;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class BookCreationScreen implements Screen {

	@Override
	public Pane getPane() {
		GridPane gridPane = new GridPane();
		
		HBox boxName = new HBox(5);
		Label labelName = new Label("Book Name :");
		TextField textFieldName = new TextField();
		
		HBox boxYear = new HBox(5);
		Label labelYear = new Label("Publication Year :");
		TextField textFieldYear = new TextField();
		
		boxName.getChildren().addAll(labelName, textFieldName);
		boxYear.getChildren().addAll(labelYear, textFieldYear);
		
		gridPane.add(boxName, 0, 0);
		gridPane.add(boxYear, 5, 5);
		
		return boxName;
	}

}
