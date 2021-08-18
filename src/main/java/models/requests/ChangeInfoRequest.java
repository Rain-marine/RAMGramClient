package models.requests;

import models.NetworkData;
import models.responses.Response;
import models.types.ChangeInfoType;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("changeInfo")
public class ChangeInfoRequest implements Request {

    private String token;
    private long userId;
    private String newInfo;
    private byte[] newPhoto;
    private ChangeInfoType type;

    public ChangeInfoRequest(String token, long userId,ChangeInfoType type, String newInfo) {
        this.token = token;
        this.userId = userId;
        this.newInfo = newInfo;
        this.type = type;
    }

    public ChangeInfoRequest(String token, long userId, ChangeInfoType type, byte[] newPhoto) {
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

    public ChangeInfoType getType() {
        return type;
    }

    public void setType(ChangeInfoType type) {
        this.type = type;
    }

    public byte[] getNewPhoto() {
        return newPhoto;
    }

    public void setNewPhoto(byte[] newPhoto) {
        this.newPhoto = newPhoto;
    }
}
