package com.revinate.instagram;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Your Instagram object will be instantiated and called as such:
 * Instagram instagram = new Instagram();
 * instagram.postMedia(userId,mediaId);
 * List<Integer> feed = instagram.getMediaFeed(userId);
 * instagram.follow(followerId,followedId);
 * instagram.unfollow(followerId,followedId);
 */

class Media {
    int id, time;
    Media next;
    
    public Media(int id, int time) {
        this.id = id;
        this.time = time;
    }
}

class User {
    int id;
    Set<Integer> followed;
    Media mediaHead;
    
    public User(int id) {
        this.id = id;
        followed = new HashSet<>();
        follow(id);
    }
    
    public void follow(int id){
        followed.add(id);
    }
        
    public void unfollow(int id){
        followed.remove(id);
    }
        
    // everytime user post a new media, add it to the head of media list.
    public void post(int id, int time){
        Media m = new Media(id, time);
        m.next = mediaHead;
        mediaHead = m;
    }
}

public class Instagram {
    HashMap<Integer, User> userMap;
    int timeStamp = 0;

    /** Initialize your data structure here. */
    public Instagram() {
        userMap = new HashMap<>();
    }

    /** Add a new media. */
    public void postMedia(int userId, int mediaId) {
        if (!userMap.containsKey(userId)) {
            User u = new User(userId);
            userMap.put(userId, u);
        }
        userMap.get(userId).post(mediaId, timeStamp++);
    }

    /** Retrieve the 10 most recent media ids in the user's media feed.
     * Each media must be posted by the user herself or by someone the user follows
     * Media must be ordered from most recent to least recent. */
    public List<Integer> getMediaFeed(int userId) {
        List<Integer> res = new LinkedList<>();
        if (!userMap.containsKey(userId)) return res;
        Set<Integer> users = userMap.get(userId).followed;
        PriorityQueue<Media> feed = new PriorityQueue<>(users.size(),(m1, m2) -> m2.time - m1.time);
        for (int u : users) {
            Media m = userMap.get(u).mediaHead;
            if (m != null) feed.add(m);
        }
        while (feed.size() > 0 && res.size() < 10) {
            Media m = feed.remove();
            res.add(m.id);
            if (m.next != null) feed.add(m.next);
        }
        return res;
    }

    /** A Follower follows a followed. Nothing happens if invalid */
    public void follow(int followerId, int followedId) {
        if (!userMap.containsKey(followerId)) {
            User u = new User(followerId);
            userMap.put(followerId, u);
        }
        if (!userMap.containsKey(followedId)) {
            User u = new User(followedId);
            userMap.put(followedId, u);
        }
        userMap.get(followerId).follow(followedId);
    }

    /** A Follower unfollows a followed. Nothing happens if invalid */
    public void unfollow(int followerId, int followedId) {
        if (userMap.containsKey(followerId) && followedId != followerId) { 
            userMap.get(followerId).unfollow(followedId);
        }
    }
}


