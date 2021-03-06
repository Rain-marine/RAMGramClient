package models.responses;


import models.trimmed.TrimmedTweet;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("tweet")
public class TweetResponse implements Response<Object>{

    private TrimmedTweet trimmedTweet;

    public TweetResponse(TrimmedTweet trimmedTweet) {
        this.trimmedTweet = trimmedTweet;
    }

    public TweetResponse() {

    }

    @Override
    public Object unleash() {

        return null;
    }

    public TrimmedTweet getTrimmedTweet() {
        return trimmedTweet;
    }

    public void setTrimmedTweet(TrimmedTweet trimmedTweet) {
        this.trimmedTweet = trimmedTweet;
    }
}
