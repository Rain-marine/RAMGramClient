package controllers;

import models.LoggedUser;
import models.requests.FactionActionRequest;
import models.requests.ListRequest;
import models.requests.MessageAccessRequest;
import models.responses.BooleanResponse;
import models.responses.FactionResponse;
import models.responses.Response;
import models.trimmed.TrimmedFaction;
import models.types.FactionActionType;
import models.types.ListType;
import models.types.MessageAccessType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class FactionsController {

    public FactionsController() {
    }

    public void insertNewFaction(String name, List<String> users) {
        new FactionActionRequest(LoggedUser.getToken() ,
                LoggedUser.getId() ,
                FactionActionType.NEW_FACTION ,
                0,
                0L,
                users,
                name
        ).execute();
    }

    public boolean canAddToGroup(String username) {
        Response response = new MessageAccessRequest(LoggedUser.getToken() , LoggedUser.getId() , MessageAccessType.FACTION , username).execute();
        return ((BooleanResponse)response).isResult();
    }

    public List<TrimmedFaction> getFactions() {
        Response response = new ListRequest(LoggedUser.getToken() , LoggedUser.getId() , ListType.FACTION , 0L).execute();
        return ((FactionResponse)response).getTrimmedFactions();
    }

    public HashMap<Long, String> getActiveFollowers() {
        Response response = new ListRequest(LoggedUser.getToken() , LoggedUser.getId() , ListType.FOLLOWERS , 0L).execute();
        return (((FactionResponse)response).getTrimmedFactions()).get(0).getMembers();
    }

    public HashMap<Long, String> getActiveFollowings() {
        Response response = new ListRequest(LoggedUser.getToken() , LoggedUser.getId() , ListType.FOLLOWINGS, 0L).execute();
        return (((FactionResponse)response).getTrimmedFactions()).get(0).getMembers();
    }

    public HashMap<Long, String> getActiveBlockedUsers() {
        Response response = new ListRequest(LoggedUser.getToken() , LoggedUser.getId() , ListType.BLACKLIST , 0L).execute();
        return (((FactionResponse)response).getTrimmedFactions()).get(0).getMembers();
    }

    public HashMap<Long, String> getGroupMembers(int factionId) {
        Response response = new ListRequest(LoggedUser.getToken() , LoggedUser.getId() , ListType.FACTION , 0L).execute();
        return (((FactionResponse)response).getTrimmedFactions()).stream().filter(it -> it.getId() == factionId).findAny().orElseThrow().getMembers();
    }

    public void deleteFaction(int factionId) {
        new FactionActionRequest(LoggedUser.getToken() ,
                LoggedUser.getId() ,
                FactionActionType.DELETE_FACTION ,
                factionId,
                0L,
                null,
                null
                ).execute();
    }

    public void deleteUserFromFaction(int factionId, long userId) {
        new FactionActionRequest(LoggedUser.getToken() ,
                LoggedUser.getId() ,
                FactionActionType.DELETE_MEMBER ,
                factionId,
                userId,
                null,
                null
        ).execute();    }

    public void addUserToFaction(int factionId, String username) {
        new FactionActionRequest(LoggedUser.getToken() ,
                LoggedUser.getId() ,
                FactionActionType.ADD_MEMBER ,
                factionId,
                0L,
                null,
                username
        ).execute();
    }

    public List<String> getFactionNames() {
        //todo: check
        ArrayList<String> factionNames = new ArrayList<>();
        Response response = new ListRequest(LoggedUser.getToken() , LoggedUser.getId() , ListType.FACTION , 0L).execute();
        return (((FactionResponse)response).getTrimmedFactions()).stream().map(TrimmedFaction::getName).collect(Collectors.toList());
    }
}