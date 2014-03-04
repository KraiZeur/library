package application.presentationLayer.screens.bookScreens;

import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.PaginationBuilder;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import application.businessLayer.controller.BookController;
import application.businessLayer.utils.DialogUtil;
import application.presentationLayer.screens.Screen;
import dataAccessLayer.daoLayer.DAOBook;
import dataAccessLayer.daoLayer.DAOFactory;
import dataAccessLayer.model.Book;
import dataAccessLayer.model.BookType;

/**
 * The screen to remove a book
 * @author Thomas
 *
 */
public class BookRemoveScreen implements Screen {

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
	private DAOBook daoBook = DAOFactory.getBookDAO();
	private List<Book> bookList;
	private BookController bookCtrl = new BookController();;

	/**
	 * default constructor
	 * @param primaryStage the stage of the application
	 */
	public BookRemoveScreen(Stage primaryStage) {
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
			bookList = bookCtrl.getBooksByParameters(txtFieldName.getText(), txtFieldAuthor.getText(), txtFieldYear.getText(), comboBookType.getValue().toString(), seriesTf.getText(), comboAvailable.getValue());
			flowPane.getChildren().clear();
			flowPane.setVgap(5);
			flowPane.setHgap(5);

			updateBookListView();
		}
	}

	private void updateBookListView() {
		for (Book book : bookList) {
			// Only available books are removable
			if (book.getAvailibility()) {
				Button btn1 = new Button("");
				StackPane stckPane = new StackPane();
				stckPane.getChildren().add(btn1);
				btn1.setGraphic(new ImageView(new Image("file:"+book.getCover())));
				btn1.setCursor(Cursor.DEFAULT);
				btn1.setPrefSize(120, 150);
				Label label = new Label(book.getName());
				label.setCursor(Cursor.DEFAULT);
				label.setTranslateY(65);
				label.setPrefWidth(120);
				label.setStyle("-fx-background-color: rgba(220, 220, 220, 0.95); -fx-font-size : 15px;");

				Button removeBookBtn = new Button("Remove");
				removeBookBtn.setTranslateY(-65);
				removeBookBtn.setPrefWidth(120);

				removeBookBtn.setStyle("-fx-background-color: linear-gradient(#b21200 0%, #c21400 100%);-fx-text-fill: white;"
						+ "-fx-background-radius: 0;");

				removeBookBtn.setOnMouseClicked(new bookRemoveHandler(book));

				stckPane.getChildren().add(label);
				stckPane.getChildren().add(removeBookBtn);

				stckPane.setStyle("-fx-cursor: hand;");
				flowPane.getChildren().add(stckPane);
			}
		}
	}

	class bookRemoveHandler implements EventHandler<MouseEvent>{

		private Book book;

		public bookRemoveHandler(Book book) {
			super();
			this.book = book;
		}

		@Override
		public void handle(MouseEvent mouseEvent) {

			if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
				if(mouseEvent.getClickCount() == 2){

					book.setAvailibility(false);
					daoBook.update(book);
					DialogUtil.basicDialog("Book : "+book.getName() +" deleted");
					bookList = bookCtrl.getBooksByParameters(txtFieldName.getText(), txtFieldAuthor.getText(), txtFieldYear.getText(), comboBookType.getValue().toString(), seriesTf.getText(), comboAvailable.getValue());
					flowPane.getChildren().clear();
					updateBookListView();
				}
			}
		}
	}
}

