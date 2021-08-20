package models.requests;

import models.LoggedUser;
import models.NetworkData;
import models.responses.Response;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("logout")
public class LogoutRequest implements Request {

    private String token;
    private long userId;


    @Override
    public Response execute() {
        this.token = LoggedUser.getToken();
        this.userId = LoggedUser.getId();
        return NetworkData.sendRequest(this);
    }

    public LogoutRequest() {
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
}
