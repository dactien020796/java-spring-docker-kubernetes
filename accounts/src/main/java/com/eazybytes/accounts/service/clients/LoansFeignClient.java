package com.eazybytes.accounts.service.clients;

import com.eazybytes.accounts.model.Loans;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "loanClient", url = "${urls.loan-service}")
public interface LoansFeignClient {

    @GetMapping("/loans")
    List<Loans> getLoansDetail(@RequestParam int customerId);
}
