package controllers;

import models.Group;
import models.LoggedUser;
import models.User;
import models.requests.ExploreRequest;
import models.requests.UserActionRequest;
import models.responses.ExploreResponse;
import models.responses.Response;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import repository.Repository;

import java.util.Date;

public class UserController implements Repository {
    final RegisterManager REGISTER_MANAGER ;
    private final static Logger log = LogManager.getLogger(UserController.class);


    public UserController() {
        REGISTER_MANAGER = new RegisterManager();
    }

    public void blockUser(long userToBlockId) {
        new UserActionRequest(LoggedUser.getToken() , LoggedUser.getId() , userToBlockId , UserActionRequest.USER_ACTION.BLOCK).execute();
    }

    public long getUserByUsername(String usernameToFind) throws NullPointerException {
        Response response = new ExploreRequest(LoggedUser.getToken() , LoggedUser.getId() , usernameToFind).execute();
        if (((ExploreResponse)response).getFoundUserId() == 0){
            throw new NullPointerException();
        }
        return ((ExploreResponse)response).getFoundUserId();
    }

    public void reportUser(long userId) {
        new UserActionRequest(LoggedUser.getToken() , LoggedUser.getId() , userId , UserActionRequest.USER_ACTION.REPORT).execute();

    }

    public boolean ChangeUsername(String newUsername) {
        if (REGISTER_MANAGER.isUsernameAvailable(newUsername)) {
            USER_REPOSITORY.changeUsername(LoggedUser.getLoggedUser().getId(), newUsername);
            log.info(LoggedUser.getLoggedUser().getId() + " user name was changed to "+ newUsername);
            return true;
        }
        return false;
    }

    public void changeBio(String newBio) {
        USER_REPOSITORY.changeBio(LoggedUser.getLoggedUser().getId(), newBio);
    }

    public void changeName(String newName) {
        USER_REPOSITORY.changeFullName(LoggedUser.getLoggedUser().getId(), newName);
    }


    public boolean changeEmail(String newEmail) {
        if (REGISTER_MANAGER.isEmailAvailable(newEmail)) {
            USER_REPOSITORY.changeEmail(LoggedUser.getLoggedUser().getId(), newEmail);
            log.info(LoggedUser.getLoggedUser().getId() + " email name was changed to "+ newEmail);

            return true;
        }
        return false;
    }

    public boolean changeNumber(String newNumber) {
        if(REGISTER_MANAGER.isPhoneNumberAvailable(newNumber)) {
            USER_REPOSITORY.changePhoneNumber(LoggedUser.getLoggedUser().getId(), newNumber);
            return true;
        }
        return false;
    }

    public void unblockUser(long userId) {
        new UserActionRequest(LoggedUser.getToken() , LoggedUser.getId() , userId , UserActionRequest.USER_ACTION.UNBLOCK).execute();

    }


    public void changeProfilePhoto(byte[] newPhoto){
        USER_REPOSITORY.changeProfilePhoto(LoggedUser.getLoggedUser().getId(), newPhoto);
    }

    public User getById(long id) {
        return USER_REPOSITORY.getById(id);
    }

    public String getUsername(long frontUserID) {
        return  USER_REPOSITORY.getById(frontUserID).getUsername();
    }

    public byte[] getProfilePhoto(long frontUserID) {
        return USER_REPOSITORY.getById(frontUserID).getProfilePhoto();

    }

    public String getUserBio(long userId) {
        return USER_REPOSITORY.getById(userId).getBio();
    }

    public String UserFullName(long userId) {
        return USER_REPOSITORY.getById(userId).getFullName();
    }
}
