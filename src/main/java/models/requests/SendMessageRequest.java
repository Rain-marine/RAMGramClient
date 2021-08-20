package models.requests;

import controllers.Controllers;
import models.LoggedUser;
import models.NetworkData;
import models.responses.BooleanResponse;
import models.responses.Response;
import models.types.SendMessageType;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.util.ArrayList;
import java.util.List;

@JsonTypeName("sendMessage")
public class SendMessageRequest implements Request {

    private String token;
    private long userId;
    private SendMessageType type;
    private long contentId;
    private List<String> users;
    private List<String> factions;
    private String content;
    private byte[] contentImage;
    private String targetUsername;

    public SendMessageRequest(SendMessageType type, long contentId, List<String> users, List<String> factions, String content, byte[] contentImage, String targetUsername) {
        this.token = LoggedUser.getToken();
        this.userId = LoggedUser.getId();
        this.type = type;
        this.contentId = contentId;
        this.users = users;
        this.factions = factions;
        this.content = content;
        this.contentImage = contentImage;
        this.targetUsername = targetUsername;
    }



    @Override
    public Response execute() {
       return NetworkData.sendRequest(this);
    }

    public SendMessageRequest() {
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

    public SendMessageType getType() {
        return type;
    }

    public void setType(SendMessageType type) {
        this.type = type;
    }

    public long getContentId() {
        return contentId;
    }

    public void setContentId(long contentId) {
        this.contentId = contentId;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    public List<String> getFactions() {
        return factions;
    }

    public void setFactions(List<String> factions) {
        this.factions = factions;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public byte[] getContentImage() {
        return contentImage;
    }

    public void setContentImage(byte[] contentImage) {
        this.contentImage = contentImage;
    }

    public String getTargetUsername() {
        return targetUsername;
    }

    public void setTargetUsername(String targetUsername) {
        this.targetUsername = targetUsername;
    }

}
