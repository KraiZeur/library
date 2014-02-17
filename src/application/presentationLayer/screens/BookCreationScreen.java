package application.presentationLayer.screens;

import java.io.File;
import java.io.FileInputStream;

import application.businessLayer.BookCreationManager;
import dataAccessLayer.domainObjects.BookType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class BookCreationScreen implements Screen {

	private final FileChooser fileChooser = new FileChooser();
	private Stage primaryStage;
	private TextField textFieldName = new TextField();
	private TextField textFieldAuthor = new TextField();
	private TextField textFieldBookSeries = new TextField();
	private TextField textFieldYear = new TextField();

	public BookCreationScreen(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@Override
	public Pane getPane() {
		GridPane gridPane = new GridPane();
		gridPane.setHgap(5); gridPane.setVgap(5);
		gridPane.getStyleClass().add("grid-pane-style");

		Label labelName = new Label("Book Name :");
		GridPane.setHalignment(labelName, HPos.RIGHT);
		
		
		Label labelAuthor = new Label("Author :");
		GridPane.setHalignment(labelAuthor, HPos.RIGHT);
		

		Label labelYear = new Label("Publication Year :");
		GridPane.setHalignment(labelYear, HPos.RIGHT);
		

		Label labelBookSeries = new Label("Book Series :");
		GridPane.setHalignment(labelBookSeries, HPos.RIGHT);
		

		TextArea taNotes = new TextArea();
		taNotes.setPrefColumnCount(2);
		taNotes.setPrefRowCount(1);

		//gridPane.setGridLinesVisible(true);

		gridPane.add(labelName, 0, 0);
		gridPane.add(textFieldName, 1, 0);

		Button authorButton = new Button("New Author");
		gridPane.add(authorButton, 3, 1);

		gridPane.add(labelAuthor, 0, 1);
		gridPane.add(textFieldAuthor, 1, 1);

		gridPane.add(labelYear, 0, 2);
		gridPane.add(textFieldYear, 1, 2);

		gridPane.add(labelBookSeries, 0, 3);
		gridPane.add(textFieldBookSeries, 1, 3);

		ChoiceBox<BookType> cb = new ChoiceBox<BookType>();
		for (BookType bookType : BookType.values()) {
			cb.getItems().add(bookType);
		}

		cb.getSelectionModel().selectFirst();

		Label labelBookType = new Label("Book Type :");
		GridPane.setHalignment(labelBookType, HPos.RIGHT);

		Button openButton = new Button("Open a Picture...");

		openButton.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(final ActionEvent e) {
						File file = fileChooser.showOpenDialog(primaryStage);
						if (file != null) {
							System.out.println(createPortrait(file));
						}
					}
				});

		gridPane.add(labelBookType, 0, 4);
		gridPane.add(cb, 1, 4);

		Label labelCover = new Label("Cover :");
		GridPane.setHalignment(labelCover, HPos.RIGHT);

		gridPane.add(labelCover, 0, 5);
		gridPane.add(openButton, 1, 5);

		Button submitBtn = new Button("Submit");

		submitBtn.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(final ActionEvent e) {
						BookCreationManager.createBook(textFieldName.getText());
					}
				});
		
		gridPane.add(submitBtn, 1, 8);

		gridPane.setAlignment(Pos.CENTER);

		return gridPane;
	}

	public static byte[] createPortrait(File file) {
		byte[] byteFile = new byte[(int) file.length()];

		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.read(byteFile);
			fileInputStream.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return byteFile;
	}

}
