package models.requests;

import models.NetworkData;
import models.responses.Response;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("userAction")
public class UserActionRequest implements Request {

    public enum USER_ACTION {MUTE , BLOCK , REPORT , UNBLOCK , FOLLOW , UNFOLLOW , QUIET_UNFOLLOW, REQUEST , ACCEPT , REJECT , DELETE_REQUEST, QUIET_REJECT , DELETE_NOTIF}

    private String token;
    private long userId;
    private long otherId;
    private USER_ACTION action;

    public UserActionRequest(String token, long userId, long otherUserId, USER_ACTION action) {
        this.token = token;
        this.userId = userId;
        this.otherId = otherUserId;
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

    public long getOtherId() {
        return otherId;
    }

    public void setOtherId(long otherId) {
        this.otherId = otherId;
    }

    public USER_ACTION getAction() {
        return action;
    }

    public void setAction(USER_ACTION action) {
        this.action = action;
    }
}
