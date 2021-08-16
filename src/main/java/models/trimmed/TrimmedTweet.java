package models.trimmed;

import controllers.Controllers;
import repository.Repository;

import java.util.ArrayList;


public class TrimmedTweet{

    private long Id;
    private long writerId;
    private String tweetText;
    private String writerUsername;
    private ArrayList<String> likedUsernames;
    private byte[] tweetImage;
    private ArrayList<Long> commentsIds;
    private byte[] writerProfile;
    private String date;

    public TrimmedTweet() {
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public long getWriterId() {
        return writerId;
    }

    public void setWriterId(long writerId) {
        this.writerId = writerId;
    }

    public String getTweetText() {
        return tweetText;
    }

    public void setTweetText(String tweetText) {
        this.tweetText = tweetText;
    }

    public String getWriterUsername() {
        return writerUsername;
    }

    public void setWriterUsername(String writerUsername) {
        this.writerUsername = writerUsername;
    }

    public ArrayList<String> getLikedUsernames() {
        return likedUsernames;
    }

    public void setLikedUsernames(ArrayList<String> likedUsernames) {
        this.likedUsernames = likedUsernames;
    }

    public byte[] getTweetImage() {
        return tweetImage;
    }

    public void setTweetImage(byte[] tweetImage) {
        this.tweetImage = tweetImage;
    }

    public ArrayList<Long> getCommentsIds() {
        return commentsIds;
    }

    public void setCommentsIds(ArrayList<Long> commentsIds) {
        this.commentsIds = commentsIds;
    }

    public byte[] getWriterProfile() {
        return writerProfile;
    }

    public void setWriterProfile(byte[] writerProfile) {
        this.writerProfile = writerProfile;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
