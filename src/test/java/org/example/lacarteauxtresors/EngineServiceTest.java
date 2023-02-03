package org.example.lacarteauxtresors;

import org.example.lacarteauxtresors.service.AdventurerService;
import org.example.lacarteauxtresors.service.AdventurerServiceImpl;
import org.example.lacarteauxtresors.service.EngineService;
import org.example.lacarteauxtresors.service.EngineServiceImpl;
import org.example.lacarteauxtresors.service.TreasureMapService;
import org.example.lacarteauxtresors.service.TreasureMapServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EngineServiceTest {

    private final TreasureMapService treasureMapService = new TreasureMapServiceImpl();
    private final AdventurerService adventurerService = new AdventurerServiceImpl(treasureMapService);
    private final EngineService engineService = new EngineServiceImpl(adventurerService, treasureMapService);

    @Test
    void testParsingMap() {

        // ARRANGE
        List<String> rows = List.of("C - 5 - 5", "M - 1 - 1", "T - 2 - 2 - 2");

        // ACT
        List<String> result = engineService.runMap(rows);

        // ASSERT
        assertEquals(rows, result);
    }

    @Test
    void testRunMap() {

        // ARRANGE
        List<String> rows = List.of(
                "C - 3 - 4",
                "M - 1 - 0",
                "M - 2 - 1",
                "T - 0 - 3 - 2",
                "T - 1 - 3 - 3",
                "A - Lara - 1 - 1 - S - AADADAGGA");

        // ACT
        List<String> result = engineService.runMap(rows);

        // ASSERT
        assertEquals(List.of("C - 3 - 4",
                "M - 1 - 0",
                "M - 2 - 1",
                "T - 1 - 3 - 2",
                "A - Lara - 0 - 3 - S - 3"), result);
    }

    @Test
    void testRunMapEmptyRows() {

        // ARRANGE
        List<String> rows = List.of();

        // ACT
        List<String> result = engineService.runMap(rows);

        // ASSERT
        assertEquals(rows, result);
    }

    @Test
    void testRunMapWithComment() {

        // ARRANGE
        List<String> rows = List.of(
                "C - 3 - 4",
                "M - 1 - 0",
                "# Comment",
                "M - 2 - 1",
                "T - 0 - 3 - 2",
                "   # Comment",
                "T - 1 - 3 - 3",
                "A - Lara - 1 - 1 - S - AADADAGGA");

        // ACT
        List<String> result = engineService.runMap(rows);

        // ASSERT
        assertEquals(List.of("C - 3 - 4",
                "M - 1 - 0",
                "M - 2 - 1",
                "T - 1 - 3 - 2",
                "A - Lara - 0 - 3 - S - 3"), result);
    }

    @Test
    void testRunMapCaseAfterAdventurer() {

        // ARRANGE
        List<String> rows = List.of(
                "C - 3 - 4",
                "M - 1 - 0",
                "A - Lara - 1 - 1 - S - AADADAGGA",
                "M - 2 - 1");

        // ACT & ASSERT
        try {
            engineService.runMap(rows);
            assert false;
        } catch (IllegalArgumentException exception) {
            assert true;
        }
    }
}
