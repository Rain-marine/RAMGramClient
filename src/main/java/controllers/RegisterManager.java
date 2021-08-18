package controllers;

import models.requests.RegisterRequest;
import models.responses.BooleanResponse;
import models.responses.Response;

public class RegisterManager {

    public void makeNewUser(String fullName, String username, String password, String email, String phoneNumber, String bio, String birthday) {
        String[] info = new String[]{fullName, username, password, email,  phoneNumber, bio, birthday};
        new RegisterRequest(RegisterRequest.TYPE.INSERT, null, info).execute();
    }

    public boolean isUsernameAvailable(String username) {
        Response response = new RegisterRequest(RegisterRequest.TYPE.USERNAME, username, null).execute();
        return ((BooleanResponse) response).isResult();
    }

    public boolean isEmailAvailable(String email) {
        Response response = new RegisterRequest(RegisterRequest.TYPE.EMAIL, email, null).execute();
        return ((BooleanResponse) response).isResult();
    }

    public boolean isPhoneNumberAvailable(String phoneNumber) {
        Response response = new RegisterRequest(RegisterRequest.TYPE.NUMBER, phoneNumber, null).execute();
        return ((BooleanResponse) response).isResult();
    }
}
