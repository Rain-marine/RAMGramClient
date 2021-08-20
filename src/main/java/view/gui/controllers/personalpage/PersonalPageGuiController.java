package view.gui.controllers.personalpage;

import controllers.DateFormat;
import controllers.UserController;
import controllers.Controllers;
import javafx.scene.control.Button;
import view.ImageController;
import view.SceneLoader;
import view.popups.AlertBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import models.LoggedUser;
import util.ConfigLoader;

import javax.naming.SizeLimitExceededException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class PersonalPageGuiController implements Initializable, Controllers {


    @FXML
    private javafx.scene.control.TextField usernameTextField;
    @FXML
    private javafx.scene.control.TextField nameTextField;
    @FXML
    private javafx.scene.control.TextField emailTextField;
    @FXML
    private javafx.scene.control.TextField phoneNumberTextField;
    @FXML
    private Label birthdayTextLabel;
    @FXML
    private javafx.scene.control.TextField bioTextField;
    @FXML
    private Label usernameEdit;
    @FXML
    private Label phoneNumberEdit;
    @FXML
    private Label emailEdit;
    @FXML
    private ImageView profilePhotoImage;
    @FXML
    private Button factionsButton;
    @FXML
    private Button yourTweetsButton;
    @FXML
    private Button newTweetButton;
    @FXML
    private Button notificationButton;
    @FXML
    private Button logoutButton;

    private String username;
    private String fullName;
    private String bio;
    private String phoneNumber;
    private String email;
    private Date birthday;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Loading personal page");
        loadInfo();
        usernameTextField.setFocusTraversable(false);
        nameTextField.setFocusTraversable(false);
        emailTextField.setFocusTraversable(false);
        phoneNumberTextField.setFocusTraversable(false);
        bioTextField.setFocusTraversable(false);
        if (LoggedUser.getMode() == LoggedUser.Mode.ONLINE)
            LoggedUser.update();
        else {
            yourTweetsButton.setDisable(true);
            newTweetButton.setDisable(true);
            factionsButton.setDisable(true);
            notificationButton.setDisable(true);
            logoutButton.setDisable(true);
        }
    }

    private void loadInfo() {
        username = LoggedUser.getTrimmedLoggedUser().getUsername();
        fullName = LoggedUser.getTrimmedLoggedUser().getFullName();
        email = LoggedUser.getTrimmedLoggedUser().getEmail();
        phoneNumber = LoggedUser.getTrimmedLoggedUser().getPhoneNumber();
        bio = LoggedUser.getTrimmedLoggedUser().getBio();
        birthday = LoggedUser.getTrimmedLoggedUser().getBirthday();
        byte[] byteArray = LoggedUser.getTrimmedLoggedUser().getProfilePic();

        Rectangle clip = new Rectangle(
                profilePhotoImage.getFitWidth(), profilePhotoImage.getFitHeight()
        );
        clip.setArcWidth(1000);
        clip.setArcHeight(1000);
        profilePhotoImage.setClip(clip);

        profilePhotoImage.setImage(ImageController.byteArrayToImage(byteArray));
        usernameTextField.setText(username);
        nameTextField.setText(fullName);
        emailTextField.setText(email);
        phoneNumberTextField.setText(phoneNumber.equals("") ? "not set" : phoneNumber);
        bioTextField.setText(bio);
        birthdayTextLabel.setText(birthday == null ? "not set" : DateFormat.dayMonthYear(birthday));
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

    public void updateInfoButtonClicked(ActionEvent actionEvent) {
        System.out.println("updating info...");
        usernameEdit.setText("");
        emailEdit.setText("");
        phoneNumberEdit.setText("");

        String newUsername = usernameTextField.getText();
        if (!newUsername.equals(username)) {
            if (LoggedUser.getMode() == LoggedUser.Mode.OFFLINE)
                usernameEdit.setText("can't change username in offline mode");
            else {
                if (!USER_CONTROLLER.ChangeUsername(newUsername)) {
                    usernameEdit.setText("username already exists");
                }
            }
        }

        String newEmail = emailTextField.getText();
        if (!newEmail.equals(email)) {
            if (LoggedUser.getMode() == LoggedUser.Mode.OFFLINE)
                emailEdit.setText("can't change email in offline mode");
            else {
                if (!newEmail.contains("@") || !newEmail.contains(".")) {
                    emailEdit.setText("invalid email address");
                } else if (!USER_CONTROLLER.changeEmail(newEmail)) {
                    emailEdit.setText("email already exists");
                }
            }
        }

        String newName = nameTextField.getText();
        if (!newName.equals(fullName)) {
            USER_CONTROLLER.changeName(newName);
            LoggedUser.getTrimmedLoggedUser().setFullName(newName);
        }

        String newNumber = phoneNumberTextField.getText();
        try {
            Integer.parseInt(newNumber);
            if (!newNumber.equals(phoneNumber)) {
                if (LoggedUser.getMode() == LoggedUser.Mode.OFFLINE)
                    phoneNumberEdit.setText("can't change number in offline mode");
                else {
                    if (!USER_CONTROLLER.changeNumber(newNumber)) {
                        phoneNumberEdit.setText("phone number already exists");
                    }
                }
            }

        } catch (NumberFormatException e) {
            if (!(phoneNumber.equals(""))) {
                phoneNumberEdit.setText("invalid format");
            }
        }

        String newBio = bioTextField.getText();
        if (!newBio.equals(bio)) {
            USER_CONTROLLER.changeBio(newBio);
            LoggedUser.getTrimmedLoggedUser().setBio(newBio);
        }
        if (LoggedUser.getMode() == LoggedUser.Mode.ONLINE)
            LoggedUser.update();
        loadInfo();


    }

    public void newTweetButtonClicked(ActionEvent actionEvent) {
        SceneLoader.getInstance().changeScene(ConfigLoader.loadFXML("newTweet"), actionEvent);
    }

    public void yourTweetsButtonClicked(ActionEvent actionEvent) {
        SceneLoader.getInstance().yourTweets(actionEvent);
    }

    public void factionsButtonClicked(ActionEvent actionEvent) {
        SceneLoader.getInstance().changeScene(ConfigLoader.loadFXML("factionList"), actionEvent);
    }

    public void notificationButtonClicked(ActionEvent actionEvent) {
        SceneLoader.getInstance().changeScene(ConfigLoader.loadFXML("notificationAdd"), actionEvent);
    }


    public void changePhotoButtonClicked(ActionEvent actionEvent) {
        byte[] byteArray;
        try {
            byteArray = ImageController.pickImage();
            USER_CONTROLLER.changeProfilePhoto(byteArray);
            LoggedUser.update();
            loadInfo();
        } catch (SizeLimitExceededException e) {
            AlertBox.display("size limit error", "Image size is too large. \nImage size should be less than 2MB");
        }
    }
}
