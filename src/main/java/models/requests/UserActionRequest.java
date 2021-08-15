package models.requests;

import controllers.Controllers;
import models.NetworkData;
import models.responses.BooleanResponse;
import models.responses.Response;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("userAction")
public class UserActionRequest implements Request {

    public enum USER_ACTION {MUTE , BLOCK , REPORT}

    private String token;
    private long userId;
    private long otherUserId;
    private USER_ACTION action;

    public UserActionRequest(String token, long userId, long otherUserId, USER_ACTION action) {
        this.token = token;
        this.userId = userId;
        this.otherUserId = otherUserId;
        this.action = action;
    }

    public UserActionRequest() {
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

    public long getOtherUserId() {
        return otherUserId;
    }

    public void setOtherUserId(long otherUserId) {
        this.otherUserId = otherUserId;
    }

    public USER_ACTION getAction() {
        return action;
    }

    public void setAction(USER_ACTION action) {
        this.action = action;
    }
}
