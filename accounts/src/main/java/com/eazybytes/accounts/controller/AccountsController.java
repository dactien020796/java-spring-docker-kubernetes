/**
 * 
 */
package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.config.AccountServiceConfig;
import com.eazybytes.accounts.model.Accounts;
import com.eazybytes.accounts.model.Customer;
import com.eazybytes.accounts.model.CustomerDetails;
import com.eazybytes.accounts.repository.AccountsRepository;
import com.eazybytes.accounts.service.clients.CardsFeignClient;
import com.eazybytes.accounts.service.clients.LoansFeignClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Eazy Bytes
 *
 */

@RestController
@AllArgsConstructor
public class AccountsController {

	private final AccountsRepository accountsRepository;
	private final AccountServiceConfig config;
	private final CardsFeignClient cardsFeignClient;
	private final LoansFeignClient loansFeignClient;

	@SneakyThrows
	@GetMapping
	public String hello() {
		AccountsController.ServiceConfig serviceConfig = new AccountsController.ServiceConfig(config);
		return new ObjectMapper().writeValueAsString(serviceConfig);
	}

	@PostMapping("/myAccount")
	public Accounts getAccountDetails(@RequestBody Customer customer) {

		Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId());
		if (accounts != null) {
			return accounts;
		} else {
			return null;
		}

	}

	@GetMapping("/myCustomerDetails")
	public CustomerDetails getCustomerDetail(@RequestBody Customer customer) {
		CustomerDetails customerDetails = new CustomerDetails();
		customerDetails.setAccounts(accountsRepository.findByCustomerId(customer.getCustomerId()));
		customerDetails.setLoans(loansFeignClient.getLoansDetail(customer));
		customerDetails.setCards(cardsFeignClient.getCardsDetail(customer));
		return customerDetails;
	}

	@Getter
	@Setter
	private static class ServiceConfig {
		private String msg;
		private String buildVersion;
		private Map<String,String> mailDetails;
		private List<String> activeBranches;

		public ServiceConfig(AccountServiceConfig config) {
			this.msg = config.getMsg();
			this.buildVersion = config.getBuildVersion();
			this.mailDetails = config.getMailDetails();
			this.activeBranches = config.getActiveBranches();
		}
	}

}
