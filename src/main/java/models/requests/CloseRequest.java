package models.requests;

import models.NetworkData;
import models.responses.BooleanResponse;
import models.responses.Response;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("close")
public class CloseRequest implements Request{

    @Override
    public Response execute() {
        return NetworkData.sendRequest(this);
    }

    public CloseRequest() {
    }
}
