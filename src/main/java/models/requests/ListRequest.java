package models.requests;

import controllers.Controllers;
import models.LoggedUser;
import models.NetworkData;
import models.responses.BooleanResponse;
import models.responses.ListResponse;
import models.responses.Response;
import models.types.ListType;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("list")
public class ListRequest implements Request {

    private String token;
    private long userId;
    private ListType type;
    private long superId;

    public ListRequest( ListType type, long superId) {
        this.token = LoggedUser.getToken();
        this.userId = LoggedUser.getId();
        this.type = type;
        this.superId = superId;
    }

    public ListRequest() {
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

    public ListType getType() {
        return type;
    }

    public void setType(ListType type) {
        this.type = type;
    }

    public long getSuperId() {
        return superId;
    }

    public void setSuperId(long superId) {
        this.superId = superId;
    }
}
