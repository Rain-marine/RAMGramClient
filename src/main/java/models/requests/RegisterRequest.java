package models.requests;

import models.NetworkData;
import models.responses.Response;
import models.types.RegisterType;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("register")
public class RegisterRequest implements Request {

    private String infoCheck;
    private RegisterType type;
    private String[] finalInfo;

    public RegisterRequest(RegisterType type , String infoCheck, String[] finalInfo) {
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

    public RegisterType getType() {
        return type;
    }

    public void setType(RegisterType type) {
        this.type = type;
    }

    public String[] getFinalInfo() {
        return finalInfo;
    }

    public void setFinalInfo(String[] finalInfo) {
        this.finalInfo = finalInfo;
    }
}
