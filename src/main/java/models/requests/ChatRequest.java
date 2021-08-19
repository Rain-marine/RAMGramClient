package models.requests;

import models.NetworkData;
import models.responses.BooleanResponse;
import models.responses.ChatResponse;
import models.responses.Response;
import models.trimmed.TrimmedChat;
import models.types.ChatType;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("chat")
public class ChatRequest implements Request{

    private String token;
    private long userId;
    private long chatId;
    private ChatType mode;
    private String sender;

    public ChatRequest(String token, long userId, long chatId) {
        this.token = token;
        this.userId = userId;
        this.chatId = chatId;
        this.mode = ChatType.NORMAL;
        this.sender = null;
    }


    public ChatRequest(String token, long userId, long chatId , ChatType mode , String sender) {
        this.token = token;
        this.userId = userId;
        this.chatId = chatId;
        this.mode = mode;
        this.sender =sender;
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

    public ChatType getMode() {
        return mode;
    }

    public void setMode(ChatType mode) {
        this.mode = mode;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
