package controllers;

import models.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import repository.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class NotificationController implements Repository {
    private final static Logger log = LogManager.getLogger(NotificationController.class);

    public NotificationController() {
    }

    public List<Notification> getFollowingRequestsNotifications() {
        User user = USER_REPOSITORY.getById(LoggedUser.getLoggedUser().getId());
        List<Notification> notifications = user.getReceiverNotifications();
        List<Notification> followNotification = new ArrayList<>();
        for (Notification notification : notifications) {
            if (notification.getReceiver().getUsername().equals(LoggedUser.getLoggedUser().getUsername())) {
                if (notification.getType() == NotificationType.FOLLOW_REQ)
                    followNotification.add(notification);
            }
        }
        return followNotification;
    }

    public List<Notification> getYourFollowingRequestNotification() {
        User user = USER_REPOSITORY.getById(LoggedUser.getLoggedUser().getId());
        List<Notification> notifications = user.getSenderNotifications();
        List<Notification> followNotification = new ArrayList<>();
        for (Notification notification : notifications) {
            if (notification.getSender().getUsername().equals(LoggedUser.getLoggedUser().getUsername())) {
                if (notification.getType() == NotificationType.FOLLOW_REQ)
                    followNotification.add(notification);
            }
        }
        return followNotification;
    }

    public List<Notification> getSystemNotification() {
        User user = USER_REPOSITORY.getById(LoggedUser.getLoggedUser().getId());
        List<Notification> notifications = user.getReceiverNotifications();
        List<Notification> followNotification = new ArrayList<>();
        for (Notification notification : notifications) {
            if (notification.getReceiver().getUsername().equals(LoggedUser.getLoggedUser().getUsername())) {
                if (notification.getType() != NotificationType.FOLLOW_REQ)
                    followNotification.add(notification);
            }
        }
        return followNotification;
    }

    public void acceptFollowRequest(Notification notification) {
        deleteNotification(notification);

        NOTIFICATION_REPOSITORY.addNewFollower(LoggedUser.getLoggedUser().getId(), notification.getSender().getId());
        //notificationRepository.addNewFollowing(notification.getSender().getId(), LoggedUser.getLoggedUser().getId());
        log.info( LoggedUser.getLoggedUser().getUsername() + " accepted " + notification.getSender().getUsername() );
    }

    public void rejectFollowRequestWithNotification(Notification notification) {
        User loggedUser = USER_REPOSITORY.getById(LoggedUser.getLoggedUser().getId());
        User requestSender = USER_REPOSITORY.getById(notification.getSender().getId());
        Notification rejectFollowRequest = new Notification(loggedUser, requestSender, NotificationType.FOLLOW_REQ_REJECT);
        NOTIFICATION_REPOSITORY.insert(rejectFollowRequest);

        deleteNotification(notification);
    }

    public void rejectFollowRequestWithoutNotification(Notification notification) {
        deleteNotification(notification);
    }

    public void deleteNotification(Notification notification) {
        NOTIFICATION_REPOSITORY.deleteNotification(notification.getId());
        User loggedUser = USER_REPOSITORY.getById(LoggedUser.getLoggedUser().getId());
        loggedUser.getReceiverNotifications().remove(notification);
    }

    public void deleteRequest(long rawUserId) {
        User user = USER_REPOSITORY.getById(rawUserId);
        long loggedUserId = LoggedUser.getLoggedUser().getId();
        Notification request = user.getReceiverNotifications().stream()
                .filter(it -> ((it.getSender().getId() == loggedUserId) && (it.getType() == NotificationType.FOLLOW_REQ)))
                .collect(Collectors.toList()).get(0);
        NOTIFICATION_REPOSITORY.deleteNotification(request.getId());
    }
}