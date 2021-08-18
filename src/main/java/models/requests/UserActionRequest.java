package models.requests;

import models.NetworkData;
import models.responses.Response;
import models.types.UserActionType;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("userAction")
public class UserActionRequest implements Request {

    private String token;
    private long userId;
    private long otherId;
    private UserActionType action;

    public UserActionRequest(String token, long userId, long otherUserId, UserActionType action) {
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

    public UserActionType getAction() {
        return action;
    }

    public void setAction(UserActionType action) {
        this.action = action;
    }
}
