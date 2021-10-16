package com.nothing.service.response

import com.nothing.annotations.springcomponents.InjectableService
import com.nothing.service.MatchHttpFetcher
import com.nothing.service.StatsProcessorAggregator

@InjectableService
class MatchResponseService {
    public final MatchHttpFetcher matchHttpFetcher
    public final StatsProcessorAggregator statsProcessorAggregator

    def getMatchResponse(String matchId) {
        def (matchData, team1Data, team2Data) = matchHttpFetcher.fullApiInfo(matchId)
        def (t1stats, t2stats) = statsProcessorAggregator.processStats(team1Data, team2Data)

        return [
                matchData: matchData,
                team1Info: team1Data[0], team2Info: team2Data[0],
                team1Stats: t1stats, team2Stats: t2stats
        ]
    }
}