package models.requests;

import controllers.Controllers;
import models.NetworkData;
import models.responses.BooleanResponse;
import models.responses.Response;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("messageAccess")
public class MessageAccessRequest implements Request {

    public enum TYPE {USER, GROUP}

    private String token;
    private long userId;
    private TYPE type;
    private String targetUsername;

    public MessageAccessRequest(String token, long userId, TYPE type, String targetUsername) {
        this.token = token;
        this.userId = userId;
        this.type = type;
        this.targetUsername = targetUsername;
    }

    @Override
    public Response execute() {
        return NetworkData.sendRequest(this);
    }

    public MessageAccessRequest() {
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

    public String getTargetUsername() {
        return targetUsername;
    }

    public void setTargetUsername(String targetUsername) {
        this.targetUsername = targetUsername;
    }
}
