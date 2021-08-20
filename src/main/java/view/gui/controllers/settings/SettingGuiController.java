package view.gui.controllers.settings;

import controllers.DateFormat;
import controllers.Controllers;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import view.SceneLoader;
import view.popups.SimpleConfirmBox;
import view.popups.password.NewPasswordBoxSimple;
import view.popups.password.PasswordConfirmBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import models.LoggedUser;
import models.requests.*;
import models.responses.BooleanResponse;
import models.responses.Response;
import util.ConfigLoader;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingGuiController implements Initializable, Controllers {

    boolean isPublic;

    @FXML
    private Button privateButton;
    @FXML
    private Button publicButton;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button logoutButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(LoggedUser.getMode() == LoggedUser.Mode.ONLINE){
            isPublic = (Boolean)  new IsPublicRequest().execute().unleash();
        }
        else {
            isPublic = LoggedUser.getTrimmedLoggedUser().isPublic();
            logoutButton.setDisable(true);
        }
        if(isPublic){
            privateButton.setStyle("-fx-background-color: #717171;");
            publicButton.setStyle("-fx-background-color: #D7A4FF;");
        }
        else {
            publicButton.setStyle("-fx-background-color: #717171;");
            privateButton.setStyle("-fx-background-color: #D7A4FF;");
        }

    }

    public void mainMenuButtonClicked(ActionEvent actionEvent) {
        SceneLoader.getInstance().mainMenu(actionEvent);
    }

    public void backButtonClicked(ActionEvent actionEvent) {
        SceneLoader.getInstance().mainMenu(actionEvent);
    }

    public void logoutButtonClicked(ActionEvent actionEvent) {
        SceneLoader.getInstance().logout(actionEvent);
    }


    public void deActiveButtonClicked(ActionEvent actionEvent) {
        boolean answer = SimpleConfirmBox.display("deactivation","Are you sure you want to deActivate your account?");
        if(answer){
            new DeActiveRequest(false).execute();
            if(LoggedUser.getMode() == LoggedUser.Mode.OFFLINE){
                LoggedUser.getTrimmedLoggedUser().setActive(false);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("your account will deActivate whenever you connect to server \nyou'll exit now");
                alert.setOnHidden(we -> {
                    SETTING_CONTROLLER.logout();
                    Platform.exit();
                });
                alert.show();
            }
            SceneLoader.getInstance().noConfirmLogout(actionEvent);
        }
    }

    public void changePassButtonClicked(ActionEvent actionEvent) {
        boolean result = PasswordConfirmBox.display();
        if(result){
            NewPasswordBoxSimple.display();
        }
    }

    public void changeBDButtonClicked(ActionEvent actionEvent) {
        String birthday = datePicker.getValue() == null ? "" : datePicker.getValue().toString() ;
        if(!birthday.equals("")) {
            new ChangeBirthdayRequest(DateFormat.stringToDate(birthday)).execute();
        }
    }

    public void deleteAccountButtonClicked(ActionEvent actionEvent) {
        boolean answer = SimpleConfirmBox.display("delete account", "Are you sure you want to delete your account?");
        if(answer){
            boolean result = PasswordConfirmBox.display();
            if(result){
                new DeleteAccountRequest().execute();
                SceneLoader.getInstance().noConfirmLogoutDelete(actionEvent);
            }
        }
    }

    public void PrivacySettingButtonClicked(ActionEvent actionEvent) {
        SceneLoader.getInstance().changeScene(ConfigLoader.loadFXML("privacySetting"),actionEvent);
    }


    public void publicButtonClicked(ActionEvent actionEvent) {
        if(!isPublic){
            new ChangeAccountVisibilityRequest( true).execute();
            privateButton.setStyle("-fx-background-color: #717171;");
            publicButton.setStyle("-fx-background-color: #D7A4FF;");
            LoggedUser.getTrimmedLoggedUser().setPublic(true);
            isPublic = true;
        }
    }


    public void privateButtonClicked(ActionEvent actionEvent) {
        if(isPublic){
            new ChangeAccountVisibilityRequest(false).execute();
            publicButton.setStyle("-fx-background-color: #717171;");
            privateButton.setStyle("-fx-background-color: #D7A4FF;");
            LoggedUser.getTrimmedLoggedUser().setPublic(false);
            isPublic = false;
        }
    }
}
