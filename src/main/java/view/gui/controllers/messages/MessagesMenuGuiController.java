package view.gui.controllers.messages;

import view.SceneLoader;
import view.popups.messaging.NewGroup;
import view.popups.messaging.SendNewMessage;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import models.LoggedUser;
import models.requests.ListRequest;
import models.responses.ListResponse;
import models.responses.Response;
import models.types.ListType;
import util.ConfigLoader;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MessagesMenuGuiController implements Initializable {

    @FXML
    private ScrollPane chatsArea;

    private PauseTransition timer;
    private Response response;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadChats();
    }

    private void loadChats() {
        ChatShowerGuiController.setPreviousMenu(ChatShowerGuiController.PREVIOUS.DEFAULT);
        timer = new PauseTransition(Duration.seconds(Integer.parseInt(ConfigLoader.readProperty("refreshTime"))));
        timer.setOnFinished(
                e -> {
                    updatePane();
                });
        updatePane();
    }

    private void updatePane() {
        try {
            response = new ListRequest(ListType.CHAT, 0L).execute();
            List<Long> chatIds = ((ListResponse) response).getIds();
            if (chatIds.size() == 0) {
                chatsArea.setContent(new Label("you have no chats"));
            } else {
                VBox list = new VBox(10);
                for (Long chatId : chatIds) {
                    list.getChildren().add(new ChatCard(chatId).getCard());
                }
                chatsArea.setContent(list);
            }
            timer.playFromStart();
        } catch (ClassCastException ignored) {

        }
    }

    public void backButtonClicked(ActionEvent actionEvent) {
        timer.stop();
        SceneLoader.getInstance().mainMenu(actionEvent);
    }

    public void logoutButtonClicked(ActionEvent actionEvent) {
        timer.stop();
        SceneLoader.getInstance().logout(actionEvent);
    }

    public void mainMenuButtonClicked(ActionEvent actionEvent) {
        timer.stop();
        SceneLoader.getInstance().mainMenu(actionEvent);
    }


    public void newChatButtonClicked(ActionEvent actionEvent) {
        SendNewMessage.display();
        SceneLoader.getInstance().messaging(actionEvent);
    }

    public void newGroupButtonClicked(ActionEvent actionEvent) {
        NewGroup.display();
        SceneLoader.getInstance().messaging(actionEvent);
    }

    public void savedMessagesButtonClicked(ActionEvent actionEvent) {
        SceneLoader.getInstance().changeScene(ConfigLoader.loadFXML("savedMessages"), actionEvent);
    }

    public void savedTweetsButtonClicked(ActionEvent actionEvent) {
        SceneLoader.getInstance().changeScene(ConfigLoader.loadFXML("savedTweets"), actionEvent);
    }
}
