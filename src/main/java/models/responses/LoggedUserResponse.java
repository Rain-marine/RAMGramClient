package models.responses;


import models.LoggedUser;
import models.trimmed.TrimmedLoggedUser;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("LoggedUser")
public class LoggedUserResponse implements Response<Object>{

    private TrimmedLoggedUser trimmedLoggedUser;

    public LoggedUserResponse(TrimmedLoggedUser trimmedLoggedUser) {
        this.trimmedLoggedUser = trimmedLoggedUser;
    }

    public LoggedUserResponse() {
    }

    @Override
    public Object unleash() {
        LoggedUser.setTrimmedLoggedUser(trimmedLoggedUser);
        return null;
    }

    public TrimmedLoggedUser getTrimmedLoggedUser() {
        return trimmedLoggedUser;
    }

    public void setTrimmedLoggedUser(TrimmedLoggedUser trimmedLoggedUser) {
        this.trimmedLoggedUser = trimmedLoggedUser;
    }
}
