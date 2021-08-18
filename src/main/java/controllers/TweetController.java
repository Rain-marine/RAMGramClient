package controllers;

import models.LoggedUser;
import models.requests.ListRequest;
import models.responses.ListResponse;
import models.responses.Response;

import java.util.ArrayList;

public class TweetController{

    public TweetController() {

    }

    public ArrayList<Long> getAllTweets(long userId) {
        Response response = new ListRequest(LoggedUser.getToken() , LoggedUser.getId() , ListRequest.TYPE.TWEETS , userId).execute();
        return ((ListResponse) response).getIds();
    }

    public ArrayList<Long> getTopTweets() {
        Response response = new ListRequest(LoggedUser.getToken() , LoggedUser.getId() , ListRequest.TYPE.EXPLORER , 0L).execute();
        return ((ListResponse) response).getIds();
    }

    public ArrayList<Long> getFollowingTweets() {
        Response response = new ListRequest(LoggedUser.getToken() , LoggedUser.getId() , ListRequest.TYPE.TIMELINE , 0L).execute();
        return ((ListResponse) response).getIds();
    }

}
