package view.gui.controllers.tweets;

import view.ImageController;
import view.SceneLoader;
import view.popups.AlertBox;
import view.popups.SimpleConfirmBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import models.LoggedUser;
import models.requests.AddContentRequest;
import models.types.AddContentType;

import javax.naming.SizeLimitExceededException;

public class NewTweetGuiController{

    @FXML
    private TextField tweetText;
    @FXML
    private ImageView image;

    private byte[] tweetImage = null;

    public void mainMenuButtonClicked(ActionEvent actionEvent) {
        SceneLoader.getInstance().mainMenu(actionEvent);
    }

    public void backButtonClicked(ActionEvent actionEvent) {
        SceneLoader.getInstance().personalPage(actionEvent);
    }

    public void logoutButtonClicked(ActionEvent actionEvent) {
        SceneLoader.getInstance().logout(actionEvent);
    }

    public void postButtonClicked(ActionEvent actionEvent) {
        if (!tweetText.getText().equals("") || tweetImage != null){
            boolean answer = SimpleConfirmBox.display("confirmation" , "Are you sure?");
            if (answer){
                new AddContentRequest(AddContentType.TWEET , tweetImage ,tweetText.getText() ,0L ,0L).execute();
                AlertBox.display("Done!", "Tweet posted");
                tweetText.setText("");
                image.setImage(null);
            }

        }
        else{
            AlertBox.display("Nerd Alert", "you haven't written anything");
        }
    }

    public void addImageButtonClicked(ActionEvent actionEvent) {
        try {
            tweetImage = ImageController.pickImage();
            image.setImage(ImageController.byteArrayToImage(tweetImage));
        } catch (SizeLimitExceededException e) {
            AlertBox.display("too large!", "image size is too large");
        }
    }
}
