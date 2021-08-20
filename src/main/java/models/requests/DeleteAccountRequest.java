package models.requests;

import controllers.Controllers;
import models.LoggedUser;
import models.NetworkData;
import models.responses.BooleanResponse;
import models.responses.Response;
import org.codehaus.jackson.annotate.JsonTypeName;


@JsonTypeName("deleteAccount")
public class DeleteAccountRequest implements Request {

    private String token;
    private long userId;

    public DeleteAccountRequest() {
    }


    @Override
    public Response execute() {
        this.token = LoggedUser.getToken();
        this.userId = LoggedUser.getId();
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
}
