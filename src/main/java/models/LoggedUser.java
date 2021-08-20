package models;

import models.requests.UpdateLoggedUserRequest;
import models.responses.LoggedUserResponse;
import models.responses.Response;
import models.trimmed.TrimmedLoggedUser;

public class LoggedUser {
    private static TrimmedLoggedUser trimmedLoggedUser;
    private static String token;
    private static Mode mode;

    public enum Mode {ONLINE , OFFLINE}

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

    public static Mode getMode() {
        return mode;
    }

    public static void setMode(Mode mode) {
        LoggedUser.mode = mode;
    }
}
