package gui.controllers.mainmenu;

import controllers.Controllers;
import gui.controllers.SceneLoader;
import gui.controllers.tweets.TweetCard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import models.LoggedUser;
import models.requests.ListRequest;
import models.responses.ListResponse;
import models.responses.Response;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TimeLineGuiController implements Initializable {

    @FXML
    private ScrollPane tweetsArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        VBox list = new VBox(0);
        Response response = new ListRequest(LoggedUser.getToken() , LoggedUser.getId() , ListRequest.TYPE.TIMELINE , 0L).execute();
        ArrayList<Long> listOfTweets = ((ListResponse) response).getIds();
        for (long tweet : listOfTweets) {
            list.getChildren().add(new TweetCard(tweet , TweetCard.MODE.TIMELINE).getVBox());
        }
        tweetsArea.setContent(list);
    }

    public void logoutButtonClicked(ActionEvent actionEvent) {
        SceneLoader.getInstance().logout(actionEvent);
    }

    public void backButtonClicked(ActionEvent actionEvent) {
        SceneLoader.getInstance().mainMenu(actionEvent);
    }

    public void mainMenuButtonClicked(ActionEvent actionEvent) {
        SceneLoader.getInstance().mainMenu(actionEvent);
    }



}
