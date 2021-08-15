package models.requests;

import controllers.Controllers;
import models.NetworkData;
import models.responses.BooleanResponse;
import models.responses.Response;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("addComment")
public class AddCommentRequest implements Request{

    private String token;
    private long userId;
    private long tweetId;
    private String commentText;
    private byte[] commentImage;

    public AddCommentRequest(String token, long userId, long tweetId, String commentText, byte[] commentImage) {
        this.token = token;
        this.userId = userId;
        this.tweetId = tweetId;
        this.commentText = commentText;
        this.commentImage = commentImage;
    }

    @Override
    public Response execute() {
       return NetworkData.sendRequest(this);
    }

    public AddCommentRequest() {
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

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public byte[] getCommentImage() {
        return commentImage;
    }

    public void setCommentImage(byte[] commentImage) {
        this.commentImage = commentImage;
    }
}
