package exceptions;

import view.popups.AlertBox;

import java.io.IOException;

public class FXMLLoadException extends IOException {

    String message;
    public FXMLLoadException(String fxmlTitle) {
        super("404: " + fxmlTitle);
        this.message = "the FXML file for " + fxmlTitle + "is missing";
        AlertBox.display("fxml missing" , message);
    }
}
