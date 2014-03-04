package application.presentationLayer.screens.borrowScreens;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BorrowData {
	
    private StringProperty bookId;
    private StringProperty userId;
    private StringProperty bookName;
    private StringProperty userName;
    private StringProperty date;
    private StringProperty returnDate;

    public BorrowData(String bookId, String userId, String bookName, String userName, String date, String returnDate) {

        this.bookId = new SimpleStringProperty(bookId);
        this.userId = new SimpleStringProperty(userId);
        this.bookName = new SimpleStringProperty(bookName);
        this.userName = new SimpleStringProperty(userName);
        this.date = new SimpleStringProperty(date);
        this.returnDate = new SimpleStringProperty(returnDate);

    }


    public StringProperty bookIdProperty() { return bookId; }
    public StringProperty userIdProperty() { return userId; }
    public StringProperty bookNameProperty() { return bookName; }
    public StringProperty userNameProperty() { return userName; }
    public StringProperty dateProperty() { return date; }
    public StringProperty returnDateProperty() { return returnDate; }
}
