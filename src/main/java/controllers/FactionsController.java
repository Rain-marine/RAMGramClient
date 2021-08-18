package controllers;

import models.Group;
import models.LoggedUser;
import models.User;
import models.requests.ListRequest;
import models.requests.MessageAccessRequest;
import models.responses.BooleanResponse;
import models.responses.FactionResponse;
import models.responses.Response;
import models.trimmed.TrimmedFaction;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import repository.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class FactionsController implements Repository {

    public FactionsController() {
    }

    public void insertNewFaction(String name, List<String> users) {
        User loggedUser = USER_REPOSITORY.getById(LoggedUser.getLoggedUser().getId());
        Group newGroup = new Group(name, loggedUser);

        List<User> members = new ArrayList<>();
        for (String username : users) {
            if (members.stream().noneMatch(it -> it.getUsername().equals(username)))
                members.add(USER_REPOSITORY.getByUsername(username));
        }
        newGroup.setMembers(members);
        FACTION_REPOSITORY.insert(newGroup);
    }

    public boolean canAddToGroup(String username) {
        Response response = new MessageAccessRequest(LoggedUser.getToken() , LoggedUser.getId() , MessageAccessRequest.TYPE.FACTION , username).execute();
        return ((BooleanResponse)response).isResult();
    }

    public List<TrimmedFaction> getFactions() {
        Response response = new ListRequest(LoggedUser.getToken() , LoggedUser.getId() , ListRequest.TYPE.FACTION , 0L).execute();
        return ((FactionResponse)response).getTrimmedFactions();
    }

    public HashMap<Long, String> getActiveFollowers() {
        Response response = new ListRequest(LoggedUser.getToken() , LoggedUser.getId() , ListRequest.TYPE.FOLLOWERS , 0L).execute();
        return (((FactionResponse)response).getTrimmedFactions()).get(0).getMembers();
    }

    public HashMap<Long, String> getActiveFollowings() {
        Response response = new ListRequest(LoggedUser.getToken() , LoggedUser.getId() , ListRequest.TYPE.BLACKLIST , 0L).execute();
        return (((FactionResponse)response).getTrimmedFactions()).get(0).getMembers();
    }

    public HashMap<Long, String> getActiveBlockedUsers() {
        Response response = new ListRequest(LoggedUser.getToken() , LoggedUser.getId() , ListRequest.TYPE.FOLLOWERS , 0L).execute();
        return (((FactionResponse)response).getTrimmedFactions()).get(0).getMembers();
    }

    public HashMap<Long, String> getGroupMembers(int factionId) {
        Response response = new ListRequest(LoggedUser.getToken() , LoggedUser.getId() , ListRequest.TYPE.FACTION , 0L).execute();
        return (((FactionResponse)response).getTrimmedFactions()).stream().filter(it -> it.getId() == factionId).findAny().orElseThrow().getMembers();
    }

    public void deleteFaction(int factionId) {

    }

    public void deleteUserFromFaction(int factionId, long userId) {
        FACTION_REPOSITORY.deleteUserFromFaction(factionId, userId);
    }

    public void addUserToFaction(int factionId, String username) {
        FACTION_REPOSITORY.addUserToFaction(factionId, USER_REPOSITORY.getByUsername(username).getId());
    }

    public List<String> getFactionNames() {
        ArrayList<String> factionNames = new ArrayList<>();
        Response response = new ListRequest(LoggedUser.getToken() , LoggedUser.getId() , ListRequest.TYPE.FACTION , 0L).execute();
        return (((FactionResponse)response).getTrimmedFactions()).stream().map(it -> it.getName()).collect(Collectors.toList());
    }
}