package controllers;

import models.LoggedUser;
import models.requests.ListRequest;
import models.responses.ListResponse;
import models.responses.Response;
import models.types.ListType;

import java.util.ArrayList;

public class TweetController{

    public TweetController() {

    }

    public ArrayList<Long> getAllTweets(long userId) {
        return (ArrayList<Long>) new ListRequest(LoggedUser.getToken() , LoggedUser.getId() , ListType.TWEETS , userId).execute().unleash();
    }

    public ArrayList<Long> getTopTweets() {
        return (ArrayList<Long>) new ListRequest(LoggedUser.getToken() , LoggedUser.getId() , ListType.EXPLORER , 0L).execute().unleash();
    }

    public ArrayList<Long> getFollowingTweets() {
        return (ArrayList<Long>) new ListRequest(LoggedUser.getToken() , LoggedUser.getId() , ListType.TIMELINE , 0L).execute().unleash();
    }



}
