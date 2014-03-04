package application.presentationLayer.screens.userScreens;

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
import javafx.scene.control.Label;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import application.businessLayer.controller.CustomerController;
import application.businessLayer.utils.IntegerUtil;
import application.presentationLayer.screens.Screen;
import dataAccessLayer.model.Customer;
import dataAccessLayer.model.CustomerAdress;
import dataAccessLayer.model.Name;

/**
 * 
 * @author Thomas
 *
 */
public class UserFindScreen implements Screen{

	@SuppressWarnings("unused")
	private Stage primaryStage;
	private FlowPane flowPane = new FlowPane();
	private BorderPane borderPane = new BorderPane();
	private TextField txtFieldFirstname = new TextField();
	private TextField txtFieldLastname = new TextField();
	private TextField txtFieldZipCode = new TextField();
	private CustomerController customerCtrl;

	public UserFindScreen(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public HBox searchBar() {
		HBox hbox = new HBox();
		hbox.setSpacing(5);
		hbox.getStyleClass().add("hbox-search-bar");
		hbox.getChildren().add(new Label("FirstName"));
		txtFieldFirstname.setPromptText("First Name");
		hbox.getChildren().add(txtFieldFirstname);
		hbox.getChildren().add(new Label("LastName"));
		txtFieldLastname.setPromptText("Last Name");
		hbox.getChildren().add(txtFieldLastname);
		hbox.getChildren().add(new Label("ZipCode"));
		txtFieldZipCode.setPromptText("Zip Code");
		hbox.getChildren().add(txtFieldZipCode);

		hbox.setStyle("-fx-padding: 10px;");

		Button searchButton  = new Button("Search");
		hbox.getChildren().add(searchButton);
		searchButton.setOnAction( new searchHandler());
		return hbox;
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

	class searchHandler implements EventHandler<ActionEvent>{

		public searchHandler() {
			super();
		}

		@Override
		public void handle(ActionEvent event) {
			customerCtrl = new CustomerController();

			List<Customer> customerList = customerCtrl.findCustomerWithParameters(txtFieldFirstname.getText(), txtFieldLastname.getText(), txtFieldZipCode.getText());
			flowPane.getChildren().clear();
			flowPane.setVgap(5);
			flowPane.setHgap(5);

			if(customerList != null)  {
				for (Customer customer : customerList) {

					Button btn1 = new Button("");
					btn1.setGraphic(new ImageView(new Image("file:img/icons/customer.png")));
					btn1.setOnAction(new customerInfoHandler(customer));
					StackPane stckPane = new StackPane();
					stckPane.getChildren().add(btn1);
					btn1.setPrefSize(120, 150);
					Label label = new Label(customer.getName().toString());
					label.setTranslateY(65);
					label.setPrefWidth(120);
					label.setStyle("-fx-background-color: rgba(220, 220, 220, 0.95); -fx-font-size : 15px;");
					stckPane.getChildren().add(label);
					stckPane.setStyle("-fx-cursor: hand;");
					flowPane.getChildren().add(stckPane);
				}
			}
		}
	}

	/*
	 * Class that handle the click on a book and display a popup about the informations of the book
	 */
	class customerInfoHandler implements EventHandler<ActionEvent>{

		private Customer customer;
		private Button editBtn;
		private TextField firstNameTf = new TextField();
		private TextField lastNameTf = new TextField();
		private TextField streetTf = new TextField();
		private TextField CityTf = new TextField();
		private TextField zipcodeTf = new TextField();

		public customerInfoHandler(Customer customer) {
			super();
			this.customer = customer;
		}

		@Override
		public void handle(ActionEvent event) {

			final Stage myDialog = new Stage();
			myDialog.initModality(Modality.WINDOW_MODAL);

			GridPane gp = new GridPane();
			gp.setVgap(5);
			gp.setHgap(10);
			gp.add(new Label("Id"), 0, 0);
			gp.add(new Label("#" +customer.getId()), 1, 0);
			gp.add(new Label("First Name"), 0, 1);
			gp.add(firstNameTf, 1, 1);
			firstNameTf.setText(customer.getName().getFirstname());
			gp.add(new Label("Last Name"), 0, 2);
			gp.add(lastNameTf, 1, 2);
			lastNameTf.setText(customer.getName().getLastname());
			gp.add(new Label("Street"), 0, 3);
			gp.add(new Label(customer.getAdress().getNumber() +" " +customer.getAdress().getStreet()), 1, 3);
			gp.add(new Label("City"), 0, 4);
			gp.add(CityTf, 1, 4);
			CityTf.setText(customer.getAdress().getCity());
			gp.add(new Label("Zip Code"), 0, 5);
			gp.add(zipcodeTf, 1, 5);
			zipcodeTf.setText("" +customer.getAdress().getZipcode());

			
			editBtn = new Button("Edit");
			editBtn.setOnAction(new EventHandler<ActionEvent>() {
			    @Override public void handle(ActionEvent e) {
			    	customer.setName(new Name(firstNameTf.getText(), lastNameTf.getText()));
			    	customer.setAdress(new CustomerAdress("tmp", "tmp", CityTf.getText(), Integer.parseInt(zipcodeTf.getText())));
			    	
			    	customerCtrl.getCustomerService().update(customer);
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