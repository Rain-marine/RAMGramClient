package gui.controllers.welcome;

import controllers.AuthController;
import exceptions.InvalidInputException;
import gui.controllers.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.LoggedUser;
import models.NetworkData;
import models.User;
import models.requests.LoginRequest;
import models.responses.LoginResponse;
import models.responses.Response;
import util.ConfigLoader;

public class LoginGuiController {
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private Label errorMessage;
    private Stage stage;
    private Scene scene;
    private Parent root;

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
