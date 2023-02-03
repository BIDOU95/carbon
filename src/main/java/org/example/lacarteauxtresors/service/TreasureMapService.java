package org.example.lacarteauxtresors.service;

import org.example.lacarteauxtresors.model.Adventurer;
import org.example.lacarteauxtresors.model.Case;
import org.example.lacarteauxtresors.model.TreasureMap;

import java.util.List;

public interface TreasureMapService {

  void addAdventurerToMap(TreasureMap treasureMap, Adventurer adventurer);

  Case getCaseAt(TreasureMap treasureMap, int posX, int posY);

  Adventurer getAdventurerAt(TreasureMap treasureMap, int posX, int posY);

  void addCaseToMap(TreasureMap treasureMap, Case newCase);

  void checkMapExist(TreasureMap map);

  void checkMapCase(TreasureMap map);

  List<String> exportMapToString(TreasureMap map);
}
