package models.requests;

import controllers.Controllers;
import models.NetworkData;
import models.responses.BooleanResponse;
import models.responses.ListResponse;
import models.responses.Response;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("list")
public class ListRequest implements Request {

    public enum TYPE {TIMELINE, EXPLORER , COMMENT ,CHAT ,MESSAGE ,FACTION , SAVED_MESSAGES , SAVED_TWEET , TWEETS , FOLLOWINGS , FOLLOWERS , BLACKLIST}

    private String token;
    private long userId;
    private TYPE type;
    private long superId;

    public ListRequest(String token, long userId, TYPE type, long superId) {
        this.token = token;
        this.userId = userId;
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

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public long getSuperId() {
        return superId;
    }

    public void setSuperId(long superId) {
        this.superId = superId;
    }
}
