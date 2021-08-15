package models.requests;

import controllers.Controllers;
import models.NetworkData;
import models.responses.Response;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("deActive")
public class DeActiveRequest implements Request, Controllers {

    private String token;
    private long userId;
    private boolean isActive;

    public DeActiveRequest(String token, long userId, boolean isActive) {
        this.token = token;
        this.userId = userId;
        this.isActive = isActive;
    }

    public DeActiveRequest() {
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
