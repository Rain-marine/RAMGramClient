package models.responses;

import controllers.Controllers;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.util.ArrayList;

@JsonTypeName("chatInfo")
public class ChatInfoResponse implements Response{
    private long loggedUserId;
    private long chatId;
    private long frontUserId;
    private String frontUsername;
    private String lastSeen;
    private byte[] frontProfile;
    private ArrayList<String> membersNames;


    @Override
    public void unleash() {

    }

    public ChatInfoResponse() {
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public long getFrontUserId() {
        return frontUserId;
    }

    public void setFrontUserId(long frontUserId) {
        this.frontUserId = frontUserId;
    }

    public String getFrontUsername() {
        return frontUsername;
    }

    public void setFrontUsername(String frontUsername) {
        this.frontUsername = frontUsername;
    }

    public String getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(String lastSeen) {
        this.lastSeen = lastSeen;
    }

    public byte[] getFrontProfile() {
        return frontProfile;
    }

    public void setFrontProfile(byte[] frontProfile) {
        this.frontProfile = frontProfile;
    }

    public ArrayList<String> getMembersNames() {
        return membersNames;
    }

    public void setMembersNames(ArrayList<String> membersNames) {
        this.membersNames = membersNames;
    }

    public long getLoggedUserId() {
        return loggedUserId;
    }

    public void setLoggedUserId(long loggedUserId) {
        this.loggedUserId = loggedUserId;
    }
}
