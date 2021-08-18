package controllers;


import models.LoggedUser;
import models.requests.LogoutRequest;


public class SettingController{

    public SettingController() {
    }

    public void logout() {
        new LogoutRequest(LoggedUser.getToken() , LoggedUser.getId()).execute();
        LoggedUser.setToken(null);
    }


}
