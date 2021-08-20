package models.requests;

import controllers.Controllers;
import models.LoggedUser;
import models.NetworkData;
import models.responses.BooleanResponse;
import models.responses.Response;
import models.types.TweetActionType;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("tweetAction")
public class TweetActionRequest implements Request {

    private String token;
    private long userId;
    private long tweetId;
    private TweetActionType action;

    public TweetActionRequest(long tweetId, TweetActionType action) {
        this.token = LoggedUser.getToken();
        this.userId = LoggedUser.getId();
        this.tweetId = tweetId;
        this.action = action;
    }

    public TweetActionRequest() {
    }

    @Override
    public Response execute() {
        return NetworkData.sendRequest(this);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getTweetId() {
        return tweetId;
    }

    public void setTweetId(long tweetId) {
        this.tweetId = tweetId;
    }

    public TweetActionType getAction() {
        return action;
    }

    public void setAction(TweetActionType action) {
        this.action = action;
    }
}
