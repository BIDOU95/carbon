package org.example.lacarteauxtresors.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class TreasureMap extends AbstractRow<TreasureMap> {
  private static final String PATTERN = "C - [0-9]+ - [0-9]+$";
  private final List<Case> cases = new ArrayList<>();
  private final List<Adventurer> adventurers = new ArrayList<>();
  private int sizeX;
  private int sizeY;

  public static Pattern getPattern() {
    return Pattern.compile(PATTERN);
  }

  public int getSizeX() {
    return sizeX;
  }

  public int getSizeY() {
    return sizeY;
  }

  public List<Adventurer> getAdventurers() {
    return adventurers;
  }

  public List<Case> getCases() {
    return cases;
  }

  @Override
  public TreasureMap build(List<String> args) {
    if (args.size() != 3) {
      throw new IllegalArgumentException("Invalid number of argument");
    }
    try {
      this.sizeX = Integer.parseInt(args.get(1));
      this.sizeY = Integer.parseInt(args.get(2));
      return this;
    } catch (NumberFormatException exception) {
      throw new IllegalArgumentException("Wrong integer format");
    }
  }

  @Override
  public String buildRow() {
    return "C - " + sizeX + " - " + sizeY;
  }
}
