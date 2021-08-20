package view;

import controllers.SettingController;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import models.LoggedUser;
import models.requests.ChangeAccountVisibilityRequest;
import models.requests.ChangeBirthdayRequest;
import models.requests.DeActiveRequest;
import util.Load;
import util.Save;
import view.popups.SimpleConfirmBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import models.NetworkData;
import models.requests.CloseRequest;
import util.ConfigLoader;

import java.io.IOException;
import java.util.Objects;

public class StartUp {


    public void onlineInitialize(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(Objects.requireNonNull(ConfigLoader.loadFXML("loginFXMLAddress")))));
            primaryStage.setTitle("RAMGram");
            primaryStage.setOnCloseRequest(e -> {
                e.consume();
                boolean answer = SimpleConfirmBox.display("Exit confirmation", "Are you sure to Exit?");
                if (answer) {
                    if (LoggedUser.getTrimmedLoggedUser() != null)
                        new SettingController().logout();
                    new CloseRequest().execute();
                    NetworkData.close();
                    primaryStage.close();
                }
            });
            Image icon = new Image(String.valueOf(getClass().getClassLoader().getResource(ConfigLoader.readProperty("appIconAddress"))));
            primaryStage.getIcons().add(icon);
            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(Boolean.parseBoolean(ConfigLoader.readProperty("appWindowResizable")));
            primaryStage.show();
        } catch (IOException fxmlLoadException) {
            System.err.println("FXML URLs configuration is missing");
        }
    }

    public void offlineInitialize(Stage primaryStage) {
        try {
            Load.getInstance().loadLoggedUser();
            if (!LoggedUser.getTrimmedLoggedUser().isActive()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("deActivated accounts can't run in offline mode \nyou'll exit now");
                alert.setOnHidden(we -> {
                    Platform.exit();
                });
                alert.show();
            } else {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(Objects.requireNonNull(ConfigLoader.loadFXML("mainMenuAdd")))));
                primaryStage.setTitle("RAMGram");
                primaryStage.setOnCloseRequest(e -> {
                    e.consume();
                    boolean answer = SimpleConfirmBox.display("Exit confirmation", "Are you sure to Exit?");
                    if (answer) {
                        new SettingController().logout();
                        primaryStage.close();
                    }
                });
                Image icon = new Image(String.valueOf(getClass().getClassLoader().getResource(ConfigLoader.readProperty("appIconAddress"))));
                primaryStage.getIcons().add(icon);
                primaryStage.setScene(new Scene(root));
                primaryStage.setResizable(Boolean.parseBoolean(ConfigLoader.readProperty("appWindowResizable")));
                primaryStage.show();
            }
        } catch (IOException fxmlLoadException) {
            System.err.println("FXML URLs configuration is missing");
        }
    }
}
