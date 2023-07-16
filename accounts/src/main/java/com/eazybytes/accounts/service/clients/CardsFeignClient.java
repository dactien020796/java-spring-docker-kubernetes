package com.eazybytes.accounts.service.clients;

import com.eazybytes.accounts.model.Cards;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "cardClient", url = "${urls.card-service}")
public interface CardsFeignClient {

    @GetMapping("/cards")
    List<Cards> getCardsDetail(@RequestParam int customerId);
}
