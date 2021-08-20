package controllers;


import models.LoggedUser;
import models.requests.LogoutRequest;
import util.Save;


public class SettingController {

    public SettingController() {
    }

    public void logout() {
        if (LoggedUser.getMode() == LoggedUser.Mode.ONLINE){
            LoggedUser.update();
            new LogoutRequest().execute();
        }
        Save.getInstance().saveLoggedUser();
        LoggedUser.setToken(null);
    }


    public void logoutDeleted() {
        LoggedUser.setToken(null);
    }
}
