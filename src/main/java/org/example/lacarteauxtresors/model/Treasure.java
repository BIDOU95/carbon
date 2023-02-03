package org.example.lacarteauxtresors.model;

import java.util.List;
import java.util.regex.Pattern;

public class Treasure extends Case<Treasure> {

  private static final String PATTERN = "T - [0-9]+ - [0-9]+ - [0-9]+$";
  private int numberOfTreasure;

  public static Pattern getPattern() {
    return Pattern.compile(PATTERN);
  }

  @Override
  public boolean canVisit(Adventurer adventurer) {
    return !getAdventurerOnCase();
  }

  @Override
  public void visit(Adventurer adventurer) {
    if (numberOfTreasure > 0) {
      adventurer.setTreasureHold(adventurer.getTreasureHold() + 1);
      numberOfTreasure -= 1;
    }
    setAdventurerOnCase(true);
  }

  @Override
  public Treasure build(List<String> args) {
    if (args.size() != 4) {
      throw new IllegalArgumentException("Invalid number of argument");
    }
    try {
      this.posX = Integer.parseInt(args.get(1));
      this.posY = Integer.parseInt(args.get(2));
      this.numberOfTreasure = Integer.parseInt(args.get(3));
      return this;
    } catch (NumberFormatException exception) {
      throw new IllegalArgumentException("Wrong integer format");
    }
  }

  @Override
  public String buildRow() {
    if (numberOfTreasure > 0) {
      return "T - " + posX + " - " + posY + " - " + numberOfTreasure;
    }
    return null;
  }
}
