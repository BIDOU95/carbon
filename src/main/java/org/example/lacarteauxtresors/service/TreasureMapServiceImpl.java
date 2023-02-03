package org.example.lacarteauxtresors.service;

import org.example.lacarteauxtresors.model.Adventurer;
import org.example.lacarteauxtresors.model.Case;
import org.example.lacarteauxtresors.model.TreasureMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TreasureMapServiceImpl implements TreasureMapService {

  @Override
  public void addAdventurerToMap(TreasureMap map, Adventurer adventurer) {
    checkCoordinates(adventurer.getPosX(), adventurer.getPosY(),map);
    Case newCaseItem = getCaseAt(map, adventurer.getPosX(), adventurer.getPosY());
    if (newCaseItem != null && newCaseItem.canVisit(adventurer)) {
      newCaseItem.visit(adventurer);
    } else if (newCaseItem != null) {
      throw new IllegalArgumentException("Case is not free");
    }
    if (map.getAdventurers().stream().anyMatch(otherAdventurer -> otherAdventurer.getPosX() == adventurer.getPosX() && otherAdventurer.getPosY() == adventurer.getPosY())) {
      throw new IllegalArgumentException("An adventurer is already on the case");
    }
    map.getAdventurers().add(adventurer);
  }

  @Override
  public Case getCaseAt(TreasureMap treasureMap, int posX, int posY) {
    return treasureMap.getCases().stream()
        .filter(caseItem -> caseItem.getPosX() == posX && caseItem.getPosY() == posY)
        .findFirst()
        .orElse(null);
  }

  @Override
  public Adventurer getAdventurerAt(TreasureMap treasureMap, int posX, int posY) {
    return treasureMap.getAdventurers().stream()
        .filter(caseItem -> caseItem.getPosX() == posX && caseItem.getPosY() == posY)
        .findFirst()
        .orElse(null);
  }

  @Override
  public void addCaseToMap(TreasureMap treasureMap, Case newCase) {
    checkCoordinates(newCase.getPosX(), newCase.getPosY(),treasureMap);
    if (getCaseAt(treasureMap, newCase.getPosX(), newCase.getPosY()) != null) {
      throw new IllegalArgumentException(
          "Case already taken for coordinates x : "
              + newCase.getPosX()
              + " / "
              + "y : "
              + newCase.getPosY());
    }
    if (!treasureMap.getAdventurers().isEmpty()) {
      throw new IllegalArgumentException("Cannot add case after adventurer");
    }
    treasureMap.getCases().add(newCase);
  }

  @Override
  public void checkMapExist(TreasureMap map) {
    if (map == null) {
      throw new IllegalArgumentException("Parsing error : map not setup");
    }
  }

  @Override
  public void checkMapCase(TreasureMap map) {
    checkMapExist(map);
    if (!map.getAdventurers().isEmpty()) {
      throw new IllegalArgumentException("Parsing error : cannot setup map after adventurers");
    }
  }

  @Override
  public List<String> exportMapToString(TreasureMap map) {
    if (map == null) {
      return List.of();
    }
    List<String> resultRows = new ArrayList<>();
    resultRows.add(map.buildRow());
    resultRows.addAll(
        map.getCases().stream()
            .map(Case::buildRow)
            .filter(Objects::nonNull)
            .collect(Collectors.toList()));
    resultRows.addAll(
        map.getAdventurers().stream().map(Adventurer::buildRow).collect(Collectors.toList()));
    return resultRows;
  }

  private void checkCoordinates(int posX, int posY, TreasureMap map) {
    if (posX > map.getSizeX()
            || posX < 0
            || posY > map.getSizeY()
            || posY < 0) {
      throw new IllegalArgumentException("Out of range coordinates");
    }
  }
}
