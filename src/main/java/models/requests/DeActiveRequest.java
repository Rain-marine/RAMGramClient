package models.requests;

import controllers.Controllers;
import models.LoggedUser;
import models.NetworkData;
import models.responses.Response;
import org.codehaus.jackson.annotate.JsonTypeName;
import util.Save;

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
        if (LoggedUser.getMode() == LoggedUser.Mode.OFFLINE) {
            Save.getInstance().update(this);
            return null;
        }
        this.token = LoggedUser.getToken();
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
