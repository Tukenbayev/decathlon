package io.farel.resultcalculator.service;

import io.farel.resultcalculator.enums.DecathlonEvent;
import io.farel.resultcalculator.pojo.DecathlonResult;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class PrizePlaceService {

    public void calculateTotalScore(List<DecathlonResult> results) {
        results.forEach(result -> {
            result.addScore(DecathlonEvent._100M.calculateScore(result._100m));
            result.addScore(DecathlonEvent.LONG_JUMP.calculateScore(result.longJump));
            result.addScore(DecathlonEvent.SHOT_PUT.calculateScore(result.shotPut));
            result.addScore(DecathlonEvent.HIGH_JUMP.calculateScore(result.highJump));
            result.addScore(DecathlonEvent._400M.calculateScore(result._400m));
            result.addScore(DecathlonEvent.HURDLES_110M.calculateScore(result.hurdles110m));
            result.addScore(DecathlonEvent.DISCUS_THROW.calculateScore(result.discusThrow));
            result.addScore(DecathlonEvent.POLE_VAULT.calculateScore(result.poleVault));
            result.addScore(DecathlonEvent.JAVELIN_THROW.calculateScore(result.javelinThrow));
            result.addScore(DecathlonEvent._1500M.calculateScore(result._1500m));
        });
    }

    /**
     * Determines places and sorts "results"
     */
    public void determinePrizePlaces(List<DecathlonResult> results) {
        SortedMap<Integer, List<DecathlonResult>> places = new TreeMap<>(Collections.reverseOrder());

        results.forEach(result -> {
            if (!places.containsKey(result.totalScore)) {
                places.put(result.totalScore, new ArrayList<>(results.size()));
            }
            places.get(result.totalScore).add(result);
        });

        AtomicInteger placeCounter = new AtomicInteger(1);
        places.forEach((score, athletes) -> {
            String place = String.valueOf(placeCounter.get());
            athletes.forEach(athlete -> {
                if (athletes.size() > 1) {
                    athlete.place = place + "-" + (placeCounter.get() + athletes.size() - 1);
                } else {
                    athlete.place = place;
                }
            });

            placeCounter.addAndGet(athletes.size());
        });

        Collections.sort(results);
    }


}
