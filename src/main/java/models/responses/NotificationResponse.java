package models.responses;

import models.trimmed.TrimmedNotification;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.util.ArrayList;
import java.util.List;

@JsonTypeName("notification")
public class NotificationResponse implements Response<Object> {

    private List<TrimmedNotification> trimmedNotifications = new ArrayList<>();


    @Override
    public Object unleash() {
        return null;
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
