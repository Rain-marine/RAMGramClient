package models.responses;


import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("explore")
public class ExploreResponse implements Response<Object> {

    private long foundUserId;

    public ExploreResponse(long foundUserId) {
        this.foundUserId = foundUserId;
    }

    public long getFoundUserId() {
        return foundUserId;
    }

    public void setFoundUserId(long foundUserId) {
        this.foundUserId = foundUserId;
    }

    @Override
    public Object unleash() {
        return null;
    }

    public ExploreResponse() {
    }
}
