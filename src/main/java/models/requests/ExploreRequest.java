package models.requests;

import models.LoggedUser;
import models.NetworkData;
import models.responses.Response;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("explore")
public class ExploreRequest implements Request{

    private String token;
    private long userId;
    private String usernameToFind;

    public ExploreRequest( String usernameToFind) {
        this.token = LoggedUser.getToken();
        this.userId = LoggedUser.getId();
        this.usernameToFind = usernameToFind;
    }

    @Override
    public Response execute() {
        return NetworkData.sendRequest(this);
    }

    public ExploreRequest() {
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

    public String getUsernameToFind() {
        return usernameToFind;
    }

    public void setUsernameToFind(String usernameToFind) {
        this.usernameToFind = usernameToFind;
    }
}
