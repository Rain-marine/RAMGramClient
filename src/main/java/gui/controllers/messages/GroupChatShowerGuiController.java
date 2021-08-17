package gui.controllers.messages;

import controllers.Controllers;
import gui.controllers.ImageController;
import gui.controllers.SceneLoader;
import gui.controllers.popups.AlertBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import gui.controllers.popups.messaging.AddMemberToGroupChat;
import models.LoggedUser;
import models.requests.AddContentRequest;
import models.requests.ChatInfoRequest;
import models.requests.LeaveGroupRequest;
import models.requests.ListRequest;
import models.responses.ChatInfoResponse;
import models.responses.ListResponse;
import models.responses.Response;

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

    private byte[] chosenImageByteArray = null;

    private static long groupId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Response response = new ChatInfoRequest(LoggedUser.getToken() , LoggedUser.getId() ,groupId).execute();
        groupNameLabel.setText(((ChatInfoResponse)response).getFrontUsername());
        ArrayList<String> membersNames = ((ChatInfoResponse)response).getMembersNames();
        membersLabel.setText(String.join("\n" , membersNames));
        loadMessages();

    }
    public void backButtonClicked(ActionEvent actionEvent) {
        SceneLoader.getInstance().messaging(actionEvent);
    }

    public void logoutButtonClicked(ActionEvent actionEvent) {
        SceneLoader.getInstance().logout(actionEvent);
    }

    public void mainMenuButtonClicked(ActionEvent actionEvent) {
        SceneLoader.getInstance().mainMenu(actionEvent);
    }

    public void sendButtonClicked(ActionEvent actionEvent) {
        String messageText = messageTextField.getText();
        if (messageText.equals("") && chosenImageByteArray == null){
            AlertBox.display("Nerd Alert" , "write something idiot");
        }
        else {
            new AddContentRequest(LoggedUser.getToken() , LoggedUser.getId() , AddContentRequest.TYPE.GROUP_MESSAGE , chosenImageByteArray ,messageText ,groupId,0L).execute();
            chosenImageView.setImage(null);
            messageTextField.clear();
            loadMessages();
        }
    }

    private void loadMessages() {
        Response infoResponse = new ChatInfoRequest(LoggedUser.getToken() , LoggedUser.getId() ,groupId).execute();
        groupNameLabel.setText(((ChatInfoResponse)infoResponse).getFrontUsername());
        VBox list = new VBox(5);
        Response response = new ListRequest(LoggedUser.getToken() , LoggedUser.getId() , ListRequest.TYPE.MESSAGE , groupId).execute();
        ArrayList<Long> messageIDs = ((ListResponse)response).getIds();
        for (Long messageID : messageIDs) {
            list.getChildren().add(new MessageCard(messageID).getCard());
        }
        messagesArea.setContent(list);
    }

    public static long getGroupId() {
        return groupId;
    }

    public static void setGroupId(long groupId) {
        GroupChatShowerGuiController.groupId = groupId;
    }

    public void addMemberButtonClicked(ActionEvent actionEvent) {
        if (AddMemberToGroupChat.display(groupId))
        {
            Response infoResponse = new ChatInfoRequest(LoggedUser.getToken() , LoggedUser.getId() ,groupId).execute();
            ArrayList<String> membersNames = ((ChatInfoResponse)infoResponse).getMembersNames();
            membersLabel.setText(String.join("\n" , membersNames));
        }

    }

    public void leaveButtonClicked(ActionEvent actionEvent) {
        new LeaveGroupRequest(LoggedUser.getToken(),LoggedUser.getId() , groupId).execute();
        SceneLoader.getInstance().messaging(actionEvent);

    }

    public void choosePicButtonClicked(ActionEvent actionEvent) {
        try {
            chosenImageByteArray = ImageController.pickImage();
            if (chosenImageByteArray != null){
                chosenImageView.setImage(ImageController.byteArrayToImage(chosenImageByteArray));
            }
        } catch (SizeLimitExceededException e) {
            AlertBox.display("too large" , "Image size too large");
        }
    }
}
