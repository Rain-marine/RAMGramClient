package gui.controllers.messages;

import controllers.Controllers;
import gui.controllers.ImageController;
import gui.controllers.SceneLoader;
import gui.controllers.popups.AlertBox;
import javafx.animation.PauseTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import gui.controllers.popups.messaging.AddMemberToGroupChat;
import javafx.util.Duration;
import models.LoggedUser;
import models.requests.AddContentRequest;
import models.requests.ChatInfoRequest;
import models.requests.LeaveGroupRequest;
import models.requests.ListRequest;
import models.responses.ChatInfoResponse;
import models.responses.ListResponse;
import models.responses.Response;
import models.types.AddContentType;
import models.types.ListType;
import util.ConfigLoader;

import javax.naming.SizeLimitExceededException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GroupChatShowerGuiController implements Initializable {

    @FXML
    private Label groupNameLabel;
    @FXML
    private ScrollPane messagesArea;
    @FXML
    private TextField messageTextField;
    @FXML
    private ImageView chosenImageView;
    @FXML
    private Label membersLabel;
    @FXML
    private TextField hour;
    @FXML
    private TextField minute;

    private ArrayList<String> membersNames;
    private ArrayList<Long> messageIDs;
    private Response response;
    private Response infoResponse;
    private byte[] chosenImageByteArray = null;
    private PauseTransition timer;
    private static long groupId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        hour.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                hour.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        minute.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                minute.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        timer = new PauseTransition(Duration.seconds(Integer.parseInt(ConfigLoader.readProperty("refreshTime"))));
        timer.setOnFinished(
                e -> {
                    loadMessages();
                });
        loadMessages();
    }

    public void backButtonClicked(ActionEvent actionEvent) {
        timer.stop();
        SceneLoader.getInstance().messaging(actionEvent);
    }

    public void logoutButtonClicked(ActionEvent actionEvent) {
        timer.stop();
        SceneLoader.getInstance().logout(actionEvent);
    }

    public void mainMenuButtonClicked(ActionEvent actionEvent) {
        timer.stop();
        SceneLoader.getInstance().mainMenu(actionEvent);
    }

    public void sendButtonClicked(ActionEvent actionEvent) {
        String messageText = messageTextField.getText();
        if (messageText.equals("") && chosenImageByteArray == null) {
            AlertBox.display("Nerd Alert", "write something idiot");
        } else {
            new AddContentRequest(LoggedUser.getToken(), LoggedUser.getId(), AddContentType.GROUP_MESSAGE, chosenImageByteArray, messageText, groupId, 0L).execute();
            chosenImageView.setImage(null);
            messageTextField.clear();
            loadMessages();
        }
    }

    private void loadMessages() {
        timer.stop();
        infoResponse = new ChatInfoRequest(LoggedUser.getToken(), LoggedUser.getId(), groupId).execute();
        groupNameLabel.setText(((ChatInfoResponse) infoResponse).getFrontUsername());
        membersNames = ((ChatInfoResponse) infoResponse).getMembersNames();
        membersLabel.setText(String.join("\n", membersNames));

        VBox list = new VBox(5);
        response = new ListRequest(LoggedUser.getToken(), LoggedUser.getId(), ListType.MESSAGE, groupId).execute();
        messageIDs = ((ListResponse) response).getIds();
        for (Long messageID : messageIDs) {
            list.getChildren().add(new MessageCard(messageID).getCard());
        }
        messagesArea.setContent(list);
        timer.playFromStart();
    }

    public static long getGroupId() {
        return groupId;
    }

    public static void setGroupId(long groupId) {
        GroupChatShowerGuiController.groupId = groupId;
    }

    public void addMemberButtonClicked(ActionEvent actionEvent) {
        if (AddMemberToGroupChat.display(groupId)) {
            Response infoResponse = new ChatInfoRequest(LoggedUser.getToken(), LoggedUser.getId(), groupId).execute();
            ArrayList<String> membersNames = ((ChatInfoResponse) infoResponse).getMembersNames();
            membersLabel.setText(String.join("\n", membersNames));
        }

    }

    public void leaveButtonClicked(ActionEvent actionEvent) {
        timer.stop();
        new LeaveGroupRequest(LoggedUser.getToken(), LoggedUser.getId(), groupId).execute();
        SceneLoader.getInstance().messaging(actionEvent);

    }

    public void choosePicButtonClicked(ActionEvent actionEvent) {
        timer.pause();
        try {
            chosenImageByteArray = ImageController.pickImage();
            if (chosenImageByteArray != null) {
                chosenImageView.setImage(ImageController.byteArrayToImage(chosenImageByteArray));
            }
        } catch (SizeLimitExceededException e) {
            AlertBox.display("too large", "Image size too large");
        }
        timer.play();
    }

    public void sendScheduledButtonClicked(ActionEvent actionEvent) {
        String messageText = messageTextField.getText();
        if (messageText.equals("") && chosenImageByteArray == null) {
            AlertBox.display("Nerd Alert", "write something idiot");
        } else {
            if (hour.getText().equals("") && minute.getText().equals("")) {
                AlertBox.display("Nerd Alert", "enter the time idiot");
            } else {
                long hours = Long.parseLong(hour.getText()) * 3600;
                long minutes = Long.parseLong(minute.getText()) * 60;
                new AddContentRequest(LoggedUser.getToken(), LoggedUser.getId(), AddContentType.SCHEDULED, chosenImageByteArray, messageText, groupId, hours + minutes).execute();
                chosenImageView.setImage(null);
                messageTextField.clear();
                hour.clear();
                minute.clear();
            }
        }

    }

}
