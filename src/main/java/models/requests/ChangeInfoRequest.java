package models.requests;

import models.LoggedUser;
import models.NetworkData;
import models.responses.Response;
import models.types.ChangeInfoType;
import org.codehaus.jackson.annotate.JsonTypeName;
import util.Save;

@JsonTypeName("changeInfo")
public class ChangeInfoRequest implements Request {

    private String token;
    private long userId;
    private String newInfo;
    private byte[] newPhoto;
    private ChangeInfoType type;

    public ChangeInfoRequest( ChangeInfoType type, String newInfo) {
        this.token = LoggedUser.getToken();
        this.userId = LoggedUser.getId();
        this.newInfo = newInfo;
        this.type = type;
    }

    public ChangeInfoRequest(ChangeInfoType type, byte[] newPhoto) {
        this.token = LoggedUser.getToken();
        this.userId = LoggedUser.getId();
        this.newPhoto = newPhoto;
        this.type = type;
    }

    public ChangeInfoRequest() {
    }

    @Override
    public Response execute() {
        if (LoggedUser.getMode() == LoggedUser.Mode.OFFLINE) {
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
