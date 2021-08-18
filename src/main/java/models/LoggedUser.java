package models;

import controllers.UserController;
import models.requests.UpdateLoggedUserRequest;
import models.responses.LoggedUserResponse;
import models.responses.Response;
import models.trimmed.TrimmedLoggedUser;

import java.time.format.ResolverStyle;

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
        Response response = new UpdateLoggedUserRequest(LoggedUser.getId()).execute();
        setTrimmedLoggedUser(((LoggedUserResponse)response).getTrimmedLoggedUser());
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

    public static long getId() {
        return trimmedLoggedUser.getId();
    }

    public static String getUsername() {
        return trimmedLoggedUser.getUsername();
    }
}
