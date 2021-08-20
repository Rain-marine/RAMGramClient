package models.requests;

import models.LoggedUser;
import models.NetworkData;
import models.responses.BooleanResponse;
import models.responses.Response;
import models.responses.UserResponse;
import models.trimmed.TrimmedUser;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("user")
public class UserRequest implements Request{

    private String token;
    private long userId;
    private long otherUserId;

    public UserRequest(long otherUserId) {
        this.token = LoggedUser.getToken();
        this.userId = LoggedUser.getId();
        this.otherUserId = otherUserId;
    }

    @Override
    public Response execute() {
        return NetworkData.sendRequest(this);
    }

    public UserRequest() {
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
}
