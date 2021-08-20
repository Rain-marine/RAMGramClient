package models.responses;


import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("permission")
public class PermissionResponse implements Response{

    private boolean[] permissions;

    @Override
    public Object unleash() {

        return null;
    }

    public PermissionResponse() {
    }

    public boolean[] getPermissions() {
        return permissions;
    }

    public void setPermissions(boolean[] permissions) {
        this.permissions = permissions;
    }
}
