package view.gui.controllers.profiles;

import controllers.Controllers;
import view.InfoLoader;
import view.SceneLoader;
import view.popups.AlertBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import models.LoggedUser;
import models.requests.UserActionRequest;
import models.types.UserActionType;
import util.ConfigLoader;

import java.net.URL;
import java.util.ResourceBundle;

public class PrivateProfileGuiController implements Initializable, Controllers {
    @FXML
    private Label info;
    @FXML
    private ImageView profilePhotoImage;

    private static long userId;
    private static int previous;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        InfoLoader.loadInfo(userId, profilePhotoImage, info);
    }


    public void mainMenuButtonClicked(ActionEvent actionEvent) {
        SceneLoader.getInstance().mainMenu(actionEvent);
    }

    public void backButtonClicked(ActionEvent actionEvent) {
        switch (previous){
            case (1) -> SceneLoader.getInstance().explorer(actionEvent);
            case (2) -> SceneLoader.getInstance().timeline(actionEvent);
        }
    }

    public void logoutButtonClicked(ActionEvent actionEvent) {
        SceneLoader.getInstance().logout(actionEvent);
    }


    public void followButtonClicked(ActionEvent actionEvent) {
        new UserActionRequest(LoggedUser.getToken() , LoggedUser.getId() , userId , UserActionType.REQUEST).execute();
        PendingRequestProfileGuiController.setUser(userId);
        PendingRequestProfileGuiController.setPrevious(previous);
        SceneLoader.getInstance().changeScene(ConfigLoader.loadFXML("pendingProf"),actionEvent);
    }

    public void reportButtonClicked(ActionEvent actionEvent) {
        USER_CONTROLLER.reportUser(userId);
        AlertBox.display("reported","User reported successfully");
    }

    public void blockButtonClicked(ActionEvent actionEvent) {
        USER_CONTROLLER.blockUser(userId);
        BlockedProfileGuiController.setUser(userId);
        BlockedProfileGuiController.setPrevious(previous);
        SceneLoader.getInstance().changeScene(ConfigLoader.loadFXML("blockedProf"),actionEvent);
    }

    public static long getUser() {
        return userId;
    }

    public static void setUser(long user) {
        PrivateProfileGuiController.userId = user;
    }

    public static int getPrevious() {
        return previous;
    }

    public static void setPrevious(int previous) {
        PrivateProfileGuiController.previous = previous;
    }
}
