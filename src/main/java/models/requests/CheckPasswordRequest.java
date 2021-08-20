package models.requests;


import controllers.Controllers;
import models.LoggedUser;
import models.NetworkData;
import models.responses.BooleanResponse;
import models.responses.Response;
import org.codehaus.jackson.annotate.JsonTypeName;


@JsonTypeName("checkPass")
public class CheckPasswordRequest implements Request {

    private String password;
    private long userId;
    private String token;

    public CheckPasswordRequest(String password) {
        this.password = password;
        this.token = LoggedUser.getToken();
        this.userId = LoggedUser.getId();
    }

    public CheckPasswordRequest() {
    }

    @Override
    public Response execute() {
        return NetworkData.sendRequest(this);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
