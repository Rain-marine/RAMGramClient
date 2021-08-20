package models.requests;

import controllers.Controllers;
import models.LoggedUser;
import models.NetworkData;
import models.responses.BooleanResponse;
import models.responses.Response;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.io.IOException;

@JsonTypeName("isPublic")
public class IsPublicRequest implements Request, Controllers {

    private long userId;
    private String token;


    public IsPublicRequest() {
    }

    @Override
    public Response execute() {
        this.token = LoggedUser.getToken();
        this.userId = LoggedUser.getId();
       return NetworkData.sendRequest(this);
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
