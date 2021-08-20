package models.requests;

import controllers.Controllers;
import models.LoggedUser;
import models.NetworkData;
import models.responses.BooleanResponse;
import models.responses.LoggedUserResponse;
import models.responses.Response;
import models.trimmed.TrimmedLoggedUser;
import org.codehaus.jackson.annotate.JsonTypeName;
import util.Save;

import java.util.Date;

@JsonTypeName("changeBirthday")
public class ChangeBirthdayRequest implements Request {

    private String token;
    private long userId;
    private Date newBirthday;

    public ChangeBirthdayRequest(String token, long userId, Date newBirthday) {
        this.token = token;
        this.userId = userId;
        this.newBirthday = newBirthday;
    }

    public ChangeBirthdayRequest() {
    }

    @Override
    public Response execute() {
        if (LoggedUser.getMode() == LoggedUser.Mode.OFFLINE) {
            Save.getInstance().update(this);
            return null;
        }
        this.token = LoggedUser.getToken();
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

    public Date getNewBirthday() {
        return newBirthday;
    }

    public void setNewBirthday(Date newBirthday) {
        this.newBirthday = newBirthday;
    }
}
