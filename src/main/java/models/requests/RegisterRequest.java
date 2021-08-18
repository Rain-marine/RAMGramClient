package models.requests;

import models.NetworkData;
import models.responses.Response;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("register")
public class RegisterRequest implements Request {

    public enum TYPE { USERNAME , EMAIL , NUMBER , INSERT}
    private String infoCheck;
    private TYPE type;
    private String[] finalInfo;

    public RegisterRequest(TYPE type , String infoCheck, String[] finalInfo) {
        this.infoCheck = infoCheck;
        this.type = type;
        this.finalInfo = finalInfo;
    }

    @Override
    public Response execute() {
       return NetworkData.sendRequest(this);
    }

    public RegisterRequest() {
    }

    public String getInfoCheck() {
        return infoCheck;
    }

    public void setInfoCheck(String infoCheck) {
        this.infoCheck = infoCheck;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public String[] getFinalInfo() {
        return finalInfo;
    }

    public void setFinalInfo(String[] finalInfo) {
        this.finalInfo = finalInfo;
    }
}
