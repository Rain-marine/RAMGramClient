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
        new UserActionRequest( userToBlockId , UserActionType.BLOCK).execute();
    }

    public long getUserByUsername(String usernameToFind) throws NullPointerException {
        Response response = new ExploreRequest(usernameToFind).execute();
        if (((ExploreResponse)response).getFoundUserId() == 0){
            throw new NullPointerException();
        }
        return ((ExploreResponse)response).getFoundUserId();
    }

    public void reportUser(long userId) {
        new UserActionRequest( userId , UserActionType.REPORT).execute();

    }

    public boolean ChangeUsername(String newUsername) {
        return (Boolean)  new ChangeInfoRequest( ChangeInfoType.USERNAME , newUsername).execute().unleash();
    }

    public void changeBio(String newBio) {
        new ChangeInfoRequest(ChangeInfoType.BIO , newBio).execute();
    }

    public void changeName(String newName) {
        new ChangeInfoRequest(ChangeInfoType.FULL_NAME ,newName).execute();
    }

    public boolean changeEmail(String newEmail) {
        return (Boolean)  new ChangeInfoRequest(ChangeInfoType.EMAIL , newEmail).execute().unleash();
    }

    public boolean changeNumber(String newNumber) {
        return (Boolean)  new ChangeInfoRequest( ChangeInfoType.NUMBER , newNumber).execute().unleash();
    }

    public void unblockUser(long userId) {
        new UserActionRequest( userId , UserActionType.UNBLOCK).execute();
    }

    public void changeProfilePhoto(byte[] newPhoto){
        new ChangeInfoRequest(ChangeInfoType.PROFILE , newPhoto).execute();
    }

}
