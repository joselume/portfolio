package com.demo.portfolio.dto;

import com.demo.portfolio.model.Portfolio;
import org.springframework.social.twitter.api.Tweet;

import java.util.List;

public class PortfolioDTO {

    private Portfolio portfolio;
    private List<Tweet> tweets;

    public PortfolioDTO(Portfolio portfolio, List<Tweet> tweets) {
        this.portfolio = portfolio;
        this.tweets = tweets;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }
}
