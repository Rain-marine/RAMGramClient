package gui.controllers.settings;

import gui.controllers.SceneLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import models.LoggedUser;
import models.requests.ChangeInfoRequest;
import models.types.ChangeInfoType;
import util.ConfigLoader;

import java.net.URL;
import java.util.ResourceBundle;

public class PrivacySettingGuiController implements Initializable {
    @FXML
    private ChoiceBox<String> lastSeenChoice;

    private String lastSeenStatus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lastSeenChoice.getItems().addAll("nobody", "everybody", "following");
        reload();
    }

    private void reload() {
        lastSeenStatus = LoggedUser.getTrimmedLoggedUser().getLastSeenStatus();
        lastSeenChoice.setValue(lastSeenStatus);
    }

    public void mainMenuButtonClicked(ActionEvent actionEvent) {
        SceneLoader.getInstance().mainMenu(actionEvent);
    }

    public void backButtonClicked(ActionEvent actionEvent) {
        SceneLoader.getInstance().changeScene(ConfigLoader.loadFXML("setting"), actionEvent);
    }

    public void logoutButtonClicked(ActionEvent actionEvent) {
        SceneLoader.getInstance().logout(actionEvent);
    }

    public void saveButtonClicked(ActionEvent actionEvent) {
        boolean hasAnythingChanged = false;
        String newLSStatus = lastSeenChoice.getValue();
        if (!newLSStatus.equals(lastSeenStatus)) {
            hasAnythingChanged = true;
            new ChangeInfoRequest(LoggedUser.getToken() , LoggedUser.getId() , ChangeInfoType.LAST_SEEN, newLSStatus).execute().unleash();
        }
        if (hasAnythingChanged)
            reload();
    }

}
