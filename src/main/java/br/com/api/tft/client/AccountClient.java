package br.com.api.tft.client;


import br.com.api.tft.dto.AccountResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "${riot.api.account.base-url}", name = "account")
public interface AccountClient {

    @GetMapping(path = "/by-puuid/{puuid}")
    AccountResponseDTO getAccountByPuuid(@PathVariable String puuid);

    @GetMapping(path = "/by-riot-id/{gameName}/{tagLine}")
    AccountResponseDTO getAccountByNickTag(@PathVariable String gameName, @PathVariable String tagLine);
}
