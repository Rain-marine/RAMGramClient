package view.gui.controllers.mainmenu;

import controllers.Controllers;
import view.SceneLoader;
import view.gui.controllers.tweets.TweetCard;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import util.ConfigLoader;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TimeLineGuiController implements Initializable , Controllers{

    @FXML
    private ScrollPane tweetsArea;

    private PauseTransition timer;
    private ArrayList<Long> listOfTweets;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        timer = new PauseTransition(Duration.seconds(Integer.parseInt(ConfigLoader.readProperty("refreshTime"))));
        timer.setOnFinished(
                e -> {
                    updatePane();
                });
        updatePane();

    }

    private void updatePane() {
        System.out.println("updating...");
        VBox list = new VBox(0);
        listOfTweets = TWEET_CONTROLLER.getFollowingTweets();
        for (long tweet : listOfTweets) {
            list.getChildren().add(new TweetCard(tweet , TweetCard.MODE.TIMELINE).getVBox());
        }
        tweetsArea.setContent(list);
        timer.playFromStart();
    }

    public void logoutButtonClicked(ActionEvent actionEvent) {
        timer.stop();
        SceneLoader.getInstance().logout(actionEvent);
    }

    public void backButtonClicked(ActionEvent actionEvent) {
        timer.stop();
        SceneLoader.getInstance().mainMenu(actionEvent);
    }

    public void mainMenuButtonClicked(ActionEvent actionEvent) {
        timer.stop();
        SceneLoader.getInstance().mainMenu(actionEvent);
    }



}
