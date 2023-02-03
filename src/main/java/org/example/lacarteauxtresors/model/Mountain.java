package org.example.lacarteauxtresors.model;

import java.util.List;
import java.util.regex.Pattern;

public class Mountain extends Case<Mountain> {

  private static final String PATTERN = "M - [0-9]+ - [0-9]+$";

  public static Pattern getPattern() {
    return Pattern.compile(PATTERN);
  }

  @Override
  public boolean canVisit(Adventurer adventurer) {
    return false;
  }

  @Override
  public void visit(Adventurer adventurer) {
    throw new IllegalArgumentException("Action not allowed");
  }

  @Override
  public Mountain build(List<String> args) {
    if (args.size() != 3) {
      throw new IllegalArgumentException("Invalid number of argument");
    }
    try {
      posX = Integer.parseInt(args.get(1));
      posY = Integer.parseInt(args.get(2));
      return this;
    } catch (NumberFormatException exception) {
      throw new IllegalArgumentException("Wrong integer format");
    }
  }

  @Override
  public String buildRow() {
    return "M - " + posX + " - " + posY;
  }
}
