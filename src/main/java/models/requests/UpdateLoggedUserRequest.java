package models.requests;

import models.NetworkData;
import models.responses.LoggedUserResponse;
import models.responses.Response;
import models.trimmed.TrimmedLoggedUser;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("updateLoggedUser")
public class UpdateLoggedUserRequest implements Request {

    private long userId;


    public UpdateLoggedUserRequest() {
    }

    public UpdateLoggedUserRequest(long userId) {
        this.userId = userId;
    }

    @Override
    public Response execute() {
        return NetworkData.sendRequest(this);
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
