package gui.controllers.mainmenu;

import controllers.Controllers;
import controllers.ProfileAccessController;
import controllers.UserController;
import gui.controllers.SceneLoader;
import gui.controllers.popups.AlertBox;
import gui.controllers.tweets.TweetCard;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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

public class ExplorerGuiController implements Initializable, Controllers {

    @FXML
    private TextField searchField;
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
        listOfTweets = TWEET_CONTROLLER.getTopTweets();
        VBox list = new VBox(0);
        for (Long tweet : listOfTweets) {
            list.getChildren().add(new TweetCard(tweet, TweetCard.MODE.EXPLORER).getVBox());
        }
        tweetsArea.setContent(list);
        timer.playFromStart();

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


    public void searchButtonClicked(ActionEvent actionEvent) {
        String usernameToFind = searchField.getText();
        if (usernameToFind.equals("")) {
            AlertBox.display("Nerd Alert", "You gotta enter a name idiot!");
        } else {
            try {
                long userId = new UserController().getUserByUsername(usernameToFind);
                ProfileAccessController profileAccessController = new ProfileAccessController(1, userId, 0);
                SceneLoader.getInstance().changeScene(profileAccessController.checkAccessibility(), actionEvent);

            } catch (NullPointerException nullPointerException) {
                AlertBox.display("404", "user not found");
            }

        }
    }

}
