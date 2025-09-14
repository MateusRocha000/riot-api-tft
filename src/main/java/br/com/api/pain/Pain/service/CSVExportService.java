package br.com.api.pain.Pain.service;

import br.com.api.pain.Pain.dto.MatchDTO;
import br.com.api.pain.Pain.dto.ParticipantDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CSVExportService {

    private static final Logger logger = LoggerFactory.getLogger(CSVExportService.class);

    public String exportPlayerDataToCSV (List<MatchDTO> data, String playerName) throws IOException {
        String filename = playerName + "_SoloQ_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".csv";

        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("MatchID,Game Duration,Win,Player,Team Position,Champion,ChampionTransform,Kills,Deaths,Assists," +
                    "TotalMinionsKilled,NeutralMinionsKilled,WardsPlaced,WardsKilled,VisionWardsBought,DetectorWardsPlaced,VisionScore,OnMyWayPings,NeedVisionPings,GetBackPings," +
                    "AssistMePings,AllInPings,EnemyMissingPings,EnemyVisionPings,HoldPings,CommandPings,GoldEared,GoldSpent,Item1,Item2," +
                    "Item3,Item4,Item5,Item6,Trinket,DragonKills,BaronKills,DamageDealtToObjectives,DamageDealtToTurrets,DamageSelfMitigated," +
                    "DamageDealtToBuildings,FirstBloodKill,FirstBloodAssist,FirstTowerKill,FirstTowerAssist,TurretKills,TurretTakedowns,TurretsLost,TotalDamageDealt,TotalDamageDealtToChampions," +
                    "TotalDamageShieldedOnTeammates,TotalDamageTaken,TotalEnemyJungleMinionsKilled,ControlWardTimeCoverageInRiverOrEnemyHalf,EarliestBaron,EarliestDragonTakedown,EarliestElderDragon,EarlyLaningPhaseGoldExpAdvantage,LaningPhaseGoldExpAdvantage,JunglerKillsEarlyJungle," +
                    "KillsOnLanersEarlyJungleAsJungler,MaxCsAdvantageOnLaneOpponent,MaxLevelLeadLaneOponent,TakedownsFirst25Minutes,VisionScoreAdvantageLaneOpponent,AbilityUses,ControlWardsPlaced,DPM,DamageTakenOnTeamPercentage,FirstTurretKilled," +
                    "GPM,JungleCsBefore10Minutes,KP,SoloKills,StealthWardsPlaced,TeamBaronKills,TeamDamagePercentage,TeamElderDragonKills,TeamRiftHeraldKills,TurretPlatesTaken," +
                    "TurretTakedowns,UnseenRecals,VSPM, WardsGuarded,Offense,Defense,Flex,PerkDescription0,Perk0,0Var1," +
                    "0Var2,0Var3,Perk1,1Var1,1Var2,1Var3,Perk2,2Var1,2Var2,2Var3," +
                    "Perk3,3Var1,3Var2,3Var3,PerkStyle0,PerkDescription1,Perk0,0Var1,0Var2,0Var3," +
                    "Perk1,1Var1,1Var2,1Var3,PerkStyle1\n"
            );

            for (MatchDTO matchDTO : data) {
                for (ParticipantDTO p : matchDTO.info().participants()) {
                    writer.write(String.format(
                                    "%s,%d,%s,%s,%s,%s,%s,%d,%d,%d," +
                                    "%d,%d,%d,%d,%d,%d,%d,%d,%d,%d," +
                                    "%d,%d,%d,%d,%d,%d,%d,%d,%d,%d," +
                                    "%d,%d,%d,%d,%d,%d,%d,%d,%d,%d," +
                                    "%d,%s,%s,%s,%s,%d,%d,%d,%d,%d," +
                                    "%d,%d,%d,%f,%d,%d,%d,%d,%d,%d," +
                                    "%d,%f,%d,%d,%f,%d,%d,%f,%f,%d," +
                                    "%s,%f,%f,%d,%d,%d,%f,%d,%d,%d," +
                                    "%d,%d,%f,%d,%d,%d,%d,%s,%d,%d," +
                                    "%d,%d,%d,%s,%d,%d,%d,%d,%s,%d," +
                                    "%d,%d,%d,%d,%d,%s,%d,%d,%d,%d," +
                                    "%d,%d,%d,%d,%d\n",
                            matchDTO.metadata().matchId(),matchDTO.info().gameDuration(),p.win(),p.riotIdGameName() + "#" + p.riotIdTagline(),p.teamPosition(),p.championName(),p.championTransform(),p.kills(),p.deaths(),p.assists(),
                            p.totalMinionsKilled(),p.neutralMinionsKilled(),p.wardsPlaced(),p.wardsKilled(),p.visionWardsBoughtInGame(),p.detectorWardsPlaced(),p.visionScore(),p.onMyWayPings(),p.needVisionPings(),p.getBackPings(),
                            p.assistMePings(),p.allInPings(),p.enemyMissingPings(),p.enemyVisionPings(),p.holdPings(),p.commandPings(),p.goldEarned(),p.goldSpent(),p.item0(),p.item1(),
                            p.item2(),p.item3(),p.item4(),p.item5(),p.item6(),p.dragonKills(),p.baronKills(),p.damageDealtTiIObjectives(),p.damageDealtToTurrets(),p.damageSelfMitigated(),
                            p.damageDealtToBuildings(),p.firstBloodKill(),p.firstBloodAssist(),p.firstTowerKill(),p.firstTowerAssist(),p.turretKills(),p.turretTakedowns(),p.turretsLost(),p.totalDamageDealt(),p.totalDamageDealtToChampions(),
                            p.totalDamageShieldedOnTeammates(),p.totalDamageTaken(),p.totalEnemyJungleMinionsKilled(),p.challenges().controlWardTimeCoverageInRiverOrEnemyHalf(),p.challenges().earliestBaron(),p.challenges().earliestDragonTakedown(),p.challenges().earliestElderDragon(),p.challenges().earlyLaningPhaseGoldExpAdvantage(),p.challenges().laningPhaseGoldExpAdvantage(),p.challenges().junglerKillsEarlyJungle(),
                            p.challenges().killsOnLanersEarlyJungleAsJungler(),p.challenges().maxCsAdvantageOnLaneOpponent(),p.challenges().maxLevelLeadLaneOpponent(),p.challenges().takedownsFirst25Minutes(),p.challenges().visionScoreAdvantageLaneOpponent(),p.challenges().abilityUses(),p.challenges().controlWardsPlaced(),p.challenges().damagePerMinute(),p.challenges().damageTakenOnTeamPercentage(),p.challenges().firstTurretKilled(),
                            p.challenges().goldPerMinute(),p.challenges().jungleCsBefore10Minutes(),p.challenges().killParticipation(),p.challenges().soloKills(),p.challenges().stealthWardsPlaced(),p.challenges().teamBaronKills(),p.challenges().teamDamagePercentage(),p.challenges().teamElderDragonKills(),p.challenges().teamRiftHeraldKills(),p.challenges().turretPlatesTaken(),
                            p.challenges().turretTakedowns(),p.challenges().unseenRecalls(),p.challenges().visionScorePerMinute(),p.challenges().wardsGuarded(),p.perks().statPerks().offense(),p.perks().statPerks().defense(),p.perks().statPerks().flex(),p.perks().styles().get(0).description(),p.perks().styles().getFirst().selections().get(0).perk(),p.perks().styles().getFirst().selections().get(0).var1(),
                            p.perks().styles().getFirst().selections().get(0).var2(),p.perks().styles().getFirst().selections().get(0).var3(),p.perks().styles().getFirst().selections().get(1).perk(),p.perks().styles().getFirst().selections().get(1).var1(),p.perks().styles().getFirst().selections().get(1).var2(),p.perks().styles().getFirst().selections().get(1).var3(),p.perks().styles().getFirst().selections().get(2).perk(),p.perks().styles().getFirst().selections().get(2).var1(),p.perks().styles().getFirst().selections().get(2).var2(),p.perks().styles().getFirst().selections().get(2).var3(),
                            p.perks().styles().getFirst().selections().get(3).perk(),p.perks().styles().getFirst().selections().get(3).var1(),p.perks().styles().getFirst().selections().get(3).var2(),p.perks().styles().getFirst().selections().get(3).var3(),p.perks().styles().get(0).style(),p.perks().styles().get(1).description(),p.perks().styles().getFirst().selections().get(0).perk(),p.perks().styles().getFirst().selections().get(0).var1(),p.perks().styles().getFirst().selections().get(0).var2(),p.perks().styles().getFirst().selections().get(0).var3(),
                            p.perks().styles().getFirst().selections().get(1).perk(),p.perks().styles().getFirst().selections().get(1).var1(),p.perks().styles().getFirst().selections().get(1).var2(),p.perks().styles().getFirst().selections().get(1).var3(),p.perks().styles().get(1).style()
                    ));
                }
            }
        }
        logger.info("CSV Exported: {}", filename);
        return filename;
    }
}
