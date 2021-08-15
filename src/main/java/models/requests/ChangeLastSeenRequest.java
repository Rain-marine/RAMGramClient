package models.requests;

import controllers.Controllers;
import models.NetworkData;
import models.responses.BooleanResponse;
import models.responses.LoggedUserResponse;
import models.responses.Response;
import models.trimmed.TrimmedLoggedUser;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("changeLastSeen")
public class ChangeLastSeenRequest implements Request {

    private String token;
    private long userId;
    private String newLastSeen;

    public ChangeLastSeenRequest(String token, long userId, String newLastSeen) {
        this.token = token;
        this.userId = userId;
        this.newLastSeen = newLastSeen;
    }

    public ChangeLastSeenRequest() {
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

    public String getNewLastSeen() {
        return newLastSeen;
    }

    public void setNewLastSeen(String newLastSeen) {
        this.newLastSeen = newLastSeen;
    }
}
