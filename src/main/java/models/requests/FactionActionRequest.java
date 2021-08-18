package models.requests;

import controllers.Controllers;
import models.NetworkData;
import models.responses.BooleanResponse;
import models.responses.Response;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.util.List;

@JsonTypeName("factionAction")
public class FactionActionRequest implements Request {

    public enum TYPE {DELETE_FACTION, DELETE_MEMBER, NEW_FACTION, ADD_MEMBER}

    private String token;
    private long userId;
    private TYPE type;
    private long otherUserId;
    private int factionId;
    private List<String> users;
    private String name;


    public FactionActionRequest(String token, long userId, TYPE type, int factionId, long otherUserId, List<String> users , String name) {
        this.token = token;
        this.userId = userId;
        this.type = type;
        this.otherUserId = otherUserId;
        this.factionId = factionId;
        this.users = users;
        this.name = name;
    }

    @Override
    public Response execute() {
        return NetworkData.sendRequest(this);
    }

    public FactionActionRequest() {
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

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public long getOtherUserId() {
        return otherUserId;
    }

    public void setOtherUserId(long otherUserId) {
        this.otherUserId = otherUserId;
    }

    public int getFactionId() {
        return factionId;
    }

    public void setFactionId(int factionId) {
        this.factionId = factionId;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
