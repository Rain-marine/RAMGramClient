package controllers;

import models.LoggedUser;
import models.requests.NotificationRequest;
import models.requests.UserActionRequest;
import models.responses.NotificationResponse;
import models.responses.Response;
import models.trimmed.TrimmedNotification;
import models.types.UserActionType;

import java.util.List;


public class NotificationController {

    public NotificationController() {
    }

    public List<TrimmedNotification> getFollowingRequestsNotifications() {
        Response response = new NotificationRequest(LoggedUser.getToken(),LoggedUser.getId() , NotificationRequest.TYPE.REQ_TO_ME).execute();
        return ((NotificationResponse)response).getTrimmedNotifications();
    }

    public List<TrimmedNotification> getYourFollowingRequestNotification() {
        Response response = new NotificationRequest(LoggedUser.getToken(),LoggedUser.getId() , NotificationRequest.TYPE.REQUESTS).execute();
        return ((NotificationResponse)response).getTrimmedNotifications();
    }

    public List<TrimmedNotification> getSystemNotification() {
        Response response = new NotificationRequest(LoggedUser.getToken(),LoggedUser.getId() , NotificationRequest.TYPE.SYSTEM).execute();
        return ((NotificationResponse)response).getTrimmedNotifications();
    }

    public void acceptFollowRequest(long notification) {
        new UserActionRequest(LoggedUser.getToken() , LoggedUser.getId() , notification , UserActionType.ACCEPT).execute();

    }

    public void rejectFollowRequestWithNotification(long notification) {
        new UserActionRequest(LoggedUser.getToken() , LoggedUser.getId() , notification , UserActionType.REJECT).execute();

    }

    public void rejectFollowRequestWithoutNotification(long notification) {
        new UserActionRequest(LoggedUser.getToken() , LoggedUser.getId() , notification , UserActionType.QUIET_REJECT).execute();

    }

    public void deleteNotification(long notification) {
        new UserActionRequest(LoggedUser.getToken() , LoggedUser.getId() , notification , UserActionType.DELETE_NOTIF).execute();

    }

}