package controllers;


import models.LoggedUser;
import models.requests.LogoutRequest;
import util.Save;


public class SettingController{

    public SettingController() {
    }

    public void logout() {
        LoggedUser.update();
        new LogoutRequest(LoggedUser.getToken() , LoggedUser.getId()).execute();
        Save.getInstance().saveLoggedUser();
        LoggedUser.setToken(null);
    }


}
