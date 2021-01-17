package com.demo.portfolio.service;

import com.demo.portfolio.model.Portfolio;

public interface PortfolioService {

    Portfolio getPortfolio(int id);

    Portfolio updatePortfolio(int id, Portfolio portfolio);
}
