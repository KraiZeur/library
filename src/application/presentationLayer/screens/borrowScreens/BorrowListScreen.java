package application.presentationLayer.screens.borrowScreens;

import java.text.SimpleDateFormat;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import application.presentationLayer.screens.Screen;
import dataAccessLayer.daoLayer.DAOBorrow;
import dataAccessLayer.model.Borrowing;

public class BorrowListScreen implements Screen{

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Pane getPane() {
		BorderPane root = new BorderPane();


        final ObservableList<BorrowData> data = FXCollections.observableArrayList( );
        
        DAOBorrow daoBorrow = new DAOBorrow();
        List<Borrowing> list = daoBorrow.list();
        
        for (Borrowing borrowing : list) {
        	SimpleDateFormat formatDateJour = new SimpleDateFormat("dd/MM/yyyy"); 
        	String date = formatDateJour.format(borrowing.getDate_borrowing()); 
        	if (borrowing.getDate_delivery() == null) {
        		data.add(new BorrowData(String.valueOf(borrowing.getBook().getId()), String.valueOf(borrowing.getCustomer().getId()), borrowing.getBook().getName(), borrowing.getCustomer().getName().toString(),date, ""));
        	} else {
            	String returnDate = formatDateJour.format(borrowing.getDate_delivery()); 
            	data.add(new BorrowData(String.valueOf(borrowing.getBook().getId()), String.valueOf(borrowing.getCustomer().getId()), borrowing.getBook().getName(), borrowing.getCustomer().getName().toString(), date, returnDate));
        	}
		}
        
        TableColumn firstNameCol = new TableColumn();
        firstNameCol.setText("Book ID");
        firstNameCol.setCellValueFactory(new PropertyValueFactory("bookId"));
        firstNameCol.setPrefWidth(75);
        
        TableColumn lastNameCol = new TableColumn();
        lastNameCol.setText("User ID");
        lastNameCol.setCellValueFactory(new PropertyValueFactory("userId"));
        lastNameCol.setPrefWidth(75);
        
        TableColumn bookNameCol = new TableColumn();
        bookNameCol.setText("Book Name");
        bookNameCol.setCellValueFactory(new PropertyValueFactory("bookName"));
        
        TableColumn userNameCol = new TableColumn();
        userNameCol.setText("user Name");
        userNameCol.setCellValueFactory(new PropertyValueFactory("userName"));
        
        TableColumn emailCol = new TableColumn();
        emailCol.setText("Date Borrowing");
        emailCol.setCellValueFactory(new PropertyValueFactory("date"));
        emailCol.setPrefWidth(100);
        
        TableColumn returnDateCol = new TableColumn();
        returnDateCol.setText("return Borrowing");
        returnDateCol.setCellValueFactory(new PropertyValueFactory("returnDate"));
        returnDateCol.setPrefWidth(115);
        
        TableView tableView = new TableView();
        tableView.setItems(data);
        tableView.getColumns().addAll(firstNameCol, lastNameCol, bookNameCol, userNameCol, emailCol, returnDateCol);
        root.setCenter(tableView);
        
        return root;
	}

}
