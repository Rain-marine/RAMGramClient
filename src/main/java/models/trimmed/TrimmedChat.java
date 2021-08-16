package models.trimmed;

import controllers.Controllers;

public class TrimmedChat {

    private long loggedUserId;
    private long id;
    private boolean isGroup;
    private String name;
    private String unseenCount;

    public TrimmedChat() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isGroup() {
        return isGroup;
    }

    public void setGroup(boolean group) {
        isGroup = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnseenCount() {
        return unseenCount;
    }

    public void setUnseenCount(String unseenCount) {
        this.unseenCount = unseenCount;
    }

    public long getLoggedUserId() {
        return loggedUserId;
    }

    public void setLoggedUserId(long loggedUserId) {
        this.loggedUserId = loggedUserId;
    }
}
