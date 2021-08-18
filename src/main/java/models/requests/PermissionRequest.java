package models.requests;

import controllers.ProfileAccessController;
import models.NetworkData;
import models.responses.BooleanResponse;
import models.responses.PermissionResponse;
import models.responses.Response;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.util.ArrayList;


@JsonTypeName("permission")
public class PermissionRequest implements Request{

    public enum TYPE {REGISTER , PROFILE}
    private String token;
    private long userId;
    private long otherUserId;
    private TYPE type;
    private ArrayList<String> info; //username , email , number

    public PermissionRequest(String token, long userId, long otherUserId, TYPE type, ArrayList<String> info) {
        this.token = token;
        this.userId = userId;
        this.otherUserId = otherUserId;
        this.type = type;
        this.info = info;
    }

    public PermissionRequest() {
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

    public long getOtherUserId() {
        return otherUserId;
    }

    public void setOtherUserId(long otherUserId) {
        this.otherUserId = otherUserId;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public ArrayList<String> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<String> info) {
        this.info = info;
    }
}
