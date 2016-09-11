package com.revinate.instagram;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class InstagramTest {

    @Test
    public void getMediaFeed() throws Exception {
        Instagram instagram = new Instagram();
        instagram.postMedia(1,1025);
        Thread.sleep(1);
        instagram.postMedia(2,1135);
        Thread.sleep(1);
        instagram.postMedia(3,1092);
        Thread.sleep(1);
        instagram.postMedia(3,3094);
        Thread.sleep(1);
        instagram.postMedia(1,1022);

        instagram.follow(1, 2);
        instagram.follow(1, 3);

        List<Integer> feed = instagram.getMediaFeed(1);
        assertThat(feed.get(0), is(1022));
        assertThat(feed.get(1), is(3094));
        assertThat(feed.get(2), is(1092));
        assertThat(feed.get(3), is(1135));
        assertThat(feed.get(4), is(1025));

        instagram.unfollow(1, 3);

        List<Integer> feed_2 = instagram.getMediaFeed(1);
        assertThat(feed_2.get(0), is(1022));
        assertThat(feed_2.get(1), is(1135));
        assertThat(feed_2.get(2), is(1025));
    }
}