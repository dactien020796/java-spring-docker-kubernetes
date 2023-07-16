/**
 * 
 */
package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.model.AccountDetail;
import com.eazybytes.accounts.model.Accounts;
import com.eazybytes.accounts.repository.AccountsRepository;
import com.eazybytes.accounts.service.clients.CardsFeignClient;
import com.eazybytes.accounts.service.clients.LoansFeignClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Eazy Bytes
 *
 */

@RestController
@AllArgsConstructor
@Slf4j
public class AccountsController {

	private final AccountsRepository accountsRepository;
	private final CardsFeignClient cardsFeignClient;
	private final LoansFeignClient loansFeignClient;

	@GetMapping("/accounts")
	@Timed(value="request.latency", extraTags = {"api", "getAccount"})
	@Counted(value="request.latency", extraTags = {"api", "getAccount"})
	public Accounts getAccount(@RequestParam int customerId) {
		log.info("getAccount() start...");
		var result = accountsRepository.findByCustomerId(customerId);
		log.info("getAccount() end... result = {}", result);
		return result;
	}

	@GetMapping("/accounts/detail")
	@CircuitBreaker(name = "getCustomerDetail", fallbackMethod = "fallbackCustomerDetail")
	@Timed(value="request.latency", extraTags = {"api", "getAccountDetail"})
	@Counted(value="request.latency", extraTags = {"api", "getAccountDetail"})
	public AccountDetail getAccountDetail(@RequestParam int customerId) {
		log.info("getAccountDetail() start...");
		AccountDetail accountDetail = new AccountDetail();
		accountDetail.setAccounts(accountsRepository.findByCustomerId(customerId));
		log.info("getAccountDetail() start calling loan-service...");
		accountDetail.setLoans(loansFeignClient.getLoansDetail(customerId));
		log.info("getAccountDetail() start calling card-service...");
		accountDetail.setCards(cardsFeignClient.getCardsDetail(customerId));
		log.info("getAccountDetail() end... result = {}", accountDetail);
		return accountDetail;
	}

	@GetMapping("/exception")
	@Timed(value="request.latency", extraTags = {"api", "throwException"})
	@Counted(value="request.latency", extraTags = {"api", "throwException"})
	public void throwException() throws Exception {
		throw new Exception("An unexpected error occurs...");
	}

	private AccountDetail fallbackCustomerDetail(int customerId, Throwable throwable) {
		log.info("Fallback method for getCustomerDetail()");
		AccountDetail accountDetail = new AccountDetail();
		accountDetail.setAccounts(accountsRepository.findByCustomerId(customerId));
		accountDetail.setLoans(loansFeignClient.getLoansDetail(customerId));
		return accountDetail;
	}

}
