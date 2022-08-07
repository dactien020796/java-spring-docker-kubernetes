/**
 * 
 */
package com.eazybytes.cards.controller;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eazybytes.cards.model.Cards;
import com.eazybytes.cards.model.Customer;
import com.eazybytes.cards.repository.CardsRepository;

/**
 * @author Eazy Bytes
 *
 */

@RestController
@Slf4j
public class CardsController {

	@Autowired
	private CardsRepository cardsRepository;

	@PostMapping("/myCards")
	public List<Cards> getCardDetails(@RequestBody Customer customer) {
		log.info("getCardDetails() start...");
		List<Cards> cards = cardsRepository.findByCustomerId(customer.getCustomerId());
		log.info("getCardDetails() end...");
		if (cards != null) {
			return cards;
		} else {
			return null;
		}

	}

}
