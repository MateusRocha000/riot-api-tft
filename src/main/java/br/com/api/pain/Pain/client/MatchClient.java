package br.com.api.pain.Pain.client;

import br.com.api.pain.Pain.dto.MatchDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(url = "${riot.api.match.base-url}", name = "match")
public interface MatchClient {

    @GetMapping("/by-puuid/{puuid}/ids?queue=420&start=0&count=100")
    List<String> getMatchIDsFromPuuid(@PathVariable String puuid);

    @GetMapping("/by-puuid/{puuid}/ids")
    List<String> getMatchIDsFromPuuid(@PathVariable String puuid, @RequestParam("start") int start, @RequestParam("count") int count, @RequestParam("queue") int queue);

    @GetMapping("/{matchId}")
    MatchDTO getMatchDataById(@PathVariable String matchId);
}
