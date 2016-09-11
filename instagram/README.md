# engineering-quiz-java/instagram

You are building a simplified version of Instagram.

Complete the Instagram.java and InstagramTest.java files.

To run your tests
```
./gradlew test
```

Example:
```
Instagram instagram = new Instagram();

// User 1 posts a new media (id = 5).
instagram.postMedia(1, 5);

// User 1's media feed should return a list with 1 media id -> [5].
instagram.getMediaFeed(1);

// User 1 follows user 2.
instagram.follow(1, 2);

// User 2 posts a new media (id = 3).
instagram.postMedia(2, 3);

// User 1's media feed should return a list with 2 media ids -> [3, 5].
// Media id 3 should precede media id 5 because it is posted after media id 5.
instagram.getMediaFeed(1);

// User 1 unfollows user 2.
instagram.unfollow(1, 2);

// User 1's media feed should return a list with 1 media id -> [5],
// since user 1 is no longer following user 2.
instagram.getMediaFeed(1);
```