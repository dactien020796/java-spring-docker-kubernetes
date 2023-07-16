/**
 * 
 */
package com.eazybytes.cards.controller;

import com.eazybytes.cards.model.Cards;
import com.eazybytes.cards.repository.CardsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class CardsController {

	@Autowired
	private CardsRepository cardsRepository;

	@GetMapping("/cards")
	public List<Cards> getCardDetails(@RequestParam int customerId) {
		log.info("getCardDetails() start...");
		List<Cards> cards = cardsRepository.findByCustomerId(customerId);
		log.info("getCardDetails() end...");
		return cards;
	}
}
