package controllers;

import models.LoggedUser;
import models.Tweet;
import models.User;
import models.requests.ListRequest;
import models.responses.ListResponse;
import models.responses.Response;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import repository.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TweetController implements Repository {
    private final static Logger log = LogManager.getLogger(TweetController.class);


    private final FactionsController factionsController;

    public TweetController() {
        factionsController = new FactionsController();
    }


    public void addTweet(String text, byte[] image){
        Tweet tweet = new Tweet(LoggedUser.getLoggedUser(),text, image);
        TWEET_REPOSITORY.insert(tweet);
        log.info("new tweet");
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

    public void saveTweet(long tweetId) {
        USER_REPOSITORY.addFavoriteTweet(LoggedUser.getLoggedUser().getId(), tweetId);

    }

    public void retweet(long currentTweetId) {
        USER_REPOSITORY.addRetweet(currentTweetId,LoggedUser.getLoggedUser().getId());
    }

    public boolean reportSpam(long currentTweetId) {
        return false;
    }

    public void addComment(String comment,byte[] image , long rawParentTweetId) {
        Tweet parentTweet = TWEET_REPOSITORY.getById(rawParentTweetId);
        Tweet commentTweet = new Tweet(USER_REPOSITORY.getById(LoggedUser.getLoggedUser().getId()),comment, image);
        TWEET_REPOSITORY.addComment(parentTweet,commentTweet);
    }

    public boolean isLiked (long tweetId){
        Tweet completeTweet = TWEET_REPOSITORY.getById(tweetId);
        for (User user : completeTweet.getUsersWhoLiked()) {
            if (user.getUsername().equals(LoggedUser.getLoggedUser().getUsername()))
                return true;
        }
        return false;

    }

    public boolean like(long tweetId) {
        Tweet completeTweet = TWEET_REPOSITORY.getById(tweetId);
        for (User user : completeTweet.getUsersWhoLiked()) {
            if (user.getUsername().equals(LoggedUser.getLoggedUser().getUsername()))
                return false;
        }
        TWEET_REPOSITORY.like(LoggedUser.getLoggedUser().getId(), tweetId);
        return true;
    }

    public boolean isSelfTweet(long tweetId) {
        Tweet tweet = TWEET_REPOSITORY.getById(tweetId);
        return tweet.getUser().getUsername().equals(LoggedUser.getLoggedUser().getUsername());
    }

    public long getWriterId(long tweetId) {
        return TWEET_REPOSITORY.getById(tweetId).getUser().getId();
    }

    public String getTweetText(long tweetId) {
        return TWEET_REPOSITORY.getById(tweetId).getText();
    }

    public String getWriterUsername(long tweetId) {
        return TWEET_REPOSITORY.getById(tweetId).getUser().getUsername();
    }

    public String getTweetDate(long tweetId) {
        return TWEET_REPOSITORY.getById(tweetId).getTweetDateTime().toString();
    }

    public byte[] getTweetImage(long tweetId) {
        return TWEET_REPOSITORY.getById(tweetId).getImage();
    }

    public ArrayList<Long> getTweetComments(long tweetId) {
        List<Tweet> comments = TWEET_REPOSITORY.getById(tweetId).getComments();
        ArrayList<Long> commentId = new ArrayList<>();
        for (Tweet comment : comments) {
            commentId.add(comment.getId());
        }
        return commentId;
    }

    public ArrayList<String> getLikedList(long tweetId) {
        ArrayList<String> liked = new ArrayList<>();
        for (User user : TWEET_REPOSITORY.getById(tweetId).getUsersWhoLiked()) {
            liked.add(user.getUsername());
        }
        return liked;
    }
}
