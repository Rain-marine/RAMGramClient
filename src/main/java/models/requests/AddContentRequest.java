package models.requests;

import models.NetworkData;
import models.responses.Response;
import models.types.AddContentType;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("addContent")
public class AddContentRequest implements Request {

    private String token;
    private long userId;
    private AddContentType type;
    private byte[] image;
    private String text;
    private long superId;
    private long selfId;


    public AddContentRequest(String token, long userId,AddContentType type, byte[] image, String text, long superId, long selfId) {
        this.token = token;
        this.userId = userId;
        this.type = type;
        this.image = image;
        this.text = text;
        this.superId = superId;
        this.selfId = selfId;
    }

    @Override
    public Response execute() {
        return NetworkData.sendRequest(this);
    }


    public AddContentRequest() {
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

    public AddContentType getType() {
        return type;
    }

    public void setType(AddContentType type) {
        this.type = type;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getSuperId() {
        return superId;
    }

    public void setSuperId(long superId) {
        this.superId = superId;
    }

    public long getSelfId() {
        return selfId;
    }

    public void setSelfId(long selfId) {
        this.selfId = selfId;
    }
}
