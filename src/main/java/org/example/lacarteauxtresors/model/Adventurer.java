package org.example.lacarteauxtresors.model;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Adventurer extends AbstractRow<Adventurer> {

  private static final String PATTERN = "A - [a-zA-Z]+ - [0-9]+ - [0-9]+ - [NSEO] - [AGD]+$";
  private String name;
  private int posX;
  private int posY;
  private OrientationEnum orientation;
  private int treasureHold;

  private List<MoveEnum> moves;

  public Adventurer() {}

  public static Pattern getPattern() {
    return Pattern.compile(PATTERN);
  }

  public int getTreasureHold() {
    return treasureHold;
  }

  public Adventurer setTreasureHold(int treasureHold) {
    this.treasureHold = treasureHold;
    return this;
  }

  public OrientationEnum getOrientation() {
    return orientation;
  }

  public Adventurer setOrientation(OrientationEnum orientation) {
    this.orientation = orientation;
    return this;
  }

  public String getName() {
    return name;
  }

  public int getPosX() {
    return posX;
  }

  public Adventurer setPosX(int posX) {
    this.posX = posX;
    return this;
  }

  public int getPosY() {
    return posY;
  }

  public Adventurer setPosY(int posY) {
    this.posY = posY;
    return this;
  }

  public List<MoveEnum> getMoves() {
    return moves;
  }

  @Override
  public Adventurer build(List<String> args) {
    if (args.size() != 6) {
      throw new IllegalArgumentException("Invalid number of argument");
    }
    try {
      this.name = args.get(1);
      this.posX = Integer.parseInt(args.get(2));
      this.posY = Integer.parseInt(args.get(3));
      this.orientation = OrientationEnum.getOrientationType(args.get(4));
      this.moves =
          Stream.of(args.get(5).split("")).map(MoveEnum::getRowType).collect(Collectors.toList());
      return this;
    } catch (NumberFormatException exception) {
      throw new IllegalArgumentException("Wrong integer format");
    }
  }

  @Override
  public String buildRow() {
    return "A - "
        + name
        + " - "
        + posX
        + " - "
        + posY
        + " - "
        + orientation.getLetter()
        + " - "
        + treasureHold;
  }
}
