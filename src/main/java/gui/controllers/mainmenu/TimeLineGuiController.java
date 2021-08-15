package gui.controllers.mainmenu;

import controllers.Controllers;
import gui.controllers.SceneLoader;
import gui.controllers.tweets.TweetCard;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import models.LoggedUser;
import models.requests.ListRequest;
import models.responses.ListResponse;
import models.responses.Response;
import util.ConfigLoader;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TimeLineGuiController implements Initializable {

    @FXML
    private ScrollPane tweetsArea;

    private PauseTransition timer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updatePane();
//        timer = new PauseTransition(Duration.seconds(5));
//        timer.setOnFinished(
//                e -> {
//                    updatePane();
//                });
//        timer.playFromStart();

    }

    private void updatePane() {
        System.out.println("updating...");
        VBox list = new VBox(0);
        Response response = new ListRequest(LoggedUser.getToken() , LoggedUser.getId() , ListRequest.TYPE.TIMELINE , 0L).execute();
        ArrayList<Long> listOfTweets = ((ListResponse) response).getIds();
        for (long tweet : listOfTweets) {
            list.getChildren().add(new TweetCard(tweet , TweetCard.MODE.TIMELINE).getVBox());
        }
        tweetsArea.setContent(list);
//        timer.playFromStart();
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
