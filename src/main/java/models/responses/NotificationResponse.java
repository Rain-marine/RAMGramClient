package models.responses;

import models.Notification;
import models.trimmed.TrimmedNotification;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.util.ArrayList;
import java.util.List;

@JsonTypeName("notification")
public class NotificationResponse implements Response {

    private List<TrimmedNotification> trimmedNotifications = new ArrayList<>();


    @Override
    public void unleash() {
    }

    public NotificationResponse() {
    }

    public List<TrimmedNotification> getTrimmedNotifications() {
        return trimmedNotifications;
    }

    public void setTrimmedNotifications(List<TrimmedNotification> trimmedNotifications) {
        this.trimmedNotifications = trimmedNotifications;
    }
}
