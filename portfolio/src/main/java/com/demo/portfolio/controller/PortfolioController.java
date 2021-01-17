package com.demo.portfolio.controller;

import com.demo.portfolio.dto.PortfolioDTO;
import com.demo.portfolio.model.Portfolio;
import com.demo.portfolio.service.PortfolioService;
import com.demo.portfolio.service.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {
    @Autowired
    PortfolioService portfolioService;
    @Autowired
    TwitterService twitterService;

    @GetMapping("/{id}")
    public PortfolioDTO getPortfolio(@PathVariable(value="id") int id){
        Portfolio portfolio = portfolioService.getPortfolio(id);
        List<Tweet> tweets = twitterService.getLastTweets(portfolio.getTwitterUser());
        // List<Tweet> tweets = new ArrayList<>();

        return new PortfolioDTO(portfolio, tweets);
    }

    @PutMapping(value="/{id}")
    public Portfolio updatePortfolio(@PathVariable(value="id") int id, @RequestBody @Valid Portfolio portfolio){
        return portfolioService.updatePortfolio(id, portfolio);
    }
}
