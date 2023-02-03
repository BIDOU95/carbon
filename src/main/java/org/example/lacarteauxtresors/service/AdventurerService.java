package org.example.lacarteauxtresors.service;

import org.example.lacarteauxtresors.model.Adventurer;
import org.example.lacarteauxtresors.model.TreasureMap;

public interface AdventurerService {

  void computeMoves(Adventurer adventurer, TreasureMap map);
}
