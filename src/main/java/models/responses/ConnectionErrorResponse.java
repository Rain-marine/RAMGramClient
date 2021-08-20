package models.responses;

import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("error")
public class ConnectionErrorResponse implements Response<Object>{

    private String message;

    public ConnectionErrorResponse(String message) {
        this.message = message;
    }

    public ConnectionErrorResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Object unleash() {

        return null;
    }
}
