package com.demo.portfolio;

import com.demo.portfolio.exception.NoSuchPortfolioException;
import com.demo.portfolio.model.Portfolio;
import com.demo.portfolio.repository.PortfolioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class PortfolioIntegrationTests {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private PortfolioRepository portfolioRepository;

	private static final int EXISTING_PORTFOLIO_ID = 987654321;

	private static final int NON_EXISTING_PORTFOLIO_ID = 123456789;

	private Portfolio portfolioObject;

	@BeforeEach
	private void setUp (){
		portfolioObject = new Portfolio(EXISTING_PORTFOLIO_ID, "https://pbs.twimg.com/profile_images/668279339838935040/8sUE9d4C_400x400.jpg", "Tyrion Lannister", "Tyrion of House Lannister. Imp, Halfman. Never forget what you are, for surely the world will not. Not affiliated with #GameofThrones or HBO", "GoT_Tyrion");
		portfolioRepository.save(portfolioObject);
	}

	@Test
	void getPortfolioReturnsExistingPortfolio() throws Exception {

		MvcResult result =
				mockMvc.perform(get("/portfolio/{id}", portfolioObject.getId()))
						.andDo(print())
						.andExpect(status().isOk())
						.andReturn();

		String contentAsString = result.getResponse().getContentAsString();
		JSONObject jsonObject = new JSONObject(contentAsString);
		Portfolio obtainedPortfolio = objectMapper.readValue(jsonObject.getString("portfolio"), Portfolio.class);

		assertThat(obtainedPortfolio.getTitle()).isEqualTo(portfolioObject.getTitle());
	}

	@Test
	void getNonExistingPortfolioReturnsNotFound() throws Exception {
		mockMvc.perform(get("/portfolio/{id}", NON_EXISTING_PORTFOLIO_ID))
				.andDo(print())
				.andExpect(status().isNotFound());
	}

	@Test
	void getPortfolioWithInvalidTwitterUserReturnsNotFound() throws Exception {
		portfolioObject.setTwitterUser("nonexistinguser_192837465");
		portfolioRepository.save(portfolioObject);

		mockMvc.perform(get("/portfolio/{id}", portfolioObject.getId()))
				.andDo(print())
				.andExpect(status().isNotFound());
	}

	@Test
	void updatePortfolioModifyThePortfolio() throws Exception {
		portfolioObject.setTitle("updatePortfolioModifyThePortfolio");

		mockMvc.perform(put("/portfolio/{id}", portfolioObject.getId())
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(portfolioObject)))
				.andExpect(status().isOk());
		Portfolio updatedPortfolio = portfolioRepository.findById(portfolioObject.getId()).orElseThrow(() -> new NoSuchPortfolioException("There is not portfolio for the given Id"));

		assertThat(updatedPortfolio.getTitle()).isEqualTo("updatePortfolioModifyThePortfolio");
	}

	@Test
	void updatePortfolioWithBlankArgumentsReturnsBadRequest() throws Exception {
		portfolioObject.setTitle("");

		mockMvc.perform(put("/portfolio/{id}", portfolioObject.getId())
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(portfolioObject)))
				.andExpect(status().isBadRequest());
	}

	@Test
	void updateNonExistingPortfolioReturnsNotFound() throws Exception {
		mockMvc.perform(put("/portfolio/{id}", NON_EXISTING_PORTFOLIO_ID)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(portfolioObject)))
				.andExpect(status().isNotFound());
	}
}
