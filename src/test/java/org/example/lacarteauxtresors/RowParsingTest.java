package org.example.lacarteauxtresors;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.example.lacarteauxtresors.model.Adventurer;
import org.example.lacarteauxtresors.model.Mountain;
import org.example.lacarteauxtresors.model.Treasure;
import org.example.lacarteauxtresors.model.TreasureMap;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RowParsingTest {
    @Test
    void testPatterMap() {
        // ARRANGE
        List<ImmutablePair<String, Boolean>> tests = List.of(
                ImmutablePair.of("C - 4 - 5", true),
                ImmutablePair.of("C -4 - 5", false),
                ImmutablePair.of("C - R - U", false),
                ImmutablePair.of("E - 4 - 5", false),
                ImmutablePair.of("C - 4 - 5 - 4", false),
                ImmutablePair.of("C - 4", false),
                ImmutablePair.of("C - -4 - 5", false)
        );
        // ACT
        assertTrue(tests.stream().allMatch(test -> test.getRight().equals(TreasureMap.getPattern().matcher(test.getLeft()).find())));
    }

    @Test
    void testPatterMountain() {
        // ARRANGE
        List<ImmutablePair<String, Boolean>> tests = List.of(
                ImmutablePair.of("M - 4 - 5", true),
                ImmutablePair.of("M -4 - 5", false),
                ImmutablePair.of("M - R - U", false),
                ImmutablePair.of("E - 4 - 5", false),
                ImmutablePair.of("M - 4 - 5 - 4", false),
                ImmutablePair.of("M - 4", false),
                ImmutablePair.of("M - -4 - 5", false)
        );
        // ACT
        assertTrue(tests.stream().allMatch(test -> test.getRight().equals(Mountain.getPattern().matcher(test.getLeft()).find())));
    }

    @Test
    void testPatterTreasure() {
        // ARRANGE
        List<ImmutablePair<String, Boolean>> tests = List.of(
                ImmutablePair.of("T - 4 - 5 - 3", true),
                ImmutablePair.of("T -4 - 5 - 4", false),
                ImmutablePair.of("T - 4 - 5 -", false),
                ImmutablePair.of("T - R - U - 3", false),
                ImmutablePair.of("E - 4 - 5 - 7", false),
                ImmutablePair.of("T - 4 - 5 - 4 - 3", false),
                ImmutablePair.of("T - 4 - 5", false),
                ImmutablePair.of("T - -4 - 5 - 3", false),
                ImmutablePair.of("T - 4 - 5 - -3", false)
        );
        // ACT
        assertTrue(tests.stream().allMatch(test -> test.getRight().equals(Treasure.getPattern().matcher(test.getLeft()).find())));
    }

    @Test
    void testPatterAdventurer() {
        // ARRANGE
        List<ImmutablePair<String, Boolean>> tests = List.of(
                ImmutablePair.of("A - LaRa - 4 - 5 - S - ADG", true),
                ImmutablePair.of("U - LaRa - 4 - 5 - S - ADG", false),
                ImmutablePair.of("A - L4r;a - 4 - 5 - S - ADG", false),
                ImmutablePair.of("A - Lara - 4 - 5 - G - ADG", false),
                ImmutablePair.of("A - Lara - 4 - 5 - S - AR", false),
                ImmutablePair.of("A - Lara - -4 - -5 - S - ADG", false),
                ImmutablePair.of("A - Lara - 4 - 5 - n - adg", false),
                ImmutablePair.of("A - Lara - 4 - 5 - S - ADG - 4", false)
        );
        // ACT
        assertTrue(tests.stream().allMatch(test -> test.getRight().equals(Adventurer.getPattern().matcher(test.getLeft()).find())));
    }
}
