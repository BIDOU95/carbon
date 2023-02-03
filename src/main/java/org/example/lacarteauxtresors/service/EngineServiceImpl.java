package org.example.lacarteauxtresors.service;

import org.example.lacarteauxtresors.model.Adventurer;
import org.example.lacarteauxtresors.model.Mountain;
import org.example.lacarteauxtresors.model.Treasure;
import org.example.lacarteauxtresors.model.TreasureMap;

import java.util.List;
import java.util.regex.Pattern;

public class EngineServiceImpl implements EngineService {

  private static final String DELIMITER = " - ";
  private static final String COMMENT_REGEX = "#";

  private final AdventurerService adventurerService;
  private final TreasureMapService treasureMapService;

  public EngineServiceImpl(
      AdventurerService adventurerService, TreasureMapService treasureMapService) {
    this.adventurerService = adventurerService;
    this.treasureMapService = treasureMapService;
  }

  @Override
  public List<String> runMap(List<String> rows) {
    TreasureMap treasureMap = null;
    for (String rowString : rows) {
      rowString = rowString.trim();
      List<String> row = List.of(rowString.split(DELIMITER));
      if (TreasureMap.getPattern().matcher(rowString).find()) {
        if (treasureMap != null) {
          throw new IllegalArgumentException("Parsing error : more than 1 map");
        }
        treasureMap = new TreasureMap().build(row);
      } else if (Treasure.getPattern().matcher(rowString).find()) {
        treasureMapService.checkMapCase(treasureMap);
        treasureMapService.addCaseToMap(treasureMap, new Treasure().build(row));
      } else if (Mountain.getPattern().matcher(rowString).find()) {
        treasureMapService.checkMapCase(treasureMap);
        treasureMapService.addCaseToMap(treasureMap, new Mountain().build(row));
      } else if (Adventurer.getPattern().matcher(rowString).find()) {
        treasureMapService.checkMapExist(treasureMap);
        Adventurer adventurer = new Adventurer().build(row);
        treasureMapService.addAdventurerToMap(treasureMap, adventurer);
        adventurerService.computeMoves(adventurer, treasureMap);
      } else if (!rowString.startsWith(COMMENT_REGEX)) {
        throw new IllegalArgumentException("Fail to parse the map with line " + rowString);
      }
    }
    return treasureMapService.exportMapToString(treasureMap);
  }
}
