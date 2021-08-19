package gui.controllers.messages;

import controllers.ProfileAccessController;
import gui.controllers.ImageController;
import gui.controllers.SceneLoader;
import gui.controllers.popups.AlertBox;
import gui.controllers.popups.messaging.SendLinkOrAdd;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import models.LoggedUser;
import models.requests.AddContentRequest;
import models.requests.ChatInfoRequest;
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

public class ChatShowerGuiController implements Initializable {

    @FXML
    private ScrollPane messagesArea;
    @FXML
    private ImageView profileImageview;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label lastSeenLabel;
    @FXML
    private Button sendBtn;
    @FXML
    private Button choosePicBtn;
    @FXML
    private TextField messageTextField;
    @FXML
    private ImageView chosenImageView;

    private PauseTransition timer;
    private byte[] chosenImageByteArray = null;
    private ArrayList<Long> messageIDs;
    private Response response;
    private Response messageResponse;


    public enum PREVIOUS {DEFAULT, PROFILE}

    private static long chatId;
    private static PREVIOUS previousMenu;
    private static ProfileAccessController profileAccessController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        timer = new PauseTransition(Duration.seconds(Integer.parseInt(ConfigLoader.readProperty("refreshTime"))));
        timer.setOnFinished(
                e -> {
                    loadMessages();
                });
        loadMessages();
    }

    private void loadMessages() {
        timer.stop();
        response = new ChatInfoRequest(LoggedUser.getToken(), LoggedUser.getId(), chatId).execute();
        usernameLabel.setText(((ChatInfoResponse) response).getFrontUsername());
        profileImageview.setImage(ImageController.byteArrayToImage(((ChatInfoResponse) response).getFrontProfile()));
        lastSeenLabel.setText(((ChatInfoResponse) response).getLastSeen());

        VBox list = new VBox(5);
        messageResponse = new ListRequest(LoggedUser.getToken(), LoggedUser.getId(), ListType.MESSAGE, chatId).execute();
        messageIDs = ((ListResponse) messageResponse).getIds();
        for (Long messageID : messageIDs) {
            list.getChildren().add(new MessageCard(messageID).getCard());
        }
        messagesArea.setContent(list);
        timer.playFromStart();
    }

    public void backButtonClicked(ActionEvent actionEvent) {
        timer.stop();
        switch (previousMenu) {
            case DEFAULT -> SceneLoader.getInstance().messaging(actionEvent);
            case PROFILE -> SceneLoader.getInstance().changeScene(profileAccessController.checkAccessibility(), actionEvent);
        }
    }

    public void logoutButtonClicked(ActionEvent actionEvent) {
        timer.stop();
        SceneLoader.getInstance().logout(actionEvent);
    }

    public void mainMenuButtonClicked(ActionEvent actionEvent) {
        timer.stop();
        SceneLoader.getInstance().mainMenu(actionEvent);
    }

    public static long getChatId() {
        return chatId;
    }

    public static void setChatId(long chatId) {
        ChatShowerGuiController.chatId = chatId;
    }

    public void sendButtonClicked(ActionEvent actionEvent) {
        String messageText = messageTextField.getText();
        if (messageText.equals("") && chosenImageByteArray == null) {
            AlertBox.display("Nerd Alert", "write something idiot");
        } else {
            new AddContentRequest(LoggedUser.getToken(), LoggedUser.getId(), AddContentType.MESSAGE, chosenImageByteArray, messageText, chatId, 0L).execute();
            chosenImageView.setImage(null);
            messageTextField.clear();
            loadMessages();
        }
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

    public void getLinkButtonClicked(ActionEvent actionEvent) {
        SendLinkOrAdd.display(chatId , SendLinkOrAdd.Mode.LINK);
    }


    public static PREVIOUS getPreviousMenu() {
        return previousMenu;
    }

    public static void setPreviousMenu(PREVIOUS previousMenu) {
        ChatShowerGuiController.previousMenu = previousMenu;
    }

    public static ProfileAccessController getProfileAccessController() {
        return profileAccessController;
    }

    public static void setProfileAccessController(ProfileAccessController profileAccessController) {
        ChatShowerGuiController.profileAccessController = profileAccessController;
    }
}
