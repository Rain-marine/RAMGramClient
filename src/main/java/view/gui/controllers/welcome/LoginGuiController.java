package view.gui.controllers.welcome;

import util.Load;
import view.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import models.LoggedUser;
import models.requests.LoginRequest;
import models.responses.LoginResponse;
import util.ConfigLoader;

public class LoginGuiController {
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private Label errorMessage;


    public void loginButtonClicked(ActionEvent actionEvent) {
        System.out.println("Checking Login validation. please wait...");
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        if (username.isEmpty())
            errorMessage.setText("Enter Your Username");
        else if (password.isEmpty())
            errorMessage.setText("Enter Your Password");
        else {
                LoginResponse response = (LoginResponse) new LoginRequest(username, password).execute();
                response.unleash();
                if (response.isLoginValid()) {
                    System.out.println("you are now logged in. wait while we set up a few things...");
                    Load.getInstance().sendRequests();
                    if (LoggedUser.getTrimmedLoggedUser().isActive()) {
                        SceneLoader.getInstance().mainMenu(actionEvent);
                    } else {
                        SceneLoader.getInstance().changeScene(ConfigLoader.loadFXML("deactiveSelfProf"), actionEvent);
                    }
                }
            else{
                errorMessage.setText(response.getError());
                }
        }
    }

    public void registerButtonClicked(ActionEvent actionEvent) {
        SceneLoader.getInstance().changeScene(ConfigLoader.loadFXML("register"), actionEvent);
    }


}
