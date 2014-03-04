package application.presentationLayer.screens.bookScreens;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import application.businessLayer.controller.BookController;
import application.businessLayer.utils.DialogUtil;
import application.businessLayer.utils.ImageUtil;
import application.presentationLayer.screens.Screen;
import dataAccessLayer.model.BookType;

/**
 * The screen to create a book
 * @author Thomas
 *
 */
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
	private Label labelFileName;
	private TextArea description = new TextArea();

	public BookCreationScreen(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@Override
	public Pane getPane() {

		gridPane = new GridPane();
		FlowPane flowplane = new FlowPane();

		/* Grid pane and flow pane parameters */
		flowplane.getChildren().add(gridPane);
		gridPane.setStyle("-fx-font: bold 18pt \"Times New Roman\";");
		gridPane.setHgap(5); gridPane.setVgap(5);
		gridPane.getStyleClass().add("grid-pane-style");
		gridPane.setStyle("-fx-background-color: linear-gradient(#E8EAEC 0%, #CCD1D7 50%, #E8EAEC 100%); -fx-padding: 35px;-fx-background-radius: 35px;");
		flowplane.getStyleClass().add("background-style");
		gridPane.setAlignment(Pos.CENTER);
		flowplane.setAlignment(Pos.CENTER);

		/* Creation of the different label and textfield inside the formulaire */
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

		description.setPromptText("Enter the book's description");
		
		Label labelCover = new Label("Cover :");
		GridPane.setHalignment(labelCover, HPos.RIGHT);

		Button authorButton = new Button("New Author");
		GridPane.setHalignment(authorButton, HPos.LEFT);

		cb = new ChoiceBox<BookType>();
		for (BookType bookType : BookType.values()) {
			cb.getItems().add(bookType);
		}

		cb.getSelectionModel().selectFirst();

		Label labelBookType = new Label("Book Type :");
		GridPane.setHalignment(labelBookType, HPos.RIGHT);

		Button openButton = new Button("Open a Picture...");
		Button submitBtn = new Button("Create");
		/* End creation of formulaire components */

		/* Adding the components to the grid pane */
		gridPane.add(labelName, 0, 0);
		gridPane.add(textFieldName, 1, 0);
		textFieldName.setPromptText("Book's Name");
		//gridPane.add(authorButton, 2, 1);
		gridPane.add(labelAuthor, 0, 1);
		gridPane.add(textFieldAuthor, 1, 1);
		textFieldAuthor.setPromptText("Lastname, Firstname");
		gridPane.add(labelYear, 0, 2);
		gridPane.add(textFieldYear, 1, 2);
		textFieldYear.setPromptText("Year");
		gridPane.add(labelBookSeries, 0, 3);
		gridPane.add(textFieldBookSeries, 1, 3);
		textFieldBookSeries.setPromptText("Enter the book's series");
		gridPane.add(labelBookType, 0, 5);
		gridPane.add(cb, 1, 5);
		gridPane.add(labelCover, 0, 6);
		gridPane.add(openButton, 1, 6);
		gridPane.add(new Label("Description"), 0, 4);
		description.setWrapText(true);
		gridPane.add(description, 1, 4);
		description.setPrefWidth(200);
		gridPane.add(submitBtn, 1, 9);
		/* End adding components to the grid pane */


		/* Button's action */
		/* Action when the user click on the add picture button */
		openButton.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(final ActionEvent e) {
						file = fileChooser.showOpenDialog(primaryStage);
						if (file != null) {

							labelFileName = new Label(file.getName());
							
							File folder = new File("img/books/");
							int count = folder.list().length;

							dest = new File("img/books/" +"book_"+count +"_"+file.getName());

							gridPane.add(labelFileName, 2, 6); // Add the label when the file is loaded
						}
					}
				});
		
		/* Action when the user submit the formulaire */
		submitBtn.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(final ActionEvent e) {

						BookController bookCtrl = new BookController();

						if( dest != null) {
							try {
								ImageUtil.copyFile(file, dest);
							} catch (IOException e1) {
								System.out.println(e1.getMessage());
							}
						} 
						
						String result = "";
						
						if(dest == null) {
							result = bookCtrl.createBookWithParameters(textFieldName.getText(), textFieldAuthor.getText(), textFieldYear.getText(), cb.getValue(), null, description.getText());
						} else {
							result = bookCtrl.createBookWithParameters(textFieldName.getText(), textFieldAuthor.getText(), textFieldYear.getText(), cb.getValue(), dest.getPath(), description.getText());
						}

						if(result.equals("Creation succeed")) {
							if(file != null) {
								gridPane.getChildren().remove(15);
							}
							file = null;
							dest = null;
							
						}

						DialogUtil.basicDialog(result);

					}
				});
		/* End button's action */

		return flowplane;
	}
}
