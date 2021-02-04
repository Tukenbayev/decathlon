package io.farel.resultcalculator;

import io.farel.resultcalculator.pojo.DecathlonResult;
import io.farel.resultcalculator.service.PrizePlaceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrizePlaceServiceTest {

    private final PrizePlaceService placeService = new PrizePlaceService();

    @Test
    @DisplayName("Should calculate total score of athlete")
    void totalScoreTest() {
        List<DecathlonResult> results = new ArrayList<>();
        DecathlonResult r = new DecathlonResult();
        r.javelinThrow = 20.0;
        r._100m = 12.25;
        results.add(r);

        placeService.calculateTotalScore(results);

        assertEquals(764, results.get(0).totalScore);
    }

    @Test
    @DisplayName("Determined places should be equal")
    void determinedPlacesShouldBeEqual() {
        List<DecathlonResult> results = new ArrayList<>();
        DecathlonResult r1 = new DecathlonResult();
        r1.totalScore = 1000;
        results.add(r1);
        DecathlonResult r2 = new DecathlonResult();
        r2.totalScore = 1000;
        results.add(r2);

        placeService.determinePrizePlaces(results);

        assertEquals(results.get(0).place, results.get(1).place);
    }

    @Test
    @DisplayName("Determined places should be diff (based on totalScore)")
    void determinedPlacesShouldBeDiff() {
        List<DecathlonResult> results = new ArrayList<>();
        DecathlonResult r1 = new DecathlonResult();
        r1.totalScore = 1200;
        results.add(r1);
        DecathlonResult r2 = new DecathlonResult();
        r2.totalScore = 1000;
        results.add(r2);

        placeService.determinePrizePlaces(results);

        Assertions.assertAll(() -> assertEquals("1", results.get(0).place),
                             () -> assertEquals("2", results.get(1).place));
    }
}
