package models.requests;

import models.NetworkData;
import models.responses.Response;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("changeInfo")
public class ChangeInfoRequest implements Request {

    public enum TYPE { FULL_NAME ,USERNAME , EMAIL , NUMBER , BIO , LAST_SEEN , PROFILE}
    private String token;
    private long userId;
    private String newInfo;
    private byte[] newPhoto;
    private TYPE type;

    public ChangeInfoRequest(String token, long userId,TYPE type, String newInfo) {
        this.token = token;
        this.userId = userId;
        this.newInfo = newInfo;
        this.type = type;
    }

    public ChangeInfoRequest(String token, long userId, TYPE type, byte[] newPhoto) {
        this.token = token;
        this.userId = userId;
        this.newPhoto = newPhoto;
        this.type = type;
    }

    public ChangeInfoRequest() {
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

    public String getNewInfo() {
        return newInfo;
    }

    public void setNewInfo(String newInfo) {
        this.newInfo = newInfo;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public byte[] getNewPhoto() {
        return newPhoto;
    }

    public void setNewPhoto(byte[] newPhoto) {
        this.newPhoto = newPhoto;
    }
}
