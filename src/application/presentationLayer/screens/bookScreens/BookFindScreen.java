package application.presentationLayer.screens.bookScreens;

import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.control.Pagination;
import javafx.scene.control.PaginationBuilder;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.Modality;
import javafx.stage.Stage;
import application.businessLayer.controller.BookController;
import application.businessLayer.utils.IntegerUtil;
import application.presentationLayer.screens.Screen;
import dataAccessLayer.model.Book;
import dataAccessLayer.model.BookType;

/**
 * The screen to find a book
 * @author Thomas
 *
 */
public class BookFindScreen implements Screen {

	@SuppressWarnings("unused")
	private Stage primaryStage;
	private BorderPane borderPane = new BorderPane();
	private FlowPane flowPane = new FlowPane();
	private TextField txtFieldYear = new TextField();
	private TextField txtFieldName = new TextField();
	private TextField txtFieldAuthor = new TextField();
	private ChoiceBox<BookType> comboBookType;
	private TextField seriesTf;
	private ChoiceBox<String> comboAvailable;
	private BookController bookCtrl;

	public BookFindScreen(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@Override
	public Pane getPane() {

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setStyle("-fx-padding: 25px;");
		scrollPane.setContent(flowPane);
		scrollPane.viewportBoundsProperty().addListener(new ChangeListener<Bounds>() {
			@Override
			public void changed(ObservableValue<? extends Bounds> ov, Bounds oldBounds, Bounds bounds) {
				flowPane.setPrefWidth(bounds.getWidth());
				flowPane.setPrefHeight(bounds.getHeight());
			}
		});

		borderPane.setCenter(scrollPane);
		borderPane.setTop(searchBar());

		Pagination pagination = PaginationBuilder.create().pageCount(1).build();

		borderPane.setBottom(pagination);
		pagination.getStyleClass().add("hbox-search-bar");
		scrollPane.getStyleClass().add("background-style");


		return borderPane;
	}

	/**
	 * Create the search bar of the screen with name field, year field ...
	 * @return
	 */
	public HBox searchBar() {
		HBox hbox = new HBox();
		hbox.setSpacing(5);
		hbox.getStyleClass().add("hbox-search-bar");
		hbox.getChildren().add(new Label("Name"));
		txtFieldName.setPromptText("Enter the book's name");
		txtFieldName.setPrefWidth(100);
		hbox.getChildren().add(txtFieldName);
		
		hbox.getChildren().add(new Label("Author"));
		hbox.getChildren().add(txtFieldAuthor);
		txtFieldAuthor.setPromptText("Author's name");
		txtFieldAuthor.setPrefWidth(100);
		hbox.getChildren().add(new Label("Year"));

		txtFieldYear.setPrefWidth(50);
		txtFieldYear.setPromptText("Year");
		hbox.getChildren().add(txtFieldYear);
		hbox.setStyle("-fx-padding: 10px;");

		Label seriesLabel = new Label("Series");
		hbox.getChildren().add(seriesLabel);

		seriesTf = new TextField();
		seriesTf.setPromptText("Enter the book's series");
		hbox.getChildren().add(seriesTf);

		comboBookType = new ChoiceBox<BookType>();
		for (BookType bookType : BookType.values()) {
			comboBookType.getItems().add(bookType);
		}
		comboBookType.getSelectionModel().selectFirst();
		comboAvailable = new ChoiceBox<String>();
		comboAvailable.getItems().add("available");
		comboAvailable.getItems().add("not available");
		comboAvailable.getSelectionModel().selectFirst();

		Label typeLabel = new Label("Type");

		hbox.getChildren().add(typeLabel);
		hbox.getChildren().add(comboBookType);
		hbox.getChildren().add(comboAvailable);
		Button searchButton  = new Button("Search");
		hbox.getChildren().add(searchButton);


		searchButton.setOnAction( new searchHandler());

		return hbox;
	}

	/*
	 * CLass that handle the click on the search button according to the parameters entered by the user
	 */
	class searchHandler implements EventHandler<ActionEvent>{

		public searchHandler() {
			super();
		}

		@Override
		public void handle(ActionEvent event) {
			bookCtrl = new BookController();
			List<Book> bookList = bookCtrl.getBooksByParameters(txtFieldName.getText(), txtFieldAuthor.getText(), txtFieldYear.getText(), comboBookType.getValue().toString(), seriesTf.getText(), comboAvailable.getValue());
			flowPane.getChildren().clear();
			flowPane.setVgap(5);
			flowPane.setHgap(5);

			// Create the books icon in the view according to the search parameters
			createBooksIcon(bookList);
		}
	}

	/**
	 * Create the books icon according to the list of books
	 * @param list the list containing the books to be displayed
	 */
	private void createBooksIcon(List<Book> list) {

		if (list !=null) {
			for (Book book : list) {
				Button btn1 = new Button("");
				btn1.setOnAction(new bookInfoHandler(book));
				StackPane stckPane = new StackPane();
				stckPane.getChildren().add(btn1);
				btn1.setGraphic(new ImageView(new Image("file:"+book.getCover())));
				btn1.setPrefSize(120, 150);
				Label label = new Label(book.getName());
				label.setTranslateY(65);
				label.setPrefWidth(120);
				label.setStyle("-fx-background-color: rgba(220, 220, 220, 0.95); -fx-font-size : 15px;");
				stckPane.getChildren().add(label);
				stckPane.setStyle("-fx-cursor: hand;");
				flowPane.getChildren().add(stckPane);
			}
		}

	}

	/*
	 * Class that handle the click on a book icon
	 * Display a popup when the user click on the icon about the informations of the book
	 */
	class bookInfoHandler implements EventHandler<ActionEvent>{

		private Book book;
		private TextField nameTf =  new TextField();
		private TextField yearTf =  new TextField();
		private TextArea desc;
		private Button editBtn;

		public bookInfoHandler(Book book) {
			super();
			this.book = book;
		}

		@Override
		public void handle(ActionEvent event) {

			final Stage myDialog = new Stage();
			myDialog.initModality(Modality.WINDOW_MODAL);

			GridPane gp = new GridPane();
			gp.setVgap(5);
			gp.setHgap(10);
			gp.add(new Label("Book Id"), 0, 0);
			gp.add(new Label("#" +String.valueOf(book.getId())), 1, 0);
			gp.add(new Label("Name"), 0, 1);
			gp.add(nameTf, 1, 1);
			nameTf.setText(book.getName());

			gp.add(new Label("Author"), 0, 2);
			gp.add(new Label(book.getAuthor().getName().toString()), 1, 2);
			
			gp.add(new Label("Year"), 0, 3);
			gp.add(yearTf, 1, 3);
			yearTf.setText(String.valueOf(book.getYear()));
			
			gp.add(new Label("Description"), 0, 4);
			desc = new TextArea(book.getDescription());
			desc.setPrefWidth(200);
			desc.setWrapText(true);
			
			gp.add(desc, 1, 4);
			
			if(book.getAvailibility()) {
				gp.add(LabelBuilder.create().text("Available").style("-fx-text-fill: #31BB22;").build(), 0, 5);
			} else {
				gp.add(LabelBuilder.create().text("Not Available").style("-fx-text-fill: #D7090E;").build(), 0, 5);
			}
			
			editBtn = new Button("Edit");
			editBtn.setOnAction(new EventHandler<ActionEvent>() {
			    @Override public void handle(ActionEvent e) {
			    	book.setDescription(desc.getText());
			    	book.setName(nameTf.getText());
			    	if(IntegerUtil.isInteger(yearTf.getText())) {
			    		book.setYear(Integer.parseInt(yearTf.getText()));
			    	}
			    	
			    	bookCtrl.getBookService().update(book);
			    	editBtn.setStyle("-fx-background-color:linear-gradient(#f0ff35, #a9ff00),radial-gradient(center 50% -40%, radius 200%, #b8ee36 45%, #80c800 50%);");
			    }
			});
			
			gp.add(editBtn, 0,6);

			Scene myDialogScene = new Scene(VBoxBuilder.create()
					.children(gp)
					.alignment(Pos.CENTER)
					.padding(new Insets(10))
					.build());

			myDialog.setScene(myDialogScene);
			myDialog.show();
		}
	}
}

