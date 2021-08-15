package models.responses;


import models.LoggedUser;
import models.trimmed.TrimmedLoggedUser;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("LoggedUser")
public class LoggedUserResponse implements Response{

    private TrimmedLoggedUser trimmedLoggedUser;

    public LoggedUserResponse(TrimmedLoggedUser trimmedLoggedUser) {
        this.trimmedLoggedUser = trimmedLoggedUser;
    }

    public LoggedUserResponse() {
    }

    @Override
    public void unleash() {
        LoggedUser.setTrimmedLoggedUser(trimmedLoggedUser);
    }

    public TrimmedLoggedUser getTrimmedLoggedUser() {
        return trimmedLoggedUser;
    }

    public void setTrimmedLoggedUser(TrimmedLoggedUser trimmedLoggedUser) {
        this.trimmedLoggedUser = trimmedLoggedUser;
    }
}
