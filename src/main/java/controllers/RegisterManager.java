package controllers;

import models.requests.RegisterRequest;
import models.responses.BooleanResponse;
import models.responses.Response;
import models.types.RegisterType;

public class RegisterManager {

    public void makeNewUser(String fullName, String username, String password, String email, String phoneNumber, String bio, String birthday) {
        String[] info = new String[]{fullName, username, password, email,  phoneNumber, bio, birthday};
        new RegisterRequest(RegisterType.INSERT, null, info).execute();
    }

    public boolean isUsernameAvailable(String username) {
        return (Boolean)  new RegisterRequest(RegisterType.USERNAME, username, null).execute().unleash();
    }

    public boolean isEmailAvailable(String email) {
        return (Boolean) new RegisterRequest(RegisterType.EMAIL, email, null).execute().unleash();
    }

    public boolean isPhoneNumberAvailable(String phoneNumber) {
        return (Boolean) new RegisterRequest(RegisterType.NUMBER, phoneNumber, null).execute().unleash();
    }
}
