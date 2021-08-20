package models.responses;

import org.codehaus.jackson.annotate.JsonTypeName;

import java.util.ArrayList;

@JsonTypeName("list")
public class ListResponse implements Response<ArrayList<Long>>{
    private ArrayList<Long> ids;

    public ListResponse(ArrayList<Long> ids) {
        this.ids = ids;
    }

    public ListResponse() {
    }

    @Override
    public ArrayList<Long> unleash() {
        return ids;
    }

    public ArrayList<Long> getIds() {
        return ids;
    }

    public void setIds(ArrayList<Long> ids) {
        this.ids = ids;
    }
}
