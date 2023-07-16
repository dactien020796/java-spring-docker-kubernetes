/**
 * 
 */
package com.eazybytes.loans.controller;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.eazybytes.loans.model.Customer;
import com.eazybytes.loans.model.Loans;
import com.eazybytes.loans.repository.LoansRepository;

@RestController
@Slf4j
public class LoansController {

	@Autowired
	private LoansRepository loansRepository;

	@GetMapping("/loans")
	public List<Loans> getLoansDetails(@RequestParam int customerId) {
		log.info("getLoansDetails() start...");
		List<Loans> loans = loansRepository.findByCustomerIdOrderByStartDtDesc(customerId);
		log.info("getLoansDetails() end...");
		return loans;
	}
}
