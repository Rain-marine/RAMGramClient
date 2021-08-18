package models.requests;

import models.NetworkData;
import models.responses.Response;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("notification")
public class NotificationRequest implements Request {

    public enum TYPE {SYSTEM , REQUESTS , REQ_TO_ME}

    private String token;
    private long userId;
    private TYPE type;

    public NotificationRequest() {
    }

    public NotificationRequest(String token, long userId, TYPE type) {
        this.token = token;
        this.userId = userId;
        this.type = type;
    }

    @Override
    public Response execute() {
        return NetworkData.sendRequest(this);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }
}
