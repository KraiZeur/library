package application.presentationLayer.screens;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import application.businessLayer.BookCreationManager;
import application.businessLayer.utils.ImageUtil;
import dataAccessLayer.model.BookType;

public class BookCreationScreen implements Screen {

	private final FileChooser fileChooser = new FileChooser();
	private Stage primaryStage;
	private TextField textFieldName = new TextField();
	private TextField textFieldAuthor = new TextField();
	private TextField textFieldBookSeries = new TextField();
	private TextField textFieldYear = new TextField();
	private ChoiceBox<BookType> cb;
	private GridPane gridPane;
	private File file;
	private File dest;
	private static final String  DEFAULT_IMG_PATH = "img/books/default_book.png";

	public BookCreationScreen(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@Override
	public Pane getPane() {

		gridPane = new GridPane();
		FlowPane flowplane = new FlowPane();
		flowplane.getChildren().add(gridPane);
		gridPane.setStyle("-fx-font: bold 18pt \"Times New Roman\";");

		gridPane.setHgap(5); gridPane.setVgap(5);
		gridPane.getStyleClass().add("grid-pane-style");
		gridPane.setStyle("-fx-background-color: linear-gradient(#E8EAEC 0%, #CCD1D7 50%, #E8EAEC 100%); -fx-padding: 35px;-fx-background-radius: 35px;");
		flowplane.getStyleClass().add("background-style");

		Label labelName = new Label("Book Name :");
		GridPane.setHalignment(labelName, HPos.RIGHT);

		Label labelAuthor = new Label("Author :");
		GridPane.setHalignment(labelAuthor, HPos.RIGHT);


		Label labelYear = new Label("Publication Year :");
		GridPane.setHalignment(labelYear, HPos.RIGHT);


		Label labelBookSeries = new Label("Book Series :");
		GridPane.setHalignment(labelBookSeries, HPos.RIGHT);

		List<Label> listLabel = new ArrayList<Label>();
		listLabel.add(labelName);
		listLabel.add(labelAuthor);
		listLabel.add(labelBookSeries);
		listLabel.add(labelYear);
		
		TextArea taNotes = new TextArea();
		taNotes.setPrefColumnCount(2);
		taNotes.setPrefRowCount(1);


		gridPane.add(labelName, 0, 0);
		gridPane.add(textFieldName, 1, 0);

		Button authorButton = new Button("New Author");
		GridPane.setHalignment(authorButton, HPos.LEFT);
		gridPane.add(authorButton, 2, 1);

		gridPane.add(labelAuthor, 0, 1);
		gridPane.add(textFieldAuthor, 1, 1);

		gridPane.add(labelYear, 0, 2);
		gridPane.add(textFieldYear, 1, 2);

		gridPane.add(labelBookSeries, 0, 3);
		gridPane.add(textFieldBookSeries, 1, 3);

		cb = new ChoiceBox<BookType>();
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
						file = fileChooser.showOpenDialog(primaryStage);
						if (file != null) {
							
							File folder = new File("img/books/");
							int count = folder.list().length;
							
							dest = new File("img/books/" +"book_"+count +"_"+file.getName());
							

							
							gridPane.add(new Label(file.getName()), 2, 5);
						}
					}
				});

		gridPane.add(labelBookType, 0, 4);
		gridPane.add(cb, 1, 4);

		Label labelCover = new Label("Cover :");
		GridPane.setHalignment(labelCover, HPos.RIGHT);

		gridPane.add(labelCover, 0, 5);
		gridPane.add(openButton, 1, 5);

		Button submitBtn = new Button("Create");

		submitBtn.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(final ActionEvent e) {
						int checkNum;
						
						if( dest != null) {
							try {
								ImageUtil.copyFile(file, dest);
							} catch (IOException e1) {
								System.out.println(e1.getMessage());
							}
							checkNum = BookCreationManager.createBook(textFieldName.getText(), cb.getValue(), dest.getPath(), "description", textFieldYear.getText());
						} else {
							checkNum = BookCreationManager.createBook(textFieldName.getText(), cb.getValue(), DEFAULT_IMG_PATH, "description", textFieldYear.getText());
						}

						switch(checkNum) {

						case 0:
							System.err.println("errror");
							break;
						case 1: 
							Stage dialog = new Stage();
							dialog.initStyle(StageStyle.UTILITY);
							Scene scene = new Scene(new Group(new Text(25, 25, "Book Created !")));
							dialog.setHeight(75);
							dialog.setWidth(150);
							dialog.setScene(scene);
							dialog.show();
							file = null;
							break;

						}
					}
				});

		gridPane.add(submitBtn, 1, 8);
		
		gridPane.setAlignment(Pos.CENTER);
		flowplane.setAlignment(Pos.CENTER);

		return flowplane;
	}



}
