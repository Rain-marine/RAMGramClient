package models.requests;

import controllers.Controllers;
import models.LoggedUser;
import models.NetworkData;
import models.responses.BooleanResponse;
import models.responses.Response;
import org.codehaus.jackson.annotate.JsonTypeName;
import util.Save;

@JsonTypeName("visibility")
public class ChangeAccountVisibilityRequest implements Request {

    private String token;
    private long userId;
    private boolean isPublic;

    public ChangeAccountVisibilityRequest(String token, long userId, boolean isPublic) {
        this.token = token;
        this.userId = userId;
        this.isPublic = isPublic;
    }

    public ChangeAccountVisibilityRequest() {
    }

    @Override
    public Response execute() {
        if(LoggedUser.getMode() == LoggedUser.Mode.OFFLINE){
            this.token = LoggedUser.getToken();
            Save.getInstance().update(this);
            return null;
        }
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

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }
}
