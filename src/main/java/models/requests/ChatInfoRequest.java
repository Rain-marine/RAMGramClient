package models.requests;

import models.LoggedUser;
import models.NetworkData;
import models.responses.BooleanResponse;
import models.responses.ChatInfoResponse;
import models.responses.Response;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("chatInfo")
public class ChatInfoRequest implements Request {

    private long chatId;
    private String token;
    private long userId;

    public ChatInfoRequest( long chatId) {
        this.chatId = chatId;
        this.token = LoggedUser.getToken();
        this.userId = LoggedUser.getId();
    }

    public ChatInfoRequest() {
    }

    @Override
    public Response execute() {
        return NetworkData.sendRequest(this);
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
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
}
