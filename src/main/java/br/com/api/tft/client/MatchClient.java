package br.com.api.tft.client;

import br.com.api.tft.dto.MatchDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(url = "${riot.api.match.base-url}", name = "match")
public interface MatchClient {

    @GetMapping("/by-puuid/{puuid}/ids?count=100")
    List<String> getMatchIDsFromPuuid(@PathVariable String puuid);

    @GetMapping("/by-puuid/{puuid}/ids")
    List<String> getMatchIDsFromPuuid(@RequestParam("count") int count);

    @GetMapping("/{match_id}")
    MatchDTO getMatchDataById(@PathVariable String matchId);
}
