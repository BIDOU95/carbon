package org.example.lacarteauxtresors.model;

public abstract class Case<T> extends AbstractRow<T> {
  protected int posX;
  protected int posY;
  private boolean adventurerOnCase;

  public abstract boolean canVisit(Adventurer adventurer);

  public abstract void visit(Adventurer adventurer);

  public int getPosX() {
    return posX;
  }

  public int getPosY() {
    return posY;
  }

  public boolean getAdventurerOnCase() {
    return adventurerOnCase;
  }

  public Case setAdventurerOnCase(boolean adventurerOnCase) {
    this.adventurerOnCase = adventurerOnCase;
    return this;
  }
}
