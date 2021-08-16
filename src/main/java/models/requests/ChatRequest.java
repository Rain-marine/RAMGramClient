package models.requests;

import models.NetworkData;
import models.responses.BooleanResponse;
import models.responses.ChatResponse;
import models.responses.Response;
import models.trimmed.TrimmedChat;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("chat")
public class ChatRequest implements Request{

    private String token;
    private long userId;
    private long chatId;

    public ChatRequest(String token, long userId, long chatId) {
        this.token = token;
        this.userId = userId;
        this.chatId = chatId;
    }

    public ChatRequest() {
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

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }
}
