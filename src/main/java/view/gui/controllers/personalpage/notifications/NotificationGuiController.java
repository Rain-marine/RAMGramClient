package view.gui.controllers.personalpage.notifications;

import view.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import models.LoggedUser;
import util.ConfigLoader;

import java.net.URL;
import java.util.ResourceBundle;

public class NotificationGuiController implements Initializable {

    @FXML
    private Button requestsToMeBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (LoggedUser.getTrimmedLoggedUser().isPublic()){
            requestsToMeBtn.setDisable(true);
        }
    }


    public void logoutButtonClicked(ActionEvent actionEvent) {
        SceneLoader.getInstance().logout(actionEvent);
    }

    public void backButtonClicked(ActionEvent actionEvent) {
        SceneLoader.getInstance().personalPage(actionEvent);
    }

    public void mainMenuButtonClicked(ActionEvent actionEvent) {
        SceneLoader.getInstance().mainMenu(actionEvent);
    }

    public void requestsToMe(ActionEvent actionEvent) {
        SceneLoader.getInstance().changeScene(ConfigLoader.loadFXML("reqToMe"), actionEvent);
    }

    public void myRequests(ActionEvent actionEvent) {
        SceneLoader.getInstance().changeScene(ConfigLoader.loadFXML("myReq"), actionEvent);

    }

    public void systemNotif(ActionEvent actionEvent) {
        SceneLoader.getInstance().changeScene(ConfigLoader.loadFXML("systemNotif"), actionEvent);

    }
}
