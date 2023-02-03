package org.example.lacarteauxtresors;


import org.example.lacarteauxtresors.model.Adventurer;
import org.example.lacarteauxtresors.model.Mountain;
import org.example.lacarteauxtresors.model.OrientationEnum;
import org.example.lacarteauxtresors.model.Treasure;
import org.example.lacarteauxtresors.model.TreasureMap;
import org.example.lacarteauxtresors.service.AdventurerService;
import org.example.lacarteauxtresors.service.AdventurerServiceImpl;
import org.example.lacarteauxtresors.service.TreasureMapService;
import org.example.lacarteauxtresors.service.TreasureMapServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AdventurerServiceTest {

    private TreasureMapService treasureMapService = new TreasureMapServiceImpl();
    private AdventurerService adventurerService = new AdventurerServiceImpl(treasureMapService);

    @Test
    void computeMovesWithoutCase() {
        // ARRANGE
        TreasureMap treasureMap = new TreasureMap().build(List.of("C", "5", "5"));
        Adventurer adventurer = new Adventurer().build(List.of("A", "Main", "0", "0", "N", "DAAADAG"));
        treasureMapService.addAdventurerToMap(treasureMap, adventurer);

        // ACT
        adventurerService.computeMoves(adventurer, treasureMap);

        // ASSERT
        assertEquals(3, adventurer.getPosX());
        assertEquals(1, adventurer.getPosY());
        assertEquals(OrientationEnum.EAST, adventurer.getOrientation());
    }

    @Test
    void computeMovesWithObstacle() {
        // ARRANGE
        TreasureMap treasureMap = new TreasureMap().build(List.of("C", "5", "5"));
        treasureMapService.addCaseToMap(treasureMap, new Mountain().build(List.of("M", "1", "1")));
        Adventurer adventurer = new Adventurer().build(List.of("A", "Main", "0", "0", "N", "ADAADADA"));
        Adventurer otherAdventurer = new Adventurer().build(List.of("A", "Other", "2", "0", "N", ""));
        treasureMapService.addAdventurerToMap(treasureMap, adventurer);
        treasureMapService.addAdventurerToMap(treasureMap, adventurer);

        // ACT
        adventurerService.computeMoves(adventurer, treasureMap);

        // ASSERT
        assertEquals(0, adventurer.getPosX());
        assertEquals(0, adventurer.getPosY());
        assertEquals(OrientationEnum.WEST, adventurer.getOrientation());
    }

    @Test
    void computeMovesWithTreasure() {
        // ARRANGE
        TreasureMap treasureMap = new TreasureMap().build(List.of("C", "5", "5"));
        Treasure treasure = new Treasure().build(List.of("T", "1", "1", "2"));
        treasureMapService.addCaseToMap(treasureMap, treasure);
        Adventurer adventurer = new Adventurer().build(List.of("A", "Test", "0", "0", "N", "ADADADA"));
        treasureMapService.addAdventurerToMap(treasureMap, adventurer);

        // ACT
        adventurerService.computeMoves(adventurer, treasureMap);

        // ASSERT
        assertEquals(0, adventurer.getPosX());
        assertEquals(1, adventurer.getPosY());
        assertEquals(OrientationEnum.WEST, adventurer.getOrientation());
        assertEquals(1, adventurer.getTreasureHold());
        assertEquals(1, treasure.getNumberOfTreasure());
        assertFalse(treasure.getAdventurerOnCase());
    }
}
