package view.gui.controllers.personalpage.notifications;

import controllers.Controllers;
import view.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import models.trimmed.TrimmedNotification;
import util.ConfigLoader;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SystemNotificationGuiController implements Initializable, Controllers {

    @FXML
    private ScrollPane notifArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<TrimmedNotification> notifications = NOTIFICATION_CONTROLLER.getSystemNotification();
        if (notifications.size() == 0) {
            Label nothing = new Label("You have no system notification!");
            notifArea.setContent(nothing);
        } else {
            VBox list = new VBox(10);
            for (TrimmedNotification notification : notifications) {
                Label info = new Label();
                switch (notification.getType()) {
                    case UNFOLLOW -> info.setText(notification.getSender() + " unfollowed you!");
                    case START_FOLLOW -> info.setText(notification.getSender() + " started following you!");
                    case FOLLOW_REQ_REJECT -> info.setText(notification.getSender() + " rejected your follow request!");
                }
                NOTIFICATION_CONTROLLER.deleteNotification(notification.getId());
                list.getChildren().add(info);
            }
            notifArea.setContent(list);
        }

    }

    public void logoutButtonClicked(ActionEvent actionEvent) {
        SceneLoader.getInstance().logout(actionEvent);
    }

    public void backButtonClicked(ActionEvent actionEvent) {
        SceneLoader.getInstance().changeScene(ConfigLoader.loadFXML("notificationAdd"),actionEvent);
    }

    public void mainMenuButtonClicked(ActionEvent actionEvent) {
        SceneLoader.getInstance().mainMenu(actionEvent);
    }
}
