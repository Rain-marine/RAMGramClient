package controllers;

import view.gui.controllers.profiles.*;
import models.LoggedUser;
import models.requests.PermissionRequest;
import models.responses.PermissionResponse;
import models.responses.Response;

public class ProfileAccessController {

    private final long loggedUserId;
    private final int previousMenu;
    private final long otherUserId;
    private final int factionId;
    private boolean[] results;

    public ProfileAccessController(int previousMenu, long otherUserID, int factionId) {
        this.previousMenu = previousMenu;
        this.loggedUserId = LoggedUser.getId();
        this.otherUserId = otherUserID;
        this.factionId = factionId;
        Response response = new PermissionRequest( otherUserID).execute();
        this.results = ((PermissionResponse) response).getPermissions();
    }

    public String checkAccessibility() {
        //is it myself? :)
        if (otherUserId == loggedUserId)
            return "FXMLs/PersonalPage/PersonalPageMenu.fxml";
        //is Active>
        if (!results[0]) {
            return "FXMLs/Profiles/NotVisibleProfile.fxml";
        } else {
            //have I blocked them?

            if (results[1]) {
                BlockedProfileGuiController.setUser(otherUserId);
                BlockedProfileGuiController.setPrevious(previousMenu);
                BlockedProfileGuiController.setFactionId(factionId);
                BlockedProfileGuiController.setProfileAccessController(this);
                return "FXMLs/Profiles/BlockedProfile.fxml";
            }
            //am I following them?

            if (results[2]) {
                FollowingProfileGuiController.setUser(otherUserId);
                FollowingProfileGuiController.setPrevious(previousMenu);
                FollowingProfileGuiController.setFactionId(factionId);
                FollowingProfileGuiController.setProfileAccessController(this);
                return "FXMLs/Profiles/FollowingProfile.fxml";
            }

            //am I blocked?
            if (results[3]) {
                return "FXMLs/Profiles/NotVisibleProfile.fxml";
            }

            //is their account public?
            if (results[4]) {
                PublicProfileGuiController.setUser(otherUserId);
                PublicProfileGuiController.setPrevious(previousMenu);
                PublicProfileGuiController.setProfileAccessController(this);
                return "FXMLs/Profiles/PublicProfile.fxml";
            }

            //have I sent request?
            if (results[5]) {
                PendingRequestProfileGuiController.setPrevious(previousMenu);
                PendingRequestProfileGuiController.setUser(otherUserId);
                return "FXMLs/Profiles/PendingRequestProfile.fxml";

            }
            PrivateProfileGuiController.setUser(otherUserId);
            PrivateProfileGuiController.setPrevious(previousMenu);
            return "FXMLs/Profiles/PrivateProfile.fxml";


        }
    }

    public int getPreviousMenu() {
        return previousMenu;
    }

    public long getOtherUserId() {
        return otherUserId;
    }

    public int getFactionId() {
        return factionId;
    }

    public boolean[] getResults() {
        return results;
    }

    public void setResults(boolean[] results) {
        this.results = results;
    }
}
