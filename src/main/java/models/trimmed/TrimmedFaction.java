package models.trimmed;

import java.util.HashMap;

public class TrimmedFaction {

    private int id;
    private long ownerId;
    private String name;
    private HashMap<Long , String> members;

    public TrimmedFaction() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<Long, String> getMembers() {
        return members;
    }

    public void setMembers(HashMap<Long, String> members) {
        this.members = members;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }
}
