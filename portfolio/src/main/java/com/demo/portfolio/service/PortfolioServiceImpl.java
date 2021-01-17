package com.demo.portfolio.service;

import com.demo.portfolio.exception.NoSuchPortfolioException;
import com.demo.portfolio.model.Portfolio;
import com.demo.portfolio.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortfolioServiceImpl implements PortfolioService {
    @Autowired
    PortfolioRepository portfolioRepository;

    @Override
    public Portfolio getPortfolio(int id) {
        return portfolioRepository.findById(id).orElseThrow(() -> new NoSuchPortfolioException("There is not portfolio for the given Id"));
    }

    @Override
    public Portfolio updatePortfolio(int id, Portfolio portfolio) {
        Portfolio updatedPortfolio = getPortfolio(id);

        updatedPortfolio.setDescription(portfolio.getDescription());
        updatedPortfolio.setImage(portfolio.getImage());
        updatedPortfolio.setTitle(portfolio.getTitle());
        updatedPortfolio.setTwitterUser(portfolio.getTwitterUser());

        return portfolioRepository.save(updatedPortfolio);
    }
}
