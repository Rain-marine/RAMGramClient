package controllers;

import models.LoggedUser;
import models.requests.ChangeInfoRequest;
import models.requests.ExploreRequest;
import models.requests.UserActionRequest;
import models.responses.BooleanResponse;
import models.responses.ExploreResponse;
import models.responses.Response;

public class UserController{

    public UserController() {
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
        Response response = new ChangeInfoRequest(LoggedUser.getToken() , LoggedUser.getId() , ChangeInfoRequest.TYPE.USERNAME , newUsername).execute();
        return ((BooleanResponse)response).isResult();
    }

    public void changeBio(String newBio) {
        new ChangeInfoRequest(LoggedUser.getToken() , LoggedUser.getId() , ChangeInfoRequest.TYPE.BIO , newBio).execute();
    }

    public void changeName(String newName) {
        new ChangeInfoRequest(LoggedUser.getToken() , LoggedUser.getId() , ChangeInfoRequest.TYPE.FULL_NAME ,newName).execute();
    }

    public boolean changeEmail(String newEmail) {
        Response response = new ChangeInfoRequest(LoggedUser.getToken() , LoggedUser.getId() , ChangeInfoRequest.TYPE.EMAIL , newEmail).execute();
        return ((BooleanResponse)response).isResult();
    }

    public boolean changeNumber(String newNumber) {
        Response response = new ChangeInfoRequest(LoggedUser.getToken() , LoggedUser.getId() , ChangeInfoRequest.TYPE.NUMBER , newNumber).execute();
        return ((BooleanResponse)response).isResult();
    }

    public void unblockUser(long userId) {
        new UserActionRequest(LoggedUser.getToken() , LoggedUser.getId() , userId , UserActionRequest.USER_ACTION.UNBLOCK).execute();
    }

    public void changeProfilePhoto(byte[] newPhoto){
        new ChangeInfoRequest(LoggedUser.getToken() , LoggedUser.getId() , ChangeInfoRequest.TYPE.PROFILE , newPhoto).execute();
    }

}
