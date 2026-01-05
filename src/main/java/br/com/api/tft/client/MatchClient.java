package br.com.api.tft.client;

import br.com.api.tft.config.KeyConfig;
import br.com.api.tft.dto.MatchDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(url = "${riot.api.match.base-url}", name = "match", configuration = KeyConfig.class)
public interface MatchClient {

    @GetMapping("/by-puuid/{puuid}/ids?count=200")
    List<String> getMatchIDsFromPuuid(@PathVariable String puuid);

    @GetMapping("/{matchId}")
    MatchDTO getMatchDataById(@PathVariable String matchId);
}
