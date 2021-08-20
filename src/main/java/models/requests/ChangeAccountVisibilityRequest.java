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

    public ChangeAccountVisibilityRequest( boolean isPublic) {
        this.token = LoggedUser.getToken();
        this.userId = LoggedUser.getId();
        this.isPublic = isPublic;
    }

    public ChangeAccountVisibilityRequest() {
    }

    @Override
    public Response execute() {
        if(LoggedUser.getMode() == LoggedUser.Mode.OFFLINE){
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

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }
}
