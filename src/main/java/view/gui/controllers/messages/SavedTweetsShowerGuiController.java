package view.gui.controllers.messages;

import view.SceneLoader;
import view.gui.controllers.tweets.TweetCard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import models.LoggedUser;
import models.requests.ListRequest;
import models.responses.ListResponse;
import models.responses.Response;
import models.types.ListType;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SavedTweetsShowerGuiController implements Initializable {

    @FXML
    private ScrollPane messagesArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadMessages();
    }

    private void loadMessages() {
        VBox list = new VBox(0);
        Response response = new ListRequest(LoggedUser.getToken() , LoggedUser.getId() , ListType.SAVED_TWEET , 0L).execute();
        ArrayList<Long> tweetIDs = ((ListResponse)response).getIds();
        for (Long tweetId : tweetIDs) {
            list.getChildren().add(new TweetCard(tweetId , TweetCard.MODE.PROFILE).getVBox());
        }
        messagesArea.setContent(list);
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


}
