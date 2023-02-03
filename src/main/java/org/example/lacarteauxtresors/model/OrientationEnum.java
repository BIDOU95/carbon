package org.example.lacarteauxtresors.model;

import java.util.Arrays;

public enum OrientationEnum {
  NORTH("N"),
  SOUTH("S"),
  EAST("E"),
  WEST("O");
  private final String letter;

  OrientationEnum(String letter) {
    this.letter = letter;
  }

  public static OrientationEnum getOrientationType(String letter) {
    return Arrays.stream(OrientationEnum.values())
        .filter(orientationEnum -> orientationEnum.getLetter().equals(letter))
        .findFirst()
        .orElseThrow(IllegalAccessError::new);
  }

  public String getLetter() {
    return letter;
  }
}
