package gui.controllers.mainmenu;

import controllers.Controllers;
import controllers.ProfileAccessController;
import controllers.UserController;
import gui.controllers.SceneLoader;
import gui.controllers.popups.AlertBox;
import gui.controllers.tweets.TweetCard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import models.LoggedUser;
import models.requests.ListRequest;
import models.responses.ListResponse;
import models.responses.Response;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ExplorerGuiController implements Initializable, Controllers {

    @FXML
    private TextField searchField;
    @FXML
    private ScrollPane tweetsArea;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<Long> listOfTweets = TWEET_CONTROLLER.getTopTweets();
        VBox list = new VBox(0);
        for (Long tweet : listOfTweets) {
            list.getChildren().add(new TweetCard(tweet, TweetCard.MODE.EXPLORER).getVBox());
        }
        tweetsArea.setContent(list);
    }

    public void backButtonClicked(ActionEvent actionEvent) {
        SceneLoader.getInstance().mainMenu(actionEvent);
    }

    public void logoutButtonClicked(ActionEvent actionEvent) {
        SceneLoader.getInstance().logout(actionEvent);
    }

    public void mainMenuButtonClicked(ActionEvent actionEvent) {
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
