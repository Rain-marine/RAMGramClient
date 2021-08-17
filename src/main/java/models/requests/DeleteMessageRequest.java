package models.requests;

import controllers.Controllers;
import models.NetworkData;
import models.responses.BooleanResponse;
import models.responses.Response;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("deleteMessage")
public class DeleteMessageRequest implements Request {

    private String token;
    private long userId;
    private long messageId;


    public DeleteMessageRequest(String token, long userId, long messageId) {
        this.token = token;
        this.userId = userId;
        this.messageId = messageId;
    }

    @Override
    public Response execute() {
       return NetworkData.sendRequest(this);
    }

    public DeleteMessageRequest() {
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
