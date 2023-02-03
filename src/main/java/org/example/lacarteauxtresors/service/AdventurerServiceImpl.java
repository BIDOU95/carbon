package org.example.lacarteauxtresors.service;

import org.example.lacarteauxtresors.model.Adventurer;
import org.example.lacarteauxtresors.model.Case;
import org.example.lacarteauxtresors.model.MoveEnum;
import org.example.lacarteauxtresors.model.OrientationEnum;
import org.example.lacarteauxtresors.model.TreasureMap;

public class AdventurerServiceImpl implements AdventurerService {

  private final TreasureMapService treasureMapService;

  public AdventurerServiceImpl(TreasureMapService treasureMapService) {
    this.treasureMapService = treasureMapService;
  }

  @Override
  public void computeMoves(Adventurer adventurer, TreasureMap map) {
    adventurer.getMoves().forEach(move -> computeNextMove(move, adventurer, map));
  }

  private void computeNextMove(MoveEnum move, Adventurer adventurer, TreasureMap map) {
    switch (adventurer.getOrientation()) {
      case EAST:
        if (MoveEnum.LEFT.equals(move)) {
          adventurer.setOrientation(OrientationEnum.NORTH);
        } else if (MoveEnum.RIGHT.equals(move)) {
          adventurer.setOrientation(OrientationEnum.SOUTH);
        } else {
          move(adventurer.getPosX() + 1, adventurer.getPosY(), adventurer, map);
        }
        break;
      case WEST:
        if (MoveEnum.LEFT.equals(move)) {
          adventurer.setOrientation(OrientationEnum.SOUTH);
        } else if (MoveEnum.RIGHT.equals(move)) {
          adventurer.setOrientation(OrientationEnum.NORTH);
        } else {
          move(adventurer.getPosX() - 1, adventurer.getPosY(), adventurer, map);
        }
        break;
      case NORTH:
        if (MoveEnum.LEFT.equals(move)) {
          adventurer.setOrientation(OrientationEnum.WEST);
        } else if (MoveEnum.RIGHT.equals(move)) {
          adventurer.setOrientation(OrientationEnum.EAST);
        } else {
          move(adventurer.getPosX(), adventurer.getPosY() - 1, adventurer, map);
        }
        break;
      case SOUTH:
        if (MoveEnum.LEFT.equals(move)) {
          adventurer.setOrientation(OrientationEnum.EAST);
        } else if (MoveEnum.RIGHT.equals(move)) {
          adventurer.setOrientation(OrientationEnum.WEST);
        } else {
          move(adventurer.getPosX(), adventurer.getPosY() + 1, adventurer, map);
        }
        break;
    }
  }

  private void move(int newPosX, int newPosY, Adventurer adventurer, TreasureMap map) {
    if (newPosX > map.getSizeX() || newPosX < 0 || newPosY > map.getSizeY() || newPosY < 0) {
      // Out of range
      return;
    }
    if (treasureMapService.getAdventurerAt(map, newPosX, newPosY) != null) {
      return;
    }
    Case newCaseItem = treasureMapService.getCaseAt(map, newPosX, newPosY);
    if (newCaseItem != null) {
      if (!newCaseItem.canVisit(adventurer)) {
        return;
      }
      newCaseItem.visit(adventurer);
    }
    Case oldCase = treasureMapService.getCaseAt(map, adventurer.getPosX(), adventurer.getPosY());
    if (oldCase != null) {
      oldCase.setAdventurerOnCase(false);
    }
    adventurer.setPosX(newPosX);
    adventurer.setPosY(newPosY);
  }
}
