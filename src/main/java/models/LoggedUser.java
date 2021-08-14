package models;

import controllers.UserController;
import models.trimmed.TrimmedLoggedUser;

public class LoggedUser {
    private static User loggedUser;
    private static TrimmedLoggedUser trimmedLoggedUser;
    private static final UserController USER_CONTROLLER = new UserController();
    private static String token;

    public static User getLoggedUser() {
        return loggedUser;
    }

    public static void setLoggedUser(User loggedUser) {
        LoggedUser.loggedUser = loggedUser;
    }

    public static void update() {
        loggedUser = USER_CONTROLLER.getById(loggedUser.getId());
    }

    public static TrimmedLoggedUser getTrimmedLoggedUser() {
        return trimmedLoggedUser;
    }

    public static void setTrimmedLoggedUser(TrimmedLoggedUser trimmedLoggedUser) {
        LoggedUser.trimmedLoggedUser = trimmedLoggedUser;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        LoggedUser.token = token;
    }
}
