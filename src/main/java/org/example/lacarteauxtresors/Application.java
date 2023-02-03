package org.example.lacarteauxtresors;

import org.example.lacarteauxtresors.exception.ExportMapException;
import org.example.lacarteauxtresors.exception.NumberArgumentException;
import org.example.lacarteauxtresors.service.AdventurerService;
import org.example.lacarteauxtresors.service.AdventurerServiceImpl;
import org.example.lacarteauxtresors.service.EngineService;
import org.example.lacarteauxtresors.service.EngineServiceImpl;
import org.example.lacarteauxtresors.service.TreasureMapService;
import org.example.lacarteauxtresors.service.TreasureMapServiceImpl;
import org.example.lacarteauxtresors.utils.FileUtils;

import java.util.List;

public class Application {

  public static void main(String[] args) throws NumberArgumentException, ExportMapException {
    if (args.length < 2) {
      throw new NumberArgumentException("Invalid number of argument");
    }
    List<String> rows = FileUtils.readFile(args[0]);
    TreasureMapService treasureMapService = new TreasureMapServiceImpl();
    AdventurerService adventurerService = new AdventurerServiceImpl(treasureMapService);
    EngineService engineService = new EngineServiceImpl(adventurerService, treasureMapService);
    List<String> result = engineService.runMap(rows);
    FileUtils.exportFile(args[1], result);
  }
}
