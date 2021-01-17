package com.demo.portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TwitterServiceImpl implements TwitterService{

    private static final int RETRIEVED_TWEETS = 5;

    @Autowired
    private Twitter twitter;

    @Override
    public List<Tweet> getLastTweets(String userName) {
        return twitter.timelineOperations().getUserTimeline(userName, RETRIEVED_TWEETS);
    }
}
