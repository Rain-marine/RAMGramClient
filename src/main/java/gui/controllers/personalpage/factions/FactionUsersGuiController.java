package gui.controllers.personalpage.factions;

import controllers.ProfileAccessController;
import controllers.Controllers;
import gui.controllers.SceneLoader;
import gui.controllers.popups.factions.AddUserToFactionBox;
import gui.controllers.popups.SimpleConfirmBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import util.ConfigLoader;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class FactionUsersGuiController implements Initializable, Controllers {

    private static int factionID;

    @FXML
    private ScrollPane factionArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadUsers();


    }

    private void loadUsers() {
        HashMap<Long, String> members = FACTIONS_CONTROLLER.getGroupMembers(factionID);
        VBox list = new VBox(5);
        for (Long memberId : members.keySet()) {
            HBox userCard = new HBox(5);
            Label name = new Label(members.get(memberId));
            Button deleteUser = new Button("remove");
            deleteUser.setId(String.valueOf(memberId));
            deleteUser.setOnAction(event -> {
                FACTIONS_CONTROLLER.deleteUserFromFaction(factionID , Long.parseLong(deleteUser.getId()));
                loadUsers();
            });
            Button profile = new Button("profile");
            profile.setId(String.valueOf(memberId));
            profile.setOnAction(event -> {
                ProfileAccessController profileAccessController = new ProfileAccessController(ConfigLoader.getPreviousMenuCode("factions"), memberId, factionID);
                SceneLoader.getInstance().changeScene(profileAccessController.checkAccessibility(), event);
            });

            userCard.getChildren().addAll(name , deleteUser , profile);
            list.getChildren().add(userCard);
        }
        factionArea.setContent(list);
    }

    public void deleteFactionButtonClicked(ActionEvent actionEvent) {
        boolean answer = SimpleConfirmBox.display("confirmation" , "Are you sure to delete the faction?");
        if (answer){
            FACTIONS_CONTROLLER.deleteFaction(factionID);
            SceneLoader.getInstance().changeScene(ConfigLoader.loadFXML("factionList"),actionEvent);
        }

    }

    public void addMemberButtonClicked(ActionEvent actionEvent) {
        AddUserToFactionBox.display(factionID);
        loadUsers();
    }

    public void logoutButtonClicked(ActionEvent actionEvent) {
        SceneLoader.getInstance().logout(actionEvent);
    }

    public void backButtonClicked(ActionEvent actionEvent) {
        SceneLoader.getInstance().changeScene(ConfigLoader.loadFXML("factionList"),actionEvent);
    }

    public void mainMenuButtonClicked(ActionEvent actionEvent) {
        SceneLoader.getInstance().mainMenu(actionEvent);
    }

    public static int getFactionID() {
        return factionID;
    }

    public static void setFactionID(int factionID) {
        FactionUsersGuiController.factionID = factionID;
    }
}
