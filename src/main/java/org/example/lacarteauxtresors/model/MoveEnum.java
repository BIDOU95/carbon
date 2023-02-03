package org.example.lacarteauxtresors.model;

import java.util.Arrays;

public enum MoveEnum {
  FORWARD("A"),
  LEFT("G"),
  RIGHT("D");

  private final String letter;

  MoveEnum(String letter) {
    this.letter = letter;
  }

  public static MoveEnum getRowType(String letter) {
    return Arrays.stream(MoveEnum.values())
        .filter(moveEnum -> moveEnum.getLetter().equals(letter))
        .findFirst()
        .orElseThrow(IllegalAccessError::new);
  }

  public String getLetter() {
    return letter;
  }
}
