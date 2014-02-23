package application.presentationLayer.screens;

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
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import dataAccessLayer.daoLayer.DAOBook;
import dataAccessLayer.daoLayer.DAOFactory;
import dataAccessLayer.model.Book;
import dataAccessLayer.model.BookType;

public class BookFindScreen implements Screen {

	private Stage primaryStage;
	private BorderPane borderPane = new BorderPane();
	private FlowPane flowPane = new FlowPane();
	private TextField txtFieldYear = new TextField();

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
		
		Pagination pagination = PaginationBuilder.create().pageCount(20).build();
		
		borderPane.setBottom(pagination);
		pagination.getStyleClass().add("hbox-search-bar");
		scrollPane.getStyleClass().add("background-style");


		return borderPane;
	}

	public HBox searchBar() {
		HBox hbox = new HBox();
		hbox.setSpacing(5);
		hbox.getStyleClass().add("hbox-search-bar");
		hbox.getChildren().add(new Label("Name : "));
		hbox.getChildren().add(new TextField());
		hbox.getChildren().add(new Label("Year : "));
		
		txtFieldYear.setPrefWidth(50);
		hbox.getChildren().add(txtFieldYear);
		hbox.setStyle("-fx-padding: 10px;");


		ChoiceBox<BookType> cb = new ChoiceBox<BookType>();
		for (BookType bookType : BookType.values()) {
			cb.getItems().add(bookType);
		}

		hbox.getChildren().add(cb);
		Button searchButton  = new Button("Search");
		hbox.getChildren().add(searchButton);


		searchButton.setOnAction( new searchHandler());

		return hbox;
	}
	
	/*
	 * CLass that handle the click on the search button
	 */
	class searchHandler implements EventHandler<ActionEvent>{

		public searchHandler() {
			super();
		}

		@Override
		public void handle(ActionEvent event) {
			DAOBook daobook = DAOFactory.getUserDAO();
			List<Book> bookList = daobook.getBooksByYear(Integer.parseInt(txtFieldYear.getText()));
			
			flowPane.getChildren().clear();
			flowPane.setVgap(5);
			flowPane.setHgap(5);

			for (Book book : bookList) {

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
	 * Class that handle the click on a book and display a popup about the informations of the book
	 */
	class bookInfoHandler implements EventHandler<ActionEvent>{

		private Book book;
		
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
            gp.add(new Label("Name"), 0, 0);
            gp.add(new Label(book.getName()), 1, 0);
            
            gp.add(new Label("Year"), 0, 1);
            gp.add(new Label(String.valueOf(book.getYear())), 1, 1);
            
            if(book.getAvailibility()) {
            	gp.add(LabelBuilder.create().text("Available").style("-fx-text-fill: #31BB22;").build(), 0, 2);
            }
            
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

