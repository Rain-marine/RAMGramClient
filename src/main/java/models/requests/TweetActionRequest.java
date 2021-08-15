package models.requests;

import controllers.Controllers;
import models.NetworkData;
import models.responses.BooleanResponse;
import models.responses.Response;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("tweetAction")
public class TweetActionRequest implements Request {

    public enum TWEET_ACTION {LIKE, REPORT , SAVE , RETWEET}

    private String token;
    private long userId;
    private long tweetId;
    private TWEET_ACTION action;

    public TweetActionRequest(String token, long userId, long tweetId, TWEET_ACTION action) {
        this.token = token;
        this.userId = userId;
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

    public TWEET_ACTION getAction() {
        return action;
    }

    public void setAction(TWEET_ACTION action) {
        this.action = action;
    }
}
