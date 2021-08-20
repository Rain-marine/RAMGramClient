package models.responses;


import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("boolean")
public class BooleanResponse implements Response<Boolean>{

    private boolean result;

    public BooleanResponse() {
    }

    public BooleanResponse(boolean result) {
        this.result = result;
    }

    @Override
    public Boolean unleash() {
        return this.result;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
