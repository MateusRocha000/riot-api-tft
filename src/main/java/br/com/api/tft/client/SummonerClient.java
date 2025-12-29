package br.com.api.tft.client;


import br.com.api.tft.dto.SummonerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "${riot.api.summoner.base-url}", name = "summoner")
public interface SummonerClient {

    @GetMapping("/by-name/{summonerName}")
    SummonerDTO getSummonerByName(@PathVariable String summonerName);

    @GetMapping("/by-puuid/{encryptedPUUID}")
    SummonerDTO getSummonerByPuuid(@PathVariable String encryptedPUUID);

    @GetMapping("/by-account/{accountId}")
    SummonerDTO getSummonerByAccountId(@PathVariable String accountId);

    @GetMapping("/{summonerId}")
    SummonerDTO getSummonerById(@PathVariable String summonerId);
}