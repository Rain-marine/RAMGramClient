package controllers;


import models.LoggedUser;
import models.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import repository.Repository;

import java.util.Date;
import java.util.List;


public class SettingController implements Repository {
    private final static Logger log = LogManager.getLogger(SettingController.class);


    public SettingController() {
    }

    public void logout() {
        if (!(LoggedUser.getLoggedUser() == null))
            USER_REPOSITORY.setLastSeen(LoggedUser.getLoggedUser().getId(), new Date());
        LoggedUser.setLoggedUser(null);
    }

    public String lastSeenForLoggedUser(long rawUserId) {
        User user = USER_REPOSITORY.getById(rawUserId);
        long loggedUserId = LoggedUser.getLoggedUser().getId();
        String status = USER_REPOSITORY.getById(user.getId()).getLastSeenStatus();
        if (user.getFollowings().stream().noneMatch(it -> it.getId() == loggedUserId)) {
            return ("last seen recently");
        } else if (status.equals("everybody"))
            return (user.getLastSeen().toString());
        else if (status.equals("following")) {
            List<User> userFollowing = user.getFollowings();
            for (User following : userFollowing) {
                if (following.getId() == loggedUserId) {
                    return (user.getLastSeen().toString());
                }
            }
        }
        return ("last seen recently");
    }

    public String birthdayForLoggedUser(long userId) {
        User user = USER_REPOSITORY.getById(userId);
        User.Level status = user.isBirthDayVisible();
        if (user.getBirthday() != null){
            if (status == User.Level.FOLLOWING) {
                long loggedUserId = LoggedUser.getLoggedUser().getId();
                List<User> following = user.getFollowings();
                for (User followed : following) {
                    if (followed.getId() == loggedUserId) {
                        return user.getBirthday().toString();
                    }
                }
                return "not visible";
            } else if (status == User.Level.ALL) {
                return user.getBirthday().toString();
            } else {
                return "not visible";
            }
        }
        else
            return "not visible";
    }


    public String emailForLoggedUser(long userId) {
        User user = USER_REPOSITORY.getById(userId);
        User.Level status = user.isEmailVisible();
        if (status == User.Level.FOLLOWING) {
            long loggedUserId = LoggedUser.getLoggedUser().getId();
            List<User> following = user.getFollowings();
            for (User followed : following) {
                if (followed.getId() == loggedUserId) {
                    return user.getEmail();
                }
            }
            return "not visible";
        } else if (status == User.Level.ALL) {
            return user.getEmail();
        } else {
            return "not visible";
        }

    }

    public String phoneNumberForLoggedUser(long userId) {
        User user = USER_REPOSITORY.getById(userId);
        User.Level status = user.isPhoneNumberVisible();
        if (!user.getPhoneNumber().equals("")){
            if (status == User.Level.FOLLOWING) {
                long loggedUserId = LoggedUser.getLoggedUser().getId();
                List<User> following = user.getFollowings();
                for (User followed : following) {
                    if (followed.getId() == loggedUserId) {
                        return user.getPhoneNumber();
                    }
                }
                return "not visible";
            } else if (status == User.Level.ALL) {
                return user.getPhoneNumber();
            } else {
                return "not visible";
            }
        }
        else {
            return "not visible";
        }


    }

}
