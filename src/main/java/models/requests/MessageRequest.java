package models.requests;

import models.LoggedUser;
import models.NetworkData;
import models.responses.BooleanResponse;
import models.responses.MessageResponse;
import models.responses.Response;
import models.trimmed.TrimmedMessage;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("message")
public class MessageRequest implements Request{

    private String token;
    private long userId;
    private long messageId;

    public MessageRequest( long messageId) {
        this.token = LoggedUser.getToken();
        this.userId = LoggedUser.getId();
        this.messageId = messageId;
    }

    public MessageRequest() {
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

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }
}
