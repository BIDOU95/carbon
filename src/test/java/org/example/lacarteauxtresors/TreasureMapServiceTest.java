package org.example.lacarteauxtresors;

import org.example.lacarteauxtresors.model.Adventurer;
import org.example.lacarteauxtresors.model.Case;
import org.example.lacarteauxtresors.model.Mountain;
import org.example.lacarteauxtresors.model.OrientationEnum;
import org.example.lacarteauxtresors.model.TreasureMap;
import org.example.lacarteauxtresors.service.TreasureMapService;
import org.example.lacarteauxtresors.service.TreasureMapServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TreasureMapServiceTest {

    private TreasureMapService treasureMapService = new TreasureMapServiceImpl();

    @Test
    void getCaseAtTest() {
        // ARRANGE
        TreasureMap treasureMap = new TreasureMap().build(List.of("C", "5", "5"));
        Mountain mountain = new Mountain().build(List.of("M", "1", "1"));
        treasureMap.getCases().add(mountain);

        // ACT
        Case resultCase = treasureMapService.getCaseAt(treasureMap, 1, 1);

        // ASSERT
        assertNotNull(resultCase);
        assertEquals(1, resultCase.getPosX());
        assertEquals(1, resultCase.getPosY());
        assertTrue(resultCase instanceof Mountain);
    }

    @Test
    void addCaseTest() {
        // ARRANGE
        TreasureMap treasureMap = new TreasureMap().build(List.of("C", "5", "5"));
        Mountain mountain = new Mountain().build(List.of("M", "0", "0"));

        // ACT
        treasureMapService.addCaseToMap(treasureMap, mountain);

        // ASSERT
        Case resultCase = treasureMapService.getCaseAt(treasureMap, 0, 0);
        assertEquals(0, resultCase.getPosX());
        assertEquals(0, resultCase.getPosY());
        assertTrue(resultCase instanceof Mountain);
    }

    @Test
    void addCaseBadCoordinatesTest() {
        // ARRANGE
        TreasureMap treasureMap = new TreasureMap().build(List.of("C", "5", "5"));
        Mountain mountain = new Mountain().build(List.of("M", "-1", "-1"));

        // ACT
        try {
            treasureMapService.addCaseToMap(treasureMap, mountain);
            assert false;
        } catch (IllegalArgumentException exception) {
            assert true;
        }
    }

    @Test
    void addCaseOnObstacleTest() {
        // ARRANGE
        TreasureMap treasureMap = new TreasureMap().build(List.of("C", "5", "5"));
        Mountain mountain1 = new Mountain().build(List.of("M", "1", "1"));
        Mountain mountain2 = new Mountain().build(List.of("M", "1", "1"));
        treasureMap.getCases().add(mountain1);

        // ACT & ASSERT
        try {
            treasureMapService.addCaseToMap(treasureMap, mountain2);
            assert false;
        } catch (IllegalArgumentException exception) {
            assert true;
        }
    }

    @Test
    void addAdventurerTest() {
        // ARRANGE
        TreasureMap treasureMap = new TreasureMap().build(List.of("C", "5", "5"));
        Adventurer adventurer = new Adventurer().build(List.of("A", "Main", "0", "0", "N", "DAAADAG"));

        // ACT
        treasureMapService.addAdventurerToMap(treasureMap, adventurer);

        // ASSERT
        assertEquals(0, adventurer.getPosX());
        assertEquals(0, adventurer.getPosY());
        assertEquals(OrientationEnum.NORTH, adventurer.getOrientation());
    }

    @Test
    void getAdventurerAtTest() {
        // ARRANGE
        TreasureMap treasureMap = new TreasureMap().build(List.of("C", "5", "5"));
        Adventurer adventurer = new Adventurer().build(List.of("A", "Main", "0", "0", "N", "DAAADAG"));
        treasureMapService.addAdventurerToMap(treasureMap, adventurer);

        //ACT

        Adventurer result = treasureMapService.getAdventurerAt(treasureMap, 0, 0);

        // ASSERT
        assertEquals(adventurer, result);
    }

    @Test
    void addAdventurerOnObstacleTest() {
        // ARRANGE
        TreasureMap treasureMap = new TreasureMap().build(List.of("C", "5", "5"));
        Adventurer adventurer = new Adventurer().build(List.of("A", "Main", "1", "1", "N", "DAAADAG"));
        treasureMapService.addCaseToMap(treasureMap, new Mountain().build(List.of("M", "1", "1")));

        // ACT & ASSERT
        try {
            treasureMapService.addAdventurerToMap(treasureMap, adventurer);
            assert false;
        } catch (IllegalArgumentException exception) {
            assert true;
        }
    }

    @Test
    void addAdventurerOnAdventurerTest() {
        // ARRANGE
        TreasureMap treasureMap = new TreasureMap().build(List.of("C", "5", "5"));
        Adventurer main = new Adventurer().build(List.of("A", "Main", "1", "1", "N", "DAAADAG"));
        Adventurer otherAdventurer = new Adventurer().build(List.of("A", "Main", "1", "1", "N", "DAAADAG"));
        treasureMap.getAdventurers().add(main);

        // ACT & ASSERT
        try {
            treasureMapService.addAdventurerToMap(treasureMap, otherAdventurer);
            assert false;
        } catch (IllegalArgumentException exception) {
            assert true;
        }
    }

    @Test
    void addAdventurerBadCoordinatesTest() {
        // ARRANGE
        TreasureMap treasureMap = new TreasureMap().build(List.of("C", "5", "5"));
        Adventurer adventurer = new Adventurer().build(List.of("A", "Main", "-1", "-1", "N", "DAAADAG"));

        // ACT & ASSERT
        try {
            treasureMapService.addAdventurerToMap(treasureMap, adventurer);
            assert false;
        } catch (IllegalArgumentException exception) {
            assert true;
        }
    }

    @Test
    void addCaseAfterAdventurerTest() {
        // ARRANGE
        TreasureMap treasureMap = new TreasureMap().build(List.of("C", "5", "5"));
        Adventurer adventurer = new Adventurer().build(List.of("A", "Main", "1", "1", "N", "DAAADAG"));
        Mountain mountain = new Mountain().build(List.of("M", "1", "1"));
        treasureMapService.addAdventurerToMap(treasureMap, adventurer);

        // ACT & ASSERT
        try {
            treasureMapService.addCaseToMap(treasureMap, mountain);
            assert false;
        } catch (IllegalArgumentException exception) {
            assert true;
        }
    }
}
