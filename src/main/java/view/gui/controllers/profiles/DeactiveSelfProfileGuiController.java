package view.gui.controllers.profiles;

import controllers.Controllers;
import view.SceneLoader;
import javafx.event.ActionEvent;
import models.LoggedUser;
import models.requests.DeActiveRequest;

public class DeactiveSelfProfileGuiController implements Controllers {

    public void activateButtonClicked(ActionEvent actionEvent) {
        new DeActiveRequest( true).execute();
        SceneLoader.getInstance().mainMenu(actionEvent);
    }

    public void logoutButtonClicked(ActionEvent actionEvent) {
        SceneLoader.getInstance().logout(actionEvent);
    }
}
