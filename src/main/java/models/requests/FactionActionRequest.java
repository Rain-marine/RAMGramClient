package models.requests;

import models.LoggedUser;
import models.NetworkData;
import models.responses.Response;
import models.types.FactionActionType;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.util.List;

@JsonTypeName("factionAction")
public class FactionActionRequest implements Request {

    private String token;
    private long userId;
    private FactionActionType type;
    private long otherUserId;
    private int factionId;
    private List<String> users;
    private String name;


    public FactionActionRequest(FactionActionType type, int factionId, long otherUserId, List<String> users , String name) {
        this.token = LoggedUser.getToken();
        this.userId = LoggedUser.getId();
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

    public FactionActionType getType() {
        return type;
    }

    public void setType(FactionActionType type) {
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
