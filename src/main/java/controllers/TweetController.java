package controllers;

import models.LoggedUser;
import models.requests.ListRequest;
import models.responses.ListResponse;
import models.responses.Response;
import models.types.ListType;

import java.util.ArrayList;

public class TweetController {

    public TweetController() {

    }

    public ArrayList<Long> getAllTweets(long userId) {
        Response response = new ListRequest(ListType.TWEETS, userId).execute();
        return ((ListResponse) response).getIds();
    }

    public ArrayList<Long> getTopTweets() throws ClassCastException {
        Response response = new ListRequest(ListType.EXPLORER, 0L).execute();
        return ((ListResponse) response).getIds();

    }

    public ArrayList<Long> getFollowingTweets() throws ClassCastException {
        Response response = new ListRequest(ListType.TIMELINE, 0L).execute();
        return ((ListResponse) response).getIds();

    }


}
