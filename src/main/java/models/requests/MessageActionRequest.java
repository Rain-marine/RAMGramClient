package models.requests;

import models.LoggedUser;
import models.NetworkData;
import models.responses.Response;
import models.types.MessageActionType;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("messageAction")
public class MessageActionRequest implements Request {

    private String token;
    private long userId;
    private long messageId;
    private String newText;
    private MessageActionType type;


    public MessageActionRequest(MessageActionType type , long messageId ) {
        this.token = LoggedUser.getToken();
        this.userId = LoggedUser.getId();
        this.messageId = messageId;
        this.type = type;
    }

    public MessageActionRequest(String token, long userId, MessageActionType type, long messageId, String newText) {
        this.token = token;
        this.userId = userId;
        this.messageId = messageId;
        this.newText = newText;
        this.type = type;
    }

    @Override
    public Response execute() {
       return NetworkData.sendRequest(this);
    }

    public MessageActionRequest() {
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

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public String getNewText() {
        return newText;
    }

    public void setNewText(String newText) {
        this.newText = newText;
    }

    public MessageActionType getType() {
        return type;
    }

    public void setType(MessageActionType type) {
        this.type = type;
    }
}
