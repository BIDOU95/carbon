package org.example.lacarteauxtresors.model;

import java.util.List;

public abstract class AbstractRow<T> {
  public abstract T build(List<String> args);

  public abstract String buildRow();
}
