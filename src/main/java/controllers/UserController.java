package controllers;

import models.LoggedUser;
import models.requests.ChangeInfoRequest;
import models.requests.ExploreRequest;
import models.requests.UserActionRequest;
import models.responses.BooleanResponse;
import models.responses.ExploreResponse;
import models.responses.Response;
import models.types.ChangeInfoType;
import models.types.UserActionType;

public class UserController{

    public UserController() {
    }

    public void blockUser(long userToBlockId) {
        new UserActionRequest(LoggedUser.getToken() , LoggedUser.getId() , userToBlockId , UserActionType.BLOCK).execute();
    }

    public long getUserByUsername(String usernameToFind) throws NullPointerException {
        Response response = new ExploreRequest(LoggedUser.getToken() , LoggedUser.getId() , usernameToFind).execute();
        if (((ExploreResponse)response).getFoundUserId() == 0){
            throw new NullPointerException();
        }
        return ((ExploreResponse)response).getFoundUserId();
    }

    public void reportUser(long userId) {
        new UserActionRequest(LoggedUser.getToken() , LoggedUser.getId() , userId , UserActionType.REPORT).execute();

    }

    public boolean ChangeUsername(String newUsername) {
        Response response = new ChangeInfoRequest(LoggedUser.getToken() , LoggedUser.getId() , ChangeInfoType.USERNAME , newUsername).execute();
        return ((BooleanResponse)response).isResult();
    }

    public void changeBio(String newBio) {
        new ChangeInfoRequest(LoggedUser.getToken() , LoggedUser.getId() , ChangeInfoType.BIO , newBio).execute();
    }

    public void changeName(String newName) {
        new ChangeInfoRequest(LoggedUser.getToken() , LoggedUser.getId() , ChangeInfoType.FULL_NAME ,newName).execute();
    }

    public boolean changeEmail(String newEmail) {
        Response response = new ChangeInfoRequest(LoggedUser.getToken() , LoggedUser.getId() , ChangeInfoType.EMAIL , newEmail).execute();
        return ((BooleanResponse)response).isResult();
    }

    public boolean changeNumber(String newNumber) {
        Response response = new ChangeInfoRequest(LoggedUser.getToken() , LoggedUser.getId() , ChangeInfoType.NUMBER , newNumber).execute();
        return ((BooleanResponse)response).isResult();
    }

    public void unblockUser(long userId) {
        new UserActionRequest(LoggedUser.getToken() , LoggedUser.getId() , userId , UserActionType.UNBLOCK).execute();
    }

    public void changeProfilePhoto(byte[] newPhoto){
        new ChangeInfoRequest(LoggedUser.getToken() , LoggedUser.getId() , ChangeInfoType.PROFILE , newPhoto).execute();
    }

}
