package models.responses;

import models.trimmed.TrimmedFaction;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.util.List;

@JsonTypeName("faction")
public class FactionResponse implements Response<Object>{

    private List<TrimmedFaction> trimmedFactions;

    @Override
    public Object unleash() {
        return null;
    }

    public FactionResponse() {
    }

    public List<TrimmedFaction> getTrimmedFactions() {
        return trimmedFactions;
    }

    public void setTrimmedFactions(List<TrimmedFaction> trimmedFactions) {
        this.trimmedFactions = trimmedFactions;
    }
}
